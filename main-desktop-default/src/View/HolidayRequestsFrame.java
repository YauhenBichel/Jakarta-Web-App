package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import Utils.RetrieveDataHelper;

import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class HolidayRequestsFrame extends JFrame 
{
private JPanel contentPane;

private JPanel topPanel;
private JPanel bottomPanel;

private JButton newRequestButtonBottom;
private JLabel holidayRequestsLabel;
private JButton allRequestsButton;
private JButton allEmployeesButton;
private JButton newRequestButtonTop_1;

	public HolidayRequestsFrame() 
	{
		initialiseComponents();
		createEvents();
	}

	private void initialiseComponents() 
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/Resouce/calendar.jfif"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 877, 509);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		topPanel = new JPanel();
		contentPane.add(topPanel, BorderLayout.NORTH);
		
		holidayRequestsLabel = new JLabel("Holiday Requests");
		holidayRequestsLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		allRequestsButton = new JButton("All Requests");
		
		allEmployeesButton = new JButton("All Employees");
					
		newRequestButtonTop_1 = new JButton("New Request");
		topPanel.add(newRequestButtonTop_1);
		
		GroupLayout gl_topPanel = new GroupLayout(topPanel);
		gl_topPanel.setHorizontalGroup(
			gl_topPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_topPanel.createSequentialGroup()
					.addGap(5)
					.addComponent(holidayRequestsLabel)
					.addGap(18)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(allRequestsButton, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(allEmployeesButton, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
					.addGap(189)
					.addComponent(newRequestButtonTop_1, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGap(28))
		);
		gl_topPanel.setVerticalGroup(
			gl_topPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_topPanel.createSequentialGroup()
					.addGap(8)
					.addComponent(holidayRequestsLabel))
				.addGroup(gl_topPanel.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_topPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(allRequestsButton)
						.addComponent(allEmployeesButton)))
				.addGroup(gl_topPanel.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_topPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(newRequestButtonTop_1)))
		);
		topPanel.setLayout(gl_topPanel);
		
		bottomPanel = new JPanel();
		contentPane.add(bottomPanel, BorderLayout.CENTER);
		bottomPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		bottomPanel.add(panel, BorderLayout.NORTH);
		
		JLabel myHolidayRequestsLabel = new JLabel("My Holiday Requests");
		myHolidayRequestsLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
				
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(myHolidayRequestsLabel)
					.addGap(523)
					.addGap(86))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(myHolidayRequestsLabel)))
		);
		panel.setLayout(gl_panel);
		
		JPanel changePanel = new JPanel();
		bottomPanel.add(changePanel, BorderLayout.CENTER);
	}

	private void createEvents() 
	{
		newRequestButtonTop_1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				System.out.println("New Request Initiated");
				CalendarPanel calendarPanel = new CalendarPanel(HolidayRequestsFrame.this, bottomPanel);
				bottomPanel.removeAll();
				bottomPanel.add(calendarPanel, BorderLayout.CENTER);
				HolidayRequestsFrame.this.revalidate();
			}
		});
		
		allRequestsButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				System.out.println("View All Requests");
				AllRequestsPanel allRequestsPanel = new AllRequestsPanel(HolidayRequestsFrame.this, bottomPanel);
				bottomPanel.removeAll();
				bottomPanel.add(allRequestsPanel, BorderLayout.CENTER);
				HolidayRequestsFrame.this.revalidate();
			}
		});
		
		allEmployeesButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{	
				System.out.println("View All Employees");
				EmployeesPanel allRequestsPanel = new EmployeesPanel(HolidayRequestsFrame.this, bottomPanel);
				bottomPanel.removeAll();
				bottomPanel.add(allRequestsPanel, BorderLayout.CENTER);
				HolidayRequestsFrame.this.revalidate();
			}
		});
	}
}
