package com.example.order.controller

import com.example.order.service.OrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/orders")
class OrderController @Autowired constructor(
    private val orderService: OrderService
) {
    data class CreateOrderRequest(
        val userId: Long,
        val fromStationId: Long,
        val toStationId: Long
    )

    @PostMapping
    fun makeOrder(@RequestBody request: CreateOrderRequest): ResponseEntity<Any> {
        return try {
            val order = orderService.makingOrder(request.userId, request.fromStationId, request.toStationId)
            ResponseEntity.ok(order)
        } catch (e: Exception) {
            ResponseEntity.status(400).body("Error in creating order process: ${e.message}")
        }
    }

    @GetMapping("/{orderId}")
    fun getOrder(@PathVariable orderId: Long): ResponseEntity<Any> {
        val order = orderService.getOrderById(orderId)
        return if (order != null) {
            ResponseEntity.ok(order)
        } else {
            ResponseEntity.status(404).body("Order not found")
        }
    }
}
