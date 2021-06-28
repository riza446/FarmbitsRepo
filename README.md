# Farmbits test application developed by Rizauddin Mohammad

This is a test given by Farmbits Java / Maven / Spring Boot / in-memory database (H2) test application  I hope it helps me to pass this test.

## How to Run 

This application is packaged as a JAR based on requirement. You run it using the ```java -jar``` command.

* Clone this repository ```https://github.com/riza446/FarmbitsRepo.git```
* Make sure you are using JDK 1.8 and Maven 3.x
* You can build the project and run the tests by running ```mvn clean package``` or run using 
* Once successfully built, you can run the service by one of these two methods:

        java -jar -Dspring.profiles.active=test target/riza-farmbits-project-0.0.1-SNAPSHOT.jar 
        


## About the Service

The service is just a test category, product, provider review REST service. It uses an in-memory database (H2) to store the data. If your database connection properties work, you can call some REST endpoints defined in ```com.jiratec.farmbits.ui.controller``` on **port 8080**. (see below)

Here is what this little application demonstrates: 

* Full integration with the latest **Spring boot** 
* No need to install a container separately on the host just run using the ``java -jar`` command

* Writing a RESTful service using annotation
* for log and getter & setter   used  ``@Slf4j`` lombok
* *Spring Data* Integration with JPA/Hibernate with just a few lines of configuration and familiar annotations. 
* Automatic CRUD functionality against the data source using Spring *Repository* pattern 

Here are some endpoints you can call as per the main requirement:

### Add Provider to Product (adicionar fornecedores para um produto; )
```
POST /riza-farmbits/addProviderForProduct
Accept: */*
Content-Type: application/x-www-form-urlencoded


providerName:xyz2
productName:testing


RESPONSE: HTTP 201 
{
    "api": "addProviderForProduct",
    "message": "Successfully added",
    "providers": null
}
URL: http://localhost:8080/riza-farmbits/addProviderForProduct

```

### change a product's category (alterar a categoria de um produto;)

```
PUT /riza-farmbits/changeProductCategory
Accept: */*
Content-Type: application/x-www-form-urlencoded


productName:Apple
categoryName:mobile


RESPONSE: HTTP 201 
{
    "api": "changeProductCategory",
    "message": "Successfully updated",
    "products": null
}
URL: http://localhost:8080/riza-farmbits/changeProductCategory

```

### List products by categories (listar produtos por categorias;)
```
GET /riza-farmbits/getProductsByCategory/{id}
Accept: */*


RESPONSE: HTTP 201 
{
    "api": "getProductsByCategory",
    "message": "success",
    "products": [
        {
            "productName": "testing2",
            "description": "sdas",
            "price": 20.2,
            "discount": 5.3
        }
    ]
}
URL: http://localhost:8080/riza-farmbits/getProductsByCategory/test

```

### list products by provider (listar produtos por fornecedor;)
```
GET /riza-farmbits/getProductsByProvider/{id}
Accept: */*


RESPONSE: HTTP 201 
{
    "api": "getProductsByProvider",
    "message": "success",
    "products": [
        {
            "productName": "testing",
            "description": "sdas",
            "price": 10.2,
            "discount": 30.3
        },
        {
            "productName": "testing2",
            "description": "sdas",
            "price": 20.2,
            "discount": 5.3
        },
        {
            "productName": "testing4",
            "description": "sdas",
            "price": 40.2,
            "discount": 15.3
        }
    ]
}
URL: http://localhost:8080/riza-farmbits/getProductsByProvider/xyz

```
### ● list products with a discount above a value informed by parameter (listar os produtos com desconto acima de um valor informado por parâmetro -
ex: 30 (para 30%).)

```
GET /riza-farmbits/getProductsByDiscount/{id}
Accept: */*


RESPONSE: HTTP 201 
{
    "api": "getProductsByDiscount",
    "message": "success",
    "products": [
        {
            "productName": "testing",
            "description": "sdas",
            "price": 10.2,
            "discount": 30.3
        },
        {
            "productName": "testing3",
            "description": "sdas",
            "price": 30.2,
            "discount": 10.3
        },
        {
            "productName": "testing4",
            "description": "sdas",
            "price": 40.2,
            "discount": 15.3
        }
    ]
}
URL: http://localhost:8080/riza-farmbits//getProductsByDiscount/10

```

### Create a Category resource

```
POST /riza-farmbits/addCategory
Accept: */*
Content-Type: application/x-www-form-urlencoded

{
categoryName:mobile
}

RESPONSE: HTTP 201 (Created)
{
    "api": "addCategory",
    "message": "Successfully added",
    "categories": null
}
URL: http://localhost:8080/riza-farmbits/addCategory

```

### Create a Provider resource
```
POST /riza-farmbits/addProvider
Accept: */*
Content-Type: application/x-www-form-urlencoded

{
providerName:John
email:john@gmail.com
}

RESPONSE: HTTP 201 (Created)
{
    "api": "addProvider",
    "message": "Successfully added",
    "categories": null
}
URL: http://localhost:8080/riza-farmbits/addProvider

```

### Create a Product resource

```
POST /riza-farmbits/addProduct
Accept: */*
Content-Type: application/json

{
    "productName":"Apple",
    "description":"working with touch",
    "price":"1000",
    "discount": "5.0",
    "providerName":"John",
    "categoryName":"mobile"
}

RESPONSE: HTTP 201 (Created)
{
    "api": "addProduct",
    "message": "Successfully added",
    "products": null
}
URL: http://localhost:8080/riza-farmbits/addProduct

```
