package com.projects.neo.presentation.home.portfolio


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.projects.neo.R
import com.projects.neo.data.model.Portfolio
import com.projects.neo.data.util.extensions.hide
import com.projects.neo.data.util.extensions.show
import com.projects.neo.databinding.FragmentPortfolioBinding
import com.projects.neo.presentation.BaseFragments
import com.projects.neo.presentation.home.ActivityHome
import javax.inject.Inject

class FragmentPortfolio : BaseFragments() {

    private lateinit var fragmentPortfolioBinding: FragmentPortfolioBinding
    private lateinit var portfolioViewModel: PortfolioViewModel
    private lateinit var arrayPortfolios :ArrayList<Portfolio>
    private var adapterPortfolio: AdapterPortfolio?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_portfolio, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentPortfolioBinding = FragmentPortfolioBinding.bind(view)
        init()
    }

    private fun init(){
        (activity as ActivityHome).updateToolbar(getString(R.string.home),false)
        portfolioViewModel = (activity as ActivityHome).portfolioViewModel
         setPortfolios()
    }

    private fun getPortfolios(){
        fragmentPortfolioBinding.loader.loading.show()
            val responseLiveData = portfolioViewModel.getPortfolios()
            responseLiveData.observe(requireActivity(), Observer {
                fragmentPortfolioBinding.loader.loading.hide()
                if(it!!.data!!.portfolios.isNotEmpty()){
                    arrayPortfolios.clear()
                    arrayPortfolios.addAll(it.data!!.portfolios)
                    adapterPortfolio!!.notifyDataSetChanged()
                }

            })
    }

    private fun setPortfolios(){
        arrayPortfolios= arrayListOf()
        adapterPortfolio = AdapterPortfolio(requireActivity(),arrayPortfolios)
        fragmentPortfolioBinding.rvPortfolio.apply {
            adapter = adapterPortfolio
            layoutManager = LinearLayoutManager(activity)
        }
        adapterPortfolio!!.setOnItemClickListener {
            val bundle = Bundle().apply { putSerializable("selected_portfolio",it) }
            findNavController().navigate(R.id.action_fragmentPortfolio_to_fragmentPortfolioDetails,bundle)
        }
        getPortfolios()
    }


}