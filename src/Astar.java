import java.util.Scanner;

public class Astar {

    public static void main(String[] args) {
        
        AstarOperations ao = new AstarOperations();

        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        scanner.close();

        Board board = ao.interpretInput(line);

        System.out.println(ao.AstarSearch(board));

    
    }
}
