package xbot.edubot.subsystems.drive.commands;

import com.google.inject.Inject;

import xbot.common.command.BaseCommand;
import xbot.edubot.operator_interface.OperatorInterface;
import xbot.edubot.subsystems.drive.DriveSubsystem;

public class ArcadeDriveWithJoysticksCommand extends BaseCommand {
	DriveSubsystem drive;
	OperatorInterface operate;
	
    @Inject
    public ArcadeDriveWithJoysticksCommand(DriveSubsystem driveSubsystem, OperatorInterface oi) {
    	drive = driveSubsystem;
        operate = oi;
        this.requires(driveSubsystem);
    }
    
    @Override
    public void initialize() {
        
    }

    @Override
    public void execute() {
    	double leftStickFormula = operate.controller.getLeftVector().y + operate.controller.getRightVector().x;
    	double rightStickFormula = operate.controller.getLeftVector().y - operate.controller.getRightVector().x;
    	
    	if(leftStickFormula > 1.0 || leftStickFormula < -1.0) {
    		leftStickFormula /= 2.0;
    	}
    	
        //double leftStick = operate.controller.getLeftVector().y;
        //double rightStick = operate.controller.getRightVector().x;
        
        
        
        drive.tankDrive(leftStickFormula, rightStickFormula);        
        
    }

}
