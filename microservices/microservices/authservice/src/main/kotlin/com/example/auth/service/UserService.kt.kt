package com.example.auth.service

import com.example.auth.model.Session
import com.example.auth.model.User
import com.example.auth.repository.ListOfSessions
import com.example.auth.repository.ListOfUsers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class UserService @Autowired constructor(
    private val listOfUsers: ListOfUsers,
    private val listOfSessions: ListOfSessions
) {
    private val encoder = BCryptPasswordEncoder()

    fun register(email: String, password: String): String? {
        val person = listOfUsers.findwithEmail(email) ?: return null
        if (!isEmailRight(email)) {
            throw IllegalArgumentException("Invalid email")
        }

        if (!isPasswordRight(password)) {
            throw IllegalArgumentException("Invalid password")
        }
        return if (encoder.matches(password, person.password)) {
            val token = UUID.randomUUID().toString()
            val session = Session(user = person, token = token, time = LocalDateTime.now().plusHours(1))
            listOfSessions.save(session)
            token
        } else {
            null
        }


    }

    fun registration(nickname: String, email: String, password: String): User {
        val encodedPassword = encoder.encode(password)
        val user = User(name = nickname, email = email, password = encodedPassword)
        return listOfUsers.save(user)
    }


    fun findingWithToken(token: String): User? {
        val session = listOfSessions.findwithToken(token) ?: return null
        return session.user
    }

    private fun isEmailRight(email: String): Boolean {
        return email.contains("@") && email.contains(".")
    }

    private fun isPasswordRight(password: String): Boolean {
        val passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}$"
        return password.matches(passwordPattern.toRegex())
    }

}
