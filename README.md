# cellsociety 

**Contributors**  
Ezra Lieblich  
Christopher Lu  
Eric Song  
  
**Time Spent**  
Ezra Lieblich: 9/15/16-10/3/16, 40 hours  
Christopher Lu: 9/15/16-10/3/16, 30 hours  
Eric Song: 9/15/16-10/3/16, ~30 hours  
  
**Role/Contributions**  
Ezra Lieblich: In charge of all XO logic and simulation parts. Worked primarily on UI classes like CellGraph, Toolbar,
SliderProperties and all their respecitve children. Also worked on GridView parent class. Refactored and 
worked with GridController to handle interaction between UI and simulation.  
Christopher Lu: Game of Life, Spread of Fire, XML parsing and XML files, Error checking and alert boxes for XML files.  
Eric Song: Designed overall initial structure of project with MVC in mind, all logic of predator-prey (WaTor World) simulation,
backend and visualizations of square, triangular, and hexagonal grids, backend of finite and toroidal grid, exception handling
and default value assignment for all missing parameters for simulations, factories for each type of simulation, multiple neighbor types
(cardinal, all, or diagonal), some work with XML files, GridController and interfacing with logic classes.  
  
**Resources Used**  
[XML Parsing, Factories, etc](https://git.cs.duke.edu/CompSci308_2016Fall/example_xml)  
[XML Files](http://www.w3schools.com/xml/xml_examples.asp)  
[Alert Boxes](http://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html)  
[Game of Life](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life)  
[Spread of Fire](http://nifty.stanford.edu/2007/shiflet-fire/)  
JavaFX documentation, StackOverflow  
  
Duke CompSci 308 Cell Society Project  
  
**Files Used To Start the Project**  
Main.java  
  
**Files Used To Test the Project**  
NotAnXML.txt: Should throw error saying that file is not an XML file.  
data/xml has all of our test files for sims  
  
Group Names- Ezra Lieblich, Chris Lu, Eric Song  
Date: Started- 9/15 Finished- 10/2 
Hours Worked: Ezra- ~40 hrs, Christopher- 30 hrs, Eric- ~30 hours  
Roles:  
Ezra- In charge of all XO logic and simulation parts. Worked primarily on UI classes like CellGraph, Toolbar,
SliderProperties and all their respecitve children. Also worked on GridView parent class. Refactored and 
worked with GridController to handle interaction between UI and simulation.  
Christopher Lu: Game of Life, Spread of Fire, XML parsing and XML files, Error checking and alert boxes for XML files.  
Eric Song: Designed overall initial structure of project with MVC in mind, all logic of predator-prey (WaTor World) simulation,
backend and visualizations of square, triangular, and hexagonal grids, backend of finite and toroidal grid, exception handling
and default value assignment for all missing parameters for simulations, factories for each type of simulation, multiple neighbor types
(cardinal, all, or diagonal), some work with XML files, GridController and interfacing with logic classes.  
  
Resources Used- JavaFX documentation, StackOverflow  
Files used to start program- Main.java  
  
**Required Data/Resource Files**  
resource/ contains properties files for strings used in view as well as a css file for 
formatting  
  
**Known Bugs**  
We have not finished implementing feature that allows XML to specify individual locations so 
GameOfLifeH.xml does not work currently  
  
**Extra Info/Easter Eggs**  
User has the ability to delete simulations if they want and view updates and shifts everything
XML .  
  
General Tags for all Simulations  
  
simulation_name: Game Of Life, WaTor World, Spread Of Fire, XO Segregation  
simulation_title: anything  
simulation_author: anything  
rows: integer greater than 0  
columns: integer greater than 0  
shape: squ(default), tri, hex  
bounds: finite(default), toroidal  
neighborType: all(default), cardinal, diagonal  
  
  
Conway's Game of Life  
  
percentAlive: decimal between 0 and 1  
  
  
Spreading of Fire  
  
probCatch: decimal between 0 and 1  
percentTree: decimal between 0 and 1  
percentBurn: decimal between 0 and 1  
  
  
Segregation  
  
percentX: decimal between 0 and 1  
percentO: decimal between 0 and 1  
similarPercentage: decimal between 0 and 1  
  
  
WaTor World  
  
percentFish: decimal between 0 and 1  
percentShark: decimal between 0 and 1  
fishReproduce: integer greater than 0  
sharkDeath: integer greater than 0  
sharkReproduce: integer greater than 0  

**Impressions Of Assignment**
Ezra: I really enjoyed this assignment and the challenges it presented. In the future I think having a balance
between Sprint 2 and 3 would be better as I thought Sprint 3 had significantly more features to implement.
Also I know there was purposely a lot of features and it was hard to do all them so maybe you could reccomend
good features to work on that would affirm concepts we are learning in class the most.  
  
Christopher: CellSociety was a new and interesting experience for me. I learned a lot about class interaction and front end and back end interaction, and I felt that this assignment really showed off the power and importance of controllers.
In the future, this assignment could be improved by either providing less new features to deal with in Sprint 3, or unveiling more of the new features in Sprint 2 to provide more of a balance between the two weeks.  
  
Eric: I learned a great deal about working on a project on a team, as it
presents many different challenges such as communication and integration of
code, which were not present in the first assignment. Despite Duvall's constant
talk of never sacrificing quality of code for amount of features, I believe
we would improve as software developers much more if we were given a smaller list of 
features and much more emphasis on strictly following the rules of good software design.
