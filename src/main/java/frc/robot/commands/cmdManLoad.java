// FRC 5730 - The Professionals
// 2020 Season

// This command allows the operator to manually run the loader and intake

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class cmdManLoad extends Command {

    public cmdManLoad() {
        requires(Robot.subLoader);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        // TODO: validate button numbers 
        // TODO: validate magnatude and directions

        // Intake In
        if (Robot.oi.jsOperator.getRawButton(1)) {
            Robot.subLoader.runIntake(1.0);
        } else {
            Robot.subLoader.runIntake(0.0);
        }

        // Intake out
        if (Robot.oi.jsOperator.getRawButton(1)) {
            Robot.subLoader.runIntake(-1.0);
        } else {
            Robot.subLoader.runIntake(0.0);
        }

        // Loader In
        if (Robot.oi.jsOperator.getRawButton(1)) {
            Robot.subLoader.runLoader(1.0);
        } else {
            Robot.subLoader.runLoader(0.0);
        }

        // Loader out
        if (Robot.oi.jsOperator.getRawButton(1)) {
            Robot.subLoader.runLoader(-1.0);
        } else {
            Robot.subLoader.runLoader(0.0);
        }
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
