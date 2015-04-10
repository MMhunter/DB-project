package petBoarding;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * @author Hang
 * Panel Contains Several text input fields with their labels
 * Having a callback function when submitted;
 * Just like "Form" in HTML
 */
@SuppressWarnings("serial")
public class FieldPanel extends JPanel {
	
	//Map to store field name and its corresponding textfield
	Map<String,TextField> tfs = new HashMap<String,TextField>();
	
	//Submit Callback
	Callable<Void> SubmitCallback; 
	
	/**
	 * @param title Form Title
	 * @param fieldNames The names of input fields
	 * @param callback callback function when Submitted
	 */
	FieldPanel(String title,String[] fieldNames,Callable<Void> callback){
		this.SubmitCallback = callback;
		TextField tf;
		JLabel lb;
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5,10,5,10);
		
		//Adding Title
		lb = new JLabel();
		lb.setText("<html>"+title+"</html>");
		c.weightx = 1;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		add(lb,c);
		c.gridwidth = 1;
		int count = 1;
		
		//Loop fields' names and construct input textfields;
		for(String str : fieldNames){
			lb = new JLabel(str);
			tf = new TextField(20);
			c.weightx = 0.5;
			c.gridx = 0;
			c.gridy = count;
			add(lb,c);
			c.gridx = 1;
			c.gridy = count;
			add(tf,c);
			tfs.put(str, tf);
			count++;
		}
		JButton submitBtn = new JButton("Submit Modification");
		
		//callback on clicking submit button
		submitBtn.addActionListener(new ActionListener()
		{
			@Override
		    public void actionPerformed(ActionEvent e)
		    {
		        try {
					SubmitCallback.call();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    }
		});
		c.gridx = 1;
		c.gridy = count;
		add(submitBtn,c);
	}
	
	//Displayed when no operation is seleted;
	FieldPanel(){
		JLabel lb = new JLabel();
		lb.setText("<html>Choose a Table To Modify</html>");
		add(lb);
	}
	
	//Get field text values by field Name
	public String get(String string) {
		// TODO Auto-generated method stub
		return this.tfs.get(string).getText();
	}
}
