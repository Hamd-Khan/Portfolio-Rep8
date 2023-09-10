package edu.ucalgary.oop;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/** 
 * This class includes all of the SQL queries required for creating and saving the schedule.
 * ENSF 380: Term Project 2023-04-06
    @Authors    Group 11 (Dominic Gartner <a href = "mailto:dominic.gartner@ucalgary.ca">dominic.gartner@ucalgary.ca</a>, 
                          Hamd Khan <a href = "mailto:hamd.khan1@ucalgary.ca">hamd.khan1@ucalgary.ca</a>, 
                          Alex Mclean <a href = "mailto:alexander.mclean@ucalgary.ca">alexander.mclean@ucalgary.ca</a>, 
                          Kaylyn Tanton <a href = "mailto:kaylyn.tanton@ucalgary.ca">kaylyn.tanton@ucalgary.ca</a>)
    @Version    2.8
    @since      1.0
 */
public class TaskDatabase {
    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    private static Task[] tasks = null;
    private static Animal[] animals = null;
    
    /**
     * Retrieves all tasks from the EWR database using JDBC and returns them as an array of Task objects.
     * @return An array of medical tasks that need to be completed for all the animals in the wildlife rescue center.   
     * @throws SQLException if a database access error occurs
     * @throws ClassNotFoundException if the JDBC driver class is not found
     */
    public static Task[] getTasks() {
        try {
            // Connect to the EWR database using JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/EWR", "oop", "password");
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            // Build the SQL query to retrieve all task data from the database
            rs = stmt.executeQuery("SELECT T.AnimalID, T.StartHour, TS.Duration, TS.Description, TS.TaskID, TS.MaxWindow, A.AnimalNickname, A.AnimalSpecies " +
            "FROM TREATMENTS T INNER JOIN TASKS TS ON T.TaskID = TS.TaskID INNER JOIN ANIMALS A ON T.AnimalID = A.AnimalID ORDER BY T.StartHour ASC;");
            rs.last();
            int numRows = rs.getRow();
            rs.beforeFirst();
            tasks = new Task[numRows];
            int i = 0;
            // Loop through the result set and create a Task object for each row
            while (rs.next()) {
                tasks[i] = new Task(
                        rs.getInt("TaskID"),
                        rs.getString("Description"),
                        rs.getInt("Duration"),
                        rs.getInt("MaxWindow"),
                        rs.getInt("StartHour"),
                        rs.getInt("AnimalID"),
                        rs.getString("AnimalNickname"));
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                // Close the database connection and statements
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace(); }
        }
        return tasks;
    }

    /**
     * Returns an Animal array object of all the animals within the database.
     * @return An array of animals that are currently being cared for within the wildlife rescue center.   
     * @throws SQLException if a database access error occurs
     * @throws ClassNotFoundException if the JDBC driver class is not found
     */
    public static Animal[] getAnimals()  {
        try {
            // Connect to the EWR database using JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/EWR", "oop", "password");
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            // Build the SQL query to retrieve all animals' nicknames and species
            rs = stmt.executeQuery("SELECT A.AnimalNickname, A.AnimalSpecies FROM Animals A;");
            rs.last();
            int numRows = rs.getRow();
            rs.beforeFirst();
            // Create an array to store the Animal objects to be returned
            animals = new Animal[numRows];
            int i = 0;
            // Iterate through the ResultSet and create an Animal object for each row
            while (rs.next()) {
                animals[i] = new Animal(
                        rs.getString("AnimalNickname"),
                        rs.getString("AnimalSpecies"));
                i++;
            }
        } catch (SQLException e) {
             e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                // Close the ResultSet, Statement, and Connection objects
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace(); }
        }
        return animals;
    }

    /**
     * Returns an Animal array object of all the animals within the database that are not orphaned.
     * @return An array of animals that do not have a TaskID = 1 associated with them that are currently being cared for within the wildlife rescue center.   
     * @throws SQLException if a database access error occurs
     * @throws ClassNotFoundException if the JDBC driver class is not found
     */
    public static Animal[] getAnimalsNoOrphan() {
        try {
            // Connect to the EWR database using JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/EWR", "oop", "password");
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            // Build the SQL query to retrieve all animals except orphans
            rs = stmt.executeQuery("SELECT AnimalNickname, AnimalSpecies FROM ANIMALS WHERE AnimalID NOT IN (SELECT AnimalID FROM TREATMENTS WHERE TaskID = 1);");
            rs.last();
            int numRows = rs.getRow();
            rs.beforeFirst();
            animals = new Animal[numRows];
            int i = 0;
            // Create a new Animal object for each record retrieved from the database
            while (rs.next()) {
                animals[i] = new Animal(
                        rs.getString("AnimalNickname"),
                        rs.getString("AnimalSpecies"));
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                // Close the ResultSet, Statement, and Connection objects
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace(); }
        }
        return animals;
    }

     /**
    * Saves the list of tasks with their respective start hours to the database.
    * @param tasks the list of tasks to save
    * @param startHours the list of start hours corresponding to each task
    * @throws SQLException if there is an error executing the SQL statement
    */
    public static void saveTasks(List<Task> tasks, ArrayList<Integer> startHours) throws SQLException {
        PreparedStatement stmt = null;
        int j = 0;
        for (Task task : tasks)
        {
            try {
                // Get the connection and create the prepared statement
                conn = DriverManager.getConnection("jdbc:mysql://localhost/EWR", "oop", "password");
                String sql = "UPDATE Treatments SET StartHour = " + startHours.get(j) +
                " WHERE TaskID = " + task.getTaskID() + " AND AnimalID = " + task.getAnimalID() + " AND StartHour = " + task.getStartHour(); 
                j++;
                stmt = conn.prepareStatement(sql);
                stmt.executeUpdate();
            } catch (Exception e) {
                throw new SQLException("Error executing the SQL query: " + e.getMessage());
            } finally {
                // Close the statement and connection
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            }
        }
    }

    /**
     * Used to retrieve the number of animals in a specific species in the database who are not orphans
     * @param species species of the animal.
     * @return an integer representing the count of the number of animals of that specific species within the database which do not have a taskID = 1 associated with them
     */
    public static int getNumSpeciesNoOrphan(String species) {
        int count = 0;
        try {
            // Connect to the EWR database using JDBC
            Connection myConnect = DriverManager.getConnection("jdbc:mysql://localhost/EWR", "oop", "password");
            Statement mystmt = myConnect.createStatement();
            // Build the SQL query
            String query = "SELECT COUNT(*) AS count FROM Animals WHERE AnimalSpecies = '"+ species + "' AND AnimalID NOT IN ( " +
              "SELECT AnimalID FROM Treatments WHERE TaskID = 1);";
            ResultSet results = mystmt.executeQuery(query);
            // Retrieve the count from the SQL results
            while (results.next()) {
                count = results.getInt("count");
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return count;
    }
}


