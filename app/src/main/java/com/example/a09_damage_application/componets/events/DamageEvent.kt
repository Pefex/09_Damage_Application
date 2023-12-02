package com.example.a09_damage_application.componets.events

import com.example.a09_damage_application.data.entities.Damage

sealed interface DamageEvent{
    data class SaveDamage(val damage: Damage) : DamageEvent
    data class DeleteDamage(val damage: Damage) : DamageEvent
}
