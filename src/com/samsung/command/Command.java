package com.samsung.command;

import com.samsung.option.CommandOption;

public abstract class Command {
    private CommandOption commandOption;
    public abstract void execute();
}
