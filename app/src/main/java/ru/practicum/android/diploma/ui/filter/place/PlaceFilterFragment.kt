package ru.practicum.android.diploma.ui.filter.place

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.practicum.android.diploma.databinding.FragmentPlaceFilterBinding

class PlaceFilterFragment : Fragment() {
    private var _binding: FragmentPlaceFilterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlaceFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /* Пока здесь ничего нет */
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
