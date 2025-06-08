import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.Math;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;


public class Manhattan {

    public static void main(String[] args) {
        
        ManhattanOperations mo = new ManhattanOperations();

        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        scanner.close();

        Board board = mo.interpretInput(line);

        System.out.println(mo.BestFirstSearch(board));

        

        
    } 



}

    
    

