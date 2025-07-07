package org.firstinspires.ftc.teamcode.pedroPathing;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import dev.frozenmilk.pedropathing.localization.pose.PoseUpdater;
import dev.frozenmilk.pedropathing.geometry.Vector2d;
import dev.frozenmilk.pedropathing.drive.Drive;

@TeleOp(name = "🔧 Pedro: Drive Direction Tuner")
public class DriveDirectionTuner extends LinearOpMode {

    @Override
    public void runOpMode() {
        PoseUpdater poseUpdater = new PoseUpdater(hardwareMap);
        Drive drive = new Drive(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {
            poseUpdater.updatePose();

            double y = -gamepad1.left_stick_y;
            double x = -gamepad1.left_stick_x;
            double rot = -gamepad1.right_stick_x;

            Vector2d move = new Vector2d(x, y);
            drive.setDrivePower(move, rot);

            telemetry.addLine("Control robot with joysticks.");
            telemetry.update();
        }
    }
}
