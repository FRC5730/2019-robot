// FRC 5730 - The Professionals
// 2020 Season

// This command automatically drives the robot forward for a specified 
// number of seconds at a specified speed. The drive is also gyro stablized.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class cmdTimedDrive extends Command {

    private double m_seconds;
    private double m_speed;
    private Timer driveTimer = new Timer();
    private double kP = 1.0;

    public cmdTimedDrive(double seconds, double speed) {
        requires(Robot.subDrive);
        m_seconds = seconds;
        m_speed = speed;
    }

    @Override
    protected void initialize() {
        driveTimer.reset();
        driveTimer.start();
    }

    @Override
    protected void execute() {
        // Gyro stablized drive
        //double error = -Robot.subDrive.getRate();
        //System.out.println("Error val is :" + error);
        //Robot.subDrive.Drive(m_speed + kP * error, m_speed - kP * error);
        Robot.subDrive.Drive(m_speed, m_speed);
    }

    @Override
    protected boolean isFinished() {
        // end when time has past
        if (driveTimer.get() > m_seconds) {
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
