package com.app_lab6.task7

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList

class PageStack {
    // Список для хранения страниц
    private val pages = SnapshotStateList<Any>()

    // Счетчики добавленных и удаленных страниц
    var addedPagesCount = mutableIntStateOf(0)
    var removedPagesCount = mutableIntStateOf(0)

    // Метод для добавления страницы
    fun addPage(page: Any) {
        pages.add(page)
        addedPagesCount.intValue += 1
        // Вы можете вызвать здесь обработчик событий, если необходимо
        onPageAdded(page)
    }

    // Метод для удаления страницы
    fun removePage(page: Any) {
        if (pages.remove(page)) {
            removedPagesCount.value += 1
            // Вы можете вызвать здесь обработчик событий, если необходимо
            onPageRemoved(page)
        }
    }

    fun clear() {
        addedPagesCount.intValue = 0
        removedPagesCount.intValue = 0
        pages.clear()
    }

    // Получение списка страниц
    fun getPages(): List<Any> {
        return pages.toList()
    }

    // Обработчики событий
    private fun onPageAdded(page: Any) {
        // Здесь можно добавить логику обработки добавления страницы
    }

    private fun onPageRemoved(page: Any) {
        // Здесь можно добавить логику обработки удаления страницы
    }
}