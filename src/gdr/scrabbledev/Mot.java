package gdr.scrabbledev;

public class Mot {

    private String LeMot;

    Mot(String UnMot) {
        this.LeMot = UnMot;
    }

    public String getMot() {
        return this.LeMot;
    }

    // Comparaisons
    public boolean equals(Mot n) {
        return n.getMot().equalsIgnoreCase(this.getMot());
    }

    public int compareTo(Mot n) {
        return (this.getMot().compareTo(n.getMot()));
    }

    // A Completer...
    public String toString() {
        return " Nom :" + this.getMot();
    }

    private int NbLettre(char a, String s) {
        int Nb = 0;
        for (int j = 0; j < s.length(); j++) {
            if (s.charAt(j) == a) {
                Nb++;
            }
        }
        return Nb;
    }

    private boolean lettrePresente(char c, String s) {
        int j = 0;
        boolean present = false;
        while (j < s.length() && !present) {
            if (s.charAt(j) == c) {
                present = true;
            }
            j++;
        }
        return present;
    }

    // mot gagnant si les lettreJoueur sont inclues dans le motDico.
    // Mais il faut aussi le nombre de lettresJoueur soient en nombre suffisant pour constituer le mot
    private boolean LettresPresentes(String s) {
        int i = 0;
        boolean present = true;
        while (i < this.getMot().length() && present) {
            char c = this.getMot().charAt(i);
            present = lettrePresente(c, s);
            i++;
        }
        return present;
    }

    public boolean MotOK(String s) {
        return false;
    }
}
