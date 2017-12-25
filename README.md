# batch-job-processor
This project demonstrates the features of JavaEE 7 batch processing API. 
The use case is to send SMS/Email notification on daily basis to some particular group of users.

### Technology Stack
* JavaEE 7
* EJBs
* CDI
* WildFly 10
* MySQL

### Project Setup
Download/clone the project.
Execute the attached scripts

Build the project using:
```sh
$ mvn clean install
```

Deploy the war in wildfly and start the server using:

```sh
$ standalone.bat (or standalone.sh)
```

Open browser and go to URL:
> http://localhost:8080/batch

This will open up index.jsp page. Click on the link to start the job.
