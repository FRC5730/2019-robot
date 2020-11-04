// FRC 5730 - The Professionals
// 2020 Season

// This command rotates the robot based on vision processing to center the target
// then move forward or backward for the appropriate ranging

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class cmdCenterTarget extends Command {

    //Tunables
    double rotateSpeed = 0.42;     // How fast should the robot rotate?
    double targetLeftEdge = 75;   // If it's less than this, rotate left
    double targetRightEdge = 85;  // If it's greater than this, rotate right

    // Access the network table to get values from vision co-processor
    NetworkTableInstance tableInst = NetworkTableInstance.getDefault();
    NetworkTable gripTable = tableInst.getTable("GRIP/upperTarget");
    double[] centerX;
    double[] dblCenters = new double[0];
    int sampleCounter = 0;

    public cmdCenterTarget() {
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
      centerX = gripTable.getEntry("centerX").getDoubleArray(dblCenters);
      // if we find a value, rotate the robot to center the target left/right.
      if (centerX.length > 0) {
        System.out.println("Center point is: " + centerX[0]);
        if (centerX[0] < targetLeftEdge) {
          // Rotate to the left
          Robot.subDrive.Drive(rotateSpeed, 0);
        } else if (centerX[0] > targetRightEdge) {
          // Rotate to the right
          Robot.subDrive.Drive(-rotateSpeed, 0);
        } else {
          Robot.subDrive.Drive(0.0, 0.0);
          sampleCounter++;
        }
      }
    }

    @Override
    protected boolean isFinished() {
      // if the target is in the center band, go ahead and quit
      if (sampleCounter > 50) {
        System.out.println("Bailing. Got 50 failed/stopped samples");
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
