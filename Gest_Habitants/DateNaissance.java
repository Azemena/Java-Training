import java.io.Serializable;


public class DateNaissance implements Serializable{
    private int jour, mois, annee;
	
    public DateNaissance(int jour, int mois, int annee) throws DateNaissanceException{
	if(!estValide(jour, mois, annee))
	   throw new DateNaissanceException("La date de naissance n'est pas une date valide");
	else{
	   this.jour=jour;
	   this.mois=mois;
	   this.annee=annee;
	}		
    }
	
    public int getJour(){
	return this.jour;
    }
	
    public int getMois(){
	return this.mois;
    }
	
    public int getAnnee(){
	return this.annee;
    }	
	
    private boolean estValide(int jour, int mois, int annee){
	boolean valide=false;
	    
	switch(mois){
	    case 1:
	    case 3:
	    case 5:
	    case 7:
	    case 8:
	    case 10:
	    case 12:
	         if(jour>=1 && jour<=31)
	            valide=true;
	    break;
	    case 4:
	    case 6:
	    case 9:
	    case 11:
	         if(jour>=1 && jour<=30)
	            valide=true;
	    break;
	    case 2:
	         if((annee%4==0 && jour>=1 && jour<=29) || (annee%4!=0 && jour>=1 && jour<=28))
	             valide=true;
	    break;
	    default:
	        valide=false;
	}
	return valide;
    }
}