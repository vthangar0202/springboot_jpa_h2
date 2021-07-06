# springboot_jpa_h2
springboot demo with H2 and JPA

CRUD operations:

GET: http://localhost:8080/api/customers
     http://localhost:8080/api/customers?name=vasanth1
POST: http://localhost:8080/api/customers
  {
  "name":"vasanth1",
  "email":"vasanthu.ala@gmail.com.com",
  "mobileNumber": 9841700000,
  "age":37
  }
PUT: http://localhost:8080/api/customers/1
  {
  "name":"vasanth2",
  "email":"vasanthu.ala2@gmail.com.com",
  "mobileNumber": 9841700001,
  "age":38
  }
DELETE:http://localhost:8080/api/customers/1



Application.properties:
=======================

spring.datasource.url: jdbc:h2:mem for In-memory database. 
use jdbc:h2:file for disk-based database.

spring.h2.console.enabled=true tells the Spring to start H2 Database administration tool and you can access this tool on the browser: http://localhost:8080/h2-console.
spring.h2.console.path=/h2-ui is for H2 consol’s url, so the default url http://localhost:8080/h2-console will change to http://localhost:8080/h2-ui
