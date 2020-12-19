package com.trimonovds.catan.shared

import kotlin.test.Test
import kotlin.test.assertTrue

class PlayerTest {
    @Test
    fun whenPlayerIsCreatedItHasCorrectPool() {
        val sut = Player()
        assertTrue(sut.pool.filter { it == Building.Settlement }.size == 5)
        assertTrue(sut.pool.filter { it == Building.City }.size == 4)
        assertTrue(sut.pool.filter { it == Building.Road }.size == 15)
    }
}