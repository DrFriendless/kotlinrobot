/**
 * Created by john on 26/08/16.
 */
val PLACE_PATTERN = Regex("^PLACE\\s+(\\d)\\s*,\\s*(\\d)\\s*,\\s*((?:NORTH)|(?:EAST)|(?:SOUTH)|(?:WEST))$")

private val SINGLETON_INSTRUCTIONS =
    listOf(Opcodes.LEFT, Opcodes.RIGHT, Opcodes.REPORT, Opcodes.MOVE).associate { Pair(it, Instruction(it)) }

fun parseInstruction(original: String, lineNum: Int): Instruction {
    val s = original.trim().toUpperCase()
    val match = lazy { PLACE_PATTERN.matchEntire(s) }
    val opcode = when {
        s == "LEFT" -> Opcodes.LEFT
        s == "RIGHT" -> Opcodes.RIGHT
        s == "MOVE" -> Opcodes.MOVE
        s == "REPORT" -> Opcodes.REPORT
        match.value != null -> Opcodes.PLACE
        else -> throw ParseError("Line $lineNum: syntax error parsing '$original'")
    }
    if (opcode == Opcodes.PLACE) {
        val m = match.value!!
        val position = Position(m.groupValues[1].toInt(), m.groupValues[2].toInt(), Direction.valueOf(m.groupValues[3]))
        return Place(position)
    } else {
        return SINGLETON_INSTRUCTIONS[opcode] ?: throw ParseError("Internal error unknown opcode")
    }
}

fun parseInstructions(source: String): List<Instruction> {
    val lines = source.split('\n')
    return lines.mapIndexed { lineNum, line -> parseInstruction(line, lineNum+1) }
}

class ParseError(mesg: String): RuntimeException(mesg) {}