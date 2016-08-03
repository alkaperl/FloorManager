package org.marso.floormanager.table;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.floreantpos.swing.IntegerTextField;
import com.floreantpos.swing.PosButton;

public class EditTableFormCapacityButtons implements ActionListener {
	private IntegerTextField tfTableCapacity = new IntegerTextField(6);
	private JButton btnCapacityOne 	= new PosButton("1"); //$NON-NLS-1$
	private JButton btnCapacityTwo 	= new PosButton("2"); //$NON-NLS-1$
	private JButton btnCapacityFour = new PosButton("4"); //$NON-NLS-1$
	private JButton btnCapacitySix 	= new PosButton("6"); //$NON-NLS-1$
	private JButton btnCapacityEight= new PosButton("8"); //$NON-NLS-1$
	private JButton btnCapacityTen 	= new PosButton("10"); //$NON-NLS-1$
	
	public EditTableFormCapacityButtons(JPanel tableForm){
		tableForm.add(tfTableCapacity, "flowx,grow,cell 1 3"); //$NON-NLS-1$
		
		btnCapacityOne.setPreferredSize(	new Dimension(52, 52));
		btnCapacityTwo.setPreferredSize(	new Dimension(52, 52));
		btnCapacityFour.setPreferredSize(	new Dimension(52, 52));
		btnCapacitySix.setPreferredSize(	new Dimension(52, 52));
		btnCapacityEight.setPreferredSize(	new Dimension(52, 52));		
		btnCapacityTen.setPreferredSize(	new Dimension(52, 52));

		btnCapacityOne.addActionListener(this);
		btnCapacityTwo.addActionListener(this);
		btnCapacityFour.addActionListener(this);
		btnCapacitySix.addActionListener(this);
		btnCapacityEight.addActionListener(this);
		btnCapacityTen.addActionListener(this);

		tableForm.add(btnCapacityOne,  "cell 1 3"); //$NON-NLS-1$
		tableForm.add(btnCapacityTwo,  "cell 1 3"); //$NON-NLS-1$
		tableForm.add(btnCapacityFour, "cell 1 3"); //$NON-NLS-1$
		tableForm.add(btnCapacitySix,  "cell 1 3"); //$NON-NLS-1$
		tableForm.add(btnCapacityEight,"cell 1 3"); //$NON-NLS-1$
		tableForm.add(btnCapacityTen,  "cell 1 3"); //$NON-NLS-1$
	}
	
	public void toggleCapacityButtons(boolean enable){
		tfTableCapacity.setEnabled(enable);
		btnCapacityOne.setEnabled(enable);
		btnCapacityTwo.setEnabled(enable);
		btnCapacityFour.setEnabled(enable);
		btnCapacitySix.setEnabled(enable);
		btnCapacityEight.setEnabled(enable);
		btnCapacityTen.setEnabled(enable);
	}

	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		System.out.println("TableCapacityButton:"+b.getText());
		setTableCapacity(b.getText());
	}
	
	public void setTableCapacity(String cap){
		tfTableCapacity.setText(cap);
	}

	public Integer getTableCapacity(){
		return tfTableCapacity.getInteger();
	}
}