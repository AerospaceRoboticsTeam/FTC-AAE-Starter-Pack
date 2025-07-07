package org.firstinspires.ftc.teamcode.pedroPathing;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import dev.frozenmilk.pedropathing.localization.pose.PoseUpdater;
import dev.frozenmilk.pedropathing.localization.pose.Pose;

@TeleOp(name = "🔧 Pedro: Localization Tuner")
public class LocalizationTuner extends LinearOpMode {

    @Override
    public void runOpMode() {
        PoseUpdater poseUpdater = new PoseUpdater(hardwareMap);
        waitForStart();

        while (opModeIsActive()) {
            poseUpdater.updatePose();
            Pose pose = poseUpdater.getPose();

            telemetry.addData("X (in)", pose.x);
            telemetry.addData("Y (in)", pose.y);
            telemetry.addData("Heading (deg)", Math.toDegrees(pose.heading));
            telemetry.update();
        }
    }
}
