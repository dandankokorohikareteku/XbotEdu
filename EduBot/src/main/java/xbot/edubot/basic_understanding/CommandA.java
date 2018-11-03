package xbot.edubot.basic_understanding;

import com.google.inject.Inject;

import xbot.common.command.BaseCommand;

public class CommandA extends BaseCommand {

    ExampleSubsystem exampleSubsystem;
    
    @Inject
    public CommandA(ExampleSubsystem exampleSubsystem) {
        requires(exampleSubsystem);
        this.exampleSubsystem = exampleSubsystem;
    }
    
    @Override
    public void initialize() {
        log.info("Initializing!");
    }

    @Override
    public void execute() {
         exampleSubsystem.writeMessage("CommandA is the best.");
    }

}
