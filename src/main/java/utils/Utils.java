/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author rdanilchenko
 */
public class Utils {
    public static double lerp(double a, double b, double f)
    {
        return (a * (1.0 - f)) + (b * f);
    }
    
    public static double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }
}
