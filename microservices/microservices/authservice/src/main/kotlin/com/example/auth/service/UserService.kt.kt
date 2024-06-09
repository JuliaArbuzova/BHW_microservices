package com.example.auth.service

import com.example.auth.model.Session
import com.example.auth.model.User
import com.example.auth.repository.SessionRep
import com.example.auth.repository.UserRep
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class UserService @Autowired constructor(
    private val userRep: UserRep,
    private val sessionRep: SessionRep
) {
    private val passwordEncoding = BCryptPasswordEncoder()

    fun authenticateUser(email: String, password: String): String? {
        val user = userRep.findwithEmail(email) ?: return null
        return if (passwordEncoding.matches(password, user.password)) {
            val token = UUID.randomUUID().toString()
            val session = Session(user = user, token = token, expires = LocalDateTime.now().plusHours(1))
            sessionRep.save(session)
            token
        } else {
            null
        }
    }

    fun registerUser(nickname: String, email: String, password: String): User {
        val encodedPassword = passwordEncoding.encode(password)
        val user = User(name = nickname, email = email, password = encodedPassword)
        return userRep.save(user)
    }


    fun findByToken(token: String): User? {
        val session = sessionRep.findwithToken(token) ?: return null
        return session.user
    }
}
