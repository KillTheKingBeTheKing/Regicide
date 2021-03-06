package com.osrs.game.entity.impl.npc;

import java.util.ArrayList;
import java.util.List;

import com.osrs.game.World;
import com.osrs.game.content.combat.CombatFactory;
import com.osrs.game.content.combat.CombatType;
import com.osrs.game.content.combat.hit.PendingHit;
import com.osrs.game.content.combat.method.CombatMethod;
import com.osrs.game.definition.NpcDefinition;
import com.osrs.game.entity.impl.Character;
import com.osrs.game.entity.impl.npc.NPCMovementCoordinator.CoordinateState;
import com.osrs.game.entity.impl.player.Player;
import com.osrs.game.model.FacingDirection;
import com.osrs.game.model.NodeType;
import com.osrs.game.model.Position;
import com.osrs.game.model.areas.AreaManager;
import com.osrs.game.task.TaskManager;
import com.osrs.game.task.impl.NPCDeathTask;


/**
 * Represents a non-playable character, which players can interact with.
 *
 * @author Professor Oak
 */

public class NPC extends Character {

	/**
	 * The npc's id.
	 */
	private final int id;
	/**
	 * The npc's movement coordinator. Handles random walking.
	 */
	private NPCMovementCoordinator movementCoordinator = new NPCMovementCoordinator(this);
	/**
	 * The npc's current hitpoints.
	 */
	private int hitpoints;
	/**
	 * The npc's spawn position (default).
	 */
	private Position spawnPosition;
	/**
	 * The npc's head icon.
	 */
	private int headIcon = -1;
	/**
	 * The npc's current state. Is it dying?
	 */
	private boolean isDying;
	/**
	 * The npc's owner.
	 * <p>
	 * The only player who can see right-click actions on the npc.
	 */
	private Player owner;
	/**
	 * The npc's current state. Is it visible?
	 */
	private boolean visible = true;
	/**
	 * The npc's facing.
	 */
	private FacingDirection face = FacingDirection.NORTH;
	/**
	 * The npc's combat method, used for attacking.
	 */
	private CombatMethod combatMethod;
	/**
	 * Is this {@link NPC} a pet?
	 */
	private boolean pet;
	
	/**
	 * Creates a new {@link NPC}.
	 * @param id
	 * @param position
	 * @return
	 */
	public static NPC create(int id, Position position) {
		return new NPC(id, position);
	}

	/**
	 * Constructs a new npc.
	 *
	 * @param id
	 *            The npc id.
	 * @param position
	 *            The npc spawn (default) position.
	 */
	public NPC(int id, Position position) {
		super(NodeType.NPC, position);
		this.id = id;
		this.spawnPosition = position;

		if (getDefinition() == null) {
			setHitpoints(10);
		} else {
			setHitpoints(getDefinition().getHitpoints());
		}

		CombatFactory.assignCombatMethod(this);
	}

	@Override
	public void onAdd() {
	}

	@Override
	public void onRemove() {
	}

	/**
	 * Processes this npc.
	 */
	public void process() {
		// Only process the npc if they have properly been added
		// to the game with a definition.
		if (getDefinition() != null) {
			// Timers
			getTimers().process();

			// Handles random walk and retreating from fights
			getMovementQueue().process();
			movementCoordinator.process();

			// Handle combat
			getCombat().process();

			// Process areas..
			AreaManager.process(this);

			//Process instanced region..
			if(getInstancedRegion().isPresent()) {
				getInstancedRegion().get().sequence(this);
			}
			
			// Regenerating health if needed, but only after 20 seconds of last attack.
			if (getCombat().getLastAttack().elapsed(20000)
					|| movementCoordinator.getCoordinateState() == CoordinateState.RETREATING) {

				// We've been damaged.
				// Regenerate health.
				if (getDefinition().getHitpoints() > hitpoints) {
					setHitpoints(hitpoints + (int) (getDefinition().getHitpoints() * 0.1));
					if (hitpoints > getDefinition().getHitpoints()) {
						setHitpoints(getDefinition().getHitpoints());
					}
				}
			}
		}		
	}
	
	public List<Player> getNearbyPlayers(int distance) {
		List<Player> list = new ArrayList<>();
		for (Player player : World.getPlayers()) {
			if (player == null) {
				continue;
			}
			if (player.getPosition().getDistance(this.getPosition()) <= distance) {
				list.add(player);
			}
		}
		return list;
	}

	@Override
	public void appendDeath() {
		if (!isDying) {	        
			TaskManager.submit(new NPCDeathTask(this));
			isDying = true;
		}
	}

	@Override
	public int getHitpoints() {
		return hitpoints;
	}

	@Override
	public NPC setHitpoints(int hitpoints) {
		this.hitpoints = hitpoints;
		if (this.hitpoints <= 0)
			appendDeath();
		return this;
	}

	@Override
	public void heal(int heal) {
		if ((this.hitpoints + heal) > getDefinition().getHitpoints()) {
			setHitpoints(getDefinition().getHitpoints());
			return;
		}
		setHitpoints(this.hitpoints + heal);
	}

	@Override
	public boolean isNpc() {
		return true;
	}

	@Override
	public boolean equals(Object other) {
		return other instanceof NPC && ((NPC) other).getIndex() == getIndex() && ((NPC) other).getId() == getId();
	}

	@Override
	public int getSize() {
		return getDefinition() == null ? 1 : getDefinition().getSize();
	}

	@Override
	public int getBaseAttack(CombatType type) {

		if (type == CombatType.RANGED) {
			return getDefinition().getStats()[3];
		} else if (type == CombatType.MAGIC) {
			return getDefinition().getStats()[4];
		}

		return getDefinition().getStats()[1];
		// 0 = attack
		// 1 = strength
		// 2 = defence
		// 3 = range
		// 4 = magic
	}

	@Override
	public int getBaseDefence(CombatType type) {
		int base = 0;
		switch (type) {
		case MAGIC:
			base = getDefinition().getStats()[13];
			break;
		case MELEE:
			base = getDefinition().getStats()[10];
			break;
		case RANGED:
			base = getDefinition().getStats()[14];
			break;
		}
		// 10,11,12 = melee
		// 13 = magic
		// 14 = range
		return base;
	}

	@Override
	public int getBaseAttackSpeed() {
		return getDefinition().getAttackSpeed();
	}

	@Override
	public int getAttackAnim() {
		return getDefinition().getAttackAnim();
	}

	@Override
	public int getBlockAnim() {
		return getDefinition().getDefenceAnim();
	}

	/*
	 * Getters and setters
	 */

	public int getId() {
		if (getNpcTransformationId() != -1) {
			return getNpcTransformationId();
		}
		return id;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public boolean isDying() {
		return isDying;
	}

	public void setDying(boolean isDying) {
		this.isDying = isDying;
	}

	public Player getOwner() {
		return owner;
	}

	public NPC setOwner(Player owner) {
		this.owner = owner;
		return this;
	}

	public NPCMovementCoordinator getMovementCoordinator() {
		return movementCoordinator;
	}

	public NpcDefinition getDefinition() {
		return NpcDefinition.forId(id);
	}

	public Position getSpawnPosition() {
		return spawnPosition;
	}

	public int getHeadIcon() {
		return headIcon;
	}

	public void setHeadIcon(int headIcon) {
		this.headIcon = headIcon;
		// getUpdateFlag().flag(Flag.NPC_APPEARANCE);
	}

	public CombatMethod getCombatMethod() {
		return combatMethod;
	}

	public void setCombatMethod(CombatMethod combatMethod) {
		this.combatMethod = combatMethod;
	}

	@Override
	public NPC clone() {
		return create(getId(), getSpawnPosition());
	}

	public FacingDirection getFace() {
		return face;
	}

	public void setFace(FacingDirection face) {
		this.face = face;
	} // i'm thinking client side

	public boolean isPet() {
		return pet;
	}

	public void setPet(boolean pet) {
		this.pet = pet;
	}

	@Override
	public PendingHit manipulateHit(PendingHit hit) {
		return hit;
	}

    public boolean ignoreCombatRestriction() {
        return getDefinition().ignoreCombatRestriction();
    }
    public boolean ignoreAgressionRadius() {
        return getDefinition().ignoreAgressionRadius();
    }
}
