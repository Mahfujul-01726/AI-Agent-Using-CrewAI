package com.example.helpconnect.model

data class Ngo(
    val id: String,
    val name: String,
    val category: String,
    val description: String,
    val distance: String,
    val rating: Double,
    val imageUrl: String, // Placeholder for resource ID or abstract key
    val goalAmount: Double,
    val raisedAmount: Double,
    val daysLeft: Int,
    val lat: Double,
    val lng: Double
)

data class Donation(
    val id: String,
    val ngoName: String,
    val amount: Double,
    val date: String,
    val status: String
)

data class User(
    val id: String,
    val name: String,
    val email: String,
    val profileImage: String
)
