
package tankwarz;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.RenderingHints;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class TankView extends JFrame implements ComponentListener {

    Tank tank = null;
    TankController controller = null;
    Image image = null;

    public TankView(Tank tank, TankController controller) {
        try {
            //initial values for tank
            Map map = new Map("./map.txt");
            Globals.map = ImageIO.read(new File("./media/map.jpg"));
            this.tank = tank;
            Globals.tank = tank;
            this.controller = controller;
            this.addMouseListener(controller);
            this.addKeyListener(controller);
            this.addMouseMotionListener(controller);
            tank.setX(250);
            tank.setY(250);
            this.addComponentListener(this);
            image = createImage(getWidth(), getHeight());
        } catch (IOException ex) {
            Logger.getLogger(TankView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void drawBullets() {
        Graphics2D bg = (Graphics2D) image.getGraphics();
        bg.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        bg.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        bg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        bg.setColor(Color.orange);

        for (int i = 0; i < Globals.list.size(); i++) {
            Bullet bul = (Bullet) Globals.list.get(i);
            bg.fillOval((int) bul.getX() + (Bullet.WIDTH / 2), (int) bul.getY() + (Bullet.WIDTH / 2), (Bullet.WIDTH), (Bullet.WIDTH));
        }
    }

    public void drawItems() {
        Graphics2D bg = (Graphics2D) image.getGraphics();
        bg.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        bg.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        bg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (int i = 0; i < Globals.items.size(); i++) {
            Item item = (Item) Globals.items.get(i);
            bg.drawImage(item.getImage(), (int) item.getX(), (int) item.getY(), null);
        }

    }

    public void paint(Graphics g) {
        //move the tank across the screen
        if (image == null) {
            image = createImage(getWidth(), getHeight());
            System.out.println("Recreated");
            return;
        }
        Graphics2D bg = (Graphics2D) image.getGraphics();
        bg.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        bg.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        bg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        bg.setColor(Color.white);
        bg.fillRect(0, 0, getWidth(), getHeight());
        bg.drawImage(Globals.map, 0, 0, this);

        DecimalFormat df = new DecimalFormat("####");
        String str = "X:" + df.format((int) tank.getX()) + " Y:" + df.format((int) tank.getY());

        bg.drawString(str, 30, 40);

        double mx = MouseInfo.getPointerInfo().getLocation().x;
        double my = MouseInfo.getPointerInfo().getLocation().y;

        Collisions.checkBullets();
        Collisions.checkTank(bg);
        
        drawItems();
        AffineTransform gt = bg.getTransform();
        AffineTransform at = new AffineTransform();
        at.rotate(tank.ra, tank.getX(), tank.getY());
        
        bg.transform(at);

        
        double vx = tank.getX() - (tank.getWidth() / 2);
        double vy = tank.getY() - (tank.getHeight() / 2);

        int ty = (int) vy;
        int tx = (int) vx;

        bg.drawImage(tank.getImage(), tx, ty, this);
        //bg.setColor(Color.orange);
        //bg.drawRect((int)(tank.getX()-(tank.getWidth()/2)), (int)(tank.getY()-(tank.getWidth()/2)), (int)(tank.getWidth()),(int) (tank.getWidth()));

        double mxp = (mx - tank.getX());
        double myp = ((tank.getY()) - my);
        double theta = Math.atan2(mxp, myp);

        at = new AffineTransform();
        at.rotate(theta - tank.ra, tank.getX(), tank.getY());
        bg.transform(at);


        
        bg.setTransform(gt);
        drawBullets();

        at = new AffineTransform();
        at.rotate(theta, tank.getX(), tank.getY());
        bg.transform(at);

        bg.drawImage(tank.getTurretImage(), tx, ty, this);
        g.drawImage(image, 0, 0, this);

    }

    public void componentResized(ComponentEvent e) {
        image = null;
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void componentShown(ComponentEvent e) {
    }

    public void componentHidden(ComponentEvent e) {
    }
}
