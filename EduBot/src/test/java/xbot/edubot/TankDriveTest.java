package xbot.edubot;

import org.junit.Test;

import xbot.common.command.BaseCommand;
import xbot.edubot.subsystems.drive.commands.TankDriveWithJoysticksCommand;

public class TankDriveTest extends BaseDriveTest {

    @Test
    public void test() {
        BaseCommand command = injector.getInstance(TankDriveWithJoysticksCommand.class);
        
        // Call the TankDriveWithJoysticksCommand initialize once
        command.initialize();
        
        // Call execute without having moved the joysticks yet. This means the joystick axes are at 0,
        // so the motors should be at 0 power (stopped).
        command.execute();
        assertDrive(0, 0, "Expect Motors initially 0");
        
        // Push the left and right joystick to fully forward (+1). This should make all motors go to +1,
        // also knows as "full forward"
        left.setY(1.0);
        right.setY(1.0);
        command.execute();
        assertDrive(1.0,1.0, "Expect Motors are all forward when both joysticks are completely forward");
        
        // Push the left joystick fully backward (-1) and the right joystick fully forward (1). This
        // would make a tank turn!
        left.setY(-1.0);
        right.setY(1.0);
        command.execute();
        assertDrive(-1.0,1.0, "Expect Motors are all forward when both joysticks are completely forward");
        
    }
}
