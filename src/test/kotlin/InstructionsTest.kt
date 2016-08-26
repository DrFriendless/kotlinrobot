import org.junit.Test
import org.junit.Assert.assertEquals

/**
 * Created by john on 26/08/16.
 */
class InstructionsTest {
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
}