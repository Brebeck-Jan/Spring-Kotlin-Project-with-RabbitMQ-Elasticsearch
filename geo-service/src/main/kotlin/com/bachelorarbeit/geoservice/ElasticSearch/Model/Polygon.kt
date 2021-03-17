package com.bachelorarbeit.geoservice.ElasticSearch.Model

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType

@Document(indexName = "geo-index")
class Polygon {
    @Id
    private val id: String? = null

    @Field(type = FieldType.Text)
    private val name: String? = null
}