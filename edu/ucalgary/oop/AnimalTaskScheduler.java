package edu.ucalgary.oop;
import java.util.*;
/** 
 * This class includes all of the logic for scheduling the tasks throughout the day and
 * creates the final schedule that is displayed in the GUI.
 * ENSF 380: Term Project 2023-04-06
    @Authors    Group 11 (Dominic Gartner <a href = "mailto:dominic.gartner@ucalgary.ca">dominic.gartner@ucalgary.ca</a>, 
                          Hamd Khan <a href = "mailto:hamd.khan1@ucalgary.ca">hamd.khan1@ucalgary.ca</a>, 
                          Alex Mclean <a href = "mailto:alexander.mclean@ucalgary.ca">alexander.mclean@ucalgary.ca</a>, 
                          Kaylyn Tanton <a href = "mailto:kaylyn.tanton@ucalgary.ca">kaylyn.tanton@ucalgary.ca</a>)
    @Version    2.8
    @since      1.0
 */
public class AnimalTaskScheduler extends CommonTasks implements Format {
    private static final int NUM_HOURS = 24;
    private static int[] totalHourlyTime; 
    private static List<List<Task>> schedule;
    private List<Task> tasksList;
    private static Task[] tasksArray;
    
    /**
     * Default constructor.
    */
    public AnimalTaskScheduler() {
        totalHourlyTime = new int[NUM_HOURS];
        tasksArray = TaskDatabase.getTasks();
        tasksList = Arrays.asList(tasksArray);
        try {
            schedule = scheduleTasks(tasksList);
        } catch (Exception e) {
            System.out.println("Error scheduling tasks: " + e.getMessage());
            return;
        }
        List<Task> commonTasks = generateCommonTasks();
        scheduleTasksForHour(schedule, groupTasksByStartHour(commonTasks));
        mergeCommonTasksIntoSchedule(schedule);
    }
    
    /**
    * Merges common tasks into the schedule for each hour, removing duplicates.
    * @param schedule a list of tasks scheduled for each hour
    */
    private void mergeCommonTasksIntoSchedule(List<List<Task>> schedule) {
        for (int hour = 0; hour < NUM_HOURS; hour++) {
            List<Task> commonTasksThisHour = schedule.get(hour);
            List<Task> tasksToAdd = new ArrayList<>();
            for (Task task : commonTasksThisHour) {
                tasksToAdd.add(task);
            }
            List<Task> tasksThisHour = schedule.get(hour);
            tasksThisHour.addAll(tasksToAdd);
            Set<Task> taskSet = new HashSet<>(tasksThisHour);
            tasksThisHour.clear();
            tasksThisHour.addAll(taskSet);
        }
    }

    /**
    * Groups animals by their species
    * @return a Map where each key is a species and its corresponding value is a List of animals of that species
    */
    private Map<String, List<Animal>> groupAnimalsBySpecies() {
        Map<String, List<Animal>> animalsBySpecies = new HashMap<>();
        // iterate through all animals in TaskDatabase (excluding orphans)
        for (Animal animal : TaskDatabase.getAnimalsNoOrphan()) {
            String species = animal.getSpecies();
            animalsBySpecies.putIfAbsent(species, new ArrayList<>());
            // add animal to the species' list
            animalsBySpecies.get(species).add(animal);
        }
        return animalsBySpecies;
    }
    
    /**
    * Generates feeding tasks for a list of animals, taking into account the maximum number of animals that can be fed per hour for a given species.
    * @param animals The list of animals to be fed.
    * @param species The species of the animals.
    * @return A list of feeding tasks for the animals.
    */
    private List<Task> generateFeedingTasks(List<Animal> animals, String species) {
        // If the amount of animals needing to be fed is < the max possible ti be fed in the hour, feed all at once, else feed some of them
        if (animals.size() <= getMaxAnimalsToFeed(species)) {
            return feedAllAnimals(animals, species);
        } else {
            return feedAnimalSubset(animals, species);
        }
    }
    
    /**
    * Adds generated feeding tasks to an array of all the feeding tasks for all the animals of a given species
    * @param animals A list of animals of the given species to generate feeding tasks for.
    * @param species The species of animals to generate feeding tasks for.
    * @return A list of feeding tasks generated for the animals.
    */ 
    private List<Task> feedAllAnimals(List<Animal> animals, String species) {
        StringBuilder animalNames = new StringBuilder();
        // iterate through the animals in the list
        for (int i = 0; i < animals.size(); i++) {
            // append the name of the current animal to the string builder
            animalNames.append(animals.get(i).getName());
            // if the current animal is not the last one, append a comma and a space
            if (i < animals.size() - 1) {
                animalNames.append(", "); }
        }
        return Collections.singletonList(new Task("Feeding (" + animals.size() + ": " + animalNames.toString() + ")", species,
                getFeedStartTime(species), 3, calculateFeedTimeAll(TaskDatabase.getNumSpeciesNoOrphan(species), species)));
    }

    /**
    * Adds generated feeding tasks to an array of all the feeding tasks for a subset of animals of a given species.
    * @param animals A list of animals of the given species to generate feeding tasks for.
    * @param species The species of animals to generate feeding tasks for.
    * @return A list of feeding tasks generated for the subset of animals.
    */ 
    private List<Task> feedAnimalSubset(List<Animal> animals, String species) {
        int maxAnimalsPerHour = getMaxAnimalsToFeed(species);
        List<Task> feedingTasks = new ArrayList<>();
        List<Animal> remainingAnimalsList = animals.subList(maxAnimalsPerHour, animals.size());
        feedingTasks.add(generateFeedTaskSub(animals.subList(0, maxAnimalsPerHour), species));
        feedingTasks.add(generateFeedTaskSub(remainingAnimalsList, species));
        return feedingTasks;
    }

    /**
    * Generates a feeding task for a subset of animals of the given species.
    * @param animals the subset of animals to be fed
    * @param species the species of the animals
    * @return a Task object representing the feeding task for the given subset of animals
    */
    private Task generateFeedTaskSub(List<Animal> animals, String species) {
        StringBuilder animalNames = new StringBuilder();
        // iterate through the animals in the list
        for (int i = 0; i < animals.size(); i++) {
            // append the name of the current animal to the string builder
            animalNames.append(animals.get(i).getName());
            // if the current animal is not the last one, append a comma and a space
            if (i < animals.size() - 1) {
                animalNames.append(", "); }
        }
        return new Task("Feeding (" + animals.size() + ": " + animalNames.toString() + ")", species,
                getFeedStartTime(species), 3, calculateFeedTimeAll(animals.size(), species));
    }
    
    /**
     * Generates all of the cage cleaning tasks for the day so that each animals cage is cleaned once
     * @return  a list of Task objects representing the cage cleaning tasks for all animals.
     */
    private List<Task> generateCleaningTasks() {
        List<Task> cleaningTasks = new ArrayList<>();
        for (Animal animal : TaskDatabase.getAnimals()) {
            cleaningTasks.add(new Task("Cage cleaning for " + animal.getName(), animal.getSpecies(), 0,
                    24, calculateCageCleanIndiv(animal.getSpecies())));
        }
        return cleaningTasks;
    }
    

    /**
    * Generates a list of common tasks for all animals in the database.
    * @return a list of common tasks required for the day
    */
    private List<Task> generateCommonTasks() {
        List<Task> commonTasks = new ArrayList<>();
        // Group the animals by species
        Map<String, List<Animal>> animalsBySpecies = groupAnimalsBySpecies();
        // Generate feeding tasks for each species and add them to the common task list
        for (String species : animalsBySpecies.keySet()) {
            List<Animal> animals = animalsBySpecies.get(species);
            commonTasks.addAll(generateFeedingTasks(animals, species));
        }
        // Generate cleaning tasks and add them to the common task list
        commonTasks.addAll(generateCleaningTasks());
        return commonTasks;
    }
    
    /**
    * Schedules tasks by grouping them by their start hour, and then adding them to an hourly schedule.
    * @param tasks A list of tasks to be scheduled
    * @return A two-dimensional list representing the schedule, where each inner list represents tasks scheduled for a particular hour
    */
    public static List<List<Task>> scheduleTasks(List<Task> tasks) {
        Map<Integer, List<Task>> tasksByHour = groupTasksByStartHour(tasks);
        List<List<Task>> schedule = initializeSchedule();
        scheduleTasksForHour(schedule, tasksByHour);
        return schedule;
    }

    /**
    * Groups tasks by their start hour into a Map with the start hour as the key and the tasks starting at that hour as the value.
    * @param tasks A List of Task objects to be grouped by their start hour.
    * @return A Map containing the tasks grouped by their start hour.
    */
    private static Map<Integer, List<Task>> groupTasksByStartHour(List<Task> tasks) {
        Map<Integer, List<Task>> tasksByHour = new HashMap<>();
        try {
            for (Task task : tasks) {
                int startHour = task.getStartHour();
                // If the tasksByHour map does not already contain a list of tasks for the current start hour, add an empty list
                if (!tasksByHour.containsKey(startHour)) {
                    tasksByHour.put(startHour, new ArrayList<>());
                }
                // Add the current task to the list of tasks starting at the current start hour
                tasksByHour.get(startHour).add(task);
            }
        } catch (NullPointerException e) {
            System.err.println("Error: input list of tasks is null");
        }
        return tasksByHour;
    }

    /**
     * Initializes the schedule as a List of empty task lists for each hour of the day.
     * @return A List of empty task lists representing the schedule for the day.
    */
    private static List<List<Task>> initializeSchedule() {
        List<List<Task>> schedule = new ArrayList<>(NUM_HOURS);
        for (int i = 0; i < NUM_HOURS; i++) {
            schedule.add(new ArrayList<>());
        }
        return schedule;
    }

     /**
     * Schedules tasks by filling up the available time slots with the highest priority tasks.
     * @param schedule An empty List of Lists representing the schedule for the day.
     * @param tasksByHour A Map of Lists where each key is an hour and the value is a List of tasks scheduled to start at that hour.
     * @throws IndexOutOfBoundsException if the index is out of range (schedule.get(i)).
     * @throws NullPointerException if the tasksByHour parameter is null.
     */
    private static void scheduleTasksForHour(List<List<Task>> schedule, Map<Integer, List<Task>> tasksByHour) throws IndexOutOfBoundsException, NullPointerException {
        // Iterate over each hour in the day
        for (int hour = 0; hour < NUM_HOURS; hour++) {
            try {
                List<Task> tasksThisHour = tasksByHour.get(hour);
                if (tasksThisHour != null) {
                    // Iterate over each task that starts at this hour
                    for (Task task : tasksThisHour) {
                        int maxEndHour = task.getStartHour() + task.getMaxWindow();
                        // Iterate over each hour from the starting hour until the task ending hour or the end of the day
                        for (int i = hour; i < maxEndHour && i < NUM_HOURS; i++) {
                            try {
                                List<Task> tasksThisSlot = schedule.get(i);
                                int durationThisSlot = sumDurationsAll(tasksThisSlot);
                                if (durationThisSlot + task.getDuration() <= 60 || (i == maxEndHour - 1 && durationThisSlot + task.getDuration() > 60)) {
                                    tasksThisSlot.add(task);
                                    break; } 
                            } catch (IndexOutOfBoundsException e) {
                                System.err.println("Error accessing schedule Tasks at hour " + hour); }
                        }
                    }
                }
            } catch (NullPointerException e2) {
                System.err.println("Error: tasks by hour map is null"); }
        }
    }
 
     /**
     * Calculates a total summed time duration of tasks
     * @param tasks A list of tasks
     * @return A summed duration of all the tasks within the tasks list passed through params
     */
    private static int sumDurationsAll(List<Task> tasks) {
        int sum = 0;
        for (Task task : tasks) {
            sum += task.getDuration();
        }
        return sum;
    }

    /**
     * Calculates an array of total task times by hour
     * @return an integer array of total task times by hour
     */
    public static int[] getTotalHourlyTime() {
        for (int hour = 0; hour < NUM_HOURS; hour++) {
            List<Task> tasksThisHour = schedule.get(hour);
            int durationThisHour = sumDurationsAll(tasksThisHour);
            totalHourlyTime[hour] = durationThisHour;
        }
        return totalHourlyTime;
    }
    
    /**
     * Calculates the sum of durations of all the tasks in a given hour and returns a formatted string.
     * @param hour the hour for which the sum of durations needs to be calculated
     * @param tasksHour the list of tasks scheduled in the given hour
     * @return a string containing the formatted sum of durations for the given hour
    */
    public static String getHourlyDurations(int hour, List<Task> tasksHour) {
        int sum = sumDurationsAll(tasksHour);
        if(sum <= 120) {
            return Format.getFormattedTwoVar("Hour %d: %d mins", hour, sum);
        }
        return Format.getFormattedTwoVar("Hour %d: %d mins *", hour, sum);
    }
    
    /**
     * Gets the tasks that are scheduled for the hour specified
     * @param hour an integer representing the hour for which tasks are to be retrieved.
     * @return a List of Tasks that are scheduled for the hour specified
     */
    public static List<Task> getTasksThisHour(int hour) {
        return schedule.get(hour);
    }

    /**
     * Constructs the schedule into a string from all the tasks required throughout the day
     * @return The schedule for the wildlife rescue center formatted as a string   
     * @throws ScheduleCreationException  If schedule cannot be made due to too many tasks required within a single hour
     */
    public static String makeSchedule() throws ScheduleCreationException {
        String output = "";
        for (int hour = 0; hour < NUM_HOURS; hour++) {
            output += Format.getFormattedOneVar("%d:00", hour);
            List<Task> tasksThisHour = getTasksThisHour(hour);
            int durationThisHour = sumDurationsAll(tasksThisHour);
            if (durationThisHour > 120) {
                throw new ScheduleCreationException("Duration of tasks in hour " + hour + " exceeds 120 minutes.", tasksThisHour, hour);
            } if (durationThisHour > 60) {
                output += " [+ backup volunteer]" + "\n";
            } else if (durationThisHour <= 60) {
                output += Format.getFormattedOneVar("    Total Time: %d\n", durationThisHour);
            }
            for (Task task : tasksThisHour) {
                output += Format.getFormattedThreeVar("- %s ( %s )    (%dmin)\n",task.getDescription(), task.getNickname(), task.getDuration());
            }
            output += "\n";
        }
        System.out.println(output);
        return output;
    }

     /**
     * The main method creates a new Schedule object
     * @param args The command-line arguments, which are not used in this program.
     * @throws ScheduleCreationException If the schedule cannot be created due to too many tasks required within a single hour.
     */
    public static void main(String[] args) throws ScheduleCreationException {
        new Schedule();
    }
}