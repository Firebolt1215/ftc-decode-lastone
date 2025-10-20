package org.firstinspires.ftc.teamcode.interstellar;

import org.firstinspires.ftc.teamcode.interstellar.actions.Action;

import java.util.function.BooleanSupplier;

public class EventListener {
	private final BooleanSupplier condition;
	private final Action action;

	public EventListener(BooleanSupplier condition, Action action) {
		this.condition = condition;
		this.action = action;
	}

	public void update() {
		if (condition.getAsBoolean()) {
			action.run();
		}
	}
}
