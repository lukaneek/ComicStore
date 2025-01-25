This full stack project is a comic book store built with java, spring boot, JSP's(Java Server Pages), Maven, Gradle, glassfish, tomcat, bcrypt and uses a SQL database.  

Project details:

  register/login authentication/error validation.
  protected routes that protect against non logged in users from accessing further into the site without logging in/registering, aswell as limiting access for non admin users.
  password hashing using bcrypt.
  JSP's use bootstrap for styling, JSTL(JavaServer Pages Standard Tag Library) for functionality and conditional rendering, aswell as spring framework's form tags for better form functionality.
  Objects use one to many and many to many relationships.
  All data retrieved from the user is validated before entering the database.
  JPA query methods.
  
The project is made up of a 3 tier architecture:

  1. The UI uses a MVC(Model, View, Controller) framework.  The controller takes in data from the user, which calls the service's methods for any buisness logic, which then gets sent back to the controller.
  The controller then takes the new data and gives it to the view which renders a new display for the user.  

  2. When the controllers recieve data they call the service methods for any buisness logic.

  3. The third tier is for accessing the data.  When the services are called they call the repositories to query data from the database. The database then returns the data which is sent to the services.


Project Summary:

once logged in, a normal user can view/search through the library of comics and choose to either rent or buy comics. While an admin user is allowed to add/update/delete comics and genres to the library.
a comic consists of a title, author, publisher, number of pages, genres, and a cover picture which you can upload from your computer or choose a preset picture from the cover picture folder provided. All fields are authenticated and will display errors on screen if left blank or criteria isn't met.(this also applies to updating a comic)
when a user rents a comic it is shown in a seperate library that only the user that rented it can see.  The user has the option to either return the comic which adds it back to the main library or they can purchase the comic which removes it from all libraries.
a user can click on the name of a comic to view details of the comic, aswell as write a review on the comic which includes their written review, 2 options for if they recommend it or not, aswell as a 1-10 rating of the comic. These comments are then displayed under the details of the comic.(if user is an admin they're given update or delete options here).





