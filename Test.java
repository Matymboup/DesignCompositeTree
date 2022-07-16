import java.io.*;         // on commence par importer nos packages dont on aura besoin
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class Test{
     public static void main(String [] args) throws ParserConfigurationException, SAXException, IOException{
          // // "/home/matymboup/Bureau/TpPattern/Trie" :;; "/home/matymboup/Bureau/sans-pattern", ("/home/matymboup/Bureau/TpJava");
        String path=pathToXml("/home/matymboup/Bureau/TpPattern");  // Dans TpPattern il y a  Trie 
         System.out.println("=============== Path to xml ===============================");
        System.out.println(path);
        System.out.println("=============== xml to Doc =================================");
         System.out.println();
       Composant folder=xmlToDoc(path); 
       folder.afficher(0); 

    }
 
// la methode pathToxml prend en argument un chemin qui est une chaine puis retourne un fichier xml
    public static String pathToXml(String path){    
        File fic =new File(path); 
        String chaine = "<Directory name='"+fic.getName()+"'>\n"; // on initialise notre chaine avec une balise ouvrante de directory
        for(File file: fic.listFiles()){   // la on parcours noter liste de files
            if(file.isDirectory()){    // si le file est un dossier
                chaine=chaine + pathToXml(file.getAbsolutePath()); // on concatène notre chaine puis on rappelle la methode a nouveau s est de maniere recursive
            }
            else if(file.isFile()){  // si file n est pas un dossier on concatene juste sans rappeler la methode encore
                chaine=chaine+"<File name='"+file.getName()+"'/>\n";
            }
        }
        chaine = chaine+"</Directory>\n";   // a la fin on ferme notre directory 
        return chaine;  // puis on retourne la chaine
    } 

    // la methode xmlToDoc prend en argument une chaine c est a dire notre chaine en xml et retourne un document
    public static Composant xmlToDoc(String Text) throws ParserConfigurationException,SAXException,IOException{
        String  nText="<?xml version='1.0' encoding='UTF-8' ?>\n"+Text; 
        DocumentBuilderFactory dbFactory =DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder= dbFactory.newDocumentBuilder();
        StringBuilder xmlText=new StringBuilder();
        xmlText.append(nText);
        ByteArrayInputStream input=new ByteArrayInputStream(xmlText.toString().getBytes("UTF-8"));
        Document doc=dBuilder.parse(input);
        doc.getDocumentElement().normalize();

        Element root = doc.getDocumentElement(); // on obtient notre directory qui contient tous ses fils
         return (ajoutElement(root));  // la on parcous le document pour recuperer les differents fichier et dossier via leur name avec cette methode  puis l ajouter
    }
     public static Composant ajoutElement(Element el){   
       Composant folder=new Dossier(el.getAttribute("name"));
        NodeList nodes=el.getChildNodes();
        for(int j=0;j<nodes.getLength();j++){
            Node noeud=nodes.item(j);
            if(noeud.getNodeType()==Node.ELEMENT_NODE){
                Element element=(Element) noeud;
                if(noeud.getNodeName().equals("File")){
                    Composant file=new Fichier(element.getAttribute("name"));
                    folder.ajouter(file);
                }
                else if(element.getNodeName().equals("Directory")){
                    folder.ajouter(ajoutElement(element));

                }
            }
        }
               return folder;
    }


}   


