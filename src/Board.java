import java.util.Arrays;

public class Board implements Cloneable {

    int[][] cells;
    int rows;
    int columns;
    int totalManhattanDistance;
    int totalPriceManhattanDistance; //used for the Price algorithm
    int emptyRow;
    int emptyColumn;

    public Board(int n, int m, int[][] cells) {
        this.rows = n;
        this.columns = m;
        this.cells = cells;
   
        this.totalManhattanDistance = 0;
    }

    public void printBoard() {
        for (int i = 0; i < this.cells.length; i++) {
            System.out.println(Arrays.toString(this.cells[i]));
        }
    }

    public int[][] getCells() {
        return this.cells;
    }

    public int getEmptyCellRow() {
        for (int i = 0; i < this.rows; i++) {
            for (int x = 0; x < this.columns; x++) {
                if (this.cells[i][x] == 0) {
                    return this.rows - i;
                }
            }
        }
        return 0;
    }

    public void setEmpty(int row, int column) {
        this.emptyRow = row;
        this.emptyColumn = column;
    }

    public int getEmptyRow() {
        return this.emptyRow;
    }

    public int getEmptyColumn() {
        return this.emptyColumn;
    }

    // Implement the copy method
    public Board copy() {
        int[][] copiedCells = new int[rows][columns];
        
        // Copy the cell values
        for (int i = 0; i < rows; i++) {
            System.arraycopy(cells[i], 0, copiedCells[i], 0, columns);
        }
        
        // Create a new board with copied cells
        Board copiedBoard = new Board(rows, columns, copiedCells);
        return copiedBoard;
    }

    public int getRows() {
        return this.rows;
    }

    public int getColumns() {
        return this.columns;
    }

    public int getTotalManhattanDistance() {
        return this.totalManhattanDistance;
    }

    public int getTotalPriceManhattanDistance() {
        return this.totalPriceManhattanDistance;
    }


    public void setCells(int[][] cells) {
        this.cells = cells;
    }

    public void setManhattanDistance(int value) {
        this.totalManhattanDistance = value;
    }

      public void setPriceManhattanDistance(int value) {
        this.totalPriceManhattanDistance = value;
    }

    public void setCell(int row, int column, int value) {
        this.cells[row][column] = value;
    }

    public int getCell(int row, int column) {
        return this.cells[row][column];
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

  
    @Override
	 protected Board clone() throws CloneNotSupportedException {
        Board cloned = (Board) super.clone();
	    cloned.setCells(cloned.getCells().clone());
        int[][] cells = cloned.getCells();
        int[][] clonedCells = new int[cloned.getRows()][cloned.getColumns()];
        for (int i = 0; i < clonedCells.length; i++) {
            clonedCells[i] = Arrays.copyOf(cells[i], cells[i].length);
        }
        cloned.setCells(clonedCells.clone());
        return cloned;
	 }

    

   

}