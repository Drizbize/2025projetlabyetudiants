package vue2D.javafx;

import java.util.Collection;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import static labyrinthe.ESalle.ESCALIER_DESCENDANT;
import labyrinthe.ILabyrinthe;
import labyrinthe.ISalle;
import vue2D.AVue;
import vue2D.sprites.ISprite;
import labyrinthe.IEtage;

/**
 *
 * @author INFO Professors team
 */
public class Dessin extends Canvas {

    private Collection<ISprite> sprites;
    private int unite;

    // donnees labyrinthe labyrinthe
    private ILabyrinthe labyrinthe;
    private ISalle entree;
    private ISalle sortie;
    private int largeur;
    private int hauteur;

    private GraphicsContext tampon;
    private Image murImage;
    private Image solImage;
    private Image escalierM;
    private Image escalierD;
    private int tailleLinkH = 6;
    private int tailleLinkL = 2;

    public Dessin(ILabyrinthe labyrinthe, Collection<ISprite> sprites) {
        this.labyrinthe = labyrinthe;
        this.sprites = sprites;
        this.unite = AVue.UNITE;
        entree = labyrinthe.getEntree();
        sortie = labyrinthe.getSortie();
        largeur = labyrinthe.getEtageCourant().getLargeur();
        hauteur = labyrinthe.getEtageCourant().getHauteur();
        setWidth(largeur * unite);
        setHeight(hauteur * unite);
        tampon = this.getGraphicsContext2D();
        chargementImages();
        dessinFond();
    }

    public void chargementImages() {
        murImage = new Image("file:icons/mur0.gif");
        solImage = new Image("file:icons/pyramide.png");
        escalierM = new Image("file:icons/up.gif");
        escalierD = new Image("file:icons/down.gif");
    }

    public void dessinFond() {
        tampon.drawImage(solImage, 0, 0, unite * largeur,
                unite * hauteur);
    }

    public void dessinSalles(IEtage etage) {
        for (ISalle s : etage) {
            //Color c = Color.rgb(200, 200, 200);
            dessinSalle(s);
        }
    }

    public void dessinSalle(ISalle s) {
        int posX = unite * s.getX();
        int posY = unite * s.getY();
        
        Image img = null;
        Color c = null;
        switch (s.getType()) {
            case ESCALIER_DESCENDANT:
                img = escalierD;
                break;
            case ESCALIER_MONTANT:
                img = escalierM;
                break;
            case NORMALE:
                c = Color.rgb(200, 200, 200);
                break;
            case ENTREE:
                c = Color.rgb(200, 20, 30);
                break;
            case SORTIE:
                c = Color.GREEN;
                break;
        }
        
        if (img != null)
        {
            tampon.drawImage(img, posX, posY, unite, unite);
        }
        else if (c != null)
        {
            tampon.setFill(c);
            tampon.fillRect(posX, posY, unite, unite);
        }
    }
    
    public void drawSprites()
    {
        for (ISprite sprite : sprites)
        {
            if (sprite.getPosition().getEtage() == labyrinthe.getEtageCourant())
                sprite.dessiner(tampon);
        }
    }

    public void dessinPlusCourtChemin(ISprite p) {
        // ...
    }

}
