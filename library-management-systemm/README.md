Table of Contents 
. System Requirements 
. Setup and Installation 
. Running the Application 
. API Endpoints 
. Authentication 
. Interacting with the API 
. Cache Configuration

System Requirements 
.Java Development Kit (JDK) 11 or higher 
.Maven 3.6+ 
.MySQL Database (or any other database you plan to use)
.Spring Boot 2.6+ 
.Postman or any API testing tool (optional, but recommended)

Setup and Installation git clone https://github.com/Rodaynaa/library-management-systemm.git
cd library-management-system

Build the Project 
mvn clean install

Running the Application 
mvn spring-boot:run

API Endpoints Book Management GET /api/books: Retrieve a list of all books.
GET /api/books/{id}: Retrieve details of a specific book by ID.
POST /api/books: Add a new book to the library.
PUT /api/books/{id}: Update an existing book's information.
DELETE /api/books/{id}: Remove a book from the library. 
Patron Management GET /api/patrons: Retrieve a list of all patrons. 
GET /api/patrons/{id}: Retrieve details of a specific patron by ID. 
POST /api/patrons: Add a new patron to the system. 
PUT /api/patrons/{id}: Update an existing patron's information. 
DELETE /api/patrons/{id}: Remove a patron from the system. 
Borrowing Records POST /api/borrow/{bookId}/patron/{patronId}: Allow a patron to borrow a book. 
PUT /api/return/{bookId}/patron/{patronId}: Record the return of a borrowed book by a patron.
