/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personnages;

import java.util.Collection;
import labyrinthe.ISalle;
import labyrinthe.ILabyrinthe;

/**
 *
 * @author rdanilchenko
 */
public class Hero extends APersonnage {
    public ISalle salleChoisie;
    private ILabyrinthe m_labyrinthe;
    
    public Hero(ILabyrinthe labyrinthe) {
        super(labyrinthe.getEntree());
        this.m_labyrinthe = labyrinthe;
        setPosition(labyrinthe.getEntree());
    }

    @Override
    public ISalle faitSonChoix(Collection<ISalle> sallesAccessibles) {
        if (salleChoisie == null || !sallesAccessibles.contains(salleChoisie))
        {
            return getPosition();
        }
        
        return salleChoisie;
    }
    
    @Override
    public void setPosition(ISalle s)
    {
        super.setPosition(s);
        m_labyrinthe.setEtageCourant(s.getEtage());
    }
}
