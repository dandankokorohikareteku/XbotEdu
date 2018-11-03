
package xbot.edubot;

import xbot.common.command.BaseRobot;
import xbot.edubot.operator_interface.OperatorCommandMap;
import xbot.edubot.subsystems.DefaultCommandMap;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends BaseRobot {
    
    @Override
    protected void setupInjectionModule() {
        this.injectionModule = new EducationModule();
    }

    @Override
    protected void initializeSystems() {
        super.initializeSystems();
        this.injector.getInstance(DefaultCommandMap.class);
        this.injector.getInstance(OperatorCommandMap.class);
    }
}
