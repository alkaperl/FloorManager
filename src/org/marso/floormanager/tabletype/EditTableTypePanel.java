package org.marso.floormanager.tabletype;

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

import com.floreantpos.bo.ui.CustomCellRenderer;
import com.floreantpos.model.ShopTableType;
import com.floreantpos.model.dao.ShopTableTypeDAO;
import com.floreantpos.swing.BeanTableModel;

import net.miginfocom.swing.MigLayout;

public class EditTableTypePanel extends JPanel implements ActionListener, ListSelectionListener {

	/**
	 * TODO: is serialised necessary??? 
	 */
	private static final long serialVersionUID = 1L;
	protected JXTable browserTable = new JXTable();	
	private BeanTableModel<ShopTableType> tableModel = new BeanTableModel<ShopTableType>(ShopTableType.class);	
	protected EditTableTypeForm beanEditor = new EditTableTypeForm();	
	//TODO: ADD messages.properties	
	private JButton btnNew = new JButton( "NEW" ); //$NON-NLS-1$
	private JButton btnSave = new JButton( "SAVE" ); //$NON-NLS-1$		
	private JButton btnCancel = new JButton( "CANCEL CHANGES" ); //$NON-NLS-1$		
	private JButton btnDelete = new JButton( "DELETE" ); //$NON-NLS-1$	
	protected int selectedRowIndex = -1;
	protected int selectedRowId = -1;	
	
	public EditTableTypePanel() {
		this.setName("EditFloorTypePanel");
		initializePanel();
	}
	
	public void initializePanel(){
		setLayout(new BorderLayout(10, 10));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		browserTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		browserTable.getSelectionModel().addListSelectionListener( this );
		browserTable.setDefaultRenderer(Date.class, new CustomCellRenderer());
		
		tableModel.addColumn( "ID", ShopTableType.PROP_ID); //$NON-NLS-1$
		tableModel.addColumn( "NAME", ShopTableType.PROP_NAME); //$NON-NLS-1$
		tableModel.addColumn( "DESC", ShopTableType.PROP_DESCRIPTION); //$NON-NLS-1$
		browserTable.setModel(tableModel);
		
		JPanel browserPanel = new JPanel(new BorderLayout());
		browserPanel.add(new JScrollPane(browserTable));
		add(browserPanel, BorderLayout.WEST);		
		
		JPanel beanEditorPanel = new JPanel(new MigLayout()); //$NON-NLS-1$
		beanEditorPanel.add(beanEditor);
		JPanel beanPanel = new JPanel(new BorderLayout());
		beanPanel.setBorder(BorderFactory.createEtchedBorder());			
		beanPanel.add(beanEditorPanel);		
		
		beanPanel.setPreferredSize(new Dimension(600, 400));
		add(beanPanel, BorderLayout.CENTER);		
		
		JPanel buttonPanel = new JPanel();
		btnNew.addActionListener(this);
		btnSave.addActionListener(this);
		btnDelete.addActionListener(this);
		btnCancel.addActionListener(this);
		
		buttonPanel.add(btnNew);
		buttonPanel.add(btnSave);
		buttonPanel.add(btnDelete);
		buttonPanel.add(btnCancel);	
		add(buttonPanel, BorderLayout.SOUTH);
		
		refreshTables();
		browserTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	}
	
	public void refreshTables() {
		List<ShopTableType> tableTypes = ShopTableTypeDAO.getInstance().findAll();
		tableModel = (BeanTableModel) browserTable.getModel();
		tableModel.removeAll();
		tableModel.addRows( tableTypes );
		//beanEditor.setFieldsEnable( false );
		selectedRowId = -1;
	}	
	
	public void actionPerformed(ActionEvent e) {		
		System.out.println("EditTableTypePanel.actionPerformed():"+e.getActionCommand()+":");
	}
	
	@SuppressWarnings("unchecked")
	public void valueChanged(ListSelectionEvent e) {
		System.out.println("EditTableTypePanel.valueChanged():"+e.getValueIsAdjusting()+":");
		
		if ( !e.getValueIsAdjusting()) {
			BeanTableModel<ShopTableType> model = (BeanTableModel<ShopTableType>) browserTable.getModel();
			selectedRowIndex = browserTable.getSelectedRow();
			if (selectedRowIndex > -1) {
				System.out.println("EditTablePanel.valueChanged():selectedRow:"+selectedRowIndex+":");

				selectedRowId = browserTable.convertRowIndexToModel(selectedRowIndex);
				System.out.println("EditTablePanel.valueChanged():selectedRow.C:"+selectedRowId+":");

				if (selectedRowId > -1){
					System.out.println("EditTablePanel.valueChanged():selectedRow.C.if:"+selectedRowId+":");

					//ShopTableType data = (ShopTableType) model.getRow(selectedRowId);
					//beanEditor.setBean(data);
//		btnNew.setEnabled(true);
//		btnEdit.setEnabled(true);
//		btnSave.setEnabled(false);
//		btnDelete.setEnabled(true);
//		btnCancel.setEnabled(false);
					//beanEditor.setFieldsEnable(true);
				}
			}				
		}
	}

}

