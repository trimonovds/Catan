package com.trimonovds.catan.shared

import org.junit.Assert.assertTrue
import org.junit.Test

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