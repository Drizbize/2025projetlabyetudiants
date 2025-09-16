package labyrinthe;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import utils.ExceptionInvalidFile;
import utils.Utils;
import utils.Vector2;

/**
 *
 * @author INFO Professors team
 */
public final class Etage extends ArrayList<ISalle> implements IEtage {
    private int largeur;
    private int hauteur;
    private int num;
    private boolean[][] m_walls;
    
    public Etage(){
        largeur = 40;
        hauteur = 40;
        m_walls = new boolean[hauteur][largeur];
        num = 1;
    }
    
    public Etage(int id){
        this.num = id;
        m_walls = new boolean[hauteur][largeur];
    }
    
    public Etage(int id, String file) throws IOException
    {
        this.num = id;
        charger(file);
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
            if (!add(newFloor))
            {
                throw new ExceptionInvalidFile("Loading file is invalid");
            }
        }
        
        m_walls = new boolean[hauteur][largeur];
        for (ISalle s : this)
        {
            int posX = s.getX();
            int posY = s.getY();
            for (Vector2 position : Utils.DIRECTION_POSITIONS)
            {
                Vector2 wallPos = new Vector2(posX + position.x, posY + position.y);
               
                if (getAt(wallPos.x, wallPos.y) == null && !isOutOfMap(wallPos.x, wallPos.y))
                {
                    m_walls[wallPos.y][wallPos.x] = true;
                }
            }
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
        if (!isSalleCollides(salle) && isOutOfMap(salle.getX(), salle.getY()))
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
    
    @Override
    public boolean isWallAt(int x, int y) {
        if (isOutOfMap(x, y))
            return false;
        
        return m_walls[y][x];
    }
    
    public boolean isOutOfMap(int x, int y)
    {
        return x < 0 || y < 0 || x >= largeur || y >= hauteur;
    }
    
    /**
     * checks if current salle has the same position with other one
     * @param salle the salle to check
     * @return true if collides
     */
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
    
    /**
     * creates a list of salles who has the same type from etage
     * @param etage etage class
     * @param type type to select
     * @return list of salles of one type
     */
    public static ArrayList<ISalle> getSallesByType(IEtage etage, ESalle type)
    {
        ArrayList<ISalle> salles = new ArrayList<>();
        for (ISalle s : etage)
        {
            if (s.getType() == type)
            {
                salles.add(s);
            }
        }
        
        return salles;
    }
}
