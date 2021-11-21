package sorting;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

enum DataTypes {
    LONG,
    WORD,
    LINE,
    NUMBER
}

enum SortingTypes {
    NATURAL,
    BYCOUNT
}

public class Main {
    public static void main(final String[] args) {
        ErrorHandler handler = new ErrorHandler(args);
        if(handler.getErrors().size() == 0) {
            if(handler.getInformation().size() > 0) {
                handler.getInformation().forEach(System.out::println);
            }


            DataTypesContainer converter;
            try {
                switch (handler.getDataType()) {
                    case LINE:
                        converter = new Line(handler.getSortingType(), handler.getInputFile(), handler.getOutputFile());
                        break;
                    case LONG:
                        converter = new LongNumber(handler.getSortingType(), handler.getInputFile(), handler.getOutputFile());
                        break;
                    case WORD:
                        converter = new Word(handler.getSortingType(), handler.getInputFile(), handler.getOutputFile());
                        break;
                    case NUMBER:
                        converter = new Numbers(handler.getSortingType(), handler.getInputFile(), handler.getOutputFile());
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + handler.getDataType());
                }

                converter.getInput();
                converter.printTotal();
                converter.printSort();

                if(handler.getOutputFile() != null) {
                    try {
                        converter.writeToFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


            } catch (FileNotFoundException|NullPointerException e) {
                e.printStackTrace();
                System.out.println("File not found!");
            }
        } else {
            handler.getErrors().forEach(System.out::println);
        }

    }



}


