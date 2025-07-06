package org.firstinspires.ftc.teamcode.Libs;

import dev.frozenmilk.pedropathing.PedroPathing;
import dev.frozenmilk.pedropathing.geometry.Vector2d;

public class PedroWrapper {
    private final PedroPathing pathing;
    private final MecanumDrive_5518_PP drive;

    public PedroWrapper(MecanumDrive_5518_PP drive) {
        this.drive = drive;
        this.pathing = new PedroPathing(drive);
    }

    public void update() {
        drive.updateIMU();
        pathing.update();
    }

    public void setTargetPose(Vector2d pose) {
        pathing.setTargetPosition(pose);
    }

    public PedroPathing getPathing() {
        return pathing;
    }
}
