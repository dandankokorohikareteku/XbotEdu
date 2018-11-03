package xbot.edubot.subsystems.drive;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import edu.wpi.first.wpilibj.MockDistanceSensor;
import xbot.common.controls.actuators.XSpeedController;
import xbot.common.injection.wpi_factories.CommonLibFactory;
import xbot.edubot.MockHeadingSensor;

@Singleton
public class DriveSubsystem {

    public MockDistanceSensor distanceSensor;
    public MockHeadingSensor gyro;
    
    XSpeedController frontLeft;
    XSpeedController frontRight;
    XSpeedController rearLeft;
    XSpeedController rearRight;
        
    @Inject
    public DriveSubsystem(CommonLibFactory factory) {
        // instantiate speed controllers and sensors here, save them as class members
        distanceSensor = new MockDistanceSensor();
        gyro = new MockHeadingSensor();
        
        frontLeft = factory.createSpeedController(1);
        rearLeft = factory.createSpeedController(3);
        frontRight = factory.createSpeedController(2);
        rearRight = factory.createSpeedController(4);
    }
    
    public void tankDrive(double leftPower, double rightPower) {
        // You'll need to take these power values and assign them to all of the motors. As
        // an example, here is some code that has the frontLeft motor to spin according to
        // the value of leftPower:
        frontLeft.setPower(leftPower);
    }
}
