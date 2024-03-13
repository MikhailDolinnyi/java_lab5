package commands;

import console.ConsoleOutput;
import exceptions.IllegalArguments;
import managers.CollectionManager;

/**
 * Команда 'clear'
 * Очищает коллекцию
 */
public class Clear extends Command {
    private CollectionManager collectionManager;
    private ConsoleOutput consoleOutput;

    public Clear(ConsoleOutput consoleOutput, CollectionManager collectionManager) {
        super("clear", ": очистить коллекцию");
        this.collectionManager = collectionManager;
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
        collectionManager.clear();
        consoleOutput.println("Элементы удалены");
    }
}
