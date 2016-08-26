import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull

/**
 * Created by john on 26/08/16.
 */
class TableTest {
    fun Table.positionAfter(ops: Table.() -> Unit): String? {
        ops()
        return report()
    }

    @Test
    fun testPlace() {
        assertEquals("0,0,NORTH", Table().positionAfter { place(Position(0,0,Direction.NORTH)) })
        assertEquals("1,2,EAST", Table().positionAfter { place(Position(1,2,Direction.EAST)) })
    }

    @Test
    fun testMove() {
        assertEquals("2,2,EAST", Table().positionAfter { place(Position(1,2,Direction.EAST)); move() })
        assertEquals("1,1,SOUTH", Table().positionAfter { place(Position(1,2,Direction.SOUTH)); move() })
    }

    @Test
    fun testLeft() {
        assertEquals("1,2,NORTH", Table().positionAfter { place(Position(1,2,Direction.EAST)); left() })
    }

    @Test
    fun testRight() {
        assertEquals("1,2,SOUTH", Table().positionAfter { place(Position(1,2,Direction.EAST)); right() })
    }

    @Test
    fun testNoPlace() {
        assertNull(Table().positionAfter { })
        assertNull(Table().positionAfter { left() })
        assertNull(Table().positionAfter { right() })
        assertNull(Table().positionAfter { move() })
        assertNull(Table().positionAfter { report() })
    }

    private fun tableStartingAt(x: Int, y: Int, direction: Direction): Table {
        val t = Table()
        t.place(Position(x,y,direction))
        return t
    }

    @Test
    fun testValidMoves() {
        assertEquals(true, tableStartingAt(0, 0, Direction.NORTH).move())
        assertEquals(true, tableStartingAt(0, 0, Direction.EAST).move())
        assertEquals(true, tableStartingAt(1, 1, Direction.SOUTH).move())
        assertEquals(true, tableStartingAt(1, 1, Direction.WEST).move())
    }

    @Test
    fun testInvalidMoves() {
        assertEquals(false, tableStartingAt(0, 0, Direction.SOUTH).move())
        assertEquals(false, tableStartingAt(0, 0, Direction.WEST).move())
    }
}