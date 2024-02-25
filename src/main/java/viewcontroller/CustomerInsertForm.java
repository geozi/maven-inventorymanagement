package viewcontroller;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import applauncher.Main;
import dao.exceptions.CustomerInsertDAOException;
import model.Customer;
import service.IService;
import service.CustomerServiceImpl;
import service.dto.CustomerDTO;
import service.exceptions.NoDataProvidedException;
import viewcontroller.validation.CustomerInsertValidator;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Map;
import java.awt.event.ActionEvent;

/**
 * The {@link CustomerInsertForm} creates the form
 * for inserting a new Customer to the database. 
 * It is accessed by the {@link Menu} class.
 */
public class CustomerInsertForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField firstnameTxt;
	private JTextField lastnameTxt;
	private JTextField mobilePhoneTxt;
	private JTextField emailTxt;
	private JTextField billingAddressTxt;
	private JTextField cityTxt;


	/**
	 * Create the frame.
	 */
	public CustomerInsertForm() {

		setBackground(new Color(255, 255, 240));
		setTitle("Insert Customer");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 12, 426, 244);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel firstnameLbl = new JLabel("Firstname");
		firstnameLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		firstnameLbl.setForeground(new Color(128, 0, 0));
		firstnameLbl.setBounds(36, 23, 106, 15);
		panel.add(firstnameLbl);
		
		JLabel lastnameLbl = new JLabel("Lastname");
		lastnameLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		lastnameLbl.setForeground(new Color(128, 0, 0));
		lastnameLbl.setBounds(36, 50, 106, 15);
		panel.add(lastnameLbl);
		
		JLabel mobilePhoneLbl = new JLabel("Mobile Phone");
		mobilePhoneLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		mobilePhoneLbl.setForeground(new Color(128, 0, 0));
		mobilePhoneLbl.setBounds(36, 77, 106, 15);
		panel.add(mobilePhoneLbl);
		
		JLabel emailLbl = new JLabel("Email");
		emailLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		emailLbl.setForeground(new Color(128, 0, 0));
		emailLbl.setBounds(36, 104, 106, 15);
		panel.add(emailLbl);
		
		JLabel billingAddressLbl = new JLabel("Billing Address");
		billingAddressLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		billingAddressLbl.setForeground(new Color(128, 0, 0));
		billingAddressLbl.setBounds(36, 131, 106, 15);
		panel.add(billingAddressLbl);
		
		JLabel cityLbl = new JLabel("City");
		cityLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		cityLbl.setForeground(new Color(128, 0, 0));
		cityLbl.setBounds(36, 158, 106, 15);
		panel.add(cityLbl);
		
		firstnameTxt = new JTextField();
		firstnameTxt.setBounds(175, 21, 158, 19);
		panel.add(firstnameTxt);
		firstnameTxt.setColumns(10);
		
		lastnameTxt = new JTextField();
		lastnameTxt.setColumns(10);
		lastnameTxt.setBounds(175, 48, 158, 19);
		panel.add(lastnameTxt);
		
		mobilePhoneTxt = new JTextField();
		mobilePhoneTxt.setColumns(10);
		mobilePhoneTxt.setBounds(175, 75, 158, 19);
		panel.add(mobilePhoneTxt);
		
		emailTxt = new JTextField();
		emailTxt.setColumns(10);
		emailTxt.setBounds(175, 102, 158, 19);
		panel.add(emailTxt);
		
		billingAddressTxt = new JTextField();
		billingAddressTxt.setColumns(10);
		billingAddressTxt.setBounds(175, 129, 158, 19);
		panel.add(billingAddressTxt);
		
		cityTxt = new JTextField();
		cityTxt.setColumns(10);
		cityTxt.setBounds(175, 156, 158, 19);
		panel.add(cityTxt);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Map<String, String> errors;
				CustomerDTO customerDTO;
				IService<CustomerDTO, Customer> customerService = new CustomerServiceImpl();
				
				try {
					String firstname = firstnameTxt.getText().trim();
					String lastname = lastnameTxt.getText().trim();
					String mobilePhone = mobilePhoneTxt.getText().trim();
					String email = emailTxt.getText().trim();
					String billingAddress = billingAddressTxt.getText().trim();
					String city = cityTxt.getText().trim();
					
					customerDTO = new CustomerDTO(firstname, lastname, mobilePhone, email, billingAddress, city);
					errors = CustomerInsertValidator.validate(customerDTO);
					
					if(!errors.isEmpty()) {
						String firstnameMessage = (errors.get("Firstname") != null) ? "Firstname: " + errors.get("Firstname") : "" ;
						String lastnameMessage = (errors.get("Lastname") != null) ? "Lastname: " + errors.get("Lastname") : "";
						String mobilePhoneMessage = (errors.get("Mobile Phone") != null) ? "Mobile Phone: " + errors.get("Mobile Phone") : "";
						String emailMessage = (errors.get("Email") != null) ? "Email: " + errors.get("Email") : "";
						String billingAddressMessage = (errors.get("Billing Address") != null) ? "Billing Address: " + errors.get("Billing Address") : "";
						String cityMessage = (errors.get("City") != null) ? "City: " + errors.get("City") : "";
						
						String errorMessage = CustomerInsertValidator.createErrorMessage(firstnameMessage, lastnameMessage, mobilePhoneMessage, emailMessage, billingAddressMessage, cityMessage);
						
						JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					customerService.insert(customerDTO);
					JOptionPane.showMessageDialog(null, "Customer added to database", "Insert", JOptionPane.PLAIN_MESSAGE); 
					
					firstnameTxt.setText("");
					lastnameTxt.setText("");
					mobilePhoneTxt.setText("");
					emailTxt.setText("");
					billingAddressTxt.setText("");
					cityTxt.setText("");
					
					
					} catch(CustomerInsertDAOException | NoDataProvidedException e1) {
						String errorSummary = e1.getMessage();
						JOptionPane.showMessageDialog(null, errorSummary, "Error", JOptionPane.ERROR_MESSAGE);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
			}
		});
		btnAdd.setForeground(new Color(0, 0, 255));
		btnAdd.setBounds(39, 207, 117, 25);
		panel.add(btnAdd);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getCustomerInsertForm().setVisible(false);
				Main.getMenu().setEnabled(true);
				}
		});
		btnClose.setForeground(new Color(0, 0, 255));
		btnClose.setBounds(297, 207, 117, 25);
		panel.add(btnClose);
		
		JButton newCustomerClearBtn = new JButton("Clear");
		newCustomerClearBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				firstnameTxt.setText("");
				lastnameTxt.setText("");
				mobilePhoneTxt.setText("");
				emailTxt.setText("");
				billingAddressTxt.setText("");
				cityTxt.setText("");
			}
		});
		newCustomerClearBtn.setForeground(new Color(0, 0, 255));
		newCustomerClearBtn.setBounds(168, 207, 117, 25);
		panel.add(newCustomerClearBtn);
	}
}
