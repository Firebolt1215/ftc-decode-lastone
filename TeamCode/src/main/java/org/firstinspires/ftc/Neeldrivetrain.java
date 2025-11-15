package org.firstinspires.ftc.teamcode;
// all the imports:
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Neeldrivetrain { /* class */
    private final OpMode opmode; // This is creating a varieble "opmode" which contains the actual opmode we are using//

    //all of this is the variebles
    double maxspeed;
    private DcMotor frontleft = null;
    private DcMotor frontright = null;
    private DcMotor backright = null;
    private DcMotor backleft = null;

    // this is the constructor. The thing in the parenthesis is the varieble opmode//
    public Neeldrivetrain(OpMode opmode)
    {
        this.opmode = opmode;
    }
//here i am initializing the motors
    public void DCinit()
    {
        frontleft = opmode.hardwareMap.get(DcMotor.class,"front_left");
        frontleft.setDirection(DcMotor.Direction.REVERSE);
        frontleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontright = opmode.hardwareMap.get(DcMotor.class,"front_right");
        frontright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backleft = opmode.hardwareMap.get(DcMotor.class, "back_left");
        backleft.setDirection(DcMotor.Direction.REVERSE);
        backleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backright = opmode.hardwareMap.get(DcMotor.class, "back_right");
        backright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    /*this is where the the variebles are going to contain the gamepad button and
    also the equations for everthing to run*/
    public void move_robot() {
        double accel = opmode.gamepad1.left_stick_y;
        double lat = opmode.gamepad1.left_stick_x;
        double rot = opmode.gamepad1.right_stick_x;
        //rot=rotation lat=lateral//

        double frontleftpower = accel + lat + rot;
        double frontrightpower = accel - lat - rot;
        double backleftpower = accel - lat +  rot;
        double backrightpower = accel + lat - rot;
     //array is where you put all the variebles in positions ex. pos 0, pos 1
        double[] por_array = {frontleftpower,frontrightpower,backleftpower, backrightpower};
        /*
        simply this is comparing all the motor power from the array.
         */
        for(int x = 0; x<3; x++) {
            maxspeed = Math.max(Math.abs(por_array[x]), Math.abs(x + 1));
        }
        // here we are saying that if it is greater than 0.6 - the power -  then multiply the power by 0.6
        if (maxspeed >= 0.6) {
            frontleftpower *= 0.6;
            frontrightpower *= 0.6;
            backleftpower *= 0.6;
            backrightpower *= 0.6;


        }

        frontright.setPower(frontrightpower);
        frontleft.setPower(frontleftpower);
        backleft.setPower(backleftpower);
        backright.setPower(backrightpower);


    }

}

