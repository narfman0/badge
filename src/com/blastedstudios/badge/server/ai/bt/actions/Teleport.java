// ******************************************************* 
//                   MACHINE GENERATED CODE                
//                       DO NOT MODIFY                     
//                                                         
// Generated on 11/04/2012 10:41:54
// ******************************************************* 
package com.blastedstudios.badge.server.ai.bt.actions;

/** ModelAction class created from MMPM action Teleport. */
public class Teleport extends jbt.model.task.leaf.action.ModelAction {
	/**
	 * Value of the parameter "target" in case its value is specified at
	 * construction time. null otherwise.
	 */
	private float[] target;
	/**
	 * Location, in the context, of the parameter "target" in case its value is
	 * not specified at construction time. null otherwise.
	 */
	private java.lang.String targetLoc;

	/**
	 * Constructor. Constructs an instance of Teleport.
	 * 
	 * @param target
	 *            value of the parameter "target", or null in case it should be
	 *            read from the context. If null, <code>targetLoc</code> cannot
	 *            be null.
	 * @param targetLoc
	 *            in case <code>target</code> is null, this variable represents
	 *            the place in the context where the parameter's value will be
	 *            retrieved from.
	 */
	public Teleport(jbt.model.core.ModelTask guard, float[] target,
			java.lang.String targetLoc) {
		super(guard);
		this.target = target;
		this.targetLoc = targetLoc;
	}

	/**
	 * Returns a
	 * com.blastedstudios.badge.server.ai.bt.actions.execution.Teleport task
	 * that is able to run this task.
	 */
	public jbt.execution.core.ExecutionTask createExecutor(
			jbt.execution.core.BTExecutor executor,
			jbt.execution.core.ExecutionTask parent) {
		return new com.blastedstudios.badge.server.ai.bt.actions.execution.Teleport(
				this, executor, parent, this.target, this.targetLoc);
	}
}