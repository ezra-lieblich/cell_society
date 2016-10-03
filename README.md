# cellsociety 

Duke CompSci 308 Cell Society Project
Outline
Main class- Where the user can go in a select a simulation
Grid class- A visual component that shows the all the elements and is a 2d array.


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