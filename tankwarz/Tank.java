
package tankwarz;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Tank extends Vehicle {

    double tx, ty;
    double ra;

    private Image image = null;
    private Image turret = null;


    public Tank()
    {
        try {
            turret = ImageIO.read(new File("./media/turret.gif"));
            image = ImageIO.read(new File("./media/tank.gif"));
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public Image getTurretImage()
    {
        return turret;
    }
    
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
}
