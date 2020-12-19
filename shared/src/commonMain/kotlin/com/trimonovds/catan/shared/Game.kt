package com.trimonovds.catan.shared

class Game(private val numOfPlayers: Int) {
    init {
        require(numOfPlayers in 2..4)
    }

    private val bank = Bank()
    private val players = createInitialPlayers(numOfPlayers)
    private var currentPlayerIndex: Int = 0
}

private fun createInitialPlayers(numOfPlayers: Int): List<Player> {
    return (0 until numOfPlayers).map { Player() }
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