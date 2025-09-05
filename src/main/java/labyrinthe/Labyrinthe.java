package labyrinthe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import personnages.IPersonnage;

/**
 *
 * @author professor team
 */
public final class Labyrinthe extends ArrayList<ISalle> implements ILabyrinthe {
    private IEtage etageCourant = new Etage(1);
    private final ArrayList<IEtage> m_floors = new ArrayList<>();
    private ISalle m_roomEnter;
    private ISalle m_roomExit;
    
    public Labyrinthe() throws IOException
    {
        etageCourant.charger("etages/etage1N.txt");
        m_floors.add(etageCourant);
        
        etageCourant = new Etage(2);
        etageCourant.charger("etages/etage2N.txt");
        m_floors.add(etageCourant);
        
        setEtageCourant(m_floors.get(0));
        
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

    @Override
    public Collection<ISalle> sallesAccessibles(IPersonnage hero) {
        ArrayList<ISalle> salles = new ArrayList<>();
        ISalle heroPos = hero.getPosition();
        
        for (IEtage floors : m_floors) // not optimized
        {
            for (ISalle s : floors)
            {
                if (heroPos.estAdjacente(s))
                    salles.add(s);
            }
        }
        
        
        return salles;
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
    public void setEtageCourant(IEtage etage) {
        this.etageCourant = etage;
        //updateEtageEnterExit();
    }

    @Override
    public Collection<ISalle> chemin(ISalle u, ISalle v) {
        return null;
    }
    
    public Collection<ISalle> sallesAccessibles(ISalle currentSalle)
    {
        ArrayList<ISalle> salles = new ArrayList<>();
        for (ISalle s : etageCourant)
        {
            if (currentSalle.estAdjacente(s))
            {
                salles.add(s);
            }
        }
        
        return salles;
    }

    
}
