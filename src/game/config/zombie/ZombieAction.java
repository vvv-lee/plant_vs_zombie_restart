package game.config.zombie;

/**
 * 僵尸基本动作
 */
public enum ZombieAction {

    move("move"),
    attack("attack"),
    die("die");

    private String name;


    ZombieAction(String name) {
        this.name = name;

    }

    public static void check(String action) {
        boolean have = false;
        for (ZombieAction zombieAction : ZombieAction.values()) {
            if (zombieAction.name.equals(action)) {
                have = true;
                break;
            }
        }
        if (!have) throw new RuntimeException("error action:" + action);
    }

    public String getName() {
        return name;
    }

}
