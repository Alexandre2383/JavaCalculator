public class Sum {
    public static int calculateSum(String input) {//Declares a public static method called calculateSum, which takes a string (input) as input and returns an integer. This method is the main function for calculating the sum of operations.
        // Delete all space in the operation
        input = input.replaceAll("\\s", "");

        // First the operation in parentheses is calculate
        input = evaluateParentheses(input);

        // Then the operation is calculate with the prioritize rules
        String[] additions = input.split("\\+");// Divides the input string according to the '+' character to obtain an array of parts to be added.
        int sum = 0;//initialization of sum.This variable will store the total sum of the operations.
        // for each entry in addition list, first we call calculateSubstraction, then we implement sum with result
        for (String addition : additions) {
            int tempResult = calculateSubtraction(addition);
            sum += tempResult;//Adds the temporary result (tempResult) to the sum total (sum).
        }

        return sum;
    }

    // Declares a private method called evaluateParentheses that takes an input string and returns a modified string after processing the parentheses.
    private static String evaluateParentheses(String input) {
        while (input.contains("(")) {//Loops as long as the input string contains parentheses.
            int startIndex = input.lastIndexOf('(');//Finds the index of the last opening parenthesis.
            int endIndex = input.indexOf(')', startIndex);//Finds the index of the first closing parenthesis after the opening parenthesis.

            if (startIndex == -1 || endIndex == -1) {//Checks if parenthesis indices are valid.
                // Missing brackets or if they are not close correctly
                System.err.println("Erreur : Missing brackets");
                return "0";
            }

            String insideParentheses = input.substring(startIndex + 1, endIndex);//Extracts content inside parentheses.
            String evaluatedInsideParentheses = Integer.toString(calculateSum(insideParentheses));//Recursively evaluates the content inside the brackets by calling calculateSum.

            input = input.substring(0, startIndex) + evaluatedInsideParentheses + input.substring(endIndex + 1);//Replaces the evaluated sub-expression in the input string.
        }

        return input;// Returns the modified string after processing parentheses.
    }
    
    private static int calculateSubtraction(String input) {
        String[] subtraction = input.split("-");
        int tempResult = calculateMultiplication(subtraction[0]);

        for (int i = 1; i < subtraction.length; i++) {
            tempResult -= calculateMultiplication(subtraction[i]);
        }

        return tempResult;
    }

    private static int calculateMultiplication(String input) {
        String[] multiplication = input.split("\\*");
        int tempResult = calculateDivision(multiplication[0]);

        for (int i = 1; i < multiplication.length; i++) {
            tempResult *= calculateDivision(multiplication[i]);
        }

        return tempResult;
    }

    private static int calculateDivision(String input) {
        String[] divisions = input.split("/");
        int tempResult = calculateSimpleOperations(divisions[0]);

        for (int i = 1; i < divisions.length; i++) {
            tempResult /= calculateSimpleOperations(divisions[i]);
        }

        return tempResult;
    }
    
    //Declares a private method for handling simple operations (in this case, converting the string to an integer).
    private static int calculateSimpleOperations(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            // Gestion de l'exception si la conversion échoue
            System.err.println("Error: Wrong entry in the operation");
            return 0; // Ou une valeur par défaut, selon vos besoins
        }
    }
}