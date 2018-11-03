package xbot.edubot.operator_interface;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import xbot.edubot.subsystems.drive.commands.ArcadeDriveWithJoysticksCommand;
import xbot.edubot.subsystems.drive.commands.TankDriveWithJoysticksCommand;
import xbot.edubot.subsystems.drive.commands.TogglePrecisionDriveCommand;

@Singleton
public class OperatorCommandMap {

    @Inject
    public OperatorCommandMap(
            OperatorInterface operatorInterface,
            TogglePrecisionDriveCommand togglePrecisionDriveCommand, 
            ArcadeDriveWithJoysticksCommand arcade,
            TankDriveWithJoysticksCommand tankDrive) {
        // Set which buttons should run which commands here
        
    	operatorInterface.controller.getifAvailable(9).toggleWhenPressed(arcade);
    	operatorInterface.controller.getifAvailable(10).whenPressed(tankDrive);
        // Example:
        // operatorInterface.driverJoystickButtons.getifAvailable(1).whenPressed(instanceOfYourCommand);
    }
}
