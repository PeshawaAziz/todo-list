import db.Database;
import db.Entity;
import db.exception.EntityNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import todo.entity.Step;
import todo.entity.Task;
import todo.service.StepService;
import todo.service.TaskService;
import todo.validator.StepValidator;
import todo.validator.TaskValidator;

public class Main {
    public static void main(String[] args) {
        Database.registerValidator(Task.getCode(), new TaskValidator());
        Database.registerValidator(Step.getCode(), new StepValidator());

        Scanner in = new Scanner(System.in);
        String[] input;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        do {
            input = in.nextLine().split(" ");

            switch (input[0]) {
                case "add" -> {
                    switch (input[1]) {
                        case "task" -> {
                            String title, description;
                            Date dueDate;

                            System.out.printf("Title: ");
                            title = in.nextLine();

                            System.out.printf("Description: ");
                            description = in.nextLine();

                            System.out.printf("Due date (yyyy-mm-dd): ");

                            try {
                                dueDate = sdf.parse(in.nextLine());

                                int id = TaskService.saveTask(title, description, dueDate);

                                System.out.println("Task saved successfully.");
                                System.out.println("ID: " + id);
                            } catch (ParseException e) {
                                System.out.println("Invalid date format.");
                            }
                        }

                        case "step" -> {
                            int taskId;
                            String title;

                            System.out.printf("Task ID: ");
                            taskId = in.nextInt();

                            System.out.printf("Title: ");
                            title = in.nextLine();

                            int id = StepService.saveStep(taskId, title);

                            System.out.println("Step saved successfully.");
                            System.out.println("ID: " + id);
                        }

                        default -> {
                            System.out.println("Invalid input.");
                        }
                    }
                }

                case "delete" -> {
                    int id;

                    System.out.printf("ID: ");
                    id = in.nextInt();

                    try {
                        Database.delete(id);
                        System.out.println("Entity deleted successfully.");
                    } catch (EntityNotFoundException e) {
                        System.out.println("Cannot delete entity.");
                        System.out.println(e.getMessage());
                    }
                }

                case "update" -> {
                    switch (input[1]) {
                        case "task" -> {
                            int taskId;
                            String field;

                            System.out.printf("ID: ");
                            taskId = in.nextInt();
                            in.nextLine();

                            System.out.printf("Field (title, description, status, due-date): ");
                            field = in.nextLine();

                            switch (field) {
                                case "title" -> {
                                    System.out.printf("New value: ");
                                    String newTitle = in.nextLine();

                                    // TODO Exception Handling
                                    TaskService.updateTitle(taskId, newTitle);
                                }

                                case "description" -> {
                                    System.out.printf("New value: ");
                                    String newDescription = in.nextLine();

                                    // TODO Exception Handling
                                    TaskService.updateDescription(taskId, newDescription);
                                }

                                case "status" -> {
                                    System.out.printf("New value (NotStarted, InProgress, Completed): ");
                                    String newStatus = in.nextLine();

                                    switch (newStatus) {
                                        case "NotStarted" -> {
                                            TaskService.setAsCompleted(taskId);
                                        }

                                        case "InProgress" -> {
                                            TaskService.setAsInProgress(taskId);
                                        }

                                        case "Completed" -> {
                                            TaskService.setAsCompleted(taskId);
                                        }

                                        default -> {
                                            System.out.println("Invalid input.");
                                        }
                                    }

                                }

                                case "due-date" -> {
                                    System.out.printf("New value: ");
                                    try {
                                        Date newDueDate = sdf.parse(in.nextLine());

                                        // TODO Exception Handling
                                        TaskService.updateDueDate(taskId, newDueDate);
                                    } catch (ParseException e) {

                                        System.out.println(e.getMessage());
                                    }
                                }

                                default -> {
                                    System.out.println("Invalid input.");
                                }
                            }
                        }

                        case "step" -> {
                            int stepId;
                            String field;

                            System.out.printf("ID: ");
                            stepId = in.nextInt();

                            System.out.printf("Field (title, status): ");
                            field = in.nextLine();

                            System.out.printf("New value: ");
                            switch (field) {
                                case "title" -> {
                                    String newTitle = in.nextLine();
                                    System.out.println();

                                    StepService.updateTitle(stepId, newTitle);
                                }

                                case "status" -> {
                                    System.out.println("New value (NotStarted, Completed): ");
                                    String newStatus = in.nextLine();

                                    switch (newStatus) {
                                        case "NotStarted" -> {
                                            StepService.setAsCompleted(stepId);
                                        }

                                        case "Completed" -> {
                                            StepService.setAsCompleted(stepId);
                                        }

                                        default -> {
                                            System.out.println("Invalid input.");
                                        }
                                    }

                                }

                                default -> {
                                    System.out.println("Invalid input.");
                                }
                            }
                        }

                        default -> {
                            System.out.println("Invalid input.");
                        }
                    }
                }

                case "get" -> {
                    switch (input[1]) {
                        case "task-by-id" -> {
                            System.out.printf("ID: ");
                            int taskId = in.nextInt();

                            try {
                                System.out.println("__________________________");
                                System.out.println(Database.get(taskId));
                            } catch (EntityNotFoundException e) {
                                System.out.println(e.getMessage());
                            }
                        }

                        case "all-tasks" -> {
                            if (Database.getAll(Task.getCode()).isEmpty())
                                System.out.println("(No tasks.)");
                            else
                                for (Entity entity : Database.getAll(Task.getCode())) {
                                    Task task = (Task) entity;
                                    System.out.println("__________________________");
                                    System.out.println(task + "\n");
                                }
                        }

                        case "incomplete-tasks" -> {
                            if (TaskService.getIncompleteTasks().isEmpty())
                                System.out.println("(No tasks.)");
                            else
                                for (Task task : TaskService.getIncompleteTasks()) {
                                    System.out.println("__________________________");
                                    System.out.println(task + "\n");
                                }

                        }

                        default -> {
                            System.out.println("Invalid input.");
                        }
                    }
                }

                default -> {
                    System.out.println("Invalid input.");
                }
            }
        } while (!input[0].equals("exit"));
    }
}