package com.example.a09_damage_application.componets.events

import com.example.a09_damage_application.data.entities.Localization

sealed interface LocalizationEvent{
    data class SaveLocalization(val localization: Localization)  : LocalizationEvent

    data class DeleteLocalization(val localization: Localization)  : LocalizationEvent
}