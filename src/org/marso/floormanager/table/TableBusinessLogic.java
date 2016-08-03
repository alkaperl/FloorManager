package org.marso.floormanager.table;

import java.util.List;

import javax.swing.JOptionPane;

import com.floreantpos.Messages;
import com.floreantpos.model.ShopTable;
import com.floreantpos.model.TableBookingInfo;
import com.floreantpos.model.dao.ShopTableDAO;
import com.floreantpos.model.dao.TableBookingInfoDAO;
import com.floreantpos.ui.dialog.POSMessageDialog;
import com.floreantpos.util.POSUtil;

public class TableBusinessLogic {
	
	public TableBusinessLogic(){
		
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
