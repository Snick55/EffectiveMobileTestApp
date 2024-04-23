package com.snick55.presentation.search

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.text.InputFilter
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.snick55.core.viewBinding
import com.snick55.domain.entities.OfferTicket
import com.snick55.presentation.CyrillicInputFilter
import com.snick55.presentation.PresentationComponentViewModel
import com.snick55.presentation.R
import com.snick55.presentation.databinding.SearchWithCountryFragmentBinding
import com.snick55.presentation.findTopNavController
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

class SearchWithCountryFragment : Fragment(R.layout.search_with_country_fragment) {

    private val binding by viewBinding<SearchWithCountryFragmentBinding>()
    private val args by navArgs<SearchWithCountryFragmentArgs>()

    @Inject
    internal lateinit var factory: SearchWithCountryViewModelFactory
    private val viewModel: SearchWithCountryViewModel by viewModels {
        factory
    }
    private var info = ""

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ViewModelProvider(this).get<PresentationComponentViewModel>()
            .newDetailsComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val filters = arrayOf<InputFilter>(CyrillicInputFilter())
        binding.toET.filters = filters
        binding.fromET.filters = filters

        binding.showAllTicketsBtn.setOnClickListener {
            val from = binding.fromET.text.toString()
            val to = binding.toET.text.toString()
            val direction =
                SearchWithCountryFragmentDirections.actionSearchWithCountryFragmentToAllTicketsFragment(
                    "$from - $to", "$info, 1 пассажир"
                )
            findTopNavController().navigate(direction)
        }

        binding.fromET.setText(args.fromTown)
        binding.toET.setText(args.toTown)

        binding.backTicketBtn.setOnClickListener {
            showDatePickerDialog(binding.backTicketBtn)
        }

        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        val currentMonth = calendar.get(Calendar.MONTH)
        val currentDay = calendar.get(Calendar.DAY_OF_MONTH)

        val currentDate = "$currentDay ${getMonthName(currentMonth + 1)},$currentYear"
        info = currentDate
        binding.dateBtn.text = currentDate
        binding.dateBtn.setOnClickListener {
            showDatePickerDialog(binding.dateBtn)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.tickets.collectLatest {
                    setupFirstItem(it[0])
                    setupSecondItem(it[1])
                    setupThirdItem(it[2])
                }
            }

        }

        binding.goBackIV.setOnClickListener {
            findTopNavController().popBackStack()
        }

        binding.swipeIV.setOnClickListener {
            val temp = binding.fromET.text
            binding.fromET.text = binding.toET.text
            binding.toET.text = temp
        }
        binding.clearIV.setOnClickListener {
            binding.toET.setText("")
        }

    }


    private fun showDatePickerDialog(button: Button) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "$selectedDay ${getMonthName(selectedMonth + 1)},$selectedYear"
                button.text = selectedDate
                info = selectedDate
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }

    private fun getMonthName(monthNumber: Int): String {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.MONTH, monthNumber - 1)

        val dateFormat = SimpleDateFormat("MMMM", Locale.getDefault())
        return dateFormat.format(calendar.time).capitalize(Locale.getDefault())
    }

    @SuppressLint("SetTextI18n")
    private fun setupSecondItem(offerTicket: OfferTicket) {
        with(binding) {
            secondTitle.text = offerTicket.title
            secondTime.text = offerTicket.times.toString().replace(',', ' ')
                .substring(1, offerTicket.times.toString().length - 1)
            secondPrice.text =
                "${offerTicket.price.toString()[0]} ${offerTicket.price.toString().substring(1)}₽ >"
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setupThirdItem(offerTicket: OfferTicket) {
        with(binding) {
            thirdTitle.text = offerTicket.title
            thirdTime.text = offerTicket.times.toString().replace(',', ' ')
                .substring(1, offerTicket.times.toString().length - 1)
            thirdPrice.text =
                "${offerTicket.price.toString()[0]} ${offerTicket.price.toString().substring(1)}₽ >"
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setupFirstItem(offerTicket: OfferTicket) {
        with(binding) {
            firstTitle.text = offerTicket.title
            firstTime.text = offerTicket.times.toString().replace(',', ' ')
                .substring(1, offerTicket.times.toString().length - 1)
            firstPrice.text =
                "${offerTicket.price.toString()[0]} ${offerTicket.price.toString().substring(1)}₽ >"
        }
    }
}