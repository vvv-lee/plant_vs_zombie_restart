package game.config.animation;

import java.util.Map;
import java.util.Set;

public class AnimationConfig {

    private Set<String> needActions;

    private Set<String> fragments;

    private Map<String, String> replace;

    public Set<String> getNeedActions() {
        return needActions;
    }

    public Set<String> getFragments() {
        return fragments;
    }

    public Map<String, String> getReplace() {
        return replace;
    }

}
