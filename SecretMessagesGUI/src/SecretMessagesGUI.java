import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.Font;

public class SecretMessagesGUI extends JPanel {
	private JTextField txtKey;
	private JTextArea txtOut;
	private JTextArea txtIn;
	private JSlider slider;
	
	public String encode(String message, int k) { //for java we have to set the data types of the arguments
		
		String out = "";
		char key = (char) k;
		
		for(int x = 0; x < message.length(); x++) {
			char input = message.charAt(x); //store the char to a variable
			
			//setting the rule for capital letters
			if(input >= 'A' && input <= 'Z') { //guarantee only letters will be changed
				input += key;
				if(input > 'Z')
					input -= 26;
				if(input < 'A')
					input += 26;
			}
			
			//setting the rule for lower case
			if(input >= 'a' && input <= 'z') {
				input += key;
				if(input > 'z')
					input -= 26;
				if(input < 'a')
					input += 26;
			}
			
			if(input >= '0' && input <= '9') {
				input += (k % 10);
				if(input > '9')
					input -= 10;
				if(input < '0')
					input += 10;
			}
			
			out += input;
		}
		
		return out;
		
	}
	
	public SecretMessagesGUI() {
		setLayout(null);
		
		txtIn = new JTextArea();
		txtIn.setFont(new Font("Courier New", Font.BOLD, 16));
		txtIn.setRows(3);
		txtIn.setColumns(10);
		txtIn.setBounds(10, 11, 430, 101);
		add(txtIn);
		
		JLabel lblKey = new JLabel("Key:");
		lblKey.setHorizontalAlignment(SwingConstants.RIGHT);
		lblKey.setBounds(174, 138, 49, 14);
		add(lblKey);
		
		txtKey = new JTextField();
		txtKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtKey.setText("0");
		txtKey.setBounds(227, 135, 30, 20);
		add(txtKey);
		txtKey.setColumns(3);
		
		JButton btnEncodeDecode = new JButton("ENCODE/DECODE");
		btnEncodeDecode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//get the message from txtIn
				String message = txtIn.getText();
				
				//get the key amount from the txtKey
				int key = Integer.parseInt(txtKey.getText());
				
				//encode the message with the key
				String output = encode(message, key);
				
				//show the message in the txtOut
				txtOut.setText(output);
				
				//set the same value from the txtKey field
				slider.setValue(Integer.parseInt(txtKey.getText()));
			}
		});
		btnEncodeDecode.setBounds(273, 134, 141, 23);
		add(btnEncodeDecode);
		
		txtOut = new JTextArea();
		txtOut.setFont(new Font("Courier New", Font.BOLD, 16));
		txtOut.setRows(3);
		txtOut.setColumns(10);
		txtOut.setBounds(10, 188, 430, 101);
		add(txtOut);
		setPreferredSize(new Dimension(450, 320));
		
		slider = new JSlider();
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				//change the key text field to match the slider
				txtKey.setText("" + slider.getValue()); //sets the value of the slider to the txtKey text field
				
				//get the message from txtIn
				String message = txtIn.getText();
				
				//get the key amount from the txtKey
				int key = Integer.parseInt(txtKey.getText());
				
				//encode the message with the key
				String output = encode(message, key);
				
				//show the message in the txtOut
				txtOut.setText(output);
				
			}
		});
		slider.setPaintLabels(true);
		slider.setMajorTickSpacing(13);
		slider.setMinorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setSnapToTicks(true);
		slider.setValue(0);
		slider.setMinimum(-13);
		slider.setMaximum(13);
		slider.setBounds(10, 132, 163, 45);
		add(slider);
	}

	public static void main(String[] args) {
		// Setup a window JFrame for the app
		JFrame frame = new JFrame ("Secret Message App"); //create a frame for the instance and gives it a title
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//add the encoder panel to the frame
		frame.getContentPane().add(new SecretMessagesGUI()); //the encoder panel is defined in the design function SecretMessagesGUI()
		
		//prepare and show the frame
		frame.pack();
		frame.setVisible(true);
		
	}
}
