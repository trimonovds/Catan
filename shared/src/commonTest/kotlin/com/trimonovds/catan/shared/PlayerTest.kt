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

    @Test
    fun whenPlayerIsCreatedItHas0VictoryPoints() {
        val sut = Player()
        val victoryPoints = VictoryPointsCalculator.victoryPoints(
            sut.builtBuildings,
            sut.developmentCards,
            specialCards = emptyList()
        )
        assertTrue(victoryPoints == 0)
    }

    @Test
    fun whenPlayerHasLongestRoadItHas2VictoryPoints() {
        val sut = Player()
        val victoryPoints = VictoryPointsCalculator.victoryPoints(
            sut.builtBuildings,
            sut.developmentCards,
            specialCards = listOf(SpecialCard.LongestRoad)
        )
        assertTrue(victoryPoints == 2)
    }

    @Test
    fun whenPlayerHasLongestRoadAnd2CitiesItHas6VictoryPoints() {
        val sut = Player(builtBuildings = listOf(Building.City, Building.City))
        val victoryPoints = VictoryPointsCalculator.victoryPoints(
            sut.builtBuildings,
            sut.developmentCards,
            specialCards = listOf(SpecialCard.LongestRoad)
        )
        assertTrue(victoryPoints == 6)
    }

    @Test
    fun whenPlayerReceiceResourcesForRoadItCanBuildRoad() {
        var sut = Player()
        sut = sut.perform(Action.Receive(listOf(Resource.Brick, Resource.Lumber)))
        assertTrue(sut.canPerform(Action.Build(Building.Road)))
    }

    @Test
    fun whenPlayerReceiceResourcesForDevCardItCanBuyDevCard() {
        var sut = Player()
        sut = sut.perform(Action.Receive(listOf(Resource.Ore, Resource.Grain, Resource.Wool)))
        assertTrue(sut.canPerform(Action.Buy(DevelopmentCard.Knight)))
    }

    @Test
    fun whenPlayerBuysRoadItReceiveRoadAndLoseResources() {
        var sut = Player()
        sut = sut.perform(Action.Receive(listOf(Resource.Brick, Resource.Lumber)))
        sut = sut.perform(Action.Build(Building.Road))
        assertTrue(sut.builtBuildings.contains(Building.Road))
        assertTrue(sut.resources.isEmpty())
    }
}