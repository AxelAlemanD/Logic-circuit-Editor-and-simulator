<div align="center">
<img src="https://user-images.githubusercontent.com/56330832/178573000-8d74f578-27ec-4fae-97db-c7856234d265.png" width="12%">
  <h1>Logic circuit: Editor and simulator</h1>
  
  <br>  
  
  ![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
  ![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
  ![AndroidStudio](https://img.shields.io/badge/Android_Studio-3DDC84?style=for-the-badge&logo=android-studio&logoColor=white)
  
</div>

<br>

Mobile application to generate logic circuits and simulate their operation

Features:
* Support for input controls (Switch)
* Support for logic gates (AND, OR, NOT, NAND, NOR)
* Results displayed by means of a graph
* Allows you to add N amount of items
* Allows connection between logic gates
* Allows you to move items freely on the canvas
* Simulates the output of the generated logic circuit

## Visuals

### Available Components
The application offers the following logic gates:
* AND gate
* OR gate
* NOT gate
* NAND gate
* NOR gate

It also offers input controls that allow changing the state of the logic gates:
* Switch
* Clock

https://user-images.githubusercontent.com/99099658/182475018-a11d1350-0823-4af4-8daa-dd7b5addaaf7.mp4


### Add Components
To add a component, simply click on the icon of the component you want to add

https://user-images.githubusercontent.com/99099658/182477991-a0927efd-04bd-4da7-88da-93cf645b5f68.mp4

#### Input Controls
Input controls allow the user to interact with them
* Switch
 
  In the case of the Switch, it changes state every time the user clicks on it.

  https://user-images.githubusercontent.com/99099658/182477369-1afd4e4c-44d2-4294-9bdf-ecaccdb8d2b0.mp4

* Clock

  In the case of the Clock, after adding it, it allows to define every how many seconds its state will change  
  
  https://user-images.githubusercontent.com/99099658/182477408-27e8a0fb-48d1-44c0-81de-e83d3b22b920.mp4


  
    Note: Each component has a unique identifier that is made up of the name of the component + a counter that increases as components are added. This facilitates the connection between components

### Connect components
Each component has an exit point (A); in the case of logic gates, these also have one or two entry points (B, C).

To make a connection between the components, click on the exit point (A), this will display a Dialog in which you can select a certain entry point from the available components.

https://user-images.githubusercontent.com/99099658/182478040-aaf1f4f8-ffcb-4f1d-931d-78f928bf8334.mp4

### Start simulation
To start the simulation, click on the play button.

* If I add at least one component, the simulation will start without problems, visually showing the change of state of the components; Blue = 1; Black = 0.

https://user-images.githubusercontent.com/99099658/182477553-9086feee-c152-4d79-8d05-04a041524ca4.mp4

* If you have not added any components, the simulation will fail to start and you will be prompted to add a component.

https://user-images.githubusercontent.com/99099658/182478724-a6a5e20e-96d8-494c-9c45-236d6eeb8fcf.mp4

### View graph in real time
The application allows to display the graph in real time once the simulation starts.

https://user-images.githubusercontent.com/99099658/182477697-6c16694e-d3ae-42e5-939b-f0e89642e545.mp4

### Change the sampling time
Additionally, you can modify the sampling time for the output graph.

To do this, click on the upper menu, then click on Settings and finally define every few seconds the output of the circuit will be taken to be graphed.

https://user-images.githubusercontent.com/99099658/182477791-9bdedd7f-83e8-4602-9299-e14de0f1ce8a.mp4

### Delete circuit
To clean the circuit, simply click on the button with the broom icon located on the right side of the screen, this will clean the added components as well as the generated graph.

https://user-images.githubusercontent.com/99099658/182477863-ba446f6a-4006-435e-b45a-08dd60d19766.mp4
