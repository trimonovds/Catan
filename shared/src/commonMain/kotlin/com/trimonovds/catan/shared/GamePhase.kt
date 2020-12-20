package com.trimonovds.catan.shared

sealed class GamePhase {
    object Initial : GamePhase()

    sealed class Setup : GamePhase() {
        object PreparationsFinished : Setup()
        sealed class RoundOne : Setup() {
            data class RollingDices(
                val results: Map<PlayerColor, DicesValues> = emptyMap(),
                val turn: Turn
            ) : RoundOne()

            data class BuildRoadAndSettlement(val turn: Turn) : RoundOne()
        }

        object RoundTwo : Setup()
    }

    sealed class Play : GamePhase() {
        object RollDices : Play()
        object Trade : Play()
        object Build : Play()
    }
}

data class Turn(private val players: List<Player>, private val index: Int = 0) {
    init {
        require(players.isNotEmpty())
    }

    val currentPlayer: Player
        get() = players[index]

    internal fun next(): Turn? {
        val nextIndex = index + 1
        return if (nextIndex < players.size) {
            Turn(players, nextIndex)
        } else {
            null
        }
    }
}