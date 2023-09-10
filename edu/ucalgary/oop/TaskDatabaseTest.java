package edu.ucalgary.oop;
import org.junit.*;
import static org.junit.Assert.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDatabaseTest {


            // Tests for getTasks
     /**
     * Test that the proper number of tasks are returned from getTasks().
     * (used database provided on D2L)
     */
    @Test
    public void testGetTasksLength() {
        Task[] tasks = TaskDatabase.getTasks();
        assertEquals("getTasks() did not return the correct amount of tasks", 30, tasks.length);
    }
    /**
     * test whether the getTasks() method correctly throws an SQLException when passing an invalid username and passowrd to the DriverManager.
     * (used database provided on D2L)
     */
    @Test
    public void testGetTasksSQLException() {
        boolean passed = false;
        try {
            Connection myConnect = DriverManager.getConnection("jdbc:mysql://localhost/EWR", "invalidUsername", "invalidPassword");
            TaskDatabase.getTasks();
        } catch (SQLException e) {
            passed = true;
        } catch(Exception e) {
            assertTrue("getTasks did not throw an SQLException when passing an invalid username and passowrd to the DriverManager", passed);
        }
    }
    /**
     * Test getTasks() to determine if the proper tasks are returned for the first 3 indexes.
     * (used database provided on D2L)
     */
    @Test
    public void testGetTasksFirst3Indexes() {
        Task[] tasks = TaskDatabase.getTasks();
        assertEquals("getTaskID() did not return the correct task ID", 1, tasks[0].getTaskID());
        assertEquals("getDescription() did not return the correct description","Kit feeding", tasks[0].getDescription());
        assertEquals("getDuration() did not return the correct duration", 30, tasks[0].getDuration());
        assertEquals("getMaxWindow() did not return the correct max window", 2, tasks[0].getMaxWindow());
        assertEquals("getStartHour() did not return the correct start hour", 0, tasks[0].getStartHour());
        assertEquals("getAnimalID() did not return the correct animal ID", 6, tasks[0].getAnimalID());
        assertEquals("getNickname() did not return the correct name", "Annie, Oliver and Mowgli", tasks[0].getNickname());

        assertEquals("getTaskID() did not return the correct task ID",1, tasks[1].getTaskID());
        assertEquals("getDescription() did not return the correct description","Kit feeding", tasks[1].getDescription());
        assertEquals("getDuration() did not return the correct duration",30, tasks[1].getDuration());
        assertEquals("getMaxWindow() did not return the correct max window",2, tasks[1].getMaxWindow());
        assertEquals("getStartHour() did not return the correct start hour",2, tasks[1].getStartHour());
        assertEquals("getAnimalID() did not return the correct animal ID",6, tasks[1].getAnimalID());
        assertEquals("getNickname() did not return the correct name","Annie, Oliver and Mowgli", tasks[1].getNickname());

        assertEquals("getTaskID() did not return the correct task ID",1, tasks[2].getTaskID());
        assertEquals("getDescription() did not return the correct description", "Kit feeding", tasks[2].getDescription());
        assertEquals("getDuration() did not return the correct duration",30, tasks[2].getDuration());
        assertEquals("getMaxWindow() did not return the correct max window",2, tasks[2].getMaxWindow());
        assertEquals("getStartHour() did not return the correct start hour",4, tasks[2].getStartHour());
        assertEquals("getAnimalID() did not return the correct animal ID",6, tasks[2].getAnimalID());
        assertEquals("getNickname() did not return the correct name","Annie, Oliver and Mowgli", tasks[2].getNickname());
    }
   


            // Tests for getNumSpeciesNoOrphan()

    /**
     * Check whether the getNumSpeciesNoOrphan() method returns the correct number of non-orphaned animals of species "coyote".
     * (used database provided on D2L)
     */
    @Test
    public void testGetNumSpeciesNoOrphanCoyote() {
        int result = TaskDatabase.getNumSpeciesNoOrphan("coyote");
        assertEquals("getAnimalsNoOrphan method did not return the correct amount of coyotes present in the database",8, result);
    }
    /**
     * Check whether the getNumSpeciesNoOrphan() method returns the correct number of non-orphaned animals of species "porcupine".
     * (used database provided on D2L)
     */
    @Test
    public void testGetNumSpeciesNoOrphanPorcupine() {
        int result = TaskDatabase.getNumSpeciesNoOrphan("porcupine");
        assertEquals("getAnimalsNoOrphan method did not return the correct amount of porcupine's present in the database",5, result);
    }
     /**
     * Check whether the getNumSpeciesNoOrphan() method returns the correct number of non-orphaned animals of species "fox".
     * (used database provided on D2L)
     */
    @Test
    public void testGetNumSpeciesNoOrphanFox() {
        int result = TaskDatabase.getNumSpeciesNoOrphan("fox");
        assertEquals("getAnimalsNoOrphan method did not return the correct amount of fox's present in the database",1, result);
    }
    /**
     * Check whether the getNumSpeciesNoOrphan() method returns the correct number of non-orphaned animals of species "beaver".
     * (used database provided on D2L)
     */
    @Test
    public void testGetNumSpeciesNoOrphanBeaver() {
        int result = TaskDatabase.getNumSpeciesNoOrphan("beaver");
        assertEquals("getAnimalsNoOrphan method did not return the correct amount of beaver's present in the database",0, result);
    }
    /**
     * Check whether the getNumSpeciesNoOrphan() method returns the correct number of non-orphaned animals of species "racoon".
     * (used database provided on D2L)
     */
    @Test
    public void testGetNumSpeciesNoOrphanRacoon() {
        int result = TaskDatabase.getNumSpeciesNoOrphan("racoon");
        assertEquals("getAnimalsNoOrphan method did not return the correct amount of racoon's present in the database",0, result);
    }
    /**
     * Check whether the getNumSpeciesNoOrphan() method returns the correct number of non-orphaned animals of null species.
     * (used database provided on D2L)
     */
    @Test
    public void testGetNumSpeciesNoOrphanNullSpecies() {
        int result = TaskDatabase.getNumSpeciesNoOrphan(null);
        assertEquals("getAnimalsNoOrphan method did not return 0 animal count for null species",0, result);
    }
    /**
     * Check whether the getNumSpeciesNoOrphan() method returns the correct number of non-orphaned animals for empty species.
     * (used database provided on D2L)
     */
    @Test
    public void testGetNumSpeciesNoOrphanEmptySpecies() {
        int result = TaskDatabase.getNumSpeciesNoOrphan("");
        assertEquals("getAnimalsNoOrphan method did not return 0 animal count for empty species", 0, result);
    }
    /**
     * Check whether the getNumSpeciesNoOrphan() method returns the correct number of non-orphaned animals of non cared for species type.
     * (used database provided on D2L)
     */
    @Test
    public void testGetNumSpeciesNoOrphanNotSpecies() {
        int result = TaskDatabase.getNumSpeciesNoOrphan("Lion");
        assertEquals("getAnimalsNoOrphan method did not return 0 animal count for species not cared for by Wildlife Rescue Center", 0, result);
    }
    /**
     * test whether the getNumSpeciesNoOrphan() method correctly throws an SQLException when passing an invalid username and password to the DriverManager.
     * (used database provided on D2L)
     */
    @Test
    public void testGetNumSpeciesNoOrphanSQLException() {
        boolean passed = false;
        try {
            Connection myConnect = DriverManager.getConnection("jdbc:mysql://localhost/EWR", "invalidUsername", "invalidPassword");
            TaskDatabase.getNumSpeciesNoOrphan("Lion");
        } catch (SQLException e) {
            passed = true;
        } catch(Exception e) {
            assertTrue("getNumSpeciesNoOrphan did not throw an SQLException when passing an invalid username and passowrd to the DriverManager", passed);
        }
    }
    

                        // Tests for getAnimals()

    /**
     * Test that the proper number of animals are returned from getAnimals().
     * (used database provided on D2L)
     */
    @Test
    public void testGetAnimalsLength() {
        Animal[] animals = TaskDatabase.getAnimals();
        assertEquals(15, animals.length);
    }
    /**
     * test whether the getNumSpeciesNoOrphan() method correctly throws an SQLException when passing an invalid username and passowrd to the DriverManager.
     * (used database provided on D2L)
     */
    @Test
    public void testGetAnimalsSQLException() {
        boolean passed = false;
        try {
            Connection myConnect = DriverManager.getConnection("jdbc:mysql://localhost/EWR", "invalidUsername", "invalidPassword");
            TaskDatabase.getAnimals();
        } catch (SQLException e) {
            passed = true;
        } catch(Exception e) {
            assertTrue("getNumSpeciesNoOrphan did not throw an SQLException when passing an invalid username and passowrd to the DriverManager", passed);
        }
    }
    /**
     * Test getAnimals() to determine if the proper animal values are returned (name, species) for the first 6 indexes.
     * (used database provided on D2L)
     */
    @Test
    public void testGetAnimalsFirst6Indexes() {
        Animal[] animals = TaskDatabase.getAnimals();
        assertEquals("getName() did not return the correct animal name","Loner", animals[0].getName());
        assertEquals("getSpecies() did not return the correct animal species","coyote", animals[0].getSpecies());
        assertEquals("getName() did not return the correct animal name","Biter", animals[1].getName());
        assertEquals("getSpecies() did not return the correct animal species","coyote", animals[1].getSpecies());
        assertEquals("getName() did not return the correct animal name","Bitter", animals[2].getName());
        assertEquals("getSpecies() did not return the correct animal species","coyote", animals[2].getSpecies());
        assertEquals("getName() did not return the correct animal name","Pencil", animals[3].getName());
        assertEquals("getSpecies() did not return the correct animal species","coyote", animals[3].getSpecies());
        assertEquals("getName() did not return the correct animal name","Eraser", animals[4].getName());
        assertEquals("getSpecies() did not return the correct animal species","coyote", animals[4].getSpecies());
        assertEquals("getName() did not return the correct animal name","Annie, Oliver and Mowgli", animals[5].getName());
        assertEquals("getSpecies() did not return the correct animal species","fox", animals[5].getSpecies());
    }



                    // Tests for getAnimalsNoOrphan()

    /**
     * Test that the proper number of animals are returned from getAnimalsNoOrphan().
     * (used database provided on D2L)
     */
    @Test
    public void testGetAnimalsNoOrphanLength() {
        Animal[] animals = TaskDatabase.getAnimalsNoOrphan();
        assertEquals("getAnimalsNoOrphan() did not return the correct amount of non-orphaned animals", 14, animals.length);
    }
    /**
     * test whether the getAnimalsNoOrphan() method correctly throws an SQLException when passing an invalid username and passowrd to the DriverManager.
     * (used database provided on D2L)
     */
    @Test
    public void testGetAnimalsNoOrphanSQLException() {
        boolean passed = false;
        try {
            Connection myConnect = DriverManager.getConnection("jdbc:mysql://localhost/EWR", "invalidUsername", "invalidPassword");
            TaskDatabase.getAnimalsNoOrphan();
        } catch (SQLException e) {
            passed = true;
        } catch(Exception e) {
            assertTrue("getNumSpeciesNoOrphan did not throw an SQLException when passing an invalid username and passowrd to the DriverManager", passed);
        }
    }
    /**
     * Test getAnimalsNoOrphan() to determine if the proper animal values are returned (name, species) for the first 6 indexes.
     * (used database provided on D2L)
     */
    @Test
    public void testGetAnimalsNoOrphanFirst6Indexes() {
        Animal[] animals = TaskDatabase.getAnimalsNoOrphan();
        assertEquals("getName() did not return the correct animal name","Loner", animals[0].getName());
        assertEquals("getSpecies() did not return the correct animal species","coyote", animals[0].getSpecies());
        assertEquals("getName() did not return the correct animal name","Biter", animals[1].getName());
        assertEquals("getSpecies() did not return the correct animal species","coyote", animals[1].getSpecies());
        assertEquals("getName() did not return the correct animal name","Bitter", animals[2].getName());
        assertEquals("getSpecies() did not return the correct animal species","coyote", animals[2].getSpecies());
        assertEquals("getName() did not return the correct animal name","Pencil", animals[3].getName());
        assertEquals("getSpecies() did not return the correct animal species","coyote", animals[3].getSpecies());
        assertEquals("getName() did not return the correct animal name","Eraser", animals[4].getName());
        assertEquals("getSpecies() did not return the correct animal species","coyote", animals[4].getSpecies());
        assertEquals("getName() did not return the correct animal name","Slinky", animals[5].getName());
        assertEquals("getSpecies() did not return the correct animal species","fox", animals[5].getSpecies());
    }
   


                // Tests for saveTasks()

    /**
     * Check whether the saveTasks() method correctly updates the task start hour to the database.
     * (used database provided on D2L)
     */
    @Test
    public void testSaveTasks() {
        // get original task list
        Task[] originalTaskList = TaskDatabase.getTasks();
        List<Task> tasks = new ArrayList<>();
        tasks.add(originalTaskList[0]);
        ArrayList<Integer> startHours = new ArrayList<>();
        startHours.add(5);
        assertEquals(0, originalTaskList[0].getStartHour());
        try {
            TaskDatabase.saveTasks(tasks, startHours);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Task[] updatedTaskList = TaskDatabase.getTasks();
        Task updatedTask = null;
        for (Task task : updatedTaskList) {
            if (task.getTaskID() == tasks.get(0).getTaskID() &&
                task.getAnimalID() == tasks.get(0).getAnimalID() &&
                task.getStartHour() == startHours.get(0)) {
                updatedTask = task;
                break; }
        }
        assertNotNull(updatedTask);
        assertEquals(5, updatedTask.getStartHour());
        // revert the database back to its original state
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/EWR", "oop", "password");
            String sql = "UPDATE Treatments SET StartHour = 0 WHERE TaskID = " + updatedTask.getTaskID() + " AND AnimalID = " +
                         updatedTask.getAnimalID() + " And StartHour = " + updatedTask.getStartHour();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Test if the saveTasks() method correctly throws an SQL exception when start hours are null.
     * (used database provided on D2L)
     */
    @Test
    public void testSaveTasksWithNullStartHours() {
        boolean passed = false;
        List<Task> tasks = new ArrayList<>();
        Task[] taskList = TaskDatabase.getTasks();
        tasks.add(taskList[0]);
        ArrayList<Integer> startHours = null;
        try {
            TaskDatabase.saveTasks(tasks, startHours);
        } catch (SQLException e) {
            passed = true;
        } catch(Exception e) {
            assertEquals("Null start hours list did not throw an exception", e.getMessage());
        }
    }

}
