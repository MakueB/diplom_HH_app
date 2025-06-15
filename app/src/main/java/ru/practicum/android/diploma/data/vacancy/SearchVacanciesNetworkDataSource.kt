package ru.practicum.android.diploma.data.vacancy

import ru.practicum.android.diploma.data.VacanciesSearchRequest
import ru.practicum.android.diploma.data.network.ApiResponse
import ru.practicum.android.diploma.data.network.NetworkClient
import ru.practicum.android.diploma.data.vacancy.models.SearchVacanciesDto
import ru.practicum.android.diploma.data.vacancy.models.VacancyDto
import ru.practicum.android.diploma.domain.models.FilterOptions
import ru.practicum.android.diploma.domain.vacancy.models.Vacancy
import ru.practicum.android.diploma.util.HTTP_200_OK
import ru.practicum.android.diploma.util.HTTP_400_BAD_REQUEST
import ru.practicum.android.diploma.util.HTTP_500_INTERNAL_SERVER_ERROR
import ru.practicum.android.diploma.util.HTTP_NO_CONNECTION
import ru.practicum.android.diploma.util.VACANCY_PER_PAGE

class SearchVacanciesNetworkDataSource(
    private val networkClient: NetworkClient,
) {
    suspend fun getSearchResults(options: FilterOptions): ApiResponse<List<Vacancy>> {
        val request = VacanciesSearchRequest(getOptions(options))
        val response = networkClient.doRequest(request)
        return when (response.resultCode) {
            HTTP_NO_CONNECTION -> ApiResponse.Error(HTTP_NO_CONNECTION)
            HTTP_200_OK -> {
                with(response as SearchVacanciesDto) {
                    val data = items.map { formatVacancy(it) }
                    ApiResponse.Success(data, response.page, response.pages)
                }
            }

            HTTP_400_BAD_REQUEST -> ApiResponse.Error(HTTP_400_BAD_REQUEST)
            else -> ApiResponse.Error(HTTP_500_INTERNAL_SERVER_ERROR)
        }
    }

    private fun formatVacancy(data: VacancyDto): Vacancy {
        return Vacancy(
            id = data.id,
            title = data.name,
            employerTitle = data.employer.name,
            logoUrl = data.employer.logoUrls?.size240,
            salaryRange = data.salaryRange?.let {
                Vacancy.VacancySalaryRange(
                    currency = data.salaryRange.currency,
                    from = data.salaryRange.from,
                    to = data.salaryRange.to,
                )
            }
        )
    }

    private fun getOptions(options: FilterOptions): HashMap<String, String> {
        val hhOptions: HashMap<String, String> = HashMap()
        hhOptions["per_page"] = VACANCY_PER_PAGE
        hhOptions["text"] = options.searchText
        hhOptions["page"] = options.page.toString()
        if (options.area.isNotEmpty()) {
            hhOptions["area"] = options.area
        }
        if (options.currency.isEmpty()) {
            hhOptions["currency"] = "RUR"
        }
        if (options.salary != null) {
            hhOptions["currency"] = options.currency
        }
        if (options.industry.isNotEmpty()) {
            hhOptions["industry"] = options.industry
        }
        hhOptions["only_with_salary"] = options.onlyWithSalary.toString()
        return hhOptions
    }
}
