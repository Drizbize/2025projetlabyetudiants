/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author rdanilchenko
 */
public class Vector2 {
    public int x, y;

    public Vector2()
    {
        x = 0;
        y = 0;
    }
    
    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void plus(Vector2 other)
    {
        x += other.x;
        y += other.y;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.x;
        hash = 89 * hash + this.y;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vector2 other = (Vector2) obj;
        if (this.x != other.x) {
            return false;
        }
        return this.y == other.y;
    }
    
    
}
