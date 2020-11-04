// FRC 5730 - The Professionals
// 2020 Season

// This command allows the operator to manually run the wench up or down.

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class cmdManClimb extends Command {

    public cmdManClimb() {
        requires(Robot.subClimber);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        // the climber is manually operated so that code is in subClimber
        if (Robot.oi.jsOperator.getRawButton(4)) {
            Robot.subClimber.climbControl(1.0);
        } else if (Robot.oi.jsOperator.getRawButton(7)) {
            Robot.subClimber.climbControl(-1.0);
        } else {
            Robot.subClimber.climbControl(0.0);
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
    }
}
