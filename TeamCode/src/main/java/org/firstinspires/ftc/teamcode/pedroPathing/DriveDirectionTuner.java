package org.firstinspires.ftc.teamcode.pedroPathing;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import dev.frozenmilk.pedropathing.localization.pose.PoseUpdater;
import dev.frozenmilk.pedropathing.geometry.Vector2d;
import org.firstinspires.ftc.teamcode.Libs.MecanumDrive_5518_PP;

@TeleOp(name = "🔧 Pedro: Drive Direction Tuner")
public class DriveDirectionTuner extends LinearOpMode {

    @Override
    public void runOpMode() {
        PoseUpdater poseUpdater = new PoseUpdater(hardwareMap);
        MecanumDrive_5518_PP drive = new MecanumDrive_5518_PP(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {
            poseUpdater.updatePose();
            drive.updateIMU();

            double y = -gamepad1.left_stick_y;
            double x = -gamepad1.left_stick_x;
            double rot = -gamepad1.right_stick_x;

            Vector2d move = new Vector2d(x, y);
            drive.setDrivePower(move, rot);

            telemetry.addLine("Control robot with joysticks.");
            telemetry.addData("Pose X (in)", poseUpdater.getPose().x);
            telemetry.addData("Pose Y (in)", poseUpdater.getPose().y);
            telemetry.addData("Heading (deg)", Math.toDegrees(drive.getHeading()));

            telemetry.addData("Motor Powers", 
                String.format("FL: %.2f FR: %.2f BL: %.2f BR: %.2f",
                    drive.getFrontLeftPower(), drive.getFrontRightPower(),
                    drive.getBackLeftPower(), drive.getBackRightPower()));

            telemetry.update();
        }
    }
}
