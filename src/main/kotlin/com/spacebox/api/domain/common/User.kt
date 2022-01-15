package com.spacebox.api.domain.common

data class User(val id: Long, val name: String, val favorites: MutableSet<Entry>) {
    fun addFavorite(entry: Entry) = this.favorites.add(entry)
}
