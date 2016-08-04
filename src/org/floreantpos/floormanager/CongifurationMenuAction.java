package org.floreantpos.floormanager;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.floreantpos.actions.PosAction;
import com.floreantpos.bo.ui.BackOfficeWindow;

public class CongifurationMenuAction extends PosAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private JPanel configurationPanel;
	private BackOfficeWindow backOfficeWindow;

	public CongifurationMenuAction(String nm, JPanel panel, BackOfficeWindow backOfficeWindowInstance) {
		//TODO: optimization: 
		//      Pass Controller instead of panel: 
		//      so that Panel is instantiated only if needed
		//      and not in Configuration Window open
		super(nm);
		name = nm;
		configurationPanel = panel;
		backOfficeWindow = backOfficeWindowInstance;
	}

	@Override
	public void execute() {
		System.out.println("EditFloorAction.execute()");
		JTabbedPane tabbedPane = backOfficeWindow.getTabbedPane();
		int index = tabbedPane.indexOfTab(name);
		if (index == -1) {
			tabbedPane.addTab(name, configurationPanel);
		} else {
			configurationPanel = (JPanel) tabbedPane.getComponentAt(index);
		}
		tabbedPane.setSelectedComponent(configurationPanel);
	}	
}