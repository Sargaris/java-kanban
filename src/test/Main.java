package test;

import services.*;
import tasks.Epic;
import tasks.SubTask;
import tasks.Task;
import tasks.TaskStatus;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        Managers manager = new ManagerServiceFabric();
        TaskManager taskManager = manager.getDefault();

        PrintInConsoleService printInConsoleService = new PrintInConsoleService();

        Task task = new Task("таска1", "новая таска", TaskStatus.NEW);
        Task task2 = new Task("таска2", "новая таска 2", TaskStatus.NEW);


        taskManager.addTask(task);
        taskManager.addTask(task2);

        Epic epic = new Epic("Epic 1", "новый эпик", TaskStatus.NEW);
        Epic epic2 = new Epic("Epic 2", "новый эпик 2", TaskStatus.NEW);

        taskManager.addEpic(epic);
        taskManager.addEpic(epic2);

        SubTask subTask = new SubTask("SubTAsk 1", "Новая Сабтаска", TaskStatus.NEW, epic.getId());
        SubTask subTask2 = new SubTask("SubTAsk 2", "Новая Сабтаска 2", TaskStatus.DONE, epic.getId());

        taskManager.addSubTask(subTask);
        taskManager.addSubTask(subTask2);

        List<Task> tasks = taskManager.getTasks();
        printInConsoleService.printTasks(tasks);

        List<Epic> epics = taskManager.getEpics();
        printInConsoleService.printEpics(epics);

        List<SubTask> subTasks = taskManager.getSubTasks();
        printInConsoleService.printSubTasks(subTasks);

        System.out.println();

        taskManager.getTaskById(1);
        taskManager.getTaskById(2);

        System.out.println(taskManager.getHistory());

        taskManager.getTaskById(1);
        taskManager.getTaskById(2);

        System.out.println(taskManager.getHistory());
    }

}
