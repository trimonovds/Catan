package com.trimonovds.catan.shared

data class GameState(
    var phase: GamePhase = GamePhase.Initial,
    val bank: Bank = Bank(),
    val players: List<Player> = emptyList(),
)