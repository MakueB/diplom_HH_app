package ru.practicum.android.diploma.ui.filter.industry

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentIndustryFilterBinding
import ru.practicum.android.diploma.ui.root.BindingFragment
import ru.practicum.android.diploma.ui.root.RootActivity

class IndustryFilterFragment : BindingFragment<FragmentIndustryFilterBinding>() {

    private val viewModel: IndustryViewModel by viewModel()
    private var industryAdapter: IndustryAdapter? = null
    private val args by navArgs<IndustryFilterFragmentArgs>()

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentIndustryFilterBinding {
        return FragmentIndustryFilterBinding.inflate(inflater, container, false)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.industrySearch.searchEditText.hint = getString(R.string.enter_industry)
        binding.buttonActionIndustry.buttonBlue.text = getString(R.string.select)

        binding.industrySearch.searchEditText.addTextChangedListener(
            onTextChanged = { text, _, _, _ ->
                val hasText = !text.isNullOrEmpty()
                val icon = if (hasText) {
                    ContextCompat.getDrawable(requireContext(), R.drawable.close_24px)
                } else {
                    ContextCompat.getDrawable(requireContext(), R.drawable.search_24px)
                }

                binding.industrySearch.searchEditText.setCompoundDrawablesWithIntrinsicBounds(null, null, icon, null)
                viewModel.filterIndustries(text.toString())
            }
        )

        binding.industrySearch.searchEditText.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                val drawableEnd = binding.industrySearch.searchEditText.compoundDrawables[2]
                if (drawableEnd != null) {
                    val touchX = event.x
                    val drawableStart = binding.industrySearch.searchEditText.width - binding.industrySearch.searchEditText.paddingEnd - drawableEnd.bounds.width()
                    if (touchX >= drawableStart) {
                        binding.industrySearch.searchEditText.text.clear()
                        return@setOnTouchListener true
                    }
                }
            }
            false
        }

        args.selectedIndustryId?.let { viewModel.setPreselectedIndustryId(it) }
        viewModel.getIndustries()

        industryAdapter = IndustryAdapter(object : IndustryClickListener {
            override fun onIndustryClick(selectedIndustry: IndustryListItem) {
                val query = binding.industrySearch.searchEditText.text.toString()
                viewModel.selectIndustry(selectedIndustry.id, query)
            }
        })

        binding.industryRecyclerView.adapter = industryAdapter

        setObservers()

        binding.buttonActionIndustry.buttonBlue.setOnClickListener {
            viewModel.getSelectedIndustry()?.let { selected ->
                val action = IndustryFilterFragmentDirections.actionIndustryFilterFragmentToFilterFragment(
                    selectedIndustryId = selected.id,
                    selectedIndustryName = selected.name
                )
                findNavController().navigate(action)
            }
        }

        // Системная кнопка или жест назад
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            closeFragment(false)
        }

        initUiTopbar()
    }

    private fun setObservers() {
        viewModel.industryState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is IndustryState.CONTENT -> {
                    showContent(state)
                    val anySelected = state.industryListItems.any { it.isSelected }
                    binding.buttonActionIndustry.buttonBlue.isVisible = anySelected
                }
                is IndustryState.ERROR -> {
                    showError()
                }
                is IndustryState.EMPTY -> {
                    showEmpty()
                }
                is IndustryState.LOADING -> {
                    showLoading()
                }
            }
        }
    }

    private fun initUiTopbar() {
        binding.topbar.apply {
            btnFirst.setImageResource(R.drawable.arrow_back_24px)
            btnSecond.isVisible = false
            btnThird.isVisible = false
            header.text = requireContext().getString(R.string.area)
        }

        binding.topbar.btnFirst.setOnClickListener {
            closeFragment(false)
        }
    }

    private fun closeFragment(barVisibility: Boolean) {
        (activity as RootActivity).setNavBarVisibility(barVisibility)
        findNavController().popBackStack()
    }

    private fun showContent(state: IndustryState.CONTENT) {
        binding.industryRecyclerView.visibility = View.VISIBLE
        binding.includedProgressBar.root.visibility = View.GONE
        binding.placeholderNoList.visibility = View.GONE
        binding.placeholderNoIndustry.visibility = View.GONE
        industryAdapter?.submitList(state.industryListItems)
    }

    private fun showError() {
        binding.industryRecyclerView.visibility = View.GONE
        binding.includedProgressBar.root.visibility = View.GONE
        binding.placeholderNoList.visibility = View.VISIBLE
        binding.placeholderNoIndustry.visibility = View.GONE
        binding.buttonActionIndustry.buttonBlue.isVisible = false
    }

    private fun showEmpty() {
        binding.industryRecyclerView.visibility = View.GONE
        binding.includedProgressBar.root.visibility = View.GONE
        binding.placeholderNoList.visibility = View.GONE
        binding.placeholderNoIndustry.visibility = View.VISIBLE
        binding.buttonActionIndustry.buttonBlue.isVisible = false
    }

    private fun showLoading() {
        binding.industryRecyclerView.visibility = View.GONE
        binding.includedProgressBar.root.visibility = View.VISIBLE
        binding.buttonActionIndustry.buttonBlue.isVisible = false
    }
}
