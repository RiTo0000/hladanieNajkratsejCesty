public class Move {
    int startRiadok;
    int startStlpec;
    int pocetKrokov;

    public Move(int startRiadok, int startStlpec, int pocetKrokov) {
        this.startRiadok = startRiadok;
        this.startStlpec = startStlpec;
        this.pocetKrokov = pocetKrokov;
    }

    public int getStartRiadok() {
        return startRiadok;
    }

    public int getStartStlpec() {
        return startStlpec;
    }

    public int getPocetKrokov() {
        return pocetKrokov;
    }

}
