package edu.ucalgary.oop;
/** 
 * This class implements required methods for creating the animal objects from the animals present withing the database.
 * ENSF 380: Term Project 2023-04-06
    @Authors    Group 11 (Dominic Gartner <a href = "mailto:dominic.gartner@ucalgary.ca">dominic.gartner@ucalgary.ca</a>, 
                          Hamd Khan <a href = "mailto:hamd.khan1@ucalgary.ca">hamd.khan1@ucalgary.ca</a>, 
                          Alex Mclean <a href = "mailto:alexander.mclean@ucalgary.ca">alexander.mclean@ucalgary.ca</a>, 
                          Kaylyn Tanton <a href = "mailto:kaylyn.tanton@ucalgary.ca">kaylyn.tanton@ucalgary.ca</a>)
    @Version    2.8
    @since      1.0
 */
public class Animal {
    private String species;
    private String name;

    /**
     * Overload constructor.
     * @param name Name of the animal.
     * @param species Species of the animal.
     */
    public Animal(String name, String species) {
        this.name = name;
        this.species = species;
    }

    /**
     * return the animals name
     * @return The animals name for the animal instance.   
     */
    public String getName() {
        return this.name;
    }

    /**
     * return the animals species
     * @return The animals species for the animal instance.   
     */
    public String getSpecies() {
        return this.species;
    }

}