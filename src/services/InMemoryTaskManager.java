package services;

import tasks.Epic;
import tasks.SubTask;
import tasks.Task;
import tasks.TaskStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTaskManager implements TaskManager {

    private final Map<Integer, Task> tasks = new HashMap<>();
    private final Map<Integer, SubTask> subTasks = new HashMap<>();
    private final Map<Integer, Epic> epics = new HashMap<>();

    private final HistoryManager historyManager = ManagerServiceFabric.getDefaultHistory();

    private int idGenerator = 0;


    @Override
    public void addTask(Task task) {

        int taskId = generateNextId();
        task.setId(taskId);
        tasks.put(taskId, task);
    }

    @Override
    public void addEpic(Epic epic) {

        int epicId = generateNextId();
        epic.setId(epicId);
        epics.put(epicId, epic);
    }

    @Override
    public void addSubTask(SubTask subTask) {

        int epicId = subTask.getEpicId();
        Epic epic = epics.get(epicId);
        if (epic == null) {
            return;
        }

        int subTaskId = generateNextId();
        subTask.setId(subTaskId);
        subTasks.put(subTaskId, subTask);

        epic.addTask(subTaskId);
        updateEpicStatus(epic);
    }

    @Override
    public List<Task> getTasks() {

        return new ArrayList<>(tasks.values());
    }

    @Override
    public List<Epic> getEpics() {

        return new ArrayList<>(epics.values());
    }

    @Override
    public List<SubTask> getSubTasks() {

        return new ArrayList<>(subTasks.values());
    }

    @Override
    public void removeTasks() {

        tasks.clear();
    }

    @Override
    public void removeEpics() {
        epics.clear();
        subTasks.clear();
    }

    @Override
    public void removeSubTasks() {

        subTasks.clear();
        for (Epic epic : epics.values()) {
            epic.getIdSubTasks().clear();
            updateEpicStatus(epic);
        }
    }

    @Override
    public Task getTaskById(int taskId) {
        historyManager.add(tasks.get(taskId));
        return tasks.get(taskId);

    }

    @Override
    public Epic getEpicById(int epicId) {
        historyManager.add(epics.get(epicId));
        return epics.get(epicId);
    }

    @Override
    public SubTask getSubTaskById(int subTaskId) {
        historyManager.add(subTasks.get(subTaskId));
        return subTasks.get(subTaskId);
    }

    @Override
    public void updateTask(Task task) {

        int id = task.getId();
        tasks.remove(id);
        tasks.put(id, task);
    }

    @Override
    public void updateEpic(Epic epic) {

        int id = epic.getId();
        tasks.remove(id);
        tasks.put(id, epic);
        updateEpicStatus(epic);
    }

    @Override
    public void updateSubTask(SubTask subTask) {

        int id = subTask.getId();
        tasks.remove(id);
        tasks.put(id, subTask);
        int epicId = subTask.getEpicId();
        updateEpicStatus(epics.get(epicId));
    }

    @Override
    public void removeTaskById(int taskId) {

        tasks.remove(taskId);

    }

    @Override
    public void removeEpicById(int epicId) {

        Epic epic = epics.remove(epicId);
        for (Integer subtaskId : epic.getIdSubTasks()) {
            subTasks.remove(subtaskId);
        }


    }

    @Override
    public void removeSubTaskById(int subTaskId) {
        SubTask subTask = subTasks.remove(subTaskId);
        Epic epic = epics.get(subTask.getEpicId());
        epic.getIdSubTasks().remove((Integer) subTaskId);
        updateEpicStatus(epic);

    }

    @Override
    public List<SubTask> getListSubTasksFromEpic(Epic epic) {
        List<SubTask> subTaskList = new ArrayList<>();

        for (int id : epic.getIdSubTasks()) {
            subTaskList.add(subTasks.get(id));
        }
        return subTaskList;
    }

    @Override
    public List<Task> getHistory() {

        return historyManager.getHistory();
    }

    private int generateNextId() {

        return ++idGenerator;
    }

    private void updateEpicStatus(Epic epic) {
        List<SubTask> sub = getListSubTasksFromEpic(epic);
        if (sub.size() == 0) {
            epic.setStatus(TaskStatus.NEW);
            return;
        }

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

        if (statusNew == sub.size()) {
            epic.setStatus(TaskStatus.NEW);
        } else if (statusDone == sub.size()) {
            epic.setStatus(TaskStatus.DONE);
        } else epic.setStatus(TaskStatus.IN_PROGRESS);
    }

}
