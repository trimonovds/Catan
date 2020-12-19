package com.trimonovds.catan.shared

import kotlin.test.Test
import kotlin.test.assertTrue

class DicesTest {
    @Test
    fun testRollDicesShouldReturnValueFrom2To12() {
        val results = mutableListOf<DicesValues>()
        for (i in 0..1000) {
            val dicesValues = rollDices()
            results.add(dicesValues)
        }
        assertTrue(results.all { it.sum in (2..12) } )
    }
}