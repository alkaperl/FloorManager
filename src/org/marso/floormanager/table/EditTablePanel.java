package org.marso.floormanager.table;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import org.jdesktop.swingx.JXTable;

import com.floreantpos.Messages;
import com.floreantpos.bo.ui.CustomCellRenderer;
import com.floreantpos.model.ShopTable;
import com.floreantpos.model.dao.ShopTableDAO;
import com.floreantpos.swing.BeanTableModel;

import net.miginfocom.swing.MigLayout;

public class EditTablePanel extends JPanel {

	/**
	 * TODO: is serialised necessary??? 
	 */
	private static final long serialVersionUID = 1L;
	protected JXTable browserTable = new JXTable();
	private BeanTableModel<ShopTable> tableModel = new BeanTableModel<ShopTable>(ShopTable.class);
	protected EditTableForm beanEditor = new EditTableForm();
	protected int selectedRowIndex = -1;
	protected int selectedRowId = -1;
	private EditTablePanelListeners listeners;
	//TODO: ADD messages.properties			
	protected JButton btnDelete = new JButton( "DELETE" ); //$NON-NLS-1$	
	protected JButton btnDuplicate = new JButton( "REPLICATE" ); //$NON-NLS-1$
	protected JButton btnDeleteAll = new JButton( "DELETE ALL" ); //$NON-NLS-1$	
	protected JButton btnNew = new JButton( "NEW" ); //$NON-NLS-1$		
	protected JButton btnSave = new JButton( "SAVE" ); //$NON-NLS-1$		
	protected JButton btnCancel = new JButton( "CLEAR CHANGES" ); //$NON-NLS-1$		
	
	public EditTablePanel() {
		this.setName("EditFloorPanel");
		initializePanel();
	}
	
	public void initializePanel(){
		listeners = new EditTablePanelListeners( this );
		
		setLayout(new BorderLayout(10, 10));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		browserTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		browserTable.getSelectionModel().addListSelectionListener( listeners );
		browserTable.setDefaultRenderer(Date.class, new CustomCellRenderer());

		//Pass as a parameter???
		tableModel.addColumn(Messages.getString("ShopTableBrowser.0"), ShopTable.PROP_ID); //$NON-NLS-1$
		tableModel.addColumn(Messages.getString("ShopTableBrowser.1"), ShopTable.PROP_CAPACITY); //$NON-NLS-1$
		tableModel.addColumn(Messages.getString("ShopTableBrowser.2"), ShopTable.PROP_DESCRIPTION); //$NON-NLS-1$
		tableModel.addColumn("Status", ShopTable.PROP_DISABLE); //$NON-NLS-1$
		browserTable.setModel(tableModel);

		JPanel browserPanel = new JPanel(new BorderLayout());
		browserPanel.add(new JScrollPane(browserTable));
		
		JPanel buttonPanelLeft = new JPanel();
		buttonPanelLeft.add(btnDelete);
		buttonPanelLeft.add(btnDuplicate);
		buttonPanelLeft.add(btnDeleteAll);	
		browserPanel.add(buttonPanelLeft, BorderLayout.SOUTH);
		add(browserPanel);

		JPanel beanEditorPanel = new JPanel(new MigLayout()); //$NON-NLS-1$
		beanEditorPanel.add(beanEditor);
		JPanel beanPanel = new JPanel(new BorderLayout());
		beanPanel.setBorder(BorderFactory.createEtchedBorder());			
		beanPanel.add(beanEditorPanel);

		JPanel buttonPanel = new JPanel();
	
		buttonPanel.add(btnNew);
		buttonPanel.add(btnSave);
		buttonPanel.add(btnCancel);			
		
		beanPanel.setPreferredSize(new Dimension(600, 400));
		beanPanel.add(buttonPanel, BorderLayout.SOUTH);

		add(beanPanel, BorderLayout.EAST);

		btnNew.addActionListener( listeners );
		btnSave.addActionListener( listeners );
		btnDelete.addActionListener( listeners );
		btnCancel.addActionListener( listeners );		
		btnDuplicate.addActionListener( listeners );
		btnDeleteAll.addActionListener( listeners );			

		beanEditor.clearFields();
		beanEditor.setFieldsEnable(false);

		refreshTables();
		browserTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	}
	
	public void refreshTables() {
		List<ShopTable> tables = ShopTableDAO.getInstance().findAll();
		tableModel = (BeanTableModel) browserTable.getModel();
		tableModel.removeAll();
		tableModel.addRows( tables );
		beanEditor.setFieldsEnable( false );
		selectedRowId = -1;
	}	
	
}