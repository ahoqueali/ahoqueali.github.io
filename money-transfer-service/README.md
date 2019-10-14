# Money Transfer Service

Introduction
---

The Money Transfer REST API service allows users to:
* create accounts,
* add funds to an account,
* query account details,
* query account balances,
* query account transactions,
* and transfer money between accounts

The service is built using DropWizard.

How to start the MoneyTransfer application
---

1. Run `mvn clean install` to build your application
1. Run `java -jar target/money-transfer-service-1.0-SNAPSHOT.jar db migrate config.yml` to setup database 
1. Run `java -jar target/money-transfer-service-1.0-SNAPSHOT.jar server config.yml` to start application
1. Visit url `http://localhost:8080` to check the application is running

Health Check
---

Health url `http://localhost:8081/healthcheck`

Code
---
Visit the following link to get the [source](https://github.com/ahoqueali/ahoqueali.github.io/tree/master/money-transfer-service)
