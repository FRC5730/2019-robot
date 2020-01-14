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

public class cmdCenterTarget extends Command {

    //Tunables
    double rotateSpeed = 0.2;     // How fast should the robot rotate?
    double targetLeftEdge = 300;  // Pixel count for the left edge. Min value is 0
    double targetRightEdge = 340; // Pixel count for the right edge. Max value is 640 

    // Access the network table to get values from vision co-processor
    NetworkTableInstance tableInst = NetworkTableInstance.getDefault();
    NetworkTable gripTable = tableInst.getTable("GRIP/myLinesReport");
    NetworkTableEntry centerX;

    public cmdCenterTarget() {
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
      centerX = gripTable.getEntry("centerX");

      // if we find a value, rotate the robot to center the target left/right.
      if (centerX.getDouble(0.0) > 0 ){
        if (centerX.getDouble(0.0) < targetLeftEdge) {
          // Rotate to the left
          Robot.subDrive.Drive(-rotateSpeed, rotateSpeed);
        } else if (centerX.getDouble(0.0) > targetRightEdge) {
          // Rotate to the right
          Robot.subDrive.Drive(rotateSpeed, -rotateSpeed);
        } else {
          Robot.subDrive.Drive(0.0, 0.0);
        }
      }
    }

    @Override
    protected boolean isFinished() {
      // if the target is in the center band, go ahead and quit
      if (centerX.getDouble(0.0) > targetLeftEdge && centerX.getDouble(0.0) < targetRightEdge) {
        return true;
      } else {
        return false;
      }
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
