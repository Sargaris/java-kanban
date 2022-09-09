package services;

import tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    final List<Task> lastTenActions = new ArrayList<>();


    @Override
    public final List<Task> getHistory() {

        return lastTenActions;
    }


    @Override
    public void add(Task task) {
        if (task != null) {
            lastTenActions.add(task);

            if (lastTenActions.size() > 10) {
                lastTenActions.remove(0);

            }
        }
    }
}
