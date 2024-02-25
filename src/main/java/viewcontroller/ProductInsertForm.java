package viewcontroller;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import applauncher.Main;
import dao.exceptions.ProductInsertDAOException;
import dao.exceptions.SupplierInsertDAOException;
import model.Product;
import model.Supplier;
import service.IService;
import service.SupplierServiceImpl;
import service.ProductServiceImpl;
import service.dto.ProductDTO;
import service.dto.SupplierDTO;
import service.exceptions.NoDataProvidedException;
import viewcontroller.validation.ProductInsertValidator;

import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Map;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * The {@link ProductInsertForm} creates the form
 * for inserting a new Product to the database. 
 * It is accessed by the {@link Menu} class.
 */
public class ProductInsertForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField productNameTxt;
	private JTextField productDescriptionTxt;
	private JTextField productUnitPriceTxt;
	private JTextField productQuantityTxt;
	private JTextField productSupplierIDTxt;
	private final JList<Supplier> supplierList = new JList<>();
	private int chosenSupplierID;


	/**
	 * Create the frame.
	 */
	public ProductInsertForm() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				
				DefaultListModel<Supplier> dlm = new DefaultListModel<>();
				IService<SupplierDTO, Supplier> supplierService = new SupplierServiceImpl();
				
				try {
					List<Supplier> suppliers = supplierService.getAll();
					
					for(Supplier supplier : suppliers) {
						dlm.addElement(supplier);
					}
					
					supplierList.setModel(dlm);
					
				} catch(SupplierInsertDAOException e1) {
					String errorSummary = e1.getMessage();
					JOptionPane.showMessageDialog(null, errorSummary, "Error", JOptionPane.ERROR_MESSAGE);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		setBackground(new Color(255, 255, 240));
		setTitle("Insert Product");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 964, 512);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(423, 60, 1, 262);
		contentPane.add(separator);
		
		JPanel productInsertPanel = new JPanel();
		productInsertPanel.setBounds(37, 29, 361, 364);
		contentPane.add(productInsertPanel);
		productInsertPanel.setLayout(null);
		
		JLabel productNameLbl = new JLabel("Name");
		productNameLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		productNameLbl.setForeground(new Color(165, 42, 42));
		productNameLbl.setBounds(12, 47, 92, 15);
		productInsertPanel.add(productNameLbl);
		
		JLabel productDescriptionLbl = new JLabel("Description");
		productDescriptionLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		productDescriptionLbl.setForeground(new Color(165, 42, 42));
		productDescriptionLbl.setBounds(12, 89, 92, 15);
		productInsertPanel.add(productDescriptionLbl);
		
		JLabel productUnitPriceLbl = new JLabel("Unit Price");
		productUnitPriceLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		productUnitPriceLbl.setForeground(new Color(165, 42, 42));
		productUnitPriceLbl.setBounds(12, 136, 92, 15);
		productInsertPanel.add(productUnitPriceLbl);
		
		JLabel productQuantityLbl = new JLabel("Quantity");
		productQuantityLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		productQuantityLbl.setForeground(new Color(165, 42, 42));
		productQuantityLbl.setBounds(12, 163, 92, 15);
		productInsertPanel.add(productQuantityLbl);
		
		JLabel productSupplierLbl = new JLabel("SupplierID");
		productSupplierLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		productSupplierLbl.setForeground(new Color(165, 42, 42));
		productSupplierLbl.setBounds(12, 190, 92, 15);
		productInsertPanel.add(productSupplierLbl);
		
		productNameTxt = new JTextField();
		productNameTxt.setBounds(122, 45, 177, 19);
		productInsertPanel.add(productNameTxt);
		productNameTxt.setColumns(10);
		
		productDescriptionTxt = new JTextField();
		productDescriptionTxt.setColumns(10);
		productDescriptionTxt.setBounds(122, 72, 177, 50);
		productInsertPanel.add(productDescriptionTxt);
		
		productUnitPriceTxt = new JTextField();
		productUnitPriceTxt.setColumns(10);
		productUnitPriceTxt.setBounds(122, 134, 60, 19);
		productInsertPanel.add(productUnitPriceTxt);
		
		productQuantityTxt = new JTextField();
		productQuantityTxt.setColumns(10);
		productQuantityTxt.setBounds(122, 161, 60, 19);
		productInsertPanel.add(productQuantityTxt);
		
		productSupplierIDTxt = new JTextField();
		productSupplierIDTxt.setColumns(10);
		productSupplierIDTxt.setBounds(122, 188, 60, 19);
		productInsertPanel.add(productSupplierIDTxt);
		
		JButton newProductBtn = new JButton("Add");
		newProductBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Map<String, String> errors;
				ProductDTO productDTO;
				IService<ProductDTO, Product> productService = new ProductServiceImpl();
				
				try {
					
					String name = productNameTxt.getText().trim();
					String description = productDescriptionTxt.getText().trim();
					String unitPrice = productUnitPriceTxt.getText().trim();
					String quantity = productQuantityTxt.getText().trim();
					String supplierID = productSupplierIDTxt.getText().trim();
					
					productDTO = new ProductDTO(name, description, unitPrice, quantity, supplierID);
					errors = ProductInsertValidator.validate(productDTO);
					
					if(!errors.isEmpty()) {
						String nameMessage = (errors.get("Name") != null) ? "Name: " + errors.get("Name") : "";
						String descriptionMessage = (errors.get("Description") != null) ? "Description: " + errors.get("Description") : "";
						String unitPriceMessage = (errors.get("Unit Price") != null) ? "Unit Price: " + errors.get("Unit Price") : "";
						String quantityMessage = (errors.get("Quantity") != null) ? "Quantity: " + errors.get("Quantity") : "";
						String supplierIDMessage = (errors.get("SupplierID") != null) ? "SupplierID: " + errors.get("SupplierID") : "";
						
						String errorMessage = ProductInsertValidator.createErrorMessage(nameMessage, descriptionMessage, unitPriceMessage, quantityMessage, supplierIDMessage);
						JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					productService.insert(productDTO);
					JOptionPane.showMessageDialog(null, "Product added to database", "Insert", JOptionPane.PLAIN_MESSAGE);
					
				} catch (ProductInsertDAOException | NoDataProvidedException e1) {
					String errorSummary = e1.getMessage();
					JOptionPane.showMessageDialog(null, errorSummary, "Error", JOptionPane.ERROR_MESSAGE);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				productNameTxt.setText("");
				productDescriptionTxt.setText("");
				productUnitPriceTxt.setText("");
				productQuantityTxt.setText("");
				productSupplierIDTxt.setText("");
			}
		});
		newProductBtn.setForeground(new Color(0, 0, 255));
		newProductBtn.setBounds(36, 252, 92, 25);
		productInsertPanel.add(newProductBtn);
		
		JButton newProductCloseBtn = new JButton("Close");
		newProductCloseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getProductInsertForm().setVisible(false);
				Main.getMenu().setEnabled(true);
			}
		});
		newProductCloseBtn.setForeground(new Color(0, 0, 255));
		newProductCloseBtn.setBounds(244, 252, 92, 25);
		productInsertPanel.add(newProductCloseBtn);
		
		JButton newProductClearBtn = new JButton("Clear");
		newProductClearBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				productNameTxt.setText("");
				productDescriptionTxt.setText("");
				productUnitPriceTxt.setText("");
				productQuantityTxt.setText("");
				productSupplierIDTxt.setText("");
			}
		});
		newProductClearBtn.setForeground(new Color(0, 0, 255));
		newProductClearBtn.setBounds(140, 252, 92, 25);
		productInsertPanel.add(newProductClearBtn);
		
		JPanel supplierViewPanel = new JPanel();
		supplierViewPanel.setBounds(436, 29, 506, 424);
		contentPane.add(supplierViewPanel);
		supplierViewPanel.setLayout(null);
		supplierList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				chosenSupplierID = supplierList.getSelectedValue().getId();
				productSupplierIDTxt.setText(String.valueOf(chosenSupplierID));
			}
		});
		
		JScrollPane scrollPane = new JScrollPane(supplierList);
		scrollPane.setBounds(12, 12, 482, 400);
		supplierViewPanel.add(scrollPane);
		
		JLabel currentSuppliersLbl = new JLabel("Current Suppliers");
		currentSuppliersLbl.setForeground(new Color(165, 42, 42));
		currentSuppliersLbl.setBounds(624, 12, 134, 15);
		contentPane.add(currentSuppliersLbl);
	}
	
}
