package edu.ucalgary.oop;
/** 
 * This class implements functionality for the task objects used in the schedule.
 * ENSF 380: Term Project 2023-04-06
    @Authors    Group 11 (Dominic Gartner <a href = "mailto:dominic.gartner@ucalgary.ca">dominic.gartner@ucalgary.ca</a>, 
                          Hamd Khan <a href = "mailto:hamd.khan1@ucalgary.ca">hamd.khan1@ucalgary.ca</a>, 
                          Alex Mclean <a href = "mailto:alexander.mclean@ucalgary.ca">alexander.mclean@ucalgary.ca</a>, 
                          Kaylyn Tanton <a href = "mailto:kaylyn.tanton@ucalgary.ca">kaylyn.tanton@ucalgary.ca</a>)
    @Version    2.8
    @since      1.0
 */
public class Task {
    
    private int taskID;
    private String description;
    private int duration;
    private int maxWindow;
    private int startHour;
    private int animalID;
    private String nickname;

     /**
     * Overload constructor.
     * @param taskID ID for the task found in database.
     * @param description Description of the task.
     * @param duration Task duration.
     * @param maxWindow How long the task must be completed after the start hour.
     * @param startHour Hour the task completion can begin.
     * @param animalID ID of the animal in the database.
     * @param nickname Name of the animal.
     */
    public Task(int taskID, String description, int duration, int maxWindow, int startHour, int animalID, String nickname) throws IllegalArgumentException {
        if (maxWindow <= 0 || duration <= 0 || startHour > 23 ||startHour < 0 || taskID < 0 || maxWindow > 24 || animalID < 0 || duration > 120) {
            throw new IllegalArgumentException("Something in the animals information is missing or invalid");
        }
        this.taskID = taskID;
        this.description = description;
        this.duration = duration;
        this.maxWindow = maxWindow;
        this.startHour= startHour;
        this.animalID = animalID;
        this.nickname = nickname;
    }

     /**
     * Overload constructor.     
     * @param description Description of the task.
     * @param nickname Name of the animal.
     * @param startHour Hour the task completion can begin.
     * @param maxWindow How long the task must be completed after the start hour.
     * @param duration Task duration.
     */
    public Task(String description, String nickname, int startHour, int  maxWindow, int duration) throws IllegalArgumentException {
        if (maxWindow <= 0 || duration <= 0 || startHour > 23 || startHour < 0 || maxWindow > 24 || duration > 120){
            throw new IllegalArgumentException("Something in the animals information is missing or invalid");
        }
        this.description = description;
        this.nickname = nickname;
        this.startHour = startHour;
        this.maxWindow = maxWindow;
        this.duration = duration;
    }
    
     /**
     * return the tasks ID
     * @return The ID for the task object.     
     */
    public int getTaskID() {
        return taskID;
    }

     /**
     * return the tasks description
     * @return The description for the task object.     
     */
    public String getDescription() {
        return description;
    }

     /**
     * return the tasks duration
     * @return The duration for the task object.     
     */
    public int getDuration() {
        return duration;
    }

    /**
     * return the animals ID associated with the task
     * @return The animals ID associated with the task.
     */
    public int getAnimalID() {
        return animalID;
    }
    
     /**
     * return the tasks max window
     * @return The max window for the task object.     
     */
    public int getMaxWindow() {
        return maxWindow;
    }

     /**
     * return the tasks start hour
     * @return The maxWindow for the task object.     
     */
    public int getStartHour() {
        return startHour;
    }

     /**
     * set the tasks start hour
     * @param startHour The start hour for the task object     
     */
    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

     /**
     * return the animals name for the task
     * @return The animals name for the task object.     
     */
    public String getNickname() {
        return nickname;
    }

}