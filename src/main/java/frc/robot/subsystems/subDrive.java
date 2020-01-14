// FRC 5730 - The Professionals
// 2020 Season

package frc.robot.subsystems;

import com.analog.adis16448.frc.ADIS16448_IMU;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.commands.cmdDrive;

public class subDrive extends Subsystem {

    private WPI_VictorSPX scLeftFront;
    private WPI_VictorSPX scLeftRear;
    private SpeedControllerGroup leftGroup;
    private WPI_VictorSPX scRightFront;
    private WPI_VictorSPX scRightRear;
    private SpeedControllerGroup rightGroup;
    private DifferentialDrive difDrive;
    private Ultrasonic usForwardCollision;

    private final ADIS16448_IMU imu = new ADIS16448_IMU();

    public subDrive() {
        scLeftFront = new WPI_VictorSPX(1);
        addChild("scLeftFront", scLeftFront);
        scLeftFront.setInverted(false);

        scLeftRear = new WPI_VictorSPX(2);
        addChild("scLeftRear", scLeftRear);
        scLeftRear.setInverted(false);

        leftGroup = new SpeedControllerGroup(scLeftFront, scLeftRear);
        addChild("leftGroup",leftGroup);

        scRightFront = new WPI_VictorSPX(3);
        addChild("scRightFront", scRightFront);
        scRightFront.setInverted(false);

        scRightRear = new WPI_VictorSPX(4);
        addChild("scRightRear", scRightRear);
        scRightRear.setInverted(false);

        rightGroup = new SpeedControllerGroup(scRightFront, scRightRear);
        addChild("rightGroup",rightGroup);

        difDrive = new DifferentialDrive(leftGroup, rightGroup);
        addChild("difDrive",difDrive);
        difDrive.setSafetyEnabled(true);
        difDrive.setExpiration(0.1);
        difDrive.setMaxOutput(1.0);

        usForwardCollision = new Ultrasonic(0, 1);
        addChild("usForwardCollision",usForwardCollision);
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new cmdDrive());
    }

    @Override
    public void periodic() {
    }

    public void Drive(double leftVal, double rightVal) {
        difDrive.tankDrive(leftVal, rightVal);
    }

    public double getRate() {
        return imu.getRate();
    }

    public double getAngle() {
        return imu.getAngle();
    }
}
