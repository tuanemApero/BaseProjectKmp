package com.apero.aiservicekmp.common

import KMPDemo.aiservicekmp.BuildConfig
import com.apero.aiservicekmp.data.model.ApiConfig

object ClientConfig {
    val PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAuVg0t47njOJoF2AtZRj2O7VfPESF4vc1BVmtvI59ZQqlgydmNTddhpKQISMJz94p45aZLoJpQikluV+ywg4a/40UTsPl53nL5nFAnDjCvaeMOYqI74iXoy6nYkF+0yM449qH8kceqLyViZylcetTXU6s/J3DV2eKXo7/eocEef8qtlZlLkFnoppi5UQ5WNZD/qHh9oMknkN/1qYzcckn4pzHmapOAJopLpO9AjudfD9rHD+5Tsg9O3lucnBO4MyZCx5po8K4PX57gvfEmfsSxlC7tQUAcFM5PytxD7j8HwCr1msQTT4otNhjyqPL3opDNgvrtfUhTbhPCEcUh200BQIDAQAB"

    val GENERATE_VIDEO_CONFIG = ApiConfig(
        baseUrl = BuildConfig.BASE_URL_GENERATE
    )

    val STYLE_CONFIG = ApiConfig(
        baseUrl = BuildConfig.BASE_URL_STYLE
    )

    /*config when download image, video using url*/
    val DOWNLOAD_CONFIG = ApiConfig(
        baseUrl = "",
        requestTimeout = 15000L,
        connectTimeout = 15000L,
        socketTimeout = 15000L,
    )

}