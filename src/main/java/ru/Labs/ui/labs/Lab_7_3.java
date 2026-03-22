package ru.Labs.ui.labs;

import java.util.List;
import ru.Labs.util.FileUtils;
import ru.Labs.core.algorithms.search.LcsSearch;
import ru.Labs.util.MessageManager;

public class Lab_7_3 implements LabRunner {
    private final String inputFile;

    public Lab_7_3(String inputFile) {
        this.inputFile = inputFile;
    }

    @Override
    public void start() {
        List<String> lines = FileUtils.readOrCreateFile(inputFile);
        if (lines == null) {
            return;
        }

        if (lines.size() < 2) {
            System.out.println(MessageManager.get("error.invalid_input.required.more"));
            return;
        }

        String x = lines.get(0).trim();
        String y = lines.get(1).trim();

        int result = LcsSearch.solve(x, y);

        System.out.println(result);
    }
}