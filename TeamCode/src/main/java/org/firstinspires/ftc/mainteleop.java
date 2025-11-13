package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

@TeleOp(name="ftc2025code",group="Iterative Mode")

public class mainteleop extends OpMode
{
    Neeldrivetrain drive_train;
    public void init()
    {
        drive_train = new Neeldrivetrain(this);
        drive_train.DCinit();
    }
    public void loop()
    {
        drive_train.move_robot();

    }
}




