package han.virusverge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
/*
 * Naam: VirusVerge
 * Versie: V1.1
 * Auteur: Mark de Korte
 * Datum: 07-02-18
 */

public class Viruslogica {
	
	//Declaratie van de variabelen
	static VirusGUI Window;
	static HashMap<Integer , String> hosts = new HashMap<Integer , String>();
	static ArrayList<Virus> allViruses = new ArrayList<Virus>();
	static ArrayList<Virus> uniViruses = new ArrayList<Virus>();
	static ArrayList<Integer> hostslist;
	static ArrayList<Virus> uniVirus = new ArrayList<Virus>();
	static ArrayList<Virus> virusesSelectie;
	static ArrayList<Virus> viruseshost1 = new ArrayList<Virus>();
	static ArrayList<Virus> viruseshost2 = new ArrayList<Virus>();
	static ArrayList<Virus> virusesRes = new ArrayList<Virus>();

	//Main functie start het programma door een GUI aan te maken
	public static void main(String[] args) {
		Window = new VirusGUI();
	}

	//Deze functie is verantwoordlijk voor het verbinden en downloaden van het bestand van een FTP server
	public static void ftpBestand() throws MalformedURLException {
		/*
		Het adresvak is gemaakt om het onderstaande adres te gebruiken
		ftp://ftp.genome.jp/pub/db/virushostdb/virushostdb.tsv
		 */

		//Declaratie Variabelen
		ArrayList<String> lines = new ArrayList<String>();
		URL virushostdb = new URL(Window.gettxtURL().getText());
		//System.out.println(window.gettxtURL().getText());
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(virushostdb.openStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null)
				lines.add(inputLine);
			in.close();
		} catch (IOException ex) {
			System.out.println("Error: " + ex.getMessage());
			ex.printStackTrace();
		}finally {
			try {
				lijstMaker(lines); 
			}catch(IndexOutOfBoundsException e) {
				System.out.println("De lijst bevat onjuiste of niet leesbare waarden");
				e.printStackTrace();
			}
		}
	}

	//Deze functie maakt een lijst zonder duplicaten
	public static void lijstMaker(ArrayList<String> lines) {
		//deze line word weggehaald omdat deze de kolomtitels bevat
		lines.remove(0);
		//Instantieer nieuwe objecten met de regels van het bestand
		try {
			for (int i = 0; i < lines.size(); i++) {
				allViruses.add(new Virus(lines.get(i)));
			}
			//Dmv een HashSet word er een lijst zonder duplicaten aangemaakt
			Set<Integer> virus_ids = new HashSet<Integer>();
			for(int i = 0; i<allViruses.size(); i++) {
				hosts.put(allViruses.get(i).gethost_tax_id(), allViruses.get(i).gethost_name());
				if(virus_ids.add(allViruses.get(i).virus_id)) {
					uniViruses.add(allViruses.get(i));
				}else {
					//Deze loop verlangzaamt de Big O van deze functie redelijk, desondanks duurt het minder dan een seconde voordat deze loop is voltooid
					for(int x =0; x < uniViruses.size(); x++) {
						if(uniViruses.get(x).getvirus_id() == allViruses.get(i).getvirus_id()) {
							uniViruses.get(x).sethost_list(allViruses.get(i).gethost_tax_id());
						}
					}
				}
			}
		}catch(IndexOutOfBoundsException e) {
			System.out.println("De lijsten bevatten geen waarden");
			e.printStackTrace();
		}

		//Aanroepen van de functie die Classificatie optie geeft, doordat mijn programma enigszins hervormd is, is deze aparte functie een beetje overbodig
		addChoices();
	}

	//Deze functie geeft de keuzemogelijkheden aan de GUI class,deze aparte functie is misschien een beetje overbodig en had bij classificatieSort gekunt
	public static void addChoices() {
		//Declaratie Variabelen
		ArrayList<String> Classificaties = new ArrayList<String>();
		Classificaties.add(0, "None");
		try {
			for(int i=0; i<uniViruses.size();i++) {
				String temp = uniViruses.get(i).getClassificatie();
				if(Classificaties.contains(temp)==false) {
					Classificaties.add(temp);
				}

			}
		}catch(IndexOutOfBoundsException e) {
			System.out.println("De lijsten bevatten geen waarden");
			e.printStackTrace();
		}
		//Roep de functie in de GUIclass aan die de comboboxes kan vullen
		Window.setkeuze_Viral_class(Classificaties);
	}

	//Deze functie is verantwoordelijk voor het verwerken van de gekozen classificatie
	public static void classificatieSort(String keuze) {
		//Declaratie Variabelen
		hostslist = new ArrayList<Integer>();
		virusesSelectie = new ArrayList<Virus>();
		ArrayList<Integer> hostsres = new ArrayList<Integer>();
		Set<Integer> uniHost = new HashSet<Integer>();
		//Als er een classificatie gekozen is word deze verwerkt door onderstaande code, afhankelijk van de keus verandert de selectie
		try {
			for(int i = 0; i < uniViruses.size(); i++) {
				if(keuze.equals("None")) {
					hostslist.addAll(uniViruses.get(i).gethost_list());
				}
				else if(uniViruses.get(i).getClassificatie().equals(keuze)) {
					hostslist.addAll(uniViruses.get(i).gethost_list());
					virusesSelectie.add(uniViruses.get(i));
				}
			}
			for(int i = 0; i < hostslist.size(); i++) {
				if(uniHost.add(hostslist.get(i))) {
					hostsres.add(hostslist.get(i));
				}
			}
		}catch(IndexOutOfBoundsException e) {
			System.out.println("De lijsten bevatten geen waarden");
			e.printStackTrace();
		}
		//Roep functie binnen GUI class aan
		Window.setkeuze_HOST_ID(hostsres, hosts);
	}

	//Deze functie is verantwoordelijk voor het sorteren en vullen van de output lijsten
	public static void sorteerViruses() {
		//Declaratie Variabelen
		int keuze1 = Window.getKeuze_host_id_1();
		int keuze2 = Window.getKeuze_host_id_2();
		//if-statements voor de gekozen sortering
		if(Window.getRdbtnID()) {
			Collections.sort(virusesSelectie, Virus.IDComparator());
		}
		if(Window.getRdbtnClass()) {
			Collections.sort(viruseshost1, Virus.ClassComparator());
		}
		if(Window.getRdbtnHosts()) {
			Collections.sort(viruseshost1, Virus.AantalComparator());
		}
		try {
			//Onderstaande code voegt de objecten die overeenkomen met de gekozen hosts toe aan lijsten
			for(int i = 0; i < virusesSelectie.size(); i++) {
				if(virusesSelectie.get(i).gethost_list().contains(keuze1) && viruseshost1.contains(virusesSelectie.get(i))==false) {
					viruseshost1.add(virusesSelectie.get(i));
				}
				if(virusesSelectie.get(i).gethost_list().contains(keuze2) && viruseshost2.contains(virusesSelectie.get(i))==false) {
					viruseshost2.add(virusesSelectie.get(i));
				}
			}
			
			for(int i = 0; i < viruseshost1.size(); i++) {
				if(viruseshost2.contains(viruseshost1.get(i))){
					virusesRes.add(viruseshost1.get(i));
				}
			}
			if(Window.getRdbtnID()) {
				Collections.sort(viruseshost1, Virus.IDComparator());
				Collections.sort(viruseshost2, Virus.IDComparator());
			}
		}catch(IndexOutOfBoundsException e) {
			System.out.println("De lijsten bevatten geen waarden");
			e.printStackTrace();
		}
		//geef de lijsten aan de GUI
		Window.setLists(viruseshost1, viruseshost2, virusesRes);
	}
}
