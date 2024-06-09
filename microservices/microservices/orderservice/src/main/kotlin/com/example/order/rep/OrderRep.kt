package com.example.order.repository

import com.example.order.model.Order
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRep : JpaRepository<Order, Long>
