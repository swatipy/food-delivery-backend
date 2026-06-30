## Food Delivery Application

### Build and Run

```shell
./mvnw clean install
java -jar target/food-delivery-0.0.1-SNAPSHOT.jar
java -jar target/food-delivery-0.0.1-SNAPSHOT.jar \
  --spring.profiles.active=dev
```

### Docker Build and run 

docker build -t food-delivery-service .

docker run -p 8080:8080 \
-e SPRING_PROFILES_ACTIVE=docker \
food-delivery-backend:latest

### EC2 Build and run 