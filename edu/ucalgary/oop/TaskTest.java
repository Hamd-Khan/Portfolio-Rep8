package edu.ucalgary.oop;
import org.junit.*;
import static org.junit.Assert.*;
/** 
 * This class includes junit tests for Tasks.java
 * ENSF 380: Term Project 2023-04-06
    @Authors    Group 11 (Dominic Gartner <a href = "mailto:dominic.gartner@ucalgary.ca">dominic.gartner@ucalgary.ca</a>, 
                          Hamd Khan <a href = "mailto:hamd.khan1@ucalgary.ca">hamd.khan1@ucalgary.ca</a>, 
                          Alex Mclean <a href = "mailto:alexander.mclean@ucalgary.ca">alexander.mclean@ucalgary.ca</a>, 
                          Kaylyn Tanton <a href = "mailto:kaylyn.tanton@ucalgary.ca">kaylyn.tanton@ucalgary.ca</a>)
    @Version    2.8
    @since      1.0
 */
public class TaskTest {
    private final int TASKID = 123;
    private final String DESCRIPTION = "Feed the lions";
    private final int DURATION = 30;
    private final int MAXWINDOW = 23;
    private final int STARTHOUR = 10;
    private final int ANIMALID = 456;
    private final String NICKNAME = "Simba";
    private final Task TASK = new Task(TASKID, DESCRIPTION, DURATION, MAXWINDOW, STARTHOUR, ANIMALID, NICKNAME);

    /**
     * Check whether the getTaskID() method returns the correct task ID.
     */
    @Test
    public void testGetTaskID() {
        assertEquals("getTaskID did not return the correct Task ID",TASKID, TASK.getTaskID());
    }
    /**
     * Check whether the getDescription() method returns the correct task description.
    */
    @Test
    public void testGetDescription() {
        assertEquals("getDescription did not return the correct description",DESCRIPTION, TASK.getDescription());
    }
    /**
     * Check whether the getDuration() method returns the correct task duration.
    */
    @Test
    public void testGetDuration() {
        assertEquals("getDuration did not return the correct duration",DURATION, TASK.getDuration());
    }
    /**
     * Check whether the getAnimalID() method returns the correct animal ID.
    */
    @Test
    public void testGetAnimalID() {
        assertEquals("getAnimalID did not return the correct animal ID",ANIMALID, TASK.getAnimalID());
    }
    /**
     * Check whether the getMaxWindow() method returns the correct maximum window of time for the task.
    */
    @Test
    public void testGetMaxWindow() {
        assertEquals("getMaxWindow did not return the correct max window",MAXWINDOW, TASK.getMaxWindow());
    }
    /**
     * Check whether the getStartHour() method returns the correct start hour for the task.
    */
    @Test
    public void testGetStartHour() {
        assertEquals("getStartHour did not return the correct start hour",STARTHOUR, TASK.getStartHour());
    }
    /**
     * Check whether the setStartHour() method correctly sets the start hour for the task.
    */
    @Test
    public void testSetStartHour() {
        TASK.setStartHour(12);
        assertEquals("setStartHour did not properly set the new start hour",12, TASK.getStartHour());
    }
    /**
     * Check whether the getNickname() method returns the correct nickname for the task.
    */
    @Test
    public void testGetNickname() {
        assertEquals("getNickname did not return the correct animal name",NICKNAME, TASK.getNickname());
    }
    /**
     * Check whether the Task constructor with no task ID parameter correctly initializes a Task object with the given parameters.
    */
    @Test
    public void testConstructorWithNoID() {
        final Task TASK = new Task(DESCRIPTION, NICKNAME, STARTHOUR, MAXWINDOW, DURATION);
        assertEquals("getDescription did not return the correct description",DESCRIPTION, TASK.getDescription());
        assertEquals("getNickname did not return the correct animal name",NICKNAME, TASK.getNickname());
        assertEquals("getStartHour did not return the correct start hour",STARTHOUR, TASK.getStartHour());
        assertEquals("getMaxWindow did not return the correct max window",MAXWINDOW, TASK.getMaxWindow());
        assertEquals("getDuration did not return the correct duration",DURATION, TASK.getDuration());
    }
    /**
     * Check whether the Task constructor with description, nickname, start hour, max window, and duration parameters correctly initializes a Task object.
    */
    @Test
    public void testSecondConstructorTask() {
        Task task = new Task("Feed the lion", "Simba", 8, 23, 30);
        assertEquals("getDescription did not return the correct description", "Feed the lion", task.getDescription());
        assertEquals("getNickname did not return the correct animal name", "Simba", task.getNickname());
        assertEquals("getStartHour did not return the correct start hour", 8, task.getStartHour());
        assertEquals("getMaxWindow did not return the correct max window", 23, task.getMaxWindow());
        assertEquals("getDuration did not return the correct duration", 30, task.getDuration());
    }
    /**
     * Check whether an IllegalArgumentException is thrown when a negative duration is passed to the Task constructor.
    */
    @Test
    public void testNegativeDurationTask() {
        boolean passed = false;
        try {
            new Task(1, "Feed the lion", -30, 60, 8, 1, "Simba");
        } catch (IllegalArgumentException e) {
            passed = true;
        } catch(Exception e) {
            assertTrue("IllegalArgumentException is not thrown when duration < 0 mins", passed);
        }
    }
    /**
     * Check whether an IllegalArgumentException is thrown when a negative max window is passed to the Task constructor.
     */
    @Test
    public void testNegativeMaxWindowTask() {
        boolean passed = false;
        try {
            new Task(1, "Feed the lion", 30, -60, 8, 1, "Simba");
        } catch (IllegalArgumentException e) {
            passed = true;
        } catch(Exception e) {
            assertTrue("IllegalArgumentException is not thrown when maxWindow < 0", passed);
        }
    }
    /**
     * Check whether an IllegalArgumentException is thrown when the start hour is out of range (less than 0 or greater than 24) in the Task constructor.
    */
    @Test
    public void testStartHourOutOfRangeTask() {
        boolean passed = false;
       try {
            new Task(1, "Feed the lion", 30, 60, 29, 1, "Simba");
        } catch (IllegalArgumentException e) {
            passed = true;
        } catch(Exception e) {
            assertTrue("IllegalArgumentException is not thrown when startHour > 23", passed);
        }
    }
    /**
     * Check whether an IllegalArgumentException is thrown when a negative task ID is passed to the Task constructor.
    */
    @Test
    public void testNegativeTaskIDTask() {
        boolean passed = false;
        try {
            new Task(-1, "Feed the lion", 30, 60, 8, 1, "Simba");
        } catch (IllegalArgumentException e) {
            passed = true;
        } catch(Exception e) {
            assertTrue("IllegalArgumentException is not thrown when TaskID < 0", passed);
        }
    }
    /**
     * Check whether an IllegalArgumentException is thrown when a duration of 0 is passed to the Task constructor.
    */
    @Test
    public void testDurationZeroTask() {
        boolean passed = false;
        try {
            new Task(1, "Feed the lion", 0, 60, 8, 1, "Simba");
        } catch (IllegalArgumentException e) {
            passed = true;
        } catch(Exception e) {
            assertTrue("IllegalArgumentException is not thrown when duration = 0 mins", passed);
        }

    }
    /**
     * Check whether an IllegalArgumentException is thrown when a max window of 0 is passed to the Task constructor.
    */
    @Test
    public void testMaxWindowZeroTask() {
        boolean passed = false;
        try {
            new Task(1, "Feed the lion", 30, 0, 8, 1, "Simba");
        } catch (IllegalArgumentException e) {
            passed = true;
        } catch(Exception e) {
            assertTrue("IllegalArgumentException is not thrown when maxWindow = 0", passed);
        }
    }
    /**
     * Check whether an IllegalArgumentException is thrown when the start hour is out of range (less than 0) in the Task constructor.
    */
    @Test
    public void testStartHourOutOfRangeLowTask() {
        boolean passed = false;
        try {
            new Task(1, "Feed the lion", 30, 60, -1, 1, "Simba");
        } catch (IllegalArgumentException e) {
            passed = true;
        } catch(Exception e) {
            assertTrue("IllegalArgumentException is not thrown when startHour < 0", passed);
        }
    }
    /**
     * Check whether an IllegalArgumentException is thrown when the start hour is out of range (greater than 23) in the Task constructor.
    */
    @Test
    public void testStartHourOutOfRangeHighTask() {
        boolean passed = false;
        try {
            new Task(1, "Feed the lion", 30, 60, 25, 1, "Simba");
        }catch (IllegalArgumentException e) {
            passed = true;
        } catch(Exception e) {
            assertTrue("IllegalArgumentException is not thrown when startHour > 23", passed);
        }
    }
    /**
     * Check whether an IllegalArgumentException is thrown when the animal ID is invalid (negative) in the Task constructor.
    */
    @Test
    public void testNegativeAnimalIDTask() {
        boolean passed = false;
        try {
            new Task(1, "Feed the lion", 30, 60, 8, -1, "Simba");
        } catch (IllegalArgumentException e) {
            passed = true;
        } catch(Exception e) {
            assertTrue("IllegalArgumentException is not thrown when animalID < 0", passed);
        }
    }
    /**
     * Check whether an IllegalArgumentException is thrown when the duration is too large (> 120 mins) in the Task constructor.
    */
    @Test
    public void testDurationTooHigh() {
        boolean passed = false;
        try {
            new Task(1, "Feed the lion", 200, 60, 8, -1, "Simba");
        } catch (IllegalArgumentException e) {
            passed = true;
        } catch(Exception e) {
            assertTrue("IllegalArgumentException is not thrown when duration > 120 mins", passed);
        }
    }

}
