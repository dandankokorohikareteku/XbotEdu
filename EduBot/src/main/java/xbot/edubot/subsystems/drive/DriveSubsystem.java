package xbot.edubot.subsystems.drive;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import edu.wpi.first.wpilibj.MockDistanceSensor;
import xbot.common.command.BaseSubsystem;
import xbot.common.controls.actuators.XCANTalon;
import xbot.common.controls.actuators.XSpeedController;
import xbot.common.injection.wpi_factories.CommonLibFactory;
import xbot.edubot.MockHeadingSensor;

@Singleton
public class DriveSubsystem extends BaseSubsystem{

    public MockDistanceSensor distanceSensor;
    public MockHeadingSensor gyro;
    
    XSpeedController frontLeft;
    XSpeedController frontRight;
    XSpeedController rearLeft;
    XSpeedController rearRight;
    
    XCANTalon leftMaster;
    XCANTalon leftFollower;
    XCANTalon rightMaster;
    XCANTalon rightFollower;
    
    boolean PrecisionMode = false; 
        
    @Inject
    public DriveSubsystem(CommonLibFactory factory) {
        // instantiate speed controllers and sensors here, save them as class members
        distanceSensor = new MockDistanceSensor();
        gyro = new MockHeadingSensor();
        
        frontLeft = factory.createSpeedController(1);
        rearLeft = factory.createSpeedController(3);
        frontRight = factory.createSpeedController(2);
        rearRight = factory.createSpeedController(4);
        
        this.leftMaster = factory.createCANTalon(34);
        this.leftFollower = factory.createCANTalon(35);
        configureMotorTeam("LeftDriveMaster", leftMaster, leftFollower, true,
                true, false);

        this.rightMaster = factory.createCANTalon(21);
        this.rightFollower = factory.createCANTalon(20);
        configureMotorTeam("RightDriveMaster", rightMaster, rightFollower, false,
                false, false);
    }
    
    public void tankDrive(double leftPower, double rightPower) {
        // You'll need to take these power values and assign them to all of the motors. As
        // an example, here is some code that has the frontLeft motor to spin according to
        // the value of leftPower:
    	if(PrecisionMode == true) {
			leftPower = leftPower/2.0; 
			rightPower = rightPower/2.0;
		}
        leftMaster.simpleSet(leftPower);
        rightMaster.simpleSet(rightPower);
        
    }
    public void togglePrecisionMode() {
		if (PrecisionMode == false) {
			PrecisionMode = true;
		} else {
			PrecisionMode = false;
		}
    }
    private void configureMotorTeam(String masterName, XCANTalon master, XCANTalon follower, boolean masterInverted,
            boolean followerInverted, boolean sensorPhase) {
        follower.follow(master);

        master.setInverted(masterInverted);
        follower.setInverted(followerInverted);

        master.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
        master.setSensorPhase(sensorPhase);
        master.createTelemetryProperties("getPrefix()", masterName);

        // Master Config
        master.setNeutralMode(NeutralMode.Coast);
        master.configForwardLimitSwitchSource(LimitSwitchSource.Deactivated, LimitSwitchNormal.Disabled, 0);
        master.configReverseLimitSwitchSource(LimitSwitchSource.Deactivated, LimitSwitchNormal.Disabled, 0);

        master.configPeakOutputForward(1, 0);
        master.configPeakOutputReverse(-1, 0);

        // Follower Config
        follower.setNeutralMode(NeutralMode.Coast);
        follower.configPeakOutputForward(1, 0);
        follower.configPeakOutputReverse(-1, 0);

        follower.configForwardLimitSwitchSource(LimitSwitchSource.Deactivated, LimitSwitchNormal.Disabled, 0);
    }
}
