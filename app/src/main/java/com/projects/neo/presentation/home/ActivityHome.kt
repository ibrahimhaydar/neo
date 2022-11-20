package com.projects.neo.presentation.home


import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.projects.neo.R
import com.projects.neo.data.util.AppConstants
import com.projects.neo.data.util.AppHelper
import com.projects.neo.data.util.LocaleUtils
import com.projects.neo.data.util.extensions.safeCall
import com.projects.neo.databinding.ActivityHomeBinding
import com.projects.neo.presentation.BaseCompactActivity
import com.projects.neo.presentation.home.portfolio.PortfolioViewModel
import com.projects.neo.presentation.home.portfolio.portfolioViewModelFactory
import com.projects.neo.presentation.home.portfolio_details.PortfolioDetailsViewModel
import com.projects.neo.presentation.home.portfolio_details.portfolioDetailsViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject


@AndroidEntryPoint
class ActivityHome : BaseCompactActivity() {
    @Inject
    lateinit var factory: portfolioViewModelFactory

    @Inject
    lateinit var factoryHistorical: portfolioDetailsViewModelFactory
    lateinit var portfolioViewModel: PortfolioViewModel
    lateinit var portfolioDetailsViewModel: PortfolioDetailsViewModel
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init(){
        portfolioViewModel = ViewModelProvider(this, factory)[PortfolioViewModel::class.java]
        portfolioDetailsViewModel = ViewModelProvider(this, factoryHistorical)[PortfolioDetailsViewModel::class.java]
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_menu, menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.btChangeLanguage -> changeLanguage()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun changeLanguage(){
        if(AppHelper.sessionRepo.language== AppConstants.LANG_ARABIC){
            AppHelper.sessionRepo.language = AppConstants.LANG_ENGLISH
            LocaleUtils.setLocale(Locale("en"))
        }else{
            AppHelper.sessionRepo.language = AppConstants.LANG_ARABIC
            LocaleUtils.setLocale(Locale("ar"))
        }
        safeCall { reloadActivity() }
    }

    private fun reloadActivity(){
        val bundle = ActivityOptionsCompat.makeCustomAnimation(
            this,
            android.R.anim.fade_in, android.R.anim.fade_out
        ).toBundle()
        startActivity(intent, bundle)
        finish()
    }

    fun updateToolbar(title:String,showBack:Boolean){
        supportActionBar!!.setDisplayHomeAsUpEnabled(showBack)
        supportActionBar!!.title = title
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return false
    }

}