// FRC 5730 - The Professionals
// 2020 Season

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.cmdManLoad;

public class subLoader extends Subsystem {
    private Spark scPivot;
    private Spark scBallIntake;
    private Spark scElevator;
    private Spark scDeckIntake;
    private DigitalInput diHopperStatus;

    public subLoader() {
        scPivot = new Spark(0);
        addChild("scPivot",scPivot);
        scPivot.setInverted(false);
                
        scBallIntake = new Spark(1);
        addChild("scBallIntake",scBallIntake);
        scBallIntake.setInverted(false);

        scElevator = new Spark(2);
        addChild("scElevator",scElevator);
        scElevator.setInverted(false);

        scDeckIntake = new Spark(9);
        addChild("scDeckIntake",scDeckIntake);
        scDeckIntake.setInverted(false);

        diHopperStatus = new DigitalInput(6);
        addChild("diHopperStatus",diHopperStatus);
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new cmdManLoad());
    }

    @Override
    public void periodic() {
    }

    public void runArms(double dir){
        scPivot.set(dir);
    }

    public void runIntake(double dir) {
        // TODO add method to adjust dir value if needed to slow motors down
        scBallIntake.set(dir);
        scDeckIntake.set(dir);
    }

    public boolean isEmpty() {
        return diHopperStatus.get();
    }

	public void runElevator(double dir) {
        scElevator.set(dir);

	}
}
