import java.util.Scanner;

public class Price {

    public static void main(String[] args) {
        
        PriceOperations po = new PriceOperations();

        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        scanner.close();

        Board board = po.interpretInput(line);

        System.out.println(po.PriceSearch(board));

    
    }
}