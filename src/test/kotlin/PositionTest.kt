import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.fail

/**
 * Created by john on 26/08/16.
 */
class PositionTest {
    @Test
    fun testPositions() {
        assertEquals(1, Position(1, 2, Direction.NORTH).x)
        assertEquals(2, Position(1, 2, Direction.NORTH).y)
        assertEquals(Direction.NORTH, Position(1, 2, Direction.NORTH).direction)
        assertEquals(0, Position(0, 3, Direction.EAST).x)
        assertEquals(3, Position(0, 3, Direction.EAST).y)
        assertEquals(Direction.EAST, Position(0, 3, Direction.EAST).direction)
    }

    @Test
    fun testInvalidPositions() {
        try {
            Position(-1, 0, Direction.EAST)
            fail()
        } catch (ex: IllegalArgumentException) {
            // success
        }
        try {
            Position(0, -1, Direction.EAST)
            fail()
        } catch (ex: IllegalArgumentException) {
            // success
        }
        try {
            Position(MAXX+1, 0, Direction.EAST)
            fail()
        } catch (ex: IllegalArgumentException) {
            // success
        }
        try {
            Position(0, MAXY+1, Direction.EAST)
            fail()
        } catch (ex: IllegalArgumentException) {
            // success
        }
    }
}