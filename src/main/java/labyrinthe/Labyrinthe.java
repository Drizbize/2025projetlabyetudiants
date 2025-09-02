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
    private IEtage etageCourant = new Etage();
    private final ArrayList<IEtage> m_floors = new ArrayList<>();
    private ISalle m_roomEnter;
    private ISalle m_roomExit;
    
    public Labyrinthe() throws IOException
    {
        etageCourant.charger("etages/etage1N.txt");
        m_floors.add(etageCourant);
        
        etageCourant = new Etage();
        etageCourant.charger("etages/etage2N.txt");
        m_floors.add(etageCourant);
        
        setEtageCourant(m_floors.get(0));
    }

    @Override
    public Collection<ISalle> sallesAccessibles(IPersonnage heros) {
        throw new UnsupportedOperationException("Not supported yet."); 
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
    public void setEtageCourant(IEtage etage) {
        this.etageCourant = etage;
        updateEtageEnterExit();
    }

    @Override
    public Collection<ISalle> chemin(ISalle u, ISalle v) {
        return null;
    }
    
    public void updateEtageEnterExit()
    {
        m_roomEnter = null;
        m_roomExit = null;
        for (ISalle s : etageCourant)
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
