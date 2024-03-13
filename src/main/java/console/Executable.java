package console;

import exceptions.CommandRuntimeError;
import exceptions.ExitException;
import exceptions.IllegalArguments;

/**
 * Интерфейс для исполняемых команд
 */
public interface Executable {
    void execute (String args) throws CommandRuntimeError, ExitException, IllegalArguments;

}
