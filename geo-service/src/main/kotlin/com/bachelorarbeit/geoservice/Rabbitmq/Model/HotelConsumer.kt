package com.bachelorarbeit.geoservice.Rabbitmq.Model

import Hotels
import com.bachelorarbeit.geoservice.ElasticSearch.ElasticRepository
import com.google.gson.Gson
import org.springframework.amqp.rabbit.annotation.RabbitHandler
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository
import org.springframework.stereotype.Service

@Service
@RabbitListener(queues = ["hello"])
class HotelConsumer(
        val elasticRepository: ElasticRepository

) {
    @RabbitHandler
    fun processHotels(name: String) {
        var gson = Gson()
        var test = gson.fromJson(name, Hotels::class.java)
        println(test.coord)
        elasticRepository.getPolygonsForPoint(11.0,53.0)
    }


}