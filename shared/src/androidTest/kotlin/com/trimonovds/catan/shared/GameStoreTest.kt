package com.trimonovds.catan.shared

import org.junit.Assert.assertEquals
import org.junit.Test

class GameStoreTest {
    @Test
    fun whenRoundOneRollDicesFinishedThenPlayersAreSortedByRollDicesResult() {
        val players = listOf<Player>(
            Player(color = PlayerColor.Red),
            Player(color = PlayerColor.Green),
            Player(color = PlayerColor.White)
        )
        val sut =
            GameStore(GameState(phase = GamePhase.Setup.RoundOne.RollingDices(turn = Turn(players)), players = players))
        assertEquals(PlayerColor.Red, (sut.state.phase as GamePhase.Setup.RoundOne.RollingDices).turn.currentPlayer.color)
        sut.dispatch(GameAction.RollDices(DicesValues(1, 1))) // Red
        assertEquals(PlayerColor.Green, (sut.state.phase as GamePhase.Setup.RoundOne.RollingDices).turn.currentPlayer.color)
        sut.dispatch(GameAction.RollDices(DicesValues(5, 3))) // Green
        assertEquals(PlayerColor.White, (sut.state.phase as GamePhase.Setup.RoundOne.RollingDices).turn.currentPlayer.color)
        sut.dispatch(GameAction.RollDices(DicesValues(3, 3))) // White

        assertEquals(PlayerColor.Green, sut.state.players.first().color)
    }
}