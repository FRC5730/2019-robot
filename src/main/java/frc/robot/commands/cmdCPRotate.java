// FRC 5730 - The Professionals
// 2020 Season

// This command automatically rotates the control wheel 3.5 times by
// counting the occurances of the originally detected color

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class cmdCPRotate extends Command {

    //Tunables
    double rotateSpeed = 1.0;   // How fast should we rotate the wheel
    double driveSpeed = 0.01;   // How much drive wheel pressure do we need to apply?
    double rotationCount = 7.0; // How much rotation do we need? Counting colors means 6 counts for 3 wheel rotations.

    char originalColor;         // Detect the color before we turn the wheel
    char detectedColor;         // Color the robot sees
    double colorCount;          // Counter for color pulses

    public cmdCPRotate() {
        // enable drive motors
        requires(Robot.subControlPanel);
    }

    @Override
    protected void initialize() {
        // Initialize color count
        colorCount = 0;
        // apply a small amount of forward pressure to the wheels (i.e. 0.01 or something)
        Robot.subDrive.Drive(driveSpeed, driveSpeed);

        // Find the starting color
        originalColor = Robot.subControlPanel.detectColor();
    }

    @Override
    protected void execute() {
        // enable motor
        Robot.subControlPanel.rotateCP(rotateSpeed);
        // Increment counter if the detected color equals the originally
        // detected color
        detectedColor = Robot.subControlPanel.detectColor();

        if (detectedColor == originalColor) {
            colorCount++;
        }
    }

    @Override
    protected boolean isFinished() {
        // Quit when the pulse counter equals the desired rotation count
        if (colorCount == rotationCount) {
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
