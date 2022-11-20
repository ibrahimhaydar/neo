package com.projects.neo.data.util

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Typeface
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.View
import androidx.core.content.ContextCompat
import androidx.preference.PreferenceManager
import com.f2prateek.rx.preferences2.RxSharedPreferences
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IFillFormatter
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.google.gson.Gson
import com.projects.neo.R
import com.projects.neo.data.model.Historical
import com.projects.neo.data.model.Strings
import com.projects.neo.data.repository.SessionRepository
import com.projects.neo.data.repository.pref.PrefSessionRepository
import com.projects.neo.data.util.AppConstants.DATE_FORMAT_FULL_YDM
import com.projects.neo.data.util.AppConstants.DATE_FORMAT_ORIGINAL
import com.projects.neo.data.util.extensions.logw
import dev.b3nedikt.restring.Restring
import dev.b3nedikt.restring.toMutableRepository
import dev.b3nedikt.reword.Reword
import org.json.JSONObject
import java.io.IOException
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class AppHelper {
    companion object{
        val Two_Decimal_Thousands_zero = "#,###.##"
        lateinit var sessionRepo: SessionRepository
        var ORIGINAL_FORMAT = SimpleDateFormat(DATE_FORMAT_ORIGINAL, Locale.US)
        var sdfYMD = SimpleDateFormat(DATE_FORMAT_FULL_YDM, Locale.US)
        fun setLocal(context: Context) {
            sessionRepo = getSessionRepository(context)
            if (sessionRepo.language == AppConstants.LANG_ENGLISH) {
                LocaleUtils.setLocale(Locale("en"))
            } else if (sessionRepo.language == AppConstants.LANG_ARABIC) {
                LocaleUtils.setLocale(Locale("ar", "LB"))
            }
        }

        fun setLocalStrings(activity: Activity,  locale: Locale) {
            Restring.stringRepository.toMutableRepository().strings.clear()
            if(sessionRepo.localize.isNotEmpty()){
            var strings = Gson().fromJson(sessionRepo.localize,Strings::class.java)
            if(strings != null){
                try {
                    val myStringsMap: HashMap<String, String> = HashMap()
                    val json = JSONObject(Gson().toJson(strings))
                    val keys = json.keys()
                    while (keys.hasNext()) {
                        val key = keys.next()
                        myStringsMap[key] = json.getJSONObject(key).getString(sessionRepo.language)
                    }
                    Restring.putStrings(locale, myStringsMap)
                    Restring.locale = locale
                    updateView(activity)
                } catch (exception: IOException) {

                }
            }}
        }
        fun setInEnglish(): DecimalFormatSymbols {
            val custom = DecimalFormatSymbols(Locale.ENGLISH)
            custom.decimalSeparator = '.'
            return custom
        }

         fun setLineChart(context: Context,linechart: LineChart,array:ArrayList<Historical>){
            var xAxisLabel = arrayListOf<String>()
            val values = java.util.ArrayList<Entry>()
            array.forEachIndexed { index, historical ->
                values.add(Entry(index.toFloat(),array[index].smartWealthValue.toFloat()))
                xAxisLabel.add(array[index].date)

            }
            val set1: LineDataSet = LineDataSet(values, "")
            set1.setDrawIcons(true)
            set1.color = Color.BLACK
            set1.color = context.getColor(R.color.gold_text)
            set1.setCircleColor(context.getColor(R.color.gold_text))
            set1.lineWidth = 1f
            set1.circleRadius = 1f
            set1.setDrawCircleHole(false)
            set1.formLineWidth = 1f
            set1.formLineDashEffect = DashPathEffect(floatArrayOf(10f, 5f), 0f)
            set1.formSize = 15f
            set1.valueTextSize = 8f
            set1.setDrawFilled(false)
            set1.fillFormatter = IFillFormatter { dataSet, dataProvider -> linechart.axisLeft.axisMinimum }
            val drawable = ContextCompat.getDrawable(context, R.drawable.roud_rect_primary)
            set1.fillDrawable = drawable
            set1.mode = LineDataSet.Mode.CUBIC_BEZIER
            val dataSets = java.util.ArrayList<ILineDataSet>()
            dataSets.add(set1)
            val data = LineData(dataSets)
            linechart.setTouchEnabled(true)
            linechart.isHighlightPerTapEnabled=true
            linechart.setTouchEnabled(true)
            linechart.xAxis.valueFormatter = IndexAxisValueFormatter(xAxisLabel)
            linechart.legend.isEnabled=false
            linechart.xAxis.position = XAxis.XAxisPosition.BOTTOM
            linechart.xAxis.spaceMax = 0.5f
            linechart.xAxis.spaceMin = 0.5f
            linechart.axisLeft.setDrawLabels(true)
            linechart.axisRight.setDrawLabels(false)
            linechart.description.text = ""
            linechart.setNoDataText("No data available")
            linechart.data = data
            linechart.invalidate()
        }
        
        
        fun getDateFromTimestamp(timestamp:Long):String?{
            var date=""
            try {
                val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.US)
                val netDate = Date(timestamp*1000)
                date=sdf.format(netDate)

            }catch (exception: Exception){}
            return date
        }

        fun getFormattedNumber(number: String): String? {
            return if (number.isNotEmpty()) {
                val value = number.toDouble()
                NumberFormat.getNumberInstance(Locale.US).format(value)
            } else {
                "0"
            }
        }


        fun formatDate(c:Context,dateString:String,oldDateFormat:String,newDateFormat:String):String?{
            var format = SimpleDateFormat(oldDateFormat, Locale.US)
            val newDate = format.parse(dateString)
            format = SimpleDateFormat(newDateFormat, Locale.US)
            val date = format.format(newDate)
            return date
        }

        private fun updateView(activity: Activity) {
            val rootView: View = activity.window.decorView.findViewById(android.R.id.content)
            Reword.reword(rootView)
        }

        private fun getSessionRepository(context: Context): SessionRepository {
            val prefs = PreferenceManager.getDefaultSharedPreferences(context)
            val rxPrefs = RxSharedPreferences.create(prefs)
            val preUtils = PrefUtils(rxPrefs)
            return PrefSessionRepository(preUtils)
        }

        fun getTypeFace(context: Context): Typeface {
            return if (Locale.getDefault().language == "ar")
                Typeface.createFromAsset(
                    context.applicationContext.assets,
                    "fonts/DroidKufiRegular.ttf"
                )
            else
                Typeface.createFromAsset(
                    context.applicationContext.assets,
                    "fonts/Raleway-Regular.ttf"
                )
        }

        fun getTypeFaceBold(context: Context): Typeface {
            return if (Locale.getDefault().language == "ar")
                Typeface.createFromAsset(
                    context.applicationContext.assets,
                    "fonts/DroidKufi-Bold.ttf"
                )
            else
                Typeface.createFromAsset(
                    context.applicationContext.assets,
                    "fonts/Raleway-Bold.ttf"
                )
        }

         fun isNetworkAvailable(context: Context?):Boolean{
            if (context == null) return false
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                if (capabilities != null) {
                    when {
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                            return true
                        }
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                            return true
                        }
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                            return true
                        }
                    }
                }
            } else {
                val activeNetworkInfo = connectivityManager.activeNetworkInfo
                if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                    return true
                }
            }
            return false

        }
    }
}