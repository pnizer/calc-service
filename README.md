# calc-service

To run this services, first initilize a MongoDB instance. You can use the following Docker commands:
```
docker run --name mongo -d -p 27017:27017 mongo:latest
```

To compile and run this project with Maven, you can use the command:

```
mvn spring-boot:run
```

The service can be invoked by the url http://localhost:8080/


## API

* Sum
```
POST /sum
Content-type: application/json

{
   "x": 2,
   "y": 3
}
```

* Product by 2
```
POST /product
Content-type: application/json

{
   "a": 5
}
```

* 2nd power (^2)
```
POST /power
Content-type: application/json

{
   "s": 10
}
```

* Get the history of results from database
```
GET /history
```
