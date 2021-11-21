package sorting;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class LongNumber extends DataTypesContainer{
    private List<Long> numberList;

    public LongNumber(SortingTypes sortType, File inputFile, File outputFile) throws FileNotFoundException {
        super(sortType, inputFile, outputFile);
        this.numberList = new ArrayList<>();
    }

    @Override
    public void getInput() {
        List<String> invalidLongs = new ArrayList<>();
        while (scanner.hasNext()) {
            String s = scanner.next();
            try {
                Long l = Long.parseLong(s);
                numberList.add(l);
            } catch (NumberFormatException e) {
                invalidLongs.add("\"" + s + "\" is not a long. It will be skipped");
            }
        }
        if(invalidLongs.size() > 0) {
            invalidLongs.forEach(System.out::println);
        }
        this.closeInput();
    }

    @Override
    public void printTotal() {
        String output = String.format("Total numbers: %d.",numberList.size());
        if(shouldPrint) {
            System.out.println(output);
        } else {
            resultList.add(output);
        }
    }


    protected String naturalSort() {
        Collections.sort(numberList);
        StringBuilder sb = new StringBuilder();
        sb.append("Sorted data: ");
        numberList.forEach(number -> {
            sb.append(number);
            sb.append(" ");
        });
        return sb.toString();
    }

    protected String countSort() {
        Map<Long,Integer> numberMap = new TreeMap<>();

        numberList.forEach(number -> {
            int frequency = Collections.frequency(numberList,number);
            numberMap.put(number,frequency);
        });


        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);

        Map<Long,Integer> newNumberMap = DataTypesContainer.valueSort(numberMap);

        newNumberMap.forEach((number,frequency) -> {
            double percentage = ((double) frequency / numberList.size()) * 100;
            formatter.format("%d: %d time(s), %.0f%%\n",number,frequency,percentage);
        });

        return sb.toString();
    }
}
