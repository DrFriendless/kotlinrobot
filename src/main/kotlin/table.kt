/**
 * Created by john on 26/08/16.
 */
val MAXX = 4
val MAXY = 4

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

fun inXBounds(x: Int): Boolean {
    return x >= 0 && x <= MAXX
}

fun inYBounds(y: Int): Boolean {
    return y >= 0 && y <= MAXY
}

data class Position(val x: Int, val y: Int, val direction: Direction) {
    init {
        if (!inXBounds(x)) throw IllegalArgumentException("X outside of bounds")
        if (!inYBounds(y)) throw IllegalArgumentException("Y outside of bounds")
    }

    override fun toString(): String {
        return "$x,$y,$direction"
    }
}

class Table {
    private var position: Position? = null

    fun place(p: Position): Boolean {
        position = p
        return true
    }

    fun left(): Boolean {
        val p: Position = position ?: return false
        position = Position(p.x, p.y, p.direction.anticlockwise)
        return true
    }

    fun right(): Boolean {
        val p: Position = position ?: return false
        position = Position(p.x, p.y, p.direction.clockwise)
        return true
    }

    fun move(): Boolean {
        val p: Position = position ?: return false
        (p.x + p.direction.xDelta).let { newx ->
            if (!inXBounds(newx)) return false
            (p.y + p.direction.yDelta).let { newy ->
                if (!inYBounds(newy)) return false
                position = Position(newx, newy, p.direction)
                return true
            }
        }
    }

    fun report(): String? {
        return position?.toString() ?: null
    }
}