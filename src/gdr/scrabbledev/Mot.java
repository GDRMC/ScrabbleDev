package gdr.scrabbledev;

/**
 *
 * @author USER
 */
public class Mot {

    private String LeMot;

    Mot(String UnMot) {
        this.LeMot = UnMot;
    }

    /**
     * Renvoie le mot sous forme de chaine de caractères
     *
     * @return Chaine de caractère du mot
     */
    public String getMot() {
        return this.LeMot;
    }

    // Comparaisons
    /**
     * Compare deux mots
     *
     * @param n Mot à comparer
     * @return Egalité des mots (true ou false)
     */
    public boolean equals(Mot n) {
        return n.getMot().equalsIgnoreCase(this.getMot());
    }

    /**
     * Compare le nombre de lettres identiques dans deux mots à comparer
     *
     * @param n Mot à comparer
     * @return Nombre de lettres en commun dans les deux mots
     */
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
        int i = 0;
        boolean ok = false;
        while (i < s.length() && !ok && (s.charAt(i) != c)) {
            i++;
        }
        return (i < s.length());
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

    /**
     * Validation du mot
     *
     * @param s Mot
     * @return Etat de validation du mot
     */
    public boolean MotOk(String s) {
        if (this.LettresPresentes(s)) {
            boolean ok = true;
            int i = 0;
            while (i < this.getMot().length() && ok) {
                char c = this.getMot().charAt(i);
                if (NbLettre(c, this.getMot()) > NbLettre(c, s)) {
                    ok = false;
                }
                i++;
            }
            return ok;
        } else {
            return false;
        }
    }
}
