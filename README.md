"# loan-approval" 

Used technologies:
------------------
 - JAVA
 - HTML
 - Tomcat
 - AngularJS
 - Acessing the Camunda REST interface
 - Camunda BPMN and DMN

Description:
------------
 - This is a Java/Tomcat web application, which prompts the user to access loan-request data in a HTML form.
 - The HTML form triggers an angularJS controller
 - The angularJS controller sets-up a post request to the tomcat server
 - The server (using Spring MVC) is listing to the post request and triggers a the Camunda loan-request-procedure
   The Camunda has to be separately deployed to the Camunda workflow engine!

Important:
----------
 - This application needs a the corresponding server application, which hosts the Camunda workflow process

Bringing the complete application to run:
-----------------------------------------
 - Install and run Camunda according to https://docs.camunda.org/get-started/bpmn20/install/
 - Checkout the Camunda application part:
    1. git clone https://github.com/asalmhofer/loan-approval-server-camunda.git
	2. Add you mail properties in src/main/resources/mail.properties
	3. mvn clean install --> You get the WAR file in the "target" directory
	4. Copy the WAR file to the "webapps" folder in Camunda (so that tomcat deploys it)
	5. You can now access http://localhost:8080/camunda/app/cockpit and see the deployed process
 - Checkout the client part (i.e. the web application which triggers the previously deployed Camunda process)
    1. git clone https://github.com/asalmhofer/loan-approval-client.git
    2. mvn spring-boot:run
	3. You can now access http://localhost:8090/index.html
	4. Request your loan