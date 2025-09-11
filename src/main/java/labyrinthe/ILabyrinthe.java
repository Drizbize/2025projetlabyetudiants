package labyrinthe;

import java.util.Collection;

import personnages.IPersonnage;

/**
 *
 * @author INFO Professors team
 */
// un labyrinthe est une collection de salles, reparties sur plusieurs etages
public interface ILabyrinthe extends Collection<ISalle>{ 
    public Collection<ISalle> sallesAccessibles(IPersonnage hero);  // renvoie les salles accessibles pour le heros
    public ISalle getEntree(); // accesseur sur l'entree 
    public ISalle getSortie(); // accesseur sur la sortie
    public IEtage getEtageCourant(); // accesseurs sur l'etage affiche
    public IEtage getUpEtage(); // get up stairs etage from current
    public IEtage getDownEtage(); // get down stairs etage from current
    public IEtage getEtageFromId(int id); // get etage from id
    public int getEtageCount(); // returns count of all etages
    public void setHero(IPersonnage hero); // setting hero in labyrinthe
    public IPersonnage getHero(); // returns the labyrinth's hero
    public void setEtageCourant(IEtage etage); // setting current etage
    public Collection<ISalle> chemin(ISalle u, ISalle v); // un plus court chemin entre u et v
    public int getDistance(ISalle s1, ISalle s2) throws RuntimeException; // getting distance from salle to salle
}
