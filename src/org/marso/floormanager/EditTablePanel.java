package org.marso.floormanager;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.jdesktop.swingx.JXTable;

import com.floreantpos.Messages;
import com.floreantpos.bo.ui.CustomCellRenderer;
import com.floreantpos.model.ShopTable;
import com.floreantpos.model.ShopTableType;
import com.floreantpos.model.dao.ShopTableDAO;
import com.floreantpos.swing.BeanTableModel;
import com.floreantpos.ui.BeanEditor;

import net.miginfocom.swing.MigLayout;

public class EditTablePanel  extends JPanel implements ActionListener, ListSelectionListener {

	/**
	 * TODO: is serialised necessary??? 
	 */
	private static final long serialVersionUID = 1L;
	private JXTable browserTable = new JXTable();
	private BeanTableModel<ShopTable> tableModel = new BeanTableModel<ShopTable>(ShopTable.class);
	private BeanEditor beanEditor = new EditTableForm();

	
	public EditTablePanel() {
		this.setName("EditFloorPanel");
		initializePanel();
	}
	
	public void initializePanel(){
		setLayout(new BorderLayout(10, 10));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		browserTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		browserTable.getSelectionModel().addListSelectionListener(this);
		browserTable.setDefaultRenderer(Date.class, new CustomCellRenderer());

		//Pass as a parameter???
		tableModel.addColumn(Messages.getString("ShopTableBrowser.0"), ShopTable.PROP_ID); //$NON-NLS-1$
		tableModel.addColumn(Messages.getString("ShopTableBrowser.1"), ShopTable.PROP_CAPACITY); //$NON-NLS-1$
		tableModel.addColumn(Messages.getString("ShopTableBrowser.2"), ShopTable.PROP_DESCRIPTION); //$NON-NLS-1$
		tableModel.addColumn("Status", ShopTable.PROP_DISABLE); //$NON-NLS-1$
		
		
		if (tableModel != null) {
			browserTable.setModel(tableModel);
		}

		JPanel browserPanel = new JPanel(new BorderLayout());
//		SearchPanel<E> searchPanel 
//		if (searchPanel != null) {
//			searchPanel.setModelBrowser(this);
//			browserPanel.add(searchPanel, BorderLayout.NORTH);
//		}
		browserPanel.add(new JScrollPane(browserTable));
		add(browserPanel);

		JPanel beanEditorPanel = new JPanel(new MigLayout()); //$NON-NLS-1$
		beanEditorPanel.add(beanEditor);
		JPanel beanPanel = new JPanel(new BorderLayout());
		beanPanel.setBorder(BorderFactory.createEtchedBorder());			
		beanPanel.add(beanEditorPanel);

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

		beanEditor.clearFields();
		beanEditor.setFieldsEnable(false);

		refreshTables();
		browserTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	}
	
	public void refreshTables() {
		List<ShopTable> tables = ShopTableDAO.getInstance().findAll();
		tableModel = (BeanTableModel) browserTable.getModel();
		tableModel.removeAll();
		tableModel.addRows(tables);
	}	
	
	@Override
	public void actionPerformed(ActionEvent e) {		
		System.out.println("EditTablePanel.actionPerformed():"+e.getActionCommand()+":");
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		System.out.println("EditTablePanel.valueChanged():"+e.getValueIsAdjusting()+":");
		
		if ( !e.getValueIsAdjusting()) {
			BeanTableModel model = (BeanTableModel) browserTable.getModel();
			int selectedRow = browserTable.getSelectedRow();
			if (selectedRow > -1) {
				System.out.println("EditTablePanel.valueChanged():selectedRow:"+selectedRow+":");

				selectedRow = browserTable.convertRowIndexToModel(selectedRow);
				System.out.println("EditTablePanel.valueChanged():selectedRow.C:"+selectedRow+":");

				if (selectedRow > -1){
					System.out.println("EditTablePanel.valueChanged():selectedRow.C.if:"+selectedRow+":");

					ShopTable data = (ShopTable) model.getRow(selectedRow);
					beanEditor.setBean(data);
//		btnNew.setEnabled(true);
//		btnEdit.setEnabled(true);
//		btnSave.setEnabled(false);
//		btnDelete.setEnabled(true);
//		btnCancel.setEnabled(false);
					beanEditor.setFieldsEnable(true);
				}
			}				
		}
	}
}
