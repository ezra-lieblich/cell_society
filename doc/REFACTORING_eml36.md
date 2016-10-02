###Lab
For my code, the major refactoring I did was updating our child cell class. 
Before, our parent cell class had a protected instance variable which was in charge of the color of
ourCell. Inside out child cells, we would access that instance variable and set the instance to the color we
wanted. This is bad because the parent class isnt doing anything and isnt aware that this change happened. What
I decided to change was making the color private and instead making a protected setter that changes the color.
Now the parent method is in charge of setting the color not the child.

The second change I made was in GridController. I made our code flexible in general by making our declarations
more general instead of specific types of Lists or Maps. THis provides our code with more flexibility. 
Furthermore, I made a extracted a method that creates all the view components of our sim. This is more flexible
so for in the future we want to add a grid we just call this method. It gives us more long term flexibility.

Here are some links to the commits that show the changes described above...

[pt1](https://git.cs.duke.edu/CompSci308_2016Fall/cellsociety_team01/commit/28680c8f3ebbed8d92d298f68192fed5008743a6)

[pt2](https://git.cs.duke.edu/CompSci308_2016Fall/cellsociety_team01/commit/a706efdc1875834416880241dbda168991b132e7)
