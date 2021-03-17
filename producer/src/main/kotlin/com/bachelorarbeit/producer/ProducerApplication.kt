package com.bachelorarbeit.producer

import HotelsFromJson
import com.google.gson.Gson
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.io.BufferedReader
import java.io.File
import kotlin.reflect.typeOf


@SpringBootApplication
class ProducerApplication

fun main(args: Array<String>) {
    runApplication<ProducerApplication>(*args)
}

@RestController
class RabbitProduceController(val rabbitTemplate: RabbitTemplate) {
    @PostMapping("/triggerImport/")
    fun postHotels(): ResponseEntity<String> {
        var dataString = File("Hotels.geojson").readText()
        var gson = Gson()
        var test = gson.fromJson(dataString, HotelsFromJson::class.java)

        println(test)
        println(test.hotels.get(0))
        test.hotels.onEach {
            var response = rabbitTemplate.convertAndSend("hello", gson.toJson(it))
            println(gson.toJson(it))
            println(response)
        }
       return ResponseEntity.ok("ok")
    }
}
