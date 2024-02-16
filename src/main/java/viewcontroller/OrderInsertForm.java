package viewcontroller;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import applauncher.Main;
import dao.exceptions.CustomerDAOException;
import dao.exceptions.OrderDAOException;
import model.Customer;
import model.Order;
import service.dto.CustomerDTO;
import service.dto.OrderDTO;
import service.exceptions.NoDataProvidedException;
import viewcontroller.validation.OrderInsertValidator;
import service.IService;
import service.OrderServiceImpl;
import service.CustomerServiceImpl;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * The {@link OrderInsertForm} creates the form
 * for inserting a new Order to the database. 
 * It is accessed by the {@link Menu} class.
 */
public class OrderInsertForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField newOrderCustomerIDTxt;
	private JTextField newOrderQuantityTxt;
	private JTextField newOrderPriceTxt;
	private JTextField newOrderShippingAddressTxt;
	private JTextField newOrderCityTxt;
	private final JList<Customer> customerList = new JList<>();
	private int chosenCustomerID;


	/**
	 * Create the frame.
	 */
	public OrderInsertForm() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				DefaultListModel<Customer> dlm = new DefaultListModel<>();
				IService<CustomerDTO, Customer> customerService = new CustomerServiceImpl();
				
				try {
					List<Customer> customers = customerService.getAll();
					
					for(Customer customer : customers) {
						dlm.addElement(customer);
					}
					
					customerList.setModel(dlm);
					
				} catch(CustomerDAOException e1) {
					String errorSummary = e1.getMessage();
					JOptionPane.showMessageDialog(null, errorSummary, "Error", JOptionPane.ERROR_MESSAGE);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		setTitle("Insert Order");
		setBackground(new Color(255, 255, 240));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 975, 536);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(423, 60, 1, 262);
		contentPane.add(separator);
		
		JPanel newOrderInsertPanel = new JPanel();
		newOrderInsertPanel.setBounds(37, 29, 361, 364);
		contentPane.add(newOrderInsertPanel);
		newOrderInsertPanel.setLayout(null);
		
		JLabel newOrderCustomerIDLbl = new JLabel("CustomerID");
		newOrderCustomerIDLbl.setForeground(new Color(165, 42, 42));
		newOrderCustomerIDLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		newOrderCustomerIDLbl.setBounds(40, 54, 129, 15);
		newOrderInsertPanel.add(newOrderCustomerIDLbl);
		
		JLabel newOrderQuantityLbl = new JLabel("Quantity");
		newOrderQuantityLbl.setForeground(new Color(165, 42, 42));
		newOrderQuantityLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		newOrderQuantityLbl.setBounds(40, 81, 129, 15);
		newOrderInsertPanel.add(newOrderQuantityLbl);
		
		JLabel newOrderPriceLbl = new JLabel("Price");
		newOrderPriceLbl.setForeground(new Color(165, 42, 42));
		newOrderPriceLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		newOrderPriceLbl.setBounds(40, 108, 129, 15);
		newOrderInsertPanel.add(newOrderPriceLbl);
		
		JLabel newOrderShippingAddressLbl = new JLabel("Shipping Address");
		newOrderShippingAddressLbl.setForeground(new Color(165, 42, 42));
		newOrderShippingAddressLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		newOrderShippingAddressLbl.setBounds(40, 135, 129, 15);
		newOrderInsertPanel.add(newOrderShippingAddressLbl);
		
		JLabel newOrderCityLbl = new JLabel("City");
		newOrderCityLbl.setForeground(new Color(165, 42, 42));
		newOrderCityLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		newOrderCityLbl.setBounds(40, 160, 129, 15);
		newOrderInsertPanel.add(newOrderCityLbl);
		
		newOrderCustomerIDTxt = new JTextField();
		newOrderCustomerIDTxt.setBounds(187, 52, 55, 19);
		newOrderInsertPanel.add(newOrderCustomerIDTxt);
		newOrderCustomerIDTxt.setColumns(10);
		
		newOrderQuantityTxt = new JTextField();
		newOrderQuantityTxt.setColumns(10);
		newOrderQuantityTxt.setBounds(187, 79, 55, 19);
		newOrderInsertPanel.add(newOrderQuantityTxt);
		
		newOrderPriceTxt = new JTextField();
		newOrderPriceTxt.setColumns(10);
		newOrderPriceTxt.setBounds(187, 106, 55, 19);
		newOrderInsertPanel.add(newOrderPriceTxt);
		
		newOrderShippingAddressTxt = new JTextField();
		newOrderShippingAddressTxt.setColumns(10);
		newOrderShippingAddressTxt.setBounds(187, 133, 114, 19);
		newOrderInsertPanel.add(newOrderShippingAddressTxt);
		
		newOrderCityTxt = new JTextField();
		newOrderCityTxt.setColumns(10);
		newOrderCityTxt.setBounds(187, 158, 114, 19);
		newOrderInsertPanel.add(newOrderCityTxt);
		
		JButton newOrderAddBtn = new JButton("Add");
		newOrderAddBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Map<String, String> errors = new HashMap<>();
				OrderDTO orderDTO;
				IService<OrderDTO, Order> orderService = new OrderServiceImpl();
				
				try {
					String customerID = String.valueOf(newOrderCustomerIDTxt.getText().trim());
					String quantity = String.valueOf(newOrderQuantityTxt.getText().trim());
					String price = String.valueOf(newOrderPriceTxt.getText().trim());
					String shippingAddress = newOrderShippingAddressTxt.getText().trim();
					String city = newOrderCityTxt.getText().trim();
					
					orderDTO = new OrderDTO(customerID, quantity, price, shippingAddress, city);
					errors = OrderInsertValidator.validate(orderDTO);
					
					if(!errors.isEmpty()) {
						String customerIDMessage = (errors.get("CustomerID") != null) ? "Customer ID: " + errors.get("CustomerID") : "";
						String quantityMessage = (errors.get("Quantity") != null) ? "Quantity: " + errors.get("Quantity") : "";
						String priceMessage = (errors.get("Price") != null) ? "Price: " + errors.get("Price") : "";
						String shippingAddressMessage = (errors.get("Shipping Address") != null) ? "Shipping Address: " + errors.get("Shipping Address") : "";
						String cityMessage = (errors.get("City") != null) ? "City: " + errors.get("City") : "";
						
						String errorMessage = OrderInsertValidator.createErrorMessage(customerIDMessage, quantityMessage, priceMessage, shippingAddressMessage, cityMessage);
						JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					orderService.insert(orderDTO);
					JOptionPane.showMessageDialog(null, "Order added to database", "Insert", JOptionPane.PLAIN_MESSAGE);
					
				} catch (OrderDAOException | NoDataProvidedException e1) {
					String errorSummary = e1.getMessage();
					JOptionPane.showMessageDialog(null, errorSummary, "Error", JOptionPane.ERROR_MESSAGE);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				newOrderCustomerIDTxt.setText("");
				newOrderQuantityTxt.setText("");
				newOrderPriceTxt.setText("");
				newOrderShippingAddressTxt.setText("");
				newOrderCityTxt.setText("");
			}
		});
		newOrderAddBtn.setForeground(new Color(0, 0, 255));
		newOrderAddBtn.setBounds(23, 223, 92, 25);
		newOrderInsertPanel.add(newOrderAddBtn);
		
		JButton newOrderClearBtn = new JButton("Clear");
		newOrderClearBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newOrderCustomerIDTxt.setText("");
				newOrderQuantityTxt.setText("");
				newOrderPriceTxt.setText("");
				newOrderShippingAddressTxt.setText("");
				newOrderCityTxt.setText("");
			}
		});
		newOrderClearBtn.setForeground(new Color(0, 0, 255));
		newOrderClearBtn.setBounds(127, 223, 92, 25);
		newOrderInsertPanel.add(newOrderClearBtn);
		
		JButton newOrderCloseBtn = new JButton("Close");
		newOrderCloseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getOrderInsertForm().setVisible(false);
				Main.getMenu().setEnabled(true);
			}
		});
		newOrderCloseBtn.setForeground(new Color(0, 0, 255));
		newOrderCloseBtn.setBounds(231, 223, 92, 25);
		newOrderInsertPanel.add(newOrderCloseBtn);
		
		JPanel customerViewPanel = new JPanel();
		customerViewPanel.setBounds(436, 29, 506, 424);
		contentPane.add(customerViewPanel);
		customerViewPanel.setLayout(null);
		customerList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				chosenCustomerID = customerList.getSelectedValue().getId();
				newOrderCustomerIDTxt.setText(String.valueOf(chosenCustomerID));
			}
		});
		
		JScrollPane scrollPane = new JScrollPane(customerList);
		scrollPane.setBounds(12, 12, 482, 400);
		customerViewPanel.add(scrollPane);
		
		JLabel currentCustomersLbl = new JLabel("Current Customers");
		currentCustomersLbl.setForeground(new Color(165, 42, 42));
		currentCustomersLbl.setBounds(622, 12, 147, 15);
		contentPane.add(currentCustomersLbl);
	}
}
