package base;

import command_structure.*;

import java.util.HashMap;


/**
 * Class for working with our commands.
 */

public class CommandManager implements CommandKeeper {
    private HashMap<String, Converter<String, Boolean>> allCommands = new HashMap<>();
    private CommonCommand clearCommand;
    private CommonCommand exitCommand;
    private CommonCommand helpCommand;
    private CommonCommand infoCommand;
    private CommonCommand showCommand;
    private CommonCommand saveCommand;
    private AskCommand insertCommand;
    private CommonCommand removeByKeyCommand;
    private AskCommand updateByIdCommand;
    private CommonCommand averageOfHeightCommand;
    private CommonCommand removeLowerKeyCommand;
    private CommonCommand printFieldDescendingHeartCountCommand;
    private CommonCommand groupCountingByCategoryCommand;
    private AskCommand removeGreaterCommand;
    private AskCommand replaceIfLoweCommand;
    private CommonCommand executeScriptCommand;
    private Repeater repeater;


    {
        putCommands();
    }


    public static class Builder {
        private CommandManager newCommandManager;

        public Builder() {
            newCommandManager = new CommandManager();
        }

        public Builder withClearCommand(CommonCommand clearCommand) {
            newCommandManager.clearCommand = clearCommand;
            return this;
        }

        public Builder withExitCommand(CommonCommand exitCommand) {
            newCommandManager.exitCommand = exitCommand;
            return this;
        }

        public Builder withHelpCommand(CommonCommand helpCommand) {
            newCommandManager.helpCommand = helpCommand;
            return this;
        }

        public Builder withInfoCommand(CommonCommand infoCommand) {
            newCommandManager.infoCommand = infoCommand;
            return this;
        }

        public Builder withShowCommand(CommonCommand showCommand) {
            newCommandManager.showCommand = showCommand;
            return this;
        }


        public Builder withSaveCommand(CommonCommand saveCommand) {
            newCommandManager.saveCommand = saveCommand;
            return this;
        }

        public Builder withInsertCommand(AskCommand insertCommand) {
            newCommandManager.insertCommand = insertCommand;
            return this;
        }

        public Builder withRemoveByKeyCommand(CommonCommand removeByKeyCommand) {
            newCommandManager.removeByKeyCommand = removeByKeyCommand;
            return this;
        }

        public Builder withUpdateCommand(AskCommand updateCommand) {
            newCommandManager.updateByIdCommand = updateCommand;
            return this;
        }

        public Builder withAverageOfHeightCommand(CommonCommand averageOfHeightCommand) {
            newCommandManager.averageOfHeightCommand = averageOfHeightCommand;
            return this;
        }

        public Builder withRemoveLowerKeyCommand(CommonCommand removeLowerKeyCommand) {
            newCommandManager.removeLowerKeyCommand = removeLowerKeyCommand;
            return this;
        }

        public Builder withPrintFieldDescendingHeartCountCommand(CommonCommand printFieldDescendingHeartCountCommand) {
            newCommandManager.printFieldDescendingHeartCountCommand = printFieldDescendingHeartCountCommand;
            return this;
        }

        public Builder withGroupCountingByCategoryCommand(CommonCommand groupCountingByCategoryCommand) {
            newCommandManager.groupCountingByCategoryCommand = groupCountingByCategoryCommand;
            return this;
        }

        public Builder withRemoveGreaterCommand(AskCommand removeGreaterCommand) {
            newCommandManager.removeGreaterCommand = removeGreaterCommand;
            return this;
        }

        public Builder withReplaceIfLoweCommand(AskCommand replaceIfLoweCommand) {
            newCommandManager.replaceIfLoweCommand = replaceIfLoweCommand;
            return this;
        }

        public Builder withExecuteScriptCommand(CommonCommand executeScriptCommand) {
            newCommandManager.executeScriptCommand = executeScriptCommand;
            return this;
        }

        public Builder withRepeater(Repeater repeater) {
            newCommandManager.repeater = repeater;
            return this;
        }

        public CommandManager build() {
            return newCommandManager;
        }

    }

    /**
     * method puts links to the execution of methods
     */
    public void putCommands() {
        allCommands.put("help", this::help);
        allCommands.put("clear", this::clear);
        allCommands.put("exit", this::exit);
        allCommands.put("info", this::info);
        allCommands.put("show", this::show);
        allCommands.put("save", this::save);
        allCommands.put("insert", this::insert);
        allCommands.put("remove_key", this::removeByKey);
        allCommands.put("update", this::updateById);
        allCommands.put("average_of_height", this::averageOfHeight);
        allCommands.put("remove_lower_key", this::removeLower);
        allCommands.put("print_field_descending_heart_count", this::printFieldHeart);
        allCommands.put("group_counting_by_category", this::groupCountingByCategory);
        allCommands.put("remove_greater", this::removeGreater);
        allCommands.put("replace_if_lowe", this::replaceIfLowe);
        allCommands.put("execute_script", this::executeScript);
    }


    /**
     * Executes needed command.
     *
     * @param argument Its argument.
     * @return Command exit status.
     */

    @Override
    public boolean clear(String argument) {
        return clearCommand.execute(argument);
    }

    /**
     * Executes needed command.
     *
     * @param argument Its argument.
     * @return Command exit status.
     */
    @Override
    public boolean exit(String argument) {
        return exitCommand.execute(argument);
    }

    /**
     * Prints info about the all commands.
     *
     * @param argument Its argument.
     * @return Command exit status.
     */
    @Override
    public boolean help(String argument) {
        return helpCommand.execute(argument);
    }

    /**
     * Executes needed command.
     *
     * @param argument Its argument.
     * @return Command exit status.
     */
    @Override
    public boolean info(String argument) {
        return infoCommand.execute(argument);
    }

    /**
     * Executes needed command.
     *
     * @param argument Its argument.
     * @return Command exit status.
     */
    @Override
    public boolean show(String argument) {
        return showCommand.execute(argument);
    }

    /**
     * Executes needed command.
     *
     * @param argument Its argument.
     * @return Command exit status.
     */
    @Override
    public boolean save(String argument) {
        return saveCommand.execute(argument);
    }


    /**
     * Executes needed command.
     *
     * @param argument Its argument.
     * @return Command exit status.
     */
    @Override
    public boolean insert(String argument) {
        return insertCommand.execute(argument, repeater);
    }

    /**
     * Executes needed command.
     *
     * @param argument Its argument.
     * @return Command exit status.
     */
    @Override
    public boolean removeByKey(String argument) {
        return removeByKeyCommand.execute(argument);
    }


    /**
     * Executes needed command.
     *
     * @param argument Its argument.
     * @return Command exit status.
     */
    @Override
    public boolean updateById(String argument) {
        return updateByIdCommand.execute(argument, repeater);
    }

    /**
     * Executes needed command.
     *
     * @param argument Its argument.
     * @return Command exit status.
     */
    @Override
    public boolean averageOfHeight(String argument) {
        return averageOfHeightCommand.execute(argument);
    }

    /**
     * Executes needed command.
     *
     * @param argument Its argument.
     * @return Command exit status.
     */
    @Override
    public boolean removeLower(String argument) {
        return removeLowerKeyCommand.execute(argument);
    }

    /**
     * Executes needed command.
     *
     * @param argument Its argument.
     * @return Command exit status.
     */
    @Override
    public boolean printFieldHeart(String argument) {
        return printFieldDescendingHeartCountCommand.execute(argument);
    }

    /**
     * Executes needed command.
     *
     * @param argument Its argument.
     * @return Command exit status.
     */
    @Override
    public boolean groupCountingByCategory(String argument) {
        return groupCountingByCategoryCommand.execute(argument);
    }


    @Override
    public HashMap<String, Converter<String, Boolean>> getAllCommands() {
        return allCommands;
    }

    /**
     * Executes needed command.
     *
     * @param argument Its argument.
     * @return Command exit status.
     */
    @Override
    public boolean removeGreater(String argument) {
        return removeGreaterCommand.execute(argument, repeater);
    }

    /**
     * Executes needed command.
     *
     * @param argument Its argument.
     * @return Command exit status.
     */
    @Override
    public boolean replaceIfLowe(String argument) {
        return replaceIfLoweCommand.execute(argument, repeater);
    }

    /**
     * Executes needed command.
     *
     * @param argument Its argument.
     * @return Command exit status.
     */
    @Override
    public boolean executeScript(String argument) {
        return executeScriptCommand.execute(argument);
    }
}
