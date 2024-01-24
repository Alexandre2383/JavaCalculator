import java.util.Scanner;
//compilation: javac -d build src/main/java/com/calculator/*.java
//launch: java -cp build Calculator
public class Calculator{

    public static void main(String[] args){
        
        try(Scanner sc = new Scanner(System.in)){//Create a scanner object
            System.out.println("Enter the operation: ");
            String input = sc.nextLine();

            int result = Sum.calculateSum(input);
            System.out.println("The result of the operation is: " + result);
        }    
    }
}