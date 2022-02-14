# Warehouse Assignment

## Description 

The assignment is to implement a warehouse software. This software will hold articles, and the articles should contain an identification number, a name and available stock.


## Table of Contents (Optional)

If your README is very long, add a table of contents to make it easy for users to find what they need.

* [Implementation Details](#Implementation Details)
* [Future Enhancements](#Future Enhancements)


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
  9. Get Inventories

In the project root path, Cloud Run Warehouse.postman_collection.json file has been provided which is an exported collection having all the above api resources.
Please note the for upload of products and inventory resource, upload the products.json and inventory.json file respectively which can also be be found in the project root path (In the body, select form-data, give key as file and select value as file and then upload the file)
The base path of api resources points to cloud run where the application is deployed.


## Usage 
 Upload products and inventory to begin and then subsequently invoke any desired operation.
 The product has a generated product id and this has been maintained since generally the consumers (say front end) will pass an id while getting or removing a product.
 
 In this imnplementation, the product operations are all inserts. So to update a product or inventory, delete a products or all and then invoke the product upload again. Here, product name is considered to be unique and so application will return error if samee product is again being uploaded that already exists in database.
 
 The application is intgetated with github actions for CI/CD and deployed to cloud run (personal account).
 
 While selling/deleting a product, if any of the article is not having sufficient stock, an exception is returned with status code and an error message.
 
 The service account email and json are maintained as github secrets and passed to github action during run.
 
 Used @ControllerAdvice way of exception handling.
 Used slf4j for logs (lombok)
 Used lombok plugin for clean code
 Used in memory H2 Database
 


## Future Enhancements
This assignment covers the basic funtionality of warehouse. More functionalities to be covered will be 
  1. Maintaining product, inventory versions so that during update dirty reads are avoided.
  2. Security - OAuth2.0 - We can create an app with auth0, the clientId and clientSecret can be used then to get the jwt token from the auth server. This jwt token can be then passed as bearer token in the autth header of api resource call.
  3. API Gateway to have control of who can consumer the api resources and also to have a defined rate limit and burst limit, so that the backend servers are not overloaded.
  4. Cloud Run invoacation allows unauthenticated but for enhanced security we can limit it be using the service account.
  5. Active/Passive deployment using tags and how much % traffic to allow for each tag in cloud run(https://cloud.google.com/run/docs/managing/revisions)
  6. Multi Module( ability when application grows)
  7. Multi-profiling for example, having application-prod.properties to have specific prod config
  8. 


## Tests


