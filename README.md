# cellsociety 

Duke CompSci 308 Cell Society Project


Group Names- Ezra Lieblich, Chris Lu, Eric Song
Date: Started- 9/15 Finished- 10/2
Hours Worked: Ezra- ~40 hrs,
Roles:
Ezra- In charge of all XO logic and simulation parts. Worked primarily on UI classes like CellGraph, Toolbar,
SliderProperties and all their respecitve children. Also worked on GridView parent class. Refactored and 
worked with GridController to handle interaction between UI and simulation.  

Resources Used- JavaFX documentation, StackOverflow
Files used to start program- Main.java
Files used to test project- data/xml has all of our test files for sims
Resources files used- resource/ contains properties files for strings used in view as well as a css file for 
formatting

Known Bugs- We have not finished implementing feature that allows XML to specify individual locations so 
GameOfLifeH.xml does not work currently

Extra Features- User has the ability to delete simulations if they want and view updates and shifts everything
XML  
  
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


Impressions:
Ezra: I really enjoyed this assignment and the challenges it presented. In the future I think having a balance
between Sprint 2 and 3 would be better as I thought Sprint 3 had significantly more features to implement.
Also I know there was purposely a lot of features and it was hard to do all them so maybe you could reccomend
good features to work on that would affirm concepts we are learning in class the most.