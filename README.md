# Tic tac toe

## About
It is implementation of classic board game in console. To run App you need JDK in version 1.8, Git and Maven.  
Implemented during recruitment process to [Grid Dynamics](http://griddynamics.com).  

## Installation
On Unix simply run commands
```{r, engine='bash', count_lines}
git clone https://github.com/jakubskiba/tttgrid.git
cd tttgrid
sh install.sh
```

## Run
On Unix simply run commands
```{r, engine='bash', count_lines}
sh run.sh
```

## Configuration
Application need to be configured in ```src/main/resources/application.properties``` file.  

Default configuration:

```
ttt.listOfPlayers=human,human,ai-hard
ttt.minBoardSize=3
ttt.maxBoardSize=9
```

There are three parameters:
* ttt.listOfPlayers  
determines amount and kind(strategy) of players will.  
Possible options: 
  * human
  * random
  * ai-easy
  * ai hard
* ttt.minBoardSize  
Minimal size of board.
* ttt.maxBoardSize  
Maximum size of board.

After changing configuration please recompile project with:
```{r, engine='bash', count_lines}
sh install.sh
```
