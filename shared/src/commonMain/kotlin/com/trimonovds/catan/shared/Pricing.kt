package com.trimonovds.catan.shared

fun DevelopmentCard.price(): List<Resource> = listOf(Resource.Ore, Resource.Wool, Resource.Grain)

fun Building.price(): List<Resource> = when (this) {
    Building.Road -> listOf<Resource>(Resource.Brick, Resource.Lumber)
    Building.City -> listOf<Resource>(
        Resource.Brick,
        Resource.Lumber,
        Resource.Wool,
        Resource.Lumber
    )
    Building.Settlement -> listOf<Resource>(
        Resource.Ore,
        Resource.Ore,
        Resource.Ore,
        Resource.Grain,
        Resource.Grain
    )
}