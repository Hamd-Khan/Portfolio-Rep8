package edu.ucalgary.oop;
import org.junit.*;
import static org.junit.Assert.*;
/** 
 * This class includes junit tests for Format.java
 * ENSF 380: Term Project 2023-04-06
    @Authors    Group 11 (Dominic Gartner <a href = "mailto:dominic.gartner@ucalgary.ca">dominic.gartner@ucalgary.ca</a>, 
                          Hamd Khan <a href = "mailto:hamd.khan1@ucalgary.ca">hamd.khan1@ucalgary.ca</a>, 
                          Alex Mclean <a href = "mailto:alexander.mclean@ucalgary.ca">alexander.mclean@ucalgary.ca</a>, 
                          Kaylyn Tanton <a href = "mailto:kaylyn.tanton@ucalgary.ca">kaylyn.tanton@ucalgary.ca</a>)
    @Version    2.8
    @since      1.0
 */
public class FormatTest {

    /**
     * Test if the getFormattedOneVar() method correctly returns a formatted string
     */
    @Test
    public void testGetFormattedOneVarZeroInput() {
        String message = "Value is %d";
        int var = 0;
        String expected = "Value is 0";
        String result = Format.getFormattedOneVar(message, var);
        assertEquals("getFormattedOneVar did not return the correct formatted string", expected, result);
    }
     /**
     * Test if the getFormattedOneVar() method correctly returns a formatted string when var is negative
     */
    @Test
    public void testGetFormattedOneVarNegativeInput() {
        String message = "Value is %d";
        int var = -5;
        String expected = "Value is -5";
        String result = Format.getFormattedOneVar(message, var);
        assertEquals("getFormattedOneVar did not return the correct formatted string", expected, result);
    }
     /**
     * Test if the getFormattedOneVar() method correctly returns a formatted string when var is the max integer
     */
    @Test
    public void testGetFormattedOneVarMaxInput() {
        String message = "Value is %d";
        int var = Integer.MAX_VALUE;
        String expected = "Value is " + Integer.MAX_VALUE;
        String result = Format.getFormattedOneVar(message, var);
        assertEquals("getFormattedOneVar did not return the correct formatted string", expected, result);
    }
     /**
     * Test if the getFormattedOneVar() method throws an IllegalArgumentException when var is null
     */
    @Test
    public void testGetFormattedOneVarNullInput() {
        boolean passed = false;
        try {
            String message = "Value is %d";
            Integer var = null;
            Format.getFormattedOneVar(message, var);
        } catch(IllegalArgumentException e) {
            passed = true;
        } catch (Exception e) {
            assertTrue("getFormattedOneVar() did not throw an IllegalArgumentException when var is null", passed);
        }
    }
    /**
     * Test if the getFormattedOneVar() method throws an IllegalArgumentException when message is null
     */
    @Test
    public void testGetFormattedOneVarNullMessage() {
        boolean passed = false;
        try {
            String message = null;
            int var = 42;
            Format.getFormattedOneVar(message, var);
        } catch(IllegalArgumentException e) {
            passed = true;
        } catch (Exception e) {
            assertTrue("getFormattedOneVar() did not throw an IllegalArgumentException when message is null", passed);
        }
    }



    /**
     * Test if the getFormattedTwoVar() method correctly returns a formatted string
     */
    @Test
    public void testGetFormattedTwoVarZeroInput() {
        String message = "Value is %d %d";
        int var = 0;
        int var2 = 0;
        String expected = "Value is 0 0";
        String result = Format.getFormattedTwoVar(message, var, var2);
        assertEquals("getFormattedTwoVar did not return the correct formatted string", expected, result);
    }
     /**
     * Test if the getFormattedTwoVar() method correctly returns a formatted string when var is negative
     */
    @Test
    public void testGetFormattedTwoVarNegativeInput() {
        String message = "Value is %d %d";
        int var = -5;
        int var2 = -33;
        String expected = "Value is -5 -33";
        String result = Format.getFormattedTwoVar(message, var, var2);
        assertEquals("getFormattedTwoVar did not return the correct formatted string", expected, result);
    }
    /**
     * Test if the getFormattedTwoVar() method throws an IllegalArgumentException when message var is null
     */
    @Test
    public void testGetFormattedTwoVarNullInput() {
        boolean passed = false;
        try {
            String message = "Value is %d";
            Integer var = null;
            Integer var2 = null;
            Format.getFormattedTwoVar(message, var, var2);
        } catch(IllegalArgumentException e) {
            passed = true;
        } catch (Exception e) {
            assertTrue("getFormattedTwoVar() did not throw an IllegalArgumentException when var is null", passed);
        }
    }
    /**
     * Test if the getFormattedTwoVar() method throws an IllegalArgumentException when message is null
     */
    @Test
    public void testGetFormattedTwoVarNullMessage() {
        boolean passed = false;
        try {
            String message = null;
            int var = 42;
            int var2 = 11;
            Format.getFormattedTwoVar(message, var, var2);
        } catch(IllegalArgumentException e) {
            passed = true;
        } catch (Exception e) {
            assertTrue("getFormattedTwoVar() did not throw an IllegalArgumentException when message is null", passed);
        }
    }



     /**
     * Test if the getFormattedThreeVar() method correctly returns a formatted string 
     */
    @Test
    public void testGetFormattedThreeVarZeroInput() {
        String message = "Value is %s %s %d";
        String var = "hello";
        String var2 = "test";
        int var3 = 6;
        String expected = "Value is hello test 6";
        String result = Format.getFormattedThreeVar(message, var, var2, var3);
        assertEquals("getFormattedThreeVar did not return the correct formatted string", expected, result);
    }
     /**
     * Test if the getFormattedThreeVar() method correctly returns a formatted string when var is negative
     */
    @Test
    public void testGetFormattedThreeVarNegativeInput() {
        String message = "Value is %s %s %d";
        String var = "hello";
        String var2 = "test";
        int var3 = -10;
        String expected = "Value is hello test -10";
        String result = Format.getFormattedThreeVar(message, var, var2, var3);
        assertEquals("getFormattedThreeVar did not return the correct formatted string", expected, result);
    }
     /**
     * Test if the getFormattedThreeVar() method correctly returns a formatted string when var is empty string
     */
    @Test
    public void testGetFormattedThreeVarEmptyString() {
        String message = "Value is %s %s %d";
        String var = "";
        String var2 = "";
        int var3 = -10;
        String expected = "Value is   -10";
        String result = Format.getFormattedThreeVar(message, var, var2, var3);
        assertEquals("getFormattedTheeeVar did not return the correct formatted string", expected, result);
    }
    /**
     * Test if the getFormattedThreeVar() method throws an IllegalArgumentException when var is null
     */
    @Test
    public void testGetFormattedThreeVarNullInput() {
        boolean passed = false;
        try {
            String message = "Value is %d %d %d";
            String var = null;
            String var2 = null;
            Integer var3 = null;
            Format.getFormattedThreeVar(message, var, var2, var3);
        } catch(IllegalArgumentException e) {
            passed = true;
        } catch (Exception e) {
            assertTrue("getFormattedThreeVar() did not throw an IllegalArgumentException when var is null", passed);
        }
    }
    /**
     * Test if the getFormattedThreeVar() method throws an IllegalArgumentException when message is null
     */
    @Test
    public void testGetFormattedThreeVarNullMessage() {
        boolean passed = false;
        try {
            String message = null;
            String var = "test";
            String var2 = "123";
            Integer var3 = 7;
            Format.getFormattedThreeVar(message, var, var2, var3);
        } catch(IllegalArgumentException e) {
            passed = true;
        } catch (Exception e) {
            assertTrue("getFormattedThreeVar() did not throw an IllegalArgumentException when message is null", passed);
        }
    }

}