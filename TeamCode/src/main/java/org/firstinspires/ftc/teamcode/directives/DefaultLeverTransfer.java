package org.firstinspires.ftc.teamcode.directives;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.interstellar.Trigger;
import org.firstinspires.ftc.teamcode.interstellar.conditions.GamepadButton;
import org.firstinspires.ftc.teamcode.interstellar.conditions.StatefulCondition;
import org.firstinspires.ftc.teamcode.interstellar.directives.DefaultDirective;
import org.firstinspires.ftc.teamcode.subsystems.LeverTransfer;

public class DefaultLeverTransfer extends DefaultDirective {
	public DefaultLeverTransfer(LeverTransfer leverTransfer, Gamepad gamepad) {
        super(leverTransfer);

		setInterruptible(true);
		setRequires(leverTransfer);

		new Trigger(
				new StatefulCondition(
						new GamepadButton(gamepad, GamepadButton.Button.DPAD_UP),
						StatefulCondition.Edge.RISING //On initial press
				),
				() -> {leverTransfer.setLeverPositionIsUp(true);}
		).schedule();

		new Trigger(
				new StatefulCondition(
						new GamepadButton(gamepad, GamepadButton.Button.DPAD_DOWN),
						StatefulCondition.Edge.RISING //On initial press
				),
				() -> {leverTransfer.setLeverPositionIsUp(false);}
		).schedule();

		new Trigger(
				new StatefulCondition(
						new GamepadButton(gamepad, GamepadButton.Button.DPAD_LEFT),
						StatefulCondition.Edge.RISING //On initial press
				),
				leverTransfer::toggleLeverPosition
		).schedule();
	}
}
