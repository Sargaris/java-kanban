package services;

import tasks.*;

import java.util.*;


public interface TaskManagerServices{

    Map<Integer, Task> tasks = new HashMap<>();
    Map<Integer, SubTask> subTasks = new HashMap<>();
    Map<Integer, Epic> epics = new HashMap<>();

    int idGenerator = 0;

    int generateNextId();


    void addTask(Task task);


    void addEpic(Epic epic);


    void addSubTask(SubTask subTask);


    List<Task> getTasks();


    List<Epic> getEpics();


    List<SubTask> getSubTasks();

    void removeTasks();

    void removeEpics();

    void removeSubTasks();

    Task getTaskById(int taskId);

    Epic getEpicById(int epicId);

    SubTask getSubTaskById(int subTaskId);

    void updateTask(Task task);

    void updateEpic(Epic epic);

    void updateSubTask(SubTask subTask);

    void removeTaskById(int taskId);

    void removeEpicById(int epicId);

    void removeSubTaskById(int subTaskId);

    List<SubTask> getListSubTasksFromEpic(Epic epic);

    void updateEpicStatus(Epic epic);


    List<Task> getHistory();


}
