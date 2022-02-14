# Warehouse Assignment

## Description 

The assignment is to implement a warehouse software. This software will hold articles, and the articles should contain an identification number, a name and available stock.


## Table of Contents

If your README is very long, add a table of contents to make it easy for users to find what they need.

* [Implementation](#Implementation)
* [Usage](#Usage)
* [Enhancements](#Enhancements)
* [Tests](#Tests)

## Implementation Details

As per the requirement, the follow resources are exposed:
  1. Get all products
  2. Upload products from a given file
  3. Get a single product
  4. Delete/Sell a product
  5. Delete all Products
  6. Upload inventories from a given file
  7. Update Inventory
  8. Delete all inventory
  9. Get inventories
  
Swagger-ui view : https://datawarehouse-2gwxlvwsmq-ew.a.run.app/swagger-ui/index.html

##### In the project root path, Cloud Run Warehouse.postman_collection.json file has been provided which is an exported collection having all the above api resources.
Import the collection and start using the resources.

Please note the for upload of products and inventory resource, upload the products.json and inventory.json file respectively which can also be be found in the project root path (In the body, select form-data, give key as file and select value as file and then upload the file)
The base path of api resources points to cloud run where the application is deployed.

Schema.sql defines the database structure.
SQL  has been adopted here since the inbound data is structured . Also, it will be easier to hook up with BI tools to perform analytics as the fields can be marked as dimension or facts based on need.
(It could have been done using NOSql as well). 
Fields are available as columns and can be easily extracted in queries.

Initial data has not been imported since the understanding is that the initial load will be done using the upload api resources for products and inventories.

DTO(Data Tansfer Objects used in rest api interfaces) and Entities(reflects db model) are separately maintained.

Package info in com.learning.datawarehouse:
- Controller : Contains REST api endpoints. Interacts with service layer .
- Service : Contains the business logic. Interacts with other service and repositories.
- repositories : Contains the repositories (JPA) for each underlying table. 
- model : Contains the entity objects that reflect a table row
- dto : Contains the data transfer objects that are exchanged during rest api call.
- exception : The different exceptions used in this application. Each exception contains a unique error code that is logged and can be used to map the error returned in rest api call with the one found in logs.
- exceptionhandler : The centralized place to map different exceptions with http status codes. General rule is if it is a client error, 4xx error code is returned and if is a server side error , 5xx error code is returned.
- util : Contains mapper utilities that map dto to entity and vice versa.

## Usage 
- Upload products and inventory to begin and then subsequently invoke any desired operation. The product has a generated product id and this has been maintained since generally the consumers (say front end) will pass an id while getting or removing a product.
 
- In this implementation, the product operations are all inserts. So to update a product, delete a products or all of them and then invoke the product upload again. Here, product name is considered to be unique and so application will return error if same product is again being uploaded that already exists in database.
 
- The application is integrated with github actions for CI/CD and deployed to cloud run (personal account). The application is deployed in cloud run url : https://datawarehouse-2gwxlvwsmq-ew.a.run.app
 
- When selling/deleting a product, if any of the article is not having sufficient stock, an exception is returned with status code and an error message.

- When selling/deleting a product, if success, the correponding stock amount for an article is reduced in the inventory repository.
 
- The service account email and json are maintained as github secrets and passed to github action during run.
 
- Used @ControllerAdvice way of exception handling.
 
- Used slf4j for logs (lombok)
 
- Used lombok plugin for clean code
 
- Used in memory H2 Database
 


## Future Enhancements
This assignment covers the basic funtionality of warehouse. More features to be covered will be 
- Maintaining product, inventory versions so that during update dirty reads are avoided.
- Security , OAuth2.0 , We can create an app with auth0, the clientId and clientSecret can be used then to get the jwt token from the auth server. This jwt token can be then passed as bearer token in the autth header of api resource call.
- API Gateway to have control of who can consumer the api resources and also to have a defined rate limit and burst limit, so that the backend servers are not overloaded.
- Cloud Run invocation allows unauthenticated but for enhanced security we can limit it be using the service account.
- Active/Passive deployment using tags and how much % traffic to allow for each tag in cloud run(https://cloud.google.com/run/docs/managing/revisions)
- Multi Module( ability when application grows)
- Multi-profiling for example, having application-prod.properties to have specific prod config
- Embedding SonarQube quality gate check in gitaction and failing if the quality gate fails.
- For production, gcp provides image scan (https://cloud.google.com/container-analysis/docs/container-scanning-overview). We can embed this as well in github action.
- Structured logging needs to be implemented (json format) so that searching can be done by fields.
- Stackrdiver monitoring or Graphana for monitoring stackdriver metrics.
- Database product and inventory table having created date, last updated date. More metadata info fields can be added as per need.
  
  


## Tests
    2 integration test case and 1 unit test has been covered. This is to establish a template on how further unit test case and integration test case will be developed.
    As part of enhancement more unit test case and integration test case needs to be implemented.


