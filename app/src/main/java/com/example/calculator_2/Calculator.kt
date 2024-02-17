package com.example.calculator_2

class Calculator {
    private val stack = mutableListOf<Double>()
    private val op = mutableListOf<Char>()

    private fun performOperation() {
        val operator = op.removeAt(op.size - 1)
        when (operator) {
            '*' -> {
                val operand2 = stack.removeAt(stack.size - 1)
                val operand1 = stack.removeAt(stack.size - 1)
                stack.add(operand1 * operand2)
            }
            '/' -> {
                val operand2 = stack.removeAt(stack.size - 1)
                val operand1 = stack.removeAt(stack.size - 1)
                if (operand2 == 0.0){
                    return
                }
                stack.add(operand1 / operand2)
            }
            '+' -> {
                val operand2 = stack.removeAt(stack.size - 1)
                val operand1 = stack.removeAt(stack.size - 1)
                stack.add(operand1 + operand2)
            }
            '-' -> {
                val operand2 = stack.removeAt(stack.size - 1)
                val operand1 = stack.removeAt(stack.size - 1)
                stack.add(operand1 - operand2)
            }
        }
    }

    fun calculate(expression: String): Double {
        var i = 0
        while (i < expression.length) {
            val token = expression[i]

            when {
                token.isDigit() || token == '.' -> {
                    val sbuf = StringBuilder()
                    while (i < expression.length && (expression[i].isDigit() || expression[i] == '.')) {
                        sbuf.append(expression[i++])
                    }
                    stack.add(sbuf.toString().toDouble())
                    i--
                }

                token == ')' -> {
                    while (op.isNotEmpty() && op.last() != '(') {
                        performOperation()
                    }
                    if (op.isNotEmpty() && op.last() == '(') {
                        op.removeAt(op.size - 1)
                    }
                }
                token == '×' -> {
                    while (op.isNotEmpty() && (op.last() == '*' || op.last() == '/')) {
                        performOperation()
                    }
                    op.add('*')
                }
                token == '÷' -> {
                    while (op.isNotEmpty() && (op.last() == '*' || op.last() == '/')) {
                        performOperation()
                    }
                    op.add('/')
                }
                token in "+-" -> {
                    while (op.isNotEmpty() && (op.last() == '*' || op.last() == '/' || op.last() == '+' || op.last() == '-')) {
                        performOperation()
                    }
                    op.add(token)
                }
                token == '(' -> {
                    op.add(token)
                }
            }

            i++
        }

        while (op.isNotEmpty()) {
            performOperation()
        }
        return stack.first()
    }
}
