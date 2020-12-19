package com.trimonovds.catan.shared

class Player {
    private val _mutableResources = mutableListOf<Resource>()
    private val _developmentCards = mutableListOf<DevelopmentCard>()
    private val _builtBuildings = mutableListOf<Building>()
    private val _pool = initialPlayerPool().toMutableList()

    val resources: List<Resource>
        get() = _mutableResources

    val developmentCards: List<DevelopmentCard>
        get() = _developmentCards

    val builtBuildings: List<Building>
        get() = _builtBuildings

    val pool: List<Building>
        get() = _pool
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