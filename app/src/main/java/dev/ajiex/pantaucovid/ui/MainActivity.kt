package dev.ajiex.pantaucovid.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.core.view.setMargins
import androidx.core.view.updateLayoutParams
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dev.ajiex.pantaucovid.R
import dev.ajiex.pantaucovid.data.model.SummaryResponse
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.section_dunia.*
import kotlinx.android.synthetic.main.section_lokal.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    lateinit var summaryRespone: SummaryResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.getSummary().observe(this, Observer {
            when (it) {
                is SummaryResponse -> {
                    summaryRespone = it

                    //Default Country = Indonesia
                    setData("ID")
                }
                else -> {

                }
            }
        })

    }

    fun setData(countryCode: String) {
        var country = summaryRespone.Countries?.filter { it.CountryCode == countryCode }!!.first()

        negara.text = country.Country
        total_kasus.text = country.TotalConfirmed.toString()
        meninggal.text = country.TotalDeaths.toString()
        sembuh.text = country.TotalRecovered.toString()

        setStatistics()
    }

    fun setStatistics() {
        var total = summaryRespone.Global?.TotalConfirmed!!.toFloat()
        var meninggal = summaryRespone.Global?.TotalDeaths!!.toFloat()
        var sembuh = summaryRespone.Global?.TotalRecovered!!.toFloat()
        var aktif = total - meninggal - sembuh

        setPercentageIndicator(aktif, total, aktifKasusBar)
        setPercentageIndicator(sembuh, total, sembuhBar)
        setPercentageIndicator(meninggal, total, meninggalBar)

        aktifKasusValue.text = aktif.toInt().toString()
        meninggalValue.text = meninggal.toInt().toString()
        sembuhValue.text = sembuh.toInt().toString()

    }

    fun setPercentageIndicator(part: Float, total: Float, view: View) {
        var percentage = part / total * 100
        var layoutParams =
            LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, percentage)
        layoutParams.setMargins(4)
        view.layoutParams = layoutParams
    }


}
