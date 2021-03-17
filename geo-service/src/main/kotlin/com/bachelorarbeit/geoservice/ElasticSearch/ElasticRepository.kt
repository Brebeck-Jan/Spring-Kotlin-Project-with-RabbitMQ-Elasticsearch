package com.bachelorarbeit.geoservice.ElasticSearch

import com.bachelorarbeit.geoservice.ElasticSearch.Model.Polygon
import org.springframework.data.elasticsearch.annotations.Query
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository


interface ElasticRepository : ElasticsearchRepository<Polygon, String>{
   @Query("""    
    {
        "bool":{
        "filter":{
            "geo_shape":{
                "location": {"relation": "intersects","shape": {"type": "point","coordinates": [?0, ?1]}}}
    } 
    }
    }""")
    fun getPolygonsForPoint(latitude: Double, longitude: Double): ArrayList<Polygon>

    @Query("""{"match_all": {}}""")
    fun getAllPolygons():ArrayList<Polygon>
}


