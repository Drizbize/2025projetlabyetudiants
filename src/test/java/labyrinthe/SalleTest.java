/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package labyrinthe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author gothmog
 */
public class SalleTest {
    private Etage etage0;
    private Etage etage1;
    
    public SalleTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    public Salle normalSalle(int x, int y, Etage etage)
    {
        return new Salle(x, y, ESalle.NORMALE, etage);
    }
    
    @Before
    public void setUp() {
        etage0 = new Etage(0);
        etage1 = new Etage(1);
        
        Salle[] sallesEtage0 = {
            normalSalle(0, 0, etage0),
            normalSalle(1, 0, etage0),
            new Salle(1, 1, ESalle.ENTREE, etage0),
            normalSalle(0, 2, etage0),
            normalSalle(2, 1, etage0),
            normalSalle(3, 1, etage0),
            new Salle(4, 1, ESalle.ESCALIER_MONTANT, etage0),
            normalSalle(3, 2, etage0),
        };
        
        Salle[] sallesEtage1 = {
            normalSalle(0, 0, etage1),
            normalSalle(1, 0, etage1),
            normalSalle(1, 1, etage1),
            new Salle(4, 1, ESalle.ESCALIER_DESCENDANT, etage1),
            normalSalle(5, 1, etage1),
        };
        
        etage0.addAll(new ArrayList<>(Arrays.asList(sallesEtage0)));
        etage1.addAll(new ArrayList<>(Arrays.asList(sallesEtage1)));
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testGetX() {
        ISalle s = etage0.getAt(1, 1);
        assertEquals(1, s.getX());
    }
    
    @Test
    public void testGetY() {
        ISalle s = etage0.getAt(1, 1);
        assertEquals(1, s.getY());
    }
    
    @Test
    public void testGetType() {
        ISalle sEntree = etage0.getAt(1, 1);
        ISalle sNormal = etage0.getAt(0, 0);
        assertEquals(ESalle.ENTREE, sEntree.getType());
        assertEquals(ESalle.NORMALE, sNormal.getType());
    }
    
    @Test
    public void testGetEtage() {
        ISalle sEtage0 = etage0.getAt(1, 1);
        ISalle sEtage1 = etage1.getAt(1, 1);
        assertEquals(etage0, sEtage0.getEtage());
        assertEquals(etage1, sEtage1.getEtage());
    }
    
    @Test
    public void testEstAdjacente()
    {
        ISalle sNormal1 = etage0.getAt(0, 0);
        ISalle sNormal2 = etage0.getAt(1, 0);
        ISalle sEntree = etage0.getAt(1, 1);
        ISalle sNormal3 = etage0.getAt(0, 2);
        
        assertTrue(sNormal1.estAdjacente(sNormal2));
        assertFalse(sNormal1.estAdjacente(sEntree));
        assertFalse(sNormal1.estAdjacente(sNormal3));
        assertFalse(sNormal1.estAdjacente(sNormal1));
        
        assertTrue(sNormal2.estAdjacente(sNormal1));
        assertTrue(sNormal2.estAdjacente(sEntree));
        assertFalse(sNormal2.estAdjacente(sNormal3));
        assertFalse(sNormal2.estAdjacente(sNormal2));
        
        assertTrue(sEntree.estAdjacente(sNormal2));
        assertFalse(sEntree.estAdjacente(sNormal1));
        assertFalse(sEntree.estAdjacente(sNormal3));
        assertFalse(sEntree.estAdjacente(sEntree));
        
        ISalle sNormalNearMontant = etage0.getAt(3, 1);
        ISalle sMontant = etage0.getAt(4, 1);
        
        ISalle sNormalNearDescendant = etage1.getAt(5, 1);
        ISalle sDescendant = etage1.getAt(4, 1);
        
        assertTrue(sNormalNearMontant.estAdjacente(sMontant));
        assertTrue(sMontant.estAdjacente(sNormalNearMontant));
        assertFalse(sNormalNearMontant.estAdjacente(sDescendant));
        
        assertTrue(sMontant.estAdjacente(sDescendant));
        assertTrue(sDescendant.estAdjacente(sMontant));
        
        assertTrue(sDescendant.estAdjacente(sNormalNearDescendant));
        assertTrue(sNormalNearDescendant.estAdjacente(sDescendant));
        
        Etage testEtage0 = new Etage(0);
        Etage testEtage1 = new Etage(1);
        
        ISalle sMontant1 = new Salle(0, 0, ESalle.ESCALIER_DESCENDANT, testEtage0);
        ISalle sDescendant1 = new Salle(0, 0, ESalle.ESCALIER_MONTANT, testEtage1);
        
        testEtage0.add(sMontant1);
        testEtage1.add(sDescendant1);
        
        assertFalse(sMontant1.estAdjacente(sDescendant1));
        assertFalse(sDescendant1.estAdjacente(sMontant1));
    }
}
