package viewcontroller;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import applauncher.Main;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * The {@link Menu} class creates the main Menu form.
 */
public class Menu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public Menu() {
		setForeground(new Color(255, 255, 240));
		setBackground(new Color(47, 79, 79));
		setTitle("Main Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(1, 4, 434, 256);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel insertLbl = new JLabel("Insert");
		insertLbl.setForeground(new Color(128, 0, 0));
		insertLbl.setFont(new Font("Dialog", Font.BOLD, 13));
		insertLbl.setBounds(297, 22, 47, 15);
		panel.add(insertLbl);
		
		JLabel searchUpdateDeleteLbl = new JLabel("Search/Update/Delete");
		searchUpdateDeleteLbl.setForeground(new Color(128, 0, 0));
		searchUpdateDeleteLbl.setFont(new Font("Dialog", Font.BOLD, 13));
		searchUpdateDeleteLbl.setBounds(23, 22, 170, 15);
		panel.add(searchUpdateDeleteLbl);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(208, 37, 1, 148);
		panel.add(separator);
		
		JButton newCustomerBtn = new JButton("");
		newCustomerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getMenu().setEnabled(false);
				Main.getCustomerInsertForm().setVisible(true);
			}
		});
		newCustomerBtn.setBounds(234, 69, 39, 25);
		panel.add(newCustomerBtn);
		
		JLabel newCustomerLbl = new JLabel("New Customer");
		newCustomerLbl.setForeground(new Color(128, 0, 0));
		newCustomerLbl.setBounds(291, 69, 111, 15);
		panel.add(newCustomerLbl);
		
		JButton newSupplierBtn = new JButton("");
		newSupplierBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getMenu().setEnabled(false);
				Main.getSupplierInsertForm().setVisible(true);
			}
		});
		newSupplierBtn.setBounds(234, 106, 39, 25);
		panel.add(newSupplierBtn);
		
		JButton newProductBtn = new JButton("");
		newProductBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getMenu().setEnabled(false);
				Main.getProductInsertForm().setVisible(true);
			}
		});
		newProductBtn.setBounds(234, 143, 39, 25);
		panel.add(newProductBtn);
		
		JButton newOrderBtn = new JButton("");
		newOrderBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getMenu().setEnabled(false);
				Main.getOrderInsertForm().setVisible(true);
			}
		});
		newOrderBtn.setBounds(234, 180, 39, 25);
		panel.add(newOrderBtn);
		
		JLabel newSupplierLbl = new JLabel("New Supplier");
		newSupplierLbl.setForeground(new Color(128, 0, 0));
		newSupplierLbl.setBounds(291, 106, 111, 15);
		panel.add(newSupplierLbl);
		
		JLabel newProductLbl = new JLabel("New Product");
		newProductLbl.setForeground(new Color(128, 0, 0));
		newProductLbl.setBounds(291, 143, 111, 15);
		panel.add(newProductLbl);
		
		JLabel newOrderLbl = new JLabel("New Order");
		newOrderLbl.setForeground(new Color(128, 0, 0));
		newOrderLbl.setBounds(291, 180, 111, 15);
		panel.add(newOrderLbl);
		
		JLabel label = new JLabel("<NOT DEVELOPED>");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(23, 95, 170, 15);
		panel.add(label);
	}
}
