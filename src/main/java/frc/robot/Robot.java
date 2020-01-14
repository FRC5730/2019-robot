// FRC 5730 - The Professionals
// 2020 Season

package frc.robot;

import edu.wpi.first.hal.FRCNetComm.tInstances;
import edu.wpi.first.hal.FRCNetComm.tResourceType;
import edu.wpi.first.hal.HAL;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

public class Robot extends TimedRobot {

    Command autonomousCommand;
    SendableChooser<Command> chooser = new SendableChooser<>();

    public static OI oi;

    public static subDrive subDrive;
    public static subShooter subShooter;
    public static subLoader subLoader;
    public static subClimber subClimber;
    public static subControlPanel subControlPanel;

    @Override
    public void robotInit() {

        subDrive = new subDrive();
        subShooter = new subShooter();
        subLoader = new subLoader();
        subClimber = new subClimber();
        subControlPanel = new subControlPanel();

        oi = new OI();

        HAL.report(tResourceType.kResourceType_Framework, tInstances.kFramework_RobotBuilder);

        chooser.addOption("cmdAutoLeft", new cmdAutoLeft(null, null, null));
        chooser.addOption("cmdAutoMiddle", new cmdAutoMiddle(null, null, null));
        chooser.addOption("cmdAutoRight", new cmdAutoRight(null, null, null));
        chooser.setDefaultOption("cmdAutoLeft", new cmdAutoLeft(null, null, null));

        SmartDashboard.putData("Auto mode", chooser);
    }

    @Override
    public void disabledInit(){
    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void autonomousInit() {
        autonomousCommand = chooser.getSelected();
        if (autonomousCommand != null) autonomousCommand.start();
    }

    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
}
