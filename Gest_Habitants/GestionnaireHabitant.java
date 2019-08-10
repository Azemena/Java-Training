import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class GestionnaireHabitant extends JFrame{
    private JTextField numero, nom, prenom, recherche;
    private JRadioButton homme, femme;
    private JComboBox jour, mois, annee, ville, profession, modesRecherche;
    private JCheckBox activerRecherche;
    private JButton validerRecherche;
    private JTable table;
    private JLabel informations;
    private JPanel enregistrement, affichage, panelTable;
    private static ArrayList<Habitant> liste=new ArrayList<Habitant>();
	
    public GestionnaireHabitant(){
	this.setTitle("Gestionnaire d'habitant");
	this.setSize(700, 550);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setLocationRelativeTo(null);
	this.setResizable(false);
		
	//on crée les contôles de l'onglet pour l'enregistrement
	initEnregistrement();		
	//on crée les contôles de l'onglet pour l'affichage
	initAffichage();
		
		
        JTabbedPane onglet=new JTabbedPane();		
        onglet.add("Enregistrement", enregistrement);		
        onglet.add("Affichage", affichage);
		
	this.setContentPane(onglet);
	this.setVisible(true);
    }
	
    public void initEnregistrement(){
	enregistrement=new JPanel();
	enregistrement.setBackground(Color.WHITE);
		
	String itemsVille[]={"Dschang", "Bafang", "Mbouda", "Mbaganthe", "Douala", "Yaounde", "Ngaoundere", "Bamenda"};
	String itemsProfession[]={"Étudiant", "Élève", "Médecin", "Bussiness Man", "Enseignant", "Artisant", 
							"Journaliste","Avocat", "INgenieur Agronome", "Expert IA-SMA", "Artiste"};
	String itemsMois[]={"Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", 
							"Septembre", "Octombre", "Novembre", "Décembre"};
	String itemsJour[]=new String[31];
	String itemsAnnee[]=new String[90];
		
	//on rempli le tableau pour le jour
	for(int i=0; i<31; i++){
	    int valeur=i+1;
	    itemsJour[i]=String.valueOf(valeur);
	}
		
	Calendar calendrier=Calendar.getInstance(); //on réccupère la date en cours
	int year=calendrier.get(Calendar.YEAR); //on récupère l'année
		
	//on rempli le tableau pour l'année
	for(int i=0; i<90; i++){
	    itemsAnnee[i]=String.valueOf(year);
	    year--;
	}
		
	JLabel labels[]=new JLabel[7];
		
	labels[0]=new JLabel("Numéro d'identité");
	labels[1]=new JLabel("Nom");
	labels[2]=new JLabel("Prénom");
	labels[3]=new JLabel("Sexe");
	labels[4]=new JLabel("Date de naissance");
	labels[5]=new JLabel("Ville");
	labels[6]=new JLabel("Profession");
		
	Dimension dimLabels=new Dimension(150, 30),
		dimChamps=new Dimension(300, 30);
	
	for(int i=0; i<7; i++){
	    labels[i].setPreferredSize(dimLabels);
	}
		
	numero=new JTextField();	
	nom=new JTextField();	
	prenom=new JTextField();	
	homme=new JRadioButton("Homme");
	femme=new JRadioButton("Femme");
	jour=new JComboBox(itemsJour);	
	mois=new JComboBox(itemsMois);	
	annee=new JComboBox(itemsAnnee);	
	ville=new JComboBox(itemsVille);	
	profession=new JComboBox(itemsProfession);
	informations=new JLabel();
		
	numero.setPreferredSize(dimChamps);
	nom.setPreferredSize(dimChamps);
	prenom.setPreferredSize(dimChamps);
	jour.setPreferredSize(new Dimension(70, 25));
	mois.setPreferredSize(new Dimension(100, 25));
	annee.setPreferredSize(new Dimension(70, 25));
	ville.setPreferredSize(dimChamps);
	profession.setPreferredSize(dimChamps);
	informations.setPreferredSize(new Dimension(540, 25));
	informations.setHorizontalAlignment(JLabel.CENTER);
	informations.setFont(new Font("Arial", Font.ITALIC, 18));
		
	//je selectionne le jour en cours par défaut
	jour.setSelectedIndex(calendrier.get(Calendar.DAY_OF_MONTH)-1);
	//je selectionne le mois en cours par défaut
	mois.setSelectedIndex(calendrier.get(Calendar.MONTH));
		
	ButtonGroup groupe=new ButtonGroup();
	groupe.add(homme);
	groupe.add(femme);
	homme.setSelected(true);
		
	JPanel sexe=new JPanel();
	sexe.setPreferredSize(dimChamps);
	sexe.add(homme);
	sexe.add(femme);
			
	JPanel dateNaissance=new JPanel();
	dateNaissance.setPreferredSize(dimChamps);
	dateNaissance.add(jour);
	dateNaissance.add(mois);
	dateNaissance.add(annee);
		
	JPanel centre=new JPanel();
	centre.setPreferredSize(new Dimension(550, 380));
		
	JPanel panels[]=new JPanel[7];
		
	for(int i=0; i<7; i++){
	    panels[i]=new JPanel();
	    panels[i].setPreferredSize(new Dimension(500, 50));
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
		
	JButton valider=new JButton("Enregistrer");
	JButton annuler=new JButton("Annuler");
		
	valider.addActionListener(new EnregistrerListener());
		
	JPanel sud=new JPanel();
	sud.setPreferredSize(new Dimension(550, 70));
	sud.setBackground(Color.WHITE);
	sud.add(informations);
	sud.add(valider);
	sud.add(annuler);
		
	JLabel message=new JLabel("Enregistrement d'un habitant");
	message.setPreferredSize(new Dimension(680, 35));
	message.setHorizontalAlignment(JLabel.CENTER);
	message.setFont(new Font("Arial", Font.BOLD, 25));
			
	enregistrement.add(message);
	enregistrement.add(centre);
	enregistrement.add(sud);
    }
	
    public void initAffichage(){
	String itemsMode[]={"Numéro d'identité", "Nom", "Prénom", "Ville"};
		
	affichage=new JPanel();
	affichage.setBackground(Color.WHITE);
		
	JLabel labelMode=new JLabel("Sélectionner le mode");
		
	activerRecherche=new JCheckBox("Activer les recherches");
	modesRecherche=new JComboBox(itemsMode);
	recherche=new JTextField();
	validerRecherche=new JButton("Valider");
		
	activerRecherche.setBackground(Color.WHITE);
	activerRecherche.setPreferredSize(new Dimension(630, 25));
	recherche.setPreferredSize(new Dimension(200, 25));
		
	modesRecherche.setEnabled(false);
	recherche.setEnabled(false);
	validerRecherche.setEnabled(false);
		
	activerRecherche.addActionListener(new ActionListener(){

	     public void actionPerformed(ActionEvent e) {
		 if(activerRecherche.isSelected()){
		    modesRecherche.setEnabled(true);
		    recherche.setEnabled(true);
		    validerRecherche.setEnabled(true);
		 }
		 else{
		    recherche.setText("");
					
		    modesRecherche.setEnabled(false);
		    recherche.setEnabled(false);
		    validerRecherche.setEnabled(false);
					
		    remplireTable();
		 }
	     }
			
	});
		
	validerRecherche.addActionListener(new RechercheListener());
		
	JPanel zoneRecherche=new JPanel();
	zoneRecherche.setPreferredSize(new Dimension(650, 70));
	zoneRecherche.setBackground(Color.WHITE);
			
	zoneRecherche.add(activerRecherche);
	zoneRecherche.add(labelMode);
	zoneRecherche.add(modesRecherche);
	zoneRecherche.add(recherche);
	zoneRecherche.add(validerRecherche);
		
	JButton afficherDetails=new JButton("Afficher les détails");
	afficherDetails.addActionListener(new ActionListener(){

	      public void actionPerformed(ActionEvent e) {
		  //on vérifie si une ligne est sélectionnée
		  if(table.getSelectedRowCount()>0){
		     //on récupère la numéro de l'habitant
		     int id=(Integer)table.getValueAt(table.getSelectedRow(), 0);
		     Details details=new Details(null, "details", true, id);
		     details.show();
					
		     //on enregistre pour les eventuelles modifications
		     enregistrer();
		     
                     //si la case est est cochée on rempli la table avec seulement les habitants qui correspondent à la recherche
                    //sinon on la rempli avec tous les habitants
		     if(activerRecherche.isSelected())
			validerRecherche.doClick();
		     else
			remplireTable();
		  }
	      }			
	});
		
	JPanel bas=new JPanel();
	bas.setPreferredSize(new Dimension(650, 40));
	bas.setBackground(Color.WHITE);
	bas.add(afficherDetails);
		
	panelTable=new JPanel();
	panelTable.setPreferredSize(new Dimension(650, 380));
	panelTable.setBackground(Color.WHITE);
		
	remplireTable();
		
	affichage.add(zoneRecherche);
	affichage.add(panelTable);
	affichage.add(bas);
    }
	
    public static ArrayList<Habitant> getListe(){
	return liste;
    }
	
    public void remplireTable(){
	recupererListe();
		
	String titre[]={"Numéro d'identité", "Nom", "Prénom", "Ville"};
	Object data[][]=new Object[liste.size()][4];
		
	for(int i=0; i<liste.size(); i++){
	    data[i][0]=liste.get(i).getNumero();
	    data[i][1]=liste.get(i).getNom();
	    data[i][2]=liste.get(i).getPrenom();
	    data[i][3]=liste.get(i).getVille();
	}
		
	table=new JTable(data, titre);
	//on modifie la largeur de la première colonne
	table.getColumnModel().getColumn(0).setPreferredWidth(30);
	//on autorise la sélection d'une seule ligne
	table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
	panelTable.removeAll();
		
	JScrollPane scroll=new JScrollPane(table);
	scroll.setPreferredSize(new Dimension(650, 375));
		
	panelTable.add(scroll);				
	panelTable.revalidate();
	panelTable.repaint();	
    }
	
    public void enregistrer(){
	ObjectOutputStream ecriture=null;
		
	try{
	    ecriture=new ObjectOutputStream(new BufferedOutputStream(
			new FileOutputStream("liste.txt")));
			
	    ecriture.writeObject(liste);
	    ecriture.close();
	}catch(IOException e){
	    e.printStackTrace();
	}
    }
	
    public void recupererListe(){
	ObjectInputStream lecture=null;
		
	try{
	    lecture=new ObjectInputStream(new BufferedInputStream(
			new FileInputStream("liste.txt")));
			
	    liste=(ArrayList<Habitant>)lecture.readObject();
	    lecture.close();
	}catch(ClassNotFoundException e){
	    e.printStackTrace();
	}catch(FileNotFoundException e){
	    e.printStackTrace();
	}catch(IOException e){
	    e.printStackTrace();
	}
    }
	
    public boolean numeroExiste(int numero){
	boolean existe=false;
		
	for(Habitant habitant: liste){
	    if(habitant.getNumero()==numero){
		existe=true;
		break;
	    }
        }
		
	return existe;
    }
	
    public static boolean isNumeric(String nombre){
	try{
	    Integer.parseInt(nombre);
	    return true;
	}catch(NumberFormatException e){
	    return false;
	}
    }
	
    class EnregistrerListener implements ActionListener{

	public void actionPerformed(ActionEvent e) {
	    boolean validite=true;
	    int day, month, year;
	    DateNaissance date=null;

	    //On vérifie la validité des informations
	    if(!isNumeric(numero.getText())){
		validite=false;  
		informations.setText("Le numéro doit être un nombre entier");
		informations.setForeground(Color.RED);
	    }
	    else if(nom.getText().isEmpty()){
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
			
	    //si les informations sont valides	
	    if(validite){
		int identite=Integer.parseInt(numero.getText());
		Sexe sexe;
				
		if(homme.isSelected())
		    sexe=Sexe.Homme;
		else
		    sexe=Sexe.Femme;
				
		//si le numéro n'existe pas déjà
		if(!numeroExiste(identite)){
		    liste.add(new Habitant(identite, nom.getText(), prenom.getText(), sexe, date, 
						ville.getSelectedItem().toString(), profession.getSelectedItem().toString()));
										
		    enregistrer();

                    //si la case est est cochée on rempli la table avec seulement les habitants qui correspondent à la recherche
                    //sinon on la rempli avec tous les habitants
		    if(activerRecherche.isSelected())
			validerRecherche.doClick();
		    else
			remplireTable();
					
		    informations.setText("L'habitant a été enregistré avec succès");
		    informations.setForeground(Color.GREEN);
					
		    numero.setText("");
		    nom.setText("");
		    prenom.setText("");
		}
		else{
		    informations.setText("Le numéro existe déjà");
		    informations.setForeground(Color.RED);
		}
	    }		
	}	
    }
	
    class RechercheListener implements ActionListener{

	public void actionPerformed(ActionEvent e) {
	    Object data[][]=null;
	    int nombre=0, ligne=0;
	    boolean resultatTrouve=false;
			
	    String mode=modesRecherche.getSelectedItem().toString();
			
	    if(mode.equals("Numéro d'identité")){
		int numero=0;
		if(isNumeric(recherche.getText()))
		   numero=Integer.valueOf(recherche.getText());
				
		   for(Habitant hab: liste){
			if(hab.getNumero()==numero){
			   nombre++;
			   resultatTrouve=true;
			}
		   }
				
		data=new Object[nombre][4];
				
		for(Habitant hab: liste){
		    if(hab.getNumero()==numero){
		       data[ligne][0]=hab.getNumero();
		       data[ligne][1]=hab.getNom();
		       data[ligne][2]=hab.getPrenom();
		       data[ligne][3]=hab.getVille();
		    }
		}
	    }
			
	    if(mode.equals("Nom")){
		for(Habitant hab: liste){
		   if(hab.getNom().equals(recherche.getText())){
		       nombre++;
		       resultatTrouve=true;
		   }
		}
				
		data=new Object[nombre][4];
				
		for(Habitant hab: liste){
		    if(recherche.getText().equals(hab.getNom())){
		       data[ligne][0]=hab.getNumero();
		       data[ligne][1]=hab.getNom();
		       data[ligne][2]=hab.getPrenom();
		       data[ligne][3]=hab.getVille();
		       ligne++;
		    }
		}
	    }
			
	    if(mode.equals("Prénom")){
		for(Habitant hab: liste){
		    if(hab.getPrenom()==recherche.getText()){
			nombre++;
			resultatTrouve=true;
		    }
		}
				
		data=new Object[nombre][4];
				
		for(Habitant hab: liste){
		    if(recherche.getText().equals(hab.getPrenom())){
			data[ligne][0]=hab.getNumero();
			data[ligne][1]=hab.getNom();
			data[ligne][2]=hab.getPrenom();
			data[ligne][3]=hab.getVille();
			ligne++;
		    }
		}
	    }
			
	    if(mode.equals("Ville")){
	       for(Habitant hab: liste){
		   if(hab.getVille().equals(recherche.getText())){
			nombre++;
			resultatTrouve=true;
		   }
						
	       }
				
	       data=new Object[nombre][4];
				
	       for(Habitant hab: liste){
		   if(recherche.getText().equals(hab.getVille())){
			data[ligne][0]=hab.getNumero();
			data[ligne][1]=hab.getNom();
			data[ligne][2]=hab.getPrenom();
			data[ligne][3]=hab.getVille();
			ligne++;
		   }
	       }
	    }
			
	    if(resultatTrouve){
		String titre[]={"Numéro d'identité", "Nom", "Prénom", "Ville"};
		table=new JTable(data, titre);
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				
		panelTable.removeAll();
				
		JScrollPane scroll=new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(650, 375));
				
		panelTable.add(scroll);			
		panelTable.revalidate();
		panelTable.repaint();
	    }
	    else{
		JOptionPane info=new JOptionPane();
		info.showMessageDialog(null, "Aucun resultat disponible à votre recherche");
	    }
	}		
    }
	
    public static void main(String[] args) {
		
	try {
	     UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	} catch (ClassNotFoundException e) {
	     // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (InstantiationException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IllegalAccessException e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	} catch (UnsupportedLookAndFeelException e) {
           // TODO Auto-generated catch block
	   e.printStackTrace();
	}
	new GestionnaireHabitant();
    }
}