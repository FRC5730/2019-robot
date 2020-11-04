// FRC 5730 - The Professionals
// 2020 Season

package frc.robot;

import frc.robot.commands.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {

    public Joystick jsDriver;
    public JoystickButton jbShoot;
    public JoystickButton jbCPRotate;
    public JoystickButton jbCPColorSelect;
    public Joystick jsOperator;

    public OI() {
        jsDriver = new Joystick(0);
        jsOperator = new Joystick(1);

        // jbCPColorSelect = new JoystickButton(jsOperator, 1);
        // jbCPColorSelect.whenPressed(new cmdCPColorSelect());
        // jbCPRotate = new JoystickButton(jsOperator, 1);
        // jbCPRotate.whenPressed(new cmdCPRotate());
        // jbShoot = new JoystickButton(jsOperator, 1);
        // jbShoot.whenPressed(new cmdOpAutoShoot(null, null));
    }

    public Joystick getjsDriver() {
        return jsDriver;
    }

    public Joystick getjsOperator() {
        return jsOperator;
    }
}

