// FRC 5730 - The Professionals
// 2020 Season

// This command allows the driver to manually drive the robot

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class cmdDrive extends Command {

    public cmdDrive() {
        requires(Robot.subDrive);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        // Drive using the joysticks
        // TODO: Validate axis
        Robot.subDrive.Drive(Robot.oi.jsDriver.getRawAxis(0), Robot.oi.jsDriver.getRawAxis(0));
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        // stop motors
        Robot.subDrive.Drive(0.0, 0.0);
    }

    @Override
    protected void interrupted() {
        // stop motors
        Robot.subDrive.Drive(0.0, 0.0);
    }
}
