package org.marso.floormanager.floor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.floreantpos.Messages;
import com.floreantpos.bo.ui.Command;

public class EditFloorPanel extends JPanel implements ActionListener {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EditFloorPanel() {
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

			JButton btnNew = new JButton(Messages.getString("ModelBrowser.0")); //$NON-NLS-1$
			JButton btnEdit = new JButton(Messages.getString("ModelBrowser.1")); //$NON-NLS-1$
			JButton btnSave = new JButton(Messages.getString("ModelBrowser.2")); //$NON-NLS-1$

			buttonPanel.add(btnNew);
			buttonPanel.add(btnEdit);
			buttonPanel.add(btnSave);

			beanPanel.setPreferredSize(new Dimension(600, 400));
			beanPanel.add(buttonPanel, BorderLayout.SOUTH);

			add(beanPanel, BorderLayout.EAST);

			btnNew.addActionListener(this);
			btnEdit.addActionListener(this);
			btnSave.addActionListener(this);

			btnNew.setEnabled(true);
			btnEdit.setEnabled(false);
			btnSave.setEnabled(false);

//			beanEditor.clearFields();
//			beanEditor.setFieldsEnable(false);

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Command command = Command.fromString(e.getActionCommand());
		System.out.println("EditFloorPanel.actionPerformed():"+command.toString());

	}
}
