package com.example.order.model

import jakarta.persistence.*

@Entity
@Table(name = "station")
data class Station(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val state: String
)
