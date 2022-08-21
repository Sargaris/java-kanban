package tasks;

public class Task {


    protected int id;
    protected String taskName ;
    protected String taskDescription;
    protected TaskStatus status;


    public Task(String taskName, String taskDescription, TaskStatus status) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.status = status;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getTaskName() {return taskName;}

    public void setTaskName(String taskName) {this.taskName = taskName;}

    public String getTaskDescription() {return taskDescription;}

    public void setTaskDescription(String taskDescription) {this.taskDescription = taskDescription;}

    public TaskStatus getStatus() {return status;}

    public void setStatus(TaskStatus status) {this.status = status;}

    @Override
    public String toString() {
        return "tasks.Task{" +
                "id=" + id +
                ", taskName='" + taskName + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", status=" + status +
                '}';
    }
}
