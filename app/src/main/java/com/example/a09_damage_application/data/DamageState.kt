package com.example.a09_damage_application.data

enum class SortType {
    TITLE
}
data class DamageState(
    val damages: List<Damage> = emptyList(),
    val sortedBy: SortType = SortType.TITLE
)
