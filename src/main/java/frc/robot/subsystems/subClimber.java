// FRC 5730 - The Professionals
// 2020 Season

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.cmdManClimb;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;

public class subClimber extends Subsystem {

    private VictorSP scClimberLeft;
    private VictorSP scClimberRight;
    private SpeedControllerGroup climberGroup;
    private DigitalInput diClimberUpperLimit;
    private DigitalInput diClimberLowerLimit;

    public subClimber() {

        scClimberLeft = new VictorSP(6);
        addChild("scClimberLeft",scClimberLeft);

        scClimberRight = new VictorSP(7);
        addChild("scClimberRight",scClimberRight);

        climberGroup = new SpeedControllerGroup(scClimberLeft, scClimberRight);
        addChild("climberGroup",climberGroup);

        diClimberUpperLimit = new DigitalInput(7);
        addChild("diClimberUpperLimit",diClimberUpperLimit);

        diClimberLowerLimit = new DigitalInput(8);
        addChild("diClimberLowerLimit",diClimberLowerLimit);
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new cmdManClimb());
    }

    @Override
    public void periodic() {
    }

    public void climbControl(double dir) {
        // TODO: Update axis number.
        // TODO: Verify axis direction compared to limits
        // double dir = Robot.oi.jsOperator.getRawAxis(0);
        // double val = 0.0;
        // if (diClimberUpperLimit.get()) {
        //     if (dir < 0) {
        //         val = 0;
        //     } else {
        //         val = dir;
        //     }
        // } else if (diClimberLowerLimit.get()) {
        //     if (dir > 0) {
        //         val = 0;
        //     } else {
        //         val = dir;
        //     }
        // } else {
        //     val = dir;
        // }

        climberGroup.set(dir);
    }
}
