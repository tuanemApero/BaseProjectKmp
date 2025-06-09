package com.apero.aiservicekmp.data.datasource.impl

import com.apero.aiservicekmp.data.datasource.StyleRemoteDatasource
import com.apero.aiservicekmp.data.datasource.impl.GenerateVideoRemoteDataSourceImpl.Companion.TIMEOUT_MILLIS
import com.apero.aiservicekmp.data.model.response.AiServiceResponseState
import com.apero.aiservicekmp.data.model.response.aivideo.styleitem.CategoryDto
import com.apero.aiservicekmp.utils.error.ResponseCodeError.GET_STYLE_ERROR_CODE
import com.apero.aiservicekmp.utils.error.ResponseCodeError.TIME_OUT_ERROR_CODE
import com.apero.aiservicekmp.utils.error.ResponseMessageError.TIME_OUT_ERROR_MESSAGE
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull

class StyleRemoteDataSourceImpl(
    private val client: HttpClient
) : StyleRemoteDatasource {

    override suspend fun getAllStylesWithCategory(appName : String, sheet : String): AiServiceResponseState<List<CategoryDto>> =
        withContext(Dispatchers.IO) {
            withTimeoutOrNull(TIMEOUT_MILLIS) {
                try {
                    val response = client.get("style-external/styles") {
                        url {
                            parameters.append("appName", appName)
                            parameters.append("sheet", sheet)
                        }
                    }.body<List<CategoryDto>>()
                    AiServiceResponseState.Success(response)
                } catch (e: Exception) {
                    AiServiceResponseState.Error(GET_STYLE_ERROR_CODE, e.message ?: "Unknown error")
                }

            } ?: AiServiceResponseState.Error(TIME_OUT_ERROR_CODE, TIME_OUT_ERROR_MESSAGE)
        }

}