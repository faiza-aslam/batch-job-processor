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
* Download/clone the project.
* Execute the attached scripts
* Create datasource in standalone.xml of WildFly server as follows:
```sh
	<datasource jndi-name="java:jboss/jdbc/batch-ds" pool-name="BatchDatasource">
		<connection-url>jdbc:mysql://localhost:3306/batch?useSSL=false</connection-url>
		<driver>mysql</driver>
		<pool>
			<min-pool-size>10</min-pool-size>
			<max-pool-size>20</max-pool-size>
			<prefill>true</prefill>
		</pool>
		<security>
			<user-name>root</user-name>
			<password>root</password>
		</security>
	</datasource>
```

* To make job repository persistent, replace the jberet subsystem with the following:
```sh
	<subsystem xmlns="urn:jboss:domain:batch-jberet:1.0">
		<default-job-repository name="jdbc"/>
		<default-thread-pool name="batch"/>
		<job-repository name="in-memory">
			<in-memory/>
		</job-repository>
		<job-repository name="jdbc">
			<jdbc data-source="BatchDatasource"/>
		</job-repository>
		<thread-pool name="batch">
			<max-threads count="10"/>
			<keepalive-time time="30" unit="seconds"/>
		</thread-pool>
	</subsystem>
```


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
