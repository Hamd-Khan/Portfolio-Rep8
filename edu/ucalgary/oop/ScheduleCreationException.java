package edu.ucalgary.oop;
import java.util.List;

/** 
 * An exception that is thrown when the schedule is unable to be made.
 * ENSF 380: Term Project 2023-04-06
    @Authors    Group 11 (Dominic Gartner <a href = "mailto:dominic.gartner@ucalgary.ca">dominic.gartner@ucalgary.ca</a>, 
                          Hamd Khan <a href = "mailto:hamd.khan1@ucalgary.ca">hamd.khan1@ucalgary.ca</a>, 
                          Alex Mclean <a href = "mailto:alexander.mclean@ucalgary.ca">alexander.mclean@ucalgary.ca</a>, 
                          Kaylyn Tanton <a href = "mailto:kaylyn.tanton@ucalgary.ca">kaylyn.tanton@ucalgary.ca</a>)
    @Version    2.8
    @since      1.0
 */
public class ScheduleCreationException extends Exception {
    private static int hour;
    private static List<Task> tasks;

    /**
     * Overload constructor for ScheduleCreationException
     */
    public ScheduleCreationException(String s, List<Task> tasks, int hour) throws IllegalArgumentException {
        if(tasks == null || hour > 23 || hour < 0) {
            throw new IllegalArgumentException("Something in the animals information is missing or invalid");
        }
        System.err.println(s);
        ScheduleCreationException.hour = hour;
        ScheduleCreationException.tasks = tasks;
    }

     /**
     * returns the hour that caused the exception to be thrown
     * @return The hour that caused the exception to be thrown
     */
    public static int getHour() {
        return hour;
    }
    /**
     * return the list of tasks within the hour that the exception was thrown
     * @return The list of tasks within the hour that the exception was thrown
     */
    public static List<Task> getTasks() {
        return tasks;
    }
}