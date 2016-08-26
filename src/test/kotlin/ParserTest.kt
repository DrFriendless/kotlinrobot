import org.junit.Test
import org.junit.Assert.assertTrue
import org.junit.Assert.assertFalse
import org.junit.Assert.assertEquals

/**
 * Created by john on 26/08/16.
 */
class ParserTest {
    @Test
    fun testPlacePattern() {
        assertTrue(PLACE_PATTERN.matches("PLACE 0,0,NORTH"))
        assertTrue(PLACE_PATTERN.matches("PLACE 0,0,EAST"))
        assertTrue(PLACE_PATTERN.matches("PLACE 0,0,SOUTH"))
        assertTrue(PLACE_PATTERN.matches("PLACE 0,0,WEST"))
        assertTrue(PLACE_PATTERN.matches("PLACE 1,2,WEST"))
        assertTrue(PLACE_PATTERN.matches("PLACE 3,4,WEST"))
        assertTrue(PLACE_PATTERN.matches("PLACE 5,6,WEST"))
        assertTrue(PLACE_PATTERN.matches("PLACE 7,8,WEST"))
        assertTrue(PLACE_PATTERN.matches("PLACE 0 , 0 , NORTH"))
        assertFalse(PLACE_PATTERN.matches("PLACE -7,8,WEST"))
        assertFalse(PLACE_PATTERN.matches("PLACE 7,8,FROG"))
        assertFalse(PLACE_PATTERN.matches("PLACE 9,10,WEST"))
        assertFalse(PLACE_PATTERN.matches("PLACE0,0,NORTH"))
        assertFalse(PLACE_PATTERN.matches("PLACE -7,8,WEST"))
    }

    @Test
    fun testCaptures() {
        assertEquals("0", PLACE_PATTERN.matchEntire("PLACE 0,1,NORTH")?.groups?.get(1)?.value)
        assertEquals("1", PLACE_PATTERN.matchEntire("PLACE 0,1,NORTH")?.groups?.get(2)?.value)
        assertEquals("NORTH", PLACE_PATTERN.matchEntire("PLACE 0,1,NORTH")?.groups?.get(3)?.value)
        assertEquals("EAST", PLACE_PATTERN.matchEntire("PLACE 0,1,EAST")?.groups?.get(3)?.value)
        assertEquals("SOUTH", PLACE_PATTERN.matchEntire("PLACE 0,1,SOUTH")?.groups?.get(3)?.value)
        assertEquals("WEST", PLACE_PATTERN.matchEntire("PLACE 0,1,WEST")?.groups?.get(3)?.value)
    }
}
