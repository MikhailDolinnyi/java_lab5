package commands;

import console.ConsoleOutput;
import console.OutputColors;
import exceptions.FIleFieldException;
import exceptions.IllegalArguments;
import exceptions.InvalidForm;
import managers.CollectionManager;
import collections.SpaceMarine;
import collections.asks.AskSpaceMarine;

/**
 * Команда 'update'
 * Обновляет значение элемента коллекции, id которого равен заданному
 */
public class UpdateId extends Command{
    private CollectionManager collectionManager;
    private ConsoleOutput consoleOutput;

    public UpdateId(ConsoleOutput consoleOutput, CollectionManager collectionManager) {
        super("update", " id {element}: обновить значение элемента коллекции, id которого равен заданному");
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
        class NoSuchId extends RuntimeException{

        }
        try {
            int id = Integer.parseInt(args.trim());
            if (!collectionManager.checkExist(Long.valueOf(id))) throw new NoSuchId();
            consoleOutput.println(OutputColors.toColor("Создание нового объекта StudyGroup", OutputColors.CYAN));
            SpaceMarine newSpaceMarine = new AskSpaceMarine(consoleOutput).build();
            collectionManager.editById(Long.valueOf(id), newSpaceMarine);
            consoleOutput.println(OutputColors.toColor("Создание нового объекта StudyGroup окончено успешно!", OutputColors.CYAN));
        } catch (NoSuchId err) {
            consoleOutput.printError("В коллекции нет элемента с таким id");
        } catch (InvalidForm invalidForm) {
            consoleOutput.printError("Поля объекта не валидны! Объект не создан!");
        } catch (NumberFormatException exception) {
            consoleOutput.printError("id должно быть числом типа int");
        } catch (FIleFieldException e){
            consoleOutput.printError("Поля в файле не валидны! Объект не создан");
        }
    }
}