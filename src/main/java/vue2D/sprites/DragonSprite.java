/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vue2D.sprites;

import javafx.scene.image.Image;
import personnages.IPersonnage;

/**
 *
 * @author rdanilchenko
 */
public class DragonSprite extends ASprite {
    
    public DragonSprite(IPersonnage person) {
        super(new Image("file:icons/monstre1.gif"), person, 2);
    }
    
}
