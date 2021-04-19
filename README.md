# Spring-Kotlin-Project-with-RabbitMQ-Elasticsearch

1: Run bash command: docker-compose up -d in the projectrootfolder for Elaticsearch, RabbitMQ and Kibana

2: Run import with bash in elastic_setup: python3 geo-import.py or python geo-import.py

You need two bashs to run both, the Geo Service and the Producer:

3: Start them with the following command in their folder:  ./gradlew bootRun --args='--spring.profiles.active=dev' 

4: Trigger import and push of Hotels with Post on localhost:8080/triggerImport/ 
  e.g. : curl -X POST localhost:8080/triggerImport/

5: Now processed Hotels can be found on the RabbitMQ GeoTag-Que under localhost:15672 with the following credentials: 
  Username: producer; Password: password. 

Detailed teststeps can be found in Testanleitung.pdf
