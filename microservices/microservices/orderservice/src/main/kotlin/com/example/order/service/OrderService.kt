package com.example.order.service

import com.example.order.model.Order
import com.example.order.repository.ListOfOrders
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OrderService @Autowired constructor(
    private val listOfOrders: ListOfOrders
) {
    fun makingOrder(user: Long, stateFrom: Long, stateTo: Long): Order {
        val order = Order(userId = user, stationFrom = stateFrom, stationTo = stateTo)
        return listOfOrders.save(order)
    }

    fun getOrderById(orderId: Long): Order? {
        return listOfOrders.findById(orderId).orElse(null)
    }

    // Emulate order processing
    fun processOrders() {
        val ordersToProcess = listOfOrders.findAll().filter { it.status == 1 }
        for (order in ordersToProcess) {
            order.status = if (Math.random() > 0.5) 2 else 3
            listOfOrders.save(order)
        }
    }
}
