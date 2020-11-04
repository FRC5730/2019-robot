// FRC 5730 - The Professionals
// 2020 Season

// This command automatically rotates the robot to a specified heading

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class cmdTurnToHeading extends Command {

    // Tunables
    private double gyroDeadband = 2;

    private double m_heading;
    private double kP = .1;
    private double error;

    public cmdTurnToHeading(double heading) {
        requires(Robot.subDrive);
        m_heading = heading;
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        // Turn with gryo data
        error = m_heading - Robot.subDrive.getAngle();
        Robot.subDrive.Drive(kP * error, kP * error);
    }

    @Override
    protected boolean isFinished() {
        // End if we get close.
        if (error < gyroDeadband) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void end() {
        Robot.subDrive.Drive(0.0, 0.0);
    }

    @Override
    protected void interrupted() {
        Robot.subDrive.Drive(0.0, 0.0);
    }
}
