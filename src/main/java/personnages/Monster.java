/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personnages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import labyrinthe.ILabyrinthe;
import labyrinthe.ISalle;

/**
 *
 * @author rdanilchenko
 */
public class Monster extends APersonnage {
    Random rnd = new Random();

    public Monster(ISalle initPos) {
        super(initPos);
    }

    @Override
    public ISalle faitSonChoix(Collection<ISalle> sallesAccessibles) {
        if (sallesAccessibles == null || (sallesAccessibles != null && sallesAccessibles.isEmpty()))
            return getPosition();
        
        ArrayList<ISalle> salles = new ArrayList<>();
        for (ISalle s : sallesAccessibles)
        {
            salles.add(s);
        }
        
        return salles.get(rnd.nextInt(sallesAccessibles.size()));
    }
}
