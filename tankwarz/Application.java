package tankwarz;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.MemoryImageSource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import javax.swing.JFrame;


/**
 *
 *
 */
public class Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        final TankController controller = new TankController();
        Tank tank = new Tank();
        final TankView view = new TankView(tank, controller);
        controller.setTank(tank);
        controller.setView(view);

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                view.repaint();
            }
        };
        TimerTask task2 = new TimerTask() {
            @Override
            public void run() {
                controller.doUpdateGameLogic();
            }
        };

        timer.scheduleAtFixedRate(task, 0, 1000/Globals.fps);
        timer.scheduleAtFixedRate(task2, 0, 1000/30);
        
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.setSize(new Dimension(500, 500));
        view.setTitle("Tank WarA (c) copyright 2011. All Rights Reserved.");
        view.setVisible(true);
        view.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));


    }
}
