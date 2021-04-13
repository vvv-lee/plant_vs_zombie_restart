package game.cards;

import game.main.map.GameMap;
import game.spirits.interfaces.Click;

public interface Card extends Click {


    Card select();//选择时返回卡片

    void notUse();//不用

    void update();//更新

    void showInMap( double mouseY, double mouseX);

    Card clickInMap( double mouseY, double mouseX, int btn);


}
