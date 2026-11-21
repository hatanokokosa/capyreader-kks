package com.capyreader.app.ui

import androidx.navigation3.runtime.NavKey
import com.jocmp.capy.ArticleFilter
import com.jocmp.capy.accounts.Source
import kotlinx.serialization.Serializable

sealed class Route : NavKey {
    @Serializable
    data object AddAccount : Route()

    @Serializable
    data class Login(val source: Source) : Route()

    @Serializable
    data object Settings : Route()

    /**
     * The article list. [filter] isn't read by the list entry itself — it's a one-way seed that
     * [com.capyreader.app.MainActivity.applyListFilter] copies into
     * [com.capyreader.app.preferences.AppPreferences.filter] at launch/deep-link time, which is
     * the value the list actually reads reactively.
     */
    @Serializable
    data class ArticleList(val filter: ArticleFilter) : Route()
}
