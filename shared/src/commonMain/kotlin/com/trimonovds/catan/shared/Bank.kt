package com.trimonovds.catan.shared

data class Bank(
    val resources: List<Resource> = initialBankResources(),
    val developmentCards: List<DevelopmentCard> = initialBankDevelopmentCards()
)

private fun initialBankResources(): List<Resource> {
    val result = mutableListOf<Resource>()
    for (r in Resource.values()) {
        for (i in 0 until 19) {
            result.add(r)
        }
    }
    return result
}

private fun initialBankDevelopmentCards(): List<DevelopmentCard> {
    val result = mutableListOf<DevelopmentCard>()
    for (i in 0 until 14) {
        result.add(DevelopmentCard.Knight)
    }
    for (i in 0 until 2) {
        result.add(DevelopmentCard.BuildRoads)
        result.add(DevelopmentCard.TakeAnyResources)
        result.add(DevelopmentCard.Monopoly)
    }
    for (i in 0 until 5) {
        result.add(DevelopmentCard.VictoryPoint)
    }
    return result
}