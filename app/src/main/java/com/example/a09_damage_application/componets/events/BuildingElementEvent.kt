package com.example.a09_damage_application.componets.events

import com.example.a09_damage_application.data.entities.BuildingElement

sealed interface BuildingElementEvent{
    data class SaveBuildingElement(val localization: BuildingElement)  : BuildingElementEvent

    data class DeleteBuildingElement(val localization: BuildingElement)  : BuildingElementEvent

}