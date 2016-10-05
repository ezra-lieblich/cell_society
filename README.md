# cellsociety 

**Contributors**
Ezra Lieblich
Christopher Lu
Eric Song

**Time Spent**
Ezra Lieblich: 9/15/16-10/3/16, 40 hours
Christopher Lu: 9/15/16-10/3/16, 30 hours
Eric Song:

**Role/Contributions**
Ezra Lieblich: In charge of all XO logic and simulation parts. Worked primarily on UI classes like CellGraph, Toolbar,
SliderProperties and all their respecitve children. Also worked on GridView parent class. Refactored and 
worked with GridController to handle interaction between UI and simulation.  
Christopher Lu: Game of Life, Spread of Fire, XML parsing and XML files, Error checking and alert boxes for XML files.
Eric Song:

**Resources Used**
[XML Parsing, Factories, etc](https://git.cs.duke.edu/CompSci308_2016Fall/example_xml)
[XML Files](http://www.w3schools.com/xml/xml_examples.asp)
[Alert Boxes](http://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html)
[Game of Life](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life)
[Spread of Fire](http://nifty.stanford.edu/2007/shiflet-fire/)
JavaFX documentation, StackOverflow

Duke CompSci 308 Cell Society Project
Outline  
Main class- Where the user can go in a select a simulation  
Grid class- A visual component that shows the all the elements and is a 2d array.  

**Files Used To Start the Project**
Main.java

**Files Used To Test the Project**
NotAnXML.txt: Should throw error saying that file is not an XML file.
data/xml has all of our test files for sims

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