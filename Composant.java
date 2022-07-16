
public abstract class Composant{   
public abstract void afficher(int t);
private String nom;
public  void ajouter( Composant a){};
public Composant (String nom){
     this.nom=nom;
}
public String getNom(){
    return this.nom;
}

}


