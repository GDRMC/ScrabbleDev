package gdr.scrabbledev;

import java.util.ArrayList;

public abstract class ArbreBR {

    /**
     * Renvoie le mot racine
     * @return Racine actuelle
     */
    public abstract Mot getRacine();

    /**
     * Renvoie l'arbre à gauche de la racine
     * @return Arbre de gauche
     */
    public abstract ArbreBR getAg();

    /**
     * Renvoie l'arbre à droite de la racine
     * @return Arbre de droite
     */
    public abstract ArbreBR getAd();

    /**
     * Applique la racine entrée en paramètre sur l'arbre
     * @param s Mot à insérer
     */
    public abstract void setRacine(Mot s);

    /**
     * Applique l'arbre en paramètre sur la racine de gauche
     * @param Ag Arbre
     */
    public abstract void setAg(ArbreBR Ag);

    /**
     * Applique l'arbre en paramètre sur la racine de droite
     * @param Ad Arbre
     */
    public abstract void setAd(ArbreBR Ad);

    /**
     * Insertion dans l'arbre
     * @param val Valeur à insérer dans l'arbre
     * @return Etat de l'insertion
     */
    public abstract ArbreBR insereTo(Mot val);		// nouveau, equivalent � un constructeur

    /**
     * Recherche d'un mot dans l'arbre
     * @param val Valeur à rechercher dans l'arbre
     * @return Etat de l'insertion
     */
    public abstract boolean rechercherABR(Mot val);

    /**
     * Renvoie true si l'arbre est vide à la position donnée
     * @return
     */
    public abstract boolean estVide();

    /**
     * Affiche l'arbre à la position donnée
     */
    public abstract void afficher();

    /**
     * Renvoie le mot le plus à gauche de la position donnée
     * @return Mot le plus à gauche
     */
    public abstract Mot lePlusAGauche();

    /**
     * Supprime une valeur dans l'arbre
     * @param val Valeur à supprimer
     * @return Nouvel arbre ayant subi les modifications
     */
    public abstract ArbreBR supprimer(Mot val);

    /**
     * Trouve un mot dans l'arbre à la position actuelle
     * @param val Mot à rechercher
     * @return Etat de la recherche
     */
    public abstract boolean trouver(Mot val);

    /**
     * Renvoie true si l'arbre actuel à la position donnée est une feuille
     * @return True si c'est une feuille, sinon False
     */
    public abstract boolean estFeuille();

    /**
     * Renvoie le nombre de feuilles de l'arbre à la position actuelle
     * @return Nombre de feuilles de l'arbre à la position actuelle
     */
    public abstract int nbFeuilles();

    /**
     * Retourne le nombre de noeuds de l'arbre à la position donnée
     * @return Nombre de noeuds
     */
    public abstract int nbNoeuds();

    /**
     * Renvoie la hauteur de l'arbre
     * @return Hauteur de l'arbre
     */
    public abstract int hauteur();

    /**
     * Transforme l'arbre actuel à partir de la position donnée en ArrayList fourni en paramètre
     * @param t ArrayList contenant l'arbre actuel
     */
    public abstract void arbreBREnTab(ArrayList t);

}
//----------------------------------------------------------------------

class ArbreBRVide extends ArbreBR {

    public ArbreBRVide() {
    }

    public Mot getRacine() {
        return null;
    }

    public ArbreBR getAg() {
        return this;
    }

    public ArbreBR getAd() {
        return this;
    }

    public void setRacine(Mot s) {
    }

    public void setAg(ArbreBR Ag) {
    }

    public void setAd(ArbreBR Ad) {
    }

    public ArbreBR insereTo(Mot val) {
        return new ArbreBRCons(val);
    }

    public boolean rechercherABR(Mot val) {
        return false;
    }

    public boolean estVide() {
        return true;
    }

    public void afficher() {
        // System.out.print(" vide ");
    }

    public Mot lePlusAGauche() {
        return null;
    }

    public ArbreBR supprimer(Mot val) {
        return this;
    }

    public boolean trouver(Mot val) {
        return false;
    }

    public boolean estFeuille() {
        return false;
    }

    public int nbFeuilles() {
        return 0;
    }

    public int nbNoeuds() {
        return 0;
    }

    public int hauteur() {
        return 0;
    }

    public void arbreBREnTab(ArrayList t) {
    }

}

//----------------------------------------------------------------------
class ArbreBRCons extends ArbreBR {

    private Mot racine;
    private ArbreBR Ag;
    private ArbreBR Ad;

    public boolean estVide() {
        return false;
    }

    protected ArbreBRCons(Mot val, ArbreBR Ag, ArbreBR Ad) {
        this.racine = val;
        this.Ag = Ag;
        this.Ad = Ad;
    }

    protected ArbreBRCons(Mot val) {
        this.racine = val;
        this.Ag = new ArbreBRVide();
        this.Ad = new ArbreBRVide();
    }

    public ArbreBRCons(ArrayList<Mot> t, int d, int f) {
        if (d < f) {
            int milieu = (f + d) / 2; 	// si 12,5 alors milieu=12
            if (milieu == d) // cas d=2 f=3 milieu=2
            {
                this.racine = t.get(milieu);
                this.Ag = new ArbreBRVide();
                this.Ad = new ArbreBRCons(t.get(milieu + 1));
            } else {
                this.racine = t.get(milieu);
                this.Ag = new ArbreBRCons(t, d, milieu - 1);
                this.Ad = new ArbreBRCons(t, milieu + 1, f);
            }
        } else {
            this.racine = t.get(d);
            this.Ag = new ArbreBRVide();
            this.Ad = new ArbreBRVide();
        }

    }

    public ArbreBR insereTo(Mot val) {
        if (!rechercherABR(val)) {
            if (val.compareTo(getRacine()) < 0) {
                return new ArbreBRCons(this.getRacine(), this.getAg().insereTo(val), this.getAd());
            } else {
                return new ArbreBRCons(this.getRacine(), this.getAg(), this.getAd().insereTo(val));
            }
        }
        return this;
    }

    public Mot getRacine() {
        return this.racine;
    }

    public ArbreBR getAg() {
        return this.Ag;
    }

    public ArbreBR getAd() {
        return this.Ad;
    }

    public void setRacine(Mot s) {
        this.racine = s;
    }

    public void setAg(ArbreBR Ag) {
        this.Ag = Ag;
    }

    public void setAd(ArbreBR Ad) {
        this.Ad = Ad;
    }

    public boolean rechercherABR(Mot val) //rechercher dans un ABR
    {
        if (val.equals(this.getRacine())) {
            return true;
        } else if (val.compareTo(getRacine()) < 0) {
            return this.getAg().rechercherABR(val);
        } else {
            return this.getAd().rechercherABR(val);
        }
    }

    public boolean estFeuille() {
        return this.getAg().estVide() && this.getAd().estVide();
    }

    public void afficher() {
        System.out.print(this.getRacine() + " ");
        this.getAg().afficher();
        this.getAd().afficher();
    }

    public Mot lePlusAGauche() {
        if (this.getAg().estVide()) {
            return this.getRacine();
        } else {
            return this.getAg().lePlusAGauche();
        }
    }

    public ArbreBR supprimer(Mot val) {
        if (val.equals(this.getRacine())) {
            if (this.getAd().estVide()) {
                return this.getAg();  //cas particulier
            }
            Mot valeur = this.getAd().lePlusAGauche();
            return new ArbreBRCons(valeur, this.getAg(), this.getAd().supprimer(valeur));
        } else if (val.compareTo(getRacine()) < 0) {
            return new ArbreBRCons(this.getRacine(), this.getAg().supprimer(val), this.getAd());
        } else {
            return new ArbreBRCons(this.getRacine(), this.getAg(), this.getAd().supprimer(val));
        }

    }

    public boolean trouver(Mot val) //recherche dans tout l'arbre
    {
        if (val.equals(this.getRacine())) {
            return true;
        } else {
            return this.getAg().trouver(val) || this.getAd().trouver(val);
        }

    }

    public int nbFeuilles() {
        if (this.estFeuille()) {
            return 1;
        } else {
            return this.getAg().nbFeuilles() + this.getAd().nbFeuilles();
        }
    }

    public int nbNoeuds() {
        int nfg = 0, nfd = 0;
        nfg = getAg().nbNoeuds();
        nfd = getAd().nbNoeuds();
        return (1 + nfg + nfd);
    }

    public int hauteur() {
        int nfg = 0, nfd = 0;
        nfg = 1 + getAg().hauteur();
        nfd = 1 + getAd().hauteur();
        return Math.max(nfg, nfd);
    }

    public void arbreBREnTab(ArrayList t) {
        this.getAg().arbreBREnTab(t);
        t.add(this.getRacine());
        this.getAd().arbreBREnTab(t);
    }

}
