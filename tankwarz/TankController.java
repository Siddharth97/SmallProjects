
package tankwarz;

import java.awt.MouseInfo;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

class TankController implements KeyListener, MouseListener, MouseMotionListener {

    private Tank tank = null;
    private TankView view = null;
    double mx, my;

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            doDecelerate();
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            doAccelerate();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            tank.ra += Globals.turnSpeed;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            tank.ra -= Globals.turnSpeed;
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            doFireCannon();
        }
    }

    public void doAccelerate() {
        Globals.speed--;
    }

    public void doDecelerate() {
        Globals.speed++;
    }

    public void keyReleased(KeyEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        doFireCannon();
    
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    /**
     * @return the tank
     */
    public Tank getTank() {
        return tank;
    }

    /**
     * @param tank the tank to set
     */
    public void setTank(Tank tank) {
        this.tank = tank;
    }

    /**
     * @return the view
     */
    public TankView getView() {
        return view;
    }

    /**
     * @param view the view to set
     */
    public void setView(TankView view) {
        this.view = view;
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
        mx = e.getX();
        my = e.getY();
    }

    public void doFireCannon() {
        Bullet b = new Bullet();
        double ox = tank.getX() - (Bullet.WIDTH/2);
        double oy = tank.getY() - (Bullet.WIDTH/2);
        b.setX(ox);
        b.setY(oy);

        double speed = 10;
        double dx = (ox - mx);
        double dy = (oy - my);
        double speedX = dx / Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
        double speedY = dy / Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
        speedX *= speed;
        speedY *= speed;
        b.setVx(-speedX);
        b.setVy(-speedY);
        Globals.list.add(b);

        new SoundPlayer("./media/bang.wav").start();

    }

    public void doUpdateGameLogic() {
        tank.setX(tank.getX() + tank.getVx());
        tank.setY(tank.getY() + tank.getVy());

        for (int i = 0; i < Globals.list.size(); i++) {
            Bullet b = (Bullet) Globals.list.get(i);
            b.setX(b.getX() + b.getVx());
            b.setY(b.getY() + b.getVy());
        }

        double a = (double) tank.getX();
        double b = (double) tank.getY();
        double r = 80;
        
        double n = tank.ra;
        double t = (n + (Math.PI / 2));
        double x = a + (r * Math.cos(t));
        double y = b + (r * Math.sin(t));

        double dx = x;
        double dy = y;
        double ox = tank.getX();
        double oy = tank.getY();
        dx = -dx + ox;
        dy = -dy + oy;
        double speedX = dx / Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
        double speedY = dy / Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
        speedX *= Globals.speed;
        speedY *= Globals.speed;

        tank.setVx(speedX);
        tank.setVy(speedY);


    }
}
