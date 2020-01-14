// FRC 5730 - The Professionals
// 2020 Season

// This command detects and automatically rotates the control wheel to select
// the FMS provided color

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class cmdCPColorSelect extends Command {

    //Tunables
    double rotateSpeed = 1.0; // How fast should we rotate the wheel?
    double driveSpeed = 0.01; // How much drive wheel pressure do we need to apply?

    char targetColor;         // The calculated color we want
    char detectedColor;       // The color the robot sees
    char fmsColor;            // The color provided by the FMS

    public cmdCPColorSelect() {
        // enable drive motors
        requires(Robot.subControlPanel);
    }

    @Override
    protected void initialize() {
        // apply a small amount of forward pressure to the wheels (i.e. 0.01 or something)
        Robot.subDrive.Drive(driveSpeed, driveSpeed);
        // fetch color from FMS
        fmsColor = Robot.subControlPanel.fetchColorFromFMS();
        // determine target color since field sensor is 90 degrees
        // off of the robot's angle
        switch (fmsColor)
        {
            case 'B' :
            targetColor = 'R';
            break;
            case 'G' :
            targetColor = 'Y';
            break;
            case 'R' :
            targetColor = 'B';
            break;
            case 'Y' :
            targetColor = 'G';
            break;
            default :
            targetColor = 'X';
            break;
        }
    }

    @Override
    protected void execute() {
        // enable drive motors
        Robot.subControlPanel.rotateCP(rotateSpeed);

        // Fetch current color from sensor
        detectedColor = Robot.subControlPanel.detectColor();
    }

    @Override
    protected boolean isFinished() {
        if (detectedColor == targetColor) {
            // Return true if target color is detected
            return true;
        } else if (targetColor == 'X') {
            // Return true if target color isn't valid
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void end() {
        // stop motors
        Robot.subDrive.Drive(0.0, 0.0);
        Robot.subControlPanel.rotateCP(0.0);
    }

    @Override
    protected void interrupted() {
        // stop motors
        Robot.subDrive.Drive(0.0, 0.0);
        Robot.subControlPanel.rotateCP(0.0);
    }
}
