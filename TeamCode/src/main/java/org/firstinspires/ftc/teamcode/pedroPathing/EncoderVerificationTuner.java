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

        // Reset encoders for consistent output
        lf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        lf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        telemetry.addLine("✅ Encoders reset. Ready to verify.");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addLine("Push robot forward. Check which encoder values increase.");
            telemetry.addData("LF Encoder", lf.getCurrentPosition());
            telemetry.addData("RF Encoder", rf.getCurrentPosition());
            telemetry.addData("LB Encoder", lb.getCurrentPosition());
            telemetry.addData("RB Encoder", rb.getCurrentPosition());
            telemetry.update();
        }
    }
}

