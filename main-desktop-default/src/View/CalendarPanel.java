package View;

import javax.swing.JPanel;
import com.toedter.calendar.JCalendar;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import com.toedter.calendar.JDateChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.JTextField;

public class CalendarPanel extends JPanel 
{
private JFrame parentFrame;
private JPanel parentPanel; 
		
private JDateChooser startDateChooser;
private JDateChooser endDateChooser;

private JLabel startDateLabel;
private JLabel endDateLabel;

private JButton submitRequestButton;

	public CalendarPanel(JFrame parentFrame, JPanel parentPanel) 
	{
		this.parentFrame = parentFrame;
		this.parentPanel = parentPanel;
		
		initialiseComponents();
		createEvents();
	}

	private void initialiseComponents() 
	{
		startDateChooser = new JDateChooser();
		
		endDateChooser = new JDateChooser();
		
		startDateLabel = new JLabel("Start Date");
		startDateLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		endDateLabel = new JLabel("End Date");
		endDateLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		submitRequestButton = new JButton("Submit Request");
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(751, Short.MAX_VALUE)
					.addComponent(submitRequestButton)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(startDateLabel, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addComponent(endDateLabel, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
							.addComponent(startDateChooser, GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
							.addComponent(endDateChooser, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addGap(624))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(113, Short.MAX_VALUE)
					.addComponent(startDateLabel)
					.addGap(18)
					.addComponent(startDateChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(31)
					.addComponent(endDateLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(endDateChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(19)
					.addComponent(submitRequestButton)
					.addContainerGap())
		);
		setLayout(groupLayout);
		
	}

	private void createEvents() 
	{
		submitRequestButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{				
				try
				{
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
					Date startDate = startDateChooser.getDate();
					Date endDate = endDateChooser.getDate();
					
					String strStartDate = dateFormat.format(startDate);
					String strEndDate = dateFormat.format(endDate); 
					
					DefaultHttpClient httpClient = new DefaultHttpClient();
					HttpPost  postRequest  = new HttpPost (
							"http://localhost:8080/holidaysystem/api/holiday-request");
					
					String template = new String(
							"{\"employeeId\":\"e51b3404-810f-49ba-a725-2f0a1e7d1180\", \"status\":\"PENDING\", \"startDate\":\"%s\", \"endDate\":\"%s\" }");
					
					String result = String.format(template, strStartDate, strEndDate);

					StringEntity input = new StringEntity(result);
					input.setContentType("application/json");
					postRequest.setEntity(input);
					
					HttpResponse response = httpClient.execute(postRequest);
					if (response.getStatusLine().getStatusCode() == 200)
					{
						JOptionPane.showMessageDialog(null, "The Holiday Request has successfully submitted", "Holiday Request", JOptionPane.INFORMATION_MESSAGE);
						System.out.println("Holiday request dates are good -> submit");
						AllRequestsPanel allRequestsPanel = new AllRequestsPanel(parentFrame, parentPanel);
						parentPanel.removeAll();
						parentPanel.add(allRequestsPanel, BorderLayout.CENTER);
						parentFrame.revalidate();
					}
					else {
						JOptionPane.showMessageDialog(null,"Choose appropriate dates!","Error",JOptionPane.PLAIN_MESSAGE);
					}
				}
				catch (NullPointerException e1)
				{
					JOptionPane.showMessageDialog(null, "The Holiday Request Start/End Date CANNOT be empty", "Error", JOptionPane.ERROR_MESSAGE);
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				} catch (ClientProtocolException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
	}
}
