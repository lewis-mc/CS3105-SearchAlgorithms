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


public class PriceOperations extends AstarOperations {

    /*
     * This method gets all the moves for the price algorithm
     */
    public ArrayList<NodePrice> getAllMoves(NodePrice node) {
        int empty = 0;
    
        ArrayList<NodePrice> possibleMoves = new ArrayList<NodePrice>(1);
        Board board = node.getBoard();
    
        int rows = board.getRows();
        int columns = board.getColumns();
        int[][] cells = board.getCells();
        
        int emptyRow = board.getEmptyRow();
        int emptyColumn = board.getEmptyColumn();
    
        
        // try {
            if (cells[emptyRow][emptyColumn] == empty) {
                int[][] directions = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
                for (int[] dir : directions) {
                    int newX = emptyRow + dir[0];
                    int newY = emptyColumn + dir[1];
            
                    if (newX >= 0 && newX < rows && newY >= 0 && newY < columns) {
                        int moved = board.getCell(newX, newY);
                        Board newBoard = board.copy(); // Assuming copy() creates a new board
            
                        // Update the new board
                        newBoard.setCell(newX, newY, empty);
                        newBoard.setCell(emptyRow, emptyColumn, moved);
                        newBoard.setEmpty(newX, newY);
            
                        int costSoFar = node.getCostSoFar();
                        NodePrice newNode = new NodePrice(newBoard, node, moved, costSoFar + moved);
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
     * Search for the solution using the price search algorithm
     */
    public String PriceSearch (Board board) {
            String output = "";
            boolean searchEnd = false;
            Set<Board> visitedStates = new HashSet<>();

            Comparator<NodePrice> comp = Comparator.comparingInt(NodePrice::getTotalPrice);
            PriorityQueue<NodePrice> pq = new PriorityQueue<>(comp);

            if (!isSolvable(board)) {
                return "0";
            }

            if (goalState(board)) {
                return "1 0";
            }

            NodePrice node = new NodePrice(board, null, 0, 0);

            pq.add(node);
            visitedStates.add(board);

            long start = System.currentTimeMillis();
            long end = start + 25 * 1000;

            while (!searchEnd && !pq.isEmpty()) {
                if (System.currentTimeMillis() > end) {
                    return "-1";
                }

                NodePrice currentNode = pq.poll();
                Board currentBoard = currentNode.getBoard();

                ArrayList<NodePrice> possibleMoves = getAllMoves(currentNode);
                for (NodePrice newNode : possibleMoves) {
                    Board newBoard = newNode.getBoard();
                    if (!visitedStates.contains(newBoard)) {
                        visitedStates.add(newBoard);

                        if (goalState(newBoard)) {
                            searchEnd = true;
                            ArrayList<Integer> moves = new ArrayList<>(1);
                            int cost = newNode.getCostSoFar();

                            while (newNode.getParent() != null) {
                                moves.add(newNode.getMoved());
                                newNode = newNode.getParent();
                            }

                            output = "1 " + moves.size() + " ";

                            for (int i = moves.size() - 1; i >= 0; i--) {
                                output += moves.get(i) + " ";
                            }

                            output += cost;
                        } else { 
                            newNode.setCostSoFar(currentNode.getCostSoFar() + newNode.getMoved());
                            pq.add(newNode);
                        }
                    }
                }
            }

            return output;
        }

        /*
         * Calculate the total manhattan distance of a board with the price being accounted for as well
         */
    public int calculateTotalPriceManhattanDistance(Board board) {
        int manhattanDistance = 0;
        
        for (int x = 0; x < board.rows; x++) {
            for (int y = 0; y < board.columns; y++) {
                int currentValue = board.cells[x][y];
                
                if (currentValue != 0) {
                    int targetRow = (currentValue - 1) / board.columns;
                    int targetColumn = (currentValue - 1) % board.columns;
                    
                    manhattanDistance += Math.abs(targetRow - x) + Math.abs(targetColumn - y) * currentValue;
                }
            }
        }
        
        return manhattanDistance;
    }
    
    
}
