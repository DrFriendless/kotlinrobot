/**
 * Created by john on 26/08/16.
 */
enum class Opcodes { PLACE, MOVE, LEFT, RIGHT, REPORT }

open class Instruction(val opcode: Opcodes) {
    override fun toString(): String {
        return opcode.toString()
    }
}

class Place(val position: Position): Instruction(Opcodes.PLACE) {
    override fun toString(): String {
        return "$opcode ${position.x},${position.y},${position.direction}"
    }
}