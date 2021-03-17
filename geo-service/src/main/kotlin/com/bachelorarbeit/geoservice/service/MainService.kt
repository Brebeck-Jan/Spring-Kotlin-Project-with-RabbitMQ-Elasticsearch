package com.bachelorarbeit.geoservice.service

import com.bachelorarbeit.geoservice.ElasticSearch.ElasticRepository
import org.springframework.stereotype.Service

@Service
class MainService
(
        private val elasticRepository: ElasticRepository
) {
    fun testFct() {
        print("Im SERVADFS")
        val esResponse = elasticRepository.getPolygonsForPoint(11.0, 33.0)
        print(esResponse)
    }
}