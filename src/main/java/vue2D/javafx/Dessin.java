package vue2D.javafx;

import java.util.Collection;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import static labyrinthe.ESalle.ESCALIER_DESCENDANT;
import labyrinthe.Etage;
import labyrinthe.ILabyrinthe;
import labyrinthe.ISalle;
import vue2D.AVue;
import vue2D.sprites.ISprite;
import labyrinthe.IEtage;
import personnages.IPersonnage;
import utils.Utils;
import utils.Vector2;

/**
 *
 * @author INFO Professors team
 */
public class Dessin extends Canvas {
    /**
     * Max view distance of player in labyrinthe
     */
    private static int MAX_VIEW_DISTANCE = 10;

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
    
    /**
     * loading some images
     */
    public void chargementImages() {
        murImage = new Image("file:icons/mur0.gif");
        solImage = new Image("file:icons/pyramide.png");
        escalierM = new Image("file:icons/up.gif");
        escalierD = new Image("file:icons/down.gif");
    }
    
    /**
     * draw the background of the game
     */
    public void dessinFond() {
        tampon.setGlobalAlpha(1.0);
        //tampon.setFill(Color.BLACK);
        //tampon.fillRect(0, 0, unite * largeur, unite * hauteur);
        tampon.drawImage(solImage, 0, 0, unite * largeur, unite * hauteur);
    }
    
    /**
     * Draws all salles on the precised etage
     * @param etage the etage
     * @param hero the player
     */
    public void dessinSalles(IEtage etage, IPersonnage hero) {
        for (ISalle s : etage) {
            dessinSalle(s, hero);
        }
    }
    
    /**
     * Draws a salle.
     * Salles that are too far from player doesnt draw. But if player saw that salle so it will draws from far distance
     * @param s the salle
     * @param hero the player
     */
    public void dessinSalle(ISalle s, IPersonnage hero) {
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
        
        double coef = 1.0;
        
        if (hero != null)
        {
            coef = getCoefDistance(new Vector2(s.getX(), s.getY()), new Vector2(hero.getPosition().getX(), hero.getPosition().getY()));
            
            if (!s.isVisited())
            {
                if (coef <= 0.0)
                    return;

                int dist = labyrinthe.getDistance(hero.getPosition(), s);
                if (dist > MAX_VIEW_DISTANCE)
                    return;

                if (dist <= 2)
                {
                    s.setVisited(true);
                }
            }
            else if (coef <= 0.25)
            {
                coef = 0.25;
            }
        }
        
        if (img != null)
        {
            tampon.setGlobalAlpha(coef);
            tampon.drawImage(img, posX, posY, unite, unite);
        }
        else if (c != null)
        {
            tampon.setGlobalAlpha(coef);
            tampon.setFill(c);
            
            tampon.fillRect(posX, posY, unite, unite);
        }
        
        for (Vector2 dir : Utils.DIRECTION_POSITIONS)
        {
            Vector2 wallPos = new Vector2(s.getX() + dir.x, s.getY() + dir.y);
            boolean isWall = s.getEtage().isWallAt(wallPos.x, wallPos.y);
            if (isWall)
            {
                tampon.drawImage(murImage, wallPos.x * unite, wallPos.y * unite, unite, unite);
            }
        }
    }
    
    /**
     * Draws all sprites in the game on current etage where player is.
     * Sprites that are too far away from player is not drawing
     * @param hero player
     */
    public void drawSprites(IPersonnage hero)
    {
        for (ISprite sprite : sprites)
        {
            if (sprite.getPosition().getEtage() == labyrinthe.getEtageCourant())
            {
                double coef = 1.f;
                if (hero != null)
                {
                    coef = getCoefDistance(
                        new Vector2(sprite.getPosition().getX(), sprite.getPosition().getY()),
                        new Vector2(hero.getPosition().getX(), hero.getPosition().getY()));
                    
                    if (coef <= 0.0)
                        continue;

                    int dist = labyrinthe.getDistance(hero.getPosition(), sprite.getPosition());
                    if (dist > MAX_VIEW_DISTANCE)
                        continue;
                }
                
                tampon.setGlobalAlpha(coef);
                sprite.dessiner(tampon);
            }
                
        }
    }
    
    /**
     * Draws the shortest path from player's position to the exit
     * @param p player
     */
    public void dessinPlusCourtChemin(IPersonnage p) {
        if (p == null)
            return;
        
        Collection<ISalle> pathSalles = labyrinthe.chemin(p.getPosition(), sortie);
        if (pathSalles == null)
            return;
        
        for (ISalle s : pathSalles)
        {
            if (s.getEtage() == labyrinthe.getEtageCourant())
            {
                tampon.setGlobalAlpha(0.3);
                tampon.setFill(Color.CYAN);
                tampon.fillRect(s.getX() * unite, s.getY() * unite, unite, unite);
            }
            
        }
    }
    
    public void drawGameOver()
    {
        tampon.fillText("Game Over", 20 * unite, 20 * unite);
    }
    
    /**
     * Calculating within the formula the distance coef
     * @param pos1 from position
     * @param pos2 to position
     * @return the coeficient from 0.0 to 1.0
     */
    private double getCoefDistance(Vector2 pos1, Vector2 pos2)
    {
        Vector2 dist = new Vector2(pos2.x - pos1.x, pos2.y - pos1.y);
        return Utils.clamp((8 / (dist.getMagnitude() + 1)) - 0.75, 0.0, 1.0);
    }

}
