package frc.robot.commands;

import java.util.function.DoubleSupplier;

import frc.robot.Robot;
import frc.robot.subsystems.DrivingSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DirectDriveCommand extends CommandBase {
	
	private final DrivingSubsystem drivingSubsystem;
	private final DoubleSupplier forward;
	private final DoubleSupplier rotation;
	
	public DirectDriveCommand(DrivingSubsystem subsystem, DoubleSupplier f, DoubleSupplier r) {
	  // Use addRequirements() here to declare subsystem dependencies.
	  drivingSubsystem = subsystem;
	  forward=f;
	  rotation=r;
	  addRequirements(drivingSubsystem);
	}
  

	public void initialize() {
		// Make sure the controller is initialized before we try to use it.
		
	}

	public void execute() {
		Robot.drivingSubsystem.drive(forward.getAsDouble(), rotation.getAsDouble()); // Use input directly from the controller
	}

	public boolean isFinished() {
		return false; // We don't want the command to stop, we want it to be interrupted.
	}

	protected void end() {
		Robot.drivingSubsystem.stop();
	}

}