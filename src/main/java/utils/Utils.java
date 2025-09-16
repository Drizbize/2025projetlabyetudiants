/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 * Utils for simple functions
 * @author rdanilchenko
 */
public class Utils {
    /**
     * Direction positions offsets
     */
    public static Vector2[] DIRECTION_POSITIONS = {
        new Vector2(-1, 0),
        new Vector2(1, 0),
        new Vector2(0, -1),
        new Vector2(0, 1)
    };
    /**
     * Simple lerp function.
     * Returns a position between 2 position from coeficient
     * @param a position1
     * @param b position2
     * @param f coeficient
     * @return 
     */
    public static double lerp(double a, double b, double f)
    {
        return (a * (1.0 - f)) + (b * f);
    }
    
    /**
     * Simple clamp function.
     * Returns value within borders.
     * @param value
     * @param min
     * @param max
     * @return 
     */
    public static double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }
}
