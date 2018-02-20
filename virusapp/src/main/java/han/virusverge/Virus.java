package han.virusverge;

import java.util.ArrayList;
import java.util.Comparator;


public class Virus{
	//Declaratie Variabelen
	public int virus_id;
	public String naam;
	public String classificatie;
	public int host_tax_id;
	public String host_name;
	public ArrayList<Integer> host_list;
	public Virus(String line) {
		//onderstaande code neemt een string met een regel van het bestand, en zet die om in de verschillende variabelen
		String[] split = line.split("\t",-1);
		virus_id = Integer.parseInt(split[0]);
		naam = split[1];//naam van het virus
		if(!split[7].isEmpty()) {
			host_tax_id = Integer.parseInt(split[7]);//host ID
		}
		host_name = split[8];//Host naam
		host_list = new ArrayList<Integer>();//Array met hosts
		String[] lineage = split[2].split(";");
		classificatie = lineage[1];//Classificatie van het virus
		}

	//Getters en setters van de variabelen in het object Virus
	public int getvirus_id() {
		return virus_id;
		}
	public void setvirus_id(int virus_id) {
		this.virus_id = virus_id;
		}
	
	public String getVirusnaam() {
		return naam;
		}
	public void setVirusnaam(String naam) {
		this.naam = naam;
		}
	
	public int gethost_tax_id() {
		return host_tax_id;
		}
	public void sethost_list(int host_id) {
		this.host_list.add(host_id);
		}
	
	public ArrayList<Integer> gethost_list() {
		return host_list;
		}
	
	public String gethost_name() {
		return host_name;
		}

	public String getClassificatie() {
		return classificatie;
	}
	@Override
	public String toString() {
		return "Virus [virus_id="+virus_id+",Virusnaam=" + naam + ", host_tax_id=" + host_tax_id + "]";
	}


	public void setClassificatie(String classificatie) {
		this.classificatie = classificatie;
	}

	//Deze Comparator functies zorgen voor de sortering van de lijsten
    static Comparator<Virus> IDComparator() {
        return new Comparator<Virus>() {
			public int compare(Virus o1, Virus o2) {
				return o1.virus_id - o2.virus_id;
			}
        };
    }
    static Comparator<Virus> ClassComparator() {
        return new Comparator<Virus>() {
			public int compare(Virus o1, Virus o2) {
				return o1.getClassificatie().compareTo(o2.getClassificatie());
			}
        };
    }
    static Comparator<Virus> AantalComparator() {
        return new Comparator<Virus>() {
			public int compare(Virus o1, Virus o2) {
				return o1.host_list.size() - o2.host_list.size();
			}
        };
    }
}
