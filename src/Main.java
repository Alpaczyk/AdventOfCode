import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<int[]> list = new ArrayList<>();
        try {
            File myObj = new File("C:/Users/kamil/OneDrive/Pulpit/AOC/input.txt");
            Scanner myReader = new Scanner(myObj);
            while(myReader.hasNextLine()) {
                String line = myReader.nextLine();
                int[] numbersInLine =  Arrays.stream(line.split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                list.add(numbersInLine);
            }
            myReader.close();
        }
        catch(FileNotFoundException e) {
            System.out.println("File not found");
        }
        int safe = 0;
        boolean increase;

        for (int i = 0; i < list.size(); i++) {
            increase = list.get(i)[0] < list.get(i)[1];
            for (int j = 0; j < list.get(i).length - 1; j++) {
                if(increase && list.get(i)[j] >= list.get(i)[j+1]) {
                    System.out.println(i);
                    safe--;
                    break;
                }
                else if(!increase && list.get(i)[j] <= list.get(i)[j+1]) {
                    System.out.println(i);
                    safe--;
                    break;
                }
                if(Math.abs(list.get(i)[j] - list.get(i)[j+1]) < 1 || Math.abs(list.get(i)[j] - list.get(i)[j+1]) > 3) {
                    System.out.println(i);
                    safe--;
                    break;
                }
            }
            safe++;
        }
        System.out.println(safe);
    }
}