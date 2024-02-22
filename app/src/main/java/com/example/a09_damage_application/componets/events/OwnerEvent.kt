package com.example.a09_damage_application.componets.events

import com.example.a09_damage_application.data.entities.Owner

sealed interface OwnerEvent{
    data class SaveOwner(val owner: Owner) : OwnerEvent
    data class DeleteOwner(val ownerId: Int) : OwnerEvent
}
