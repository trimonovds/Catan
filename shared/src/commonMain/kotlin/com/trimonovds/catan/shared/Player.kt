package com.trimonovds.catan.shared

data class Player(
    val builtBuildings: List<Building> = emptyList(),
    val resources: List<Resource> = emptyList(),
    val developmentCards: List<DevelopmentCard> = emptyList(),
    val pool: List<Building> = initialPlayerPool()
)

fun Player.canPerform(action: Action): Boolean {
    return when (action) {
        is Action.Buy -> this.resources.containsAll(action.developmentCard.price())
        is Action.Build -> this.resources.containsAll(action.building.price())
        is Action.Receive -> true
    }
}

fun Player.perform(action: Action): Player {
    if (!this.canPerform(action)) {
        throw IllegalArgumentException("Player can't perform $action because it has not enough resources")
    }
    return when (action) {
        is Action.Buy -> this.copy(
            developmentCards = developmentCards + action.developmentCard,
            resources = resources - action.developmentCard.price()
        )
        is Action.Build -> this.copy(
            builtBuildings = builtBuildings + action.building,
            resources = resources - action.building.price()
        )
        is Action.Receive -> this.copy(resources = resources + action.resources)
    }
}

fun initialPlayerPool(): List<Building> {
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