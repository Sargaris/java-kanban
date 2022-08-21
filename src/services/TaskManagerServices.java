package services;
import tasks.Epic;
import tasks.SubTask;
import tasks.Task;

import java.util.HashMap;
import java.util.Map;


public class TaskManagerServices {

    private Map<Integer, Task> tasks = new HashMap<>();
    private Map<Integer, SubTask> subTasks = new HashMap<>();
    private Map<Integer, Epic> epics = new HashMap<>();

    private int idGenerator = 0;

    public void addTask(Task task) {

        int taskId = ++idGenerator;
        task.setId(taskId);
        tasks.put(taskId,task);
    }


    public void addEpic(Epic epic) {

        int epicId = ++ idGenerator;
        epic.setId(epicId);
        epics.put(epicId,epic);
    }


    public void addSubTask(SubTask subTask) {

      int epicId = subTask.getEpicId();
      Epic epic = epics.get(epicId);
      if(epic == null){
          return;
      }

        int subTaskId = ++idGenerator;
        subTask.setId(subTaskId);
        subTasks.put(subTaskId,subTask);

        epic.addTask(subTaskId);
    }
}
