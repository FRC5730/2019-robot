// FRC 5730 - The Professionals
// 2020 Season

// This command rotates the robot based on vision processing to center the target
// then move forward or backward for the appropriate ranging

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class cmdRangeTarget extends Command {

    //Tunables
    double speed = 0.2;
    double targetMinArea = 500;
    double targetMaxArea = 1000;

    // Fetch targeting info from vision co-processor
    NetworkTableInstance tableInst = NetworkTableInstance.getDefault();
    // TODO: Update target table to work with cameleon-vision
    NetworkTable gripTable = tableInst.getTable("GRIP/myLinesReport");
    NetworkTableEntry area;

    public cmdRangeTarget() {
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        // TODO: This may need to be redone to work with cameleon-vision
        area = gripTable.getEntry("area");

        // Make sure area is not null
        if (area.getDouble(0.0) > 0 ) {
            if (area.getDouble(0.0) < targetMinArea) {
                // Drive forward
                Robot.subDrive.Drive(speed, speed);
            } else if (area.getDouble(0.0) > targetMaxArea) {
                // Drive backward
                Robot.subDrive.Drive(-speed, -speed);
            } else {
                Robot.subDrive.Drive(0.0, 0.0);
            }
        }
    }

    @Override
    protected boolean isFinished() {
        // TODO: This may need to be redone to work with cameleon-vision
        if (area.getDouble(0.0) > targetMinArea && area.getDouble(0.0) < targetMaxArea) {
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
