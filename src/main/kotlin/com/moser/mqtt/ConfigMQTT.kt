package com.moser.mqtt


const val SERVER_URI = "eu1.cloud.thethings.network";
const val PORT = 8883;
const val USERNAME = "projet-enerdis-2025@ttn";
val PASSWORD = (System.getenv("TTN_API_KEY") ?: error("Variable TTN_API_KEY manquante")).toByteArray()
const val TOPIC = "#";
