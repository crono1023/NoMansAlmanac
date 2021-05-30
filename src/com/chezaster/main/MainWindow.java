package com.chezaster.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.Border;

import com.chezaster.objects.Planet;
import com.chezaster.objects.StarSystem;
import com.chezaster.scripts.PlanetGenScripts;
import java.awt.Toolkit;


public class MainWindow {
	
	private StarSystem farFarAway = PlanetGenScripts.createStarSystem();
	private ArrayList<Planet> planets = farFarAway.getPlanets();
	private int currentIndex = 0;
	DefaultListModel<String> moonListModel;

	private JFrame frmNoMansAlmanac;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				
				try {
					MainWindow window = new MainWindow();
					window.frmNoMansAlmanac.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		
		frmNoMansAlmanac = new JFrame();
		frmNoMansAlmanac.setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/com/chezaster/resources/cat128.png")));
		frmNoMansAlmanac.setTitle("No Man's Almanac");
		frmNoMansAlmanac.setBounds(100, 100, 304, 311);
		frmNoMansAlmanac.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		// First example planet at index 0
		Planet currentPlanet = planets.get(0);
		
		JLabel lblPlanetName = new JLabel("New label");
		lblPlanetName.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblPlanetName.setText(currentPlanet.getName());
		
		panel.add(Box.createRigidArea(new Dimension(0, 15)));
		
		JLabel lblPlanetLabel = new JLabel("Planet Name");
		lblPlanetLabel.setAlignmentX(0.5f);
		panel.add(lblPlanetLabel);
		panel.add(lblPlanetName);
		
		panel.add(Box.createRigidArea(new Dimension(0, 25)));
		
		JLabel lblFounderLabel = new JLabel("Discoverd By:");
		lblFounderLabel.setAlignmentX(0.5f);
		panel.add(lblFounderLabel);
		
		
		
		JLabel lblFounder = new JLabel("New label");
		lblFounder.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblFounder.setText(currentPlanet.getFounder());
		panel.add(lblFounder);
		
		frmNoMansAlmanac.getContentPane().add(panel, BorderLayout.NORTH);
		
		Component rigidArea = Box.createRigidArea(new Dimension(0, 25));
		panel.add(rigidArea);
		
		JLabel lblNumMoonsLabel = new JLabel("Number of Moons:");
		lblNumMoonsLabel.setAlignmentX(0.5f);
		panel.add(lblNumMoonsLabel);
		
		JLabel lblNumberMoons = new JLabel("New label");
		lblNumberMoons.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNumberMoons.setText("" + currentPlanet.getNumMoons());
		panel.add(lblNumberMoons);
		
		//Component rigidArea_1 = Box.createRigidArea(new Dimension(0, 25));
		//panel.add(rigidArea_1);
		
		moonListModel = new DefaultListModel<>();
		
		/*if (currentPlanet.getNumMoons() >= 1) {
			for (int i = 0; i < currentPlanet.getNumMoons(); ++i) {
				moonListModel.addElement(currentPlanet.getMoonByIndex((short)i).getName());
			}
		}*/
		
		JLabel lblMoonNamesLabel = new JLabel("Moon Names:");
		lblMoonNamesLabel.setAlignmentX(0.5f);
		panel.add(lblMoonNamesLabel);

		
		
		
		JList<String> list = new JList<>(moonListModel);
		list.setVisibleRowCount(4);
		list.setFixedCellWidth(100);
		
		list.setCellRenderer(new MyListRenderer());
		
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane listArea = new JScrollPane(list);
		Border emptyBorder = BorderFactory.createEmptyBorder();
		listArea.setBorder(emptyBorder);
		
		
		
		panel.add(listArea);		
		
		
		moonListModel = populateMoonList(currentPlanet);
		list.setModel(moonListModel);
			
		//panel.add(Box.createRigidArea(new Dimension(0, 15)));
		
		JPanel navigationPanel = new JPanel();
		navigationPanel.setBorder(emptyBorder);
		
		frmNoMansAlmanac.getContentPane().add(navigationPanel, BorderLayout.SOUTH);
		
		JButton btnPrevious = new JButton("Previous Planet");
		navigationPanel.add(btnPrevious);
		
		JButton btnNext = new JButton("Next Planet");
		navigationPanel.add(btnNext);
		
		if (planets.size() >= 2) {
			btnNext.setEnabled(true);
		}
		
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentIndex > 0 ) {
					currentIndex--;
					
					Planet currentPlanet = planets.get(currentIndex);
					
					lblPlanetName.setText(currentPlanet.getName());
					lblFounder.setText(currentPlanet.getFounder());
					lblNumberMoons.setText("" + currentPlanet.getNumMoons());
					
					moonListModel.clear();
					moonListModel = populateMoonList(currentPlanet);
					list.setModel(moonListModel);
					list.setCellRenderer(new MyListRenderer());
					
					if (currentIndex == 0) {
						btnPrevious.setEnabled(false);
					}
					
					if (!btnNext.isEnabled()) {
						btnNext.setEnabled(true);
					}
				}
			}
		});
		
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentIndex < planets.size() - 1) {
					currentIndex++;
					lblPlanetName.setText(planets.get(currentIndex).getName());
					lblFounder.setText(planets.get(currentIndex).getFounder());
					lblNumberMoons.setText("" + planets.get(currentIndex).getNumMoons());
					
					moonListModel.clear();
					moonListModel = populateMoonList(planets.get(currentIndex));
					list.setModel(moonListModel); // Force the list to update since 
												  // the model updated in another function					
					
					
					
					if (currentIndex == planets.size() - 1) {
						btnNext.setEnabled(false);
					}
					if (!btnPrevious.isEnabled()) {
						btnPrevious.setEnabled(true);
					}
					
				}
			}
		});
	}
	
	// Function to create moon list model that will populate the JList if the planet has moons.
	private DefaultListModel<String> populateMoonList (Planet planet) {
		DefaultListModel<String> newListModel = new DefaultListModel<String>();
		if(planet.getNumMoons() >= 1) {
			newListModel = new DefaultListModel<String>();
			for (short i = 0; i < planet.getNumMoons(); ++i) {
				newListModel.addElement(planet.getMoonByIndex(i).getName());
			}
			for (short i = 0; i < 4 - planet.getNumMoons(); ++i) {
				newListModel.addElement(" ");
			}
		} 
		else {
			for(int i = 0; i < 4; i++) {
				newListModel.addElement(" ");
			}
		}
		return newListModel;
	}
				
		
	
	/* Custom renderer to change background and foreground colors of cells in the JList. (Makes it gray when there are no moons
	/  so the list appears invisible.
	*/
	private class MyListRenderer extends DefaultListCellRenderer 
	{
		private static final long serialVersionUID = 1L;

		public Component getListCellRendererComponent ( JList<?> list, 
				Object value, int index, boolean isSelected,
				boolean cellHasFocus )
		{
			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			
			if (!isSelected) {
				setBackground(new Color(214,217,223));
				
			}
			else {
				setBackground(Color.CYAN);
			}
			
			this.setHorizontalAlignment(SwingConstants.CENTER);
			
			return this;
		}
		
	}

}
