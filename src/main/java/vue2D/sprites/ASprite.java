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
import utils.Utils;
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
    private ISalle m_prevSalle;
    private ISalle m_selectedSalle;
    
    public ASprite(Image img, IPersonnage person) {
        m_img = img;
        m_pos = new Vector2();
        m_person = person;
        m_prevSalle = person.getPosition();
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
        
        return choice;
    }

    @Override
    public ISalle getPosition() {
        return m_person.getPosition();
    }

    @Override
    public void setPosition(ISalle s) {
        if (m_selectedSalle == null)
        {
            if (m_prevSalle != s)
            {
                m_selectedSalle = s;
            }
            else
            {
                setCoordonnees(s.getX() * AVue.UNITE, s.getY() * AVue.UNITE);
                m_prevSalle = s;
                m_person.setPosition(s);
                return;
            }
        }

        int sX = m_selectedSalle.getX();
        int sY = m_selectedSalle.getY();

        int startX = m_prevSalle.getX() * AVue.UNITE;
        int startY = m_prevSalle.getY() * AVue.UNITE;
        int endX = sX * AVue.UNITE;
        int endY = sY * AVue.UNITE;

        double dirX = endX - startX;
        double dirY = endY - startY;

        double coefX = dirX != 0 ? (m_pos.x - startX) / dirX : 1.0;
        double coefY = dirY != 0 ? (m_pos.y - startY) / dirY : 1.0;

        coefX = Math.min(coefX + 0.1, 1.0);
        coefY = Math.min(coefY + 0.1, 1.0);

        int newX = (int)Utils.lerp(startX, endX, coefX);
        int newY = (int)Utils.lerp(startY, endY, coefY);

        setCoordonnees(newX, newY);

        if (Math.abs(newX - endX) < 1 && Math.abs(newY - endY) < 1) {
            m_prevSalle = m_selectedSalle;
            m_selectedSalle = null;
            setCoordonnees(endX, endY);
            m_person.setPosition(m_prevSalle);
        }
    }
    
    

}
