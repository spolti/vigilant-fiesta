# Project-1 - 8 puzzle solver

This project solves the 8 puzzle game using the A* algorithm with two different heuristics: 
- Manhattan distance
- Pieces not in place.

The execution performs the following steps:

- Generate a randon initial state (or from user's input)
- Generate a randon goal state (or from user's input)
- Check if the puzzle is solvable
  - By checking the inversions of the initial and goal states
- Starting the A* algorithm
  - Using the Manhattan distance heuristic
  - Using the pieces not in place heuristic

## How to run

This example uses the Quarkus framework. To run the application, you wil need:
- Java 11+
- Maven 3.6.2+

Starting the application in Dev mode:
```bash
mvn clean compile quarkus:dev
```

Building to run as a java package:
```bash
mvn clean package
java -jar target/quarkus-app/quarkus-run.jar
```

Or, build the native binary for best performance:
```bash
mvn clean package -Pnative
./target/trabalho-1-8-puzzle-1.0-SNAPSHOT-runner
```


# Example of execution:

```bash
java -jar target/quarkus-app/quarkus-run.jar
Goal State
1 | 8 | 2 | 
0 | 6 | 4 | 
3 | 7 | 5 | 
Initial State
1 | 3 | 7 | 
8 | 4 | 5 | 
2 | 0 | 6 | 

########################################################################

Solvable, both have odd parity: 11 - 11
Solving using Manhattan distance heuristic...
Solution
1 | 8 | 2 | 
0 | 6 | 4 | 
3 | 7 | 5 | 
Final Cost: 26
Iterations: 3326
Manhattan distance heuristic execution time: 42ms

########################################################################

Solving using pieces not in place heuristic...
Solution
1 | 8 | 2 | 
0 | 6 | 4 | 
3 | 7 | 5 | 
Final Cost: 26
Iterations: 44888
Pieces not in place heuristic execution time: 170ms
########################################################################
```