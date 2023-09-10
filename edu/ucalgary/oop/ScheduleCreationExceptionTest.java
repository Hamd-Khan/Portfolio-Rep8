package edu.ucalgary.oop;
import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
/** 
 * This class includes junit tests for ScheduleCreationException.java
 * ENSF 380: Term Project 2023-04-06
    @Authors    Group 11 (Dominic Gartner <a href = "mailto:dominic.gartner@ucalgary.ca">dominic.gartner@ucalgary.ca</a>, 
                          Hamd Khan <a href = "mailto:hamd.khan1@ucalgary.ca">hamd.khan1@ucalgary.ca</a>, 
                          Alex Mclean <a href = "mailto:alexander.mclean@ucalgary.ca">alexander.mclean@ucalgary.ca</a>, 
                          Kaylyn Tanton <a href = "mailto:kaylyn.tanton@ucalgary.ca">kaylyn.tanton@ucalgary.ca</a>)
    @Version    2.8
    @since      1.0
 */
public class ScheduleCreationExceptionTest {
    
     /**
     * Test if getHour() returns the correct hour
     */
    @Test
    public void testGetHour() {
        String message = "Test exception";
        int hour = 12;
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(123, "Pet Dog", 30, 2, 12, 22, "Bailey"));
        new ScheduleCreationException(message, tasks, hour);
        assertEquals("getHour() failed to return the correct hour", hour, ScheduleCreationException.getHour());
    }

     /**
     * Test if getTasks() returns the correct List of tasks
     */
    @Test
    public void testGetTasks() {
        String message = "Test exception";
        int hour = 12;
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(123, "Pet Dog", 30, 2, 12, 22, "Bailey"));
        tasks.add(new Task(987, "Wash Dog", 40, 3, 16, 23, "Kona"));
        new ScheduleCreationException(message, tasks, hour);
        assertEquals("getTasks() failed to return the proper list of tasks", tasks, ScheduleCreationException.getTasks());
    }

     /**
     * Test if ScheduleCreationException throws an IllegalArgumentException when tasks is empty
     */
    @Test
    public void testConstructorWithEmptyTasks() {
        String message = "Schedule creation failed";
        boolean passed = false;
        int hour = 12;
        List<Task> tasks = new ArrayList<>();
        try {
            ScheduleCreationException exception = new ScheduleCreationException(message, tasks, hour);
        } catch (IllegalArgumentException e) {
            passed = true;
        } catch (Exception e) {
            assertTrue("ScheduleCreationException did not throw an IllegalArgumentException when tasks is empty", passed);
        }
    }
    
    
     /**
     * Test if ScheduleCreationException throws an IllegalArgumentException when hour is negative
     */
    @Test
    public void testConstructorWithNegativeHour() {
        String message = "Schedule creation failed";
        boolean passed = false;
        int hour = -1;
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(123, "Pet Dog", 30, 2, 12, 22, "Bailey"));
        try {
            ScheduleCreationException exception = new ScheduleCreationException(message, tasks, hour);
        } catch (IllegalArgumentException e) {
            passed = true;
        } catch (Exception e) {
            assertTrue("ScheduleCreationException did not throw an IllegalArgumentException when hour is negaitve", passed);
        }
    }
    


     /**
     * Test if ScheduleCreationException throws an IllegalArgumentException when hour greater than 23
     */
    @Test
    public void testConstructorWithHourGreaterThan23() {
        String message = "Schedule creation failed";
        boolean passed = false;
        int hour = 29;
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(123, "Pet Dog", 30, 2, 12, 22, "Bailey"));
        try {
            ScheduleCreationException exception = new ScheduleCreationException(message, tasks, hour);
        } catch (IllegalArgumentException e) {
            passed = true;
        } catch (Exception e) {
            assertTrue("ScheduleCreationException did not throw an IllegalArgumentException when tasks is too large", passed);
        }
    }
}
