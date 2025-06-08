public class NodeAstar extends Node {

    int movesSoFar;
    int astarScore;

    public NodeAstar(Board board, Node parent, int moved, int movesSoFar) {
        super(board, parent, moved);
        this.movesSoFar = movesSoFar;
    }

    public int getMovesSoFar() {
        return this.movesSoFar;
    }

    public void setMovesSoFar(int value) {
        this.movesSoFar = value;
    }

    // public int getTotalDistance() {
    //     return this.movesSoFar + this.getTotalManhattanDistance();
    // }

    public int getTotalDistance() {
        return this.astarScore;
    }

    public void setAstarScore(int value) {
        this.astarScore = value;
    }

    public void incrementDistance() {
        this.movesSoFar++;
    }

    @Override
    public NodeAstar getParent() {
        return (NodeAstar) super.getParent();
    }
    
}
