package org.floreantpos.floormanager.table;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import org.floreantpos.floormanager.Messages;
import org.hibernate.StaleObjectStateException;

import com.floreantpos.bo.ui.BOMessageDialog;
import com.floreantpos.model.ShopTable;
import com.floreantpos.model.ShopTableType;
import com.floreantpos.model.dao.ShopTableDAO;
import com.floreantpos.model.dao.ShopTableTypeDAO;
import com.floreantpos.model.util.IllegalModelStateException;
import com.floreantpos.swing.CheckBoxList;
import com.floreantpos.swing.FixedLengthTextField;
import com.floreantpos.swing.IntegerTextField;
import com.floreantpos.ui.BeanEditor;

import net.miginfocom.swing.MigLayout;

public class TableForm extends BeanEditor<ShopTable> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final TableController tableController;
	private FixedLengthTextField tfTableDescription;
	private IntegerTextField tfTableNo;
	private FixedLengthTextField tfTableName;
	private CheckBoxList tableTypeCBoxList;
	private JPanel statusPanel;
	private JRadioButton rbFree;
	private JRadioButton rbDisable;
	private CapacityButtonsController capacityButtonsController;

	public TableForm(TableController tableController) {
		//TODO:Move Business Logic code to Controller class
		this.tableController = tableController;
		
		setPreferredSize(new Dimension(600, 800));
		setLayout(new MigLayout("", "[][grow]", "[][][][][][][][]")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		setBorder(BorderFactory.createTitledBorder(Messages.getString("TABLE.BORDER.TITLE"))); //$NON-NLS-1$
		tableTypeCBoxList = new CheckBoxList();
		tableTypeCBoxList.setModel(ShopTableTypeDAO.getInstance().findAll());
		JScrollPane tableTypeCheckBoxList = new JScrollPane(tableTypeCBoxList);
		tableTypeCheckBoxList.setPreferredSize(new Dimension(0, 350));

		JLabel lblName = new JLabel(Messages.getString("TABLE.FORM.LABEL.TABLE_NUMBER")); //$NON-NLS-1$
		add(lblName, "cell 0 0,alignx trailing,aligny center"); //$NON-NLS-1$

		tfTableNo = new IntegerTextField(6);
		add(tfTableNo, "cell 1 0,aligny top"); //$NON-NLS-1$

		tfTableName = new FixedLengthTextField();
		JLabel lblAddress = new JLabel(Messages.getString("TABLE.FORM.LABEL.TABLE_NAME")); //$NON-NLS-1$
		add(lblAddress, "cell 0 2,alignx trailing"); //$NON-NLS-1$

		tfTableDescription = new FixedLengthTextField();
		add(tfTableDescription, "cell 1 2,growx"); //$NON-NLS-1$

		JLabel lblCitytown = new JLabel(Messages.getString("TABLE.FORM.LABEL.CAPACITY")); //$NON-NLS-1$
		add(lblCitytown, "cell 0 3,alignx trailing"); //$NON-NLS-1$

		capacityButtonsController = new CapacityButtonsController(this);

		statusPanel = new JPanel();
		statusPanel.setBorder(new TitledBorder(null, Messages.getString("TABLE.FORM.STATUS.TITLE"), TitledBorder.LEADING, TitledBorder.TOP, null, null)); //$NON-NLS-1$
		add(statusPanel, "cell 1 4,grow"); //$NON-NLS-1$
		statusPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		rbFree = new JRadioButton(Messages.getString("TABLE.FORM.STATUS.ENABLED")); //$NON-NLS-1$
		statusPanel.add(rbFree);

		rbDisable = new JRadioButton(Messages.getString("TABLE.FORM.STATUS.DISABLED")); //$NON-NLS-1$
		statusPanel.add(rbDisable);

		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rbFree);
		buttonGroup.add(rbDisable);

		add(new JLabel(), "grow,span"); //$NON-NLS-1$
		
		add(new JLabel(Messages.getString("TABLE.FORM.LABEL.TABLE_TYPE")), "cell 0 5"); //$NON-NLS-1$ //$NON-NLS-2$
		add(tableTypeCheckBoxList, "cell 1 5,wrap,grow"); //$NON-NLS-1$		
	}

	@Override
	public void createNew() {
		ShopTable newShopTable = tableController.createNew();
		setBean( newShopTable );
		tfTableNo.setText( newShopTable.getName() );
		capacityButtonsController.setTableCapacity("4");//$NON-NLS-1$
		tfTableDescription.setText(""); //$NON-NLS-1$
		tfTableName.setText(""); //$NON-NLS-1$
		setBorder( BorderFactory.createTitledBorder( Messages.getString( "TABLE.BORDER.TITLE2" ) ) ); //$NON-NLS-1$
	}
	
	@Override
	public boolean delete() {
		boolean result = tableController.delete( getBean() );
		if ( result ){
			clearFields();
		}
		return result;
	}

	public boolean deleteAllTables() {
		boolean result = tableController.deleteAllTables();
		if ( result ){
			clearFields();
		}
		return result;
	}

	@Override
	public boolean save() {
		boolean result = false;
		try {
			if ( updateModel() ){
				ShopTable table = (ShopTable) getBean();
				ShopTableDAO.getInstance().saveOrUpdate(table);
				updateView();
				System.out.println("save:END");				
				result =  true;
			}
		} catch (IllegalModelStateException e) {
			BOMessageDialog.showError(this, e.getMessage()); //$NON-NLS-1$
		} catch (StaleObjectStateException e) {
			BOMessageDialog.showError(this, Messages.getString("TABLE.ACTION.SAVE.ERROR")); //$NON-NLS-1$
		}
		return result;
	}

	@Override
	protected void updateView() {
		ShopTable table = (ShopTable) getBean();
		if (table != null) {
			tableTypeCBoxList.setModel( ShopTableTypeDAO.getInstance().findAll() );
			tableTypeCBoxList.selectItems(table.getTypes());
			tfTableNo.setText(String.valueOf(table.getTableNumber()));
			tfTableName.setText(table.getName());
			tfTableDescription.setText(table.getDescription());
			capacityButtonsController.setTableCapacity(String.valueOf(table.getCapacity()));
			rbFree.setSelected(true);
			rbDisable.setSelected(table.isDisable());
			setBorder(BorderFactory.createTitledBorder(Messages.getString("TABLE.BORDER.TITLE3"))); //$NON-NLS-1$
			System.out.println("updateView:END");				
		}
	}

	@Override
	protected boolean updateModel() throws IllegalModelStateException {
		ShopTable table = (ShopTable) getBean();
		if (table == null) {
			table = new ShopTable();
			setBean(table, false);
		}

//		if (!isDuplicateOn() && tfTableNo.getInteger() == 0) {
//			POSMessageDialog.showError(null, Messages.getString("ShopTableForm.57")); //$NON-NLS-1$
//			return false;
//		}
//		ShopTable tableTocheck = ShopTableDAO.getInstance().get(tfTableNo.getInteger());
//		if (tableTocheck != null && selectedTable != tableTocheck.getId()) {
//			POSMessageDialog.showError(POSUtil.getBackOfficeWindow(), Messages.getString("ShopTableForm.58")); //$NON-NLS-1$
//			return false;
//		}

		table.setId(tfTableNo.getInteger());
		table.setName(tfTableName.getText());
		table.setDescription(tfTableDescription.getText());
		table.setCapacity(capacityButtonsController.getTableCapacity());
		List<ShopTableType> checkValues = tableTypeCBoxList.getCheckedValues();
		table.setTypes(checkValues);
		table.setServing(false);
		table.setBooked(false);
		table.setDirty(false);
		table.setFree( rbFree.isSelected() );
		table.setDisable( rbDisable.isSelected() );
		setBean( table, false );
		System.out.println("updateModel:END");

		return true;
	}
	
	@Override
	public void clearFields() {
		tfTableNo.setText("");//$NON-NLS-1$
		capacityButtonsController.setTableCapacity("");//$NON-NLS-1$
		tfTableDescription.setText(""); //$NON-NLS-1$
		tfTableName.setText(""); //$NON-NLS-1$
		rbFree.setSelected(false);
		rbDisable.setSelected(false);
		tableTypeCBoxList.unCheckAll();
	}
	
	@Override
	public void setFieldsEnable(boolean enable) {
		tableTypeCBoxList.setEnabled(enable);
		tableTypeCBoxList.clearSelection();
		tfTableNo.setEnabled(enable);
		tfTableName.setEnabled(enable);
		tfTableDescription.setEnabled(enable);
		capacityButtonsController.toggleCapacityButtons(enable);
		rbFree.setEnabled(enable);
		rbDisable.setEnabled(enable);
	}

	@Override
	public void cancel() {
		setBorder(BorderFactory.createTitledBorder(Messages.getString("TABLE.BORDER.TITLE4"))); //$NON-NLS-1$
	}	

	@Override
	public void edit() {
		setBorder(BorderFactory.createTitledBorder(Messages.getString("TABLE.BORDER.TITLE5"))); //$NON-NLS-1$
	}

	@Override
	public String getDisplayText() {
		return Messages.getString("TABLE.BORDER.TITLE6"); //$NON-NLS-1$
	}
}