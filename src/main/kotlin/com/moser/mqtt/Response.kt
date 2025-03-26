package com.moser.mqtt

import com.google.gson.annotations.SerializedName

data class Response(

//	@field:SerializedName("correlation_ids")
//	val correlationIds: List<String?>? = null,
//
//	@field:SerializedName("unique_id")
//	val uniqueId: String? = null,
//
//	@field:SerializedName("data")
//	val data: Data? = null,
//
//	@field:SerializedName("visibility")
//	val visibility: Visibility? = null,
//
//	@field:SerializedName("identifiers")
//	val identifiers: List<IdentifiersItem?>? = null,
//
//	@field:SerializedName("origin")
//	val origin: String? = null,
//
//	@field:SerializedName("name")
//	val name: String? = null,
//
//	@field:SerializedName("context")
//	val context: Context? = null,
//
//	@field:SerializedName("time")
//	val time: String? = null

	@SerializedName("end_device_ids") val endDeviceIds: EndDeviceIds? = null,
	@SerializedName("correlation_ids") val correlationIds: List<String?>? = null,
	@SerializedName("received_at") val receivedAt: String? = null,
	@SerializedName("uplink_message") val uplinkMessage: UplinkMessage? = null // ✅ Corrigé !
)

data class ApplicationIds(

	@field:SerializedName("application_id")
	val applicationId: String? = null
)

data class DataRate(

	@field:SerializedName("lora")
	val lora: Lora? = null
)

data class GatewayIds(

	@field:SerializedName("eui")
	val eui: String? = null,

	@field:SerializedName("gateway_id")
	val gatewayId: String? = null
)

data class IdentifiersItem(

	@field:SerializedName("device_ids")
	val deviceIds: DeviceIds? = null
)

data class Settings(

	@field:SerializedName("data_rate")
	val dataRate: DataRate? = null,

	@field:SerializedName("time")
	val time: String? = null,

	@field:SerializedName("frequency")
	val frequency: String? = null,

	@field:SerializedName("timestamp")
	val timestamp: Long? = null
)

data class Location(

	@field:SerializedName("altitude")
	val altitude: Int? = null,

	@field:SerializedName("latitude")
	val latitude: Any? = null,

	@field:SerializedName("source")
	val source: String? = null,

	@field:SerializedName("longitude")
	val longitude: Any? = null
)

data class RxMetadataItem(

	@field:SerializedName("rssi")
	val rssi: Int? = null,

	@field:SerializedName("uplink_token")
	val uplinkToken: String? = null,

	@field:SerializedName("channel_index")
	val channelIndex: Int? = null,

	@field:SerializedName("snr")
	val snr: Any? = null,

	@field:SerializedName("received_at")
	val receivedAt: String? = null,

	@field:SerializedName("channel_rssi")
	val channelRssi: Int? = null,

	@field:SerializedName("location")
	val location: Location? = null,

	@field:SerializedName("time")
	val time: String? = null,

	@field:SerializedName("gateway_ids")
	val gatewayIds: GatewayIds? = null,

	@field:SerializedName("timestamp")
	val timestamp: Long? = null
)

data class NetworkIds(

	@field:SerializedName("tenant_id")
	val tenantId: String? = null,

	@field:SerializedName("cluster_id")
	val clusterId: String? = null,

	@field:SerializedName("ns_id")
	val nsId: String? = null,

	@field:SerializedName("net_id")
	val netId: String? = null,

	@field:SerializedName("cluster_address")
	val clusterAddress: String? = null
)

data class Data(

	@field:SerializedName("correlation_ids")
	val correlationIds: List<String?>? = null,

	@field:SerializedName("@type")
	val type: String? = null,

	@field:SerializedName("end_device_ids")
	val endDeviceIds: EndDeviceIds? = null,

	@field:SerializedName("received_at")
	val receivedAt: String? = null,

	@field:SerializedName("uplink_message")
	val uplinkMessage: UplinkMessage? = null
)

data class UplinkMessage(

	@field:SerializedName("settings")
	val settings: Settings? = null,

	@field:SerializedName("frm_payload")
	val frmPayload: String? = null,

	@field:SerializedName("session_key_id")
	val sessionKeyId: String? = null,

	@field:SerializedName("received_at")
	val receivedAt: String? = null,

	@field:SerializedName("f_port")
	val fPort: Int? = null,

	@field:SerializedName("f_cnt")
	val fCnt: Int? = null,

	@field:SerializedName("network_ids")
	val networkIds: NetworkIds? = null,

	@field:SerializedName("rx_metadata")
	val rxMetadata: List<RxMetadataItem?>? = null,

	@field:SerializedName("consumed_airtime")
	val consumedAirtime: String? = null
)

data class Visibility(

	@field:SerializedName("rights")
	val rights: List<String?>? = null
)

data class Context(

	@field:SerializedName("tenant-id")
	val tenantId: String? = null
)

data class EndDeviceIds(

	@field:SerializedName("join_eui")
	val joinEui: String? = null,

	@field:SerializedName("device_id")
	val deviceId: String? = null,

	@field:SerializedName("application_ids")
	val applicationIds: ApplicationIds? = null,

	@field:SerializedName("dev_addr")
	val devAddr: String? = null,

	@field:SerializedName("dev_eui")
	val devEui: String? = null
)

data class Lora(

	@field:SerializedName("bandwidth")
	val bandwidth: Int? = null,

	@field:SerializedName("coding_rate")
	val codingRate: String? = null,

	@field:SerializedName("spreading_factor")
	val spreadingFactor: Int? = null
)

data class DeviceIds(

	@field:SerializedName("join_eui")
	val joinEui: String? = null,

	@field:SerializedName("device_id")
	val deviceId: String? = null,

	@field:SerializedName("application_ids")
	val applicationIds: ApplicationIds? = null,

	@field:SerializedName("dev_addr")
	val devAddr: String? = null,

	@field:SerializedName("dev_eui")
	val devEui: String? = null
)
