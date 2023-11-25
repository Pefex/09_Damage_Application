package com.example.a09_damage_application.data

sealed interface DamageEvent {
    data class SaveDamage(val damage: Damage): DamageEvent
    data class DeleteDamage(val damage: Damage): DamageEvent
}