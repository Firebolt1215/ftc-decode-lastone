package org.firstinspires.ftc.teamcode.interstellar;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.interstellar.directives.Directive;

public abstract class Subsystem {
	private Directive defaultDirective;
	public abstract void init(HardwareMap hardwareMap);
	public abstract void setGamepads(Gamepad gamepad1, Gamepad gamepad2);
	public abstract void update();

	public abstract String getTelemetryData();

	public void setDefaultDirective(Directive directive) {
		this.defaultDirective = directive;
	}

	public Directive getDefaultDirective() {
		return defaultDirective;
	}
}