package game.spirits.util;

import game.spirits.interfaces.Bullet;
import game.spirits.interfaces.Recyclable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Production {

    public List<Bullet> bullets = new ArrayList<>(1);

    public List<Recyclable> recyclableList = new ArrayList<>(1);

    public void newBullet(Bullet bullet) {
        bullets.add(bullet);
    }

    public void newBullets(Collection<Bullet> bullets) {
        this.bullets.addAll(bullets);
    }

    public void newRecyclables(Collection<Recyclable> recyclables) {
        this.recyclableList.addAll(recyclables);
    }

    public void newRecyclable(Recyclable recyclable) {
        recyclableList.add(recyclable);
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public List<Recyclable> getRecyclableList() {
        return recyclableList;
    }

    public Production addOtherProduction(Production production) {
        recyclableList.addAll(production.getRecyclableList());
        bullets.addAll(production.getBullets());
        return this;
    }
}



