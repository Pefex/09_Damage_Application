package com.example.a09_damage_application.componets.events

import com.example.a09_damage_application.data.entities.Contact

sealed interface ContactEvent{
    data class SaveContact(val contact: Contact)  : ContactEvent

    data class DeleteContact(val contact: Contact)  : ContactEvent
}