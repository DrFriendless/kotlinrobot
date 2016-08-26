import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
        DirectionsTest::class,
        PositionTest::class,
        TableTest::class,
        ParserTest::class,
        IntegrationTest::class,
        ExampleTests::class)
/**
 * Created by john on 26/08/16.
 */
class AllTestsSuite {
}