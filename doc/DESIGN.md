CompSci 308: Cell Society Brainstorm
===================

> This is the link to the assignment: [CellSociety](http://www.cs.duke.edu/courses/compsci308/fall16/assign/02_cellsociety/)


## INTRODUCTION

This project involves designing several different cell automata simulations. Specifically, this project will build simulations such as [Schelling’s model of segregation](http://nifty.stanford.edu/2014/mccown-schelling-model-segregation/), [Wa-Tor World model of predator-prey relationships](http://nifty.stanford.edu/2011/scott-wator-world/), [Spreading of Fire](http://nifty.stanford.edu/2007/shiflet-fire/). In order to successfully build these simulations,  we will have to display understanding of 2D arrays, random number generation, user interface design, algorithm design, JavaFx, XML, class inheritance and many other important topics in software design and implementation.
Our primary design goal is to seamlessly transition between different cell automata simulations. This includes the ability to easily change the rules of interaction between cells, the appearance of cells, and the appearance of the grid, among other things. Other design goals include laying down the foundation of a friendly user interface that can be easily tailored to the user’s needs.
In order to achieve maximum flexibility by providing the user with as many options to modify the simulations as possible, our architecture will be completely open. Third-parties will be able to add their own cell types, grid types, cell interactions, and many other factors that will maximize the potential of our Cell Automata simulator.

## OVERVIEW

[Class Structure Picture](https://git.cs.duke.edu/CompSci308_2016Fall/cellsociety_team01/blob/master/doc/design_imgs/classStructure.jpg)

[Another Class Structure Picture](https://git.cs.duke.edu/CompSci308_2016Fall/cellsociety_team01/blob/master/doc/design_imgs/classStructure1.jpg)

### CLASSES

### MainMenu

View that contains buttons to select a simulation and then switches to simulation screen

### Grid Controller

Controller class

Shows the main menu and reads from corresponding XML file

Starts the grid animation.

In charge of stepping.


### GridLogic

Parent class would contain simple methods that all simulations need to interact with the grid. It will access the Grid class to manipulate the grid. It will call each Cell’s method that determines the next state and update the Grid accordingly depending on the simulations specific logic 

**Subclasses**

Each simulation will have its own class that extends GridLogic. They will override or add methods that are specific for the interactions of each sim.

### Grid View

Contains the Grid Class and updates the UI based on the status of the Grid. Also contains an instance of the ToolBar class


### Toolbar

Toolbar contains the UI for all the buttons to change the parameters of the simulation and handles input that communicates with GridController

### Grid

Model class that has a 2D array of all the different type of cells. It will have getters to provide access to the array and also basic methods to manipulate, add, and delete from the array. Also has a list of empty spaces in Grid

### Cell

Parent class that has a state representation and calculate next state

###Slider Properties
Each simulation will have Slider in UI to alter parameters of simulation. This class Creates buttons, sliders for it

### Cell Graph
Plots the data of number of cells into a line chart
### XML Reader
Reads in the XML File chosen
### GridFactory
Makes the grid based off of the parameters passed from XML Reader

**Subclasses**

Each simulation would have its own package of states and overrides the next state method to determine how to calculate its next state. GridLogic classes will call its next state method and pass in its arguments

*Burning Tree*

*No Tree*

*Regular Tree*


*Shark*

*Fish*


*X*

*O*

Implement interaction between the classes within the subclasses.


### Action

**Subclasses**

*MoveTree*

*MoveWater*

*MoveXO*

All of these contain the algorithms for how Trees, Sharks/Fish, and XOs move and 
### XML Reader
Initializes the file and creates the Grid object. 
Fills in the grid depending on the XML File and returns to GridController

## USER INTERFACE


[Figure 1: Main Menu Screen](https://git.cs.duke.edu/CompSci308_2016Fall/cellsociety_team01/blob/master/doc/design_imgs/CellAutomataMainScreenImage.JPG)
This is our initial idea for a main menu screen. The main menu screen will be initialized as a pop up window. The user will be taken to the simulation that is clicked via a scene switch.

[Figure 2: In Simulation Screen](https://git.cs.duke.edu/CompSci308_2016Fall/cellsociety_team01/blob/master/doc/design_imgs/CellAutomataSimScreenImage.JPG)
This is our idea for the in simulation screen. The window will occupy the full screen, but the grid will not. The user will be able to modify the x and y dimensions of the grid by typing integer values into text boxes by XSIZE and YSIZE and pressing/clicking enter. The grid will grow or shrink accordingly. This function, and ALL TEXTBOX functions, will not be available while a simulation is running -- they will only be available after pressing the restart button/before starting the simulation. If the user should enter an invalid value (for example, a word instead of a number into XSIZE or YSIZE), an alert box will appear in the center of the window with a message telling the user allowed input conditions. The restart, start, stop, and main menu will all be buttons that the user can click. If the user presses main menu, the simulation will pause, an alert box will pop up with a yes and no question of: Are you sure you want to return to the main menu.
Overall there will be two scenes: the main menu scene and the simulation scene.

[Figure 3: Both Scenes]( https://git.cs.duke.edu/CompSci308_2016Fall/cellsociety_team01/blob/master/doc/design_imgs/CellAutomataOverallImage.JPG)



## DESIGN DETAILS

**USE CASES**

*Apply the rules to a middle cell: set the next state of a cell to dead by counting its number of neighbors using the Game of Life rules for a cell in the middle (i.e., with all its neighbors).*

GRIDLOGIC calls cell.performLogic method, which has CELL calculate the next state for the middle cell. Method in GRIDLOGIC then sets the cell’s next state in the grid. GRIDVIEW then displays the changed state of the middle cell.


*Apply the rules to an edge cell: set the next state of a cell to live by counting its number of neighbors using the Game of Life rules for a cell on the edge (i.e., with some of its neighbors missing).*

Same as above.


*Move to the next generation: update all cells in a simulation from their current state to their next state and display the result graphically.*

GRIDCONTROLLER contains the step function, so every time the cells need to be updated from their similar state, GRIDLOGIC and GRIDVIEW will run in synchronization with GRID CONTROLLER when step function increments. 


*Set a simulation parameter: set the value of a parameter, probCatch, for a simulation, Fire, based on the value given in an XML file.*

XMLREADER will read initial conditions for the grid, GRID will initialize based off the data from XMLREADER.


*Switch simulations: use the GUI to change the current simulation from Game of Life to Wator.*

MAINMENU will be responsible for choosing the different simulations to switch to. When in game, TOOLBAR provides path back to MAINMENU to switch simulations.




## DESIGN CONSIDERATIONS

The first design decision we had to make was where to determine the next state of the cell. We were debating whether to perform that in the Cell class or our GridLogic class. Putting in in our logic class would make it easier to write the methods since it would have easier access to the grid and it’s neighbors. This would also make the Cell class a lot simpler and have less sub-classes. However, we ultimately decided to put the method in cell class. This makes the logic class a lot cleaner because we don’t need an if tree figuring out what state it is in and then performing logic based off its state. Instead we just go through each cell and call Cells handle next state method.
Another design choice we made was to have a Grid class have methods to update the grid instead of putting those methods inside GridLogic. We thought that since have a separate GridView class that does depend on Grid, that putting in those methods in GridLogic would be too much dependence. 
Separate Toolbar from GridView was another design choice we made. Originally we thought it would make sense to put the whole UI view component in one class. However, since the Toolbar is going to be handling the parameters of the Grid and GridView we decided to create a separate class for this. Also this is good because now Toolbar interacts with the GridLogic and GridView doesn’t, so this separates the dependencies. 

We originally had XML Reader both read the XML file and then create the grid from there in the class. This we thought was originally a good idea because that way, we wouldnt need to pass all this information around to controller and then back to some other class. However, we realized after the second sprint this was not the best solution. The XML Reader was huge and had big if trees to determine which simulation and which grid to 
make. We ultimately decided to make a GridFactory that made the grid and XML reader called it to make the grid. The bad part is now XML Reader needs to pass all these parameters to Factory to make the grid but we reconciled this by having a map of properties where the properties file maintains consistency of keys. Also, now to reset graph slider properties just call the Factory class rather then XML Reader which doesnt make much sense. 
## ADD New Simulation
To add a new simulation you probably need to first make an xml file to specify the parameters and follow the documentation in the readme. 
Next you need to add the sim in the XML Reader so it can choose the right GridFactory which is next class you need to make. You need to create the 
initial grid here based off of the specifications passed from XML. Next you need to create the Cells associated with the simulation and specify the 
color and how the cell determines its next state. You need to add a Logic class that updates the grid each step and finally you need a slider class for
the view to update the parameters. All these classes extend a parent class that has clear implementation and tells you what methods you need to have in each class.
## TEAM RESPONSIBILITIES

Classes Everyone Will Work On:
GridLogic, Cell, GridController

Eric:
Grid, Wator,  MainMenu

Ezra:
GridView,Toolbar, XO, Grid Controller, SliderProperties

Chris:
XML, Tree, Life

