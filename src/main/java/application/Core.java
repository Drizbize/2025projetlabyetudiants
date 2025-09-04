package application;

import java.io.IOException;
import java.util.Collection;
import labyrinthe.ILabyrinthe;
import labyrinthe.ISalle;
import personnages.Hero;
import vue2D.IVue;
import vue2D.sprites.HeroSprite;
import vue2D.sprites.ISprite;

/**
 *
 * @author arpecher
 */
public class Core {
    ISprite hero;
    ILabyrinthe labyrinthe;

    protected void initLabyrinthe() throws IOException {
        // creation du labyrinthe
        labyrinthe = new labyrinthe.Labyrinthe();
    }

    protected void initSprites(IVue vue) {
        // creation du heros 
        Hero h = new personnages.Hero(labyrinthe.getEntree());
        this.hero = new HeroSprite(h, labyrinthe);
        vue.add(this.hero);
    }

    protected void jeu(IVue vue) {
        // boucle principale
        ISalle destination = null;
        while (!labyrinthe.getSortie().equals(hero.getPosition())) {
            // ajustement etage courant: celui du héros
            labyrinthe.setEtageCourant(hero.getPosition().getEtage());
            // choix et deplacements de chaque sprite
            for (ISprite s : vue) {
                Collection<ISalle> sallesAccessibles = labyrinthe.sallesAccessibles(s);
                destination = s.faitSonChoix(sallesAccessibles); // on demande au personnage de faire son choix de salle
                s.setPosition(destination); // deplacement
                
            }
            // detection des collisions
            boolean collision = false;
            ISprite monstre = null;
            for (ISprite s : vue) {
                if (s != hero) {
                    if (s.getPosition() == hero.getPosition()) {
                        System.out.println("Collision !!");
                        collision = true;
                        monstre = s;
                    }
                }
            }
            if (collision) {
                vue.remove(monstre);
                vue.remove(hero);
                System.out.println("Perdu !");
                System.out.println("Plus que " + vue.size() + " personnages ...");
            }

            temporisation(10);
        }
        System.out.println("Gagné!");
    }

    protected void temporisation(int nb) {
        try {
            Thread.sleep(nb); // pause de nb millisecondes
        } catch (InterruptedException ie) {
        }
    }
}
