import java.util.ArrayList;
import java.util.PriorityQueue;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.HashSet;

public class ManhattanOperations {

    /*
     * This method takes a string input and returns a board object
     */
    public Board interpretInput(String input) {
        
        String[] boardData = input.split(" ");

        int n = Integer.parseInt(boardData[0]);
        int m = Integer.parseInt(boardData[1]);

        int[][] cells = new int[n][m];
        int count = 0;
        int emptyRow = 0;
        int emptyColumn = 0;

        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                cells[x][y] = Integer.parseInt(boardData[count+2]);
                count++;
                if (cells[x][y] == 0) {
                    emptyRow = x;
                    emptyColumn = y;
                }
            }
        }

        Board board = new Board(n, m, cells);
        board.setEmpty(emptyRow, emptyColumn);

        return board;
    }

    public ArrayList<Node> getAllMoves(Node node) {

        int empty = 0;
    
        ArrayList<Node> possibleMoves = new ArrayList<Node>(1);
        Board board = node.getBoard();
    
        int rows = board.getRows();
        int columns = board.getColumns();
        int[][] cells = board.getCells();
        int emptyRow = board.getEmptyRow();
        int emptyColumn = board.getEmptyColumn();
        
    
        //try {
            
            if (cells[emptyRow][emptyColumn] == empty) {
                // Define possible move directions (up, down, left, right)
                int[][] directions = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };

                for (int[] dir : directions) {
                    int newX = emptyRow + dir[0];
                    int newY = emptyColumn + dir[1];

                    if (newX >= 0 && newX < rows && newY >= 0 && newY < columns) {
                        Board newBoard = board.copy();
                        int moved = newBoard.getCell(newX, newY);

                        // Update the new board
                        newBoard.setCell(newX, newY, empty);
                        newBoard.setCell(emptyRow, emptyColumn, moved);
                        newBoard.setEmpty(newX, newY);

                        Node newNode = new Node(newBoard, node, moved);
                        possibleMoves.add(newNode);
                        
                    }
                }
            }
        // } catch (CloneNotSupportedException e) {
        //     e.printStackTrace();
        // }
        
    
        return possibleMoves;
    }
    
   /*
    * Checks if the board is in the goal state
    */
    public boolean goalState(Board board) {
        boolean goalState = true;
        int[][] cells = board.getCells();
        int count = 1;

        for (int x = 0; x < board.getRows(); x++) {
            for (int y = 0; y < board.getColumns(); y++) {
                int correctValue = (board.getColumns() * x) + y + 1;
                if (correctValue != board.getCell(x, y) && board.getCell(x,y) != 0) {
                    return false;
                }
            }
        }
        return goalState;
    }

    /*
     * Checks if the board is solvable.
     */
    public boolean isSolvable(Board board) {

        int[] board1D = make1DArray(board.getCells());
        int inversions = countInversions(board1D);
        int rows = board.getRows();
        int columns = board.getColumns();
        int emptyToBottom = board.getEmptyCellRow();

        
        if (rows == columns) { 
            if (rows % 2 == 1) { 
                return inversions % 2 == 0;
            } else { 
                if (emptyToBottom % 2 == 0) {
                    return inversions % 2 == 1;
                } else {
                    return inversions % 2 == 0;
                }
            }
        } else { 
            if (columns % 2 == 1) { 
                return inversions % 2 == 0;
            } else { 
                if (emptyToBottom % 2 == 0) {
                    return inversions % 2 == 1;
                } else {
                    return inversions % 2 == 0;
                }
            }

        }
    }


    /*
     * Converts a 2D array to a 1D array - used for counting inversions
     */
    public int[] make1DArray(int[][] cells) {
        int[] board1D = new int[cells.length * cells[0].length];
        int count = 0;

        for (int x = 0; x < cells.length; x++) {
            for (int y = 0; y < cells[0].length; y++) {
                board1D[count] = cells[x][y];
                count++;
            }
        }

        return board1D;
    }

    /*
     * Counts the number of inversions in a 1D array - used for checking if the board is solvable
     */
    public int countInversions(int[] board1D) {
        int inversions = 0;

        for (int i = 0; i < board1D.length - 1; i++) {
            for (int j = i + 1; j < board1D.length; j++) {
                if (board1D[i] > board1D[j] && board1D[i] != 0 && board1D[j] != 0) {
                    inversions++;
                }
            }
        }

        return inversions;
    }

    /*
     * Finds the row of the empty cell - used for checking if the board is solvable
     */
    public int findEmptyCellRow(Board board) {
        int empty = 0;
        int[][] cells = board.getCells();

        for (int x = 0; x < board.getRows(); x++) {
            for (int y = 0; y < board.getColumns(); y++) {
                if (cells[x][y] == empty) {
                    return x;
                }
            }
        }

        return 0;
    }

    /*
     * Calculates the total manhattan distance of a board
     */
    public int calculateTotalManhattanDistance(Board board) {
        int manhattanDistance = 0;
        
        for (int x = 0; x < board.rows; x++) {
            for (int y = 0; y < board.columns; y++) {
                int currentValue = board.cells[x][y];
                
                if (currentValue != 0) {
                    int targetRow = (currentValue - 1) / board.rows;
                    int targetColumn = (currentValue - 1) % board.columns;
                    
                    manhattanDistance += Math.abs(targetRow - x) + Math.abs(targetColumn - y);
                }
            }
        }
        
        return manhattanDistance;
    }
    
    /*
     * Performs the Best First Search algorithm on a board using the Manhattan Distance heuristic
     */
    public String BestFirstSearch(Board board) {
        
            String output = "";
            boolean searchEnd = false;
            Set<Board> visitedStates = new HashSet<>();

            Comparator<Node> comp = Comparator.comparingInt(Node::getTotalManhattanDistance);
            PriorityQueue<Node> pq = new PriorityQueue<>(comp);

            if (!isSolvable(board)) {
                return "0";
            }

            if (goalState(board)) {
                return "1 0";
            }

            Node node = new Node(board, null, 0);

            pq.add(node);
            visitedStates.add(board);

            long start = System.currentTimeMillis();
            long end = start + 25 * 1000;

            while (!searchEnd && !pq.isEmpty()) {
                if (System.currentTimeMillis() > end) {
                    return "-1";
                }

                Node currentNode = pq.poll();
                Board currentBoard = currentNode.getBoard();

                ArrayList<Node> possibleMoves = getAllMoves(currentNode);
                possibleMoves = getAllMoves(currentNode);
                for (Node newNode : possibleMoves) {
                    Board newBoard = newNode.getBoard();
                    if (!visitedStates.contains(newBoard)) {
                        visitedStates.add(newBoard);

                        if (goalState(newBoard)) {
                            searchEnd = true;
                            ArrayList<Integer> moves = new ArrayList<>(1);

                            while (newNode.getParent() != null) {
                                moves.add(newNode.getMoved());
                                newNode = newNode.getParent();
                            }

                            output = "1 " + moves.size() + " ";

                            for (int i = moves.size() - 1; i >= 0; i--) {
                                output += moves.get(i) + " ";
                            }
                        } else {
                            newNode.getBoard().setManhattanDistance(calculateTotalManhattanDistance(newBoard));
                            pq.add(newNode);
                        }
                    }
                }
            }

            return output;
        }

}
