/**
 * Created by john on 26/08/16.
 */
enum class Direction(val xDelta: Int, val yDelta: Int) {
    NORTH(0, 1),
    EAST(1, 0),
    SOUTH(0, -1),
    WEST(-1, 0);

    val clockwise: Direction
        get() = when (this) { NORTH -> EAST; EAST -> SOUTH; SOUTH -> WEST; WEST -> NORTH }

    val anticlockwise: Direction
        get() = when (this) { NORTH -> WEST; EAST -> NORTH; SOUTH -> EAST; WEST -> SOUTH }
}

data class Position(val x: Int, val y: Int, val direction: Direction) {}