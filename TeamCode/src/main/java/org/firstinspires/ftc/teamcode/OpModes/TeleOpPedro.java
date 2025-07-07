package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Libs.MecanumDrive_5518_PP;
import org.firstinspires.ftc.teamcode.Libs.PedroWrapper;

import dev.frozenmilk.pedropathing.geometry.Vector2d;

@TeleOp(name = "Field-Centric Pedro TeleOp")
public class TeleOpPedro extends LinearOpMode {

    @Override
    public void runOpMode() {
        MecanumDrive_5518_PP drive = new MecanumDrive_5518_PP(hardwareMap);
        PedroWrapper pedro = new PedroWrapper(drive);

        // Optional: Reset encoders & run using encoders if relevant here (teleop often uses RUN_WITHOUT_ENCODER)
        // drive.resetEncoders();
        // drive.runUsingEncoders();

        waitForStart();

        while (opModeIsActive()) {
            // Gamepad inputs - standard left stick for translation, right stick for rotation
            double rawY = -gamepad1.left_stick_y;
            double rawX = -gamepad1.left_stick_x;
            double turn = -gamepad1.right_stick_x;

            // Boost control (in the past: left trigger for full speed, else half speed)
            double boost = gamepad1.left_trigger > 0.1 ? 1.0 : 0.5;
            drive.setBoost(boost);

            // Update IMU heading
            drive.updateIMU();
            double heading = drive.getHeading();

            // Field-centric coordinate transformation
            double cos = Math.cos(-heading);
            double sin = Math.sin(-heading);
            double fieldX = rawX * cos - rawY * sin;
            double fieldY = rawX * sin + rawY * cos;

            Vector2d move = new Vector2d(fieldX, fieldY);

            // Apply drive power with boost
            drive.setDrivePower(move, turn);

            // Update PedroPathing control loop
            pedro.update();

            // Telemetry for debug and driver feedback
            telemetry.addData("Heading (deg)", Math.toDegrees(heading));
            telemetry.addData("Boost", boost);
            telemetry.addData("Drive Power", 
                String.format("FL: %.2f FR: %.2f BL: %.2f BR: %.2f",
                    drive.getFrontLeftPower(), drive.getFrontRightPower(),
                    drive.getBackLeftPower(), drive.getBackRightPower()));
            telemetry.update();
        }
    }
}
