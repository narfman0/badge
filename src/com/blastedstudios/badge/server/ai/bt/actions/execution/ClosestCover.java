// ******************************************************* 
//                   MACHINE GENERATED CODE                
//                MUST BE CAREFULLY COMPLETED              
//                                                         
//           ABSTRACT METHODS MUST BE IMPLEMENTED          
//                                                         
// Generated on 10/28/2012 10:57:56
// ******************************************************* 
package com.blastedstudios.badge.server.ai.bt.actions.execution;

import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.blastedstudios.badge.server.ai.AIThread;
import com.blastedstudios.badge.server.ai.ArtificialBeing;
import com.blastedstudios.badge.world.Being;

/** ExecutionAction class created from MMPM action ClosestCover. */
public class ClosestCover extends
		jbt.execution.task.leaf.action.ExecutionAction {

	/**
	 * Constructor. Constructs an instance of ClosestCover that is able to run a
	 * com.blastedstudios.badge.server.ai.bt.actions.ClosestCover.
	 */
	public ClosestCover(
			com.blastedstudios.badge.server.ai.bt.actions.ClosestCover modelTask,
			jbt.execution.core.BTExecutor executor,
			jbt.execution.core.ExecutionTask parent) {
		super(modelTask, executor, parent);

	}

	protected void internalSpawn() {
		/*
		 * Do not remove this first line unless you know what it does and you
		 * need not do it.
		 */
		this.getExecutor().requestInsertionIntoList(
				jbt.execution.core.BTExecutor.BTExecutorList.TICKABLE, this);
		System.out.println(this.getClass().getCanonicalName() + " spawned");
	}

	protected jbt.execution.core.ExecutionTask.Status internalTick() {
		Being self = (Being) getContext().getVariable(ArtificialBeing.SELF);
		AIThread aiThread = (AIThread) getContext().getVariable(ArtificialBeing.AI_THREAD);
		List<Vector2> bases = aiThread.getClosestBases(self.getPosition(), self.getFactionType()); 
		if(!bases.isEmpty()){
			Vector2 closest = bases.get(0);
			float[] target = new float[]{closest.x, closest.y};
			getContext().setVariable("ClosestFriendlyBaseTarget", target);
			return Status.SUCCESS;
		}
		return Status.FAILURE;
	}

	protected void internalTerminate() {}
	
	protected void restoreState(jbt.execution.core.ITaskState state) {}

	protected jbt.execution.core.ITaskState storeState() {
		return null;
	}

	protected jbt.execution.core.ITaskState storeTerminationState() {
		return null;
	}
}