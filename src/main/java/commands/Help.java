package commands;


import console.ConsoleOutput;
import console.OutputColors;
import exceptions.IllegalArguments;
import managers.CommandManager;

/**
 * Команда 'help'
 * Вывести справку по доступным командам
 */
public class Help extends Command{
    private CommandManager commandManager;
    private ConsoleOutput consoleOutput;
    public Help(ConsoleOutput consoleOutput, CommandManager commandManager) {
        super("help", ": вывести справку по доступным командам");
        this.commandManager = commandManager;
        this.consoleOutput = consoleOutput;
    }

    /**
     * Исполнить команду
     * @param args аргументы команды
     * @throws IllegalArguments неверные аргументы команды
     */
    @Override
    public void execute(String args) throws IllegalArguments {
        if (!args.isBlank()) throw new IllegalArguments();
        commandManager.getCommands()
                .forEach(command -> consoleOutput.println(OutputColors.toColor(command.getName(), OutputColors.CYAN) + command.getDescription()));
    }
}