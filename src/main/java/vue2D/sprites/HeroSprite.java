/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vue2D.sprites;

import java.util.Collection;
import javafx.event.*;
import javafx.scene.image.Image;
import static javafx.scene.input.KeyCode.UP;
import javafx.scene.input.KeyEvent;
import labyrinthe.IEtage;
import labyrinthe.ILabyrinthe;
import labyrinthe.ISalle;
import labyrinthe.Salle;
import personnages.Hero;
import utils.Vector2;

/**
 *
 * @author rdanilchenko
 */
public class HeroSprite extends ASprite implements EventHandler<KeyEvent>
{
    private ILabyrinthe m_labyrinthe;
    
    public HeroSprite(Hero hero, ILabyrinthe labyrinthe) {
        super(new Image("file:icons/link/LinkRunShieldL1.gif"), hero);
        m_labyrinthe = labyrinthe;
        setPosition(hero.getPosition());
    }

    @Override
    public void handle(KeyEvent event) {
        Vector2 nextPos = new Vector2();
        ISalle heroPos = m_person.getPosition();
        boolean isClimbing = false;
        boolean isGoingDown = false;
        
        ISalle choice = null;
        
        switch (event.getCode()) {
            case UP:
                nextPos.y--;
                break;
            case DOWN:
                nextPos.y++;
                break;
            case LEFT:
                nextPos.x--;
                break;
            case RIGHT:
                nextPos.x++;
                break;
            case M:
                isClimbing = true;
                break;
            case D:
                isGoingDown = true;
                break;
        }
        
        if (isClimbing)
        {
            IEtage upstairs = m_labyrinthe.getUpEtage();
            if (upstairs != null)
            {
                choice = upstairs.getAt(heroPos.getX(), heroPos.getY());
            }
        }
        else if (isGoingDown)
        {
            IEtage downstairs = m_labyrinthe.getDownEtage();
            if (downstairs != null)
            {
                choice = downstairs.getAt(heroPos.getX(), heroPos.getY());
            }
        }
        else if (nextPos.getMagnitude() != 0.0)
        {
            choice = m_labyrinthe.getEtageCourant().getAt(heroPos.getX() + nextPos.x, heroPos.getY() + nextPos.y);
        }
        
        ((Hero)m_person).salleChoisie = choice;
    }
}
