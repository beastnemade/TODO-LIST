package Features;

import App.TodoList;
import DataSorting.DateSorting;
import java.sql.*;
import java.util.*;

public class EditTask implements Actions{
    Connection con;
    public EditTask(Connection con){
        this.con=con;
    }

    public void showActionsInformation() {
        System.out.println("");
        System.out.println("to update a task, follow the instructions and press ENTER: ");
        System.out.println("Title, Due Date (format: dd-mm-yyyy), Start Date (format: dd-mm-yyyy), Status, Priority, Project Name");
        System.out.println("Title here represent the ID of the task where an update will occur");
        System.out.println("insert a (-) when an update is not needed to that specific parameter");
        System.out.println("");
        System.out.println("Enter 0 to RETURN");
    }

    public String readUserInput() {
        while (true) {
            System.out.println("");
            System.out.println("Enter information");
            Scanner in = new Scanner(System.in);
            String userInput = in.nextLine();

            if (!userInput.equals(0)) {
                String[] parts = userInput.split(",");
                if (parts.length == 5) {
                    boolean dateValidationRequired = true;
                    if (parts[2].equals("-")) {
                        dateValidationRequired = false;
                    }

                    boolean isDateValid = true;
                    if (dateValidationRequired) {
                        isDateValid = DateSorting.isDateValid("dd-mm-yyyy", parts[2]);
                    }

                    if (isDateValid) {
                        if (TodoList.tasks.get(parts[0]) != null) {
                            return userInput;
                        } else {
                            System.out.println("ID doesn't exist, try again: ");
                        }
                    } else {
                        System.out.println("Please follow instructions or enter 0 to RETURN");
                    }
                } else {
                    return userInput;
                }
            }
        }
    }

    public void executeAction(String command) {

        String[] parts = command.split(",");

        boolean isTaskEdited = false;
        if (!parts[1].equals("-")) {
            TodoList.tasks.get(parts[0]).setTitle(parts[1]);
            isTaskEdited = true;
        }

        if (!parts[2].equals("-")) {
            TodoList.tasks.get(parts[0]).setDueDate(DateSorting.parseDate("dd-mm-yyyy", parts[2]));
            isTaskEdited = true;
        }

        if (!parts[3].equals("-")) {
            TodoList.tasks.get(parts[0]).setStatus(parts[3]);
            isTaskEdited = true;
        }
        if (!parts[4].equals("-")) {
            TodoList.tasks.get(parts[0]).setProject(parts[4]);
            isTaskEdited = true;
        }
        if (isTaskEdited) {
            System.out.println("Tasks successfully updated!!");
        } else {
            System.out.println("No change was applied...");
        }
    }
}