package org.firstinspires.ftc.teamcode.directives;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.interstellar.Trigger;
import org.firstinspires.ftc.teamcode.interstellar.conditions.GamepadButton;
import org.firstinspires.ftc.teamcode.interstellar.conditions.StatefulCondition;
import org.firstinspires.ftc.teamcode.interstellar.directives.Directive;
import org.firstinspires.ftc.teamcode.subsystems.LeverTransfer;

public class DefaultLeverTransfer extends Directive {
	private final LeverTransfer leverTransfer;
	private final Gamepad gamepad;
	public DefaultLeverTransfer(LeverTransfer leverTransfer, Gamepad gamepad) {
		this.leverTransfer = leverTransfer;
		this.gamepad = gamepad;

		setInterruptible(true);
		setRequires(leverTransfer);

		new Trigger(
				new StatefulCondition(
						new GamepadButton(gamepad, GamepadButton.Button.DPAD_UP),
						StatefulCondition.Edge.RISING
				),
				() -> {leverTransfer.setLeverPositionIsUp(true);}
		).schedule();

		new Trigger(
				new StatefulCondition(
						new GamepadButton(gamepad, GamepadButton.Button.DPAD_DOWN),
						StatefulCondition.Edge.RISING
				),
				() -> {leverTransfer.setLeverPositionIsUp(false);}
		).schedule();

		new Trigger(
				new StatefulCondition(
						new GamepadButton(gamepad, GamepadButton.Button.DPAD_LEFT),
						StatefulCondition.Edge.RISING
				),
				leverTransfer::toggleLeverPosition
		).schedule();
	}

	@Override
	public void start(boolean interrupted) {

	}

	@Override
	public void update() {
		leverTransfer.updateServoPosition();
	}

	@Override
	public void stop(boolean interrupted) {

	}

	@Override
	public boolean isFinished() {
		return false;
	}
}
