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
    
    public Hero(ISalle startPos) {
        setPosition(startPos);
    }

    @Override
    public ISalle faitSonChoix(Collection<ISalle> sallesAccessibles) {
        if (salleChoisie == null || !sallesAccessibles.contains(salleChoisie))
            return getPosition();
        
        return salleChoisie;
    }
    
}
