import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;


public class DensityCalculator {
        private Scanner input;
        private Writer output;

        public void openInputFile() {
                try {
                        input = new Scanner(new File("CountryInfo.txt"));
                } catch (Exception e) {
                        e.getMessage();
                }
        }
        public void closeInputFile(){
                input.close();
        }
        public void readInputAndWriterOutputFile() throws IOException {
                File output = new File("Output.txt");
                output.createNewFile();
                FileWriter fileWriter = new FileWriter(output);
                BufferedWriter writer = new BufferedWriter(fileWriter);
                writer.write("Country\tArea Sq Km\tPopulation\tDensity");
                writer.newLine();

                int skipFirst1 = 0;
                Number population = 0;
                Number sq_km = 0;
                double density = 0.0;
                ArrayList<Double> densityList = new ArrayList<>();
                while (input.hasNext()) {
                        int skipFirst2 = 0;
                        String[] tabbedex = input.nextLine().split("\\t");
                        if (skipFirst1 == 0) {
                                skipFirst1++;
                                continue;
                        }
                        for (String s : tabbedex) {
                                writer.write(s + "\t");
                        }
                        for (String s : tabbedex) {
                                if (skipFirst2 == 0) {
                                        skipFirst2++;
                                        continue;
                                }
                                if (skipFirst2 == 1) {
                                        try {
                                                sq_km = NumberFormat.getNumberInstance(java.util.Locale.US).parse(s);
                                        } catch (ParseException e) {
                                                e.printStackTrace();
                                        }
                                        skipFirst2++;
                                        continue;
                                }
                                if (skipFirst2 == 2) {
                                        try {
                                                population = NumberFormat.getNumberInstance(java.util.Locale.US).parse(s);
                                        } catch (ParseException e) {
                                                e.printStackTrace();
                                        }
                                }
                        }
                        density = (population.longValue() / sq_km.longValue());
                        densityList.add(density);
                        String densityAsString = String.valueOf(density);
                        writer.write(densityAsString);
                        writer.newLine();
                }
                writer.close();
        }

        public void closeOutputFile() throws IOException {
                output.close();
        }

        public static void main(String[] args) throws IOException {
                DensityCalculator denscal = new DensityCalculator();
                denscal.openInputFile();
                denscal.readInputAndWriterOutputFile();
        }
}
