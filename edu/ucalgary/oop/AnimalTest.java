package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * This class includes junit tests for Animal.java
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
 * @Version 2.8
 * @since 1.0
 */
public class AnimalTest {
    // Tests for Animal.java

    /**
     * Check whether the Animal.java correctly sets new animals and corectly gets
     * the animal name and species.
     */
    @Test
    public void testAnimal() {
        Animal cat = new Animal("Fluffy", "Cat");
        assertEquals("getName() did not return the correct animal name", "Fluffy", cat.getName());
        assertEquals("getSpecies() did not return the correct animal species", "Cat", cat.getSpecies());

        Animal dog = new Animal("Buddy", "Dog");
        assertEquals("getName() did not return the correct animal name", "Buddy", dog.getName());
        assertEquals("getSpecies() did not return the correct animal species", "Dog", dog.getSpecies());
    }

    /**
     * Check whether the Animal constructor correctly throws a NullPointerException
     * when a null animal is constructed
     */
    @Test
    public void testNullAnimal() {
        boolean passed = false;
        try {
            new Animal(null, null);
        } catch (NullPointerException e) {
            passed = false;
        } catch (Exception e) {
            assertTrue("Animal constructor did not throw a NullPointerException when name and sepcies is null", passed);
        }
    }

    /**
     * Check whether the Animal constructor correctly throws an
     * IllegalArgumentException when an empty animal is constructed
     */
    @Test
    public void testEmptyAnimal() {
        boolean passed = false;
        try {
            new Animal("", "");
        } catch (NullPointerException e) {
            passed = false;
        } catch (Exception e) {
            assertTrue("Animal constructor did not throw a NullPointerException when name and sepcies is empty",
                    passed);
        }
    }

}
