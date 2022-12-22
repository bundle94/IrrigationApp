<h3>RUNNING ON LOCAL MACHINE</h3>
Clone project to your local machine.

Run the maven clean build command below in your terminal having navaigated to project root folder <code>mvn clean package</code>

Next, start up your application by running the command below in your terminal: <code>java -jar target/irrigationApp-0.0.1-SNAPSHOT.jar</code>

Now the application can be accessed here <code>localhost:8080</code>

<b>Endpoints</b>
1. GET http://localhost:8080/api/v1/plots/listAllPlots
2. POST http://localhost:8080/api/v1/plots/configurePlot </br>
sample request:
   <code>{
   "plotId": 1,
   "time": "06:00:00",
   "amountOfWaterNeeded": 50,
   "status": "irrigated"
   }</code>
3. POST http://localhost:8080/api/v1/plots/addPlot <br/>
sample request:
   <code>{
   "size": "123456sqm",
   "surveyed": false,
   "geoCordinates": "0246810, -3456789",
   "lastDateIrrigated": "2022-12-24",
   "crop": "cashew"
   }</code>
4. PUT http://localhost:8080/api/v1/plots/editPlot/1 <br/>
sample request:
   <code>{
   "size": "123456sqm",
   "surveyed": false,
   "geoCordinates": "0246810, -3456789",
   "lastDateIrrigated": "2022-12-24",
   "crop": "cashew"
   }</code>

<h3>RUNNING TEST</h3>
navigate to the root folder of the application, make sure you have maven installed and type <code>mvn clean test</code>

<h3>STACKS/TECHNOLOGIES USED</h3>
1. Java (using Spring Boot framework)
2. Maven (For dependency and package management),
3. JDK 16,
4. H2 Database (Which is a spring boot in-Memory Database)
5. Tomcat (which is the server),
6. Hibernate