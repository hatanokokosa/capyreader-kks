package com.capyreader.app.ui.articles

import com.capyreader.app.preferences.AppPreferences
import com.capyreader.app.ui.addintent.AddLinkViewModel
import com.capyreader.app.ui.articles.feeds.edit.EditFeedViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

internal val articlesModule = module {
    factory {
        AddFeedViewModel(
            account = get(),
        )
    }
    factory {
        AddLinkViewModel(
            account = get(),
        )
    }
    viewModel {
        val appPreferences = get<AppPreferences>()

        ArticleScreenViewModel(
            account = get(),
            appPreferences = appPreferences,
            notificationHelper = get(),
            application = get(),
        )
    }
    viewModel {
        EditFeedViewModel(
            account = get(),
        )
    }
    viewModel {
        EditFolderViewModel(
            account = get(),
            appPreferences = get(),
        )
    }
}
