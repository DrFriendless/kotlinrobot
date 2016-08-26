import org.junit.Assert.*
import org.junit.Test
import java.io.File

/**
 * Tests that run the examples stored as programs in files.
 *
 * Created by john on 26/08/16.
 */
class ExampleTests {
    private fun testExample(filename: String): List<String> {
        val text = File("programs", filename).readText()
        return Runner().run(text, true)
    }

    @Test
    fun exampleA() {
        assertEquals(listOf("0,1,NORTH"), testExample("example_a.txt"))
    }

    @Test
    fun exampleB() {
        assertEquals(listOf("0,0,WEST"), testExample("example_b.txt"))
    }

    @Test
    fun exampleC() {
        assertEquals(listOf("3,3,NORTH"), testExample("example_c.txt"))
    }

    @Test
    fun grandTour() {
        assertEquals(listOf("2,2,NORTH"), testExample("grand_tour.txt"))
    }

    @Test
    fun gibberish() {
        assertEquals(listOf("3,4,WEST"), testExample("gibberish.txt"))
    }

    @Test
    fun charge() {
        assertEquals(listOf("4,1,EAST", "4,1,EAST"), testExample("charge.txt"))
    }

}