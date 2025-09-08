/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.io.IOException;

/**
 *
 * @author rdanilchenko
 */
public class ExceptionInvalidFile extends IOException {
    public ExceptionInvalidFile(String errorMessage) {
        super(errorMessage);
    }
}
