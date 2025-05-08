package com.purity.eatwise.model

data class plan(
val id: Long = 0,  // You can include an id if needed
val name: String,
val calories: Int,
val description: String)
