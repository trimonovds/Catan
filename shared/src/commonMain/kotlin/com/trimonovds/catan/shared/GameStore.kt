package com.trimonovds.catan.shared

class GameStore(initialState: GameState = GameState()) {
    private val listeners = mutableListOf<GameStoreListener>()

    var state: GameState = initialState
        private set

    fun addListener(listener: GameStoreListener) {
        listeners.add(listener)
    }

    fun removeListener(listener: GameStoreListener) {
        listeners.remove(listener)
    }

    fun dispatch(action: GameAction) {
        state = reduce(state, action)
        listeners.forEach { it.stateDidChange() }
    }
}

private fun reduce(state: GameState, action: GameAction): GameState {
    return when (action) {
        is GameAction.Setup -> {
            onSetup(action, state)
        }
        GameAction.StartSetupRoundOne -> {
            onStartSetupRoundOne(state)
        }
        is GameAction.RollDices -> {
            onRollDices(action, state)
        }
    }
}

private fun onRollDices(action: GameAction.RollDices, state: GameState): GameState {
    when (val phase = state.phase) {
        is GamePhase.Setup.RoundOne.RollingDices -> {
            val nextTurn = phase.turn.next()
            val newResults = phase.results.toMutableMap()
            newResults[phase.turn.currentPlayer.color] = action.dicesValues
            if (nextTurn != null) {
                return state.copy(
                    phase = GamePhase.Setup.RoundOne.RollingDices(
                        turn = nextTurn,
                        results = newResults
                    )
                )
            } else {
                val playersSortedByRollDicesResult = newResults
                    .map { PlayerRollDices(it.key, it.value) }
                    .sortedBy { it.rollDicesResult.sum }
                val sortedPlayers = mutableListOf<Player>()
                for (x in playersSortedByRollDicesResult.reversed()) {
                    sortedPlayers.add(state.players.first { it.color == x.playerColor })
                }
                return state.copy(
                    phase = GamePhase.Setup.RoundOne.BuildRoadAndSettlement(
                        turn = Turn(sortedPlayers)
                    ),
                    players = sortedPlayers
                )
            }
        }
        else -> {
            return state // TODO:
        }
    }
}

private fun onStartSetupRoundOne(state: GameState): GameState {
    return state.copy(phase = GamePhase.Setup.RoundOne.RollingDices(turn = Turn(state.players)))
}

private fun onSetup(
    action: GameAction.Setup,
    state: GameState
): GameState {
    val initialPlayers = createInitialPlayers(action.numOfPlayers)
    val availableColors = availablePlayerColors()
    val coloredPlayers =
        initialPlayers.withIndex().map { it.value.copy(color = availableColors[it.index]) }
    val players = coloredPlayers.map { it.copy(pool = initialPlayerPool()) }
    val shuffledDevCardsBank =
        Bank().copy(developmentCards = state.bank.developmentCards.shuffled())
    return state.copy(
        phase = GamePhase.Setup.PreparationsFinished,
        players = players,
        bank = shuffledDevCardsBank
    )
}

private data class PlayerRollDices(val playerColor: PlayerColor, val rollDicesResult: DicesValues)

private fun createInitialPlayers(numOfPlayers: Int): List<Player> {
    return (0 until numOfPlayers).map { Player() }
}

private fun availablePlayerColors(): List<PlayerColor> {
    return PlayerColor.values().filter { it != PlayerColor.None }
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