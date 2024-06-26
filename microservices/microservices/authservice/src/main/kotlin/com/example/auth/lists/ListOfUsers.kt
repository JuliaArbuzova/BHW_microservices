package com.example.auth.repository

import com.example.auth.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ListOfUsers : JpaRepository<User, Long> {
    fun findwithEmail(email: String): User?
}
