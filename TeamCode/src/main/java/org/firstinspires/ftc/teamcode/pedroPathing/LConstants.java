package org.firstinspires.ftc.teamcode.pedroPathing;

import dev.frozenmilk.pedropathing.constants.localizers.MecanumConstants;
// If using three-wheel localizer:
// import dev.frozenmilk.pedropathing.constants.localizers.ThreeWheelConstants;
// and modify accordingly.

import dev.frozenmilk.pedropathing.localization.enums.EncoderDirection;

public class LConstants {
    static {
        // ===== For Mecanum localizer =====
        MecanumConstants.forwardTicksToInches = 0.001986;  // You’ll tune this later
        MecanumConstants.strafeTicksToInches = 0.001986;

        // Encoder directions
        MecanumConstants.frontLeftEncoderDirection = EncoderDirection.REVERSE;
        MecanumConstants.frontRightEncoderDirection = EncoderDirection.FORWARD;
        MecanumConstants.backLeftEncoderDirection = EncoderDirection.REVERSE;
        MecanumConstants.backRightEncoderDirection = EncoderDirection.FORWARD;

        // Explicit encoder names matching motor hardwareMap names
        MecanumConstants.frontLeftEncoderName = "left_front_mtr";
        MecanumConstants.frontRightEncoderName = "right_front_mtr";
        MecanumConstants.backLeftEncoderName = "left_back_mtr";
        MecanumConstants.backRightEncoderName = "right_back_mtr";
    }
}
