import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test

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

    @Test
    fun testParseInstruction() {
        assertEquals(Opcodes.LEFT, parseInstruction("LEFT", -1).opcode)
        assertEquals(Opcodes.RIGHT, parseInstruction("RIGHT", -1).opcode)
        assertEquals(Opcodes.RIGHT, parseInstruction("right", -1).opcode)
        assertEquals(Opcodes.RIGHT, parseInstruction(" right ", -1).opcode)
        assertEquals(Opcodes.MOVE, parseInstruction("MOVE", -1).opcode)
        assertEquals(Opcodes.REPORT, parseInstruction("REPORT", -1).opcode)
    }

    @Test
    fun testParsePlaceInstruction() {
        assertEquals(Opcodes.PLACE, parseInstruction("PLACE 0,0,NORTH", -1).opcode)
        assertEquals(Opcodes.PLACE, parseInstruction(" PLACE 0 , 0,  NORTH ", -1).opcode)
        assertEquals(Opcodes.PLACE, parseInstruction("PLACE 3,4,WEST", -1).opcode)
        assertEquals(Opcodes.PLACE, parseInstruction("place 3,4,west", -1).opcode)
    }

    @Test
    fun testParseErrors() {
        val PARSE_ERRORS = listOf("", "LEFF", "PLACE0,0,NORTH", "LEFT 0,0,NORTH", "PLACE 0;0,NORTH")
        PARSE_ERRORS.forEach {
            try {
                parseInstruction(it, -1)
                Assert.fail(it)
            } catch (ex: ParseError) {
                // success
            }
        }
    }

    @Test
    fun testParseInvalidPositions() {
        val PARSE_ERRORS = listOf("PLACE 9,9,NORTH", "PLACE 3,6,west")
        PARSE_ERRORS.forEach {
            try {
                parseInstruction(it, -1)
                Assert.fail(it)
            } catch (ex: IllegalArgumentException) {
                // success
            }
        }
    }

    @Test
    fun testPositionsInPlaceInstructions() {
        assertTrue(parseInstruction("place 3,4,west", -1) is Place)
        assertTrue((parseInstruction("place 3,4,west", -1) as Place).position.x == 3)
        assertTrue((parseInstruction("place 3,4,west", -1) as Place).position.y == 4)
        assertTrue((parseInstruction("place 3,4,west", -1) as Place).position.direction == Direction.WEST)
        assertTrue(parseInstruction(" PLACE 0 , 2 , NORTH ", -1) is Place)
        assertTrue((parseInstruction(" PLACE 0 , 2 , NORTH ", -1) as Place).position.x == 0)
        assertTrue((parseInstruction(" PLACE 0 , 2 , NORTH ", -1) as Place).position.y == 2)
        assertTrue((parseInstruction(" PLACE 0 , 2 , NORTH ", -1) as Place).position.direction == Direction.NORTH)
    }

    @Test
    fun testParseMultipleLines() {
        assertEquals(2, parseInstructions("LEFT\nRIGHT").size)
        assertEquals(1, parseInstructions("MOVE").size)
        assertEquals(2, parseInstructions("place 0,0,north\n move").size)
        assertEquals(listOf(Opcodes.LEFT, Opcodes.RIGHT, Opcodes.MOVE, Opcodes.REPORT, Opcodes.PLACE),
                parseInstructions("left\nright\nmove\nreport\nplace 0,0,east").map { it.opcode })
    }
}
