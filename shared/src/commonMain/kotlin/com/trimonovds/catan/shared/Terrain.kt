package com.trimonovds.catan.shared

enum class Terrain {
    Hills,
    Forest,
    Mountains,
    Fields,
    Pasture,
    Desert
}

fun Terrain.resource(): Resource? = when(this) {
    Terrain.Hills -> Resource.Brick
    Terrain.Forest -> Resource.Lumber
    Terrain.Mountains -> Resource.Ore
    Terrain.Fields -> Resource.Grain
    Terrain.Pasture -> Resource.Wool
    Terrain.Desert -> null
}