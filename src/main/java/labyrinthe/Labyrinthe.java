package labyrinthe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import personnages.IPersonnage;

/**
 *
 * @author professor team
 */
public class Labyrinthe extends ArrayList<ISalle> implements ILabyrinthe {
    private IPersonnage m_hero;
    private IEtage etageCourant;
    protected ArrayList<IEtage> m_floors;
    private ISalle m_roomEnter;
    private ISalle m_roomExit;
    
    public Labyrinthe(ArrayList<IEtage> etages)
    {
        m_floors = new ArrayList<>(etages);
        setEtageCourant(m_floors.get(0));
        m_updateEnterExit();
    }
    
    public Labyrinthe(Labyrinthe l)
    {
        this(l.m_floors);
    }
    
    public Labyrinthe(String[] etagesFiles) throws IOException
    {
        m_floors = new ArrayList<>();
        
        for (String s : etagesFiles)
        {
            IEtage e = new Etage(m_floors.size() + 1);
            e.charger(s);
            m_floors.add(e);
        }
        
        setEtageCourant(m_floors.get(0));
        
        m_updateEnterExit();
    }

    @Override
    public Collection<ISalle> sallesAccessibles(IPersonnage hero) {
        return sallesAccessiblesBySalle(hero.getPosition());
    }

    @Override
    public ISalle getEntree() {
        return m_roomEnter;
    }

    @Override
    public ISalle getSortie() {
        return m_roomExit;   
    }

    @Override
    public IEtage getEtageCourant() {
        return this.etageCourant;
    }
    
    @Override
    public IEtage getUpEtage() {
        int currentEtageId = m_floors.indexOf(etageCourant);
        currentEtageId++;
        if (currentEtageId >= m_floors.size())
            return null;
        
        return m_floors.get(currentEtageId);
    }

    @Override
    public IEtage getDownEtage() {
        int currentEtageId = m_floors.indexOf(etageCourant);
        currentEtageId--;
        if (currentEtageId < 0)
            return null;
        
        return m_floors.get(currentEtageId);
    }
    
    @Override
    public IEtage getEtageFromId(int id)
    {
        return m_floors.get(id);
    }
    
    @Override
    public int getEtageCount()
    {
        return m_floors.size();
    }

    @Override
    public final void setEtageCourant(IEtage etage) {
        this.etageCourant = etage;
        //updateEtageEnterExit();
    }

    @Override
    public Collection<ISalle> chemin(ISalle u, ISalle v) {
        return null;
    }
    
    public Collection<ISalle> sallesAccessiblesBySalle(ISalle currentSalle)
    {
        ArrayList<ISalle> salles = new ArrayList<>();
        for (IEtage floors : m_floors) // not optimized
        {
            for (ISalle s : floors)
            {
                if (currentSalle.estAdjacente(s))
                {
                    salles.add(s);
                }
            }
        }
        
        return salles;
    }
    
    @Override
    public void setHero(IPersonnage hero)
    {
        m_hero = hero;
    }
    
    @Override
    public IPersonnage getHero() {
        return m_hero;
    }

    private void m_updateEnterExit()
    {
        for (IEtage e : m_floors)
        {
            for (ISalle s : e)
            {
                if (s.getType() == ESalle.ENTREE)
                {
                    m_roomEnter = s;
                }
                else if (s.getType() == ESalle.SORTIE)
                {
                    m_roomExit = s;
                }
            }
        }
    }
}
