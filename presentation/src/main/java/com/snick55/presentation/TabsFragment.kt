package com.snick55.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.snick55.core.viewBinding
import com.snick55.presentation.databinding.TabsFragmentBinding

class TabsFragment: Fragment(R.layout.tabs_fragment) {

    private val binding by viewBinding<TabsFragmentBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bottomNavView = binding.bottomNavigationView
        val navHost = childFragmentManager.findFragmentById(R.id.tabsContainer) as NavHostFragment
        val navController = navHost.navController
        NavigationUI.setupWithNavController(bottomNavView,navController)
    }

}