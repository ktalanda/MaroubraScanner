package pl.k2net.ktalanda.maroubrascanner.main

import android.app.Activity
import android.os.Bundle
import com.github.mikephil.charting.data.BarData
import kotlinx.android.synthetic.main.activity_main.refreshLayout
import kotlinx.android.synthetic.main.activity_main.surfDetails
import kotlinx.android.synthetic.main.activity_main.swellChart
import kotlinx.android.synthetic.main.view_surf_details.view.direction
import kotlinx.android.synthetic.main.view_surf_details.view.period
import kotlinx.android.synthetic.main.view_surf_details.view.swellHeight
import kotlinx.android.synthetic.main.view_surf_details.view.time
import kotlinx.android.synthetic.main.view_surf_details.view.windDirection
import kotlinx.android.synthetic.main.view_surf_details.view.windSpeed
import pl.k2net.ktalanda.maroubrascanner.App
import pl.k2net.ktalanda.maroubrascanner.R
import pl.k2net.ktalanda.maroubrascanner.main.details.DetailsViewModel
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

class MainActivity : Activity(), MainPresenter.ViewInterface {
    @Inject lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        (application as App).component.inject(this)
        presenter.bind(this)

        refreshLayout.setOnRefreshListener {
            presenter.refreshData()
            refreshLayout.isRefreshing = false
        }
        swellChart.config()
    }

    override fun updateDataSet(updatedData: BarData) {
        swellChart.run {
            data = updatedData
            notifyDataSetChanged()
            invalidate()
        }
    }

    override fun updateDetails(updatedDetails: DetailsViewModel.Element) {
        surfDetails.time.text = SimpleDateFormat("EEEE dd/MM/yyyy HH:mm", Locale.ENGLISH).format(updatedDetails.time)
        surfDetails.swellHeight.text = getString(R.string.swellHeightValue, updatedDetails.swellHeight)
        surfDetails.period.text = getString(R.string.periodValue, updatedDetails.period)
        surfDetails.direction.text = updatedDetails.direction
        surfDetails.windSpeed.text = getString(R.string.windSpeedValue, updatedDetails.windSpeed)
        surfDetails.windDirection.text = updatedDetails.windDirection
    }
}
