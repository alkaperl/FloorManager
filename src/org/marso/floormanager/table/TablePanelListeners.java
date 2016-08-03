package org.marso.floormanager.table;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.floreantpos.Messages;
import com.floreantpos.model.ShopTable;
import com.floreantpos.swing.BeanTableModel;
import com.floreantpos.ui.dialog.POSMessageDialog;
import com.floreantpos.util.POSUtil;

public class TablePanelListeners implements ActionListener, ListSelectionListener {
	private TablePanel tablePanel;
	
	public TablePanelListeners (TablePanel e){
		tablePanel = e;		
	}
	
	public void actionPerformed(ActionEvent e) {		
		JButton b = (JButton) e.getSource();
		System.out.println("EditTablePanel.actionPerformed():"+b.getText()+":");		
		if( b.getText().equals( "DELETE" ) && tablePanel.selectedRowId > -1 ){
			int option = POSMessageDialog.showYesNoQuestionDialog(POSUtil.getBackOfficeWindow(),
					Messages.getString("ShopTableForm.14"), Messages.getString("ShopTableForm.15")); //$NON-NLS-1$ //$NON-NLS-2$
			if (option == JOptionPane.YES_OPTION) {	
				tablePanel.beanEditor.delete();
				tablePanel.refreshTables();
				tablePanel.beanEditor.clearFields();
			}
		} else if( b.getText().equals( "SAVE" ) && tablePanel.selectedRowId > -1 ){
			tablePanel.beanEditor.save();
			tablePanel.refreshTables();
			tablePanel.beanEditor.clearFields();
		} else if( b.getText().equals( "SAVE" ) && tablePanel.selectedRowId < 0 && tablePanel.beanEditor.getBean() != null ){
			tablePanel.beanEditor.save();
			tablePanel.refreshTables();
			tablePanel.beanEditor.clearFields();
		} else if( b.getText().equals( "NEW" ) ){
			tablePanel.beanEditor.clearFields();
			tablePanel.refreshTables();
			tablePanel.beanEditor.createNew();	
			tablePanel.beanEditor.setFieldsEnable( true );
		} else if( b.getText().equals( "DELETE ALL" ) ){
			tablePanel.beanEditor.clearFields();
			tablePanel.beanEditor.deleteAllTables();
			tablePanel.refreshTables();
		} else if( b.getText().equals( "CLEAR CHANGES" ) ){
			tablePanel.beanEditor.clearFields();
			tablePanel.refreshTables();
		}
	}
	
	public void valueChanged(ListSelectionEvent e) {
		System.out.println("EditTablePanel.valueChanged():"+e.getValueIsAdjusting()+":");
		
		if ( !e.getValueIsAdjusting()) {
			BeanTableModel model = (BeanTableModel) tablePanel.browserTable.getModel();
			tablePanel.selectedRowIndex = tablePanel.browserTable.getSelectedRow();
			if (tablePanel.selectedRowIndex > -1) {
				System.out.println("EditTablePanel.valueChanged():selectedRow:"+tablePanel.selectedRowIndex+":");

				tablePanel.selectedRowId = tablePanel.browserTable.convertRowIndexToModel(tablePanel.selectedRowIndex);
				System.out.println("EditTablePanel.valueChanged():selectedRow.C:"+tablePanel.selectedRowId+":");

				if (tablePanel.selectedRowId > -1){
					System.out.println("EditTablePanel.valueChanged():selectedRow.C.if:"+tablePanel.selectedRowId+":");

					ShopTable data = (ShopTable) model.getRow(tablePanel.selectedRowId);
					tablePanel.beanEditor.setBean(data);
//		btnNew.setEnabled(true);
//		btnEdit.setEnabled(true);
//		btnSave.setEnabled(false);
//		btnDelete.setEnabled(true);
//		btnCancel.setEnabled(false);
					tablePanel.beanEditor.setFieldsEnable(true);
				}
			}				
		}
	}
	
	public void toggleActionButtons( boolean enable ){
		tablePanel.btnNew.setEnabled( enable );
		tablePanel.btnDuplicate.setEnabled( enable );
		tablePanel.btnSave.setEnabled( enable );
		tablePanel.btnDelete.setEnabled( enable );
		tablePanel.btnCancel.setEnabled( enable );
		//editTablePanel.btnDeleteAll.setEnabled( enable );		
	}
}
