package org.marso.floormanager;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.floreantpos.POSConstants;
import com.floreantpos.model.OrderType;
import com.floreantpos.model.ShopTable;
import com.floreantpos.model.Ticket;
import com.floreantpos.swing.PosButton;
import com.floreantpos.swing.PosUIManager;
import com.floreantpos.swing.ScrollableFlowPanel;
import com.floreantpos.ui.tableselection.TableSelector;
import com.jidesoft.swing.JideScrollPane;

import net.miginfocom.swing.MigLayout;

public class FloorManagerTableSelector extends TableSelector implements ActionListener {
	private static final long serialVersionUID = 1L;

	public FloorManagerTableSelector(){	
		super();
		this.setName(this.getClass().getName());
		System.out.println("FloorManagerTableSelector.constructor()");	
		initialize();
	}
	
	private void initialize() {
		setLayout(new BorderLayout(10, 10));

		JPanel centerPanel = new JPanel(new java.awt.BorderLayout(5, 5));
		TitledBorder titledBorder1 = BorderFactory.createTitledBorder(null, POSConstants.TABLES, TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION);	
		centerPanel.setBorder(new CompoundBorder(titledBorder1, new EmptyBorder(2, 2, 2, 2)));

		ScrollableFlowPanel buttonsPanel = new ScrollableFlowPanel(FlowLayout.CENTER);
		JideScrollPane scrollPane = new JideScrollPane(buttonsPanel, JideScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JideScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setPreferredSize(PosUIManager.getSize(60, 0));
		centerPanel.add(scrollPane, java.awt.BorderLayout.CENTER);

		add(centerPanel, java.awt.BorderLayout.CENTER);

		JPanel rightPanel = new JPanel(new BorderLayout(20, 20));
		rightPanel.setPreferredSize(PosUIManager.getSize(120, 0));
		TitledBorder titledBorder2 = BorderFactory.createTitledBorder(null, "-", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION); //$NON-NLS-1$
		rightPanel.setBorder(new CompoundBorder(titledBorder2, new EmptyBorder(2, 2, 6, 2)));

		PosButton btnDone = new PosButton(POSConstants.SAVE_BUTTON_TEXT);
		btnDone.addActionListener(this);
		PosButton btnRefresh = new PosButton(POSConstants.REFRESH);
		btnRefresh.addActionListener(this);
		PosButton btnCancelDialog = new PosButton(POSConstants.CANCEL);
		btnCancelDialog.addActionListener(this);

		JPanel southbuttonPanel = new JPanel(new MigLayout("ins 2 2 0 2, hidemode 3, flowy", "grow", "")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		southbuttonPanel.add(btnDone, "grow");
		southbuttonPanel.add(btnRefresh, "grow");
		southbuttonPanel.add(btnCancelDialog, "grow");
		rightPanel.add(southbuttonPanel, BorderLayout.SOUTH);
		add(rightPanel, java.awt.BorderLayout.EAST);		

		System.out.println("FloorManagerTableSelector.initialize()");			
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//TODO
		System.out.println("FloorManagerTableSelector.actionPerformed.by."+e.getSource().getClass().getName());		
	}
	
	@Override
	public void redererTables() {
		System.out.println("FloorManagerTableSelector.redererTables()");		
	}

	@Override
	public List<ShopTable> getSelectedTables() {
		System.out.println("FloorManagerTableSelector.getSelectedTables()");			
		return null;
	}

	@Override
	public void updateView(boolean update) {
		System.out.println("FloorManagerTableSelector.updateView()");		
	}

	public void tablesSelected(OrderType orderType, List<ShopTable> selectedTables) {
		System.out.println("FloorManagerTableSelector.tablesSelected()");		
//		
//		try {
//			OrderServiceFactory.getOrderService().createNewTicket(orderType, selectedTables,null);
//		} catch (TicketAlreadyExistsException e) {
//			e.printStackTrace();
//		}
	}
//==================================
	public OrderType getOrderType() {
		System.out.println("FloorManagerTableSelector.getOrderType()");				
		return null;
	}

	public void setOrderType(OrderType orderType) {
		System.out.println("FloorManagerTableSelector.setOrderType()");						
		this.orderType = orderType;
	}

	public boolean isCreateNewTicket() {
		System.out.println("FloorManagerTableSelector.isCreateNewTicket()");						
		return false;
	}

	public void setCreateNewTicket(boolean createNewTicket) {
		System.out.println("FloorManagerTableSelector.setCreateNewTicket()");				
	}

	public void setTicket(Ticket ticket) {
		System.out.println("FloorManagerTableSelector.setTicket()");				
	}

	public Ticket getTicket() {
		System.out.println("FloorManagerTableSelector.getTicket()");				
		return null;
	}

}