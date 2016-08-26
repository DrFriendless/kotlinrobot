import org.junit.Test
import org.junit.Assert.assertEquals

/**
 * Created by john on 26/08/16.
 */
class DirectionsTest {
    @Test
    fun testClockwise() {
        assertEquals(Direction.SOUTH, Direction.EAST.clockwise)
        assertEquals(Direction.EAST, Direction.NORTH.clockwise)
        assertEquals(Direction.NORTH, Direction.WEST.clockwise)
        assertEquals(Direction.WEST, Direction.SOUTH.clockwise)
    }

    @Test
    fun testAnticlockwise() {
        assertEquals(Direction.SOUTH, Direction.WEST.anticlockwise)
        assertEquals(Direction.EAST, Direction.SOUTH.anticlockwise)
        assertEquals(Direction.NORTH, Direction.EAST.anticlockwise)
        assertEquals(Direction.WEST, Direction.NORTH.anticlockwise)
    }

    private fun moveSequence(sequence: List<Direction>): Pair<Int, Int> {
        return sequence.map { Pair(it.xDelta, it.yDelta) }.fold(Pair(0,0),
                fun(l: Pair<Int, Int>, r:Pair<Int, Int>) = Pair(l.first+r.first, l.second+r.second))
    }

    @Test
    fun testDirectionDeltas() {
        assertEquals(Pair(0,0), moveSequence(listOf()))
        assertEquals(Pair(0,0), moveSequence(listOf(Direction.NORTH, Direction.SOUTH)))
        assertEquals(Pair(0,0), moveSequence(listOf(Direction.EAST, Direction.WEST)))
        assertEquals(Pair(1,1), moveSequence(listOf(Direction.NORTH, Direction.EAST)))
        assertEquals(Pair(0,1), moveSequence(listOf(Direction.NORTH)))
        assertEquals(Pair(-1,0), moveSequence(listOf(Direction.WEST)))
        assertEquals(Pair(0,0), moveSequence(listOf(Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST)))
    }
}