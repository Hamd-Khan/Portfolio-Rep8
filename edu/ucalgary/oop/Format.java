package edu.ucalgary.oop;
/** 
 * This class is used for properly formatted all text included in the GUI
 * ENSF 380: Term Project 2023-04-06
    @Authors    Group 11 (Dominic Gartner <a href = "mailto:dominic.gartner@ucalgary.ca">dominic.gartner@ucalgary.ca</a>, 
                          Hamd Khan <a href = "mailto:hamd.khan1@ucalgary.ca">hamd.khan1@ucalgary.ca</a>, 
                          Alex Mclean <a href = "mailto:alexander.mclean@ucalgary.ca">alexander.mclean@ucalgary.ca</a>, 
                          Kaylyn Tanton <a href = "mailto:kaylyn.tanton@ucalgary.ca">kaylyn.tanton@ucalgary.ca</a>)
    @Version    2.8
    @since      1.0
 */
public interface Format {

    /**
     * 
     * @param message The message that needs to be properly formatted as a string
     * @return The formatted message as a string
     * @throws IllegalArgumentException If the message passed in params is null
     */
    public static String getFormattedOneVar(String message, Integer var) throws IllegalArgumentException {
        if(message == null || var == null) {
            throw new IllegalArgumentException("Error: Message or variable provided to be formatted was null.");
        }
        return String.format(message, var);
    }
    /**
     * 
     * @param message The message that needs to be properly formatted as a string
     * @param var the variable to replace the first %d
     * @param var2 the variable to replace the second %d
     * @return The formatted message as a string
     * @throws IllegalArgumentException If the message passed in params is null
     */
    public static String getFormattedTwoVar(String message, Integer var, Integer var2) throws IllegalArgumentException {
        if(message == null || var == null || var2 == null) {
            throw new IllegalArgumentException("Error: Message or variable provided to be formatted was null.");
        }
        return String.format(message, var, var2);
    }
    /**
     * 
     * @param message The message that needs to be properly formatted as a string
     * @param var the variable to replace the first %d
     * @param var2 the variable to replace the second %d
     * @param var3 the variable to replace the third %d
     * @return The formatted message as a string
     * @throws IllegalArgumentException If the message passed in params is null
     */
    public static String getFormattedThreeVar(String message, String var, String var2, Integer var3) throws IllegalArgumentException {
        if(message == null || var == null || var2 == null || var3 == null) {
            throw new IllegalArgumentException("Error: Message or variable provided to be formatted was null.");
        }
        return String.format(message, var, var2, var3);
    }
}