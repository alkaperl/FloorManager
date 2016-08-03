package org.marso.floormanager.tabletype;

import javax.swing.JTabbedPane;

import com.floreantpos.actions.PosAction;
import com.floreantpos.bo.ui.BackOfficeWindow;

public class EditTableTypeAction extends PosAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//TODO: put in messages.properties
	private static String name = "Table Types";	
	
	public EditTableTypeAction() {
		super(name);
		//super(Messages.getString("ShowTableBrowserAction.0")); //$NON-NLS-1$
	}

	@Override
	public void execute() {
		System.out.println("EditTableTypeAction.execute()");

		BackOfficeWindow backOfficeWindow = com.floreantpos.util.POSUtil.getBackOfficeWindow();
		EditTableTypePanel explorer = null;
		JTabbedPane tabbedPane = backOfficeWindow.getTabbedPane();
		int index = tabbedPane.indexOfTab(name); //$NON-NLS-1$
		if (index == -1) {
			explorer = new EditTableTypePanel();
			tabbedPane.addTab(name, explorer); //$NON-NLS-1$
		}
		else {
			explorer = (EditTableTypePanel) tabbedPane.getComponentAt(index);
		}
		tabbedPane.setSelectedComponent(explorer);
	}	

	
	//final FloorLayoutPlugin floorLayoutPlugin = (FloorLayoutPlugin) ExtensionManager.getPlugin(FloorLayoutPlugin.class);
	//if (floorLayoutPlugin != null) {
//		btnCreateType = new JButton(Messages.getString("ShopTableForm.40")); //$NON-NLS-1$
//		add(new JLabel(Messages.getString("ShopTableForm.10")), "cell 0 5"); //$NON-NLS-1$ //$NON-NLS-2$
//		add(tableTypeCheckBoxList, "cell 1 5,wrap,grow"); //$NON-NLS-1$
//		add(btnCreateType, "cell 1 6"); //$NON-NLS-1$
//
//		btnCreateType.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				//BeanEditorDialog dialog = new BeanEditorDialog(floorLayoutPlugin.getBeanEditor());
//				//dialog.open();
//				//tableTypeCBoxList.setModel(ShopTableTypeDAO.getInstance().findAll());
//				System.out.println("EditTableForm.create.actionPerformed.TYPE.");
//			}
//		});
	//}
}