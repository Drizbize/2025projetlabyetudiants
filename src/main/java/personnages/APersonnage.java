/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personnages;

import java.util.Collection;
import labyrinthe.IEtage;
import labyrinthe.ILabyrinthe;
import labyrinthe.ISalle;
import labyrinthe.Labyrinthe;

/**
 *
 * @author rdanilchenko
 */
public abstract class APersonnage implements IPersonnage {
    private ISalle m_salle;

    public APersonnage(ISalle initSalle)
    {
        m_salle = initSalle;
    }

    @Override
    public ISalle getPosition()
    {
        return m_salle;
    }

    @Override
    public void setPosition(ISalle s)
    {
        m_salle = s;
    }
    
}
