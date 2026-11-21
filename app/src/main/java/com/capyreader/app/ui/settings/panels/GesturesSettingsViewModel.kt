package com.capyreader.app.ui.settings.panels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.capyreader.app.preferences.AppPreferences
import com.capyreader.app.preferences.ArticleListVerticalSwipe
import com.capyreader.app.preferences.BackAction
import com.capyreader.app.preferences.RowSwipeOption

class GesturesSettingsViewModel(
    private val appPreferences: AppPreferences
) : ViewModel() {
    var backAction by mutableStateOf(listOptions.backAction.get())
        private set

    var rowSwipeStart by mutableStateOf(listOptions.swipeStart.get())
        private set

    var rowSwipeEnd by mutableStateOf(listOptions.swipeEnd.get())
        private set

    var listSwipeBottom by mutableStateOf(listOptions.swipeBottom.get())
        private set

    var enableEInkScrollbar by mutableStateOf(appPreferences.eInkScrollbar.get())
        private set

    var reduceMotion by mutableStateOf(appPreferences.reduceMotion.get())
        private set

    fun updateBackAction(action: BackAction) {
        backAction = action

        listOptions.backAction.set(action)
    }

    fun updateRowSwipeStart(swipe: RowSwipeOption) {
        rowSwipeStart = swipe

        listOptions.swipeStart.set(swipe)
    }

    fun updateRowSwipeEnd(swipe: RowSwipeOption) {
        rowSwipeEnd = swipe

        listOptions.swipeEnd.set(swipe)
    }

    fun updateListSwipeBottom(swipe: ArticleListVerticalSwipe) {
        listSwipeBottom = swipe

        listOptions.swipeBottom.set(swipe)
    }

    fun updateEInkScrollbar(enabled: Boolean) {
        enableEInkScrollbar = enabled

        appPreferences.eInkScrollbar.set(enabled)
    }

    fun updateReduceMotion(enabled: Boolean) {
        reduceMotion = enabled

        appPreferences.reduceMotion.set(enabled)
    }

    private val listOptions: AppPreferences.ArticleListOptions
        get() = appPreferences.articleListOptions
}
