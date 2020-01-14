// FRC 5730 - The Professionals
// 2020 Season

// This command allows the operator to manually rotate the control wheel

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class cmdManCPRotate extends Command {

    public cmdManCPRotate() {
        requires(Robot.subControlPanel);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        // TODO: validate axis
        // Rotate 
        Robot.subControlPanel.rotateCP(Robot.oi.jsOperator.getRawAxis(1));
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
    }
}
