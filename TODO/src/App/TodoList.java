package App;

import DataSorting.*;
import Features.*;
import java.sql.*;
import java.util.*;
import java.util.Date;

public class TodoList {
    Connection con;
    String user;
    public static Map<String, Task> tasks;
        public TodoList(Connection con,Map<String,Task> tasks,String user){
            this.user=user;
            this.con=con;
            this.tasks=tasks;
        }

    public void start() {
        showApplicationTitle();
        while (true) {
            Scanner sc=new Scanner(System.in);
            showAvailableActions();
            executeAction(con,sc);
        }
    }

    public void executeAction(Connection con,Scanner sc) {
        System.out.print("Enter your choice: ");
        int actionNumber ;
        if(sc.hasNext()){
        actionNumber = sc.nextInt();
        }
        else{
            actionNumber=0;
        }
        switch (actionNumber) {
            case 1:
                AddTask addTask = new AddTask(con);
                addTask.showActionsInformation();
                String command = addTask.readUserInput();
                String[] parts=command.split(",");
                try{
                    PreparedStatement pst=con.prepareStatement("insert into tasks(userName,task_name,created_at,due_date,start_date,status,priority) values(?,?,?,?,?,?,?)");
                    pst.setString(1, user);
                    pst.setString(2, parts[0]);
                    java.util.Date date=new Date();
                    pst.setTimestamp(3,new Timestamp(date.getTime()));
                    pst.setString(4, parts[1]);
                    pst.setString(5, parts[2]);
                    pst.setString(6,parts[3]);
                    pst.setString(7,parts[4]);
                    int r=pst.executeUpdate();
                    if(r>1)
                        System.out.println("inserted Successfully");
                }
                catch(Exception e){
                    System.out.println("Error inserting data");
                    System.out.println(e);
                }
                if (!command.equals("0"))
                    addTask.executeAction(command);
                break;

            case 2:
                if (tasks.size() > 0) {
                    MarkAsDone markAsDone = new MarkAsDone();
                    markAsDone.showActionsInformation();
                    String id = markAsDone.readUserInput();
                    if (!id.equals("0"))
                        markAsDone.executeAction(id);

                } else {
                    System.out.println("Your List is empty, add tasks first! ");

                }
                break;

            case 3:
                if (tasks.size() > 0) {
                    EditTask editTask = new EditTask(con);
                    editTask.showActionsInformation();
                    String editCommand = editTask.readUserInput();
                    if (!editCommand.equals(0))
                        editTask.executeAction(editCommand);
                } else {
                    System.out.println("Your list is empty, add tasks first! ");
                }
                break;

            case 4:
                if (tasks.size() > 0) {
                    TasksDisplay tasksDisplay = new TasksDisplay();
                    tasksDisplay.showActionsInformation();
                    tasksDisplay.executeAction(null);
                } else {
                    System.out.println("Your list is empty, add tasks first! ");
                }
                break;

            case 5:
                DateSorting dateSorting = new DateSorting();
                dateSorting.executeAction(null);
                break;

            case 6:
                System.out.println("\n\tThank You For Using TODO! Have a Nice Day!\n\t Don't Forget To Do Your Task \n\t And Update it at the end of your day\n");
                System.exit(0);
        }
    }


    public void showApplicationTitle() {
        System.out.println("\n-----------------------");
        System.out.println("To DO List Application");
        System.out.println("-----------------------");
    }

    public void showAvailableActions() {
        System.out.println("");
        System.out.println("1. Add a task");
        System.out.println("2. Mark task as done");
        System.out.println("3. Edit task");
        System.out.println("4. Display all tasks");
        System.out.println("5. Sort tasks by date");
        System.out.println("6. Exit");
        System.out.println("");
    }
}