/** 
* FILE: SpriteAnimation.java
* ASSIGNMENT: Final Project - Tamaclonechi
* COURSE: CSc 335; Fall 2020;
* PURPOSE: The following class is used for the animation of the Tamaclonechi 
* displayed on the GUI interface. 
* 
* @author Fernando Ruiz
* @see TamaclonechiGUI
*/

import java.io.Serializable;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class SpriteAnimation extends Transition implements Serializable {
	
	private static final long serialVersionUID = 1L;
    private final ImageView imageView;
    private final int count;
    private final int columns;
    private int offsetX;
    private int offsetY;
    private final int width;
    private final int height;
    
    public SpriteAnimation(
            ImageView imageView, Duration duration, 
            int count,   int columns, int offsetX,
            int offsetY, int width,   int height) {
        this.imageView = imageView;
        this.count     = count;
        this.columns   = columns;
        this.offsetX   = offsetX;
        this.offsetY   = offsetY;
        this.width     = width;
        this.height    = height;
        setCycleDuration(duration);
        setCycleCount(Animation.INDEFINITE);
        setInterpolator(Interpolator.LINEAR);
        this.imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
    }
    
    /** 
    * The following function interpolates the frames between the animation.
    * 
    * @param frac is a double used to calculate the index of frame.
    */
    protected void interpolate(double frac) {
        final int index = Math.min((int) Math.floor(frac * count), count - 1);
            final int x = (index % columns) * width  + offsetX;
            final int y = (index / columns) * height + offsetY;
            imageView.setViewport(new Rectangle2D(x, y, width, height));
    }
}