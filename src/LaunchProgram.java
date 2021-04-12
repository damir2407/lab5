import base.*;
import com.google.gson.Gson;
import command_structure.*;
import file_works.*;
import messenger.*;
import validate.FieldsValidation;
import validate.Result;
import validate.ResultKeeper;
import validate.Validator;

import java.util.Scanner;

public class LaunchProgram {

    public void startProgram() {
        PrintKeeper printMachine = new PrintMachine();
        Messenger messenger = (new LanguageKeeper(new RussianMessenger(), new EnglishMessenger(), printMachine).inputLanguage());
        if (messenger == null) System.exit(0);
        Scanner scanner = new Scanner(System.in);
        ResultKeeper result = new Result();
        Validator fieldsValidation = new FieldsValidation(messenger, result);
        PollKeeper poll = new Poll(scanner, fieldsValidation, messenger, printMachine);
        FileFieldsChecker fileFieldsChecker = new FileFieldsChecker(fieldsValidation, messenger, result);
        FileKeeper fileManager = new FileManager(System.getenv("My_Path"), messenger, printMachine);
        Transformer transform = new Transform(fileManager, new Gson(), fileFieldsChecker, printMachine, messenger);
        CollectionKeeper collectionManager = new CollectionManager(fileManager, messenger, transform);
        collectionManager.convertToCollection();
        SetKeeper marineSetter = new MarineSetter(poll);
        Repeater consoleRepeater = new ConsoleRepeater(collectionManager, marineSetter);
        Executor executor = new CommandExecutor(setCommands(collectionManager, consoleRepeater, messenger, printMachine), poll, messenger, printMachine);
        Reader commandReader = new CommandReader(scanner);
        commandReader.interactiveMode(executor);
    }

    public static CommandKeeper setCommands(CollectionKeeper collectionManager, Repeater consoleRepeater, Messenger messenger, PrintKeeper printMachine) {
        return new CommandManager.Builder()
                .withClearCommand(new ClearCommand(collectionManager, messenger, printMachine))
                .withExitCommand(new ExitCommand(messenger, printMachine))
                .withHelpCommand(new HelpCommand(messenger, printMachine))
                .withInfoCommand(new InfoCommand(collectionManager, messenger, printMachine))
                .withShowCommand(new ShowCommand(collectionManager, messenger, printMachine))
                .withSaveCommand(new SaveCommand(collectionManager, messenger, printMachine))
                .withInsertCommand(new InsertCommand(collectionManager, messenger, printMachine))
                .withRemoveByKeyCommand(new RemoveKeyCommand(collectionManager, messenger, printMachine))
                .withUpdateCommand(new UpdateCommand(collectionManager, messenger, printMachine))
                .withAverageOfHeightCommand(new AverageOfHeightCommand(collectionManager, messenger, printMachine))
                .withRemoveLowerKeyCommand(new RemoveLowerKey(collectionManager, messenger, printMachine))
                .withPrintFieldDescendingHeartCountCommand(new PrintFieldDescendingHeartCountCommand(collectionManager, messenger, printMachine))
                .withGroupCountingByCategoryCommand(new GroupCountingByCategoryCommand(collectionManager, messenger, printMachine))
                .withRemoveGreaterCommand(new RemoveGreaterCommand(collectionManager, messenger, printMachine))
                .withReplaceIfLoweCommand(new ReplaceIfLoweCommand(collectionManager, messenger, printMachine))
                .withExecuteScriptCommand(new ExecuteScriptCommand(messenger, printMachine))
                .withRepeater(consoleRepeater)
                .build();
    }


}
