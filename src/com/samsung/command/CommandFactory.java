package com.samsung.command;

public class CommandFactory<T> {

    public Command getCommand(String line) {
        try {
            CommandAttr commandAttr = new CommandAttr();
            commandAttr.setAttrByLine(line);

            switch (commandAttr.getCommand()) {

                case "ADD":
                    return new AddCommand<T>(line);
                case "SCH":
                    return new SearchCommand<T>(commandAttr.getOption());
                case "MOD":
                    return new ModifyCommand<T>(commandAttr.getOption());
                case "DEL":
                    return new DeleteCommand<T>(commandAttr.getOption());

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return new Command<T>() {
            @Override
            public T execute() {
                return null;
            }
        };
    }

}
