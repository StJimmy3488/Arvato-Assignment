# Arvato-Assignment
## Project Development

This assignment project was developed using Clean code best practices as well using several programming design patterns.
Such as **Dependency Injection/Injection of Control** (using Spring framework), **MVC** pattern to separate application's concerns, **Singleton** pattern to ensure a class has only one instance,
and provide a global point of access to it. As well as many other.   

## Project Task
This is a console based simulation of a toy robot who can move on a 2D surface while avoiding any move that can make the toy robot to fall off the surface.
Commands to be executed:

## Implementation

This is maven based project written using the following:

- JDK17
- Maven 4.0.0
- Spring
- Spring Data JPA 2.6.0
- Spring Boot 2.6.2
- H2 Database
- Lombok

<ul>
<li>PLACE X,Y,F</li>
<li>MOVE</li>
<li>LEFT</li>
<li>RIGHT</li>
<li>REPORT</li>
</ul>
<h3>Command Description:</h3>
<ul style="list-style-type:none;">
<li>PLACE will put the toy robot on the table in position X,Y and facing NORTH,
SOUTH, EAST or WEST</li>
<li>The origin (0,0) can be considered to be the SOUTH WEST corner</li>
<li>The first valid command to be executed is a PLACE command, after that, any
sequence of commands may be issued, in any order, including another PLACE
command</li>
<li>The application should dismiss all commands in the sequence until a valid PLACE
command has been executed</li>
<li>MOVE will move the toy robot one unit forward in the direction it is currently
facing</li>
<li>LEFT and RIGHT will rotate the robot 90 degrees in the specified direction without
changing the position of the robot</li>
<li>REPORT will announce the X,Y and F of the robot</li>
</ul>

## TESTING
(Have used Postman tool and URL commands for testing)

Example 1:

PLACE 0,0,NORTH: http://localhost:8081/place?x=0&y=0&f=NORTH

MOVE: http://localhost:8081/1/move

REPORT: http://localhost:8081/report

Output: [Robot(robotId=1, xCoord=0, yCoord=1, facing=North)]

Example 2:

PLACE 1,2,EAST: http://localhost:8081/place?x=1&y=2&f=EAST

MOVE: http://localhost:8081/1/move

MOVE: http://localhost:8081/1/move

LEFT: http://localhost:8081/1/left

MOVE: http://localhost:8081/1/move

REPORT: http://localhost:8081/report

Output: [Robot(robotId=1, xCoord=3, yCoord=3, facing=North)]

## Future Improvements

- Add more test coverage for every command
- JPA query history implimentation
- More Refactoring
- Exception Handler





 
