package ru.practicum.android.diploma.di

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.practicum.android.diploma.data.db.AppDatabase
import ru.practicum.android.diploma.data.db.converters.VacanciesDbConverter
import ru.practicum.android.diploma.data.db.dao.VacanciesDao

val dataModule = module {
    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "hh_database.db")
            .build()
    }

    single<VacanciesDao> {
        get<AppDatabase>().vacanciesDao()
    }

    single {
        VacanciesDbConverter()
    }

}
