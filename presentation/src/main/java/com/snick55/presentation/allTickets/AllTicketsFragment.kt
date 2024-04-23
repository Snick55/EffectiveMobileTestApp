package com.snick55.presentation.allTickets

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.snick55.core.viewBinding
import com.snick55.presentation.PresentationComponentViewModel
import com.snick55.presentation.R
import com.snick55.presentation.databinding.AllTicketsFragmentBinding
import com.snick55.presentation.findTopNavController
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
class AllTicketsFragment: Fragment(R.layout.all_tickets_fragment) {

    private val binding by viewBinding<AllTicketsFragmentBinding>()
    private val args by navArgs<AllTicketsFragmentArgs>()
    @Inject
    internal lateinit var factory: AllTicketsViewModelFactory
    private val viewModel: AllTicketsViewModel by viewModels {
        factory
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ViewModelProvider(this).get<PresentationComponentViewModel>()
            .newDetailsComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.townsTV.text = args.towns
        binding.infoTV.text = args.info
        val adapter = TicketsAdapter()
        binding.ticketsRV.adapter = adapter
        binding.goBackIV.setOnClickListener {
            findTopNavController().popBackStack()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.tickets.collectLatest {
                    adapter.submitList(it)
                }
            }

        }

    }

}