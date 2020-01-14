// FRC 5730 - The Professionals
// 2020 Season

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.cmdManLoad;

public class subLoader extends Subsystem {
    private Spark scLoader;
    private Spark scIntake;
    private DigitalInput diHopperStatus;

    public subLoader() {
        scLoader = new Spark(0);
        addChild("scLoader",scLoader);
        scLoader.setInverted(false);
                
        scIntake = new Spark(1);
        addChild("scIntake",scIntake);
        scIntake.setInverted(false);
                
        diHopperStatus = new DigitalInput(2);
        addChild("diHopperStatus",diHopperStatus);
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new cmdManLoad());
    }

    @Override
    public void periodic() {
    }

    public void runLoader(double dir) {
        scLoader.set(dir);
    }

    public void runIntake(double dir) {
        scIntake.set(dir);
    }

    public boolean isEmpty() {
        return diHopperStatus.get();
    }
}
