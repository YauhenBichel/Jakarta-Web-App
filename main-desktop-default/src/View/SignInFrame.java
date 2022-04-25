package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;


import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.CardLayout;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.ImageIcon;

public class SignInFrame extends JFrame {

	private JPanel contentPane;
	
	private final JLabel lblNewLabel = new JLabel("Sign In To Your Account");
	private JTextField emailTextField;
	private JPasswordField passwordField;
	private JButton signInButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try
				{
					SignInFrame frame = new SignInFrame();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SignInFrame() 
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/Resouce/calendar.jfif"));
		setTitle("Holiday Booking System");
		
		initialiseComponents();
		createEvents();
	}
	
	/*
	 All code for creating events and components
	 */
	
	private void initialiseComponents() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 590, 532);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBackground(Color.LIGHT_GRAY);
		lblNewLabel_3.setIcon(new ImageIcon("src/Resouce/calendar.jfif"));
		
		JLabel lblNewLabel_1 = new JLabel("Email Address");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		emailTextField = new JTextField();
		emailTextField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		passwordField = new JPasswordField();
		
		JCheckBox rememberMeCheckBox = new JCheckBox("Remember Me");
		
		signInButton = new JButton("Sign In");

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(20)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGap(126))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGap(151))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(rememberMeCheckBox)
									.addPreferredGap(ComponentPlacement.RELATED, 117, Short.MAX_VALUE))))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(signInButton, GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)))
					.addContainerGap())
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(emailTextField, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
					.addGap(20))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
					.addGap(20))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(8)
					.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(emailTextField, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(15)
					.addComponent(rememberMeCheckBox, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(signInButton, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(158)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(45)
							.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(55))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(0)
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
							.addGap(3))
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE))
					.addGap(166))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 16, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(192)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
					.addGap(22)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(42))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	private void createEvents() 
	{
		signInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try {
					DefaultHttpClient httpClient = new DefaultHttpClient();
					HttpPost  postRequest  = new HttpPost (
							"http://localhost:8080/holidaysystem/api/auth/login");
					
					String template = new String(
							"{\"email\":\"%s\", \"password\":\"%s\"}");
					
					String email = emailTextField.getText();
					String password = passwordField.getText();
					String result = String.format(template, email, password);
					
					StringEntity input = new StringEntity(result);
					input.setContentType("application/json");
					postRequest.setEntity(input);
					
					HttpResponse response = httpClient.execute(postRequest);
					
					if(response.getStatusLine().getStatusCode() == 200) {
						HolidayRequestsFrame holidayRequestsFrame = new HolidayRequestsFrame();
						holidayRequestsFrame.setVisible(true);
						SignInFrame.this.setVisible(false);
					}
					else {
						JOptionPane.showMessageDialog(null,"You have entered wrong credentials","Error",JOptionPane.PLAIN_MESSAGE);
					}
					
				}catch(IOException ex){
					ex.printStackTrace();
				}
			}
		});
		
	}
}
