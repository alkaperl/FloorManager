package org.marso.floormanager;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class EditTablePanel  extends JPanel implements ActionListener {

	/**
	 * TODO: is serialised necessary??? 
	 */
	private static final long serialVersionUID = 1L;

	public EditTablePanel() {
		this.setName("EditFloorPanel");
		initializePanel();
	}
	
	public void initializePanel(){
		setLayout(new BorderLayout(10, 10));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
//			browserTable = new JXTable();
//			browserTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//			browserTable.getSelectionModel().addListSelectionListener(this);
//			browserTable.setDefaultRenderer(Date.class, new CustomCellRenderer());
//
//			if (tableModel != null) {
//				browserTable.setModel(tableModel);
//			}


//			if (searchPanel != null) {
//				searchPanel.setModelBrowser(this);
//				browserPanel.add(searchPanel, BorderLayout.NORTH);
//			}
//			browserPanel.add(new JScrollPane(browserTable));
//			add(browserPanel);

//			JPanel beanEditorPanel = new JPanel(new MigLayout()); //$NON-NLS-1$
//			beanEditorPanel.add(beanEditor);
			JPanel beanPanel = new JPanel(new BorderLayout());
			beanPanel.setBorder(BorderFactory.createEtchedBorder());			
//			beanPanel.add(beanEditorPanel);

			JPanel buttonPanel = new JPanel();

			//TODO: ADD messages.properties
			JButton btnNew = new JButton( "NEW" ); //$NON-NLS-1$
			JButton btnEdit = new JButton( "EDIT" ); //$NON-NLS-1$
			JButton btnSave = new JButton( "SAVE" ); //$NON-NLS-1$
			JButton btnDuplicate = new JButton( "DUPLICATE" ); //$NON-NLS-1$
			JButton btnDeleteAll = new JButton( "DELETE ALL" ); //$NON-NLS-1$
			JButton btnDelete = new JButton( "DELETE" ); //$NON-NLS-1$
			JButton btnCancel = new JButton( "CANCEL" ); //$NON-NLS-1$			

			buttonPanel.add(btnNew);
			buttonPanel.add(btnEdit);
			buttonPanel.add(btnSave);
			buttonPanel.add(btnDelete);
			buttonPanel.add(btnCancel);			
			buttonPanel.add(btnDuplicate);
			buttonPanel.add(btnDeleteAll);
			
			beanPanel.setPreferredSize(new Dimension(600, 400));
			beanPanel.add(buttonPanel, BorderLayout.SOUTH);

			add(beanPanel, BorderLayout.EAST);

			btnNew.addActionListener(this);
			btnEdit.addActionListener(this);
			btnSave.addActionListener(this);
			btnSave.addActionListener(this);
			btnCancel.addActionListener(this);		
			btnDuplicate.addActionListener(this);
			btnDeleteAll.addActionListener(this);			

//			beanEditor.clearFields();
//			beanEditor.setFieldsEnable(false);

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {		
		System.out.println("EditTablePanel.actionPerformed():"+e.getActionCommand()+":");
	}
}
