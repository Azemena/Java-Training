import java.io.Serializable;

public class Habitant implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int numero;
    private String nom, prenom, ville, profession;
    private Sexe sexe;
    private DateNaissance date;
	
    public Habitant(int numero, String nom, String prenom, Sexe sexe, DateNaissance date,
			String ville, String profession){
	this.numero=numero;
	this.nom=nom;
	this.prenom=prenom;
	this.sexe=sexe;
	this.date=date;
	this.ville=ville;
	this.profession=profession;
    }
	
    public int getNumero(){
	return this.numero;
    }
	
    public String getNom(){
	return this.nom;
    }
	
    public String getPrenom(){
	return this.prenom;
    }
	
    public Sexe getSexe(){
	return this.sexe;
    }
	
    public DateNaissance getDateNaissance(){
	return this.date;
    }
	
    public String getVille(){
	return this.ville;
    }
	
    public String getProfession(){
	return this.profession;
    }
	
    public void setNumero(int numero){
	this.numero=numero;
    }
	
    public void setNom(String nom){
	this.nom=nom;
    }
	
    public void setPrenom(String prenom){
	this.prenom=prenom;
    }
	
    public void setSexe(Sexe sexe){
	this.sexe=sexe;
    }
	
    public void setDateNaissance(DateNaissance date){
	this.date=date;
    }
	
    public void setVille(String ville){
	this.ville=ville;
    }
	
    public void setProfession(String profession){
	this.profession=profession;
    }
}