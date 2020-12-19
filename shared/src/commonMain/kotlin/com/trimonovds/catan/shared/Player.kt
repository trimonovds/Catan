package com.trimonovds.catan.shared

data class Player(
    val builtBuildings: List<Building> = emptyList(),
    val resources: List<Resource> = emptyList(),
    val developmentCards: List<DevelopmentCard> = emptyList(),
    val pool: List<Building> = initialPlayerPool()
)

fun Player.canPerform(action: Action): Boolean {
   return when(action) {
       is Action.Buy -> this.canBuy(action.developmentCard)
       is Action.Build -> this.canBuild(action.building)
   }
}

fun Player.perform(action: Action): Player {
    return when(action) {
        is Action.Buy -> this.buy(action.developmentCard)
        is Action.Build -> this.build(action.building)
    }
}

fun Player.receive(resources: List<Resource>): Player {
    return this.copy(resources = this.resources + resources)
}

private fun Player.canBuild(building: Building): Boolean {
    return this.resources.containsAll(building.price())
}

private fun Player.build(building: Building): Player {
    if (!this.canBuild(building)) {
        throw IllegalArgumentException("Player can't build $building because it has not enough resources")
    }
    return this.copy(builtBuildings = builtBuildings + building, resources = resources - building.price())
}

private fun Player.canBuy(developmentCard: DevelopmentCard): Boolean {
    return this.resources.containsAll(developmentCard.price())
}

private fun Player.buy(developmentCard: DevelopmentCard): Player {
    if (!this.canBuy(developmentCard)) {
        throw IllegalArgumentException("Player can't buy $developmentCard because it has not enough resources")
    }
    return this.copy(developmentCards = developmentCards + developmentCard, resources = resources - developmentCard.price())
}