/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vue2D.sprites;

import java.util.Collection;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import labyrinthe.ISalle;
import personnages.IPersonnage;
import utils.Vector2;
import vue2D.AVue;

/**
 *
 * @author rdanilchenko
 */
public abstract class ASprite implements ISprite {
    protected IPersonnage m_person;
    private Image m_img;
    private Vector2 m_pos;
    
    public ASprite(Image img, IPersonnage person) {
        m_img = img;
        m_pos = new Vector2();
        m_person = person;
    }

    @Override
    public void dessiner(GraphicsContext g) {
        g.drawImage(m_img, m_pos.x, m_pos.y, AVue.UNITE, AVue.UNITE);
    }

    @Override
    public void setCoordonnees(int xpix, int ypix) {
        m_pos.x = xpix;
        m_pos.y = ypix;
    }

    @Override
    public ISalle faitSonChoix(Collection<ISalle> sallesAccessibles) {
        ISalle choice = m_person.faitSonChoix(sallesAccessibles);
        //setPosition(choice);
        //System.out.println("AA");
        return choice;
    }

    @Override
    public ISalle getPosition() {
        return m_person.getPosition();
    }

    @Override
    public void setPosition(ISalle s) {
        m_person.setPosition(s);
        setCoordonnees(s.getX() * AVue.UNITE, s.getY() * AVue.UNITE);
    }
    
}
