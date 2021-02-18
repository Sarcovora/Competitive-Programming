import java.util.ArrayList;

public class Permutations {
    static void permute (ArrayList<String> chosen, ArrayList<String> leftOver) {
        if (leftOver.size() == 1) { // if there is only one choice left in leftOver
            for (int i = 0; i < chosen.size(); i++) {
                System.out.print(chosen.get(i));
            }
            System.out.println(leftOver.get(0));
        }
        for (int i = 0; i < leftOver.size(); i++) {
            ArrayList<String> chosentemp = (ArrayList<String>) chosen.clone();
            ArrayList<String> leftTemp = (ArrayList<String>) leftOver.clone();
            chosentemp.add(leftTemp.remove(i));
            permute(chosentemp, leftTemp);
        }
    }
    public static void main (String[] args) {
        ArrayList<String> leftOver = new ArrayList<>();
        leftOver.add("A");
        leftOver.add("B");
        leftOver.add("C");
        permute(new ArrayList<String>(), leftOver);
    }
}