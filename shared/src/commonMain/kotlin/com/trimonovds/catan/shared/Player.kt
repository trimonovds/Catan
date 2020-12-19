package com.trimonovds.catan.shared

class Player {
    private val resources = mutableListOf<Resource>()
    private val developmentCards = mutableListOf<DevelopmentCard>()
    private val builtBuildings = mutableListOf<Building>()
    private val pool = initialPlayerPool().toMutableList()
}

private fun initialPlayerPool(): List<Building> {
    val result = mutableListOf<Building>()
    for (i in 0 until 5) {
        result.add(Building.Settlement)
    }
    for (i in 0 until 4) {
        result.add(Building.City)
    }
    for (i in 0 until 15) {
        result.add(Building.Road)
    }
    return result
}