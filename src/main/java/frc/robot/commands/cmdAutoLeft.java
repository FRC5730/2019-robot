// FRC 5730 - The Professionals
// 2020 Season

// This command controls the autonomous behavior when starting in the Left position
// when looking at the field from the driver's station.

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.subsystems.subDrive;
import frc.robot.subsystems.subLoader;
import frc.robot.subsystems.subShooter;

public class cmdAutoLeft extends CommandGroup {

    public cmdAutoLeft(subDrive s_drive, subLoader s_loader, subShooter s_shooter) {
        // cmdTimedDrive(seconds, speed)
        //addSequential(new cmdTimedDrive(2, 1));
        addSequential(new cmdTurnToHeading(160));
        addSequential(new cmdCenterTarget());
        addSequential(new cmdRangeTarget());
        addSequential(new cmdShoot());
    }
}
