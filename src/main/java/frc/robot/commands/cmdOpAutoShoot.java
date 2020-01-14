// FRC 5730 - The Professionals
// 2020 Season

// This command is called by the operator to auto range and shoot

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.subsystems.subLoader;
import frc.robot.subsystems.subShooter;

public class cmdOpAutoShoot extends CommandGroup {

    public cmdOpAutoShoot(subLoader s_loader, subShooter s_shooter) {
        addSequential(new cmdCenterTarget());
        addSequential(new cmdRangeTarget());
        addSequential(new cmdShoot());
    }
}
