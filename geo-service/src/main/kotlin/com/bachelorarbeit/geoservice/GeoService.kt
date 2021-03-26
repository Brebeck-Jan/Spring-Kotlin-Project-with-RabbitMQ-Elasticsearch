package com.bachelorarbeit.geoservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GeoServiceApplication

	fun main(args: Array<String>) {
		runApplication<GeoServiceApplication>(*args)
	}