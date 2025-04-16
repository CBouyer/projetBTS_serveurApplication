package com.moser.mqtt


import Gestion
import com.google.gson.Gson
import com.hivemq.client.mqtt.MqttClient
import com.hivemq.client.mqtt.datatypes.MqttQos
import com.hivemq.client.mqtt.mqtt3.Mqtt3AsyncClient
import com.moser.plugins.webSocketSessions
import java.util.Base64
import io.ktor.websocket.*
import kotlinx.coroutines.*


class MQTT (){

    private lateinit var client: Mqtt3AsyncClient

    private val gestion = Gestion()

    init {
        client = MqttClient.builder()
            .useMqttVersion3()
            .serverHost(SERVER_URI)
            .serverPort(PORT)
            .sslWithDefaultConfig()
            .buildAsync()
        println(client.config.toString())

    }

    fun connect() {
        client.connectWith()
            .simpleAuth()
            .username(USERNAME)
            .password(PASSWORD)
            .applySimpleAuth()
            .send()
            .whenComplete { mconnAck, throwable ->
                println("appel Subscribe")
                subscribe()
            }
        println("Connected to ${client.config.serverHost}")
    }

    fun subscribe() {
        val gson = Gson()
        println("subscrip to ${client.config.serverHost}")
        client.subscribeWith()
            .topicFilter(TOPIC)
            .qos(MqttQos.AT_MOST_ONCE)
            .callback { mqtt3Publish ->

                val payloadString = mqtt3Publish.payloadAsBytes?.let { String(it, Charsets.UTF_8) }
                println("Message reçu: $payloadString")

                payloadString?.let {

                    CoroutineScope(Dispatchers.IO).launch {
                        webSocketSessions.forEach { session ->
                            try {
                                session.send(Frame.Text(it))
                            } catch (e: Exception) {
                                println("Erreur envoi WebSocket: ${e.message}")
                            }
                        }
                    }

                    val response = gson.fromJson(it, Response::class.java)

                    if (response.uplinkMessage == null) {
                        println("⚠️ 'uplinkMessage' est null dans Response")
                    } else {
                        println("✅ 'uplinkMessage' est bien extrait")
                    }

                    val frmPayloadBase64 = response.uplinkMessage?.frmPayload

                    if (frmPayloadBase64.isNullOrEmpty()) {
                        println("⚠️ frm_payload est null ou vide")
                    } else {
                        println("✅ frm_payload extrait en base64: $frmPayloadBase64")

                        try {
                            val decodedBytes = Base64.getDecoder().decode(frmPayloadBase64)
                            val decodedString = String(decodedBytes, Charsets.UTF_8)

                            println("✅ frm_payload décodé: $decodedString")
                        } catch (e: Exception) {
                            println("❌ Erreur lors du décodage Base64: ${e.message}")
                        }
                    }
                }
            }
            .send()
            .whenComplete { mqtt3SubAck, throwable ->
                println("WhenComplet ok")
                if (throwable != null) {
                    println("Subscribe Failed!")
                } else {
                    println("Subscribe MQTT Ok!")
                }
            }

    }

}