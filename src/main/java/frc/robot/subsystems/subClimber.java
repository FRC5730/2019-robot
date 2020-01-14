// FRC 5730 - The Professionals
// 2020 Season

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.commands.cmdManClimb;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

public class subClimber extends Subsystem {

    private Spark scWenchLeft;
    private Spark scWenchRight;
    private SpeedControllerGroup scWench;
    private DigitalInput diWenchUpperLimit;
    private DigitalInput diWenchLowerLimit;

    public subClimber() {
        scWenchLeft = new Spark(6);
        addChild("scWenchLeft",scWenchLeft);
        scWenchLeft.setInverted(false);

        scWenchRight = new Spark(7);
        addChild("scWenchRight",scWenchRight);
        scWenchRight.setInverted(false);

        scWench = new SpeedControllerGroup(scWenchLeft, scWenchRight  );
        addChild("scWench",scWench);

        diWenchUpperLimit = new DigitalInput(3);
        addChild("diWenchUpperLimit",diWenchUpperLimit);

        diWenchLowerLimit = new DigitalInput(4);
        addChild("diWenchLowerLimit",diWenchLowerLimit);
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new cmdManClimb());
    }

    @Override
    public void periodic() {
    }

    public void climbControl() {
        // TODO: Update axis number.
        // TODO: Verify axis direction compared to limits
        double dir = Robot.oi.jsOperator.getRawAxis(0);
        double val = 0.0;
        if (diWenchUpperLimit.get()) {
            if (dir < 0) {
                val = 0;
            } else {
                val = dir;
            }
        } else if (diWenchLowerLimit.get()) {
            if (dir > 0) {
                val = 0;
            } else {
                val = dir;
            }
        } else {
            val = dir;
        }
        scWench.set(val);
    }
}
