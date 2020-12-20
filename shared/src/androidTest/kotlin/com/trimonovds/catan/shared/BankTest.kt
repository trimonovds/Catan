package com.trimonovds.catan.shared

import org.junit.Assert.assertTrue
import org.junit.Test

class BankTest {
    @Test
    fun whenBankIsCreatedItContains19ResourcesOfEachKind() {
        val sut = Bank()
        for (r in Resource.values()) {
            assertTrue(sut.resources.filter { it == r }.size == 19)
        }
    }

    @Test
    fun whenBankIsCreatedItContains14Knights() {
        val sut = Bank()
        assertTrue(sut.developmentCards.filter { it == DevelopmentCard.Knight }.size == 14)
    }

    @Test
    fun whenBankIsCreatedItContains2ProgressCardsOfEachKind() {
        val sut = Bank()
        for (kind in listOf<DevelopmentCard>(
            DevelopmentCard.BuildRoads,
            DevelopmentCard.TakeAnyResources,
            DevelopmentCard.Monopoly
        )) {
            assertTrue(sut.developmentCards.filter { it == kind }.size == 2)
        }
    }

    @Test
    fun whenBankIsCreatedItContains5VictoryPoints() {
        val sut = Bank()
        assertTrue(sut.developmentCards.filter { it == DevelopmentCard.VictoryPoint }.size == 5)
    }
}