package game.main.mission;

import game.spirits.interfaces.Zombie;
import game.spirits.recyclable.Sun;

import java.util.List;

public interface Mission {

    List<List<Zombie>> newZombie();

    List<Sun> newSuns();


}
