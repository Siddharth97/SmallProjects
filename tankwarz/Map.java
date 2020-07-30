

package tankwarz;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 */
public class Map {

    public Map(String file)
    {
        FileInputStream fis = null;
        try {
            File f = new File(file);
            fis = new FileInputStream(f);
            int c = -1;
            double x = 0;
            double y = 0;
            while( (c = fis.read()) != -1 ){
                try {
                    if( c == '\n'){
                        y++;
                        x = 0;
                    }
                    else if(c == '#')
                    {
                        Item item = new Item();
                        item.setImage(ImageIO.read(new File("./media/tank.gif")));
                        item.setWidth(80);
                        item.setHeight(80);
                        item.setX(x*100);
                        item.setY(y*100);
                        item.setSolid(false);
                        Globals.items.add(item);
                        x++;
                    }
                    else if(c == 'B')
                    {
                        Item item = new Item();
                        item.setImage(ImageIO.read(new File("./media/brick.gif")));
                        item.setWidth(80);
                        item.setHeight(80);
                        item.setX(x*100);
                        item.setY(y*100);
                        item.setSolid(true);
                        Globals.items.add(item);
                        x++;
                    } else {
                        x++;
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch(Exception ex){}
    }
}
