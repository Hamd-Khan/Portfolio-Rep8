package edu.ucalgary.oop;
import org.junit.*;
import static org.junit.Assert.*;
import java.sql.*;
import java.util.*;
/** 
 * This class includes junit tests for public methods in AnimalTaskSchedulerTest.java
 * Private methods are tested through the functionality of the public methods
 * ENSF 380: Term Project 2023-04-06
    @Authors    Group 11 (Dominic Gartner <a href = "mailto:dominic.gartner@ucalgary.ca">dominic.gartner@ucalgary.ca</a>, 
                          Hamd Khan <a href = "mailto:hamd.khan1@ucalgary.ca">hamd.khan1@ucalgary.ca</a>, 
                          Alex Mclean <a href = "mailto:alexander.mclean@ucalgary.ca">alexander.mclean@ucalgary.ca</a>, 
                          Kaylyn Tanton <a href = "mailto:kaylyn.tanton@ucalgary.ca">kaylyn.tanton@ucalgary.ca</a>)
    @Version    2.8
    @since      1.0
 */
public class AnimalTaskSchedulerTest {

                // tests for getTotalHourlyTime()

    /**
     * Test that getTotalHourlyTime() returns the proper durations of tasks per hour.
     * (used database provided on D2L)
     */
    @Test
    public void testGetTotalHourlyTime() {
        AnimalTaskScheduler scheduler = new AnimalTaskScheduler();
        int[] expected = {60, 60, 50, 0, 30, 0, 60, 0, 30, 0, 30, 0, 30, 50, 60, 10, 30, 0, 35, 45, 30, 50, 90 ,20};
        int[] actual = scheduler.getTotalHourlyTime();
        assertArrayEquals("getTotalHourlyTime() did not return the expected durations for all hours of the day", expected, actual);
    }

             // tests for getHourlyDurations()

    /**
     * Test that getHourlyDurations() returns the proper duration in string formatting when total duration > 120
     */
    @Test
    public void testGetHourlyDurationsOver120() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(999, "Jump", 50, 4, 11, 90, "Leon"));
        tasks.add(new Task(998, "Kick", 40, 2, 11, 91, "Max"));
        tasks.add(new Task(997, "Roll", 50, 6, 11, 92, "Wayne"));
        String expected = "Hour 11: 140 mins *";
        assertEquals("getHourlyDurations() did not return the correct formatted string for the duration at hour", expected, AnimalTaskScheduler.getHourlyDurations(11, tasks));
    }
    /**
     * Test that getHourlyDurations() returns the proper duration in string formatting when total duration < 120
     */
    @Test
    public void testGetHourlyDurationsUnde120() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(991, "Jump", 30, 4, 13, 99, "Leon"));
        tasks.add(new Task(990, "Roll", 40, 6, 13, 101, "Wayne"));
        String expected = "Hour 13: 70 mins";
        assertEquals("getHourlyDurations() did not return the correct formatted string for the duration at hour", expected, AnimalTaskScheduler.getHourlyDurations(13, tasks));
    }
     /**
     * Test that getHourlyDurations() returns the proper duration in string formatting when tasks are empty (0 mins)
     */
    @Test
    public void testGetHourlyDurationsEmptyList() {
        List<Task> tasks = new ArrayList<>();
        String expected = "Hour 1: 0 mins";
        assertEquals("getHourlyDurations() did not return the correct formatted string for the duration at hour", expected, AnimalTaskScheduler.getHourlyDurations(1, tasks));
    }


            // tests for scheduleTasks()

     /**
     * Test that scheduleTasks() properly initializes the schedule properly even with zero tasks
     * Tests the schedule is initalized and populated correctly
     */
    @Test
    public void testScheduleTasksWithEmptyList() {
        List<Task> tasks = new ArrayList<>();
        List<List<Task>> schedule = AnimalTaskScheduler.scheduleTasks(tasks);
        assertEquals("scheduleTasks() did not return a schedule with room for all 24 hours of the day", 24, schedule.size());
        for (List<Task> tasksThisHour : schedule) {
            assertTrue("scheduleTasks() did not return an empty list of tasks", tasksThisHour.isEmpty());
        }
    }
     /**
     * Test that scheduleTasks() properly initializes the schedule and the proper amount of tasks at each hour
     * Tests the schedule is initalized and populated correctly
     */
    @Test
    public void testScheduleTasksWithSingleTask() {
        Task task = new Task(991, "Jump", 30, 4, 19, 99, "Leon");
        List<Task> tasks = Collections.singletonList(task);
        List<List<Task>> schedule = AnimalTaskScheduler.scheduleTasks(tasks);
        assertEquals("scheduleTasks() did not return a schedule with room for all 24 hours of the day", 24, schedule.size());
        assertEquals("Returned amount of tasks at hour does not match the expected amount of tasks at hour", 1, schedule.get(19).size());
        assertTrue("Schedule at hour does not contain the task", schedule.get(19).contains(task));
    }
    /**
     * Test that scheduleTasks() properly initializes the schedule and the proper amount of tasks at each hour
     * Tests the schedule is initalized and populated correctly
     */
    @Test
    public void testScheduleTasksWithMultipleTasks() {
        Task task1 =new Task(991, "Jump", 30, 1, 13, 99, "Leon");
        Task task2 = new Task(990, "Roll", 40, 1, 14, 101, "Wayne");
        List<Task> tasks = Arrays.asList(task1, task2);
        List<List<Task>> schedule = AnimalTaskScheduler.scheduleTasks(tasks);
        assertEquals("scheduleTasks() did not return a schedule with room for all 24 hours of the day", 24, schedule.size());
        assertEquals("Returned amount of tasks at hour does not match the expected amount of tasks at hour", 1, schedule.get(13).size());
        assertTrue("Schedule at hour does not contain the task", schedule.get(13).contains(task1));
        assertEquals("Returned amount of tasks at hour does not match the expected amount of tasks at hour", 1, schedule.get(14).size());
        assertTrue("Schedule at hour does not contain the task", schedule.get(14).contains(task2));
    }
    /**
     * Test that scheduleTasks() properly initializes the schedule and the proper amount of tasks at each hour
     * Tests the schedule is initalized and populated correctly
     */
    @Test
    public void testScheduleTasksWithTasksAtSameHour() {
        Task task1 = new Task(991, "Jump", 30, 1, 13, 99, "Leon");
        Task task2 = new Task(990, "Roll", 40, 1, 13, 101, "Wayne");
        List<Task> tasks = Arrays.asList(task1, task2);
        List<List<Task>> schedule = AnimalTaskScheduler.scheduleTasks(tasks);
        assertEquals("scheduleTasks() did not return a schedule with room for all 24 hours of the day", 24, schedule.size());
        assertEquals("Returned amount of tasks at hour does not match the expected amount of tasks at hour", 2, schedule.get(13).size());
        assertTrue("Schedule at hour does not contain the task", schedule.get(13).contains(task1));
        assertTrue("Schedule at hour does not contain the task", schedule.get(13).contains(task2));
    }


            // tests for getTasksThisHour()

    /**
     * Test getTasksThisHour() returns the proper amount of tasks for an hour with multiple tasks
     * (used database provided on D2L)
     */
    @Test
    public void testGetTasksThisHourMultipleTasks() {
        List<Task> tasks = AnimalTaskScheduler.getTasksThisHour(0);
        assertNotNull("tasks returned null", tasks);
        assertEquals("getTasksThisHour() did not return 6 tasks (Cage cleaning * 4, kit feeding, Feeding (fox))", 6, tasks.size());
    }
     /**
     * Test getTasksThisHour() returns no tasks for an hour with zero tasks
     * (used database provided on D2L)
     */
    @Test
    public void testGetTasksThisHourNoTasks() {
        List<Task> tasks = AnimalTaskScheduler.getTasksThisHour(11);
        assertNotNull("tasks returned null", tasks);
        assertEquals("getTasksThisHour() did not return no tasks for an hour with zero tasks", 0, tasks.size());
    }
    /**
     * Test getTasksThisHour() returns one tasks for an hour with one task
     * (used database provided on D2L)
     */
    @Test
    public void testGetTasksThisHourOneTask() {
        List<Task> tasks = AnimalTaskScheduler.getTasksThisHour(15);
        assertNotNull("tasks returned null", tasks);
        assertEquals("getTasksThisHour() did not return one tasks for an hour with one task", 1, tasks.size());
    }
    /**
     * Test getTasksThisHour() throws a NullPointerException when hour is invalid
     * (used database provided on D2L)
     */
    @Test
    public void testGetTasksThisHourHourIncorrect() {
        boolean passed = false;
        try {
            List<Task> tasks = AnimalTaskScheduler.getTasksThisHour(-2);
        } catch (NullPointerException e) {
            passed = true;
        } catch (Exception e) {
            assertTrue("getTasksThisHour() did not throw a NullPointerException for invalid hour", passed);}
    }




            // tests for makeSchedule()

    /**
     * checks that the makeSchedule() method returns a non-null and non-empty string
     * when no tasks are present. If ScheduleCreationException thrown, test fails
     */
    @Test
    public void testMakeScheduleIsEmpty() {
        try {
            String schedule = AnimalTaskScheduler.makeSchedule();
            assertNotNull("makeSchedule() returned a null schedule", schedule);
            assertFalse("makeSchedule() returned an empty schedule", schedule.isEmpty());
        } catch (ScheduleCreationException | NullPointerException e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }
    /**
     * Test that makeSchedule() properly adds a backup volunteer when needed 
     * (Database provided on d2l required backup volunteer at hour 22 when schedule made)
     */
    @Test
    public void testMakeScheduleBackupVolunteer() {
        ArrayList<Task> taskArrayList = new ArrayList<>();
        Task[] tasks = TaskDatabase.getTasks();
        for (Task task : tasks) {
            taskArrayList.add(task);
        }
        String scheduleString = "";
        try {
            scheduleString = AnimalTaskScheduler.makeSchedule();
        } catch (ScheduleCreationException e) {
            fail("Exception thrown: " + e.getMessage());
        }
        assertTrue("makeSchedule() did not properly add a backup volunteer to the schedule when required", scheduleString.contains("22:00 [+ backup volunteer]"));
    }
    /**
     * Test that makeSchedule() properly adds hour with no duration
     * (used database provided on d2l)
     */
    @Test
    public void testMakeScheduleTaskCreatedNoDuration() {
        ArrayList<Task> taskArrayList = new ArrayList<>();
        Task[] tasks = TaskDatabase.getTasks();
        for (Task task : tasks) {
            taskArrayList.add(task);
        }
        String scheduleString = "";
        try {
            scheduleString = AnimalTaskScheduler.makeSchedule();
        } catch (ScheduleCreationException e) {
            fail("Exception thrown: " + e.getMessage());
        }
        assertTrue("makeSchedule() did not include the hour when duration was 0", scheduleString.contains("3:00    Total Time: 0"));
    }
    /**
     * Test that makeSchedule() properly adds formatted tasks into the schedule
     * (used database provided on d2l)
     */
    @Test
    public void testMakeScheduleTaskCreated() {
        ArrayList<Task> taskArrayList = new ArrayList<>();
        Task[] tasks = TaskDatabase.getTasks();
        for (Task task : tasks) {
            taskArrayList.add(task);
        }
        String scheduleString = "";
        try {
            scheduleString = AnimalTaskScheduler.makeSchedule();
        } catch (ScheduleCreationException e) {
            fail("Exception thrown: " + e.getMessage());
        }
        assertTrue("makeSchedule() did not include the required task and duration", scheduleString.contains("4:00    Total Time: 30\n- Kit feeding ( Annie, Oliver and Mowgli )    (30min)"));
    }
     /**
     * Test that makeSchedule() throws a ScheduleCreationException with the proper message when duration for a single hour > 120 mins
     * (used database provided on d2l)
     */
    @Test
    public void testMakeScheduleExceptionMessage() throws SQLException {
        // insert test data
        boolean passed = false;
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/EWR", "oop", "password")) {
            String sql = "INSERT INTO TREATMENTS (AnimalID, TaskID, StartHour) VALUES (5, 9, 22), (5, 9, 22), (5, 9, 22), (5, 9, 22);";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.executeUpdate(); }
        } catch (SQLException e) {
            e.printStackTrace(); }
        // get test data and run test
        ArrayList<Task> taskArrayList = new ArrayList<>();
        Task[] tasks = TaskDatabase.getTasks();
        for (Task task : tasks) {
            taskArrayList.add(task); }
        try {
            AnimalTaskScheduler.makeSchedule();
        } catch (ScheduleCreationException e) {
            passed = true;
            assertEquals("makeSchedule() threw ScheduleCreationException with the wrong message", "Duration of tasks in hour 1 exceeds 120 minutes.", e.getMessage());
        } catch(Exception e) {
            assertTrue("ScheduleCreationException was not thrown by makeSchedule()", passed);
        }
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/EWR", "oop", "password")) {
            String sql = "DELETE FROM TREATMENTS WHERE AnimalID = 5 AND TaskID = 9 AND StartHour = 22;";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.executeUpdate(); }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }


}