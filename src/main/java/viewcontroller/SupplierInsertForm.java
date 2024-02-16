package viewcontroller;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import applauncher.Main;
import dao.exceptions.SupplierDAOException;
import model.Supplier;
import service.SupplierServiceImpl;
import service.IService;
import service.dto.SupplierDTO;
import service.exceptions.NoDataProvidedException;
import viewcontroller.validation.SupplierInsertValidator;

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
 * The {@link SupplierInsertForm} creates the form
 * for inserting a new Supplier to the database. 
 * It is accessed by the {@link Menu} class.
 */
public class SupplierInsertForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameTxt;
	private JTextField mobilePhoneTxt;
	private JTextField emailTxt;
	private JTextField addressTxt;
	private JTextField cityTxt;


	/**
	 * Create the frame.
	 */
	public SupplierInsertForm() {
		setBackground(new Color(255, 255, 240));
		setTitle("Insert Supplier");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 12, 416, 246);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel nameLbl = new JLabel("Name");
		nameLbl.setForeground(new Color(128, 0, 0));
		nameLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		nameLbl.setBounds(68, 24, 99, 15);
		panel.add(nameLbl);
		
		JLabel mobilePhoneLbl = new JLabel("Mobile Phone");
		mobilePhoneLbl.setForeground(new Color(128, 0, 0));
		mobilePhoneLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		mobilePhoneLbl.setBounds(68, 51, 99, 15);
		panel.add(mobilePhoneLbl);
		
		JLabel emailLbl = new JLabel("Email");
		emailLbl.setForeground(new Color(128, 0, 0));
		emailLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		emailLbl.setBounds(68, 78, 99, 15);
		panel.add(emailLbl);
		
		JLabel addressLbl = new JLabel("Address");
		addressLbl.setForeground(new Color(128, 0, 0));
		addressLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		addressLbl.setBounds(68, 105, 99, 15);
		panel.add(addressLbl);
		
		JLabel cityLbl = new JLabel("City");
		cityLbl.setForeground(new Color(128, 0, 0));
		cityLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		cityLbl.setBounds(68, 132, 99, 15);
		panel.add(cityLbl);
		
		nameTxt = new JTextField();
		nameTxt.setBounds(185, 22, 170, 19);
		panel.add(nameTxt);
		nameTxt.setColumns(10);
		
		mobilePhoneTxt = new JTextField();
		mobilePhoneTxt.setColumns(10);
		mobilePhoneTxt.setBounds(185, 49, 170, 19);
		panel.add(mobilePhoneTxt);
		
		emailTxt = new JTextField();
		emailTxt.setColumns(10);
		emailTxt.setBounds(185, 76, 170, 19);
		panel.add(emailTxt);
		
		addressTxt = new JTextField();
		addressTxt.setColumns(10);
		addressTxt.setBounds(185, 103, 170, 19);
		panel.add(addressTxt);
		
		cityTxt = new JTextField();
		cityTxt.setColumns(10);
		cityTxt.setBounds(185, 130, 170, 19);
		panel.add(cityTxt);
		
		JButton newSupplierBtn = new JButton("Add");
		newSupplierBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Map<String, String> errors;
				SupplierDTO supplierDTO;
				IService<SupplierDTO, Supplier> supplierService = new SupplierServiceImpl();
				
				try {
					String name = nameTxt.getText().trim();
					String mobilePhone = mobilePhoneTxt.getText().trim();
					String email = emailTxt.getText().trim();
					String address = addressTxt.getText().trim();
					String city = cityTxt.getText().trim();
					
					supplierDTO = new SupplierDTO(name, mobilePhone, email, address, city);
					errors = SupplierInsertValidator.validate(supplierDTO);
					if(!errors.isEmpty()) {
						String nameMessage = (errors.get("Name") != null) ? "Name: " + errors.get("Name") : "";
						String mobilePhoneMessage = (errors.get("Mobile Phone") != null) ? "Mobile Phone: " + errors.get("Mobile Phone") : "";
						String emailMessage = (errors.get("Email") != null) ? "Email: " + errors.get("Email") : "";
						String addressMessage = (errors.get("Address") != null) ? "Address: " + errors.get("Address") : "";
						String cityMessage = (errors.get("City") != null) ? "City: " + errors.get("City") : "";
						
						String errorMessage = SupplierInsertValidator.createErrorMessage(nameMessage, mobilePhoneMessage, emailMessage, addressMessage, cityMessage);
						JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					supplierService.insert(supplierDTO);
					JOptionPane.showMessageDialog(null, "Supplier added to database", "Insert", JOptionPane.PLAIN_MESSAGE);
					
					nameTxt.setText("");
					mobilePhoneTxt.setText("");
					emailTxt.setText("");
					addressTxt.setText("");
					cityTxt.setText("");
					
				} catch(SupplierDAOException | NoDataProvidedException e1) {
					String errorSummary = e1.getMessage();
					JOptionPane.showMessageDialog(null, errorSummary, "Error", JOptionPane.ERROR_MESSAGE);
				} catch(Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		newSupplierBtn.setForeground(new Color(0, 0, 255));
		newSupplierBtn.setBounds(31, 176, 117, 25);
		panel.add(newSupplierBtn);
		
		JButton newSupplierCloseBtn = new JButton("Close");
		newSupplierCloseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getSupplierInsertForm().setVisible(false);
				Main.getMenu().setEnabled(true);
			}
		});
		newSupplierCloseBtn.setForeground(new Color(0, 0, 255));
		newSupplierCloseBtn.setBounds(287, 176, 117, 25);
		panel.add(newSupplierCloseBtn);
		
		JButton newSupplerClearBtn = new JButton("Clear");
		newSupplerClearBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nameTxt.setText("");
				mobilePhoneTxt.setText("");
				emailTxt.setText("");
				addressTxt.setText("");
				cityTxt.setText("");
			}
		});
		newSupplerClearBtn.setForeground(new Color(0, 0, 255));
		newSupplerClearBtn.setBounds(160, 176, 117, 25);
		panel.add(newSupplerClearBtn);
	}
}
