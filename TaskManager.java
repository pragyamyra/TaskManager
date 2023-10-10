import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

class Task {
    private String name;
    private String description;
    private String dueDate;

    public Task(String name, String description, String dueDate) {
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
    }

    
    public String show() {
        return "  Name: " + name + "\n    Description: " + description + "\n    Due Date: " + dueDate + "\n";
    }
}

public class TaskManager {
    static ArrayList<Task> tasks = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        
        while (true) {
            System.out.println("Task Management System");
            System.out.println("1. Add Task");
            System.out.println("2. Remove Task");
            System.out.println("3. List Tasks");
            System.out.println("4. Exit");
            System.out.print("Enter your choice (1/2/3/4): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    removeTask();
                    break;
                case 3:
                    listTasks();
                    break;
                case 4:
                    System.out.println();
                    System.out.println("Exiting the Task Management System.");
                    System.exit(0);
                default:
                    System.out.println();
                    System.out.println("Invalid choice. Please try again.");
                    System.out.println();
                    break;
            }
        }
    }

    public static void addTask() {
        System.out.print("Enter task name: ");
        String name = scanner.nextLine();

        System.out.print("Enter task description: ");
        String description = scanner.nextLine();

        String dueDate;
        do {
            System.out.print("Enter due date (e.g., YYYY-MM-DD): ");
            dueDate = scanner.nextLine();
        } while (!isValidDate(dueDate));

        Task task = new Task(name, description, dueDate);
        tasks.add(task);
        System.out.println();
        System.out.println("Task added successfully!");
        System.out.println();
    }

    public static boolean isValidDate(String dateStr) {
        try {
            LocalDate.parse(dateStr, dateFormatter);
            return true;
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD format.");
            return false;
        }
    }

    public static void removeTask() {
        if (tasks.isEmpty()) {
            System.out.println();
            System.out.println("No tasks to remove.");
            System.out.println();
            return;
        }
        
        System.out.println("Select a task to remove:");
        System.out.println();
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).show());
            System.out.println();
        }

        System.out.print("Enter the task number to remove: ");
        int taskNumber = scanner.nextInt();
        scanner.nextLine(); 

        if (taskNumber < 1 || taskNumber > tasks.size()) {
            System.out.println();
            System.out.println("Invalid task number. Please try again.");
            System.out.println();
        } else {
            tasks.remove(taskNumber - 1);
            System.out.println();
            System.out.println("Task removed successfully!");
            System.out.println();
        }
    }

    public static void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
        } else {
            System.out.println("**********List of Tasks:**********");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println();
                System.out.println((i + 1) + ". " + tasks.get(i).show());
                System.out.println();
            }
        }
    }
}

