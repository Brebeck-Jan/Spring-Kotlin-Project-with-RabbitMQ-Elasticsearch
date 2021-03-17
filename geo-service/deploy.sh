gradle build -x test


cd ..

docker-compose stop geo-service
docker-compose up -d --build geo-service