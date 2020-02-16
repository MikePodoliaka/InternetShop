# InternetShop

## Project purpose
#### This is a template for creating an e-store.

##### It implements typical functions for an online store. It has login and registration forms.
#### Available functions for all users:
##### view menu of the store
##### view items of the store
##### registration
##### log in
##### log out
#### Available functions for users with a USER role only:

##### add to user's bucket
##### delete from user's bucket
##### view all user's orders
##### complete order
##### view a lists of selected items in user`s bucket
#### Available functions for users with an ADMIN role only:

##### add items to the store
##### delete items from the store
##### view a list of all users
##### delete users from the store
## Project Structure
##### Java 11
##### Maven 4.0.0
##### javax.servlet-api 3.1.0
##### jstl 1.2
##### log4j 1.2.17
##### maven-checkstyle-plugin
##### mysql-connector-java 8.0.18
### For developer

##### Use file "/src/main/resources/init.sql" to create schema and all the tables required by this app in MySQL database.

##### By default there are one user with an USER role (login = "user1", password = "123") and one with an ADMIN role (login = "admin1", password = "123").

## Author
### Mike Podoliaka
