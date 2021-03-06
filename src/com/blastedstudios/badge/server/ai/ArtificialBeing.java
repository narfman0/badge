package com.blastedstudios.badge.server.ai;

import java.util.List;

import jbt.execution.core.BTExecutorFactory;
import jbt.execution.core.ContextFactory;
import jbt.execution.core.IBTExecutor;
import jbt.execution.core.IBTLibrary;
import jbt.execution.core.IContext;

import com.badlogic.gdx.physics.box2d.World;
import com.blastedstudios.badge.network.Generated.Gun;
import com.blastedstudios.badge.network.Generated.NetAccount;
import com.blastedstudios.badge.network.Generated.NetBeing.BeingType;
import com.blastedstudios.badge.network.Generated.FactionType;
import com.blastedstudios.badge.server.Server;
import com.blastedstudios.badge.server.ai.bt.BadgeBotBTLibrary;
import com.blastedstudios.badge.world.Being;

public class ArtificialBeing extends Being {
	public final static String AI_WORLD = "AIWorld", AI_THREAD = "AIThread", 
			OBJECTIVE = "Objective", SELF = "Self", WORLD = "World";
	private static IBTLibrary btLibrary = new BadgeBotBTLibrary();
	private IContext context;
	private IBTExecutor btExecutor;
	
	public ArtificialBeing(Server server, AIWorld aiWorld, String name, NetAccount account,
			BeingType type, float x, float y, float maxHP, float hp, List<Gun> guns,
			int currentGun, FactionType factionType, int cash, int level, int xp) {
		super(server.world.getWorld(), name, account, type, x, y, maxHP, hp, guns, currentGun, factionType, cash, level, xp);
		context = ContextFactory.createContext(btLibrary);
		context.setVariable(AI_WORLD, aiWorld);
		context.setVariable(SELF, this);
		context.setVariable(WORLD, server.world);
		context.setVariable(AI_THREAD, server.aiThread);
		btExecutor = BTExecutorFactory.createBTExecutor(btLibrary.getBT("Root"), context);
	}
	
	@Override public void render(World world){
		super.render(world);
		btExecutor.tick();
	}

	public Objective getObjective() {
		return (Objective) context.getVariable(OBJECTIVE);
	}

	public void setObjective(Objective objective) {
		context.setVariable(OBJECTIVE, objective);
	}
}
