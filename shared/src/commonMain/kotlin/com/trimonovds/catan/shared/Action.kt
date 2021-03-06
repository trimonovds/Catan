package com.trimonovds.catan.shared

sealed class Action {
    data class Build(val building: Building) : Action()
    data class Buy(val developmentCard: DevelopmentCard) : Action()
    data class Receive(val resources: List<Resource>): Action()
}