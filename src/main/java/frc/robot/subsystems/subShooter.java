// FRC 5730 - The Professionals
// 2020 Season

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.cmdManShoot;
import edu.wpi.first.wpilibj.Spark;

public class subShooter extends Subsystem {

    private Spark scShooter;

    public subShooter() {
        scShooter = new Spark(2);
        addChild("scShooter",scShooter);
        scShooter.setInverted(false);
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new cmdManShoot());
    }

    @Override
    public void periodic() {
    }

    public void runShooter(double dir) {
        scShooter.set(dir);
    }
}

