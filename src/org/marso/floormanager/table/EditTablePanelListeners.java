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

public class EditTablePanelListeners implements ActionListener, ListSelectionListener {
	private EditTablePanel editTablePanel;
	
	public EditTablePanelListeners (EditTablePanel e){
		editTablePanel = e;		
	}
	
	public void actionPerformed(ActionEvent e) {		
		JButton b = (JButton) e.getSource();
		System.out.println("EditTablePanel.actionPerformed():"+b.getText()+":");		
		if( b.getText().equals( "DELETE" ) && editTablePanel.selectedRowId > -1 ){
			int option = POSMessageDialog.showYesNoQuestionDialog(POSUtil.getBackOfficeWindow(),
					Messages.getString("ShopTableForm.14"), Messages.getString("ShopTableForm.15")); //$NON-NLS-1$ //$NON-NLS-2$
			if (option == JOptionPane.YES_OPTION) {	
				editTablePanel.beanEditor.delete();
				editTablePanel.refreshTables();
			}
		} else if( b.getText().equals( "SAVE" ) && editTablePanel.selectedRowId > -1 ){
			editTablePanel.beanEditor.save();
			editTablePanel.refreshTables();
		}
	}
	
	public void valueChanged(ListSelectionEvent e) {
		System.out.println("EditTablePanel.valueChanged():"+e.getValueIsAdjusting()+":");
		
		if ( !e.getValueIsAdjusting()) {
			BeanTableModel model = (BeanTableModel) editTablePanel.browserTable.getModel();
			editTablePanel.selectedRowIndex = editTablePanel.browserTable.getSelectedRow();
			if (editTablePanel.selectedRowIndex > -1) {
				System.out.println("EditTablePanel.valueChanged():selectedRow:"+editTablePanel.selectedRowIndex+":");

				editTablePanel.selectedRowId = editTablePanel.browserTable.convertRowIndexToModel(editTablePanel.selectedRowIndex);
				System.out.println("EditTablePanel.valueChanged():selectedRow.C:"+editTablePanel.selectedRowId+":");

				if (editTablePanel.selectedRowId > -1){
					System.out.println("EditTablePanel.valueChanged():selectedRow.C.if:"+editTablePanel.selectedRowId+":");

					ShopTable data = (ShopTable) model.getRow(editTablePanel.selectedRowId);
					editTablePanel.beanEditor.setBean(data);
//		btnNew.setEnabled(true);
//		btnEdit.setEnabled(true);
//		btnSave.setEnabled(false);
//		btnDelete.setEnabled(true);
//		btnCancel.setEnabled(false);
					editTablePanel.beanEditor.setFieldsEnable(true);
				}
			}				
		}
	}
	
	public void toggleActionButtons( boolean enable ){
		editTablePanel.btnNew.setEnabled( enable );
		editTablePanel.btnDuplicate.setEnabled( enable );
		editTablePanel.btnSave.setEnabled( enable );
		editTablePanel.btnDelete.setEnabled( enable );
		editTablePanel.btnCancel.setEnabled( enable );
		//editTablePanel.btnDeleteAll.setEnabled( enable );		
	}
}
