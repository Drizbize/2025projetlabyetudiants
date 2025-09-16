package labyrinthe;

import java.io.IOException;
import java.util.Collection;

/**
 *
 * @author INFO Professors team
 */
// un etage est une collection de salles disposees sur une grille
public interface IEtage extends Collection<ISalle>{
    public int getLargeur(); // largeur de la grille
    public int getHauteur(); // hauteur de la grille    
    public int getNum(); // numero de l'etage
    public ISalle getAt(int x, int y); // get Salle at position
    public boolean isWallAt(int x, int y);
    public void charger(String file) throws IOException; // charger les salles d'un fichier
}
