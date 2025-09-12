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
    
    /**
     * Creating labyrinthe from etages
     * @param etages 
     */
    public Labyrinthe(ArrayList<IEtage> etages)
    {
        m_floors = new ArrayList<>(etages);
        setEtageCourant(m_floors.get(0));
        m_updateEnterExit();
    }
    
    /**
     * Copy constructor
     * @param l other
     */
    public Labyrinthe(Labyrinthe l)
    {
        this(new ArrayList<>(l.m_floors));
    }
    
    /**
     * Creating labyrinthe from file pathes
     * @param etagesFiles array of file pathes
     * @throws IOException 
     */
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
    
    @Override
    public int getDistance(ISalle s1, ISalle s2) throws RuntimeException
    {
        Collection<ISalle> path = chemin(s1, s2);
        if (path == null)
        {
            throw new RuntimeException("Error: path is null");
            //return -1;
        }
        return path.size() - 1;
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
    
    /**
     * updates variables enter and exit.
     * checks all etages of labyrinthe
     */
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
        
        if (m_roomEnter == null)
        {
            throw new RuntimeException("No enter");
        }
        if (m_roomExit == null)
        {
            throw new RuntimeException("No exit");
        }
    }
    
    /**
     * checking all salles by all etages to check if currentSalle estAdjacente
     * @param currentSalle the salle to check
     * @return a list of salles that are "adjacente"
     */
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
}
