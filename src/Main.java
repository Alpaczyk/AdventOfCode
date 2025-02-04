import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<List<Integer>> list = new ArrayList<>();
        try {
            File myObj = new File("C:/Users/micha/Desktop/AOC/input.txt");
            Scanner myReader = new Scanner(myObj);
            while(myReader.hasNextLine()) {
                String line = myReader.nextLine();
                int[] numbersInLine =  Arrays.stream(line.split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                List<Integer> tempList =  new ArrayList<>(n
            }
            myReader.close();
        }
        catch(FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}