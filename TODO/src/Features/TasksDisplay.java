package Features;

import App.*;
import DataSorting.*;

public class TasksDisplay implements Actions{
   
    public void showActionsInformation() {
        System.out.println("");
        System.out.println("Here are all the tasks: ");
    }

    public String readUserInput() {
        throw new UnsupportedOperationException("The requested operation is not supported.");
    }

    public void executeAction(String command) {
        TodoList.tasks.forEach((key, task) -> {
            System.out.println("ID: " + key + ", Title: " + task.getTitle() + ", Due Date: "
                    + DateSorting.convertDateToString(task.getDueDate(), "dd-MM-yyyy") + ", Status: "
                    + task.getStatus() + ", Project: " + task.getProject());
        });

    }
}