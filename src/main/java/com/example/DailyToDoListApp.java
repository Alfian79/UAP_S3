package com.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class DailyToDoListApp {
    private JFrame frame;
    JTable table;
    private DefaultTableModel tableModel;
    JTextField taskField;
    private JLabel imageLabel;
    private File selectedImageFile;
    ArrayList<String[]> tasks;

    public DailyToDoListApp() {
        tasks = new ArrayList<>();
        initComponents();
    }

    private void initComponents() {
        frame = new JFrame("Daily To-Do List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        JPanel panel = new JPanel(new BorderLayout());

        // Task input and image handling
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));

        taskField = new JTextField(20);
        JButton addTaskButton = new JButton("Add Task");
        JButton updateTaskButton = new JButton("Update Task");
        JButton deleteTaskButton = new JButton("Delete Task");
        JButton searchTaskButton = new JButton("Search Task");
        JButton imageButton = new JButton("Add Image");
        JButton saveButton = new JButton("Save Tasks");
        JButton loadButton = new JButton("Load Tasks");

        imageLabel = new JLabel("No Image Selected", SwingConstants.CENTER);
        imageLabel.setPreferredSize(new Dimension(150, 150));
        imageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        inputPanel.add(new JLabel("Task:"));
        inputPanel.add(taskField);
        inputPanel.add(imageButton);
        inputPanel.add(imageLabel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addTaskButton);
        buttonPanel.add(updateTaskButton);
        buttonPanel.add(deleteTaskButton);
        buttonPanel.add(searchTaskButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);

        inputPanel.add(buttonPanel);

        // Table for tasks
        String[] columns = {"Task", "Image"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);

        // Adding components to frame
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(tableScrollPane, BorderLayout.CENTER);

        frame.add(panel);

        // Button actions
        addTaskButton.addActionListener(e -> addTask());
        updateTaskButton.addActionListener(e -> updateTask());
        deleteTaskButton.addActionListener(e -> deleteTask());
        searchTaskButton.addActionListener(e -> searchTask());
        imageButton.addActionListener(e -> chooseImage());
        saveButton.addActionListener(e -> saveTasksToFile());
        loadButton.addActionListener(e -> loadTasksFromFile());

        frame.setVisible(true);
    }

    void addTask() {
        try {
            String task = taskField.getText();
            if (task.isEmpty()) {
                throw new IllegalArgumentException("Task cannot be empty!");
            }

            String imagePath = selectedImageFile != null ? selectedImageFile.getAbsolutePath() : "No Image";
            tasks.add(new String[]{task, imagePath});
            tableModel.addRow(new Object[]{task, imagePath});
            resetInputFields();
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    void updateTask() {
        try {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                throw new IllegalArgumentException("Please select a task to update!");
            }

            String task = taskField.getText();
            if (task.isEmpty()) {
                throw new IllegalArgumentException("Task cannot be empty!");
            }

            String imagePath = selectedImageFile != null ? selectedImageFile.getAbsolutePath() : "No Image";
            tasks.set(selectedRow, new String[]{task, imagePath});
            tableModel.setValueAt(task, selectedRow, 0);
            tableModel.setValueAt(imagePath, selectedRow, 1);
            resetInputFields();
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    void deleteTask() {
        try {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                throw new IllegalArgumentException("Please select a task to delete!");
            }

            tasks.remove(selectedRow);
            tableModel.removeRow(selectedRow);
            resetInputFields();
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    void searchTask() {
        try {
            String query = taskField.getText();
            if (query.isEmpty()) {
                throw new IllegalArgumentException("Please enter a task to search!");
            }

            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i)[0].equalsIgnoreCase(query)) {
                    table.setRowSelectionInterval(i, i);
                    JOptionPane.showMessageDialog(frame, "Task found!", "Info", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            }

            JOptionPane.showMessageDialog(frame, "Task not found!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void chooseImage() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedImageFile = fileChooser.getSelectedFile();
            ImageIcon icon = new ImageIcon(selectedImageFile.getAbsolutePath());
            imageLabel.setIcon(new ImageIcon(icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
        }
    }

    void saveTasksToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tasks.dat"))) {
            oos.writeObject(tasks);
            JOptionPane.showMessageDialog(frame, "Tasks saved successfully!", "Info", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error saving tasks: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    void loadTasksFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("tasks.dat"))) {
            tasks = (ArrayList<String[]>) ois.readObject();
            tableModel.setRowCount(0);
            for (String[] task : tasks) {
                tableModel.addRow(task);
            }
            JOptionPane.showMessageDialog(frame, "Tasks loaded successfully!", "Info", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(frame, "Error loading tasks: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void resetInputFields() {
        taskField.setText("");
        imageLabel.setIcon(null);
        imageLabel.setText("No Image Selected");
        selectedImageFile = null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DailyToDoListApp::new);
    }
}
