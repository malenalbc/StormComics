package com.malenalbc.stormcomics.server

import com.malenalbc.stormcomics.data.source.URL_GET_COMICS
import com.malenalbc.stormcomics.data.source.URL_GET_STORM_CHARACTER
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader


internal class MockServerDefaultDispatcher {

    /**
     * Return ok response from mock server
     */
    internal inner class RequestDispatcher : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            println("WTF: $request")
            return when {
                request.path?.startsWith(URL_GET_COMICS) == true -> validResponse().setBody(getStringFromFile("storm_comics.json"))
                request.path?.startsWith(URL_GET_STORM_CHARACTER) == true -> validResponse().setBody(getStringFromFile("storm.json"))
                else -> MockResponse().setResponseCode(404)
            }
        }
    }


    private fun validResponse(): MockResponse {
        return MockResponse()
            .setResponseCode(200)
    }

    /**
     * Return error response from mock server
     */
    internal inner class ErrorDispatcher : Dispatcher() {

        override fun dispatch(request: RecordedRequest): MockResponse {

            return MockResponse().setResponseCode(400)

        }
    }

    companion object {
        private const val STRING_ENCODING = "UTF-8"

        fun getStringFromFile(path: String): String {
            try {
                val resourceAsStream = MockServerDefaultDispatcher::class.java.classLoader!!.getResourceAsStream(path)
                return getStringFromStream(resourceAsStream)
            } catch (exception: IOException) {
                throw RuntimeException(exception)
            }
        }

        @Throws(IOException::class)
        fun getStringFromStream(input: InputStream): String {
            val inputStreamReader = InputStreamReader(input, STRING_ENCODING)
            val bufferedReader = BufferedReader(inputStreamReader)
            val stringBuilder = StringBuilder()
            var line: String? = bufferedReader.readLine()

            do {
                stringBuilder.append(line).append("\n")
                line = bufferedReader.readLine()
            } while (line != null)

            bufferedReader.close()
            inputStreamReader.close()
            return stringBuilder.toString()
        }
    }
}
