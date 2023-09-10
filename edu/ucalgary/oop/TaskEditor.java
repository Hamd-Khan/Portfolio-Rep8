package edu.ucalgary.oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/** 
 * This class displays the GUI that allows the user to edit the tasks which caused an error in the scheduling process.
 * ENSF 380: Term Project 2023-04-06.
    @Authors    Group 11 (Dominic Gartner <a href = "mailto:dominic.gartner@ucalgary.ca">dominic.gartner@ucalgary.ca</a>, 
                          Hamd Khan <a href = "mailto:hamd.khan1@ucalgary.ca">hamd.khan1@ucalgary.ca</a>, 
                          Alex Mclean <a href = "mailto:alexander.mclean@ucalgary.ca">alexander.mclean@ucalgary.ca</a>, 
                          Kaylyn Tanton <a href = "mailto:kaylyn.tanton@ucalgary.ca">kaylyn.tanton@ucalgary.ca</a>)
    @Version    2.8
    @since      1.0
 */
public class TaskEditor extends AnimalTaskScheduler implements ActionListener {
    private JFrame frame;
    private JLabel titleLabel;
    private JPanel taskPanel;
    private List<String> hourlyDurationLabels;


 
    /**
    * Overload Constructor for the TaskEditor class.
    * @param tasks List of tasks to be edited. 
    * @param schedule The Schedule object containing the current schedule.
    * @param allTasks The list of all available tasks.
    */
    public TaskEditor(List<Task> tasks, Schedule schedule, ArrayList<Task> allTask) {
        createFrame();
        addHourlyDurations();
        createHeadLabel();
        createPanel(tasks);
        createScrollPane();
        addSaveBtn(tasks);
    }

    /**
    * Creates the title label and adds it to the frame.
    */
    private void createHeadLabel() {
         // Create the title label
         titleLabel = new JLabel("Edit Tasks");
         titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
         titleLabel.setForeground(new Color(49, 83, 125));
         titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
         frame.add(titleLabel, BorderLayout.NORTH);
    }

    /**
     * Creates a panel to hold the task controls for each task in the given list of tasks.
     * @param tasks the list of tasks to create task controls for
     * @return the panel containing the task controls for each task
    */
    private void createPanel(List<Task> tasks) {
        // Create the headers panel for the task controls
        JPanel headersPanel = new JPanel(new GridLayout(1, 3));
        // Add the headers panel to the frame's NORTH region
        frame.add(headersPanel, BorderLayout.NORTH);
        // Create the panel to hold the task controls
        taskPanel = new JPanel();
        taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.Y_AXIS));
        for (Task task : tasks) {
            JPanel taskControlPanel = createTaskControlPanel(task);
            taskPanel.add(taskControlPanel);
        }
    }

    /**
    * Creates the scroll panel and adds it to the frame
    */
    private void createScrollPane() {
        JScrollPane scrollPane = new JScrollPane(taskPanel);
        frame.add(scrollPane, BorderLayout.CENTER);
    }
    
    /**
    * Adds a save button to the editor frame and calls a private method to save the changes made to the tasks to the database when the button is clicked.
    * @param tasks The list of tasks to edit.
    */
    private void addSaveBtn(List<Task> tasks) {
        ArrayList<Integer> startHourValues = new ArrayList<Integer>();
         // Create the button to save the changes
        JButton saveButton = new JButton("Save Changes");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Save the changes to the tasks
                for (Component component : taskPanel.getComponents()) {
                    if (component instanceof JPanel) {
                        JPanel taskControlPanel = (JPanel) component;
                        JTextField descriptionField = (JTextField) taskControlPanel.getComponent(0);
                        JComboBox<String> startHourComboBox = (JComboBox<String>) taskControlPanel.getComponent(2);
                        String description = descriptionField.getText();
                        int startHour = Integer.parseInt((String) startHourComboBox.getSelectedItem());
                        startHourValues.add(startHour); 
                        findTaskByDescription(tasks, description); }
                }
                // Close the editor
                try {
                    TaskDatabase.saveTasks(tasks,startHourValues );
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                frame.dispose();
                new Schedule();
            }
        });
        frame.add(saveButton, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    /**
    * Creates the JFrame for the GUI
    */
    private void createFrame() {
        frame = new JFrame("Edit Schedule");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());
    }

    /**
     * Adds hourly duration labels to the frame.
    */
    private void addHourlyDurations() {
        JLabel titleLabel = new JLabel("Current Hourly Durations In Schedule");
        Font boldFont = new Font(titleLabel.getFont().getName(), Font.BOLD, titleLabel.getFont().getSize());
        titleLabel.setFont(boldFont);
        hourlyDurationLabels = new ArrayList<String>();
        for (int hour = 0; hour < 24; hour++) {
            hourlyDurationLabels.add(getHourlyDurations(hour, getTasksThisHour(hour)));
        }
         // Create the panel to hold the hourly duration labels
         JPanel hourlyDurationPanel = new JPanel();
         hourlyDurationPanel.setLayout(new BoxLayout(hourlyDurationPanel, BoxLayout.Y_AXIS));
         hourlyDurationPanel.add(titleLabel);
         // Add the hourly duration labels to the panel
         for (String label : hourlyDurationLabels) {
             JLabel hourlyDurationLabel = new JLabel(label);
             hourlyDurationPanel.add(hourlyDurationLabel);
         }
         // Add the hourly duration panel to the EAST side of the frame
         frame.add(hourlyDurationPanel, BorderLayout.EAST);
    }

    /**
    * Creates a JPanel containing controls for a given task.
    * @param task the task to create controls for
    * @return a JPanel containing controls for the task
    */
    private JPanel createTaskControlPanel(Task task) {
        // Create the panel to hold the task controls
        JPanel taskControlPanel = new JPanel();
        taskControlPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        // Create the text field for the task description
        JTextField descriptionField = new JTextField(task.getDescription(), 20);
        descriptionField.setEnabled(false); // disable editing
        taskControlPanel.add(descriptionField);
        // Create the text field for the task nickname
        JTextField nicknameField = new JTextField(task.getNickname(), 10);
        taskControlPanel.add(nicknameField);
        nicknameField.setEnabled(false);
        // Create the combo box for the task start hour
        String[] startHours = new String[24];
        for (int i = 0; i < 24; i++) {
            startHours[i] = Integer.toString(i);
        }
        JComboBox<String> startHourComboBox = new JComboBox<>(startHours);
        startHourComboBox.setSelectedIndex(task.getStartHour());
        taskControlPanel.add(startHourComboBox);
        return taskControlPanel;
    }

    /**
    * Searches for a Task object in a list of tasks based on its description field.
    * @param tasks the list of tasks to search
    * @param description the description field of the task to find
    * @return the Task object if found, null if not found
    */
    private Task findTaskByDescription(List<Task> tasks, String description) {
        for (Task task : tasks) {
            if (task.getDescription().equals(description)) {
                return task; }
        }
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }

    
}