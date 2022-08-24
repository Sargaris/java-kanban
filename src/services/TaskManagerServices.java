package services;

import tasks.Epic;
import tasks.SubTask;
import tasks.Task;

import java.util.*;


public class TaskManagerServices {

    private Map<Integer, Task> tasks = new HashMap<>();
    private Map<Integer, SubTask> subTasks = new HashMap<>();
    private Map<Integer, Epic> epics = new HashMap<>();

    private int idGenerator = 0;

    public void addTask(Task task) {

        int taskId = ++idGenerator;
        task.setId(taskId);
        tasks.put(taskId, task);
    }


    public void addEpic(Epic epic) {

        int epicId = ++idGenerator;
        epic.setId(epicId);
        epics.put(epicId, epic);
    }


    public void addSubTask(SubTask subTask) {

        int epicId = subTask.getEpicId();
        Epic epic = epics.get(epicId);
        if (epic == null) {
            return;
        }

        int subTaskId = ++idGenerator;
        subTask.setId(subTaskId);
        subTasks.put(subTaskId, subTask);

        epic.addTask(subTaskId);
        updateEpicStatus(epic);
    }

    public List<Task> getTasks() {
        Collection<Task> values = tasks.values();
        return new ArrayList<>(values);
    }

    public List<Epic> getEpics() {
        Collection<Epic> values = epics.values();
        return new ArrayList<>(values);
    }

    public List<SubTask> getSubTasks() {
        Collection<SubTask> value = subTasks.values();
        return new ArrayList<>(value);
    }

    public void removeTasks() {
        tasks.clear();
    }

    public void removeEpics() {
        epics.clear();
    }

    public void removeSubTasks() {
        subTasks.clear();
    }

    public Task getTaskById(int taskId) {
        return tasks.get(taskId);
    }

    public Epic getEpicById(int epicId) {
        return epics.get(epicId);
    }

    public SubTask getSubTaskById(int subTaskId) {
        return subTasks.get(subTaskId);
    }

    public void updateTask(Task task) {
        int id = task.getId();
        tasks.remove(id);
        tasks.put(id, task);
    }

    public void updateEpic(Epic epic) {
        int id = epic.getId();
        tasks.remove(id);
        tasks.put(id, epic);
    }

    public void updateSubTask(SubTask subTask) {
        int id = subTask.getId();
        tasks.remove(id);
        tasks.put(id, subTask);
    }

    public void removeTaskById(int taskId) {
        tasks.remove(taskId);
    }

    public void removeEpicById(int epicId) {
        epics.remove(epicId);
    }

    public void removeSubTaskById(int subTaskId) {
        subTasks.remove(subTaskId);
    }


    private void updateEpicStatus(Epic epic) {

    }
}

