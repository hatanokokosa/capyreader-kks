package com.capyreader.app.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.capyreader.app.preferences.AppPreferences
import com.capyreader.app.ui.accounts.AddAccountScreen
import com.capyreader.app.ui.accounts.LoginScreen
import com.capyreader.app.ui.articles.ArticleScreen
import com.capyreader.app.ui.settings.SettingsScreen
import com.capyreader.app.ui.theme.CapyTheme
import com.capyreader.app.unloadAccountModules
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun App(
    startBackStack: List<NavKey>,
    appPreferences: AppPreferences,
    deepLink: List<NavKey>? = null,
    onDeepLinkConsumed: () -> Unit = {},
) {
    val backStack = rememberNavBackStack(*startBackStack.toTypedArray())

    // Warm-start deep links (onNewIntent): replace the back stack with the link's synthetic stack.
    LaunchedEffect(deepLink) {
        val target = deepLink ?: return@LaunchedEffect
        onDeepLinkConsumed()
        backStack.clear()
        backStack.addAll(target)
    }

    // Window-level drawer: hosting it above NavDisplay lets its scrim cover the list.
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    var drawerContent by remember { mutableStateOf<(@Composable () -> Unit)?>(null) }
    val drawerController = remember(drawerState) {
        AppDrawerController(state = drawerState, setContent = { drawerContent = it })
    }
    val hasDrawerContent = drawerContent != null
    LaunchedEffect(hasDrawerContent) {
        if (!hasDrawerContent && !drawerState.isClosed) {
            drawerState.close()
        }
    }

    CapyTheme(appPreferences) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ModalNavigationDrawer(
                drawerState = drawerState,
                gesturesEnabled = drawerState.isOpen,
                drawerContent = {
                    ModalDrawerSheet { drawerContent?.invoke() }
                },
            ) {
                CompositionLocalProvider(
                    LocalAppDrawer provides drawerController,
                ) {
                    NavDisplay(
                        backStack = backStack,
                        onBack = { backStack.pop() },
                        entryDecorators = listOf(
                            rememberSaveableStateHolderNavEntryDecorator(),
                            rememberViewModelStoreNavEntryDecorator(),
                        ),
                        entryProvider = entryProvider {
                            entry<Route.AddAccount> {
                                AddAccountScreen(
                                    onAddSuccess = { backStack.resetToArticles(appPreferences) },
                                    onNavigateToLogin = { source -> backStack.add(Route.Login(source)) }
                                )
                            }
                            entry<Route.Login> { key ->
                                LoginScreen(
                                    viewModel = koinViewModel { parametersOf(key.source) },
                                    onNavigateBack = { backStack.pop(key) },
                                    onSuccess = { backStack.resetToArticles(appPreferences) },
                                )
                            }
                            entry<Route.Settings> { key ->
                                SettingsScreen(
                                    onRemoveAccount = {
                                        backStack.resetTo(Route.AddAccount)
                                        unloadAccountModules()
                                    },
                                    onNavigateBack = { backStack.pop(key) }
                                )
                            }
                            entry<Route.ArticleList> {
                                ArticleScreen(
                                    onNavigateToSettings = { backStack.add(Route.Settings) },
                                )
                            }
                        }
                    )
                }
            }
        }
    }
}

/** Replaces the entire back stack with [key] (account add/remove transitions). */
private fun NavBackStack<NavKey>.resetTo(key: NavKey) {
    clear()
    add(key)
}

private fun NavBackStack<NavKey>.resetToArticles(appPreferences: AppPreferences) {
    resetTo(Route.ArticleList(appPreferences.filter.get()))
}
