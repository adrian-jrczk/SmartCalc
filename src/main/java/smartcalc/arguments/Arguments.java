package smartcalc.arguments;

import com.beust.jcommander.Parameter;

class Arguments {

    @Parameter(names = {"--type", "-t"}, required = true, validateWith = ModeValidator.class)
    public String calculationType;

    @Parameter(names = {"--matrix-operation", "-m"}, validateWith = MatrixOperationValidator.class)
    public String matrixOperation = "";

    @Parameter(names = {"--input-file", "-i"}, required = true)
    public String inputFileName;

    @Parameter(names = {"--output-file", "-o"})
    public String outputFileName = "results.txt";
}
