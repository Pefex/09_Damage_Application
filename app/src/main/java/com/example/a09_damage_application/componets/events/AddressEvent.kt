package com.example.a09_damage_application.componets.events

import com.example.a09_damage_application.data.entities.Address
import com.example.a09_damage_application.data.entities.Damage

sealed interface AddressEvent{
    data class SaveAddress(val address: Address) : AddressEvent
    data class DeleteAddress(val address: Address) : AddressEvent
}
