package org.firstinspires.ftc.teamcode.pedroPathing;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "🔧 Pedro: Encoder Verification Tuner")
public class EncoderVerificationTuner extends LinearOpMode {

    @Override
    public void runOpMode() {
        DcMotor lf = hardwareMap.get(DcMotor.class, "left_front_mtr");
        DcMotor rf = hardwareMap.get(DcMotor.class, "right_front_mtr");
        DcMotor lb = hardwareMap.get(DcMotor.class, "left_back_mtr");
        DcMotor rb = hardwareMap.get(DcMotor.class, "right_back_mtr");

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("LF Encoder", lf.getCurrentPosition());
            telemetry.addData("RF Encoder", rf.getCurrentPosition());
            telemetry.addData("LB Encoder", lb.getCurrentPosition());
            telemetry.addData("RB Encoder", rb.getCurrentPosition());
            telemetry.update();
        }
    }
}
