package org.firstinspires.ftc.teamcode.interstellar.directives;

import org.firstinspires.ftc.teamcode.interstellar.Subsystem;

public class DefaultDirective extends Directive {
    public DefaultDirective(Subsystem subsystem) {
        setRequires(subsystem);
        setInterruptible(true);
    }

    @Override
    public void start(boolean hadToInterruptToStart) {}

    @Override
    public void update() {}

    @Override
    public void stop(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return false;
    }
}
