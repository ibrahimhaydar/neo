package com.projects.neo.presentation.home.portfolio_details

import android.graphics.Color
import android.graphics.DashPathEffect
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IFillFormatter
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.utils.Utils
import com.projects.neo.R
import com.projects.neo.data.model.Historical
import com.projects.neo.data.model.Portfolio
import com.projects.neo.data.util.AppConstants.DATE_FORMAT_FULL_YDM
import com.projects.neo.data.util.AppConstants.DATE_FORMAT_ORIGINAL
import com.projects.neo.data.util.AppHelper
import com.projects.neo.data.util.extensions.hide
import com.projects.neo.data.util.extensions.setValueColor
import com.projects.neo.data.util.extensions.show
import com.projects.neo.databinding.FragmentPortfolioDetailsBinding
import com.projects.neo.presentation.BaseFragments
import com.projects.neo.presentation.home.ActivityHome
import com.projects.neo.presentation.home.portfolio.FragmentPortfolioArgs
import java.text.DecimalFormat
import kotlin.math.abs


class FragmentPortfolioDetails : BaseFragments() {
    private lateinit var portfolio: Portfolio
    private lateinit var fragmentPortfolioDetailsBinding: FragmentPortfolioDetailsBinding
    private lateinit var portfolioDetailsViewModel: PortfolioDetailsViewModel
    private lateinit var arrayHistorical :ArrayList<Historical>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_portfolio_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentPortfolioDetailsBinding = FragmentPortfolioDetailsBinding.bind(view)
        init()
    }

    private fun init(){
        portfolioDetailsViewModel = (activity as ActivityHome).portfolioDetailsViewModel
        val args : FragmentPortfolioArgs by navArgs()
        portfolio = args.selectedPortfolio
        (activity as ActivityHome).updateToolbar(portfolio.name,true)
        setData()
        getHistorical()
    }

    private fun setData(){
        fragmentPortfolioDetailsBinding.tvBalanceValue.text =  "$ ${ AppHelper.getFormattedNumber(portfolio.balance.toString())}"
        fragmentPortfolioDetailsBinding.tvEarningValue.text = "$ ${ AppHelper.getFormattedNumber(abs( portfolio.totalEarnings).toString())}"
        fragmentPortfolioDetailsBinding.tvDateValue.text = AppHelper.formatDate(requireActivity(),portfolio.createdAt,DATE_FORMAT_ORIGINAL,DATE_FORMAT_FULL_YDM)
        fragmentPortfolioDetailsBinding.tvRiskValue.text = portfolio.modifiedRiskScore.toString()
        fragmentPortfolioDetailsBinding.tvInvestmentTypeValue.text = portfolio.investmentType
        fragmentPortfolioDetailsBinding.tvEarningValue.setValueColor(portfolio.totalEarnings)

    }

    private fun getHistorical(){
        fragmentPortfolioDetailsBinding.loader.loading.show()
        val responseLiveData = portfolioDetailsViewModel.getHistorical()
        responseLiveData.observe(requireActivity(), Observer {
            fragmentPortfolioDetailsBinding.loader.loading.hide()
            if(it!!.data!!.historical.isNotEmpty()){
                arrayHistorical= arrayListOf()
                arrayHistorical.addAll(it.data!!.historical)
                AppHelper.setLineChart(requireActivity(),fragmentPortfolioDetailsBinding.lineChart,arrayHistorical)
            }

        })
    }



}