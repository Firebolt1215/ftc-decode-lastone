package org.firstinspires.ftc.teamcode.core;

import org.firstinspires.ftc.teamcode.interstellar.InterstellarBot;
import org.firstinspires.ftc.teamcode.subsystems.*;

public class TarsBase extends InterstellarBot {
	public TarsBase() {
		super(
				new Drivebase(1.00, 1.00),
				new Intake(),
				new LeverTransfer(0.28, 0.00),
				new Spindexer()
		);
	}
}