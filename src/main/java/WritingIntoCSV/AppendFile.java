package WritingIntoCSV;

import BusinessLayer.BaseProduct;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AppendFile {

    public String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }

    public void givenDataArray_whenConvertToCSV_thenOutputCreated_Append(List<String[]> dataLines) throws IOException {
        FileWriter csvOutputFile = new FileWriter("E:\\PT2022_30225_Ciprian_Gabriel_Hirtescu_Assignment_4\\products.csv",true);
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            dataLines.stream()
                    .map(this::convertToCSV)
                    .forEach(pw::println);
        }

    }

    public String convertToCSV(String[] data) {
        return Stream.of(data)
                .map(this::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }

    public void givenDataArray_whenConvertToCSV_thenOutputCreated_Write(List<String[]> dataLines) throws IOException {
        FileWriter csvOutputFile = new FileWriter("E:\\PT2022_30225_Ciprian_Gabriel_Hirtescu_Assignment_4\\products.csv");
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            dataLines.stream()
                    .map(this::convertToCSV)
                    .forEach(pw::println);
        }

    }

   public String readFromCSVFile(){

       final String[] outputString = {""};

     /*  List<String> rezultat = new ArrayList<>()
       */

       Path path = Path.of("products.csv");

       try {

             Files.lines(path)
                   .skip(1)
                   .map(line -> {

                               String[] fields = line.split(",");
                               outputString[0] = outputString[0] +fields[0]+fields[1]+fields[2]+fields[3]+fields[4]+fields[5]+fields[6];
                               outputString[0] = outputString[0]+"\n";

                              return null;
                           }

                   );
       } catch (IOException e) {
           e.printStackTrace();
       }

       return outputString[0];
   }

}
