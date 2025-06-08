# CS3105-SearchAlgorithms
For this practical I was asked to implement and compare different search algorithms on sliding block puzzles. The different search algorithms were: Best-First Search using the Manhattan distance as a heuristic, Best-First as well but using Total Distance as the heuristic.

In the 'CS3105-P1' directory you can execute './build.sh' which will compile and create the executable files (manhattan, astar, price).

After executing './build.sh', to execute:
    - Manhattan: './manhattan'
    - A*: './astar'
    - Price: './price'

Then place the <input> in the stdin with the following format 'n m x1 x2 x3 ... xF'. 
    - n is the number of rows
    - m is the number of columns
    - x_ is a any integer value in this range {0,1,...,xF}
    - 0 is used to represent the empty space

To execute all the stacschecks run 'stacschecks Tests'
(Important - the stacschecks are run from the 'P1' directory)

To collect the data I ran './data' and I exported the results to the dataOutput directory.