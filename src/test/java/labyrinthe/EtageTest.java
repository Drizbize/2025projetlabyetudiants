/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package labyrinthe;

import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rdanilchenko
 */
public class EtageTest {
    private boolean etageValid(Etage e)
    {
        for (ISalle s : e)
        {
            if (s.getX() < 0 || s.getY() < 0 || s.getX() >= e.getLargeur() || s.getY() >= e.getHauteur())
            {
                return false;
            }
            if (!e.isSalleCollides(s))
            {
                return false;
            }
        }
        
        return true;
    }
    
    @Test
    public void testEtageValid() throws IOException
    {
        Etage e = new Etage();
        e.charger("etages/etage1N.txt");
        assertTrue(etageValid(e));
        
        e.charger("etages/etage2N.txt");
        assertTrue(etageValid(e));
        
        e.charger("etages/etage4N.txt");
        assertTrue(etageValid(e));
        
        e.charger("etages/etage5N.txt");
        assertTrue(etageValid(e));
        
        e.charger("etages/etage6N.txt");
        assertTrue(etageValid(e));
        
        e.charger("etages/etage8N.txt");
        assertTrue(etageValid(e));
        
        e.charger("etages/etage9N.txt");
        assertTrue(etageValid(e));
        
        //etageInvalide1N, 2N, 3N, 4N are invalid
//        e.charger("etages/etageInvalide1N.txt");
//        assertTrue(etageValid(e));
//        
//        e.charger("etages/etageInvalide2N.txt");
//        assertTrue(etageValid(e));
//        
//        e.charger("etages/etageInvalide3N.txt");
//        assertTrue(etageValid(e));
//        
//        e.charger("etages/etageInvalide4N.txt");
//        assertTrue(etageValid(e));
    }
}
