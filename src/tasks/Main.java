package tasks;

import services.PrintInConsoleService;
import services.TaskManagerServices;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        TaskManagerServices taskManagerServices = new TaskManagerServices();
        PrintInConsoleService printInConsoleService = new PrintInConsoleService();

        Task task = new Task("таска1", "новая таска", TaskStatus.NEW);
        Task task2 = new Task("таска2", "новая таска 2", TaskStatus.NEW);


        taskManagerServices.addTask(task);
        taskManagerServices.addTask(task2);

        Epic epic = new Epic("Epic 1", "новый эпик", TaskStatus.NEW);
        Epic epic2 = new Epic("Epic 2", "новый эпик 2", TaskStatus.NEW);

        taskManagerServices.addEpic(epic);
        taskManagerServices.addEpic(epic2);

        SubTask subTask = new SubTask("SubTAsk 1", "Новая Сабтаска", TaskStatus.NEW, epic.getId());
        SubTask subTask2 = new SubTask("SubTAsk 2", "Новая Сабтаска 2", TaskStatus.DONE, epic.getId());

        taskManagerServices.addSubTask(subTask);
        taskManagerServices.addSubTask(subTask2);




        List<Task> tasks = taskManagerServices.getTasks();
        printInConsoleService.printTasks(tasks);

        List<Epic> epics = taskManagerServices.getEpics();
        printInConsoleService.printEpics(epics);

        List<SubTask> subTasks = taskManagerServices.getSubTasks();
        printInConsoleService.printSubTasks(subTasks);





    }


}
