public class Node {

    Board board;
    Node parent;
    int moved;

    public Node(Board board, Node parent, int moved) {
        this.board = board;
        this.parent = parent;
        this.moved = moved;
    }

    public Node getParent() {
        return this.parent;
    }

    public Board getBoard() {
        return this.board;
    }

    public int getMoved() {
        return this.moved;
    } 

    public int getTotalManhattanDistance() {
        return this.board.getTotalManhattanDistance();
    }

    public void setManhattanDistance(int value) {
        this.board.setManhattanDistance(value);
    }

    public int getTotalPriceManhattanDistance() {
        return this.board.getTotalPriceManhattanDistance();
    }

    public void setMoved(int value) {
        this.moved = value;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    
    

}
