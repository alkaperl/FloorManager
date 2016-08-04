package org.floreantpos.floormanager.tabletype;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import org.hibernate.StaleObjectStateException;

import com.floreantpos.Messages;
import com.floreantpos.bo.ui.BOMessageDialog;
import com.floreantpos.model.ShopTableType;
import com.floreantpos.model.dao.ShopTableTypeDAO;
import com.floreantpos.model.util.IllegalModelStateException;
import com.floreantpos.swing.FixedLengthTextField;
import com.floreantpos.ui.BeanEditor;

import net.miginfocom.swing.MigLayout;

public class TableTypeForm extends BeanEditor<ShopTableType> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	private final EditTableBusinessLogic businessLogic = new EditTableBusinessLogic();
	private FixedLengthTextField tfTableTypeId;
	private FixedLengthTextField tfTableTypeDescription;
	private FixedLengthTextField tfTableTypeName;
	
	public TableTypeForm() {
		setPreferredSize(new Dimension(600, 800));
		setLayout(new MigLayout("", "[][grow]", "[][][][][][][][]")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		setBorder(BorderFactory.createTitledBorder(Messages.getString("ShopTableForm.19"))); //$NON-NLS-1$

		add( new JLabel( "ID" ), "cell 0 0,alignx trailing,aligny center"); //$NON-NLS-1$

		tfTableTypeId = new FixedLengthTextField();
		tfTableTypeId.setText("id");
		add( tfTableTypeId, "cell 1 0,aligny top"); //$NON-NLS-1$
		tfTableTypeId.setEnabled(false);
		
		add( new JLabel( "NAME" ), "cell 0 1,alignx trailing,aligny center"); //$NON-NLS-1$

		tfTableTypeName = new FixedLengthTextField();
		tfTableTypeName.setText("name");
		add( tfTableTypeName, "cell 1 1,aligny top"); //$NON-NLS-1$
		
		add( new JLabel( "DESCRIPTION" ), "cell 0 2,alignx trailing"); //$NON-NLS-1$

		tfTableTypeDescription = new FixedLengthTextField();
		tfTableTypeDescription.setText("desc");
		add( tfTableTypeDescription, "cell 1 2,growx"); //$NON-NLS-1$

		add(new JLabel(), "grow,span"); //$NON-NLS-1$
	}

	@Override
	public void createNew() {
//		ShopTableType newShopTable = businessLogic.createNew();
//		setBean( newShopTable );
//		tfTableNo.setText( newShopTable.getName() );
//		editTableFormCapacityButtons.setTableCapacity("4");//$NON-NLS-1$
//		tfTableDescription.setText(""); //$NON-NLS-1$
//		tfTableName.setText(""); //$NON-NLS-1$
//		setBorder( BorderFactory.createTitledBorder( Messages.getString( "ShopTableForm.18" ) ) ); //$NON-NLS-1$
	}
	
//	@Override
//	public boolean delete() {
//		boolean result = businessLogic.delete( getBean() );
//		if ( result ){
//			clearFields();
//		}
//		return result;
//	}
//
//	public boolean deleteAllTables() {
//		boolean result = businessLogic.deleteAllTables();
//		if ( result ){
//			clearFields();
//		}
//		return result;
//	}

	@Override
	public boolean save() {
		boolean result = false;
		try {
			if ( updateModel() ){
				ShopTableType table = (ShopTableType) getBean();
				ShopTableTypeDAO.getInstance().saveOrUpdate(table);
				updateView();
				System.out.println("save:END");				
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
//		ShopTable table = (ShopTable) getBean();
//		if (table != null) {
//			tableTypeCBoxList.setModel( ShopTableTypeDAO.getInstance().findAll() );
//			tableTypeCBoxList.selectItems(table.getTypes());
//			tfTableNo.setText(String.valueOf(table.getTableNumber()));
//			tfTableName.setText(table.getName());
//			tfTableDescription.setText(table.getDescription());
//			editTableFormCapacityButtons.setTableCapacity(String.valueOf(table.getCapacity()));
//			rbFree.setSelected(true);
//			rbDisable.setSelected(table.isDisable());
//			setBorder(BorderFactory.createTitledBorder(Messages.getString("ShopTableForm.56"))); //$NON-NLS-1$
//			System.out.println("updateView:END");				
//		}
	}

	@Override
	protected boolean updateModel() throws IllegalModelStateException {
//		ShopTable table = (ShopTable) getBean();
//		if (table == null) {
//			table = new ShopTable();
//			setBean(table, false);
//		}

//		table.setId(tfTableNo.getInteger());
//		table.setName(tfTableName.getText());
//		table.setDescription(tfTableDescription.getText());
//		table.setCapacity(editTableFormCapacityButtons.getTableCapacity());
//		List<ShopTableType> checkValues = tableTypeCBoxList.getCheckedValues();
//		table.setTypes(checkValues);
//		table.setServing(false);
//		table.setBooked(false);
//		table.setDirty(false);
//		table.setFree( rbFree.isSelected() );
//		table.setDisable( rbDisable.isSelected() );
//		setBean( table, false );
		System.out.println("updateModel:END");

		return true;
	}
	
	@Override
	public void clearFields() {
//		tfTableNo.setText("");//$NON-NLS-1$
//		editTableFormCapacityButtons.setTableCapacity("");//$NON-NLS-1$
//		tfTableDescription.setText(""); //$NON-NLS-1$
//		tfTableName.setText(""); //$NON-NLS-1$
//		rbFree.setSelected(false);
//		rbDisable.setSelected(false);
//		tableTypeCBoxList.unCheckAll();
	}
	
	@Override
	public void setFieldsEnable(boolean enable) {
//		tableTypeCBoxList.setEnabled(enable);
//		tableTypeCBoxList.clearSelection();
//		tfTableNo.setEnabled(enable);
//		tfTableName.setEnabled(enable);
//		tfTableDescription.setEnabled(enable);
//		editTableFormCapacityButtons.enableAllCapacityButtons(enable);
//		rbFree.setEnabled(enable);
//		rbDisable.setEnabled(enable);
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