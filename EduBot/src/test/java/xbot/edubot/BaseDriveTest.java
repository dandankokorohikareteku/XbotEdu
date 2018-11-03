package xbot.edubot;

import static org.junit.Assert.assertEquals;

import org.junit.Before;

import xbot.common.controls.sensors.mock_adapters.MockJoystick;
import xbot.common.injection.BaseWPITest;
import xbot.edubot.operator_interface.OperatorInterface;
import xbot.edubot.subsystems.drive.DriveSubsystem;

public class BaseDriveTest extends BaseWPITest {

    protected DriveSubsystem drive;
    OperatorInterface oi;
    
    MockJoystick left;
    MockJoystick right;
    
    @Before
    public void setUp() {
        super.setUp();
        drive = this.injector.getInstance(DriveSubsystem.class);
        oi = this.injector.getInstance(OperatorInterface.class);

        left = (MockJoystick)oi.leftJoystick;
        right = (MockJoystick)oi.rightJoystick;
    }
    
    public void assertDrive (double left, double right) {
        assertEquals(left, this.mockRobotIO.getPWM(1), 0.001);
        assertEquals(left, this.mockRobotIO.getPWM(3), 0.001);
        
        assertEquals(right, this.mockRobotIO.getPWM(2), 0.001);
        assertEquals(right, this.mockRobotIO.getPWM(4), 0.001);
    }
    
    public void assertDrive (double left, double right, String message) {
        assertEquals(message, left, this.mockRobotIO.getPWM(1), 0.001);
        assertEquals(message, left, this.mockRobotIO.getPWM(3), 0.001);
        
        assertEquals(message, right, this.mockRobotIO.getPWM(2), 0.001);
        assertEquals(message, right, this.mockRobotIO.getPWM(4), 0.001);
    }

}
