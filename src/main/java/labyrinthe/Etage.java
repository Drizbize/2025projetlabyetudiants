package labyrinthe;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import utils.Vector2;

/**
 *
 * @author INFO Professors team
 */
public class Etage extends ArrayList<ISalle> implements IEtage {

    private int largeur;
    private int hauteur;
    private int num;
    
    public Etage(){
        largeur = 40;
        hauteur = 40;
        num = 1;
    }
    
    public Etage(int id){
        this.num = id;
    }
 
    @Override
    public void charger(String file) throws IOException {
        this.clear();
        List<String> lignes = Files.readAllLines(Paths.get(file));
        // dimensions
        String entete = lignes.get(0);
        String[] mots = entete.split(" ");
        largeur = Integer.parseInt(mots[0]);
        hauteur = Integer.parseInt(mots[1]);
        lignes.remove(0);
        // salles
        for (String ligne : lignes) {
            mots = ligne.split(" ");
            Vector2 pos = new Vector2();
            pos.x = Integer.parseInt(mots[0]);
            pos.y = Integer.parseInt(mots[1]);
            
            ESalle salleType = getTypeFromChar(mots[2].charAt(0));
            
            Salle newFloor = new Salle(pos, salleType, this);
            add(newFloor);
            //super.add(newFloor);
        }
    }
    
    public ESalle getTypeFromChar(char type)
    {
        switch (type) {
            case 'N':
                return ESalle.NORMALE;
            case 'M':
                return ESalle.ESCALIER_MONTANT;
            case 'D':
                return ESalle.ESCALIER_DESCENDANT;
            case 'E':
                return ESalle.ENTREE;
            case 'S':
                return ESalle.SORTIE;
            default:
                throw new AssertionError();
        }
    }
    
    @Override
    public int getLargeur() {
        return largeur;
    }

    @Override
    public int getHauteur() {
        return hauteur;
    }  

    @Override
    public int getNum() {
        return num;
    }
    
    @Override
    public boolean add(ISalle salle)
    {
        if (!isSalleCollides(salle) && (salle.getX() < 0 || salle.getY() < 0 || salle.getX() >= largeur || salle.getY() >= hauteur))
            return false;
        
        super.add(salle);
        return true;
    }
    
    @Override
    public ISalle getAt(int x, int y)
    {
        for (ISalle s : this)
        {
            if (s.getX() == x && s.getY() == y)
            {
                return s;
            }
        }
        
        return null;
    }
    
    public boolean isSalleCollides(ISalle salle)
    {
        int x = salle.getX();
        int y = salle.getY();
        
        for (ISalle s : this)
        {
            if (s.getX() == x && s.getY() == y)
            {
                return true;
            }
        }
        return false;
    }
}
