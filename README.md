# Wilbur
Reading Comprehension Intelligent Tutoring System

Wilbur is a reading comprehension-focused Intelligent Tutoring System that will work on keeping students engaged and teachers involved. The tool is web-based and has a framework in place that will work to adapt the dif-ficulty of the lesson and help provide feedback at the appropriate level. The tool is focused on two main areas of improvement for Reading Comprehension ITS, student engagement and teacher engagement.

## How to build the server for Development
Navigate to wilbur-spring and run
`gradle build`

## How to run the server for Development
Navigate to the wilbur-spring directory and run
`gradle bootRun`

## How to run the UI for Development
Navigate to the wilbur-web directory and run
`npm install`
or if you are using yarn, run
`yarn install`
This will install the required packages for the UI

Run the node development server:
Navigate to the wilbur-web directory and run
`npm start`
or if you are using yarn, run
`yarn run`

## How to run the distribution build
Unzip the .zip or untar the .tar distribution and navigate to the .bin directory
Run the Wilbur executable
`./wilbur-spring`

By default, the database is set to clear at startup and is using the H2 in-memory database. To switch to PostGres, modify the application properties and include the following:

*spring.datasource.url=jdbc:postgresql://yourdatabasehost:5432/wilbur*
*spring.datasource.username=wilbur*
*spring.datasource.password=yourdatabasepasswordhere*

*spring.jpa.database=POSTGRESQL*

*spring.jpa.hibernate.ddl-auto=create*