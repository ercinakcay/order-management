# Order Management API

Order management API is basicly taking customer basket and at the end of the transaction and compete through making order with all processes.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

Before starting to development/testing;
* [Docker](https://www.docker.com/)
* [make](https://en.wikipedia.org/wiki/Makefile)
* [Maven](https://maven.apache.org/)
* [Postman](https://www.postman.com/)


## Make Usage

makefile made for the automation some useful scripts.

makefile scripts running on project parent directory.

### makefile Production Scripts

These scripts can be used as deployment process.

* `make build-dev`
	* Runs `maven` `clean`, `install` and `package` commands for preparing project.
	* Compose up for development environment which is specified at `docker-compose.dev.yml`
	* Rest a bit cause of waiting server up status.
	* Sanity check for server availability.

* `make build-test`
	* Runs `maven` `clean`, `install` and `package` commands for preparing project.
	* Compose up for development environment which is specified at `docker-compose.test.yml`
	* Rest a bit cause of waiting server up status.
	* Sanity check for server availability.	

* `make build-prod`
	* Runs `maven` `clean`, `install` and `package` commands for preparing project.
	* Compose up for development environment which is specified at `docker-compose.prod.yml`
	* Rest a bit cause of waiting server up status.
	* Sanity check for server availability.	


### makefile Development Scripts

* `make prepare`
	* Runs `sh mvnw clean install -Dmaven.test.skip=true`

* `make package`
	* Runs `sh mvnw package -Dmaven.test.skip=true`

* `make dev`
	* this is basicly remove if named order management image exist and compose up new one. 
	* `docker-compose` run with tailing the whole process.

## Running the Tests

Developed as integration tests. For this reason, `'dev'` profiled servers must be up. 

For starting server go to project parent directory;

```
make buid-dev
```

#### Integration Tests

```
com.ea.nextordermanagementapi.test.bussiness.BasketTest
```
```
com.ea.nextordermanagementapi.test.bussiness.OrderTest
```

**Note:** Tests can be flacky cause of service caches. Prevention of this situation run the below curl operation.

```
curl -XGET http://localhost:8080/cache/evictAll
```

### Postman Usage

Postman is used for testing particular requests and likewise integration testing.

Postman public embeded link [**here** ](https://www.getpostman.com/collections/cc6601be4b6ed937856f).

**Note:** If the link is unreachable please contact with me.

This postman collection has pre-request scripts. They used for granting prefered input variables for posting the defined requests.


## Author

* [**Ercin Akcay** ](https://github.com/ercinakcay)


## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details

