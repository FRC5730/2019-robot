// FRC 5730 - The Professionals
// 2020 Season

// This command controls the autonomous behavior when starting in the Left position
// when looking at the field from the driver's station.

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class cmdAutoTest extends Command {

    public cmdAutoTest() {
        requires(Robot.subDrive);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        // the climber is manually operated so that code is in subClimber
        
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
