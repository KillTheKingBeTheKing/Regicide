package com.osrs.game.model;

import com.osrs.game.entity.Entity;

/**
 * Represents an action
 * which can be executed.
 *
 * @author Gabbe
 */
public interface Action {
    public void execute();
    public void execute(Entity entity);
}
