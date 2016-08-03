package org.marso.floormanager.floor;

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

import com.floreantpos.bo.ui.Command;
import com.floreantpos.model.ShopFloor;

public class FloorPanel extends JPanel implements ActionListener, ListSelectionListener {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private DefaultListModel<ShopFloor> listModel;
    private JList<ShopFloor> floorsList;
    private FloorView floorView;	

	public FloorPanel() {
		listModel = new DefaultListModel<ShopFloor>();
        floorsList = new JList<ShopFloor>(this.listModel);
        floorView = new FloorView(this.floorsList);
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
        
        add((Component)this.floorView);
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Command command = Command.fromString(e.getActionCommand());
		System.out.println("EditFloorPanel.actionPerformed():"+command.toString());
	}
	
	@Override
	public void valueChanged(final ListSelectionEvent e) {
		System.out.println("FloorManagerConfigurationView.valueChanged.by."+e.getSource().getClass().getName());		
    }
}
