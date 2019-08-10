import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Details extends JDialog{
    private JLabel numero;
    private JTextField nom, prenom;
    private JComboBox sexe, jour, mois, annee, ville, profession;
    private JButton supprimer, modifier, fermer;
    private JLabel informations=new JLabel();
    private JPanel content;	
    private Habitant habitant;
	
    public Details(JFrame parent, String titre, boolean modal, int identite){
	super(parent, titre, modal);
	this.setSize(500, 490);
	this.setLocationRelativeTo(null);
	this.setResizable(false);
		
	//on récupère la liste
	ArrayList<Habitant> liste=GestionnaireHabitant.getListe();
	for(int i=0; i<liste.size(); i++){
	    if(liste.get(i).getNumero()==identite){
		habitant=liste.get(i);
		break;
	    }
	}
		
	this.setTitle(habitant.getPrenom()+ " "+habitant.getNom());
		
	content=new JPanel();
	initControle();
		
	this.setContentPane(content);
    }
	
    public void initControle(){
	String itemsVille[]={"Conakry", "Kindia", "Boké", "Mamou", "Labé", "Kankan", "Faranah", "N'Zérékoré"};
	String itemsProfession[]={"Étudiant", "Élève", "Médecin", "Commerçant", "Enseignant", "Artisant", 
						"Journaliste","Avocat", "Éleveur", "Cultivateur", "Artiste"};
	String itemsMois[]={"Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", 
						"Septembre", "Octombre", "Novembre", "Décembre"};
	String itemsJour[]=new String[31];
	String itemsAnnee[]=new String[90];
		
	for(int i=0; i<31; i++){
	    int valeur=i+1;
	    itemsJour[i]=String.valueOf(valeur);
	}
		
	Calendar calendrier=Calendar.getInstance();
	int year=calendrier.get(Calendar.YEAR);
		
	for(int i=0; i<90; i++){
	    itemsAnnee[i]=String.valueOf(year);
	    year--;
	}
		
	numero=new JLabel();
	nom=new JTextField();
	prenom=new JTextField();
	sexe=new JComboBox();
	jour=new JComboBox(itemsJour);
	mois=new JComboBox(itemsMois);
	annee=new JComboBox(itemsAnnee);
	ville=new JComboBox(itemsVille);
	profession=new JComboBox(itemsProfession);
		
	sexe.addItem("Homme");
	sexe.addItem("Femme");
		
	JLabel labels[]=new JLabel[7];
		
	labels[0]=new JLabel("Numéro d'identité");
	labels[1]=new JLabel("Nom");
	labels[2]=new JLabel("Prénom");
	labels[3]=new JLabel("Sexe");
	labels[4]=new JLabel("Date de naissance");
	labels[5]=new JLabel("Ville");
	labels[6]=new JLabel("Profession");
		
	Dimension dimLabels=new Dimension(150, 30),
			dimChamps=new Dimension(300, 25);
	
	for(int i=0; i<7; i++){
	    labels[i].setPreferredSize(dimLabels);
	}
		
	numero.setPreferredSize(dimChamps);
	nom.setPreferredSize(dimChamps);
	prenom.setPreferredSize(dimChamps);
	sexe.setPreferredSize(dimChamps);
	jour.setPreferredSize(new Dimension(70, 25));
	mois.setPreferredSize(new Dimension(100, 25));
	annee.setPreferredSize(new Dimension(70, 25));
	ville.setPreferredSize(dimChamps);
	profession.setPreferredSize(dimChamps);
		
	JPanel dateNaissance=new JPanel();
	dateNaissance.setPreferredSize(new Dimension(300, 30));
	dateNaissance.add(jour);
	dateNaissance.add(mois);
	dateNaissance.add(annee);
		
	JPanel centre=new JPanel();
	centre.setPreferredSize(new Dimension(500, 380));
		
	JPanel panels[]=new JPanel[7];
		
	for(int i=0; i<7; i++){
	    panels[i]=new JPanel();
	    panels[i].setPreferredSize(new Dimension(480, 50));
	    centre.add(panels[i]);
	}
		
	panels[0].add(labels[0]);
	panels[0].add(numero);
	panels[1].add(labels[1]);
	panels[1].add(nom);
	panels[2].add(labels[2]);
	panels[2].add(prenom);
	panels[3].add(labels[3]);
	panels[3].add(sexe);
	panels[4].add(labels[4]);
	panels[4].add(dateNaissance);
	panels[5].add(labels[5]);
	panels[5].add(ville);
	panels[6].add(labels[6]);
	panels[6].add(profession);
		
	supprimer=new JButton("Supprimer");
	modifier=new JButton("Modifier");
	fermer=new JButton("Fermer");
		
	modifier.addActionListener(new ModifierListener());
		
	supprimer.addActionListener(new ActionListener(){

	    public void actionPerformed(ActionEvent e) {
		JOptionPane option=new JOptionPane();
		int reponse=option.showConfirmDialog(null, "Vous êtes sûre de vouloire supprimer cet habitant?",
					"suppression de l'habitant", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				
		if(reponse==JOptionPane.YES_OPTION){
		    ArrayList<Habitant> liste=GestionnaireHabitant.getListe();	
		    liste.remove(habitant);
		    dispose();
		}
	    }
			
	});
		
	fermer.addActionListener(new ActionListener(){

	    public void actionPerformed(ActionEvent e) {
		dispose();
	    }
			
	});
		
	informations.setPreferredSize(new Dimension(480, 30));
	informations.setHorizontalAlignment(JLabel.CENTER);
		
	JPanel sud=new JPanel();
	sud.setPreferredSize(new Dimension(500, 70));
	sud.add(informations);
	sud.add(supprimer);
	sud.add(modifier);
	sud.add(fermer);
		
	content.add(centre);
	content.add(sud);
			
	remplireControles(habitant);
	activerControles(false);
    }
	
    private void remplireControles(Habitant habitant){
	numero.setText(String.valueOf(habitant.getNumero()));
	nom.setText(habitant.getNom());
	prenom.setText(habitant.getPrenom());
	sexe.setSelectedItem(habitant.getSexe().toString());
	jour.setSelectedItem(String.valueOf(habitant.getDateNaissance().getJour()));
	mois.setSelectedIndex(habitant.getDateNaissance().getMois()-1);
	annee.setSelectedItem(String.valueOf(habitant.getDateNaissance().getAnnee()));
	ville.setSelectedItem(habitant.getVille());
	profession.setSelectedItem(habitant.getProfession());	
    }
	
    private void activerControles(boolean active){
	nom.setEnabled(active);
	prenom.setEnabled(active);
	sexe.setEnabled(active);
	jour.setEnabled(active);
	mois.setEnabled(active);
	annee.setEnabled(active);
	ville.setEnabled(active);
	profession.setEnabled(active);
    }
	
    class ModifierListener implements ActionListener{

	public void actionPerformed(ActionEvent e) {
	    if(modifier.getText().equals("Modifier")){
		modifier.setText("Enregistrer les modifications");
		activerControles(true);
	    }
	    else{
		boolean validite=true;
		int day, month, year;
		DateNaissance date=null;

		//On vérifie la validité des informations
		if(nom.getText().isEmpty()){
		    validite=false;
		    informations.setText("Le nom ne doit pas être vide");
		    informations.setForeground(Color.RED);
		}
		else if(prenom.getText().isEmpty()){
		    validite=false;
		    informations.setText("Le prénom ne doit pas être vide");
		    informations.setForeground(Color.RED);					
		}
		else{
		    day=Integer.parseInt(jour.getSelectedItem().toString());
		    month=mois.getSelectedIndex()+1;
		    year=Integer.parseInt(annee.getSelectedItem().toString());
					
		    try{
			date=new DateNaissance(day, month, year);
		    }catch(DateNaissanceException ex){
			validite=false;
			informations.setText(ex.getMessage());
			informations.setForeground(Color.RED);
		    }
		}
				
		if(validite){
		    Sexe sex;
		    if(sexe.getSelectedItem().toString().equals("Homme"))
			sex=Sexe.Homme;
		    else
			sex=Sexe.Femme;
					
		    habitant.setNom(nom.getText());
		    habitant.setPrenom(prenom.getText());
		    habitant.setSexe(sex);
		    habitant.setDateNaissance(date);
		    habitant.setVille(ville.getSelectedItem().toString());
		    habitant.setProfession(profession.getSelectedItem().toString());
					
		    remplireControles(habitant);
		    activerControles(false);
		    modifier.setText("Modifier");
		}
	    }			
        }		
    }
}