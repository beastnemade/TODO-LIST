package Features;

import App.*;
import DataSorting.*;
import java.sql.*;
import java.util.Scanner;

public class AddTask implements Actions{
    Connection con;

    public AddTask(Connection con){
        this.con = con;
    }

    public void showActionsInformation() {
        System.out.println("");
        System.out.println("To add a new task, please follow the instructions and press ENTER:");
        System.out.println("Title, Due Date (format: dd-mm-yyyy), Start Date (format: dd-mm-yyyy), Status, Priority(format: 1/2/3), Project Name");
        System.out.println("");
        System.out.println("Enter 0 to RETURN");
    }

    public String readUserInput() {
        while (true) {
            System.out.println("");
            System.out.print("Enter Information: ");
            Scanner in = new Scanner(System.in);
            String userInput = in.nextLine();
            if (!userInput.equals("0")) {
                String[] parts = userInput.split(",");
                if (parts.length == 6) {
                    if (DateSorting.isDateValid("dd-MM-yyyy", parts[1].trim())) {
                        if(DateSorting.isDateValid("dd-MM-yyyy", parts[2].trim())){
                            if(parts[4].trim().equals("1")||parts[4].trim().equals("2")||parts[4].trim().equals("3")){
                                return userInput;
                            }
                            else{
                                System.out.println("The Priority Enter is not Valid");
                            }
                        }else{
                            System.out.println("The date entered is invalid, try again: ");    
                        }
                    } else {
                        System.out.println("The date entered is invalid, try again: ");
                    }
                } else {
                    System.out.println("Please follow instructions, try again: ");
                }
            } else {
                return userInput;
            }
        }
    }

    public void executeAction(String command) {
        String[] parts = command.split(",");
        Task task = Task.buildTask(parts[0], DateSorting.parseDate("dd-MM-yyyy", parts[1]),DateSorting.parseDate("dd-MM-yyyy", parts[2]),
                parts[3], Integer.parseInt(parts[4].trim()), parts[5]);

        TodoList.tasks.put(task.getTitle(), task);
        System.out.println("Task successfully added!");
    }
}