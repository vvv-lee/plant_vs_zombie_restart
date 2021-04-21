package game.main.map;

import game.main.Game;
import game.spirits.interfaces.*;
import game.spirits.recyclable.Sun;
import game.spirits.util.Production;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class MapData {


    protected List<List<MapLawn>> mapLawnsList;

    protected List<Recyclable> recyclableList;

    protected List<List<Zombie>> zombiesList;
    protected List<List<Bullet>> bulletsList;

    public MapData(GameMap gameMap) {
        int rowNum = gameMap.getMapInfo().maxRowNum();
        mapLawnsList = new ArrayList<>(rowNum);
        recyclableList = new ArrayList<>();
        bulletsList = new ArrayList<>();
        zombiesList = new ArrayList<>();
        int col;
        int row = 0;
        while (row < rowNum) {
            List<MapLawn> rowLawns = new ArrayList<>();
            mapLawnsList.add(rowLawns);
            bulletsList.add(new ArrayList<>());
            zombiesList.add(new ArrayList<>());
            col = 0;
            while (col < 9) {
                MapLawn mapLawn = new MapLawn(gameMap, col, row);
                rowLawns.add(mapLawn);
                col++;
            }
            row++;
        }
    }

    public List<List<Bullet>> getBulletsList() {
        return bulletsList;
    }

    public List<List<MapLawn>> getMapLawnsList() {
        return mapLawnsList;
    }

    public List<Recyclable> getRecyclableList() {
        return recyclableList;
    }

    public List<List<Zombie>> getZombiesList() {
        return zombiesList;
    }

    public boolean haveZombie(double beginX, double overX, int... rows) {
        if (rows.length == 0) {
            for (List<Zombie> zombieList : this.getZombiesList()) {
                if (zombieList.size() > 0) return true;
            }
            return false;
        }
        for (int row : rows) {
            if (row < 0 || row >= mapLawnsList.size()) continue;
            List<Zombie> zombieList = this.getZombiesList().get(row);
            for (Zombie zombie : zombieList) {
                if (zombie.getX() > beginX && zombie.getX() < overX) return true;
            }
        }
        return false;
    }

    public List<Recyclable> update() {
        Production newProduction = new Production();

        mapLawnsList.forEach(list -> list.forEach(lawn -> {
            if (lawn.havePlant()) newProduction.addOtherProduction(lawn.getPlant().doActionAndUpdate());
        }));
        bulletsList.forEach(list -> list.forEach(bullet -> newProduction.addOtherProduction(bullet.doActionAndUpdate())));
        zombiesList.forEach(list -> list.forEach(Zombie::doActionAndUpdate));

        recyclableList.forEach(recyclable -> newProduction.addOtherProduction(recyclable.doActionAndUpdate()));

        List<Recyclable> result = new ArrayList<>();
        for (Recyclable recyclable : recyclableList) {
            if (recyclable.needRemove() && recyclable.successRecycle()) result.add(recyclable);
        }

        recyclableList.removeIf(Sprite::needRemove);
        mapLawnsList.forEach(list -> list.forEach(MapLawn::removePlantIfNeed));
        bulletsList.forEach(list -> list.removeIf(Bullet::needRemove));
        zombiesList.forEach(list -> list.removeIf(Zombie::needRemove));

        MapInfo mapInfo = Game.getGameMap().getMapInfo();
        recyclableList.addAll(newProduction.getRecyclableList());
        for (Bullet bullet : newProduction.getBullets()) {
            bulletsList.get(mapInfo.rowByY(bullet.getY())).add(bullet);
        }
        for (List<Bullet> bulletList : bulletsList) {
            bulletList.sort(Comparator.comparing(Bullet::getX));
        }


        bulletHit();
        return result;
    }

    /**
     * todo 时间复杂度优化
     */
    private void bulletHit() {
        int rowNum = Game.getGameMap().getMapInfo().maxRowNum();
        for (List<Bullet> bullets : bulletsList) {
            for (Bullet bullet : bullets) {
                for (List<Zombie> zombieList : zombiesList) {
                    for (Zombie zombie : zombieList) {
                        if (zombie.crash(bullet)) {
                            if (bullet.hurt(zombie)) {
                                zombie.hitByBullet(bullet);
                            }

                        }
                    }
                }
            }

        }

//        for (int row = 0; row < rowNum; row++) {
//            List<Bullet> bullets = bulletsList.get(row);
//            List<Zombie> zombieList = zombiesList.get(row);
//            for (Bullet bullet : bullets) {
//                for (Zombie zombie : zombieList) {
//                    if (zombie.crash(bullet)) {
//                        bullet.hurt(zombie);
//                    }
//                }
//            }
//        }
    }

    public void draw() {
        int rowNum = Game.getGameMap().getMapInfo().maxRowNum();
        for (int row = 0; row < rowNum; row++) {
            drawByRow(row);
        }

    }

    private void drawByRow(int i) {
        zombiesList.get(i).forEach(Draw::draw);
        recyclableList.forEach(Draw::draw);
        mapLawnsList.get(i).forEach(MapLawn::draw);
        zombiesList.get(i).forEach(Draw::draw);
        bulletsList.get(i).forEach(Draw::draw);
    }
}
