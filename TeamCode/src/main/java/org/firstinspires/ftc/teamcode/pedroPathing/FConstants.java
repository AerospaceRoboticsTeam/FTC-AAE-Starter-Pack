package org.firstinspires.ftc.teamcode.pedroPathing;

import dev.frozenmilk.pedropathing.constants.FollowerConstants;
import dev.frozenmilk.pedropathing.localization.enums.Localizers;

public class FConstants {
    static {
        // Select the localizer your robot uses.
        FollowerConstants.localizers = Localizers.MECANUM;  // or THREE_WHEEL, TWO_WHEEL, etc.

        // Example follower configuration (you can tune later):
        FollowerConstants.mass = 10.0; // Robot mass in kilograms
        FollowerConstants.trackWidth = 15.0; // Distance between wheels (in inches)
        FollowerConstants.maxVelocity = 40.0; // Max robot velocity in in/s
        FollowerConstants.maxAcceleration = 50.0; // Max acceleration in in/s²
        FollowerConstants.maxAngularVelocity = Math.toRadians(200); // In rad/s
        FollowerConstants.maxAngularAcceleration = Math.toRadians(300); // In rad/s²
    }
}
