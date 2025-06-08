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



public class AstarOperations extends ManhattanOperations {

    /*
     * This method gets all the moves for the A* algorithm
     */
    public ArrayList<NodeAstar> getAllMoves(NodeAstar node) {
        int empty = 0;
    
        ArrayList<NodeAstar> possibleMoves = new ArrayList<NodeAstar>();
        Board board = node.getBoard();
    
        int rows = board.getRows();
        int columns = board.getColumns();
        int[][] cells = board.getCells();
        int emptyX = -1, emptyY = -1; // Initialize empty cell position
    
        // Find the position of the empty cell
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < columns; y++) {
                if (cells[x][y] == empty) {
                    emptyX = x;
                    emptyY = y;
                    break;
                }
            }
        }
    
        // Define possible move directions (up, down, left, right)
        int[][] directions = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
    
        for (int[] dir : directions) {
            int newX = emptyX + dir[0];
            int newY = emptyY + dir[1];
    
            if (newX >= 0 && newX < rows && newY >= 0 && newY < columns) {
                int moved = board.getCell(newX, newY);
                Board newBoard = board.copy(); // Assuming copy() creates a new board
    
                // Update the new board
                newBoard.setCell(newX, newY, empty);
                newBoard.setCell(emptyX, emptyY, moved);
    
                int movesMade = node.getMovesSoFar();
                NodeAstar newNode = new NodeAstar(newBoard, node, moved, movesMade + 1);
                possibleMoves.add(newNode);
            }
        }
    
        return possibleMoves;
    }

        public String AstarSearch(Board board) {
            String output = "";
            boolean searchEnd = false;
            Set<Board> visitedStates = new HashSet<>();

            Comparator<NodeAstar> comp = Comparator.comparingInt(NodeAstar::getTotalDistance);
            PriorityQueue<NodeAstar> pq = new PriorityQueue<>(comp);

            if (!isSolvable(board)) {
                return "0";
            }

            if (goalState(board)) {
                return "1 0";
            }

            NodeAstar node = new NodeAstar(board, null, 0, 0);

            pq.add(node);
            visitedStates.add(board);

            long start = System.currentTimeMillis();
            long end = start + 25 * 1000;

            while (!searchEnd && !pq.isEmpty()) {
                if (System.currentTimeMillis() > end) {
                    return "-1";
                }

                NodeAstar currentNode = pq.poll();
                Board currentBoard = currentNode.getBoard();

                ArrayList<NodeAstar> possibleMoves = getAllMoves(currentNode);
                for (NodeAstar newNode : possibleMoves) {
                    Board newBoard = newNode.getBoard();
                    if (!visitedStates.contains(newBoard)) {
                        visitedStates.add(newBoard);

                        if (goalState(newBoard)) {
                            searchEnd = true;
                            ArrayList<Integer> moves = new ArrayList<>();

                            while (newNode.getParent() != null) {
                                moves.add(newNode.getMoved());
                                newNode = newNode.getParent();
                            }

                            output = "1 " + moves.size() + " ";

                            for (int i = moves.size() - 1; i >= 0; i--) {
                                output += moves.get(i) + " ";
                            }
                        } else {
                            newNode.setAstarScore(calculateTotalManhattanDistance(newBoard) + newNode.getMovesSoFar());
                            pq.add(newNode);
                        }
                    }
                }
            }

            return output;
        }


    
}
