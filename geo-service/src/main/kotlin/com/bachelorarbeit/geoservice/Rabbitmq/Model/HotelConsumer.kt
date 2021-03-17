package com.bachelorarbeit.geoservice.Rabbitmq.Model

import Hotel
import com.bachelorarbeit.geoservice.ElasticSearch.ElasticRepository
import com.google.gson.Gson
import org.springframework.amqp.rabbit.annotation.RabbitHandler
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Service

@Service
@RabbitListener(queues = ["hello"])
class HotelConsumer(
        val elasticRepository: ElasticRepository,
        val gson: Gson = Gson(),
        val rabbitTemplate: RabbitTemplate
) {
    @RabbitHandler
    fun receive(HotelString: String) {
        var Hotel = gson.fromJson(HotelString, Hotel::class.java)
        var ElasticsearchResponse = elasticRepository.getPolygonsForPoint(Hotel.coord.get(0),Hotel.coord.get(1))

        ElasticsearchResponse.onEach {
            Hotel.tags.add(it.name!!)
        }
        println(Hotel)
        var response = rabbitTemplate.convertAndSend("hotel", gson.toJson(Hotel))
    }
}