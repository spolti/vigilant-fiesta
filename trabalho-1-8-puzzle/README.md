# Project-1 - 8 puzzle solver



# Example of execution:

```bash
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