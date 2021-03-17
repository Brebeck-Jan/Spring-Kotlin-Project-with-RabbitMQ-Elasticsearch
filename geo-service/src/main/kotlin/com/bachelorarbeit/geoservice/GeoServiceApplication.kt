package com.bachelorarbeit.geoservice

import Hotels
import com.bachelorarbeit.geoservice.ElasticSearch.ElasticRepository
import com.bachelorarbeit.geoservice.service.MainService
import com.google.gson.Gson
import org.springframework.amqp.rabbit.annotation.RabbitHandler
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Service

@SpringBootApplication
class GeoServiceApplication

	fun main(args: Array<String>) {
		runApplication<GeoServiceApplication>(*args)
	}

