import java.util.Arrays;
import java.util.Collections;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.io.FileWriter;
import java.util.Random;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DataRetrieval {

    public static void main(String[] args) {
        retrieveData();
    }

    /*
     * Function to generate a random sliding block puzzle board
     */
    public static int[][] getRandomBoard(int n, int m) {

        int[][] puzzle = new int[n][m];

        // Create a solved puzzle
        int counter = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                puzzle[i][j] = counter++;
            }
        }
        puzzle[n - 1][m - 1] = 0;

        // Shuffle the puzzle manually
        Random rand = new Random();
        int emptyX = n - 1;
        int emptyY = m - 1;

        for (int i = 0; i < n * m * 10; i++) { // Adjust the multiplier for more or fewer shuffles
            int randomMove = rand.nextInt(4); // 0: Up, 1: Down, 2: Left, 3: Right

            int newX = emptyX;
            int newY = emptyY;

            if (randomMove == 0 && emptyX > 0) {
                newX--;
            } else if (randomMove == 1 && emptyX < n - 1) {
                newX++;
            } else if (randomMove == 2 && emptyY > 0) {
                newY--;
            } else if (randomMove == 3 && emptyY < m - 1) {
                newY++;
            }

            // Swap the empty cell with the selected neighboring cell
            puzzle[emptyX][emptyY] = puzzle[newX][newY];
            puzzle[newX][newY] = 0;
            emptyX = newX;
            emptyY = newY;
        }

        return puzzle;

    }

    // public static int[][] getRandomBoard(int n, int m) {
    //     int[][] board = new int[n][m];
    //     ArrayList<Integer> numbers = new ArrayList<>();

    //     for (int i = 0; i < n * m; i++) {
    //         numbers.add(i);
    //     }

    //     Collections.shuffle(numbers);

    //     int index = 0;
    //     for (int i = 0; i < n; i++) {
    //         for (int x = 0; x < m; x++) {
    //             board[i][x] = numbers.get(index);
    //             index++;
    //         }
    //     }

    //     return board;

    // }

    public static Board[] generateRandomBoards(int size) {

        Board[] boards = new Board[size];

        for (int i = 0; i < 10; i++) {
            int[][] cells = getRandomBoard(3, 3);
            Board board = new Board(3, 3, cells);
            boards[i] = board;
        }

        return boards;
    }



    public static void retrieveData() {
        ManhattanOperations mo = new ManhattanOperations();
        AstarOperations ao = new AstarOperations();
        PriceOperations po = new PriceOperations();

        String csvFilePath_mo = "dataOutput/manhattan.csv";
        String csvFilePath_ao = "dataOutput/astar.csv";
        String csvFilePath_po = "dataOutput/price.csv";

        int size = 500;
        // MANHATTAN
        try (BufferedWriter writer_mo = new BufferedWriter(new FileWriter(csvFilePath_mo))) {
            
            // Create a StringBuilder to construct the CSV data in memory
            StringBuilder csvData_mo = new StringBuilder();
            

            // Assuming you have a list of data to write
            for (int i = 0; i < size; i++) { // Example: Writing 100,000 rows
                // Construct a CSV row

                //MANHATTAN
                Board board = new Board(3, 3, getRandomBoard(3, 3));
                
                long startTime_mo = System.currentTimeMillis();
                String output_mo = mo.BestFirstSearch(board);
                System.out.println(i);
                String[] outputArray_mo = output_mo.split(" ");
                long totalTime_mo = System.currentTimeMillis() - startTime_mo;

                if (outputArray_mo.length > 1) {
                    String rowData =  i + "," + totalTime_mo + "," + outputArray_mo[0] + "\n";
                    csvData_mo.append(rowData);
                } else if (outputArray_mo[0] == "-1") {
                    String rowData =  i + "," + totalTime_mo + "," + outputArray_mo[0] + "\n";
                    csvData_mo.append(rowData);
                } else {
                    String rowData =  i + "," + totalTime_mo + "," + "0" + "\n";
                    csvData_mo.append(rowData);
                }

                // Write data in batches to the file to reduce I/O operations
                if (i % 50 == 0) {
                    writer_mo.write(csvData_mo.toString());
                    csvData_mo.setLength(0); // Clear the StringBuilder

                }

            }

            // Write any remaining data in the StringBuilder
            if (csvData_mo.length() > 0) {
                writer_mo.write(csvData_mo.toString());
            }
           

            System.out.println("Data has been written to " + csvFilePath_mo);
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        //ASTAR
        try (BufferedWriter writer_ao = new BufferedWriter(new FileWriter(csvFilePath_ao))) {
            
            // Create a StringBuilder to construct the CSV data in memory
            StringBuilder csvData_ao = new StringBuilder();
            

            // Assuming you have a list of data to write
            for (int i = 0; i < size; i++) { // Example: Writing 100,000 rows
                // Construct a CSV row

                //MANHATTAN
                Board board = new Board(3, 3, getRandomBoard(3, 3));
                
                long startTime_ao = System.currentTimeMillis();
                String output_ao = ao.BestFirstSearch(board);
                System.out.println(i);
                String[] outputArray_ao = output_ao.split(" ");
                long totalTime_ao = System.currentTimeMillis() - startTime_ao;

                if (outputArray_ao.length > 1) {
                    String rowData =  i + "," + totalTime_ao + "," + outputArray_ao[0] + "\n";
                    csvData_ao.append(rowData);
                } else if (outputArray_ao[0] == "-1") {
                    String rowData =  i + "," + totalTime_ao + "," + outputArray_ao[0] + "\n";
                    csvData_ao.append(rowData);
                } else {
                    String rowData =  i + "," + totalTime_ao + "," + "0" + "\n";
                    csvData_ao.append(rowData);
                }

                // Write data in batches to the file to reduce I/O operations
                if (i % 50 == 0) {
                    writer_ao.write(csvData_ao.toString());
                    csvData_ao.setLength(0); // Clear the StringBuilder
                }

            }

            // Write any remaining data in the StringBuilder
            if (csvData_ao.length() > 0) {
                writer_ao.write(csvData_ao.toString());
            }
           

            System.out.println("Data has been written to " + csvFilePath_ao);
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        //PRICE
        try (BufferedWriter writer_po = new BufferedWriter(new FileWriter(csvFilePath_po))) {
            
            // Create a StringBuilder to construct the CSV data in memory
            StringBuilder csvData_po = new StringBuilder();
            

            // Assuming you have a list of data to write
            for (int i = 0; i < size; i++) { // Example: Writing 100,000 rows
                // Construct a CSV row

                //MANHATTAN
                Board board = new Board(3, 3, getRandomBoard(3, 3));
                
                long startTime_po = System.currentTimeMillis();
                String output_po = po.BestFirstSearch(board);
                System.out.println(i);
                String[] outputArray_po = output_po.split(" ");
                long totalTime_po = System.currentTimeMillis() - startTime_po;

                if (outputArray_po.length > 1) {
                    String rowData =  i + "," + totalTime_po + "," + outputArray_po[0] + "\n";
                    csvData_po.append(rowData);
                } else if (outputArray_po[0] == "-1") {
                    String rowData =  i + "," + totalTime_po + "," + outputArray_po[0] + "\n";
                    csvData_po.append(rowData);
                } else {
                    String rowData =  i + "," + totalTime_po + "," + "0" + "\n";
                    csvData_po.append(rowData);
                }

                // Write data in batches to the file to reduce I/O operations
                if (i % 50 == 0) {
                    writer_po.write(csvData_po.toString());
                    csvData_po.setLength(0); // Clear the StringBuilder
                }

            }

            // Write any remaining data in the StringBuilder
            if (csvData_po.length() > 0) {
                writer_po.write(csvData_po.toString());
            }
           

            System.out.println("Data has been written to " + csvFilePath_po);
            
        } catch (IOException e) {
            e.printStackTrace();
        }



    }


    
}

    
