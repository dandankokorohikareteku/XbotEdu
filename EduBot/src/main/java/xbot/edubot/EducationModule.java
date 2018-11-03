package xbot.edubot;

import xbot.common.injection.RobotModule;
import xbot.common.subsystems.pose.BasePoseSubsystem;
import xbot.edubot.subsystems.pose.PoseSubsystem;

public class EducationModule extends RobotModule {

    @Override
    protected void configure() {
        super.configure();
        this.bind(BasePoseSubsystem.class).to(PoseSubsystem.class);
    }
}
