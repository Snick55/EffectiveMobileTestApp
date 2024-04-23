package com.snick55.presentation.tickets

import android.content.Context
import android.os.Bundle
import android.text.InputFilter
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.snick55.core.viewBinding
import com.snick55.presentation.CyrillicInputFilter
import com.snick55.presentation.PresentationComponentViewModel
import com.snick55.presentation.R
import com.snick55.presentation.TabsFragmentDirections
import com.snick55.presentation.databinding.AviaTicketsFragmentBinding
import com.snick55.presentation.findTopNavController
import javax.inject.Inject

class AviaTicketsFragment : Fragment(R.layout.avia_tickets_fragment) {

    @Inject
    internal lateinit var factory:AviaTicketsViewModelFactory
    private val viewModel: AviaTicketsViewModel by viewModels {
        factory
    }

    private val binding by viewBinding<AviaTicketsFragmentBinding>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ViewModelProvider(this).get<PresentationComponentViewModel>()
            .newDetailsComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = OffersAdapter()
        binding.offersRV.adapter = adapter
        binding.fromET.setText(viewModel.getTown())
        val filters = arrayOf<InputFilter>(CyrillicInputFilter())
        binding.toET.filters = filters
        binding.fromET.filters = filters

        binding.toET.setOnClickListener {
            val fromTown = binding.fromET.text.toString()
            val toTown = binding.toET.text.toString()
            val direction = TabsFragmentDirections.actionTabsFragmentToSearchBottomSheetFragment(fromTown, toTown)
            viewModel.saveTown(fromTown)
            findTopNavController().navigate(direction)
        }


        viewModel.offers.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }
}

