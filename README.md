# BookWorm 

### Application Programming in Java: Project Specifications



#### Learning Objectives:
1. To build strong programming skills using Java from theories of computer science.
2. To build design/implementation skills of advanced applications under Java environments.
3. To apply object-oriented concepts to the development of desktop applications.
4. To find elegant solutions to complex technical problems.
5. To obtain skills necessary to coordinate technical tasks within the team

### Project Overview
At present, IUT’s library is using either manual journal-based system or a spreadsheet like Excel to manage their day to day activity. Most of the things such as record of students who’ve issued the book, no. of books in the library, fine of students, etc. are recorded using pen-paper system. This traditional system requires a lot of time and manpower for performing simple operations in the library.

In this project, you are required to develop a desktop application using Java technologies and Object-Orient Programming concepts to help librarians accomplish their daily job. Library Management System (LMS) is a software solution that helps both students and library manager to keep a constant track of all the books available in the library. It allows both the admin and the student to search for the desired book. The main feature of this system is that all the books available in the library can be displayed in a list so that students need not roam through the entire library to find a book. Additionally, the application effectively maintains the details of users/students to whom books have been issued; it also records the issued date and return date.

Below are common features of a typical LMS:

Add, modify and delete users with 4 roles (Student, Librarian and Administrator) into the database.
* Authenticate and authorize users to accomplish certain actions based on their role
* Add, modify and delete book details into the database.
* Search available books by their title, subject, author, ISBN and publish date.
* Issue book loans for a certain period (e.g. 1 semester, 1 month, 1 day) if they are available.
* Reserve borrowed books beforehand.
* Issue fines for book loan overdue.

As mentioned above, every user must be assigned to a role. For simplicity sake, assume that one user can be assigned to a single role only. Below is the list of roles and corresponding actions they can do:
* Administrators – users that are responsible for creating accounts for librarians and students as well as adding information about books.
  * Login/Logout from the system
  * View/Add/Modify/Delete Librarian
  * View/Add/Modify/Delete Student
  * View/Add/Modify/Delete Books
* Librarians – users that are responsible for adding and modifying books, book items, and users. The Librarian can also issue and return book items. For books returned after due date, the Librarian issues a fine to a responsible student.
  * Login/Logout from the system
  * View/Add/Modify/Delete Students
  * View/Add/Modify/Delete Books
  * View books borrowed by a student
  * View students with an overdue
  * View all books and filter them by borrowed status, title, subject, author, ISBN and publish date.
  * View monthly report on books borrowed from the library
  * Issue a book loan for limited period
  * Return a borrowed book.
  * Issue a fine to a student who returned book after due date.
  * Block a student from borrowing a book.
* Students – users that can search the catalog, as well as check-out, reserve and return a book.
  * Login/Logout from the system
  * View his/her personal details as well as books he/she borrowed
  * View his/her current fine
  * View all books and filter them by borrowed status, title, subject, author, ISBN and publish date.
  * Reserve borrowed books beforehand.
