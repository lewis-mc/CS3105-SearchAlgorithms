public class NodePrice extends Node {

    int costSoFar;
    

    public NodePrice(Board board, Node parent, int moved, int costSoFar) {
        super(board, parent, moved);
        this.costSoFar = costSoFar;
    }

    public int getCostSoFar() {
        return this.costSoFar;
    }

    public void setCostSoFar(int value) {
        this.costSoFar = value;
    }

    public int getTotalPrice() {
        return this.costSoFar + this.getTotalPriceManhattanDistance();
    }


    @Override
    public NodePrice getParent() {
        return (NodePrice) super.getParent();
    }
    
}
