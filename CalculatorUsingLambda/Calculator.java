/*
 * Calculator.java
 *
 * Version:
 *     1
 *
 */

/**
 * This program is to create a calculator
 * with the use of given interface and
 * lambda expression.
 *
 * @author  Ishika Prasad
 *
 */

/**
 *This interface contains method for operation to
 * be performed on the two integers.
 */
interface OperationFunctionalInterface{
    int operation(int a, int b);
}

/**
 * This interface contains performOperation which perform
 * the operation on integers and return the result based on
 * the operation.
 * The method helpMessage exit with
 * code 1: incorrect number of arguments
 * code 2: if number is not valid
 * code 3: if operator is not valid
 */
interface CalculatorInterface {
    int performOperation(OperationFunctionalInterface ofi, int num1, int num2);

    static void helpMessage(int exitCode) {
        System.out.println("Usage: <num> <operation> <num>");
        System.exit(exitCode);
    }
}

/**
 * This class contains all the calculator operation using
 * lambda expression.
 */
public class Calculator {

    /**
     * The main method
     * @param args args[0] is integer
     *             args[1] is operator
     *             args[2] is integer
     */
    public static void main(String[] args) {
        //if incorrect number of arguments then exit code of 1
        if(args.length != 3) {
            CalculatorInterface.helpMessage(1);
        }

        //if number is not valid then exit code of 2
        try {
            Integer.parseInt(args[0]);
            Integer.parseInt(args[2]);
        }catch (Exception e) {
            CalculatorInterface.helpMessage(2);
        }

        //if operator is not valid then exit code of 3
        if (!(args[1].equals("+") || (args[1].equals("-") || (args[1].equals("*") || (args[1].equals("/")))))) {
            CalculatorInterface.helpMessage(3);
        }

        //perform calculator operation
        switch(args[1]) {
            //perform addition operation
            case "+" :
                OperationFunctionalInterface addition = (a, b) -> (a + b);
                CalculatorInterface performAddition = (additionOp, a, b) -> (addition.operation(a, b));
                System.out.println("Addition of " + args[0] + " + " + args[2] + " is " +
                        performAddition.performOperation(addition, Integer.parseInt(args[0]),
                                Integer.parseInt(args[2])));
                break;

            //perform subtraction operation
            case "-" :
                OperationFunctionalInterface subtraction = (a, b) -> (a - b);
                CalculatorInterface performSubtraction = (subtractionOp, a, b) -> (subtraction.operation(a, b));
                System.out.println("Subtraction of " + args[0] + " - " + args[2] + " is " +
                        performSubtraction.performOperation(subtraction, Integer.parseInt(args[0]),
                                Integer.parseInt(args[2])));
                break;

            //perform multiplication operation
            //For multiplication, in terminal use escape character before *
            //for eg. java Calculator 5 \* 7
            case "*" :
                OperationFunctionalInterface multiplication = (a, b) -> (a * b);
                CalculatorInterface performMultiplication = (multiplicationOp, a, b) -> (multiplication.operation(a, b));
                System.out.println("Multiplication of " + args[0] + " * " + args[2] + " is " +
                        performMultiplication.performOperation(multiplication, Integer.parseInt(args[0]),
                                Integer.parseInt(args[2])));
                break;

            //perform division operation
            case "/" :
                OperationFunctionalInterface division = (a, b) -> (a / b);
                CalculatorInterface performDivision = (divisionOp, a, b) -> (division.operation(a, b));
                System.out.println("Division of " + args[0] + " / " + args[2] + " is " +
                        performDivision.performOperation(division, Integer.parseInt(args[0]),
                                Integer.parseInt(args[2])));
                break;
        }
    }
}
