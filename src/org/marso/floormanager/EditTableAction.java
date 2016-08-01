package org.marso.floormanager;

import javax.swing.JTabbedPane;

import com.floreantpos.actions.PosAction;
import com.floreantpos.bo.ui.BackOfficeWindow;

public class EditTableAction extends PosAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//TODO: put in messages.properties
	private static String name = "Edit Tables";	
	
	public EditTableAction() {
		super(name);
		//super(Messages.getString("ShowTableBrowserAction.0")); //$NON-NLS-1$
	}

	@Override
	public void execute() {
		System.out.println("EditTableAction.execute()");

		BackOfficeWindow backOfficeWindow = com.floreantpos.util.POSUtil.getBackOfficeWindow();

		//ShopTableBrowser explorer = null;
		EditTablePanel explorer = null;
		JTabbedPane tabbedPane = backOfficeWindow.getTabbedPane();
		int index = tabbedPane.indexOfTab(name); //$NON-NLS-1$
		if (index == -1) {
			explorer = new EditTablePanel();
			tabbedPane.addTab(name, explorer); //$NON-NLS-1$
		}
		else {
			explorer = (EditTablePanel) tabbedPane.getComponentAt(index);
		}
		tabbedPane.setSelectedComponent(explorer);
	}	

}