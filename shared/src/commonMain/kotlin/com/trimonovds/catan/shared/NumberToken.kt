package com.trimonovds.catan.shared

enum class NumberToken {
    Two, Three, Four, Five, Six, Eight, Nine, Ten, Eleven, Twelve
}

fun gameNumberTokens(): List<NumberToken> {
    return listOf(
        NumberToken.Ten,
        NumberToken.Two,
        NumberToken.Nine,
        NumberToken.Twelve,
        NumberToken.Six,
        NumberToken.Four,
        NumberToken.Ten,
        NumberToken.Nine,
        NumberToken.Eleven,
        NumberToken.Three,
        NumberToken.Eight,
        NumberToken.Eight,
        NumberToken.Three,
        NumberToken.Four,
        NumberToken.Five,
        NumberToken.Five,
        NumberToken.Six,
        NumberToken.Eleven
    )
}