package com.moser.mqtt


import com.google.gson.Gson
import com.hivemq.client.mqtt.MqttClient
import com.hivemq.client.mqtt.datatypes.MqttQos
import com.hivemq.client.mqtt.mqtt3.Mqtt3AsyncClient
import com.serveur.bdd_MySql.Gestion
import java.util.*
import java.util.Base64

class MQTT {
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
//                println(mqtt3Publish.payloadAsBytes.toString())

                val payloadString = mqtt3Publish.payloadAsBytes?.let { String(it, Charsets.UTF_8) }
                println("Message reçu: $payloadString")

//                payloadString?.let {
//                    val jsonObject = gson.fromJson(it, Response::class.java)
//                    val frmPayloadBase64 = jsonObject.data?.uplinkMessage?.frmPayload
//
//                    frmPayloadBase64?.let { base64Str ->
//                        //val decodedBytes = Base64.decode(base64Str, Base64.DEFAULT)  // Pour Android
//                        val decodedBytes = Base64.getDecoder().decode(base64Str) // Pour Kotlin pur
//                        val decodedString = String(decodedBytes, Charsets.UTF_8)
//
//                        println("frm_payload décodé: $decodedString")
//                    }
//                }

                payloadString?.let {
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