package pl.k2net.ktalanda.maroubrascanner.main.chart

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class HourAxisValueFormatter(private val referenceTimestamp: Long) : IAxisValueFormatter {
    override fun getFormattedValue(value: Float, axis: AxisBase?): String {
        val originalTimestamp = referenceTimestamp + value.toLong() * 60 * 60 * 1000
        val date = Date(originalTimestamp)
        return SimpleDateFormat( "EEE", Locale.ENGLISH).format(date)
    }
}
