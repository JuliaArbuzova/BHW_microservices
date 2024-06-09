package com.example.order.repository

import com.example.order.model.Station
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StationRep : JpaRepository<Station, Long>
