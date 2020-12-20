package com.trimonovds.catan.shared

sealed class GameAction {
    data class Setup(val numOfPlayers: Int): GameAction()
    object StartSetupRoundOne: GameAction()
    data class RollDices(val dicesValues: DicesValues): GameAction() // Current turn player did tap roll dices
}
