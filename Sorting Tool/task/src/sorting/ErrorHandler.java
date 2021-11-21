package sorting;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ErrorHandler {
    private final String[] arguments;
    private SortingTypes sortingType;
    private DataTypes dataType;
    private final List<String> errors;
    private final List<String> information;
    private File inputFile;
    private File outputFile;

    public ErrorHandler(String[] arguments) {
        this.arguments = arguments;
        this.sortingType = SortingTypes.NATURAL;
        this.errors = new ArrayList<>();
        this.information = new ArrayList<>();
        this.inputFile = null;
        this.outputFile = null;
        setArguments();
    };

    private void setArguments() {
        for (int i = 0; i < arguments.length; i+=2) {
            if (arguments[i].equals("-sortingType")) {
                if(i + 1 < arguments.length) {
                    setSortingType(arguments[i + 1]);
                } else {
                    this.errors.add("No sorting type defined!");
                }
            }else if(arguments[i].equals("-dataType")) {
                if(i + 1 < arguments.length) {
                    setDataType(arguments[i+1]);
                } else {
                    this.errors.add("No data type defined!");
                }
            }else if(arguments[i].equals("-inputFile")) {
                inputFile = new File(arguments[i+1]);
            }else if(arguments[i].equals("-outputFile")) {
                outputFile = new File(arguments[i+1]);
            } else  {
                information.add("\"" + arguments[i] + "\" is not a valid parameter. It will be skipped.");
            }
        }
        if(dataType == null)  {
            dataType = DataTypes.LINE;
        }
    }

    private void setSortingType(String arg) {
        for (SortingTypes s : SortingTypes.values()) {
            if (s.name().equals(arg.toUpperCase())) {
                this.sortingType = s;
                return;
            }
        }
        this.errors.add("No sorting type defined!");
    }

    private void setDataType(String arg) {
        for (DataTypes d : DataTypes.values()) {
            if (d.name().equals(arg.toUpperCase())) {
                this.dataType = d;
                return;
            }
        }

        this.errors.add("No data type defined!");
    }

    public SortingTypes getSortingType() {
        return sortingType;
    }

    public DataTypes getDataType() {
        return dataType;
    }

    public List<String> getErrors() {
        return errors;
    }

    public List<String> getInformation() {
        return information;
    }

    public File getInputFile() {
        return inputFile;
    }

    public File getOutputFile() {
        return outputFile;
    }
}

