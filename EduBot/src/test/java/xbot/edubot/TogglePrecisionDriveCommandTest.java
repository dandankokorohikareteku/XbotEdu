package xbot.edubot;

import org.junit.Test;

import xbot.common.command.BaseCommand;
import xbot.edubot.operator_interface.OperatorInterface;
import xbot.edubot.subsystems.drive.DriveSubsystem;
import xbot.edubot.subsystems.drive.commands.TankDriveWithJoysticksCommand;
import xbot.edubot.subsystems.drive.commands.TogglePrecisionDriveCommand;
import xbot.common.controls.sensors.mock_adapters.MockJoystick;

public class TogglePrecisionDriveCommandTest extends BaseDriveTest {

    @Test
    public void test() {
        DriveSubsystem drive = this.injector.getInstance(DriveSubsystem.class);
        OperatorInterface oi = this.injector.getInstance(OperatorInterface.class);
        
        BaseCommand driveCommand = new TankDriveWithJoysticksCommand(drive, oi);
        BaseCommand togglePrecisionCommand = new TogglePrecisionDriveCommand(drive);
        
        MockJoystick left = (MockJoystick)oi.leftJoystick;
        MockJoystick right = (MockJoystick)oi.rightJoystick;
        
        driveCommand.initialize();
        
        togglePrecisionCommand.initialize();
        
        if(!togglePrecisionCommand.isFinished()){
            togglePrecisionCommand.execute();
        }
        left.setY(1.0);
        right.setY(1.0);
        driveCommand.execute();
        this.assertDrive(0.5, 0.5);
        
        if(!togglePrecisionCommand.isFinished()){
            togglePrecisionCommand.execute();
        }
        left.setY(-1.0);
        right.setY(-1.0);
        driveCommand.execute();
        this.assertDrive(-0.5, -0.5);
        
        togglePrecisionCommand.initialize();
        if(!togglePrecisionCommand.isFinished()){
            togglePrecisionCommand.execute();
        }
        left.setY(1.0);
        right.setY(1.0);
        driveCommand.execute();
        this.assertDrive(1, 1);
        
    }
}
