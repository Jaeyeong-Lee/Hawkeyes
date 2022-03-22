package com.samsung.iomanager;

import java.util.List;

public interface IOManager<T> {

    List<T> readInput(String fileName);

    void writeOutput(String fileName, List<T> fileLine);
}
