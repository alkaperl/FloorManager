package org.marso.floormanager;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.floreantpos.config.ui.ConfigurationView;
import com.floreantpos.model.ShopFloor;

public class FloorManagerConfigurationView extends ConfigurationView implements ActionListener, ListSelectionListener{
	private static final long serialVersionUID = 1L;

    private DefaultListModel<ShopFloor> listModel;
    private JList<ShopFloor> floorsList;
    //private FloorView floorView;	

	public FloorManagerConfigurationView() {
		listModel = new DefaultListModel<ShopFloor>();
        floorsList = new JList<ShopFloor>(this.listModel);
        //floorView = new FloorView(this.floorsList);
        initUI();
    }
    
    private void initUI() {
        setLayout(new BorderLayout(10, 10));

        final JPanel floorListPanel = new JPanel(new BorderLayout(5, 5));
        floorListPanel.setBorder(BorderFactory.createTitledBorder("Floors"));
        
        final JScrollPane listScrollPane = new JScrollPane(this.floorsList);
        listScrollPane.setPreferredSize(new Dimension(150, 100));
        floorListPanel.add(listScrollPane);
        this.floorsList.addListSelectionListener(this);
        
        final JPanel buttonPanel = new JPanel();
        final JButton btnAddFloor = new JButton("ADD");
        btnAddFloor.addActionListener(this);
        buttonPanel.add(btnAddFloor);
        
        final JButton btnRemoveFloor = new JButton("REMOVE");
        btnRemoveFloor.addActionListener(this);
        buttonPanel.add(btnRemoveFloor);
        
        floorListPanel.add(buttonPanel, "South");
        this.add((Component)floorListPanel, (Object)"West");
        
        //add((Component)this.floorView);
    }
    
	@Override
	public void actionPerformed(final ActionEvent e) {
		//TODO
		System.out.println("FloorManagerConfigurationView.actionPerformed.by."+e.getSource().getClass().getName());		
	}
	
	@Override
	public void valueChanged(final ListSelectionEvent e) {
		System.out.println("FloorManagerConfigurationView.valueChanged.by."+e.getSource().getClass().getName());		
    }
    
    public String getName() {
        return "Floor Plan";	
    }

	@Override
	public boolean save() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void initialize() throws Exception {
		// TODO Auto-generated method stub		
	}    

}
