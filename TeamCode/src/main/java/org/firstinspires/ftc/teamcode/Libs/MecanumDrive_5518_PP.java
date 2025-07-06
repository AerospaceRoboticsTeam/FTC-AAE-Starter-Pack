package org.firstinspires.ftc.teamcode.Libs;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

import dev.frozenmilk.pedropathing.drive.HolonomicDrive;
import dev.frozenmilk.pedropathing.geometry.Vector2d;

public class MecanumDrive_5518_PP extends HolonomicDrive {

    private final DcMotorEx frontLeft, frontRight, backLeft, backRight;
    private final IMU imu;
    private double heading;

    private double boost = 1.0;

    // Store last powers for telemetry
    private double frontLeftPower = 0.0;
    private double frontRightPower = 0.0;
    private double backLeftPower = 0.0;
    private double backRightPower = 0.0;

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

    public void setBoost(double boostValue) {
        boost = Range.clip(boostValue, 0.0, 1.0);
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

        // Normalize by max absolute value, not sum
        double maxPower = Math.max(
            Math.max(Math.abs(fl), Math.abs(fr)),
            Math.max(Math.abs(bl), Math.abs(br))
        );
        if (maxPower < 1.0) maxPower = 1.0;

        // Apply boost multiplier
        fl = (fl / maxPower) * boost;
        fr = (fr / maxPower) * boost;
        bl = (bl / maxPower) * boost;
        br = (br / maxPower) * boost;

        // Set motor powers
        frontLeft.setPower(fl);
        frontRight.setPower(fr);
        backLeft.setPower(bl);
        backRight.setPower(br);

        // Save for telemetry
        frontLeftPower = fl;
        frontRightPower = fr;
        backLeftPower = bl;
        backRightPower = br;
    }

    // Getter methods for telemetry
    public double getFrontLeftPower() {
        return frontLeftPower;
    }

    public double getFrontRightPower() {
        return frontRightPower;
    }

    public double getBackLeftPower() {
        return backLeftPower;
    }

    public double getBackRightPower() {
        return backRightPower;
    }
}

