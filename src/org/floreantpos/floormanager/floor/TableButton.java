package org.floreantpos.floormanager.floor;

import javax.swing.JToggleButton;

import com.floreantpos.model.ShopTable;

public class TableButton extends JToggleButton {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ShopTable table;
    
    public TableButton(final ShopTable table) {
        this.table = table;
        this.setText(table.getTableNumber() + "");
        this.setBounds(table.getX() - 20, table.getY() - 20, 40, 40);
    }
    
    public ShopTable getTable() {
        return this.table;
    }
}