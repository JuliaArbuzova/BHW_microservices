package com.example.auth.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "session")
data class Session(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @ManyToOne @JoinColumn(name = "user_id")
    val user: User,
    val token: String,
    val time: LocalDateTime
)
