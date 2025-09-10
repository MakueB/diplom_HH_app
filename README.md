# HH Assistant – Карьера и вакансии от HH.ru

<a href="https://github.com/MakueB/diplom_HH_app"><img src="https://img.shields.io/badge/Platform-Android-green.svg" alt="Platform"></a>
<a href="https://kotlinlang.org/"><img src="https://img.shields.io/badge/Kotlin-100%25-purple.svg" alt="Kotlin"></a>
<a href="https://developer.android.com/jetpack/compose"><img src="https://img.shields.io/badge/UI-Jetpack%20Compose-blue.svg" alt="Jetpack Compose"></a>

**HH Assistant** — это современное Android-приложение, предлагающее удобный и быстрый способ поиска вакансий на платформе HeadHunter (HH.ru). Помимо основного поиска, приложение предоставляет мощные инструменты для организации работы с вакансиями: избранное, фильтрация и удобные виджеты для главного экрана.

<p align="center">
  <img src="/readme_assets/screen_main.png" width="24%" alt="Main Screen"/>
  <img src="/readme_assets/screen_search.png" width="24%" alt="Search Screen"/>
  <img src="/readme_assets/screen_filters.png" width="24%" alt="Filters Screen"/>
  <img src="/readme_assets/screen_widget.png" width="24%" alt="Widget Screen"/>
</p>

## 🚀 Возможности

*   **Поиск вакансий:** Интеграция с официальным API HH.ru для полнотекстового поиска.
*   **Расширенная фильтрация:** Гибкая система фильтров (регион, опыт, зарплата, тип занятости).
*   **Избранное:** Сохранение понравившихся вакансий в локальную базу данных для офлайн-доступа.
*   **Виджеты:** Умные виджеты для главного экрана, которые отображают количество найденных вакансий и позволяют быстро запустить поиск.
*   **Тёмная тема:** Полная поддержка светлой и тёмной темы в соответствии с настройками системы.
*   **Динамический цвет:** Поддержка динамической цветовой схемы (Material You) на Android 12+.

## 🛠 Стек технологий

*   **Язык:** [Kotlin](https://kotlinlang.org/)
*   **Декларативный UI:** [Jetpack Compose](https://developer.android.com/jetpack/compose) (100% кодовая база UI)
*   **Архитектура:** [MVVM](https://developer.android.com/topic/architecture) (Model-View-ViewModel) + Clean Architecture
*   **Асинхронность:** [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-guide.html) с [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/)
*   **Локальная база данных:** [Room Database](https://developer.android.com/training/data-storage/room)
*   **Сетевые запросы:** [Retrofit 2](https://square.github.io/retrofit/) с [Moshi](https://github.com/square/moshi)
*   **DI (Внедрение зависимостей):** [Koin](https://insert-koin.io/)
*   **Виджеты:** [Glance AppWidget](https://developer.android.com/develop/ui/views/appwidget/glance) (для создания виджетов на Compose)

## 📦 Установка и запуск

1.  Склонируйте репозиторий:
    ```bash
    git clone https://github.com/MakueB/diplom_HH_app.git
    ```
2.  Откройте проект в **Android Studio** (версия Iguana или новее).
3.  Дождитесь завершения сборки и синхронизации Gradle.
4.  Соберите и запустите приложение на подключенном устройстве или эмуляторе (требуется API 24+).

## 🏗 Ключевые особенности реализации

*   **Чистая Архитектура:** Четкое разделение на слои (data, domain, presentation) обеспечивает тестируемость, поддерживаемость и гибкость кода.
*   **Единый источник истины (Single Source of Truth):** Данные из сети сохраняются в базе данных (Room), и UI наблюдает за изменениями через Flow. Это обеспечивает стабильность и офлайн-доступ к избранным вакансиям.
*   **Кастомизация UI:** Реализованы кастомные компоненты, включая элементы для экрана фильтров и отображения вакансий.

---
<div align="center">
Разработано с ❤️ от <a href="https://github.com/MakueB">MakueB</a>
</div>
