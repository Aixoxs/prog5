package ru.ifmo.se.pult;

import java.io.*;
import java.text.ParseException;
import java.util.*;
import java.util.regex.Pattern;

//Invoker
public class Controller {
    private final HashMap<Pattern, Command> commandMap = new HashMap<>();
    Command help;
    Command info;
    Command show;
    Command add;
    Command update;
    Command removeById;
    Command clear;
    Command save;
    Command executeScript;
    Command exit;
    Command removeGreater;
    Command removeLower;
    Command history;
    Command maxByGenre;
    Command filterLessThan;
    Command printDescending;

    public Controller(Command help, Command info, Command show, Command add, Command updateId, Command removeById, Command clear, Command save, Command executeScript, Command exit, Command removeGreater, Command removeLower, Command history, Command maxByGenre, Command filterLessThan, Command printDescending) {
        this.help = help;
        this.info = info;
        this.show = show;
        this.add = add;
        this.update = updateId;
        this.removeById = removeById;
        this.clear = clear;
        this.save = save;
        this.executeScript = executeScript;
        this.exit = exit;
        this.removeGreater = removeGreater;
        this.removeLower = removeLower;
        this.history = history;
        this.maxByGenre = maxByGenre;
        this.filterLessThan = filterLessThan;
        this.printDescending = printDescending;
    }

    public void start(InputStream inputStream) throws ParseException {
        boolean exitFlag = true;
        Scanner in = new Scanner(inputStream);

        while (exitFlag && in.hasNextLine()) {

            CommandName command = Reader.readCommand(in);

            switch (command) {
                case HELP:
                    help.execute("");
                    break;
                case INFO:
                    info.execute("");
                    break;
                case UPDATE:
                    update.execute(Reader.readArgument());
                    break;
                case REMOVE_BY_ID:
                    removeById.execute(Reader.readArgument());
                    break;
                case FILTER_LESS_THEN_NUMBER_OF_PARTICIPANTS:
                    filterLessThan.execute(Reader.readArgument());
                    break;
                case ADD:
                    add.execute(Reader.readCollectionObject(in));
                    break;
                case REMOVE_LOWER:
                    removeLower.execute("");
                    break;
                case REMOVE_GREATER:
                    removeGreater.execute("");
                    break;
                case EXECUTE_SCRIPT:
                    try {
                        InputStream fileInputStream = new FileInputStream(Reader.readScriptName());
                        this.start(fileInputStream);
                        fileInputStream.close();
                    } catch (NoSuchElementException ex) {
                        System.out.println("Конец скрипта");
                    } catch (FileNotFoundException ex) {
                        System.out.println("Файл не найден");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case SHOW:
                    show.execute("");
                    break;
                case CLEAR:
                    clear.execute("");
                    break;
                case SAVE:
                    save.execute("");
                    break;
                case EXIT:
                    exit.execute("");
                    exitFlag = false;
                    break;
                case HISTORY:
                    history.execute("");
                    break;
                case MAX_BY_GENRE:
                    maxByGenre.execute("");
                    break;
                case PRINT_DESCENDING:
                    printDescending.execute("");
                    break;
                case ERROR:
                    System.out.println("Неправильный ввод");
                    break;

            }
        }
    }
}
