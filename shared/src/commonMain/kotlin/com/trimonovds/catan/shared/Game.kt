package com.trimonovds.catan.shared

class Game(private val numOfPlayers: Int) {
    init {
        require(numOfPlayers in 2..4)
    }

    private val bank = Bank()
    private val players = createPlayers(numOfPlayers)
    private var currentPlayerIndex: Int = 0
}

private fun createPlayers(numOfPlayers: Int): List<Player> {
    return (0 until numOfPlayers).map { Player() }
}
