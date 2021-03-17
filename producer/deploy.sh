./gradlew build

cd ..

docker-compose stop producer
docker-compose up -d --build producer

sleep 5

curl -X POST localhost:8080/triggerImport/
