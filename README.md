# AirlineTicketBookingSystem
INFO6150
Course - Web Development Tools and Methods
This project is a web application built using Spring MVC and Hibernate ORM frameworks. SQL is used as the database at the backend. JSP is used for the front-end. 
Admin can create airliners and add flights. End users can book the flights based on the parameters like cities, dates, etc. The end user can download a PDF ticket and receives an email about the booking details. 

Directions to run the project:
●	Download the project and open in Eclipse JAVA EE. ●	Need JRE 1.8 or above. ●	Go to "hibernate.cfg.xml" file and replace mysql url, username and password bean properties with host computer mysql url, username and password.  ●	Update the maven dependencies to download all the required libraries. ●	Run the project, select Run on Server Option.

NOTE: If any errors regarding contextLoadListener, please do following steps:

Clean Tomcat Work Directory (Servers -> Tomcat Server -> Clean , Servers -> Tomcat Server -> Clean Work Directory)
Go to Project Properties -> Deployment Assembly -> Add -> Java Build Entries -> Maven Dependencies -> Apply -> Ok
Then Run the project.
Install JRE 1.8+. Eclipse with Spring Tool Suite to run the project. Add the project as existing maven project and click Run -> 
