package ru.practicum.android.diploma.ui.main.models

import ru.practicum.android.diploma.domain.vacancy.models.Vacancy

sealed interface SearchContentStateVO {
    object Base : SearchContentStateVO
    object Loading : SearchContentStateVO
    data class Error(val noInternet: Boolean) : SearchContentStateVO
    data class Success(val vacancies: List<Vacancy>, val found: Int) : SearchContentStateVO
}
