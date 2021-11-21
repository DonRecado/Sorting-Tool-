package sorting;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Line extends DataTypesContainer{
    private List<String> linesList;

    public Line(SortingTypes sortType, File inputFile, File outputFile) throws FileNotFoundException {
        super(sortType, inputFile, outputFile);
        this.linesList = new ArrayList<>();
    }

    @Override
    public void getInput() {
        while (scanner.hasNextLine()) {
            linesList.add(scanner.nextLine());
        }
        this.closeInput();
    }

    @Override
    public void printTotal() {
        String output = String.format("Total lines: %d.",linesList.size());
        if(shouldPrint) {
            System.out.println(output);
        } else {
            resultList.add(output);
        }
    }


    protected String naturalSort() {
        Collections.sort(linesList);
        StringBuilder sb = new StringBuilder();
        sb.append("Sorted data: \n");
        linesList.forEach(number -> {
            sb.append(number);
            sb.append("\n");
        });
        return sb.toString();
    }

    protected String countSort() {
        Map<String,Integer> linesMap = new TreeMap<>();

        linesList.forEach(number -> {
            int frequency = Collections.frequency(linesList,number);
            linesMap.put(number,frequency);
        });


        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);

        Map<String,Integer> newLinesMap = DataTypesContainer.valueSort(linesMap);

        newLinesMap.forEach((number,frequency) -> {
            double percentage = ((double) frequency / linesList.size()) * 100;
            formatter.format("%s: %d time(s), %.0f%%\n",number,frequency,percentage);
        });

        return sb.toString();
    }

}
