package com.bachelorarbeit.geoservice.service

import com.bachelorarbeit.geoservice.ElasticSearch.ElasticRepository
import org.springframework.amqp.rabbit.annotation.RabbitHandler
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Service

class MainService
