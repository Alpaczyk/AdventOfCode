import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<int[]> list = new ArrayList<>();
        try {
            File myObj = new File("C:/Users/kamil/OneDrive/Pulpit/AOC/input.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                int[] numbersInLine = Arrays.stream(line.split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                list.add(numbersInLine);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        List<int[][]> listDamp = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            int[][] damp = new int[list.get(i).length + 1][list.get(i).length - 1];
            damp[0] = list.get(i);
            for (int j = 0; j < list.get(i).length; j++) {
                int finalI = i;
                int finalJ = j;
                int[] tempList = Arrays.stream(list.get(i))
                        .filter(a -> a != list.get(finalI)[finalJ])
                        .toArray();
                damp[j + 1] = tempList;
            }
            listDamp.add(damp);
        }
        int safe = 0;
        boolean increase;
        boolean help = false;
        for (int[] ints : list) {
            increase = ints[0] < ints[1];
            for (int j = 0; j < ints.length - 1; j++) {
                if (increase && ints[j] >= ints[j + 1]) {
                    if (!help) {
                        increase = false;
                        help = true;
                    }
                    else{
                        safe--;
                        break;
                    }
                }else if (!increase && ints[j] <= ints[j + 1]) {
                    if (!help) {
                        increase = true;
                        help = true;
                    }
                    else{
                        safe--;
                        break;
                    }
                }
                if (Math.abs(ints[j] - ints[j + 1]) < 1 || Math.abs(ints[j] - ints[j + 1]) > 3) {
                    if (!help) {
                        help = true;
                    }
                    else{
                        safe--;
                        break;
                    }
                }
            }
            help = false;
            safe++;
        }
        System.out.println(safe);
    }
}