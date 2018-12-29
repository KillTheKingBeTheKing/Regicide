package com.osrs.game.content;

import com.osrs.game.World;
import com.osrs.game.entity.impl.object.GameObject;
import com.osrs.game.entity.impl.player.Player;
import com.osrs.game.model.Position;
import com.osrs.game.model.areas.impl.WildernessArea;
import com.osrs.game.task.Task;
import com.osrs.game.task.TaskManager;
import com.osrs.util.Misc;

/**
 * Handles the obelisks which teleport players around in the Wilderness. (from
 * Ruse)
 *
 * @author Swiffy
 */
public class Obelisks {

    /*
     * Obelisk ids
     */
    public static final int[] OBELISK_IDS = {14829, 14830, 14827, 14828, 14826, 14831};

    /*
     * The obelisks
     */
    public static final GameObject[] obelisks = new GameObject[4];

    /*
     * Are the obelisks activated?
     */
    private static final boolean[] OBELISK_ACTIVATED = new boolean[OBELISK_IDS.length];

    /*
     * Obelisk coords
     */
    private static final int[][] OBELISK_COORDS = {{3154, 3618}, {3225, 3665}, {3033, 3730}, {3104, 3792},
            {2978, 3864}, {3305, 3914}};

    /**
     * Activates the Wilderness obelisks.
     *
     * @param objectId The object id
     * @return true if the object is an obelisk
     */
    public static boolean activate(int objectId) {
        final int index = getObeliskIndex(objectId);
        if (index >= 0) {
            if (!OBELISK_ACTIVATED[index]) {
                OBELISK_ACTIVATED[index] = true;

                @SuppressWarnings("unused")
				int obeliskX, obeliskY;
                for (int i = 0; i < obelisks.length; i++) {
                    obeliskX = i == 1 || i == 3 ? OBELISK_COORDS[index][0] + 4 : OBELISK_COORDS[index][0];
                    obeliskY = i >= 2 ? OBELISK_COORDS[index][1] + 4 : OBELISK_COORDS[index][1];
                }
                TaskManager.submit(new Task(4, false) {
                    @Override
                    public void execute() {
                        final Position obeliskLocation = new Position(OBELISK_COORDS[index][0] + 2,
                                OBELISK_COORDS[index][1] + 2);
                        int random = Misc.getRandom(5);
                        while (random == index)
                            random = Misc.getRandom(5);
                        final Position newLocation = new Position(OBELISK_COORDS[random][0] + 2,
                                OBELISK_COORDS[random][1] + 2);
                        for (Player player : World.getPlayers()) {
                            if (player == null || !(player.getArea() instanceof WildernessArea))
                                continue;
                            if (player.getPosition().isWithinDistance(obeliskLocation, 1)) {
                                player.moveTo(newLocation);
                            }
                        }

                        stop();
                    }

                    @Override
                    public void stop() {
                        setEventRunning(false);
                        OBELISK_ACTIVATED[index] = false;
                    }
                });
            }
            return true;
        }
        return false;
    }

    /*
     * Gets the array index for an obelisk
     */
    public static int getObeliskIndex(int id) {
        for (int j = 0; j < OBELISK_IDS.length; j++) {
            if (OBELISK_IDS[j] == id)
                return j;
        }
        return -1;
    }
}
