package xbot.edubot.operator_interface;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import xbot.edubot.subsystems.drive.commands.TogglePrecisionDriveCommand;

@Singleton
public class OperatorCommandMap {

    @Inject
    public OperatorCommandMap(
            OperatorInterface operatorInterface,
            TogglePrecisionDriveCommand togglePrecisionDriveCommand) {
        // Set which buttons should run which commands here
        
        // Example:
        // operatorInterface.driverJoystickButtons.getifAvailable(1).whenPressed(instanceOfYourCommand);
    }
}
