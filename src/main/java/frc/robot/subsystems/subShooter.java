// FRC 5730 - The Professionals
// 2020 Season

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.cmdManShoot;


public class subShooter extends Subsystem {
    
    private Spark scKicker;
    private VictorSP scShooter1;
    private VictorSP scShooter2;
    private SpeedControllerGroup shooterGroup;

    public subShooter() {
        scKicker = new Spark(3);
        addChild("scKicker",scKicker);
        scKicker.setInverted(false);
        
        scShooter1 = new VictorSP(4);
        addChild("scShooter1",scShooter1);
        scShooter2 = new VictorSP(5);
        addChild("scShooter2",scShooter2);
        
        shooterGroup = new SpeedControllerGroup(scShooter1, scShooter2);
        addChild("shooterGroup", shooterGroup);
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new cmdManShoot());
    }

    @Override
    public void periodic() {
    }

    public void runShooter(double dir) {
        shooterGroup.set(dir);
    }

	public void runKicker(double dir) {
        scKicker.set(dir);
	}
}

