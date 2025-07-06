package org.firstinspires.ftc.teamcode.Libs;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

import dev.frozenmilk.pedropathing.drive.HolonomicDrive;
import dev.frozenmilk.pedropathing.geometry.Vector2d;

public class MecanumDrive_5518_PP extends HolonomicDrive {

    private final DcMotorEx frontLeft, frontRight, backLeft, backRight;
    private final IMU imu;
    private double heading;

    public MecanumDrive_5518_PP(HardwareMap hwMap) {
        frontLeft  = hwMap.get(DcMotorEx.class, "left_front_mtr");
        frontRight = hwMap.get(DcMotorEx.class, "right_front_mtr");
        backLeft   = hwMap.get(DcMotorEx.class, "left_back_mtr");
        backRight  = hwMap.get(DcMotorEx.class, "right_back_mtr");

        imu = hwMap.get(IMU.class, "imu");
        IMU.Parameters parameters = new IMU.Parameters(
            new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.LEFT,
                RevHubOrientationOnRobot.UsbFacingDirection.UP
            )
        );
        imu.initialize(parameters);
        imu.resetYaw();

        frontLeft.setDirection(DcMotorEx.Direction.REVERSE);
        backLeft.setDirection(DcMotorEx.Direction.REVERSE);

        for (DcMotorEx motor : new DcMotorEx[]{frontLeft, frontRight, backLeft, backRight}) {
            motor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        }
    }

    public void updateIMU() {
        heading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
    }

    public double getHeading() {
        return heading;
    }

    @Override
    public void setDrivePower(Vector2d drive, double turn) {
        double x = drive.getX();
        double y = drive.getY();

        double fl = y + x + turn;
        double fr = y - x - turn;
        double bl = y - x + turn;
        double br = y + x - turn;

        double max = Math.max(1.0, Math.abs(fl) + Math.abs(fr) + Math.abs(bl) + Math.abs(br));
        frontLeft.setPower(fl / max);
        frontRight.setPower(fr / max);
        backLeft.setPower(bl / max);
        backRight.setPower(br / max);
    }
}
