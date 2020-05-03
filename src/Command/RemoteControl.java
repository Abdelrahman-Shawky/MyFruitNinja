package Command;

public class RemoteControl {

    private Command[] commands;

    public RemoteControl() {
        this.commands = new Command[5];
    }

    public void play(int index){
        commands[index].execute();
    }

    public void setCommand(int index,Command command){
        this.commands[index] = command;
    }

}
