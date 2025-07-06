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

    private double boost = 1.0;  // Default no scaling
    private double flPower, frPower, blPower, brPower;  // Store raw motor powers

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

    public void setBoost(double boost) {
        this.boost = boost;
    }

    @Override
    public void setDrivePower(Vector2d drive, double turn) {
        double x = drive.getX();
        double y = drive.getY();

        // Raw motor powers
        flPower = y + x + turn;
        frPower = y - x - turn;
        blPower = y - x + turn;
        brPower = y + x - turn;

        double max = Math.max(1.0, Math.abs(flPower));
        max = Math.max(max, Math.abs(frPower));
        max = Math.max(max, Math.abs(blPower));
        max = Math.max(max, Math.abs(brPower));

        // Normalize and apply boost scaling
        frontLeft.setPower((flPower / max) * boost);
        frontRight.setPower((frPower / max) * boost);
        backLeft.setPower((blPower / max) * boost);
        backRight.setPower((brPower / max) * boost);
    }

    // Getters for telemetry
    public double getFrontLeftPower() { return frontLeft.getPower(); }
    public double getFrontRightPower() { return frontRight.getPower(); }
    public double getBackLeftPower() { return backLeft.getPower(); }
    public double getBackRightPower() { return backRight.getPower(); }
}
