package com.trimonovds.catan.shared

data class DicesValues(val first: Int, val second: Int) {
    val sum: Int
        get() = first + second
}

fun rollDices(): DicesValues {
    val first = (1..6).random()
    val second = (1..6).random()
    return DicesValues(first, second)
}