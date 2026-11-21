package com.capyreader.app.ui.settings.panels

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.capyreader.app.R
import com.capyreader.app.common.RowItem
import com.capyreader.app.ui.components.TextSwitch
import org.koin.androidx.compose.koinViewModel

@Composable
fun InAppBrowserRow(viewModel: GeneralSettingsViewModel = koinViewModel()) {
    RowItem {
        TextSwitch(
            checked = viewModel.canOpenLinksInternally,
            onCheckedChange = viewModel::updateOpenLinksInternally,
            title = stringResource(R.string.settings_option_in_app_browser)
        )
    }
}
