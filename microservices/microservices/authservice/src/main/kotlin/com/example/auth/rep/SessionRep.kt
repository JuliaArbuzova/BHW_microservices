package com.example.auth.repository

import com.example.auth.model.Session
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SessionRep : JpaRepository<Session, Long> {
    fun findwithToken(token: String): Session?
}
