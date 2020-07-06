# Comic Viewer
A web application for reading comics made as a student project using *Spring Boot* with the main purpose of testing the [SimpleFlatMapper](https://github.com/arnaudroger/SimpleFlatMapper) library. It also has automated testing for all added functionality.

## Features
Due to the time constraints and the goal, the displayed site is pretty barebones, which basically means no styling.
For the same reason, it was not possible to complete all of the planned functionalities, but you can:
  * login/register
  * simple/advanced search comics
  * view comics page by page

All of this naturally means that the application is not production ready.

## Getting started
### Configuration
The application uses a in-memory H2 database. On startup the database schema is created using the *schema.sql* file and filled with data from *data.sql* file.
By default there are two users *user* and *admin* (passwords same as login) and some comics related data.

There are no images for the comics included with the code so they have to be provided if one wants to see them.
By default they are placed in */comics/* path which can be changed in *WebMvcConfig* class.
The actual path to various comic images is resolved by using the *path* column in *comics* and *chapters* tables.

Every comic can have a cover which is placed in */comics/{comic_path}/cover.jpg*, e.g. */comics/example_comic/cover.jpg*

Every chapter should have it's own folder in comic folder: */comics/{comic_path}/{chapter_path}*, e.g. */comics/example_comic/chapter_1_the_beggining/*

Finally, chapters can have images used as pages, named using numbers, counting from 1: */comics/{comic_path}/{chapter_path}/{1..n}.jpg*, e.g. */comics/example_comics/chapter_1_the_beggining/1.jpg*

All images have to have *jpg* extension.

### Building and running
To build and run the application you will need JDK 1.8.
To run it, simply enter the project directory and execute:
  * on Linux

```
$ ./gradlew bootRun
```
  * on Windows

```
$ ./gradlew.exe bootRun
```

By default the application will run on *localhost:8080*.

### Running the tests
To run tests, simply enter the project directory and execute:
  * on Linux

```
$ ./gradlew test
```
  * on Windows

```
$ ./gradlew.exe test
```

## Dependencies
  * [Spring Boot](https://github.com/spring-projects/spring-boot)
  * [Apache Commons](https://github.com/apache/commons-lang)
  * [SimpleFlatMapper](https://github.com/arnaudroger/SimpleFlatMapper)
  * [thymeleaf-extras-springsecurity](https://github.com/thymeleaf/thymeleaf-extras-springsecurity)
  * [H2 database](https://github.com/h2database/h2database)
  * [Junit 5](https://github.com/junit-team/junit5)
  * [Lombok](https://github.com/rzwitserloot/lombok)

## Database Relationship Diagram
![Image of database relationship diagram](/img/db_diagram.png)

## License
comic-viewer is licensed under *GNU AFFERO GENERAL PUBLIC LICENSE Version 3*
