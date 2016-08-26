import org.junit.Assert.*
import org.junit.Test

/**
 * Created by john on 26/08/16.
 */
class IntegrationTest {
    private fun test(program: String, expected: List<String>) {
        val result = Runner().run(program, false)
        assertEquals(expected, result)
    }

    @Test
    fun simpleTest() {
        test("PLACE 0,0,NORTH\n REPORT", listOf("0,0,NORTH"))
    }

    @Test
    fun testMove() {
        test("PLACE 0,0,NORTH\nREPORT\nMOVE\nREPORT", listOf("0,0,NORTH", "0,1,NORTH"))
    }

    @Test
    fun testRight() {
        test("PLACE 0,0,NORTH\nRIGHT\nREPORT", listOf("0,0,EAST"))
    }

    @Test
    fun testLeft() {
        test("PLACE 0,0,NORTH\nLEFT\nREPORT", listOf("0,0,WEST"))
    }

    @Test
    fun testMultiplePlaces() {
        test("PLACE 0,0,NORTH\nPLACE 1,1,WEST\nREPORT", listOf("1,1,WEST"))
    }

    @Test
    fun testNoPlace() {
        test("REPORT", listOf())
    }

    @Test
    fun testInstructionsIgnoredBeforePlace() {
        test("LEFT\nPLACE 0,0,NORTH\nREPORT", listOf("0,0,NORTH"))
        test("RIGHT\nPLACE 0,0,NORTH\nREPORT", listOf("0,0,NORTH"))
        test("MOVE\nPLACE 0,0,NORTH\nREPORT", listOf("0,0,NORTH"))
        test("REPORT\nPLACE 0,0,NORTH\nREPORT", listOf("0,0,NORTH"))
    }

    @Test
    fun testParseErrors() {
        val BAD = listOf("NONSENSE", "LEFT\n ", "PLACE 10,10,EAST")
        BAD.forEach {
            try {
                test(it, listOf())
                fail(it)
            } catch (ex: ParseError) {
                // success
            }
        }
    }
}