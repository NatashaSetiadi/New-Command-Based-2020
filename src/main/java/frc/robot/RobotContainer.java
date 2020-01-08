package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
// import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.DirectDriveCommand;
import frc.robot.commands.SetEncoderCommand;
import frc.robot.commands.TurnoffEncoderCommand;
import frc.robot.commands.WheelArmCommand;

import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DrivingSubsystem;
import frc.robot.subsystems.EncoderSubsystem;

public class RobotContainer {

    //public XboxController1 controller1;
    XboxController controller2 = new XboxController(Constants.XBOX_CONTROLLER_2_PORT);
    XboxController controller1 = new XboxController(Constants.XBOX_CONTROLLER_1_PORT);

   

    DrivingSubsystem drivingSubsystem = new DrivingSubsystem();
    ArmSubsystem armSubsystem = new ArmSubsystem();
    EncoderSubsystem encoderSubsystem = new EncoderSubsystem();
    public RobotContainer(){
        configureButtonBindings();

        drivingSubsystem.setDefaultCommand(
            new DirectDriveCommand(
                drivingSubsystem,
                () -> getDrive(),
                () -> getTurn()));
        armSubsystem.setDefaultCommand(
            new WheelArmCommand(
                armSubsystem,
                () -> getArm()));

       
    }
  
    public double getTurn() {
		double n = controller1.getX(GenericHID.Hand.kLeft);
		return Math.abs(n) < 0.1 ? 0 : n;
    }
    public double getDrive() {
		double n = controller1.getTriggerAxis(GenericHID.Hand.kRight) - controller1.getTriggerAxis(GenericHID.Hand.kLeft);
		return Math.abs(n) < 0.1 ? 0 : n;
	}
    public double getArm(){
        double n = controller2.getY(GenericHID.Hand.kRight);
		return Math.abs(n) < 0.1 ? 0 : n;
    }
    public boolean setEncoder(){
        return controller2.getYButton();
    }
    private void configureButtonBindings() {
        final JoystickButton encoderButton = new JoystickButton(controller2, XboxController.Button.kY.value);
        
        encoderButton.whenPressed(new SetEncoderCommand(encoderSubsystem));
        encoderButton.whenReleased(new TurnoffEncoderCommand(encoderSubsystem));
    }
  
  
    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
//     public Command getAutonomousCommand() {
//       // An ExampleCommand will run in autonomous
//      // return m_autoCommand;
//     }
 }