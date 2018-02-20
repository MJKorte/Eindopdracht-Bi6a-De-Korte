package han.virusverge;


import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class VirusGUI {
	
	//declaratie van variabelen
	JFrame frmVirusverge;
	JTextField txtURL;
	JTextArea txtVirus1;
	JTextArea txtVirus2;
	JTextArea txtRes;
	JRadioButton rdbtnID;
	JRadioButton rdbtnClass;
	JRadioButton rdbtnHosts;
	final ButtonGroup buttonGroup = new ButtonGroup();
	JComboBox<String> cbxClassificatie;
	JComboBox<String> cbxHost1;
	JComboBox<String> cbxHost2;
	
	
	public VirusGUI() {
		//Aanmaken van het frame
		frmVirusverge = new JFrame();
		frmVirusverge.setTitle("VirusVerge");
		frmVirusverge.setBounds(100, 100, 807, 468);
		frmVirusverge.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//verschillende panels bevatten verschillende onderdelen
		JPanel pnlDownload = new JPanel();
		JPanel pnlClassificatie = new JPanel();
		JPanel pnlSort = new JPanel();
		pnlSort.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Sort by", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		//Labels voor beschrijvingen bij het programma
		JLabel lblVirus = new JLabel("Virus");
		JLabel labelVirus2 = new JLabel("Virus");
		JLabel lblSimilarities = new JLabel("Similarities");
		JLabel lblURL = new JLabel("URL: ");
		JLabel lblViralClassification = new JLabel("Viral Classification:");
		JLabel lblHostIds = new JLabel("Host ID's :");
		
		//ScrollPanes voor het scrollen in de viruslijsten
		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JScrollPane scrollPane3 = new JScrollPane();
		scrollPane3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		//Textfields voor het ophalen van de URL en het weergeven van de virussen
		txtURL = new JTextField();
		txtURL.setText("ftp://ftp.genome.jp/pub/db/virushostdb/");
		txtURL.setColumns(10);
		
		txtVirus2 = new JTextArea();
		scrollPane2.setViewportView(txtVirus2);

		txtVirus1 = new JTextArea(); 
		scrollPane1.setViewportView(txtVirus1);
		
		txtRes = new JTextArea();
		scrollPane3.setViewportView(txtRes);
		txtRes.setColumns(10);

		//Radiobuttons worden geinstantieerd en toegevoegd aan de radiobutton groep
		rdbtnID = new JRadioButton("ID");
		buttonGroup.add(rdbtnID);

		rdbtnClass = new JRadioButton("Classification");
		buttonGroup.add(rdbtnClass);

		rdbtnHosts = new JRadioButton("Number of hosts");
		buttonGroup.add(rdbtnHosts);

		//Comboboxes voor het selecteren van de classificatie en de virussen
		cbxHost1 = new JComboBox<String>();
		cbxHost2 = new JComboBox<String>();
		cbxClassificatie = new JComboBox<String>();
		//ActionListener om er voor te zorgen dat de tekst up to date blijft met welke classificatie is gekozen
		cbxClassificatie.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) { {
				Viruslogica.classificatieSort(cbxClassificatie.getSelectedItem().toString());
			}}
		});

		//JButton voor het downloaden van het bestand van de site
		JButton btnDownload = new JButton("Download File");
		btnDownload.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) { 
				try {
					Viruslogica.ftpBestand();
				} catch (MalformedURLException e1) {
					System.out.println("Dit is geen juiste URL");
					e1.printStackTrace();
				}
			} 
		} );
		
		//JButton voor het sorteren van de lijsten
		JButton btnSort = new JButton("Sort");
		btnSort.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) { 
				Viruslogica.sorteerViruses();
			} 
		} );
		
		//Onderstaande code is gegenereerd en is verantwoordelijk voor de layout en weergave van bovenstaande componenten
		JPanel pnlOutput = new JPanel();
		GroupLayout groupLayout = new GroupLayout(frmVirusverge.getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(pnlDownload, GroupLayout.DEFAULT_SIZE, 789, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(pnlSort, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(pnlOutput, GroupLayout.DEFAULT_SIZE, 611, Short.MAX_VALUE)
						.addContainerGap())
				.addComponent(pnlClassificatie, GroupLayout.DEFAULT_SIZE, 789, Short.MAX_VALUE)
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(pnlDownload, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(pnlClassificatie, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(pnlSort, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
								.addComponent(pnlOutput, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)))
				);
		
		GroupLayout gl_pnlOutput = new GroupLayout(pnlOutput);
		gl_pnlOutput.setHorizontalGroup(
				gl_pnlOutput.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlOutput.createSequentialGroup()
						.addGroup(gl_pnlOutput.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnlOutput.createSequentialGroup()
										.addGap(72)
										.addComponent(lblVirus))
								.addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_pnlOutput.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_pnlOutput.createSequentialGroup()
										.addGap(76)
										.addComponent(labelVirus2, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, 143, Short.MAX_VALUE)
										.addComponent(lblSimilarities)
										.addGap(115))
								.addGroup(Alignment.LEADING, gl_pnlOutput.createSequentialGroup()
										.addGap(18)
										.addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(scrollPane3, GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
										.addContainerGap())))
				);
		gl_pnlOutput.setVerticalGroup(
				gl_pnlOutput.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlOutput.createSequentialGroup()
						.addGroup(gl_pnlOutput.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblVirus)
								.addComponent(labelVirus2)
								.addComponent(lblSimilarities))
						.addGap(9)
						.addGroup(gl_pnlOutput.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane3)
								.addGroup(gl_pnlOutput.createParallelGroup(Alignment.LEADING, false)
										.addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
										.addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)))
						.addContainerGap())
				);
		pnlOutput.setLayout(gl_pnlOutput);

		GroupLayout gl_pnlSort = new GroupLayout(pnlSort);
		gl_pnlSort.setHorizontalGroup(
				gl_pnlSort.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlSort.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_pnlSort.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnlSort.createSequentialGroup()
										.addGroup(gl_pnlSort.createParallelGroup(Alignment.LEADING, false)
												.addComponent(rdbtnID)
												.addComponent(rdbtnClass)
												.addComponent(rdbtnHosts, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addContainerGap(24, Short.MAX_VALUE))
								.addGroup(gl_pnlSort.createSequentialGroup()
										.addComponent(btnSort, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addGap(20))))
				);
		gl_pnlSort.setVerticalGroup(
				gl_pnlSort.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlSort.createSequentialGroup()
						.addContainerGap()
						.addComponent(rdbtnID)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(rdbtnClass)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(rdbtnHosts)
						.addGap(18)
						.addComponent(btnSort)
						.addContainerGap(36, Short.MAX_VALUE))
				);
		pnlSort.setLayout(gl_pnlSort);
		
		GroupLayout gl_pnlClassificatie = new GroupLayout(pnlClassificatie);
		gl_pnlClassificatie.setHorizontalGroup(
				gl_pnlClassificatie.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlClassificatie.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_pnlClassificatie.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnlClassificatie.createSequentialGroup()
										.addComponent(lblHostIds)
										.addGap(18)
										.addComponent(cbxHost1, 0, 344, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(cbxHost2, GroupLayout.PREFERRED_SIZE, 338, GroupLayout.PREFERRED_SIZE)
										.addContainerGap(25, Short.MAX_VALUE))
								.addGroup(gl_pnlClassificatie.createSequentialGroup()
										.addComponent(lblViralClassification, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(cbxClassificatie, GroupLayout.PREFERRED_SIZE, 358, GroupLayout.PREFERRED_SIZE)
										.addContainerGap())))
				);
		gl_pnlClassificatie.setVerticalGroup(
				gl_pnlClassificatie.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlClassificatie.createSequentialGroup()
						.addGap(17)
						.addGroup(gl_pnlClassificatie.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblViralClassification)
								.addComponent(cbxClassificatie, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
						.addGroup(gl_pnlClassificatie.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblHostIds)
								.addComponent(cbxHost1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addComponent(cbxHost2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
						.addContainerGap())
				);
		pnlClassificatie.setLayout(gl_pnlClassificatie);

		GroupLayout gl_pnlDownload = new GroupLayout(pnlDownload);
		gl_pnlDownload.setHorizontalGroup(
			gl_pnlDownload.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlDownload.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblURL)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtURL, GroupLayout.PREFERRED_SIZE, 568, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
					.addComponent(btnDownload)
					.addContainerGap())
		);
		gl_pnlDownload.setVerticalGroup(
			gl_pnlDownload.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlDownload.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlDownload.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblURL)
						.addComponent(txtURL, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDownload))
					.addContainerGap(14, Short.MAX_VALUE))
		);
		pnlDownload.setLayout(gl_pnlDownload);
		frmVirusverge.getContentPane().setLayout(groupLayout);
		frmVirusverge.setVisible(true);
	}

	//Getters en setters van de componenten
	public JTextField gettxtURL() {
		return txtURL;
	}

	public String getKeuze_Classificatie() {
		return cbxClassificatie.getSelectedItem().toString();
	}

	public int getKeuze_host_id_1() {
		String[] split = cbxHost1.getSelectedItem().toString().split("\\(");
		int keuze = Integer.parseInt(split[0]);
		return keuze;
	}

	public int getKeuze_host_id_2() {
		String[] split = cbxHost2.getSelectedItem().toString().split("\\(");
		int keuze = Integer.parseInt(split[0]);
		return keuze;
	}
	public boolean getRdbtnID() {
		return rdbtnID.isSelected();
	}
	public boolean getRdbtnClass() {
		return rdbtnClass.isSelected();
	}
	public boolean getRdbtnHosts() {
		return rdbtnHosts.isSelected();
	}

	void setkeuze_Viral_class(ArrayList<String> a) {
		for(int i = 0; i < a.size(); i++) {
			cbxClassificatie.addItem(a.get(i));
		}
	}

	//Voegt de keuzes aan de comboboxes toe
	void setkeuze_HOST_ID(ArrayList<Integer> Hosts, HashMap<Integer,String> hosts) {
		cbxHost1.removeAllItems();
		cbxHost2.removeAllItems();
		for(int i = 0; i < Hosts.size(); i++) {
			cbxHost1.addItem(Hosts.get(i).toString()+"("+hosts.get(Hosts.get(i))+")");
			cbxHost2.addItem(Hosts.get(i).toString()+"("+hosts.get(Hosts.get(i))+")");
		}
	}

	//Deze functie haalt de objecten uit de lijsten en voegt deze in Textareas
	public void setLists(ArrayList<Virus> virusesHost1, ArrayList<Virus> virusesHost2, ArrayList<Virus> virusesRes) {
		txtVirus1.setText(null);
		txtVirus2.setText(null);
		txtRes.setText(null);
		for(int i = 0; i<virusesHost1.size(); i++) {
			txtVirus1.append(virusesHost1.get(i).getvirus_id()+"\n");
		}
		for(int i = 0; i<virusesHost2.size(); i++) {
			txtVirus2.append(virusesHost2.get(i).getvirus_id()+"\n");
		}
		for(int i = 0; i<virusesRes.size(); i++) {
			txtRes.append(virusesRes.get(i).getvirus_id()+"\n");
		}
	}
}
