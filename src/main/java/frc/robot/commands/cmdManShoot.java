// FRC 5730 - The Professionals
// 2020 Season

// This command allows the operator to manually spin the shooter

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
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
        if (Robot.oi.jsOperator.getRawButton(5)) {
            Robot.subShooter.runShooter(0.90);          
        } else {
            Robot.subShooter.runShooter(0.0);
        }
        if (Robot.oi.jsOperator.getRawButton(8)) {
            Robot.subShooter.runKicker(1.0);
        } else {
            Robot.subShooter.runKicker(0.0);
        }
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
