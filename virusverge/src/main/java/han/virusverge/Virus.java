package han.virusverge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 *De Virus class is nodig om de regels op te slaan  in objecten.
 * 
 * @author Mark de Korte
 */
public class Virus{
	//Declaratie Variabelen

    /**
     *het virus id in Integers
     */
    public int virus_id;

    /**
     *de naam van het virus als String
     */
    public String naam;

    /**
     *Classificatie van het virus String
     */
    public String classificatie;

    /**
     *Host tax ID Integer
     */
    public int host_tax_id;

    /**
     *Host naam van het virus
     */
    public String host_name;

    /**
     *Lijst van hosts ID's in een Arraylist
     */
    public ArrayList<Integer> host_list;

    /**
     *
     * @param line functie neemt een regel van het bestand aan en zet die om in de verschillende variabelen door middel van een split() functie
     */
    public Virus(String line) {
		String[] split = line.split("\t",-1);
		virus_id = Integer.parseInt(split[0]);
		naam = split[1];//naam van het virus
		if(!split[7].isEmpty()) {
			host_tax_id = Integer.parseInt(split[7]);//host ID
		}
		host_name = split[8];//Host naam
		host_list = new ArrayList<Integer>(Arrays.asList(host_tax_id));//Array met hosts waarbij de originele host word toegevoegd
		String[] lineage = split[2].split(";");
		classificatie = lineage[1];//Classificatie van het virus
		}

    //Getters en setters van de variabelen in het object Virus

    /**
     *
     * @return virus ID in Integers
     */
	public int getvirus_id() {
		return virus_id;
		}

    /**
     *
     * @param virus_id verandert de virus_id van het huidige object naar de parameter
     */
    public void setvirus_id(int virus_id) {
		this.virus_id = virus_id;
		}
	
    /**
     *
     * @return Geeft naam van Virus
     */
    public String getVirusnaam() {
		return naam;
		}

    /**
     *
     * @param naam verandert de naam van het virus van het huidige object
     */
    public void setVirusnaam(String naam) {
		this.naam = naam;
		}
	
    /**
     *
     * @return geeft host tax ID van huidig object in Integers terug
     */
    public int gethost_tax_id() {
		return host_tax_id;
		}

    /**
     *
     * @param host_id Voegt een nieuwe host id aan lijst van huidig object toe
     */
    public void sethost_list(int host_id) {
		this.host_list.add(host_id);
		}
	
    /**
     *
     * @return Een array met Host ID's in integers van het huidige object
     */
    public ArrayList<Integer> gethost_list() {
		return host_list;
		}
	
    /**
     *
     * @return Naam van de host in huidig object
     */
    public String gethost_name() {
		return host_name;
		}

    /**
     *
     * @return classificatie van het huidig object
     */
    public String getClassificatie() {
		return classificatie;
	}
	@Override
	public String toString() {
		return "Virus [virus_id="+virus_id+",Virusnaam=" + naam + ", host_tax_id=" + host_tax_id + "]";
	}

    /**
     *
     * @param classificatie zet de classificatie van het huidige element met de  ingevoerde String
     */
    public void setClassificatie(String classificatie) {
		this.classificatie = classificatie;
	}

    /**
     * 
     * @return  1, 0 of -1 afhankelijk van deze return word het element gesorteerd
     */
    static Comparator<Virus> IDComparator() {
        return new Comparator<Virus>() {
			public int compare(Virus o1, Virus o2) {
				return o1.virus_id - o2.virus_id;
			}
        };
    }
    
    /**
     * 
     * @return  1, 0 of -1 afhankelijk van deze return word het element gesorteerd
     */
    static Comparator<Virus> ClassComparator() {
        return new Comparator<Virus>() {
			public int compare(Virus o1, Virus o2) {
				return o1.getClassificatie().compareTo(o2.getClassificatie());
			}
        };
    }
    
    /**
     * 
     * @return  1, 0 of -1 afhankelijk van deze return word het huidige element gesorteerd
     */
    static Comparator<Virus> AantalComparator() {
        return new Comparator<Virus>() {
			public int compare(Virus o1, Virus o2) {
				return o1.host_list.size() - o2.host_list.size();
			}
        };
    }
}
