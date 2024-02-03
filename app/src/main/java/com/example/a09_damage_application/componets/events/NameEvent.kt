package com.example.a09_damage_application.componets.events

import com.example.a09_damage_application.data.entities.Name


sealed interface NameEvent{
    data class SaveName(val name: Name) : NameEvent
    data class DeleteName(val name: Name) : NameEvent
}