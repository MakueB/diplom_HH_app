package ru.practicum.android.diploma.data.vacancy

import ru.practicum.android.diploma.data.network.ApiResponse
import ru.practicum.android.diploma.domain.vacancy.api.SearchVacanciesRepository
import ru.practicum.android.diploma.domain.vacancy.models.Vacancy

class SearchVacanciesRepositoryImpl(
    private val searchVacanciesNetworkDataSource: SearchVacanciesNetworkDataSource,
) : SearchVacanciesRepository {
    override suspend fun search(text: String): ApiResponse<List<Vacancy>> {
        return searchVacanciesNetworkDataSource.getSearchResults(text)
    }
}
