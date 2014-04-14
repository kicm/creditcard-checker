package creditcard;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;
import java.awt.Window.Type;

public class CheckFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtCardNumber;
	private CreditCard myCard;
	private JLabel lblResult;
	private JLabel lblCardType;
	private JLabel lblIssuer;
	private JLabel lblLuhnAlgorithmCheck;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckFrame frame = new CheckFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CheckFrame() {
		setResizable(false);
		setTitle("Credit Card Checker");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblIssuer = new JLabel("Credit Card Issuer:");
		lblIssuer.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblIssuer.setBounds(6, 88, 438, 16);
		contentPane.add(lblIssuer);
		
		lblLuhnAlgorithmCheck = new JLabel("Luhn Algorithm Check:");
		lblLuhnAlgorithmCheck.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblLuhnAlgorithmCheck.setBounds(6, 147, 173, 16);
		contentPane.add(lblLuhnAlgorithmCheck);
		
		lblResult = new JLabel("Result");
		lblResult.setBounds(6, 164, 438, 16);
		contentPane.add(lblResult);

		lblCardType = new JLabel("CardType");
		lblCardType.setBounds(6, 106, 438, 16);
		contentPane.add(lblCardType);
		
		myCard = new CreditCard();
		
		txtCardNumber = new JTextField();
		txtCardNumber.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				myCard.setNumber(txtCardNumber.getText());
				
				lblCardType.setText(myCard.getCardType());
				lblResult.setText(null);
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				myCard.setNumber(txtCardNumber.getText());
				
				lblCardType.setText(myCard.getCardType());
				lblResult.setText(null);
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				myCard.setNumber(txtCardNumber.getText());
				
				lblCardType.setText(myCard.getCardType());
				lblResult.setText(null);
			}
		});
		txtCardNumber.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					myCard.setNumber(txtCardNumber.getText());
					lblResult.setText(myCard.checkValidity());
					lblCardType.setText(myCard.getCardType());
				}
			}
		});
		txtCardNumber.setText("Card number");
		txtCardNumber.setBounds(6, 6, 438, 28);
		contentPane.add(txtCardNumber);
		txtCardNumber.setColumns(10);

		JButton btnCheck = new JButton("check");

		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myCard.setNumber(txtCardNumber.getText());
				
				lblResult.setText(myCard.checkValidity());	
				lblCardType.setText(myCard.getCardType());
			}
		});
		btnCheck.setBounds(6, 41, 117, 29);
		contentPane.add(btnCheck);
	}
}
