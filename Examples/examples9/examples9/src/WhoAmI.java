import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;
import com.sun.glass.events.KeyEvent;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

import net.miginfocom.swing.MigLayout;

public class WhoAmI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WhoAmI frame = new WhoAmI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public WhoAmI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450,300);
		setTitle("japp");
		setLocationRelativeTo(null);
		//setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		createMenu();
		
		contentPane.setLayout(new MigLayout("", "[][68.00][185.00,grow][29.00][][]", "[][][][][pref!,grow][][]"));
		
		JLabel lblNewLabel = new JLabel("Hello World");
		lblNewLabel.setForeground(Color.WHITE);
		contentPane.add(lblNewLabel, "cell 0 1 2 2,alignx right,aligny center");
		
		textField = new JTextField();
		contentPane.add(textField, "cell 2 2 2 1,growx");
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new buttonHandler()); // call it registering the button 
		contentPane.add(btnNewButton, "cell 0 4,growx");
	
	}
	private class buttonHandler implements ActionListener{
	
	
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String input = textField.getText();
			System.out.println(input);
		}
	}
	public void createMenu(){
		JMenuBar mainMenuBar = new JMenuBar();
		setJMenuBar(mainMenuBar);
		
		JMenu fileMenu = new JMenu("File");
		JMenu m2 = new JMenu("copy");
		JMenu m3 = new JMenu("paste");
		JMenu menu = new JMenu("items");
		mainMenuBar.add(m2);
		mainMenuBar.add(m3);

		fileMenu.setMnemonic('F');
		//fileMenu.add(menu);
		
		mainMenuBar.add(fileMenu);
		JMenuItem load = new JMenuItem("Load");
		JMenuItem l2 = new JMenuItem("copy");
		l2.addActionListener(e -> {
			JOptionPane.showMessageDialog(WhoAmI.this,"You Messed Up","error",0);
		});
		JMenuItem l3= new JMenuItem("paste");

		fileMenu.add(l2);
		fileMenu.add(l3);

		load.setMnemonic(KeyEvent.VK_L);
		load.addActionListener(e -> {
			System.out.println("im outta here");
			System.exit(0);
		});
		fileMenu.add(load);
		
		
	}

}
