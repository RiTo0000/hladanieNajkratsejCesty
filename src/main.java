import java.util.ArrayList;
import java.util.Scanner;


public class main {

    public static void main(String[] args) {
        String start;
        String end;
        int startRiadok;
        int startStlpec;
        int endRiadok;
        int endStlpec ;
        int[][] hraciePole = new int[8][8]; //koncovy bod bude mat hodnotu 2 a bod do ktoreho som sa dostal bude mat hodnotu 1
        Move[][] hraciePoleMoves = new Move[8][8];
        ArrayList<String> cesty = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        System.out.print("Zadajte pociatocne suradnice: ");
        start = sc.nextLine();
        startStlpec = getNumberFromLetter(start.substring(0, 1));
        if (startStlpec == 8)
            System.err.println("Zadane pismeno nieje spravne skontroluj si ci pismeno je napisane velkym a ci je z rozsahu");
        startRiadok = Integer.parseInt(start.substring(1)) - 1;
        System.out.print("Zadajte konecne suradnice: ");
        end = sc.nextLine();
        endStlpec = getNumberFromLetter(end.substring(0, 1));
        if (startStlpec == 8)
            System.err.println("Zadane pismeno nieje spravne skontroluj si ci pismeno je napisane velkym a ci je z rozsahu");
        endRiadok = Integer.parseInt(end.substring(1)) - 1;


        hraciePole[startRiadok][startStlpec] = 1;
        hraciePole[endRiadok][endStlpec] = 2;
        hraciePoleMoves[startRiadok][startStlpec] = new Move(startRiadok, startStlpec, 0);
        hraciePoleMoves[endRiadok][endStlpec] = new Move(0, 0, 60000);

        findAllWaysDifferentMoves(hraciePole,startRiadok,startStlpec,hraciePoleMoves);

        System.out.println("Dlzka najkratsej cesty: " + hraciePoleMoves[endRiadok][endStlpec].getPocetKrokov());
        boolean koniecCesty = false;
        cesty.add(getLetterFromNumber(endStlpec) + "" + (endRiadok + 1) + " ");
        int predosleSuradniceRiadok = endRiadok;
        int predosleSuradniceStlpec = endStlpec;
        while (!koniecCesty) {
            int x = predosleSuradniceRiadok;
            int y = predosleSuradniceStlpec;
            predosleSuradniceRiadok = hraciePoleMoves[x][y].getStartRiadok();
            predosleSuradniceStlpec = hraciePoleMoves[x][y].getStartStlpec();
            cesty.add(getLetterFromNumber(predosleSuradniceStlpec) + "" + (predosleSuradniceRiadok + 1) + " ");
            if (predosleSuradniceRiadok == 0 && predosleSuradniceStlpec == 0)
                koniecCesty = true;
        }
        for (int i = cesty.size() ; i > 0 ; i--) {
            System.out.print(cesty.get(i - 1));
        }


    }

    public static void findAllWays(int[][] hraciePole,int tempRiadok, int tempStlpec, int startRiadok, int startStlpec, Move[][] hraciePoleMoves) {
        if (tempRiadok >=0 && tempRiadok < 8 && tempStlpec >= 0 && tempStlpec <8) {
            if (hraciePole[tempRiadok][tempStlpec] == 2)
            {
                if (hraciePoleMoves[tempRiadok][tempStlpec].getPocetKrokov() > hraciePoleMoves[startRiadok][startStlpec].getPocetKrokov() + 1) {
                    hraciePoleMoves[tempRiadok][tempStlpec] = new Move(startRiadok, startStlpec, hraciePoleMoves[startRiadok][startStlpec].getPocetKrokov() + 1);
                }
            }
            else {
                if (hraciePole[tempRiadok][tempStlpec] != 1)
                {
                    hraciePole[tempRiadok][tempStlpec] = 1;
                    hraciePoleMoves[tempRiadok][tempStlpec] = new Move(startRiadok, startStlpec, hraciePoleMoves[startRiadok][startStlpec].getPocetKrokov() + 1);
                    findAllWaysDifferentMoves(hraciePole, tempRiadok, tempStlpec, hraciePoleMoves);
                }
                else {
                    if (hraciePoleMoves[tempRiadok][tempStlpec].getPocetKrokov() > hraciePoleMoves[startRiadok][startStlpec].getPocetKrokov() + 1) {
                        hraciePoleMoves[tempRiadok][tempStlpec] = new Move(startRiadok, startStlpec, hraciePoleMoves[startRiadok][startStlpec].getPocetKrokov() + 1);
                        findAllWaysDifferentMoves(hraciePole, tempRiadok, tempStlpec, hraciePoleMoves);
                    }
                }
            }
        }

    }
    public static void findAllWaysDifferentMoves(int[][] hraciePole, int startRiadok, int startStlpec, Move[][] hraciePoleMoves) {
        int tempRiadok = startRiadok + 2;
        int tempStlpec = startStlpec + 1;
        findAllWays(hraciePole, tempRiadok, tempStlpec, startRiadok, startStlpec, hraciePoleMoves);
        tempRiadok = startRiadok - 1;
        tempStlpec = startStlpec + 2;
        findAllWays(hraciePole, tempRiadok, tempStlpec, startRiadok, startStlpec, hraciePoleMoves);
        tempRiadok = startRiadok - 2;
        tempStlpec = startStlpec - 1;
        findAllWays(hraciePole, tempRiadok, tempStlpec, startRiadok, startStlpec, hraciePoleMoves);
        tempRiadok = startRiadok + 1;
        tempStlpec = startStlpec - 2;
        findAllWays(hraciePole, tempRiadok, tempStlpec, startRiadok, startStlpec, hraciePoleMoves);

        tempRiadok = startRiadok + 1;
        tempStlpec = startStlpec + 2;
        findAllWays(hraciePole, tempRiadok, tempStlpec, startRiadok, startStlpec, hraciePoleMoves);
        tempRiadok = startRiadok - 2;
        tempStlpec = startStlpec + 1;
        findAllWays(hraciePole, tempRiadok, tempStlpec, startRiadok, startStlpec, hraciePoleMoves);
        tempRiadok = startRiadok - 1;
        tempStlpec = startStlpec - 2;
        findAllWays(hraciePole, tempRiadok, tempStlpec, startRiadok, startStlpec, hraciePoleMoves);
        tempRiadok = startRiadok + 2;
        tempStlpec = startStlpec - 1;
        findAllWays(hraciePole, tempRiadok, tempStlpec, startRiadok, startStlpec, hraciePoleMoves);
    }

    public static int getNumberFromLetter (String letter) {
        switch (letter) {
            case "A" :
                return 0;
            case "B" :
                return 1;
            case "C" :
                return 2;
            case "D" :
                return 3;
            case "E" :
                return 4;
            case "F" :
                return 5;
            case "G" :
                return 6;
            case "H" :
                return 7;
            default:
                return 8;
        }
    }

    public static String getLetterFromNumber (int number) {
        switch (number) {
            case 0:
                return "A";
            case 1:
                return "B";
            case 2:
                return "C";
            case 3:
                return "D";
            case 4:
                return "E";
            case 5:
                return "F";
            case 6:
                return "G";
            case 7:
                return "H";
            default:
                return "";
        }
    }
}
