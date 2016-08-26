import java.io.File

/**
 * Created by john on 26/08/16.
 */
class Runner {
    fun run(program: String, verbose: Boolean): List<String> {
        return run(parseInstructions(program), verbose)
    }

    fun run(program: List<Instruction>, verbose: Boolean): List<String> {
        val table = Table()
        val result = mutableListOf<String>()
        program.forEach { instruction ->
            val output = execute(instruction, table, verbose).output
            if (output != null) result.add(output)
        }
        return result
    }

    fun execute(instruction: Instruction, table: Table, verbose: Boolean): ExecutionResult {
        val result = execute(instruction, table)
        if (verbose) println(result)
        return result
    }

    fun execute(instruction: Instruction, table: Table): ExecutionResult {
        return when (instruction.opcode) {
            Opcodes.LEFT -> {
                val result = table.left()
                ExecutionResult(if (result) ExecutionResultType.EXECUTED else ExecutionResultType.IGNORED, instruction, null)
            }
            Opcodes.RIGHT -> {
                val result = table.right()
                ExecutionResult(if (result) ExecutionResultType.EXECUTED else ExecutionResultType.IGNORED, instruction, null)
            }
            Opcodes.MOVE -> {
                val result = table.move()
                ExecutionResult(if (result) ExecutionResultType.EXECUTED else ExecutionResultType.IGNORED, instruction, null)
            }
            Opcodes.REPORT -> {
                val result = table.report()
                ExecutionResult(if (result != null) ExecutionResultType.EXECUTED else ExecutionResultType.IGNORED, instruction, result)
            }
            Opcodes.PLACE -> {
                val result = table.place((instruction as Place).position)
                ExecutionResult(if (result) ExecutionResultType.EXECUTED else ExecutionResultType.IGNORED, instruction, null)
            }
        }
    }
}

enum class ExecutionResultType { IGNORED, EXECUTED, OUTPUT }

class ExecutionResult(val type: ExecutionResultType, val instruction: Instruction, val output: String?) {

    override fun toString(): String {
        return when (type) {
            ExecutionResultType.IGNORED -> "IGNORE  $instruction"
            ExecutionResultType.EXECUTED -> "EXECUTE $instruction"
            ExecutionResultType.OUTPUT -> "EXECUTE $instruction\nOUTPUT  $output"
        }
    }
}

fun main(args: Array<String>) {
    args.forEach {
        val text = File(it).readText()
        Runner().run(text, true)
    }
}