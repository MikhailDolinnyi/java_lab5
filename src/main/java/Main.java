import console.ConsoleOutput;
import console.OutputColors;
import commands.*;

import exceptions.ExitException;
import managers.CollectionManager;
import managers.FileManager;
import managers.CommandManager;
import managers.InputManager;

import java.util.List;


/**
 * Main
 * Реализует запуск программы
 */
public class Main {
    public static void main(String[] args) {
        ConsoleOutput consoleOutput = new ConsoleOutput();
        CollectionManager collectionManager = new CollectionManager();
        FileManager fileManager;

        // Проверяем наличие аргументов командной строки
        if (args.length > 0 && args[0] != null && !args[0].isEmpty()) {
            // Первый аргумент командной строки используется в качестве пути к файлу
            fileManager = new FileManager(consoleOutput, collectionManager, args[0]);
        } else {
            consoleOutput.printError("Лее,друже, необходимо указать путь к файлу в качестве аргумента командной строки.");
            return;
        }


//        ByteBuffer buffer = ByteBuffer.allocate(10); // Подкласс, allocate - выделение памяти
//        // в allocate создается HeapByteBuffer, который хранит данные в обычном массиве
//
//        // MemorySegment - класс для работы с памятью?
//        buffer.put((byte) 1);
//        buffer.put((byte) 2);
//        buffer.put((byte) 3);
////        put(): Добавление данных в буфер.
////        get(): Извлечение данных из буфера.
////        flip(): Перевод буфера в режим чтения.
////        clear(): Очистка буфера для повторного использования.



        // HashMap - каждый элемент - бакет, которая хранит LinkedList со значением, список состоит из одного элемента,
        // он ссылается на null, чтобы избежать коллизии (новое значение запишется в начало, сохранив старое)


        // Обработка Выхода
        Runtime.getRuntime().addShutdownHook(new Thread(() -> consoleOutput.println(OutputColors.toColor("Всё давай пака братиш!", OutputColors.YELLOW))));


        CommandManager commandManager = new CommandManager();
        try {
            fileManager.findFile();
            fileManager.createObjects();
        } catch (ExitException e) {
            //consoleOutput.println(OutputColors.toColor("До свидания!", OutputColors.PURPLE));
            return;
        }


        commandManager.addCommand(List.of(
                new Help(consoleOutput, commandManager),
                new Info(consoleOutput, collectionManager),
                new Show(consoleOutput, collectionManager),
                new AddElement(consoleOutput, collectionManager),
                new UpdateId(consoleOutput, collectionManager),
                new RemoveById(consoleOutput, collectionManager),
                new Clear(consoleOutput, collectionManager),
                new Save(consoleOutput, fileManager),
                new ExecuteScript(consoleOutput, fileManager, commandManager),
                new Exit(),
                new RemoveHead(consoleOutput, collectionManager),
                new AddIfMin(consoleOutput, collectionManager),
                new RemoveLower(consoleOutput, collectionManager),
                new RemoveAllByWeaponType(consoleOutput, collectionManager),
                new AverageOfHeight(consoleOutput, collectionManager),
                new PrintAsceding(consoleOutput, collectionManager)

        ));
        new InputManager(consoleOutput, commandManager).interactiveMode();


    }
}
