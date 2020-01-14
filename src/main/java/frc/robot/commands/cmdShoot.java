// FRC 5730 - The Professionals
// 2020 Season

// This command automatically shoots any fuel cells in the hopper

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class cmdShoot extends Command {

    //Tunables
    private final double shooterSpeed = 1.0; // how fast should the shooter spin
    private final double delayTime = 2.0;    // How long should we wait before we start loading balls
    private final double loaderSpeed = 1.0;  // How fast should the loader run
    private Timer spoolDelay = new Timer();

    public cmdShoot() {
        requires(Robot.subShooter);
    }

    @Override
    protected void initialize() {
        // timer to wait for shooter to spool up
        spoolDelay.reset();
        spoolDelay.start();
        Robot.subShooter.runShooter(shooterSpeed);
    }

    @Override
    protected void execute() {
        // if the delay has past, start loader
        if (spoolDelay.get() > delayTime) {
            Robot.subLoader.runLoader(loaderSpeed);
        }

    }

    @Override
    protected boolean isFinished() {
        // shoot until loader is empty
        if (Robot.subLoader.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void end() {
        // stop motors
        Robot.subShooter.runShooter(0);
        Robot.subLoader.runLoader(0);
    }

    @Override
    protected void interrupted() {
        // stop motors
        Robot.subShooter.runShooter(0);
        Robot.subLoader.runLoader(0);
    }
}
