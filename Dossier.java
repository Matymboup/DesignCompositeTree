
import java.util.ArrayList;
public class Dossier extends  Composant{
  private  ArrayList<Composant> composants=new ArrayList<Composant>();
    public Dossier(String nomDossier){      
        super(nomDossier);
    }
   public  void ajouter(Composant nomComposant)  
	{
		composants.add(nomComposant);
	}

	public void afficher(int nombreTabulation)  
	{
		System.out.print(("├── "+getNom()).indent(nombreTabulation));
		for (Composant c : composants) 
		{
			 c.afficher(nombreTabulation+(4));
		}
		
	}
}

