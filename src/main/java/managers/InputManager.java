package managers;

import console.ConsoleOutput;
import console.OutputColors;
import console.Printable;
import exceptions.NoCommandException;
import exceptions.CommandRuntimeError;
import exceptions.ExitException;
import exceptions.IllegalArguments;

import java.util.*;

/**
 * Класс обработки пользовательского ввода
 *
 */
public class InputManager {
    private final Printable console;
    private final CommandManager commandManager;

    public InputManager(ConsoleOutput consoleOutput, CommandManager commandManager) {
        this.console = consoleOutput;
        this.commandManager = commandManager;
    }

    /**
     * Работа с пользователем и выполнение команд
     */
    public void interactiveMode(){
        Scanner userScanner = UserScanner.getUserScanner();
        while (true) {
            try{
                if (!userScanner.hasNext()) throw new ExitException();
                String userCommand = userScanner.nextLine().trim() + " "; // прибавляем пробел, чтобы split выдал два элемента в массиве
                this.launch(userCommand.split(" ", 2));
                commandManager.addToHistory(userCommand);
            } catch (NoSuchElementException exception) {
                console.printError("Пользовательский ввод не обнаружен!");
            } catch (NoCommandException noCommand) {
                console.printError("Такой команды нет в списке");
            } catch (IllegalArguments e) {
                console.printError("Введены неправильные аргументы команды");
            } catch (CommandRuntimeError e) {
                console.printError("Ошибка при исполнении команды");
            } catch (ExitException exitException){
                console.println(OutputColors.toColor("До свидания!", OutputColors.YELLOW));
                return;
            }
        }
    }

    /**
     * Триггер выполнения команды из {@link CommandManager}
     * @param userCommand массив из 2 элементов, первый - название команды, второй - аргументы
     * @throws NoCommandException несуществующая команда
     * @throws ExitException команда привела к окончанию работы программы
     * @throws IllegalArguments команда содержит неверные аргументы
     * @throws CommandRuntimeError команда выдала ошибку во время выполнения
     */
    public void launch(String[] userCommand) throws NoCommandException, ExitException, IllegalArguments, CommandRuntimeError {
        if (userCommand[0].equals("")) return;
        commandManager.execute(userCommand[0], userCommand[1]);
    }
}
