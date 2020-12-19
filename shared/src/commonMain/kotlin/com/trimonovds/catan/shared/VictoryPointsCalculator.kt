package com.trimonovds.catan.shared

object VictoryPointsCalculator {
    fun victoryPoints(buildings: List<Building>, devCards: List<DevelopmentCard>, specialCards: List<SpecialCard>): Int {
        val buildingVictoryPoints = buildings.map { it.victoryPoints() }
        val devCardVictoryPoints = devCards.map { it.victoryPoints() }
        val specialCardVictoryPoints = specialCards.map { it.victoryPoints() }
        return (buildingVictoryPoints + devCardVictoryPoints + specialCardVictoryPoints).sum()
    }

    private fun Building.victoryPoints(): Int {
        return when (this) {
            Building.Road -> 0
            Building.Settlement -> 1
            Building.City -> 2
        }
    }

    private fun DevelopmentCard.victoryPoints(): Int {
        return when (this) {
            DevelopmentCard.VictoryPoint -> 1
            else -> 0
        }
    }

    private fun SpecialCard.victoryPoints(): Int {
        return 2
    }
}