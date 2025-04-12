import db.Database;
import db.Entity;
import db.exception.EntityNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import todo.entity.Task;
import todo.service.StepService;
import todo.service.TaskService;

public class Main {
    public static void main(String[] args) {
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

                            System.out.println("Title: ");
                            title = in.nextLine();

                            System.out.println("Description: ");
                            description = in.nextLine();

                            System.out.println("Due date (yyyy-mm-dd): ");
                            try {
                                dueDate = sdf.parse(in.nextLine());

                                // TODO Exception Handling
                                TaskService.saveTask(title, description, dueDate);
                            } catch (ParseException e) {
                                System.out.println("Invalid date format.");
                            }

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

                        default -> {
                            System.out.println("Invalid input.");
                        }
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
                            int taskId;
                            String field;

                            System.out.println("ID: ");
                            taskId = in.nextInt();

                            System.out.println("Field (title, description, status, due-date): ");
                            field = in.nextLine();

                            switch (field) {
                                case "title" -> {
                                    System.out.println("New value: ");
                                    String newTitle = in.nextLine();

                                    // TODO Exception Handling
                                    TaskService.updateTitle(taskId, newTitle);
                                }

                                case "description" -> {
                                    System.out.println("New value: ");
                                    String newDescription = in.nextLine();

                                    // TODO Exception Handling
                                    TaskService.updateDescription(taskId, newDescription);
                                }

                                case "status" -> {
                                    System.out.println("New value (NotStarted, InProgress, Completed): ");
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
                                    System.out.println("New value: ");
                                    try {
                                        Date newDueDate = sdf.parse(in.nextLine());

                                        // TODO Exception Handling
                                        TaskService.updateDueDate(taskId, newDueDate);
                                    } catch (ParseException e) {
                                        System.out.println("Invalid date format.");
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

                            System.out.println("ID: ");
                            stepId = in.nextInt();

                            System.out.println("Field (title, status): ");
                            field = in.nextLine();

                            System.out.println("New value: ");
                            switch (field) {
                                case "title" -> {
                                    String newTitle = in.nextLine();

                                    // TODO Exception Handling
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
                            System.out.println("ID: ");
                            int taskId = in.nextInt();

                            try {
                                System.out.println(Database.get(taskId));
                            } catch (EntityNotFoundException e) {
                                System.out.println("Task with ID=" + taskId + " not found.");
                            }
                        }

                        case "all-tasks" -> {
                            for (Entity entity : Database.getAll(Task.getCode())) {
                                Task task = (Task) entity;
                                System.out.println(task + "\n");
                            }
                        }

                        case "incomplete-tasks" -> {
                            for (Task task : TaskService.getIncompleteTasks()) {
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