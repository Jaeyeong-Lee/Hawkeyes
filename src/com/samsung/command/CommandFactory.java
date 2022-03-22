package com.samsung.command;

public class CommandFactory<T> {

    public Command getCommand(String line) {
        try {
            CommandAttr.setAttrByLine(line);

            switch (CommandAttr.command) {

                case "ADD":
                    return new AddCommand<T>(line);
                case "SCH":
                    return new SearchCommand<T>(CommandAttr.option);
                case "MOD":
                    return new ModifyCommand<T>(CommandAttr.option);
                case "DEL":
                    return new DeleteCommand<T>(CommandAttr.option);

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
