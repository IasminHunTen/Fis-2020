package MainMenu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Choice;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Label;
import java.awt.Color;
import java.awt.*;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;

public class EditItem extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JButton btnAplly;
	private JButton btnUndo;
	private JButton button_3;
	private JButton button_2;
	private JComboBox<String> jcb1;
	private JButton btnRemove;
	private JButton btnNewButton_1;
	private JComboBox<String> jcb;
	private JButton button_1;
	private JButton btnNewButton;
	private JButton button;
	private JLabel lblNewLabel;
	private ItemList list;
	private DataMeneger dm; 
	private ArrayList<String> act=new ArrayList<String>();
	private ArrayList<Integer> sez=new ArrayList<Integer>();
	private Item it;
	private static int idx;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditItem  frame = new EditItem(idx);
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
	public EditItem(int idx) {
		dm=new DataMeneger();
		list=dm.readItems();
	
		EditItem.idx=idx;
		it=list.getItems().get(idx);
		act=(ArrayList<String>) it.getActors().clone();
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEditStage = new JLabel("Edit Stage");
		lblEditStage.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditStage.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblEditStage.setBounds(90, 11, 208, 26);
		contentPane.add(lblEditStage);
		
		JLabel lblTitle = new JLabel("title :");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTitle.setBounds(33, 46, 46, 14);
		contentPane.add(lblTitle);
		
		textField = new JTextField();
		textField.setBounds(82, 45, 237, 20);
		textField.setText(it.getTitle());
		contentPane.add(textField);
		textField.setColumns(10);
		
	    btnNewButton = new JButton("Edit");
	    btnNewButton.addActionListener(this);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(347, 44, 66, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblGen = new JLabel("gen :");
		lblGen.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGen.setBounds(33, 71, 46, 26);
		contentPane.add(lblGen);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(82, 76, 237, 20);
		textField_1.setText(it.getGen());
		contentPane.add(textField_1);
		
	    button = new JButton("Edit");
	    button.addActionListener(this);
		button.setFont(new Font("Tahoma", Font.BOLD, 14));
		button.setBounds(347, 73, 66, 23);
		contentPane.add(button);
		
		JLabel lblMainPlot = new JLabel("main plot :");
		lblMainPlot.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMainPlot.setBounds(0, 108, 79, 26);
		contentPane.add(lblMainPlot);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(82, 113, 237, 20);
		textField_2.setText(it.getMainPlot());
		contentPane.add(textField_2);
		
	    button_1 = new JButton("Edit");
	    button_1.addActionListener(this);
		button_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_1.setBounds(347, 112, 66, 23);
		contentPane.add(button_1);
		
		JLabel lblActors = new JLabel("actors : ");
		lblActors.setHorizontalAlignment(SwingConstants.RIGHT);
		lblActors.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblActors.setBounds(10, 162, 67, 14);
		contentPane.add(lblActors);
		
	    jcb = new JComboBox<String>();
		jcb.setBounds(83, 162, 110, 20);
		jcb.setEditable(true);
		jcb.addActionListener(this);
		for(int i=0;i<it.getActors().size();i++)
			jcb.addItem(it.getActors().get(i));
		contentPane.add(jcb);
		
		btnNewButton_1 = new JButton("add");
		btnNewButton_1.addActionListener(this);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_1.setBounds(15, 192, 59, 23);
		contentPane.add(btnNewButton_1);
		
		btnRemove = new JButton("remove");
		btnRemove.addActionListener(this);
		btnRemove.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnRemove.setBounds(0, 226, 85, 23);
		contentPane.add(btnRemove);
		
	    lblNewLabel = new JLabel("season :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(224, 164, 66, 14);
		contentPane.add(lblNewLabel);
		
	    jcb1 = new JComboBox<String>();
		jcb1.setBounds(291, 162, 28, 20);
		jcb1.setEditable(true);
		jcb1.addActionListener(this);
		if(it instanceof Series) {
			Series s=(Series)it;
			sez = (ArrayList<Integer>) s.getSeasons().clone();
		    for(int i=0;i<s.getSeasons().size();i++)
		      jcb1.addItem(String.valueOf(s.getSeasons().get(i)));
		}
		contentPane.add(jcb1);
		
		button_2 = new JButton("add");
		button_2.addActionListener(this);
		button_2.setFont(new Font("Tahoma", Font.BOLD, 10));
		button_2.setBounds(215, 192, 59, 23);
		contentPane.add(button_2);
		
	    button_3 = new JButton("remove");
	    button_3.addActionListener(this);
		button_3.setFont(new Font("Tahoma", Font.BOLD, 10));
		button_3.setBounds(201, 226, 85, 23);
		contentPane.add(button_3);
		
		if(it instanceof Movie) {
			lblNewLabel.setVisible(false);
			jcb1.setVisible(false);
			button_2.setVisible(false);
			button_3.setVisible(false);
		}
		
	    btnUndo = new JButton("undo");
	    btnUndo.addActionListener(this);
		btnUndo.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnUndo.setBounds(335, 194, 89, 23);
		contentPane.add(btnUndo);
		
		btnAplly = new JButton("aplly");
		btnAplly.addActionListener(this);
		btnAplly.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAplly.setBounds(335, 228, 89, 23);
		contentPane.add(btnAplly);
	}

	private void setDefaultValue() {
		it=list.getItems().get(idx);
		textField.setText(it.getTitle());
		textField_1.setText(it.getGen());
		textField_2.setText(it.getMainPlot());
		jcb.removeAllItems();
		act=(ArrayList<String>) it.getActors().clone();
		for(int i=0;i<act.size();i++)
			jcb.addItem(act.get(i));
		if(it instanceof Series) {
			jcb1.removeAllItems();
			Series s=(Series)it;
		    sez=(ArrayList<Integer>) s.getSeasons().clone();
		    for(int i=0;i<sez.size();i++)
		      jcb1.addItem(String.valueOf(sez.get(i)));
		}
		
	}
	
	
	public void actionPerformed(ActionEvent e) {
		String aux;
		if(e.getSource()==btnNewButton) {
			it.setTitle(textField.getText());
		}
		
		if(e.getSource()==button) {
			it.setGen(textField_1.getText());
		}
		if(e.getSource()==button_1) {
			it.setMainPlot(textField_2.getText());
		}
		
		if(e.getSource()==btnNewButton_1) {
			aux=JOptionPane.showInputDialog("add new actor");
			if(!aux.equals("")) {
			act.add(aux);
			jcb.addItem(aux);
			}
		}
		
		if(e.getSource()==btnRemove) {	
			act.remove(jcb.getSelectedIndex());
			jcb.removeItemAt(jcb.getSelectedIndex());
		}
		
		if (it instanceof Series) {
			Series s=(Series)it;
			
			if(e.getSource()==button_2) {
				aux=JOptionPane.showInputDialog("add the number of episode of the new season" );
				if(!aux.equals("")) {
				sez.add(Integer.parseInt(aux));
				jcb1.addItem(aux);
				}
			}
			
			if(e.getSource()==button_3) {
				jcb1.removeItemAt(jcb1.getSelectedIndex());
				sez.remove(jcb1.getSelectedIndex());
			}
		}
		
		if(e.getSource()==btnUndo) {
			this.setDefaultValue();
		}
		
		if(e.getSource()==btnAplly) {
		    it.setActors(act);
		    if(it instanceof Series) {
		    	Series s=(Series)it;
		    	s.setSeasons(sez);
		    }
			ArrayList<Item> temp=list.getItems();
			temp.set(idx, it);
			
			list.setItems(temp);
			dm.writeItems(list);
			this.setVisible(false);
			this.dispose();
		    new MainPage().setVisible(true);
		}
		
		
		
	}
}
