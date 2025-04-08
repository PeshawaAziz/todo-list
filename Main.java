import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] input;

        do {
            input = in.nextLine().split(" ");

            switch (input[0]) {
                case "add":
                    switch (input[1]) {
                        case "task" -> {
                        }
                        case "step" -> {
                        }
                        default -> throw new AssertionError();
                    }
                    break;

                case "delete":
                    break;

                case "update":
                    switch (input[1]) {
                        case "task" -> {
                        }
                        case "step" -> {
                        }
                        default -> throw new AssertionError();
                    }
                    break;

                case "get":
                    switch (input[1]) {
                        case "task-by-id" -> {
                        }
                        case "all-tasks" -> {
                        }
                        case "incomplete-tasks" -> {
                        }
                        default -> throw new AssertionError();
                    }
                    break;

                default:
                    throw new AssertionError();
            }
        } while (!input[0].equals("exit"));
    }
}