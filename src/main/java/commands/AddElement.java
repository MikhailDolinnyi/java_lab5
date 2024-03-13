package commands;

import exceptions.FIleFieldException;
import exceptions.IllegalArguments;
import managers.CollectionManager;
import console.ConsoleOutput;
import console.OutputColors;
import exceptions.InvalidForm;
import collections.asks.AskSpaceMarine;


/**
 * Команда 'add'
 * Добавляет новый элемент в коллекцию
 */
public class AddElement extends Command{
    private CollectionManager collectionManager;
    private ConsoleOutput consoleOutput;

    public AddElement(ConsoleOutput consoleOutput, CollectionManager collectionManager) {
        super("add", " {element}: добавить новый элемент в коллекцию");
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
        try {
            consoleOutput.println(OutputColors.toColor("Создание объекта StudyGroup", OutputColors.CYAN));
            collectionManager.addElement(new AskSpaceMarine(consoleOutput).build());
            consoleOutput.println(OutputColors.toColor("Создание объекта StudyGroup окончено успешно!", OutputColors.CYAN));
        } catch (InvalidForm invalidForm) {
            consoleOutput.printError("Поля объекта не валидны! Объект не создан!");
        } catch (FIleFieldException e){
            consoleOutput.printError("Поля в файле не валидны! Объект не создан");
        }
    }
}
