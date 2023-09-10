package edu.ucalgary.oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * This class displays the GUI with the final generated schedule. Includes
 * functionality for saving the schedule to the users device.
 * ENSF 380: Term Project 2023-04-06
 * 
 * @Authors Group 11 (Dominic Gartner <a href =
 *          "mailto:dominic.gartner@ucalgary.ca">dominic.gartner@ucalgary.ca</a>,
 *          Hamd Khan <a href =
 *          "mailto:hamd.khan1@ucalgary.ca">hamd.khan1@ucalgary.ca</a>,
 *          Alex Mclean <a href =
 *          "mailto:alexander.mclean@ucalgary.ca">alexander.mclean@ucalgary.ca</a>,
 *          Kaylyn Tanton <a href =
 *          "mailto:kaylyn.tanton@ucalgary.ca">kaylyn.tanton@ucalgary.ca</a>)
 * @Version 2.8
 * @since 1.0
 */
public class Schedule implements ActionListener {
    private JFrame frame;
    private AnimalTaskScheduler animalTaskScheduler = new AnimalTaskScheduler();
    private ArrayList<Task> taskArrayList = new ArrayList<>();

    /**
     * Default constructor constructs a new Schedule object and calls the many
     * private methods to implement the GUI functionality
     * 
     * @throws ScheduleCreationException if an error occurs while creating the
     *                                   schedule
     */
    public Schedule() {
        try {
            // Get the tasks from the database
            Task[] tasks = TaskDatabase.getTasks();
            for (Task task : tasks) {
                taskArrayList.add(task);
            }
            String scheduleString = AnimalTaskScheduler.makeSchedule();
            this.frame = initializeSchedule(scheduleString);
            saveButtonCreate(scheduleString);
        } catch (ScheduleCreationException e) {
            editTaskWindow();
        }
    }

    /**
     * Displays a confirmation dialog asking the user if they would like to edit the
     * tasks that caused an error
     */
    private void editTaskWindow() {
        int option = JOptionPane.showConfirmDialog(frame,
                "An error occurred while creating the schedule at hour " + ScheduleCreationException.getHour() +
                        ". Would you like to edit the tasks at this hour?");
        if (option == JOptionPane.YES_OPTION) {
            // Open the task editor GUI
            new TaskEditor(ScheduleCreationException.getTasks(), this, taskArrayList);
        }
    }

    /**
     * Creates a JButton and adds it to the frame's SOUTH. Calls the
     * confirmVolunteer method to add the ActionListener.
     * 
     * @param scheduleString A string representation of the schedule.
     * @return A JButton for saving the schedule.
     */
    private JButton saveButtonCreate(String scheduleString) {
        JButton saveButton = new JButton("Confirm and Save Schedule");
        confirmVolunteer(saveButton, scheduleString);
        frame.add(saveButton, BorderLayout.SOUTH);
        frame.setVisible(true);
        return saveButton;
    }

    /**
     * Adds an ActionListener to the given saveButton which confirms whether a
     * backup volunteer is required for any hour before saving the schedule.
     * 
     * @param saveButton     the JButton to which the ActionListener is added.
     * @param scheduleString the string representation of the schedule to be saved.
     */
    private void confirmVolunteer(JButton saveButton, String scheduleString) {
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if any hour requires a backup volunteer
                for (int hour = 0; hour < 24; hour++) {
                    if (requiresBackupVolunteer(hour, taskArrayList)) {
                        JOptionPane optionPane = new JOptionPane(" A volunteer is required for hour " + hour +
                                ". Have you confirmed calling the backup volunteer?", JOptionPane.QUESTION_MESSAGE,
                                JOptionPane.YES_NO_OPTION);
                        JDialog dialog = optionPane.createDialog(frame, "Backup Volunteer Confirmation");
                        dialog.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosing(WindowEvent e) {
                                optionPane.setValue(JOptionPane.NO_OPTION);
                            }
                        });
                        dialog.setVisible(true);
                        int response = (int) optionPane.getValue();
                        if (response == JOptionPane.NO_OPTION) {
                            return;
                        }
                    }
                }
                fileSave(scheduleString);
            }
        });
    }

    /**
     * Prompts the user to select a location to save the generated schedule as a
     * text file.
     * Writes the schedule string to the selected file using a FileWriter object.
     * 
     * @param scheduleString The schedule string to be saved to the selected file.
     */
    private void fileSave(String scheduleString) {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showSaveDialog(frame);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                FileWriter writer = new FileWriter(file);
                writer.write(scheduleString);
                writer.close();
                JOptionPane.showMessageDialog(frame, "Schedule saved successfully!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Error saving schedule: " + ex.getMessage(), "Save Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Initializes the JFrame that displays the animal task schedule.
     * 
     * @param scheduleString The string representation of the animal task schedule.
     * @return The initialized JFrame object.
     */
    private JFrame initializeSchedule(String scheduleString) {
        JFrame frame = new JFrame("Task Schedule");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(900, 650);
        frame.setLayout(new BorderLayout());
        // Create the title label
        JLabel titleLabel = new JLabel("Schedule for " + LocalDate.now());
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(49, 83, 125));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(titleLabel, BorderLayout.NORTH);
        // Create the text area with the schedule
        JTextArea scheduleTextArea = new JTextArea(scheduleString);
        scheduleTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(scheduleTextArea);
        frame.add(scrollPane, BorderLayout.CENTER);
        return frame;
    }

    /**
     * Determines if a backup volunteer is required for a given hour based on the
     * total duration of tasks scheduled for that hour.
     * 
     * @param hour          the hour to check for backup volunteer requirement
     * @param taskArrayList the list of tasks scheduled for the given hour
     * @return true if the total duration of tasks scheduled for the given hour is
     *         greater than 60 minutes, false otherwise
     */
    public boolean requiresBackupVolunteer(int hour, ArrayList<Task> taskArrayList) {
        int[] array = animalTaskScheduler.getTotalHourlyTime();
        int sum = array[hour];
        return sum > 60;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Nothing to be done
    }

}