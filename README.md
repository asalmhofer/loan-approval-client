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

Starting the application:
 - Install Camunda according to https://docs.camunda.org/get-started/bpmn20/install/
 

mvn spring-boot:run