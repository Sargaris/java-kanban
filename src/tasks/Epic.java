package tasks;

import java.util.ArrayList;
import java.util.List;


public class Epic extends Task{
    private List<Integer> idSubTasks = new ArrayList<>();


    public Epic(String taskName, String taskDescription, TaskStatus status) {
        super(taskName,taskDescription,status);
    }

    public void addTask(int idSubTask){
        idSubTasks.add(idSubTask);
    }

    @Override
    public String toString() {
        return "tasks.Epic{" +
                "id=" + id +
                ", taskName='" + taskName + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", status=" + status +
                '}';
    }
}

