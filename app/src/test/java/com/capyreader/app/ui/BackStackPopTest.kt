package com.capyreader.app.ui

import androidx.navigation3.runtime.NavKey
import com.jocmp.capy.ArticleFilter
import org.junit.Assert.assertEquals
import org.junit.Test

class BackStackPopTest {
    private val list = Route.ArticleList(ArticleFilter.default())

    @Test
    fun `pop removes the top entry`() {
        val backStack = mutableListOf<NavKey>(list, Route.Settings)

        backStack.pop()

        assertEquals(listOf(list), backStack)
    }

    @Test
    fun `pop keeps the root entry`() {
        val backStack = mutableListOf<NavKey>(list)

        backStack.pop()

        assertEquals(listOf(list), backStack)
    }

    @Test
    fun `pop with key is a no-op on a repeated call`() {
        val settings = Route.Settings
        val backStack = mutableListOf<NavKey>(list, settings)

        backStack.pop(settings)
        backStack.pop(settings)

        assertEquals(listOf(list), backStack)
    }

    @Test
    fun `pop with key ignores an entry that is not on top`() {
        val settings = Route.Settings
        val addAccount = Route.AddAccount
        val backStack = mutableListOf<NavKey>(list, settings, addAccount)

        backStack.pop(settings)

        assertEquals(listOf(list, settings, addAccount), backStack)
    }

    @Test
    fun `popIf pops a top entry whose key was swapped in place`() {
        val backStack = mutableListOf<NavKey>(list, Route.Settings)
        backStack[backStack.lastIndex] = Route.AddAccount

        backStack.popIf { it is Route.Settings }
        backStack.popIf { it is Route.AddAccount }

        assertEquals(listOf(list), backStack)
    }
}
