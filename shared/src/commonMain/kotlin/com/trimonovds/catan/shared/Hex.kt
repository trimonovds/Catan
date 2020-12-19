package com.trimonovds.catan.shared

sealed class HexType { }
object Desert: HexType()
data class Land(val resource: Resource): HexType()

class Hex() {
}