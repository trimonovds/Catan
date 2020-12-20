package com.trimonovds.catan.shared

import org.junit.Assert.assertTrue
import org.junit.Test

class GameTest {
    @Test
    fun whenGameSetupRoundOneStartsThenGameWaitsForPlayerToRollDices() {
        val sut = Game(3)
        sut.start()
        assertTrue(sut.gameState.phase is GamePhase.Setup.RoundOne.RollingDices)
    }

    @Test
    fun whenGameIsCreatedWithNumOfPlayersEqualTo2ThenGameHas2PlayersAfterStart() {
        val sut = Game(2)
        sut.start()
        assertTrue(sut.gameState.players.size == 2)
    }

    @Test
    fun whenGameWith2PlayersSetupRoundOneStartsAndCorrectPlayerTapsRollDicesThenGameWaitsForSecondPlayerToRollDices() {
        val sut = Game(2)
        sut.start()
        (sut.gameState.phase as GamePhase.Setup.RoundOne.RollingDices).turn.currentPlayer.color.let {
            sut.onPlayerAction(PlayerAction.RollDicesTap(it))
        }
        assertTrue(sut.gameState.phase is GamePhase.Setup.RoundOne.RollingDices)
    }

    @Test
    fun whenGameWith2PlayersSetupRoundOneStartsAndIncorrectPlayerTapsRollDicesThenGameFails() {
        val sut = Game(2)
        sut.start()
        (sut.gameState.phase as GamePhase.Setup.RoundOne.RollingDices).turn.currentPlayer.color.let { color: PlayerColor ->
            val anotherColor = PlayerColor.values().first { it != color }
            try {
                sut.onPlayerAction(PlayerAction.RollDicesTap(anotherColor))
                assertTrue(false)
            } catch (e: Exception) {
                assertTrue(true)
            }
        }
    }

    @Test
    fun whenGameWith2PlayersSetupRoundOneStartsAndAllPlayersRollDicesThenGameWaitsForBuildPhase() {
        val sut = Game(2)
        sut.start()
        (sut.gameState.phase as GamePhase.Setup.RoundOne.RollingDices).turn.currentPlayer.color.let {
            sut.onPlayerAction(PlayerAction.RollDicesTap(it))
        }
        (sut.gameState.phase as GamePhase.Setup.RoundOne.RollingDices).turn.currentPlayer.color.let {
            sut.onPlayerAction(PlayerAction.RollDicesTap(it))
        }
        assertTrue(sut.gameState.phase is GamePhase.Setup.RoundOne.BuildRoadAndSettlement)
    }
}