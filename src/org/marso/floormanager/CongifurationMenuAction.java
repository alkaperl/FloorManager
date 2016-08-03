package org.marso.floormanager;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.floreantpos.actions.PosAction;
import com.floreantpos.bo.ui.BackOfficeWindow;

public class CongifurationMenuAction extends PosAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//TODO: put in messages.properties
	private String name;
	private JPanel configurationPanel;

	public CongifurationMenuAction(String nm, JPanel p) {
		super(nm);
		name = nm;
		configurationPanel = p;
	}

	@Override
	public void execute() {
		System.out.println("EditFloorAction.execute()");
		BackOfficeWindow backOfficeWindow = com.floreantpos.util.POSUtil.getBackOfficeWindow();
		JTabbedPane tabbedPane = backOfficeWindow.getTabbedPane();
		int index = tabbedPane.indexOfTab(name); //$NON-NLS-1$
		if (index == -1) {
			tabbedPane.addTab(name, configurationPanel); //$NON-NLS-1$
		} else {
			configurationPanel = (JPanel) tabbedPane.getComponentAt(index);
		}
		tabbedPane.setSelectedComponent(configurationPanel);
	}	

}