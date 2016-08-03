package org.marso.floormanager.floor;

import javax.swing.JTabbedPane;

import com.floreantpos.actions.PosAction;
import com.floreantpos.bo.ui.BackOfficeWindow;

public class EditFloorAction extends PosAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//TODO: put in messages.properties
	private static String name = "Floors";

	public EditFloorAction() {
		super(name);
		//super(Messages.getString("ShowTableBrowserAction.0")); //$NON-NLS-1$
		
	}

	@Override
	public void execute() {
		System.out.println("EditFloorAction.execute()");

		BackOfficeWindow backOfficeWindow = com.floreantpos.util.POSUtil.getBackOfficeWindow();

		//ShopTableBrowser explorer = null;
		EditFloorPanel explorer = null;
		JTabbedPane tabbedPane = backOfficeWindow.getTabbedPane();
		int index = tabbedPane.indexOfTab(name); //$NON-NLS-1$
		if (index == -1) {
			explorer = new EditFloorPanel();
			tabbedPane.addTab(name, explorer); //$NON-NLS-1$
		}
		else {
			explorer = (EditFloorPanel) tabbedPane.getComponentAt(index);
		}
		tabbedPane.setSelectedComponent(explorer);
	}	

}