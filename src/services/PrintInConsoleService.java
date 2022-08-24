package services;

import tasks.Epic;
import tasks.SubTask;
import tasks.Task;

import java.util.List;

public class PrintInConsoleService {

    public void printTasks(List<Task> taskList) {
        for (Task task : taskList) {
            System.out.println(task);
        }
    }

    public void printEpics(List<Epic> epicList) {
        for (Epic epic : epicList) {
            System.out.println("У эпика " + epic + " есть сабтаски " + epic.getIdSubTasks());
        }
    }

    public void printSubTasks(List<SubTask> subTaskList) {
        for (SubTask subTask : subTaskList) {
            System.out.println(subTask);
        }
    }
}
