
package tankwarz;

import java.awt.Image;

/**
 *
 */
public class Item {
    private double x;
    private double y;
    private Image image;
    private double width;
    private double height;
    private boolean solid = false;


    /**
     * @return the image
     */
    public Image getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * @param x the x to set
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @param y the y to set
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * @return the width
     */
    public double getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * @return the height
     */
    public double getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * @return the y
     */
    public double getY() {
        return y;
    }

    /**
     * @return the solid
     */
    public boolean isSolid() {
        return solid;
    }

    /**
     * @param solid the solid to set
     */
    public void setSolid(boolean solid) {
        this.solid = solid;
    }

    public String toString()
    {
        return "X:" + x + "Y:" + y;
    }
}
