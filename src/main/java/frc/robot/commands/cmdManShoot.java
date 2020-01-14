// FRC 5730 - The Professionals
// 2020 Season

// This command allows the operator to manually spin the shooter

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class cmdManShoot extends Command {

    public cmdManShoot() {
        requires(Robot.subShooter);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        // TODO: Consider setting shooter val as a tunable via the dashboard
        Robot.subShooter.runShooter(1.0);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Robot.subShooter.runShooter(0.0);
    }

    @Override
    protected void interrupted() {
        Robot.subShooter.runShooter(0.0);
    }
}
