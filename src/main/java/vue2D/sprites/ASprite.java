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
import vue2D.AVue;

/**
 *
 * @author rdanilchenko
 */
public abstract class ASprite implements ISprite {
    protected IPersonnage m_person;
    private Image m_img;
    private double m_posXf;
    private double m_posYf;
    private ISalle m_prevSalle;
    private ISalle m_selectedSalle;
    private double m_speed;
    
    public ASprite(Image img, IPersonnage person, int speed) {
        m_img = img;
        m_posXf = 0.0;
        m_posYf = 0.0;
        m_person = person;
        m_prevSalle = person.getPosition();
        m_speed = (double)speed / 100;
    }

    @Override
    public void dessiner(GraphicsContext g) {
        g.drawImage(m_img, (int)m_posXf, (int)m_posYf, AVue.UNITE, AVue.UNITE);
    }

    @Override
    public void setCoordonnees(int xpix, int ypix) {
        m_posXf = xpix;
        m_posYf = ypix;
    }
    
    @Override
    public void setCoordonnees(double xpix, double ypix) {
        m_posXf = xpix;
        m_posYf = ypix;
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

        double coefX = dirX != 0 ? (m_posXf - startX) / dirX : 1.0;
        double coefY = dirY != 0 ? (m_posYf - startY) / dirY : 1.0;

        coefX = Math.min(coefX + m_speed, 1.0);
        coefY = Math.min(coefY + m_speed, 1.0);

        double newX = Utils.lerp(startX, endX, coefX);
        double newY = Utils.lerp(startY, endY, coefY);

        setCoordonnees(newX, newY);

        if (Math.abs(newX - endX) < 1 && Math.abs(newY - endY) < 1) {
            m_prevSalle = m_selectedSalle;
            m_selectedSalle = null;
            setCoordonnees(endX, endY);
            m_person.setPosition(m_prevSalle);
        }
    }
    
    

}
