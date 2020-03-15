# Codecool Online Shop

Java SE: Web Project skeleton

# Install

Import this project to IntelliJ as a Maven project.
IntelliJ can auto-install the dependencies based on the pom.xml

Assignment: Codecool Shop -- Sprint #1
Project Goal
The goal is to build an Online Shop, an online eCommerce web-application with Java, where users can browse products, add them into a shopping cart, checkout items and make payments.

The first sprint
The goal of the first sprint is to add the "shopping cart" functionality and to practice advanced Java OOP concepts.

Your Product Owner collected the Product Backlog (with all the User Stories) here in this spreadsheet:

https://docs.google.com/spreadsheets/d/1VNw3wYs492iRyUZ3oPxkPHSNnd-ASFYsBsLxoxpicTQ/edit?usp=sharing

During the Sprint Planning Meeting you have to create a Sprint Backlog with all the Tasks.

Technical requirements
Start from the existing codebase (it's a skeleton application for the Codecool Shop).
Work with GIT feature branches.
Use advanced OOP concepts:
Use inheritance
Write at least one abstract class
Implement at least one interface
Do not use a database, now only use in-memory storage or file storage but through the DAO pattern (Data Access Object).
It's not required to integrate real payment services - you can use fake payment implementations.

Assignment: Codecool Shop -- Sprint #2
Project Goal
The goal is to enhance the Online Shop, an online eCommerce web-application with Java.

Where users can not only browse products, add them into a Shopping Cart, checkout items and make payments. But also can log in and see the abandoned shopping cart or order history.

This sprint
The main goal of this sprint is to store the data in a database that is persistent. It means that the data will survive a restart of the application.

Further goals are to practice

SQL
testing
advanced Java OOP concepts
Your Product Owner enhanced the Product Backlog (with all the User Stories). Here in this Spreadsheet:

https://docs.google.com/spreadsheets/d/1pB3BPWp47UrviA0keSSuTXNlDC2sJbm4emmEzfc5D5M/edit?usp=sharing

The Product owner wants you to deliver the user stories that ensures the persistent storage of the Product data, so you have to deliver user stories 12, 13, 14 in this sprint. Among others, of course. :)

During the Sprint Planning Meeting , you have to create a Sprint Backlog , with all the Tasks. So please:

add the new stories to the Product Backlog in your existing Backlog spreadsheet
create your sprint 2 backlog with keeping in mind that you have to choose user stories 12, 13 and 14. Beyond them you can choose user stories from the whole backlog including unimplemented user stories from the last TW week.
Technical requirements
Continue your existing code from the last TW week.
Work with GIT flow.
Use JDBC for the database access.
Use the database through the DAO pattern (Data Access Object).
Suggested (though not required) to cover the new code with tests.
Use advanced OOP concepts:
Use inheritance
Write at least one Abstract class
Implement at least one Interface
It's not required to integrate real payment services - You can use fake payment implementations.
Note: Testing classes via their interface
Please test (and use) the DAO implementations via interfaces so that it will be easy to change the implementation behind the interface.

JUnit also provides support for this case, i. e. running the same test set against several implementations of the same interface.

dao_interface.png
