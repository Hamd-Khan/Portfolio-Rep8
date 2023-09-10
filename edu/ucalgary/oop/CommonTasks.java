package edu.ucalgary.oop;

/**
 * This class implements functions required for the common tasks that are
 * completed daily for all animals.
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
 * @Version 1.4
 * @since 1.0
 */
public class CommonTasks {
    /**
     * Used to retrieve the time to clean an animals cage
     * 
     * @param species species of the animal.
     * @return an integer representing the time to clean one animals cage of the
     *         species passed in through the param
     */
    public static int calculateCageCleanIndiv(String species) {
        switch (species.toLowerCase()) {
            case "beaver":
            case "fox":
            case "coyote":
                return 5;
            case "porcupine":
            case "racoon":
                return 10;
            default:
                return 0; // Return 0. No time = do not include in schedule
                          // Rather than throwing exception, do not enter into schedule
        }
    }

    /**
     * Used to retrieve the time to feed any number of animals of the same species
     * 
     * @param species species of the animal.
     * @param num     number of animals being fed
     * @return an integer representing the time to feed the num amount of animals of
     *         the same species
     */
    public static int calculateFeedTimeAll(int num, String species) {
        switch (species.toLowerCase()) {
            case "beaver":
            case "racoon":
            case "porcupine":
                return 5 * num;
            case "fox":
                return (5 * num) + 5; // 5 mins feed + 5 mins prep
            case "coyote":
                return (5 * num) + 10; // 5 mins feed + 10 mins prep
            default:
                return 0; // Return 0. No time = do not include in schedule
                          // Rather than throwing exception, do not enter into schedule
        }
    }

    /**
     * Used to retrieve the max number of animals that can possibly be fed within
     * one hour
     * 
     * @param species species of the animal.
     * @return an integer representing the max amount of animals of one species that
     *         can be fed within a single hour
     */
    public static int getMaxAnimalsToFeed(String species) {
        switch (species.toLowerCase()) {
            case "beaver":
            case "racoon":
            case "porcupine":
                return 12;
            case "fox":
                return 11;
            case "coyote":
                return 10;
            default:
                return 0; // Return 0. No animals = do not include in schedule
                          // Rather than throwing exception, do not enter into schedule
        }
    }

    /**
     * Used to retrieve the feed start times for each species
     * 
     * @param species species of the animal.
     * @return an integer representing the feed start time for the species passed in
     *         through param
     * @throws IllegalArgumentException an incorrect animal species was attempted to
     *                                  be entered into the schedule
     */
    public static int getFeedStartTime(String species) throws IllegalArgumentException {
        switch (species.toLowerCase()) {
            case "beaver":
                return 8;
            case "racoon":
            case "fox":
                return 0;
            case "coyote":
            case "porcupine":
                return 19;
            default:
                throw new IllegalArgumentException("Animal species that Wildlife Rescue Center" +
                        " is unable to care for has been detected ");
        }
    }
}