// FRC 5730 - The Professionals
// 2020 Season

// This command allows the operator to manually run the loader and intake

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class cmdManLoad extends Command {

    private double armSpeed = 0.1;
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

        // Intake based on joystick
        Robot.subLoader.runIntake(Robot.oi.jsOperator.getY());
        if (Robot.oi.jsOperator.getX() > 0.1){
            Robot.subLoader.runElevator(0.5);
        } else {
            Robot.subLoader.runElevator(0.0);
        }
        

        // Arms up/down
        // Note armSpeed variable above to set speed
        if (Robot.oi.jsOperator.getRawButton(3)) {
            Robot.subLoader.runArms(armSpeed);
        } else if (Robot.oi.jsOperator.getRawButton(6)) {
            Robot.subLoader.runArms(-armSpeed);
        } else {
            Robot.subLoader.runArms(0.0);
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
