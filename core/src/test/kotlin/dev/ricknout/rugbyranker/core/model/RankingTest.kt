package dev.ricknout.rugbyranker.core.model

import org.junit.Assert.assertEquals
import org.junit.Test

class RankingTest {
    private val worldRugbyRanking =
        Ranking(
            "1",
            "Team 1",
            "T1",
            1,
            2,
            100f,
            90f,
            10,
            Sport.MENS,
        )

    @Test
    fun resetPreviousPoints() {
        assertEquals(worldRugbyRanking.resetPreviousPoints().previousPoints, 100f)
    }

    @Test
    fun addPoints() {
        assertEquals(worldRugbyRanking.addPoints(10f).points, 110f)
        assertEquals(worldRugbyRanking.addPoints(-10f).points, 90f)
    }

    @Test
    fun updatePosition() {
        val updatedPositionWorldRugbyRanking = worldRugbyRanking.updatePosition(3)
        assertEquals(updatedPositionWorldRugbyRanking.position, 3)
        assertEquals(updatedPositionWorldRugbyRanking.previousPosition, 1)
    }

    @Test
    fun pointsDifference() {
        assertEquals(worldRugbyRanking.pointsDifference(), 10f)
    }
}
