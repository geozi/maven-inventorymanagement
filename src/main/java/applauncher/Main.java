package applauncher;

import java.awt.EventQueue;

import viewcontroller.Menu;
import viewcontroller.OrderInsertForm;
import viewcontroller.ProductInsertForm;
import viewcontroller.SupplierInsertForm;
import viewcontroller.CustomerInsertForm;

/**
 * The {@link Main} class is the driver class of the application.
 */
public class Main {
	private static Menu menu;
	private static CustomerInsertForm customerInsertForm;
	private static SupplierInsertForm supplierInsertForm;
	private static ProductInsertForm productInsertForm;
	private static OrderInsertForm orderInsertForm;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					menu = new Menu();
					menu.setVisible(true);
					
					customerInsertForm = new CustomerInsertForm();
					customerInsertForm.setVisible(false);
					
					supplierInsertForm = new SupplierInsertForm();
					supplierInsertForm.setVisible(false);
					
					productInsertForm = new ProductInsertForm();
					productInsertForm.setVisible(false);
					
					orderInsertForm = new OrderInsertForm();
					orderInsertForm.setVisible(false);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static Menu getMenu() {
		return menu;
	}

	public static CustomerInsertForm getCustomerInsertForm() {
		return customerInsertForm;
	}

	public static SupplierInsertForm getSupplierInsertForm() {
		return supplierInsertForm;
	}

	public static ProductInsertForm getProductInsertForm() {
		return productInsertForm;
	}

	public static OrderInsertForm getOrderInsertForm() {
		return orderInsertForm;
	}

	

}
