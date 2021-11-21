package sorting;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Word extends DataTypesContainer{
    private List<String> wordList;

    public Word(SortingTypes sortType, File inputFile, File outputFile) throws FileNotFoundException {
        super(sortType, inputFile, outputFile);
        this.wordList = new ArrayList<>();
    }

    @Override
    public void getInput() {
        while (scanner.hasNext()) {
            wordList.add(scanner.next());
        }
        this.closeInput();
    }

    @Override
    public void printTotal() {
        String output = String.format("Total words: %d.", wordList.size());
        if(shouldPrint) {
            System.out.println(output);
        } else {
            resultList.add(output);
        }
    }


    protected String naturalSort() {
        Collections.sort(wordList);
        StringBuilder sb = new StringBuilder();
        sb.append("Sorted data: \n");
        wordList.forEach(number -> {
            sb.append(number);
            sb.append("\n");
        });
        return sb.toString();
    }

    protected String countSort() {
        Map<String,Integer> wordMap = new TreeMap<>();

        wordList.forEach(number -> {
            int frequency = Collections.frequency(wordList,number);
            wordMap.put(number,frequency);
        });


        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);

        Map<String,Integer> newWordMap = DataTypesContainer.valueSort(wordMap);

        newWordMap.forEach((word,frequency) -> {
            double percentage = ((double) frequency / wordList.size()) * 100;
            formatter.format("%s: %d time(s), %.0f%%\n",word,frequency,percentage);
        });

        return sb.toString();
    }

}
