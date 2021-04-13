package game.spirits.protective;

import game.config.ProtectiveCard;
import game.resourceUtil.animation.data.AnimationData;
import game.resourceUtil.animation.data.DataByJson;
import game.spirits.interfaces.Protective;

import java.util.*;

public abstract class AbstractProtective implements Protective {

    private ProtectiveCard protectiveCard;

    private double hp;

    private double maxHp;

    private List<AnimationData> showDataList;

    private List<String> clashKeys;

    public AbstractProtective(ProtectiveCard protectiveCard) {
        this.protectiveCard = protectiveCard;
        this.maxHp = protectiveCard.getMaxHp();
        this.hp = maxHp;
        statusUpdate();
    }


    private void statusUpdate() {
        showDataList = protectiveCard.showDataList(hp / maxHp);
        clashKeys = protectiveCard.clashKeys(hp / maxHp);


    }

    @Override
    public List<String> clashAnimations() {
        return clashKeys;
    }

    @Override
    public List<AnimationData> showDataList() {
        return showDataList;
    }

    @Override
    public double getHp() {
        return hp;
    }

    @Override
    public double beAttack(double attack) {
        double result = 0;
        if (hp > attack) hp = hp - attack;
        else {
            result = attack - hp;
            hp = 0;
        }
        statusUpdate();
        return result;
    }

//
//    private double hp;
//
//    private double maxHp;
//
//    private String name;
//
//    private ImmutableSortedMap<String, List<String>> removeMap;//每个状态要移除的动画
//
//
//    private ImmutableSortedMap<Double, List<String>> statusAnimationMap;//每个状态要显示的动画
//
//    private Map<String, AnimationData> animationDataMap;// 动画数据
//
//    private List<String> removes;
//
//    private List<AnimationData> showAnimationData;//要显示的动画
//
//    public String getName() {
//        return name;
//    }
//
//
//    @Override
//    public List<String> removeAnimation() {
//        return removes;
//    }
//
//    @Override
//    public List<AnimationData> animationData() {
//        return showAnimationData;
//    }
//
//    @Override
//    public double beAttack(double attack) {
//        double result = 0;
//        if (hp > attack) hp = hp - attack;
//        else {
//            result = attack - hp;
//            hp = 0;
//        }
//        statusUpdate();
//        return result;
//    }
//
//    private void statusUpdate() {
//        Double keyByHp = getKeyByHp();
//        this.removes = new ArrayList<>();
//        showAnimationData = new ArrayList<>(statusAnimationMap.get(keyByHp).size());
//        for (String s : statusAnimationMap.get(keyByHp)) {
//            List<String> removeKeys = removeMap.get(s);
//            if (removeKeys != null) removes.addAll(removeKeys);
//            showAnimationData.add(animationDataMap.get(s));
//        }
//    }
//
//    public AbstractProtective(String name,
//                              double maxHp,
//                              SortedMap<Double, List<String>> statusAnimationMap,
//                              Map<String, AnimationData> animationDataMap,
//                              Map<String, List<String>> animationRemoveMap
//    ) {
////        this.statusAnimationMap = statusAnimationMap;
//        this.animationDataMap = animationDataMap;
//        this.maxHp = maxHp;
//        this.hp = maxHp;
//        this.name = name;
////        this.removeMap = animationRemoveMap;
//        statusUpdate();
//    }
//
//
//    @Override
//    public double getHp() {
//        return hp;
//    }
//
//    private Double getKeyByHp() {
//        double nowValue = hp / maxHp;
//        Double result = null;
//        for (Double minValue : statusAnimationMap.keySet()) {
//            if (nowValue < minValue) {
//                return result;
//            }
//            result = minValue;
//        }
//        return result;
//    }


}
