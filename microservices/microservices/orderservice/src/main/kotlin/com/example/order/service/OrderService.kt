package com.example.order.service

import com.example.order.model.Order
import com.example.order.repository.OrderRep
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OrderService @Autowired constructor(
    private val orderRep: OrderRep
) {
    fun makingOrder(userId: Long, fromStationId: Long, toStationId: Long): Order {
        val order = Order(userId = userId, fromStationId = fromStationId, toStationId = toStationId)
        return orderRep.save(order)
    }

    fun getOrderById(orderId: Long): Order? {
        return orderRep.findById(orderId).orElse(null)
    }

    // Emulate order processing
    fun processOrders() {
        val ordersToProcess = orderRep.findAll().filter { it.status == 1 }
        for (order in ordersToProcess) {
            order.status = if (Math.random() > 0.5) 2 else 3
            orderRep.save(order)
        }
    }
}
