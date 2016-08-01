package org.marso.floormanager.tabletype;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class EditTableTypePanel extends JPanel implements ActionListener {

	/**
	 * TODO: is serialised necessary??? 
	 */
	private static final long serialVersionUID = 1L;
	
	public EditTableTypePanel() {
		this.setName("EditFloorTypePanel");
		initializePanel();
	}
	
	public void initializePanel(){
		setLayout(new BorderLayout(10, 10));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		
		JPanel buttonPanel = new JPanel();
		//TODO: ADD messages.properties
		JButton btnNew = new JButton( "NEW" ); //$NON-NLS-1$
		JButton btnSave = new JButton( "SAVE" ); //$NON-NLS-1$		
		JButton btnCancel = new JButton( "CANCEL CHANGES" ); //$NON-NLS-1$		
		JButton btnDelete = new JButton( "DELETE" ); //$NON-NLS-1$	
		
		buttonPanel.add(btnNew);
		buttonPanel.add(btnSave);
		buttonPanel.add(btnDelete);
		buttonPanel.add(btnCancel);	
		add(buttonPanel, BorderLayout.SOUTH);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {		
		System.out.println("EditTableTypePanel.actionPerformed():"+e.getActionCommand()+":");
	}

}

