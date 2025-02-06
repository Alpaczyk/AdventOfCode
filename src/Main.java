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
        int safe = 0;
        boolean increase;
        int tempSafe = 0;
        List<int[][]> listDamp = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            int[][] damp = new int[list.get(i).length + 1][list.get(i).length - 1];
            damp[0] = list.get(i);
            for (int l = 0; l < list.get(i).length; l++) {
                int finalI = i;
                int finalJ = l;
                tempSafe = 0;
                int[] tempList = Arrays.stream(list.get(i))
                        .filter(a -> a != list.get(finalI)[finalJ])
                        .toArray();
                System.out.println(Arrays.toString(tempList));
                increase = tempList[0] < tempList[1];
                for (int j = 0; j < tempList.length - 1; j++) {
                    if (increase && tempList[j] >= tempList[j + 1]) {
                        tempSafe--;
                        break;

                    }else if (!increase && tempList[j] <= tempList[j + 1]) {

                        tempSafe--;
                        break;

                    }
                    if (Math.abs(tempList[j] - tempList[j + 1]) < 1 || Math.abs(tempList[j] - tempList[j + 1]) > 3) {
                        tempSafe--;
                        break;
                    }
                }
                tempSafe++;
            }
            if (tempSafe > 0) {
                safe++;
            }
        }

        System.out.println(safe);
    }
}