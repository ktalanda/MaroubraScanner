//package pl.k2net.ktalanda.domain
//
//import com.nhaarman.mockito_kotlin.mock
//import io.reactivex.Observable
//import org.junit.Assert
//import org.junit.Before
//import org.junit.Test
//import pl.k2net.ktalanda.data.maroubrascanner.model.Condition
//import pl.k2net.ktalanda.data.maroubrascanner.model.Forecast
//import pl.k2net.ktalanda.data.maroubrascanner.model.Swell
//import pl.k2net.ktalanda.data.maroubrascanner.model.SwellComponent
//import pl.k2net.ktalanda.data.maroubrascanner.model.Wind
//import java.util.Date
//
//class SurfForecastTest {
//    private val surfForecast: SurfForecastImpl = SurfForecastImpl(mock())
//
//    private val forecastArray = listOf(
//            Forecast(
//                    1502550000,
//                    1502582400,
//                    1502539200,
//                    1,
//                    0,
//                    Swell(
//                            1.15,
//                            1.8,
//                            "ft",
//                            1.0,
//                            2.0,
//                            mapOf(
//                                    Pair("combined", SwellComponent(
//                                            4.5,
//                                            10,
//                                            343.05,
//                                            "SSE")
//                                    ),
//                                    Pair("primary", SwellComponent(
//                                            2.5,
//                                            10,
//                                            345.38,
//                                            "SSE")
//                                    ),
//                                    Pair("secondary", SwellComponent(
//                                            0.9,
//                                            8,
//                                            231.63,
//                                            "NE")
//                                    ),
//                                    Pair("tertiary", SwellComponent(
//                                            3.5,
//                                            5,
//                                            51.73,
//                                            "SW")
//                                    )
//                            )),
//
//                    Wind(
//                            21,
//                            80,
//                            "W",
//                            8,
//                            26,
//                            "mph"
//                    ),
//                    Condition(
//                            1017,
//                            16,
//                            10,
//                            "mb",
//                            "c"
//                    )
//            ),
//            Forecast(
//                    1502560800,
//                    1502593200,
//                    1502539200,
//                    0,
//                    0,
//                    Swell(
//                            0.57,
//                            0.89,
//                            "ft",
//                            1.0,
//                            1.0,
//                            mapOf(
//                                    Pair("combined", SwellComponent(
//                                            4.0,
//                                            12,
//                                            343.05,
//                                            "SSE")
//                                    ),
//                                    Pair("primary", SwellComponent(
//                                            2.5,
//                                            10,
//                                            345.38,
//                                            "SSE")
//                                    ),
//                                    Pair("secondary", SwellComponent(
//                                            0.9,
//                                            8,
//                                            231.63,
//                                            "NE")
//                                    ),
//                                    Pair("tertiary", SwellComponent(
//                                            3.5,
//                                            5,
//                                            51.73,
//                                            "SW")
//                                    )
//                            )),
//
//                    Wind(
//                            18,
//                            54,
//                            "SW",
//                            8,
//                            26,
//                            "mph"
//                    ),
//                    Condition(
//                            1017,
//                            16,
//                            10,
//                            "mb",
//                            "c"
//                    )
//            )
//    )
//
//    var errorSpy = false
//
//    @Before
//    fun setUp() {
//        errorSpy = false
//    }
//
//    @Test
//    fun givenValidForecastStream_whenProcessForecast_shouldIterateOverAllElements() {
//        val actual = surfForecast.processForecast(Observable.fromIterable(forecastArray))
//        val actualList = mutableListOf<SurfCondition>()
//        actual.subscribe(
//                { actualList.add(it) },
//                { errorSpy = true }
//        )
//
//        Assert.assertEquals(1, actualList.size)
//        Assert.assertFalse(errorSpy)
//    }
//
//    @Test
//    fun givenEmptyStream_whenProcessForecast_shouldReturnEmptyStream() {
//        val actual = surfForecast.processForecast(Observable.fromIterable(arrayListOf()))
//        val actualList = mutableListOf<SurfCondition>()
//        actual.subscribe(
//                { actualList.add(it) },
//                { errorSpy = true }
//        )
//
//        Assert.assertEquals(0, actualList.size)
//        Assert.assertFalse(errorSpy)
//    }
//
//    @Test
//    fun givenStreamWithNullElement_whenProcessForecast_shouldThrowException() {
//        val actual = surfForecast.processForecast(Observable.fromIterable(arrayListOf(null)))
//        val actualList = mutableListOf<SurfCondition>()
//        actual.subscribe(
//                { actualList.add(it) },
//                { errorSpy = true }
//        )
//
//        Assert.assertEquals(0, actualList.size)
//        Assert.assertTrue(errorSpy)
//    }
//
//    @Test
//    fun givenValidForecast_whenMapForecastToSurfCondition_shouldReturnValidSurfCondition() {
//        val actual = surfForecast.mapForecastToSurfCondition(forecastArray[0])
//
//        Assert.assertEquals(SurfCondition(Date(1502582400000), 1.5, 10, "SSE", 21, "W"), actual)
//    }
//}
