package com.edwinkapkei.formula1.data.api

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class F1APIServiceTest {
    private lateinit var f1APIService: F1APIService
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        f1APIService =
            Retrofit.Builder()
                .baseUrl(mockWebServer.url(""))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(F1APIService::class.java)
    }

    private fun enqueueMockResponse(filename: String) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream(filename)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        mockWebServer.enqueue(mockResponse)
    }

    @Test
    fun getCurrentSchedule_sentRequest_receivedExpected() {
        runBlocking {
            enqueueMockResponse("current_schedule.json")
            val responseBody = f1APIService.getCurrentSchedule("2022").body()
            val request = mockWebServer.takeRequest()

            assertThat(responseBody).isNotNull()
            assertThat(request.path).isEqualTo("/f1/2022/races/?format=json")
        }
    }

    @Test
    fun getCurrentSchedule_sentRequest_receivedCorrectContent() {
        runBlocking {
            enqueueMockResponse("current_schedule.json")
            val responseBody = f1APIService.getCurrentSchedule("2022").body()

            assertThat(responseBody).isNotNull()
            val races = responseBody!!.mRData.raceTable.races
            val race = races[0]

            assertThat(race.raceName).isEqualTo("Bahrain Grand Prix")
            assertThat(race.circuit.circuitName).isEqualTo("Bahrain International Circuit")
        }
    }

    @Test
    fun getCurrentDrivers_sentRequest_receivedExpected() {
        runBlocking {
            enqueueMockResponse("current_drivers.json")
            val responseBody = f1APIService.getCurrentDrivers("2022").body()
            val request = mockWebServer.takeRequest()

            assertThat(responseBody).isNotNull()
            assertThat(request.path).isEqualTo("/f1/2022/driverstandings/?format=json")
        }
    }

    @Test
    fun getCurrentDrivers_sentRequest_receivedCorrectContent() {
        runBlocking {
            enqueueMockResponse("current_drivers.json")
            val responseBody = f1APIService.getCurrentDrivers("2022").body()

            assertThat(responseBody).isNotNull()
            val drivers = responseBody!!.mRData.standingsTable.standingsLists[0].driverStandings
            val driver = drivers[18]
            val driver2 = drivers[5]

            assertThat(driver.driver.driverId).isEqualTo("albon")
            assertThat(driver2.driver.driverId).isEqualTo("hamilton")
            assertThat(driver.points).isEqualTo("4")
            assertThat(driver2.points).isEqualTo("240")
        }
    }

    @Test
    fun getCurrentDriverStandings_sentRequest_receivedExpected() {
        runBlocking {
            enqueueMockResponse("current_drivers.json")
            val responseBody = f1APIService.getCurrentDrivers("2022").body()
            val request = mockWebServer.takeRequest()

            assertThat(responseBody).isNotNull()
            assertThat(request.path).isEqualTo("/f1/2022/driverstandings/?format=json")
        }
    }

    @Test
    fun getCurrentConstructors_sentRequest_receivedCorrectContent() {
        runBlocking {
            enqueueMockResponse("current_constructors.json")
            val responseBody = f1APIService.getCurrentConstructors("2022").body()

            assertThat(responseBody).isNotNull()
            val constructors = responseBody!!.mRData.standingsTable.standingsLists[0].constructorStandings
            val team = constructors[1]

            assertThat(team.points).isEqualTo("554")
            assertThat(team.constructor.constructorId).isEqualTo("ferrari")
        }
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }
}
