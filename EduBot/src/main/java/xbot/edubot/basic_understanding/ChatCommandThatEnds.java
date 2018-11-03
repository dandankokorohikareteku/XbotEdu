package xbot.edubot.basic_understanding;

import xbot.common.command.BaseCommand;

public class ChatCommandThatEnds extends BaseCommand {

    int count;
    
    @Override
    public void initialize() {
        log.info("Initializing!");
        count = 0;
        
    }

    @Override
    public void execute() {
        String message = "";
        
        switch(count) {
            case 0: message = "Looks like this is the start!"; 
            break;
            case 1: message = "I hope this command does some real work!"; 
            break;
            case 3: message = "Any time now..."; 
            break;
            case 4: message = "So, you like robots?"; 
            break;
            case 5: message = "I think robots are great.";
            break;
            case 6: message = "Uh... I don't think I get to do anything."; 
            break;
            case 7: message = "Oh my god, I'm just a chat bot. I quit!"; 
            break;
            default: message = "..."; 
            break;
        }
        
        log.info(message);
        
        count++;
    }
    
    @Override
    public boolean isFinished() {
        if (count >= 20) {
            log.info("GAME OVER!");
            return true;
        }
        return false;
    }
}
