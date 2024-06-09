package com.example.order.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "order")
data class Order(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val userId: Long,
    val stationFrom: Long,
    val stationTo: Long,
    val status: Int = 1,
    val time: LocalDateTime = LocalDateTime.now()
)
