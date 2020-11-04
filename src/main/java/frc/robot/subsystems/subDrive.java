// FRC 5730 - The Professionals
// 2020 Season

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
    private Gyro gyro;
    private Encoder encLeft;
    private Encoder encRight;

    public subDrive() {
        scLeftFront = new WPI_VictorSPX(1);
        addChild("scLeftFront", scLeftFront);
        scLeftFront.setInverted(true);

        scLeftRear = new WPI_VictorSPX(2);
        addChild("scLeftRear", scLeftRear);
        scLeftRear.setInverted(true);

        leftGroup = new SpeedControllerGroup(scLeftFront, scLeftRear);
        addChild("leftGroup", leftGroup);

        scRightFront = new WPI_VictorSPX(3);
        addChild("scRightFront", scRightFront);
        scRightFront.setInverted(true);

        scRightRear = new WPI_VictorSPX(4);
        addChild("scRightRear", scRightRear);
        scRightRear.setInverted(true);

        rightGroup = new SpeedControllerGroup(scRightFront, scRightRear);
        addChild("rightGroup", rightGroup);

        difDrive = new DifferentialDrive(leftGroup, rightGroup);
        addChild("difDrive", difDrive);
        difDrive.setSafetyEnabled(true);
        difDrive.setExpiration(0.1);
        difDrive.setMaxOutput(1.0);

        usForwardCollision = new Ultrasonic(4, 5);
        addChild("usForwardCollision", usForwardCollision);

        gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);
        addChild("gyro", (Sendable) gyro);

        encLeft = new Encoder(0, 1);
        addChild("encLeft", encLeft);
        encRight = new Encoder(2, 3);
        addChild("encRight", encRight);
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new cmdDrive());
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Gyro Angle", gyro.getAngle());
        SmartDashboard.putNumber("Gyro Rate", gyro.getRate());
        SmartDashboard.putNumber("Left Encoder", encLeft.get());
        SmartDashboard.putBoolean("Left Direction", encLeft.getDirection());
        SmartDashboard.putNumber("Right Encoder", encRight.get());
        SmartDashboard.putBoolean("Right Direction", encRight.getDirection());
    }

    public void Drive(double leftVal, double rightVal) {
        difDrive.tankDrive(leftVal, rightVal, true);
    }

    public double getRate() {
        return gyro.getRate();
    }

    public double getAngle() {
        return gyro.getAngle();
    }
}
