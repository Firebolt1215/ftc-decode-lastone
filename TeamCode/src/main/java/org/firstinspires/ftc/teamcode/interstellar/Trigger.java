package org.firstinspires.ftc.teamcode.interstellar;

import org.firstinspires.ftc.teamcode.interstellar.actions.Action;
import org.firstinspires.ftc.teamcode.interstellar.conditions.Condition;

public class Trigger {
	private final Condition condition;
	private final Action action;

	public Trigger(Condition condition, Action action) {
		this.condition = condition;
		this.action = action;
	}

	public void schedule() {
		Scheduler.getInstance().addTrigger(this);
	}

	public boolean check() {
		return condition.evaluate();
	}

	public void run() {
		action.run();
	}
}