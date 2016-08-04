package org.floreantpos.floormanager.table;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.floreantpos.Messages;
import com.floreantpos.model.ShopTable;
import com.floreantpos.model.TableBookingInfo;
import com.floreantpos.model.dao.ShopTableDAO;
import com.floreantpos.model.dao.TableBookingInfoDAO;
import com.floreantpos.swing.BeanTableModel;
import com.floreantpos.ui.dialog.POSMessageDialog;
import com.floreantpos.util.POSUtil;

public class TableController implements ActionListener, ListSelectionListener {
	private TablePanel tablePanel;
	
	public TableController (){
		this.tablePanel = new TablePanel( this );		
	}
	
	public JPanel getMainPanel(){
		return tablePanel;
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
			BeanTableModel<?> model = (BeanTableModel<?>) tablePanel.browserTable.getModel();
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
	
	public ShopTable createNew() {
		ShopTable bean2 = new ShopTable();
		int nxtTableNumber = ShopTableDAO.getInstance().getNextTableNumber();
		if (nxtTableNumber == 0) {
			nxtTableNumber = 1;
		}
		for (int i = 1; i <= nxtTableNumber + 1; i++) {
			ShopTable shopTable = ShopTableDAO.getInstance().get(i);
			if (shopTable == null) {
				bean2.setName( String.valueOf( i ) );
				break;
			}
		}		
		return bean2;
	}

	public boolean delete(ShopTable curTable) {
		boolean result = false;
		try {
			List<TableBookingInfo> bookingList = TableBookingInfoDAO.getInstance().findAll();

			for (TableBookingInfo info : bookingList) {
				List<ShopTable> tableList = info.getTables();
				for (ShopTable shopTable : tableList) {
					if (shopTable.getId().equals(curTable.getId())) {
						tableList.remove(shopTable);
						info.setTables(tableList);
						TableBookingInfoDAO.getInstance().saveOrUpdate(info);
						break;
					}
				}
			}
			ShopTableDAO.getInstance().delete(curTable);
			result = true;
		} catch (Exception e) {
			POSMessageDialog.showError(POSUtil.getBackOfficeWindow(), e.getMessage(), e);
		}
		return result;
	}

	public boolean deleteAllTables() {
		boolean result = false;
		List<ShopTable> list = ShopTableDAO.getInstance().findAll();

		if (list.isEmpty()) {
			POSMessageDialog.showError(POSUtil.getBackOfficeWindow(), Messages.getString("ShopTableForm.51")); //$NON-NLS-1$
		} else {	
			int option = POSMessageDialog.showYesNoQuestionDialog(POSUtil.getBackOfficeWindow(),
					Messages.getString("ShopTableForm.20"), Messages.getString("ShopTableForm.21")); //$NON-NLS-1$ //$NON-NLS-2$
			if (option == JOptionPane.YES_OPTION) {	
				List<TableBookingInfo> bookingList = TableBookingInfoDAO.getInstance().findAll();		
				for (TableBookingInfo info : bookingList) {
					info.setTables(null);
					TableBookingInfoDAO.getInstance().saveOrUpdate(info);
				}		
				for (ShopTable table : list) {
					table.setFloor(null);
					table.setTypes(null);
					ShopTableDAO.getInstance().delete(table);
				}
			}
		}
		return result;
	}

	public boolean save() {
//		try {
//			if (!updateModel())
//				return false;
//
//			ShopTable table = (ShopTable) getBean();
//			ShopTableDAO.getInstance().saveOrUpdate(table);
//			updateView();
//			return true;
//
//		} catch (IllegalModelStateException e) {
//		} catch (StaleObjectStateException e) {
//			BOMessageDialog.showError(this, Messages.getString("ShopTableForm.16")); //$NON-NLS-1$
//		}
		return false;
	}	
}
