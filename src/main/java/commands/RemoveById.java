package commands;


import exceptions.IllegalArguments;
import managers.CollectionManager;
import console.ConsoleOutput;
import console.OutputColors;

/**
 * Команда 'remove_by_id'
 * Удаляет элемент из коллекции по его id
 */
public class RemoveById extends Command {
    private CollectionManager collectionManager;
    private ConsoleOutput consoleOutput;

    public RemoveById(ConsoleOutput consoleOutput, CollectionManager collectionManager) {
        super("remove_by_id", " id: удалить элемент из коллекции по его id");
        this.collectionManager = collectionManager;
        this.consoleOutput = consoleOutput;
    }

    /**
     * Исполнить команду
     * @param args аргументы команды
     * @throws IllegalArguments неверные аргументы команды
     */
    @Override
    public void execute(String args) throws IllegalArguments{
        if (args.isBlank()) throw new IllegalArguments();
        class NoSuchId extends RuntimeException {
        }
        try {
            int id = Integer.parseInt(args.trim());
            if (!collectionManager.checkExist(Long.valueOf(id))) throw new NoSuchId();
            collectionManager.removeElement(collectionManager.getById(Long.valueOf(id)));
            consoleOutput.println(OutputColors.toColor("Объект удален успешно", OutputColors.GREEN));
        } catch (NoSuchId err) {
            consoleOutput.printError("В коллекции нет элемента с таким id");
        } catch (NumberFormatException exception) {
            consoleOutput.printError("id должно быть числом типа int");
        }
    }
}