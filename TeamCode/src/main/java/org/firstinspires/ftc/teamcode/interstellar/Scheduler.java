package org.firstinspires.ftc.teamcode.interstellar;

import org.firstinspires.ftc.teamcode.interstellar.directives.Directive;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Scheduler {
	private static final Scheduler instance = new Scheduler();

	private Scheduler() {}

	public static Scheduler getInstance() {
		return instance;
	}

	private final List<Directive> runningDirectives = new ArrayList<>();
	private final List<Trigger> activeTriggers = new ArrayList<>();
	private final List<Subsystem> subsystems = new ArrayList<>();

	public void addSubsystem(Subsystem subsystem) {
		subsystems.add(subsystem);
	}

	public void schedule(Directive directiveToSchedule) {
		// prevent scheduling of the same directive multiple times
		if (runningDirectives.contains(directiveToSchedule)) {
			return;
		}


		boolean didInterrupt = false;

		//for every running directive
		for (Iterator<Directive> iterator = runningDirectives.iterator(); iterator.hasNext();) {
			Directive runningDirective = iterator.next();

			//check for conflicts
			CONFLICT_CHECK:
			//for every subsystem required by the new directive
			for (Subsystem requiredByNew : directiveToSchedule.getRequiredSubsystems()) {
				//for every subsystem required by the running directive
				for (Subsystem requiredByRunning : runningDirective.getRequiredSubsystems()) {
					if (requiredByNew == requiredByRunning) {
						// CONFLICT FOUND!

						if (runningDirective.isInterruptible()) {
							// If the running command is interruptible, stop it and remove it.
							runningDirective.stop(true);
							iterator.remove();
							didInterrupt = true;
						} else {
							//running command unable to be interrupted, so can't schedule new directive
							return;
						}

						//checked requirements for this runningDirective, move to next
						break CONFLICT_CHECK;
					}
				}
			}
		}

		runningDirectives.add(directiveToSchedule); //add to running directives
		directiveToSchedule.start(didInterrupt); //start directive and pass hadToInterruptToStart status
	}

	public void addTrigger(Trigger trigger) {
		activeTriggers.add(trigger);
	}

	public void run() {
		//todo: add sequences
		//todo: be able to add subsystems other than `this` to requirements
		for (Trigger trigger : activeTriggers) {
			if (trigger.check()) {
				trigger.run();
			}
		}

		for (Iterator<Directive> iterator = runningDirectives.iterator(); iterator.hasNext();) {
			Directive directive = iterator.next();
			if (directive.isFinished()) {
				directive.stop(false);
				iterator.remove();
			} else {
				directive.update();
			}
		}

		for (Subsystem subsystem : subsystems) {
			Directive defaultDirective = subsystem.getDefaultDirective();

			if (defaultDirective != null && !isSubsystemInUse(subsystem)) {
				schedule(defaultDirective);
			}
		}
	}

	private boolean isSubsystemInUse(Subsystem subsystemToCheck) {
		for (Directive runningDirective : runningDirectives) {
			if (runningDirective == subsystemToCheck.getDefaultDirective()) {
				continue;
			}

			for (Subsystem requiredSubsystem : runningDirective.getRequiredSubsystems()) {
				if (requiredSubsystem == subsystemToCheck) {
					return true;
				}
			}
		}

		return false;
	}

	public void cancelAll() {
		activeTriggers.clear();

		for (Directive directive : runningDirectives) {
			directive.stop(true);
		}

		runningDirectives.clear();
	}
}
