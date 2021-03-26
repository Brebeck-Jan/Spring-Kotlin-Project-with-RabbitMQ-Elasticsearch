package com.bachelorarbeit.geoservice.Rabbitmq

import Hotel
import com.bachelorarbeit.geoservice.ElasticSearch.ElasticRepository
import com.bachelorarbeit.geoservice.ElasticSearch.Model.Polygon
import com.google.gson.Gson
import org.springframework.amqp.rabbit.annotation.RabbitHandler
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Service

@Service
@RabbitListener(queues = ["Hotel"])
class HotelConsumer(
        val elasticRepository: ElasticRepository,
        val gson: Gson = Gson(),
        val rabbitTemplate: RabbitTemplate
) {
    @RabbitHandler
    fun receiveAndProcessHotels(HotelString: String) {
        var Hotel = mapJsonToHotel(HotelString)
        var ElasticsearchResponse = elasticRepository.getPolygonsForPoint(Hotel.coord.get(0),Hotel.coord.get(1))
        Hotel = enricheHotelWithTags(Hotel,ElasticsearchResponse)
        rabbitTemplate.convertAndSend("GeoTags", gson.toJson(Hotel))
        println(Hotel)
    }

    private fun enricheHotelWithTags(Hotel: Hotel, ElasticsearchResponse: ArrayList<Polygon>): Hotel {
        ElasticsearchResponse.onEach {
            Hotel.tags.add(it.name!!)
        }
        return Hotel
    }

    private fun mapJsonToHotel(HotelString: String): Hotel {
    return gson.fromJson(HotelString, Hotel::class.java)
    }
}