/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package labyrinthe;

import java.util.Objects;
import static labyrinthe.ESalle.ESCALIER_DESCENDANT;
import static labyrinthe.ESalle.ESCALIER_MONTANT;
import utils.Vector2;

/**
 *
 * @author rdanilchenko
 */
public class Salle implements ISalle {
    private Vector2 m_position;
    private ESalle m_type;
    private IEtage m_floor;
    
    public Salle()
    {
       m_type = ESalle.NORMALE;
       m_floor = new Etage();
    }
    
    public Salle(Vector2 pos, ESalle type, IEtage floor)
    {
        m_position = pos;
        m_type = type;
        m_floor = floor;
    }
    
    public Salle(int x, int y, ESalle type, IEtage floor)
    {
        this(new Vector2(x, y), type, floor);
    }
    
    @Override
    public int getX() {
        return m_position.x;
    }

    @Override
    public int getY() {
        return m_position.y;
    }

    @Override
    public ESalle getType() {
        return m_type;
    }

    @Override
    public IEtage getEtage() {
        return m_floor;
    }

    @Override
    public boolean estAdjacente(ISalle autre) {
        int x = Math.abs(m_position.x - autre.getX());
        int y = Math.abs(m_position.y - autre.getY());
        
        boolean canGo = true;
        
        if (m_type == ESalle.ESCALIER_MONTANT && autre.getType() == ESalle.ESCALIER_DESCENDANT)
        {
            canGo &= m_floor.getNum() < autre.getEtage().getNum() && x == 0 && y == 0;
        }
        else if (m_type == ESalle.ESCALIER_DESCENDANT && autre.getType() == ESalle.ESCALIER_MONTANT)
        {
            canGo &= m_floor.getNum() > autre.getEtage().getNum() && x == 0 && y == 0;
        }
        else
        {
            canGo &= (x == 0 && y == 1) || (x == 1 && y == 0);
            canGo &= m_floor == autre.getEtage();
        }
        
        return canGo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.m_position);
        hash = 29 * hash + Objects.hashCode(this.m_type);
        hash = 29 * hash + Objects.hashCode(this.m_floor);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Salle other = (Salle) obj;
        if (!Objects.equals(this.m_position, other.m_position)) {
            return false;
        }
        if (this.m_type != other.m_type) {
            return false;
        }
        return Objects.equals(this.m_floor, other.m_floor);
    }
    
    
}
