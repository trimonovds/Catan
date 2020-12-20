package com.trimonovds.catan.shared

enum class PlayerColor {
    Red,
    Green,
    White,
    Blue,
    None
}

data class Player(
    val color: PlayerColor = PlayerColor.None,
    val builtBuildings: List<Building> = emptyList(),
    val resources: List<Resource> = emptyList(),
    val developmentCards: List<DevelopmentCard> = emptyList(),
    val pool: List<Building> = emptyList()
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
        throw IllegalArgumentException("Player can't perform $action")
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