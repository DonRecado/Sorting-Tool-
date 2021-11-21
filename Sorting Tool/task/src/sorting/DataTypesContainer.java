package sorting;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

abstract class DataTypesContainer {
    protected final SortingTypes sortType;
    protected final Scanner scanner;
    protected final File inputFile;
    protected final File outputFile;
    protected final List<String> resultList;
    protected boolean shouldPrint;

    public DataTypesContainer(SortingTypes sortType, File inputFile, File outputFile) throws FileNotFoundException {
        this.sortType = sortType;
        this.inputFile = inputFile;
        if (inputFile != null && inputFile.exists()) {
            this.scanner = new Scanner(inputFile);
        } else {
            this.scanner = new Scanner(System.in);
        }
        this.outputFile = outputFile;
        resultList = new ArrayList<>();

        if (outputFile != null) {
            shouldPrint = false;
        } else {
            shouldPrint = true;
        }
    }

    public abstract void getInput();

    public abstract void printTotal();

    public void printSort() {
        String output;
        if (this.sortType == SortingTypes.NATURAL) {
            output = naturalSort();
        } else {
            output = countSort();
        }

        if (shouldPrint) {
            System.out.println(output);
        } else {
            resultList.add(output);
        }
    }

    ;

    protected abstract String naturalSort();

    protected abstract String countSort();

    public static <K, V extends Comparable<V>> Map<K, V> valueSort(final Map<K, V> map) {
        Comparator<K> valueComparator = (k1, k2) -> {
            int comp = map.get(k1).compareTo(
                    map.get(k2));
            if (comp == 0)
                return 1;
            else
                return comp;
        };

        Map<K, V> sorted = new TreeMap<K, V>(valueComparator);

        sorted.putAll(map);

        return sorted;
    }

    public void closeInput() {
        scanner.close();
    }

    public void writeToFile() throws IOException {
        FileWriter writer = new FileWriter(outputFile);
        resultList.forEach(result -> {
            try {
                writer.write(result);
                writer.write("\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        writer.close();

    }

}
