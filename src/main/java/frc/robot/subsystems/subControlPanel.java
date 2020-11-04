// FRC 5730 - The Professionals
// 2020 Season

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.commands.cmdManCPRotate;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

public class subControlPanel extends Subsystem {

    // Tunables - Adjust colors to tune dection
    private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
    private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
    private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
    private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

    private Spark scCPRotate;

    // Variables to acces the color sense
    private final I2C.Port i2cPort = I2C.Port.kOnboard;
    private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
    private final ColorMatch m_colorMatcher = new ColorMatch();

    public subControlPanel() {
        scCPRotate = new Spark(8);
        addChild("scCPRotate",scCPRotate);
        scCPRotate.setInverted(false);

        m_colorMatcher.addColorMatch(kBlueTarget);
        m_colorMatcher.addColorMatch(kGreenTarget);
        m_colorMatcher.addColorMatch(kRedTarget);
        m_colorMatcher.addColorMatch(kYellowTarget);
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new cmdManCPRotate());
    }

    @Override
    public void periodic() {
        SmartDashboard.putString("Detected Color", Character.toString(detectColor()));
    }

    public void rotateCP(double dir) {
        scCPRotate.set(dir);
    }

	public char fetchColorFromFMS() {
		return DriverStation.getInstance().getGameSpecificMessage().charAt(0);
    }
    
    public char detectColor() {
        Color detectedColor = m_colorSensor.getColor();

        char colorChar;
        ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);

        if (match.color == kBlueTarget) {
            colorChar = 'B';
        } else if (match.color == kRedTarget) {
            colorChar = 'R';
        } else if (match.color == kGreenTarget) {
            colorChar = 'G';
        } else if (match.color == kYellowTarget) {
            colorChar = 'Y';
        } else {
            colorChar = 'X';
        }
        return colorChar;
    }
}
