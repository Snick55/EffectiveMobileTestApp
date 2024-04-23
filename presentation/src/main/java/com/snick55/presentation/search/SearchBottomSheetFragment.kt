package com.snick55.presentation.search

import android.app.Dialog
import android.os.Bundle
import android.text.InputFilter
import android.view.View
import android.view.WindowManager
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.snick55.core.viewBinding
import com.snick55.presentation.CyrillicInputFilter
import com.snick55.presentation.R
import com.snick55.presentation.databinding.AviaTicketsBottomSheetBinding
import com.snick55.presentation.findTopNavController

class SearchBottomSheetFragment : BottomSheetDialogFragment(R.layout.avia_tickets_bottom_sheet) {

    private val binding by viewBinding<AviaTicketsBottomSheetBinding>()
    private val args by navArgs<SearchBottomSheetFragmentArgs>()


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        dialog.setOnShowListener {
            val bottomSheetDialog = it as BottomSheetDialog
            val parentLayout =
                bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            parentLayout?.let { layout ->
                val behaviour = BottomSheetBehavior.from(layout)
                setupFullHeight(layout)
                behaviour.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        dialog.window?.setWindowAnimations(R.style.DialogAnimation)
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        val fromTown = args.fromTown
        val toTown = args.toTown
        val filters = arrayOf<InputFilter>(CyrillicInputFilter())
        binding.toET.filters = filters
        binding.fromET.filters = filters

        binding.fromET.setText(fromTown)
        binding.toET.setText(toTown)



    }

    private fun setupListeners(){
        binding.hardWayBtn.setOnClickListener {
            findTopNavController().navigate(R.id.action_searchBottomSheetFragment_to_hardWayFragment)
        }
        binding.anywhereBtn.setOnClickListener {
            findTopNavController().navigate(R.id.action_searchBottomSheetFragment_to_anywhereFragment)
        }

        binding.hotTicketsBtn.setOnClickListener {
            findTopNavController().navigate(R.id.action_searchBottomSheetFragment_to_hotTicketsFragment)
        }
        binding.weekendsBtn.setOnClickListener {
            findTopNavController().navigate(R.id.action_searchBottomSheetFragment_to_weekendsFragment)
        }

        binding.stambul.setOnClickListener {
            setHotTown("Стамбул")
        }
        binding.clearIV.setOnClickListener {
            binding.toET.setText("")
        }
        binding.sochi.setOnClickListener {
            setHotTown("Сочи")
        }
        binding.phuket.setOnClickListener {
            setHotTown("Пхукет")
        }

        binding.toET.setOnClickListener {

            val action =
               SearchBottomSheetFragmentDirections.actionSearchBottomSheetFragmentToSearchWithCountryFragment(
                    binding.fromET.text.toString(), binding.toET.text.toString()
                )
            findTopNavController().navigate(action)
        }
    }

    private fun setHotTown(town: String) {
        binding.toET.setText(town)
    }

    private fun setupFullHeight(bottomSheet: View) {
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
        bottomSheet.layoutParams = layoutParams
    }


}