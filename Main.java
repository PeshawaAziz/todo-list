import db.Database;
import java.util.Date;
import java.util.Scanner;
import todo.service.StepService;
import todo.service.TaskService;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] input;

        do {
            input = in.nextLine().split(" ");

            switch (input[0]) {
                case "add" -> {
                    switch (input[1]) {
                        case "task" -> {
                            String title, description;
                            String[] timestamp;
                            int year, month, day;
                            Date dueDate;

                            System.out.println("Title: ");
                            title = in.nextLine();

                            System.out.println("Description: ");
                            description = in.nextLine();
                            timestamp = in.nextLine().split("-");
                            year = Integer.parseInt(timestamp[0]);
                            month = Integer.parseInt(timestamp[1]);
                            day = Integer.parseInt(timestamp[2]);

                            System.out.println("Due date (yyyy-mm-dd): ");
                            dueDate = new Date(year, month, day);

                            // TODO Exception Handling
                            TaskService.saveTask(title, description, dueDate);
                        }

                        case "step" -> {
                            int taskId;
                            String title;

                            System.out.println("Task ID: ");
                            taskId = in.nextInt();

                            System.out.println("Title: ");
                            title = in.nextLine();

                            // TODO Exception Handling
                            StepService.saveStep(taskId, title);
                        }

                        default -> throw new AssertionError();
                    }
                }

                case "delete" -> {
                    int id;

                    System.out.println("ID: ");
                    id = in.nextInt();

                    // TODO Exception Handling
                    Database.delete(id);
                }

                case "update" -> {
                    switch (input[1]) {
                        case "task" -> {
                        }
                        case "step" -> {
                        }
                        default -> throw new AssertionError();
                    }
                }

                case "get" -> {
                    switch (input[1]) {
                        case "task-by-id" -> {
                        }
                        case "all-tasks" -> {
                        }
                        case "incomplete-tasks" -> {
                        }
                        default -> throw new AssertionError();
                    }
                }

                default -> throw new AssertionError();
            }
        } while (!input[0].equals("exit"));
    }
}