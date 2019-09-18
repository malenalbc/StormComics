package com.malenalbc.stormcomics.server

import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before

open class MockServerTest {
    internal lateinit var webServer: MockWebServer

    @Before
    @Throws(Exception::class)
    fun setup() {
        webServer = MockWebServer()
        webServer.start(8080)
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        webServer.shutdown()
    }
}
