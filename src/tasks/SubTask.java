package tasks;

public class SubTask extends Task{

    private int epicId;

    public SubTask(String taskName, String taskDescription, TaskStatus status,int epicId) {
        super(taskName, taskDescription, status);
        this.epicId = epicId;
    }

    public int getEpicId() {return epicId;}

    public void setEpicId(int epicId) {this.epicId = epicId;}

    @Override
    public String toString() {
        return "tasks.SubTask{" +
                "id=" + id +
                ", taskName='" + taskName + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", status=" + status +
                '}';
    }
}
