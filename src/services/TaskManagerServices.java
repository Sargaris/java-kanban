package services;

import tasks.*;

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

        return new ArrayList<>(tasks.values());
    }

    public List<Epic> getEpics() {

        return new ArrayList<>(epics.values());
    }

    public List<SubTask> getSubTasks() {

        return new ArrayList<>(subTasks.values());
    }

    public void removeTasks() {

        tasks.clear();
    }

    public void removeEpics() {

        epics.clear();
        subTasks.clear();
    }

    public void removeSubTasks() {

        subTasks.clear();
        updateEpicStatus(epics.values());


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
        updateEpicStatus(epic);
    }

    public void updateSubTask(SubTask subTask) {

        int id = subTask.getId();
        tasks.remove(id);
        tasks.put(id, subTask);
        int epicId = subTask.getEpicId();
        updateEpicStatus(epics.get(epicId));
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


    public List<SubTask> getListSubTasksFromEpic(Epic epic) {
        List<SubTask> subTaskList = new ArrayList<>();

        for (int id : epic.getIdSubTasks()) {
            subTaskList.add(subTasks.get(id));
        }
        return subTaskList;
    }

    private void updateEpicStatus(Epic epic) {
        List<SubTask> sub = getListSubTasksFromEpic(epic);
        int statusNew = 0;
        int statusDone = 0;

        for (SubTask task : sub) {
            switch (task.getStatus()) {
                case NEW:
                    statusNew++;
                    break;
                case DONE:
                    statusDone++;
                    break;
            }
        }

        if(statusNew == sub.size()) {
            epic.setStatus(TaskStatus.NEW);
        } else if (statusDone == sub.size()) {
            epic.setStatus(TaskStatus.DONE);
        } else epic.setStatus(TaskStatus.IN_PROGRESS);
    }
}

