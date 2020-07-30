
package tankwarz;

import java.awt.Color;
import java.awt.Graphics;

/**
 */
public class Collisions {

    public static void checkBullets() {
        for (int j = 0; j < Globals.list.size(); j++) {
            Bullet bull = (Bullet) Globals.list.get(j);
            for (int i = 0; i < Globals.items.size(); i++) {
                Item item = (Item) Globals.items.get(i);
                checkHitBullet(item, bull);
            }
        }
    }

    public static void checkTank(Graphics g) {

        for (int j = 0; j < Globals.items.size(); j++) {
            Item item = (Item) Globals.items.get(j);
            boolean b = checkHitTank(g, item);
            if (b) {
                Globals.tank.setVx(-Globals.tank.getVx());
                Globals.tank.setVy(-Globals.tank.getVy());
                Globals.speed = 0;
            }
        }
    }

    public static boolean checkHitTank(Graphics g, Item item) {
        Tank tank = Globals.tank;
        //g.setColor(Color.red);
        double offs = tank.getWidth() / 2;
        //g.setColor(Color.green);
        double tx = tank.getX() - offs;
        double ty = tank.getY() - offs;
        double tw = tank.getWidth();
        //g.drawRect((int) item.getX(), (int) item.getY(), (int) item.getWidth(), (int) item.getHeight());
        //g.drawRect((int) tx, (int) ty, (int) tank.getWidth(), (int) tank.getHeight());


        if (tx + tw > item.getX() && tx < item.getX() + item.getWidth()) {
            if (ty + tw > item.getY() && ty < item.getY()+ item.getWidth()) {
                System.out.println("Hit:" + item);
                return true;
            }
        }

        return false;

    }

    public static void checkHitBullet(Item item, Bullet bull) {
        if ((bull.getY() > item.getY()) && (bull.getX() > item.getX())) {
            if ((bull.getX() + Bullet.WIDTH) > item.getX() && (bull.getX() + Bullet.WIDTH) < (item.getX() + item.getWidth())) {
                if ((bull.getY() + Bullet.WIDTH) > item.getY() && (bull.getY() + Bullet.WIDTH) < (item.getY() + item.getWidth())) {
                    Globals.items.remove(item);
                    Globals.list.remove(bull);
                }
            }
        }
    }
}
