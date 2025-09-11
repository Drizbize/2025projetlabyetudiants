/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personnages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import labyrinthe.ILabyrinthe;
import labyrinthe.ISalle;

/**
 *
 * @author rdanilchenko
 */
public class Dragon extends APersonnage {
    private ILabyrinthe m_labyrinthe;
    private Random m_rnd = new Random();

    public Dragon(ISalle initSalle, ILabyrinthe l) {
        super(initSalle);
        m_labyrinthe = l;
    }

    @Override
    public ISalle faitSonChoix(Collection<ISalle> sallesAccessibles) {
        List<ISalle> path = (List<ISalle>)m_labyrinthe.chemin(getPosition(), m_labyrinthe.getHero().getPosition());
        if (path != null && path.size() > 1)
        {
            return path.get(1);
        }
        
        return null;
    }
    
}
