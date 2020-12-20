package com.trimonovds.catan.shared

sealed class PlayerAction {
    data class RollDicesTap(val playerColor: PlayerColor): PlayerAction()
}

class Game(private val numOfPlayers: Int) : GameStoreListener {
    private val listeners = mutableListOf<GameListener>()
    private val store = GameStore()

    val gameState: GameState
        get() = store.state

    init {
        val correctNumberOfPlayers = numOfPlayers in 2..4
        if (!correctNumberOfPlayers) {
            throw IllegalArgumentException("Number of players must be in 2..4 range")
        }
        store.addListener(this)
    }

    fun addListener(listener: GameListener) {
        listeners.add(listener)
    }

    fun removeListener(listener: GameListener) {
        listeners.remove(listener)
    }

    fun start() {
        if (store.state.phase != GamePhase.Initial) {
            throw IllegalStateException("Game is in progress")
        }
        store.dispatch(GameAction.Setup(numOfPlayers))
    }

    fun onPlayerAction(action: PlayerAction) {
        when (action) {
            is PlayerAction.RollDicesTap -> {
                when (val phase = gameState.phase) {
                    is GamePhase.Setup.RoundOne.RollingDices -> {
                        val expectedColor = phase.turn.currentPlayer.color
                        val actualColor = action.playerColor
                        val isCorrectPlayer = expectedColor == actualColor
                        if (!isCorrectPlayer) {
                            throw IllegalStateException("Roll dices request received from incorrect player (expected: $expectedColor, actual: $actualColor)")
                        }
                        val dicesValues = rollDices()
                        store.dispatch(GameAction.RollDices(dicesValues = dicesValues))
                    }
                    else -> {
                        // TODO:
                        return
                    }
                }
            }
            else -> {

            }
        }
    }

    override fun stateDidChange() {
        listeners.forEach { it.onGameStateChanged() }
        when (val phase = store.state.phase) {
            GamePhase.Setup.PreparationsFinished -> {
                store.dispatch(GameAction.StartSetupRoundOne)
            }
            else -> return
        }
    }
}

