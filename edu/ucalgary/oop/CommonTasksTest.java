package edu.ucalgary.oop;
import org.junit.*;
import static org.junit.Assert.*;
/** 
 * This class includes junit tests for CommonTasks.java
 * ENSF 380: Term Project 2023-04-06
    @Authors    Group 11 (Dominic Gartner <a href = "mailto:dominic.gartner@ucalgary.ca">dominic.gartner@ucalgary.ca</a>, 
                          Hamd Khan <a href = "mailto:hamd.khan1@ucalgary.ca">hamd.khan1@ucalgary.ca</a>, 
                          Alex Mclean <a href = "mailto:alexander.mclean@ucalgary.ca">alexander.mclean@ucalgary.ca</a>, 
                          Kaylyn Tanton <a href = "mailto:kaylyn.tanton@ucalgary.ca">kaylyn.tanton@ucalgary.ca</a>)
    @Version    2.8
    @since      1.0
 */
public class CommonTasksTest {
    
     private final int NUM = 3;

    /**
     * Test getFeedStartTime method for returning the proper feed start time 
     * when passed with argument "beaver" for species
     */
    @Test
    public void testBeaverFeedStart() {
        int expected = 8;
        int actual = CommonTasks.getFeedStartTime("beaver");
        assertEquals("The returned value for the beaver's start feed time is incorrect",expected, actual);
    }
     /**
     * Test getFeedStartTime method for returning the proper feed start time 
     * when passed with argument "racoon" for species
     */
    @Test
    public void testRacoonFeedStart() {
        int expected = 0;
        int actual = CommonTasks.getFeedStartTime("racoon");
        assertEquals("The returned value for the racoon's start feed time is incorrect", expected, actual);
    }
     /**
     * Test getFeedStartTime method for returning the proper feed start time 
     * when passed with argument "fox" for species
     */
    @Test
    public void testFoxFeedStart() {
        int expected = 0;
        int actual = CommonTasks.getFeedStartTime("fox");
        assertEquals("The returned value fox's the beavers start feed time is incorrect", expected, actual);
    }
     /**
     * Test getFeedStartTime method for returning the proper feed start time 
     * when passed with argument "coyote" for species
     */
    @Test
    public void testCoyoteFeedStart() {
        int expected = 19;
        int actual = CommonTasks.getFeedStartTime("coyote");
        assertEquals("The returned value for the coyote's start feed time is incorrect", expected, actual);
    }
     /**
     * Test getFeedStartTime method for returning the proper feed start time 
     * when passed with argument "porcupine" for species
     */
    @Test
    public void testPorcupineFeedStart() {
        int expected = 19;
        int actual = CommonTasks.getFeedStartTime("porcupine");
        assertEquals("The returned value for the porcupine's start feed time is incorrect", expected, actual);
    }
     /**
     * Test getFeedStartTime method for throwing an IllegalArgumentException
     * when passed with an invalid argument for species
     */
    @Test
    public void testInvalidSpeciesFeedStart() {
        boolean passed = false;
        try {
            CommonTasks.getFeedStartTime("invalid");
        } catch(IllegalArgumentException e) {
            passed = true; 
        } catch (Exception e) {
            assertTrue("getFeedStartTime did not an IllegalArgumentException for an invalid species", passed);
        }
    }
     /**
     * Test calculateCageCleanIndiv method for returning the proper time to clean a cage
     * when passed with argument "beaver" for species
     */
    @Test
    public void testBeaverClean() {
        int expected = 5;
        int actual = CommonTasks.calculateCageCleanIndiv("beaver");
        assertEquals("The returned value for the beaver's cage clean time is incorrect", expected, actual);
    }
    /**
     * Test calculateCageCleanIndiv method for returning the proper time to clean a cage
     * when passed with argument "racoon" for species
     */
    @Test
    public void testRacoonClean() {
        int expected = 10;
        int actual = CommonTasks.calculateCageCleanIndiv("racoon");
        assertEquals("The returned value for the racoon's cage clean time is incorrect", expected, actual);
    }
    /**
     * Test calculateCageCleanIndiv method for returning the proper time to clean a cage
     * when passed with argument "fox" for species
     */
    @Test
    public void testFoxClean() {
        int expected = 5;
        int actual = CommonTasks.calculateCageCleanIndiv("fox");
        assertEquals("The returned value fox's the beaver's cage clean time is incorrect", expected, actual);
    }
    /**
     * Test calculateCageCleanIndiv method for returning the proper time to clean a cage
     * when passed with argument "coyote" for species
     */
    @Test
    public void testCoyoteClean() {
        int expected = 5;
        int actual = CommonTasks.calculateCageCleanIndiv("coyote");
        assertEquals("The returned value for the coyote's cage clean time is incorrect", expected, actual);
    }
    /**
     * Test calculateCageCleanIndiv method for returning the proper time to clean a cage
     * when passed with argument "porcupine" for species
     */
    @Test
    public void testPorcupineClean() {
        int expected = 10;
        int actual = CommonTasks.calculateCageCleanIndiv("porcupine");
        assertEquals("The returned value for the porupine's cage clean time is incorrect", expected, actual);
    }
    /**
     * Test calculateCageCleanIndiv method for returning the proper time to clean a cage
     * when an invalid argument is passed for species
     */
    @Test
    public void testInvalidSpeciesClean() {
        int expected = 0;
        int actual = CommonTasks.calculateCageCleanIndiv("invalid");
        assertEquals("The returned value for an invalid species cage clean time is incorrect", expected, actual);
    }
     /**
     * Test calculateFeedTimeAll method for returning the proper time to feed num amount of animals
     * when passed with argument "beaver" for species
     */
    @Test
    public void testBeaverFeed() {
        int expected = NUM * 5;
        int actual = CommonTasks.calculateFeedTimeAll(NUM,"beaver");
        assertEquals("The returned value to feed num beaver's is incorrect", expected, actual);
    }
    /**
     * Test calculateFeedTimeAll method for returning the proper time to feed num amount of animals
     * when passed with argument "racoon" for species
     */
    @Test
    public void testRacoonFeed() {
        int expected = NUM * 5;
        int actual = CommonTasks.calculateFeedTimeAll(NUM,"racoon");
        assertEquals("The returned value to feed num racoon's is incorrect",expected, actual);
    }
    /**
     * Test calculateFeedTimeAll method for returning the proper time to feed num amount of animals
     * when passed with argument "fox" for species
     */
    @Test
    public void testFoxFeed() {
        int expected = (NUM * 5) + 5;
        int actual = CommonTasks.calculateFeedTimeAll(NUM,"fox");
        assertEquals("The returned value to feed num fox's is incorrect",expected, actual);
    }
    /**
     * Test calculateFeedTimeAll method for returning the proper time to feed num amount of animals
     * when passed with argument "coyote" for species
     */
    @Test
    public void testCoyoteFeed() {
        int expected = (NUM * 5) + 10;
        int actual = CommonTasks.calculateFeedTimeAll(NUM,"coyote");
        assertEquals("The returned value to feed num coyote's is incorrect",expected, actual);
    }
    /**
     * Test calculateFeedTimeAll method for returning the proper time to feed num amount of animals
     * when passed with argument "porcupine" for species
     */
    @Test
    public void testPorcupineFeed() {
        int expected = NUM * 5;
        int actual = CommonTasks.calculateFeedTimeAll(NUM,"porcupine");
        assertEquals("The returned value to feed num porcupine's is incorrect",expected, actual);
    }
    /**
     * Test calculateFeedTimeAll method for returning the proper time to feed num amount of animals
     * when passed with an invalid argument for species
     */
    @Test
    public void testInvalidSpeciesFeed() {
        int expected = 0;
        int actual = CommonTasks.calculateFeedTimeAll(NUM,"invalid");
        assertEquals("The returned value to feed an invalid species is incorrect",expected, actual);
    }
    /**
     * Test getMaxAnimalsToFeed method for returning the maximum amount of animals of a specified species 
     * that can be fed within a single hour when passed with argument "beaver" for species
     */
    @Test
    public void testBeaverFeedMax() {
        int expected = 12;
        int actual = CommonTasks.getMaxAnimalsToFeed("beaver");
        assertEquals("The returned value for max beaver's to feed in an hour is incorrect",expected, actual);
    }
     /**
     * Test getMaxAnimalsToFeed method for returning the maximum amount of animals of a specified species 
     * that can be fed within a single hour when passed with argument "racoon" for species
     */
    @Test
    public void testRacoonFeedMax() {
        int expected = 12;
        int actual = CommonTasks.getMaxAnimalsToFeed("racoon");
        assertEquals("The returned value for max racoon's to feed in an hour is incorrect",expected, actual);
    }
     /**
     * Test getMaxAnimalsToFeed method for returning the maximum amount of animals of a specified species 
     * that can be fed within a single hour when passed with argument "fox" for species
     */
    @Test
    public void testFoxFeedMax() {
        int expected = 11;
        int actual = CommonTasks.getMaxAnimalsToFeed("fox");
        assertEquals("The returned value for max fox's to feed in an hour is incorrect",expected, actual);
    }
     /**
     * Test getMaxAnimalsToFeed method for returning the maximum amount of animals of a specified species 
     * that can be fed within a single hour when passed with argument "coyote" for species
     */
    @Test
    public void testCoyoteFeedMax() {
        int expected = 10;
        int actual = CommonTasks.getMaxAnimalsToFeed("coyote");
        assertEquals("The returned value for max coyote's to feed in an hour is incorrect",expected, actual);
    }
     /**
     * Test getMaxAnimalsToFeed method for returning the maximum amount of animals of a specified species 
     * that can be fed within a single hour when passed with argument "procupine" for species
     */
    @Test
    public void testPorcupineFeedMax() {
        int expected = 12;
        int actual = CommonTasks.getMaxAnimalsToFeed("porcupine");
        assertEquals("The returned value for max porupine's to feed in an hour is incorrect",expected, actual);
    }
     /**
     * Test getMaxAnimalsToFeed method for returning the maximum amount of animals of a specified species 
     * that can be fed within a single hour when passed with an invalid argument for species
     */
    @Test
    public void testInvalidSpeciesFeedMax() {
        int expected = 0;
        int actual = CommonTasks.getMaxAnimalsToFeed("invalid");
        assertEquals("The returned value for number invalid species to feed in an hour is incorrect",expected, actual);
    }

}
