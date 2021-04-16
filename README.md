# Spring-Kotlin-Project-with-RabbitMQ-Elasticsearch

1: Start docker-compose with docker-compose up -d for Elaticsearch, RabbitMQ and Kibana

2: Run import script in elastic_setup localy with: python3 geo-import.py (you need to import your own data as .geojson and add it in script)

3: Start Producer and Geo-Service in their folders with:  ./gradlew bootRun --args='--spring.profiles.active=dev' 

4: Trigger import and push of Hotels with Post on localhost:8080/triggerImport/
e.g. : curl -X POST localhost:8080/triggerImport/

Detailed teststeps can be found in Testanleitung.pdf
