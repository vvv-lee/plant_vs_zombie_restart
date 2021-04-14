package game.spirits.interfaces;

import game.config.PlantCard;
import game.main.map.GameMap;
import game.main.map.MapLawn;

public interface Plant extends Sprite, Lawn, Health {

    void attackByZombie(Zombie zombie);//被僵尸攻击

    default PlantCard plantCard() {  //获取所属植物卡片，卡片中有植物的详细设置 如阳光 生命值 冷却等
        return PlantCard.getPlantCardByName(this.getClass().getSimpleName());
    }

    void updateBehavior();//根据地图状态更新行为 如射击和停止射击

//    default void toLawn(MapLawn mapLawn) {
//        to(mapLawn.getY() + plantCard().getShowOffSetY()
//                , mapLawn.getX() + plantCard().getShowOffSetX());
//    }


}
