package org.marso.floormanager;

import java.util.List;

import javax.swing.JMenu;

import org.marso.floormanager.floor.FloorPanel;
import org.marso.floormanager.table.TablePanel;
import org.marso.floormanager.tableselector.FloorManagerTableSelector;
import org.marso.floormanager.tabletype.TableTypePanel;

import com.floreantpos.actions.PosAction;
import com.floreantpos.bo.ui.BackOfficeWindow;
import com.floreantpos.config.ui.ConfigurationDialog;
import com.floreantpos.extension.FloorLayoutPlugin;
import com.floreantpos.model.ShopTable;
import com.floreantpos.model.Ticket;
import com.floreantpos.ui.BeanEditor;
import com.floreantpos.ui.tableselection.TableSelector;
import com.floreantpos.util.POSUtil;

import net.xeoh.plugins.base.annotations.PluginImplementation;

@PluginImplementation
public class FloorManager implements FloorLayoutPlugin {
	public static final String NAME = String.valueOf("org.FloorManager".hashCode());
	public static final String ID = String.valueOf(NAME.hashCode());
	
    public FloorManager() {
		System.out.println("FloorManager.constructor()");    	
    }

	@Override
	public String getId() {
		System.out.println("FloorManager.getID()="+ID);
		return ID;
	}

	@Override
	public String getName() {
		System.out.println("FloorManager.getName()="+NAME);
		return NAME;
	}

	@Override
	public void init() {
		System.out.println("FloorManager.init()");
		//Called from : Application --> initPlugins() --> plugin.init();
	}	

	@Override
	public void initialize() {
		System.out.println("FloorManager.initialize()");		
	}	

	@Override
	public void initBackoffice() {
		System.out.println("FloorManager.initBackoffice()");	
		// BackOfficeWindow --> createMenus() --> plugin.initBackoffice() 
		BackOfficeWindow backOfficeWindow = POSUtil.getBackOfficeWindow();
		JMenu floorManagerMenu = new JMenu("Floor Manager");
		floorManagerMenu.add(new CongifurationMenuAction("1.Floors", 	  new FloorPanel(), 	backOfficeWindow));
		floorManagerMenu.add(new CongifurationMenuAction("2.Tables", 	  new TablePanel(), 	backOfficeWindow));
		floorManagerMenu.add(new CongifurationMenuAction("3.Table Types", new TableTypePanel(), backOfficeWindow));
		backOfficeWindow.getBackOfficeMenuBar().remove(backOfficeWindow.getBackOfficeMenuBar().getComponentCount() - 1);
		backOfficeWindow.getBackOfficeMenuBar().add(floorManagerMenu);
	}

	@Override
	public void initConfigurationView(ConfigurationDialog dialog) {
		System.out.println("FloorManager.initConfigurationView():WILL NOT BE IMPLEMENTED");
        //dialog.addView((ConfigurationView)new FloorManagerConfigurationView());	
	}

	@Override
	public List<PosAction> getPosActions() {
		System.out.println("FloorManager.getPosActions()");		
//		SwitchboardOtherFunctionsView --> 
//		#########################################################################################################
//			List<PosAction> actions = new ArrayList();
//			actions.add(new ShowBackofficeAction());
//		...
//			actions.add(new ManageTableLayoutAction());
//
//			List<FloreantPlugin> plugins = ExtensionManager.getPlugins();
//			if (plugins != null) {
//				for (FloreantPlugin plugin : plugins) {
//					List<PosAction> posActions = plugin.getPosActions();
//					if (posActions != null) {
//						actions.addAll(plugin.getPosActions());
//					}
//				}
//			}	
		//Here one can add more actions on main board ???
		return null;
	}

	@Override
	public void openTicketsAndTablesDisplay() {
		System.out.println("FloorManager.openTicketsAndTablesDisplay()");		
//		SwitchboardOtherFunctionsDialog extends POSDialog --> actionPerformed
//		#########################################################################################################	
//		else if(source == btnTableManage) {
//			if(floorLayoutPlugin != null) {
//				floorLayoutPlugin.openTicketsAndTablesDisplay();
//			}
//		}		
//		ManageTableLayoutAction extends PosAction --> execute
//		#########################################################################################################	
//		if(floorLayoutPlugin != null) {
//			floorLayoutPlugin.openTicketsAndTablesDisplay();
//		}			
	}

	@Override
	public TableSelector createTableSelector() {
		System.out.println("FloorManager.createTableSelector()");			
		//TableSelector tableSelector = floorLayoutPlugin.createTableSelector();
		// tableSelector.setOrderType(orderType);
//		tableSelector.redererTables();
//		return new TableSelectorDialog(tableSelector);
		
		//AND
		
//		OrderView --> updateTableNumber()
//		#########################################################################################################
//		btnTableNumber.addActionListener(new java.awt.event.ActionListener() {
//			public void actionPerformed(java.awt.event.ActionEvent evt) {
//				updateTableNumber();
//			}
//		});
//		########
//		TableSelectorDialog dialog = TableSelectorFactory.createTableSelectorDialog(thisTicket.getOrderType());
//
//		dialog.setCreateNewTicket(false);
//		if (thisTicket != null) {
//			dialog.setTicket(thisTicket);
//		}
//		dialog.openUndecoratedFullScreen();
//
//		if (dialog.isCanceled()) {
//			return;
//		}
//		List<ShopTable> tables = dialog.getSelectedTables();
//
//		if (tables == null) {
//			return;
//		}
//
//		clearShopTable(session, thisTicket);
//		session.saveOrUpdate(thisTicket);
//
//		for (ShopTable shopTable : tables) {
//			shopTable.setServing(true);
//			session.merge(shopTable);
//
//			thisTicket.addTable(shopTable.getTableNumber());
//		}
		return new FloorManagerTableSelector();
	}

	@Override
	public void updateView() {
		System.out.println("FloorManager.updateView()");				
	}

	@Override
	public List<ShopTable> captureTableNumbers(Ticket ticket) {
		System.out.println("FloorManager.captureTableNumbers()");						
		//List<ShopTable> tables = floorLayoutPlugin.captureTableNumbers(thisTicket);
		//List<ShopTable> tables = floorLayoutPlugin.captureTableNumbers(null);
		//Is probably a relic. It is not called from anywhere in the code for version 1383!
		return null;
	}

	@Override
	public BeanEditor getBeanEditor() {
		System.out.println("FloorManager.getBeanEditor()");								
//		com.floreantpos.table.ShopTableForm
//		#########################################################################################################
//		final FloorLayoutPlugin floorLayoutPlugin = (FloorLayoutPlugin) ExtensionManager.getPlugin(FloorLayoutPlugin.class);
//
//		if (floorLayoutPlugin != null) {
//			btnCreateType = new JButton(Messages.getString("ShopTableForm.40")); //$NON-NLS-1$
//			add(new JLabel(Messages.getString("ShopTableForm.10")), "cell 0 5"); //$NON-NLS-1$ //$NON-NLS-2$
//			add(tableTypeCheckBoxList, "cell 1 5,wrap,grow"); //$NON-NLS-1$
//			add(btnCreateType, "cell 1 6"); //$NON-NLS-1$
//
//			btnCreateType.addActionListener(new ActionListener() {
//
//				@Override
//				public void actionPerformed(ActionEvent e) {
//					BeanEditorDialog dialog = new BeanEditorDialog(floorLayoutPlugin.getBeanEditor());
//					dialog.open();
//					tableTypeCBoxList.setModel(ShopTableTypeDAO.getInstance().findAll());
//				}
//			});
//		}
		return null;
	}

}
