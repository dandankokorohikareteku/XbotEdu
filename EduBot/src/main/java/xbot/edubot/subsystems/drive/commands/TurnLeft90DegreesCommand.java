package xbot.edubot.subsystems.drive.commands;

import com.google.inject.Inject;

import xbot.common.command.BaseCommand;
import xbot.edubot.subsystems.drive.DriveSubsystem;

public class TurnLeft90DegreesCommand extends BaseCommand {
    
    DriveSubsystem drive;
    
    @Inject
    public TurnLeft90DegreesCommand(DriveSubsystem driveSubsystem) {
        this.drive = driveSubsystem;
    }
    
    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
    }



}
