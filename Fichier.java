
public class Fichier extends Composant {
    public Fichier(String fi){  // constructeur 
        super(fi);
    }
    public  void afficher(int tab){   // methode qui nous permet d afficher les fichier
                
                System.out.print(("├── "+getNom()).indent(tab));
    
        }
    }
       
  