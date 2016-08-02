package org.marso.floormanager.table;

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

import org.hibernate.StaleObjectStateException;

import com.floreantpos.Messages;
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

public class EditTableForm extends BeanEditor<ShopTable> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final EditTableBusinessLogic businessLogic = new EditTableBusinessLogic();
	private FixedLengthTextField tfTableDescription;
	private IntegerTextField tfTableNo;
	private FixedLengthTextField tfTableName;
	private CheckBoxList tableTypeCBoxList;
	private JPanel statusPanel;
	private JRadioButton rbFree;
	private JRadioButton rbDisable;
	private EditTableFormCapacityButtons editTableFormCapacityButtons;

	public EditTableForm() {
		setPreferredSize(new Dimension(600, 800));
		setLayout(new MigLayout("", "[][grow]", "[][][][][][][][]")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		setBorder(BorderFactory.createTitledBorder(Messages.getString("ShopTableForm.19"))); //$NON-NLS-1$
		tableTypeCBoxList = new CheckBoxList();
		tableTypeCBoxList.setModel(ShopTableTypeDAO.getInstance().findAll());
		JScrollPane tableTypeCheckBoxList = new JScrollPane(tableTypeCBoxList);
		tableTypeCheckBoxList.setPreferredSize(new Dimension(0, 350));

		JLabel lblName = new JLabel(Messages.getString("ShopTableForm.0")); //$NON-NLS-1$
		add(lblName, "cell 0 0,alignx trailing,aligny center"); //$NON-NLS-1$

		tfTableNo = new IntegerTextField(6);
		add(tfTableNo, "cell 1 0,aligny top"); //$NON-NLS-1$

		tfTableName = new FixedLengthTextField();
		JLabel lblAddress = new JLabel(Messages.getString("ShopTableForm.2")); //$NON-NLS-1$
		add(lblAddress, "cell 0 2,alignx trailing"); //$NON-NLS-1$

		tfTableDescription = new FixedLengthTextField();
		add(tfTableDescription, "cell 1 2,growx"); //$NON-NLS-1$

		JLabel lblCitytown = new JLabel(Messages.getString("ShopTableForm.3")); //$NON-NLS-1$
		add(lblCitytown, "cell 0 3,alignx trailing"); //$NON-NLS-1$

		editTableFormCapacityButtons = new EditTableFormCapacityButtons(this);

		statusPanel = new JPanel();
		statusPanel.setBorder(new TitledBorder(null, Messages.getString("ShopTableForm.4"), TitledBorder.LEADING, TitledBorder.TOP, null, null)); //$NON-NLS-1$
		add(statusPanel, "cell 1 4,grow"); //$NON-NLS-1$
		statusPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		rbFree = new JRadioButton(Messages.getString("ShopTableForm.5")); //$NON-NLS-1$
		statusPanel.add(rbFree);

		rbDisable = new JRadioButton(Messages.getString("ShopTableForm.9")); //$NON-NLS-1$
		statusPanel.add(rbDisable);

		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rbFree);
		buttonGroup.add(rbDisable);

		add(new JLabel(), "grow,span"); //$NON-NLS-1$
		
		add(new JLabel(Messages.getString("ShopTableForm.10")), "cell 0 5"); //$NON-NLS-1$ //$NON-NLS-2$
		add(tableTypeCheckBoxList, "cell 1 5,wrap,grow"); //$NON-NLS-1$		
	}

	@Override
	public void createNew() {
		setBean( businessLogic.createNew( tfTableNo ) );
		editTableFormCapacityButtons.setTableCapacity("4");//$NON-NLS-1$
		tfTableDescription.setText(""); //$NON-NLS-1$
		tfTableName.setText(""); //$NON-NLS-1$
		setBorder( BorderFactory.createTitledBorder( Messages.getString( "ShopTableForm.18" ) ) ); //$NON-NLS-1$
	}
	
	@Override
	public boolean delete() {
		boolean result = businessLogic.delete( getBean() );
		if ( result ){
			clearFields();
		}
		return result;
	}

	public boolean deleteAllTables() {
		boolean result = businessLogic.deleteAllTables();
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
				result =  true;
			}
		} catch (IllegalModelStateException e) {
			BOMessageDialog.showError(this, e.getMessage()); //$NON-NLS-1$
		} catch (StaleObjectStateException e) {
			BOMessageDialog.showError(this, Messages.getString("ShopTableForm.16")); //$NON-NLS-1$
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
			editTableFormCapacityButtons.setTableCapacity(String.valueOf(table.getCapacity()));
			rbFree.setSelected(true);
			rbDisable.setSelected(table.isDisable());
			setBorder(BorderFactory.createTitledBorder(Messages.getString("ShopTableForm.56"))); //$NON-NLS-1$
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
		table.setCapacity(editTableFormCapacityButtons.getTableCapacity());
		List<ShopTableType> checkValues = tableTypeCBoxList.getCheckedValues();
		table.setTypes(checkValues);
		table.setServing(false);
		table.setBooked(false);
		table.setDirty(false);
		table.setFree( rbFree.isSelected() );
		table.setDisable( rbDisable.isSelected() );

		return true;
	}
	
	@Override
	public void clearFields() {
		tfTableNo.setText("");//$NON-NLS-1$
		editTableFormCapacityButtons.setTableCapacity("");//$NON-NLS-1$
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
		editTableFormCapacityButtons.enableAllCapacityButtons(enable);
		rbFree.setEnabled(enable);
		rbDisable.setEnabled(enable);
	}

	@Override
	public void cancel() {
		setBorder(BorderFactory.createTitledBorder(Messages.getString("ShopTableForm.46"))); //$NON-NLS-1$
	}	

	@Override
	public void edit() {
		setBorder(BorderFactory.createTitledBorder(Messages.getString("ShopTableForm.17"))); //$NON-NLS-1$
	}

	@Override
	public String getDisplayText() {
		return Messages.getString("ShopTableForm.18"); //$NON-NLS-1$
	}
}