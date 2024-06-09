package com.example.auth.controller

import com.example.auth.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth")
class UserController @Autowired constructor(
    private val userService: UserService
) {
    data class RegisterRequest(val nickname: String, val email: String, val password: String)
    data class LoginRequest(val email: String, val password: String)
    data class AuthResponse(val token: String?)

    @PostMapping("/register")
    fun register(@RequestBody request: RegisterRequest): ResponseEntity<Any> {
        return try {
            userService.registerUser(request.nickname, request.email, request.password)
            ResponseEntity.ok("User registered successfully")
        } catch (e: Exception) {
            ResponseEntity.status(400).body("Error registering user: ${e.message}")
        }
    }

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): ResponseEntity<AuthResponse> {
        val token = userService.authenticateUser(request.email, request.password)
        return if (token != null) {
            ResponseEntity.ok(AuthResponse(token))
        } else {
            ResponseEntity.status(401).body(AuthResponse(null))
        }
    }

    @GetMapping("/user")
    fun getUser(@RequestParam token: String): ResponseEntity<Any> {
        val user = userService.findByToken(token)
        return if (user != null) {
            ResponseEntity.ok(user)
        } else {
            ResponseEntity.status(404).body("User not found")
        }
    }
}
