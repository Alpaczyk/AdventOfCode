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
        boolean increase2;
        boolean help = false;
        boolean change;
        boolean change2;
        for (int[] ints : list) {
            increase = ints[0] < ints[1];
            increase2 = ints[1] < ints[2];
            change = Math.abs(ints[0] - ints[1]) < 1 || Math.abs(ints[0] - ints[1]) > 3;
            change2 = Math.abs(ints[1] - ints[2]) < 1 || Math.abs(ints[1] - ints[2]) > 3;
            if(increase != increase2) {
                increase = increase2;
                help = true;
                remove(ints, 0);
            }
            if(change && !change2) {
                help = true;
                remove(ints,0);
            }
            for (int j = 0; j < ints.length - 1; j++) {
                if (increase && ints[j] >= ints[j + 1]) {
                    if (!help) {
                        help = true;
                        ints = remove(ints, j+1);
                    }
                    else{
                        safe--;
                        break;
                    }
                }else if (!increase && ints[j] <= ints[j + 1]) {
                    if (!help) {
                        help = true;
                        ints = remove(ints, j+1);
                    }
                    else{
                        safe--;
                        break;
                    }
                }
                if(j + 1 < ints.length){
                    if (Math.abs(ints[j] - ints[j + 1]) < 1 || Math.abs(ints[j] - ints[j + 1]) > 3) {
                        if (!help) {
                            help = true;
                            ints = remove(ints, j+1);
                        }
                        else{
                            safe--;
                            break;
                        }
                    }
                }
            }
            help = false;
            safe++;
        }
        System.out.println(safe);
    }

    public static int[] remove(int[] array, int index) {
        int[] result = new int[array.length - 1];
        System.arraycopy(array, 0, result, 0, index);
        System.arraycopy(array, index + 1, result, index, result.length - index);
        return result;
    }
}