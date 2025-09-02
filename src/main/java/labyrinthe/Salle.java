/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package labyrinthe;

import utils.Vector2;

/**
 *
 * @author rdanilchenko
 */
public class Salle implements ISalle {
    private Vector2 m_position;
    private ESalle m_type;
    private IEtage m_floor;
    
    public Salle(Vector2 pos, ESalle type, IEtage floor)
    {
        m_position = pos;
        m_type = type;
        m_floor = floor;
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
        
        return (x == 1 && y == 0) || (y == 1 && x == 0);
    }
    
}
