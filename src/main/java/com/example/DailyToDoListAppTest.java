package com.example;

import java.io.File;

class DailyToDoListAppTest {
    public static void main(String[] args) {
        DailyToDoListApp app = new DailyToDoListApp();

        // Test adding a task
        app.taskField.setText("Test Task 1");
        app.addTask();
        assert app.tasks.size() == 1 : "Add Task Failed";

        // Test updating a task
        app.table.setRowSelectionInterval(0, 0);
        app.taskField.setText("Updated Task 1");
        app.updateTask();
        assert app.tasks.get(0)[0].equals("Updated Task 1") : "Update Task Failed";

        // Test searching a task
        app.taskField.setText("Updated Task 1");
        app.searchTask();
        assert app.table.getSelectedRow() == 0 : "Search Task Failed";

        // Test deleting a task
        app.table.setRowSelectionInterval(0, 0);
        app.deleteTask();
        assert app.tasks.size() == 0 : "Delete Task Failed";

        // Test saving tasks to file
        app.taskField.setText("Test Task 2");
        app.addTask();
        app.saveTasksToFile();
        File file = new File("tasks.dat");
        assert file.exists() : "Save Tasks Failed";

        // Test loading tasks from file
        app.loadTasksFromFile();
        assert app.tasks.size() == 1 : "Load Tasks Failed";
        assert app.tasks.get(0)[0].equals("Test Task 2") : "Loaded Data Incorrect";

        System.out.println("All tests passed!");
    }
}
