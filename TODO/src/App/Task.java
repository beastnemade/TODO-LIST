
package App;

import java.time.LocalDate;

public class Task {

    private String task_name;
    private LocalDate dueDate;
    private LocalDate startDate;
    private String status;
    private int priority;
    private String project;

    public String getTitle() {
        return task_name;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public String getStatus() {
        return status;
    }

 
    public LocalDate getStartDate() {
        return startDate;
    }
    

    public int getPriority() {
        return priority;
    }

  
    public String getProject() {
        return project;
    }

 
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

   
    public void setPriority(int priority) {
        this.priority = priority;
    }
    
    public void setTitle(String title) {
        this.task_name = title;
    }

     public void setProject(String project) {
        this.project=project;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static Task buildTask(String task_name, LocalDate dueDate, LocalDate  startDate, String status,int priority,String project) {
        Task task = new Task();

        task.setTitle(task_name);
        task.setDueDate(dueDate);
        task.setStartDate(startDate);
        task.setStatus(status);
        task.setPriority(priority);
        task.setProject(project);
        return task;
    }

 
    public String toString() {
        return task_name + "," + dueDate +","+startDate+ "," + status + "," + priority+","+project;
    }

}
