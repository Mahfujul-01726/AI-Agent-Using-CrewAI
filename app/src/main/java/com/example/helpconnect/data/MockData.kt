package com.example.helpconnect.data

import com.example.helpconnect.model.Donation
import com.example.helpconnect.model.Ngo
import com.example.helpconnect.model.User

object MockData {
    val ngos = listOf(
        Ngo(
            id = "1",
            name = "Paws & Whiskers Sanctuary",
            category = "Animal Welfare",
            description = "A safe haven for abandoned and injured animals. We provide medical care, rehabilitation, and adoption services.",
            distance = "1.2 miles away",
            rating = 4.8,
            imageUrl = "ngo_animal",
            goalAmount = 15000.0,
            raisedAmount = 11250.0,
            daysLeft = 25,
            lat = 40.7128,
            lng = -74.0060
        ),
        Ngo(
            id = "2",
            name = "Clean Water for Villages",
            category = "Environment",
            description = "Help us bring safe, clean drinking water to remote communities through well construction and filtration systems.",
            distance = "5.0 miles away",
            rating = 4.9,
            imageUrl = "ngo_water",
            goalAmount = 25000.0,
            raisedAmount = 3200.0,
            daysLeft = 40,
            lat = 40.7200,
            lng = -74.0100
        ),
        Ngo(
            id = "3",
            name = "Education for Every Child",
            category = "Education",
            description = "Providing essential school supplies, scholarships, and learning resources for underprivileged students.",
            distance = "3.4 miles away",
            rating = 4.7,
            imageUrl = "ngo_education",
            goalAmount = 8000.0,
            raisedAmount = 3200.0,
            daysLeft = 40,
            lat = 40.7300,
            lng = -74.0200
        ),
        Ngo(
            id = "4",
            name = "Reforest Our Planet",
            category = "Environment",
            description = "Help us plant trees and restore vital forest ecosystems in deforested areas.",
            distance = "8.1 miles away",
            rating = 4.6,
            imageUrl = "ngo_forest",
            goalAmount = 20000.0,
            raisedAmount = 18500.0,
            daysLeft = 12,
            lat = 40.7400,
            lng = -73.9900
        )
    )

    val donations = listOf(
        Donation("101", "Paws & Whiskers Sanctuary", 50.0, "Oct 24, 2023", "Completed"),
        Donation("102", "Clean Water for Villages", 25.0, "Oct 15, 2023", "Completed"),
        Donation("103", "Education for Every Child", 100.0, "Sep 30, 2023", "Completed")
    )

    val currentUser = User(
        id = "u1",
        name = "Alex Johnson",
        email = "alex.j@example.com",
        profileImage = "user_alex"
    )
}
