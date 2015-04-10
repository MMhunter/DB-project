package petBoarding;

import java.awt.Color;
import java.util.concurrent.Callable;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * @author Hang
 * Second-level Menu. Contains Operations based on the operation type
 */
@SuppressWarnings("serial")
public class SecondMenuPanel extends JPanel {

	public JDBCOperation operation;
	public FieldPanel fields;
	public GridBagConstraints c;
	public int fieldIndex = 0;
	
	/**
	 * @param status current operation type
	 * Construct Second-level Menu Based on operation type
	 */
	public SecondMenuPanel(MenuView status) {
		// TODO Auto-generated constructor stub
		switch(status){
			case BASIC:showBasic();
			break;
			case QUERY:showQuery();
			break;
			case INSERT:showInsert();
			break;
			case DELETE:showDelete();
			break;
			case UPDATE:showUpdate();
			break;
			default:showBasic();
		}
		
	}
	
	
	/**
	 * Welcome Information
	 */
	public void showBasic(){
		try {
			
			String workingDir = System.getProperty("user.dir");
			BufferedImage myPicture = ImageIO.read(new File(workingDir+"\\src\\petBoarding\\pet-hotel.jpg"));
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			setLayout(new FlowLayout(FlowLayout.LEADING, 25, 25));
			add(picLabel);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String welcomeMsg = "<html><body style='padding-top:40px'>"+
	"WELCOME TO PET BOARDING INFO SYSTEM<br/><br/>"+
				"THIS IS A PROJECT PROGRAM FOR TAMU CSCE 608 FALL 2014<br/>"+
				"<Br/>"+
				"CREATED BY <Br/>"+
				"<p style='text-align:right'>HANG MA</p>"+
				"<p style='text-align:right'>TIANJIAO LI</p>"+
				"<p style='text-align:right'>KENNETH REED</p>"+
				"<p style='text-align:right'>MINGHE LI</p>"+
				"</body></html>";
		JLabel welcomeLabel = new JLabel(welcomeMsg);
		welcomeLabel.setText(welcomeMsg);
		add(welcomeLabel);
	}

	public void showQuery(){
		operation = new QueryJDBC();
		JButton button;
		setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		c.insets = new Insets(10,10,10,10);
		
		button = new JButton();
		button.setText("<html>Average prices for service address having size of yard larger than 100 yards</html>");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		add(button, c);
		button.addActionListener(new ActionListener()
		{
			@Override
		    public void actionPerformed(ActionEvent e)
		    {
				((QueryJDBC) operation).averagePrice();
		    }
		});
		
		
		button = new JButton();
		button.setText("<html>How much has each owner spent on services</html>");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 0;
		add(button, c);
		button.addActionListener(new ActionListener()
		{
			@Override
		    public void actionPerformed(ActionEvent e)
		    {
				((QueryJDBC) operation).ownerSpent();
		    }
		});
		
		button = new JButton();
		button.setText("<html>Order of cities by amount of business in that city</html>");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 1;
		add(button, c);
		button.addActionListener(new ActionListener()
		{
			@Override
		    public void actionPerformed(ActionEvent e)
		    {
				((QueryJDBC) operation).amountOfBusiness();
		    }
		});
		
		button = new JButton();
		button.setText("<html>Users who own Dogs or Cats</html>");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 1;
		add(button, c);
		button.addActionListener(new ActionListener()
		{
			@Override
		    public void actionPerformed(ActionEvent e)
		    {
				((QueryJDBC) operation).userCatOrDog();
		    }
		});
		
		button = new JButton();
		button.setText("<html>Addresses having dogs there</html>");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 2;
		add(button, c);
		button.addActionListener(new ActionListener()
		{
			@Override
		    public void actionPerformed(ActionEvent e)
		    {
				((QueryJDBC) operation).addressDog();
		    }
		});
		
		button = new JButton();
		button.setText("<html>Number of pets in each city</html>");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 2;
		add(button, c);
		button.addActionListener(new ActionListener()
		{
			@Override
		    public void actionPerformed(ActionEvent e)
		    {
				((QueryJDBC) operation).petPerCity();
		    }
		});
		
		button = new JButton();
		button.setText("<html>Owners who is active in pet boarding business.(Having at least one agreement)</html>");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 3;
		add(button, c);
		button.addActionListener(new ActionListener()
		{
			@Override
		    public void actionPerformed(ActionEvent e)
		    {
				((QueryJDBC) operation).activeOwners();
		    }
		});
		
		button = new JButton();
		button.setText("<html>Users that own birds</html>");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 3;
		add(button, c);
		button.addActionListener(new ActionListener()
		{
			@Override
		    public void actionPerformed(ActionEvent e)
		    {
				((QueryJDBC) operation).userBirds();
		    }
		});
		
		button = new JButton();
		button.setText("<html>Pairs of users that having business relationship.</html>");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 4;
		add(button, c);
		button.addActionListener(new ActionListener()
		{
			@Override
		    public void actionPerformed(ActionEvent e)
		    {
				((QueryJDBC) operation).bussinessRelation();
		    }
		});
		
		button = new JButton();
		button.setText("<html>Sitter addresses provide services for dogs but not cats</html>");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 4;
		add(button, c);
		button.addActionListener(new ActionListener()
		{
			@Override
		    public void actionPerformed(ActionEvent e)
		    {
				((QueryJDBC) operation).dogsNoCats();
		    }
		});
		
		button = new JButton("EXECUTE SQL QUERY BELOW:");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 2;
		c.gridy = 0;
		add(button, c);
		
		final JTextArea SqlArea = new JTextArea();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weighty = 2.5;
		c.gridx = 2;
		c.gridy = 1;
		c.gridheight = 5;
		SqlArea.setPreferredSize(new Dimension(200,500));
		JScrollPane ScrollSql = new JScrollPane(SqlArea);
		ScrollSql.setPreferredSize(new Dimension(200,200));
		ScrollSql.setMinimumSize(new Dimension(200,300));
		add(ScrollSql, c);
		button.addActionListener(new ActionListener()
		{
			@Override
		    public void actionPerformed(ActionEvent e)
		    {
				((QueryJDBC) operation).executeQuery(SqlArea.getText());
		    }
		});
	}
	
	/**
	 * Insertion Menu
	 */
	public void showInsert(){
		operation = new InsertJDBC();
		JButton button;
		setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		c.insets = new Insets(10,10,10,10);
		
		button = new JButton("Insert User");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		add(button, c);
		button.addActionListener(new ActionListener()
		{
			@Override
		    public void actionPerformed(ActionEvent e)
		    {
				String[] fls = {"Email","Name","Phone"};
				fields = new FieldPanel("ADD USER",fls,new Callable<Void>(){
					@Override
					public Void call() throws Exception {
						((InsertJDBC) operation).addUser(fields.get("Email"), fields.get("Name"), fields.get("Phone"));
						return null;
					}
				});
				redrawField();
		    }
		});
		
		button = new JButton("Insert Owner");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 1;
		add(button, c);
		button.addActionListener(new ActionListener()
		{
			@Override
		    public void actionPerformed(ActionEvent e)
		    {
				String[] fls = {"Email","Billing Address","City","State","Zip Code"};
				fields = new FieldPanel("ADD OWNER",fls,new Callable<Void>(){
					@Override
					public Void call() throws Exception {
						((InsertJDBC) operation).addOwner(fields.get("Email"), fields.get("Billing Address"), fields.get("City"),fields.get("State"),fields.get("Zip Code"));
						return null;
					}
				});
				redrawField();
		    }
		});
		
		
		button = new JButton("Insert Pet");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 2;
		add(button, c);
		button.addActionListener(new ActionListener()
		{
			@Override
		    public void actionPerformed(ActionEvent e)
		    {
				String[] fls = {"Pet Name","Pet ID","Owner's Email","Species","Breed","Date of Birth(yyyy-mm-dd)","Gender(F/M)"};
				fields = new FieldPanel("ADD PET",fls,new Callable<Void>(){
					@Override
					public Void call() throws Exception {
						((InsertJDBC) operation).addPets(fields.get("Pet Name"), fields.get("Pet ID"), fields.get("Owner's Email"),fields.get("Species"),fields.get("Breed"),fields.get("Date of Birth(yyyy-mm-dd)"),fields.get("Gender(F/M)"));
						return null;
					}
				});
				redrawField();
		    }
		});
		
		button = new JButton("Insert Sitter");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 3;
		add(button, c);
		button.addActionListener(new ActionListener()
		{
			@Override
		    public void actionPerformed(ActionEvent e)
		    {
				String[] fls = {"Email","Contact Hours","Slogan","AboutUs"};
				fields = new FieldPanel("ADD SITTER",fls,new Callable<Void>(){
					@Override
					public Void call() throws Exception {
						((InsertJDBC) operation).addSitter(fields.get("Email"), fields.get("Contact Hours"), fields.get("Slogan"),fields.get("AboutUs"));
						return null;
					}
				});
				redrawField();
		    }
		});
		
		button = new JButton("Insert Service");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 4;
		add(button, c);
		
		button.addActionListener(new ActionListener()
		{
			@Override
		    public void actionPerformed(ActionEvent e)
		    {
				String[] fls = {"Address","Sitter Email","Size of House(yards)","Size of Yard(yards)","Capacity","Price of Dog Service","Price of Cat Service","Price of Bird Service","Price of Reptile Service","Service Phone"};
				fields = new FieldPanel("ADD SERVICE",fls,new Callable<Void>(){
					@Override
					public Void call() throws Exception {
						((InsertJDBC) operation).addServices(fields.get("Address"), fields.get("Sitter Email"), fields.get("Size of House(yards)"),fields.get("Size of Yard(yards)"),fields.get("Capacity"),fields.get("Price of Dog Service"),fields.get("Price of Cat Service"),fields.get("Price of Bird Service"),fields.get("Price of Reptile Service"),fields.get("Service Phone"));
						return null;
					}
				});
				redrawField();
		    }
		});
		
		button = new JButton("Insert Agreement");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 5;
		add(button, c);
		button.addActionListener(new ActionListener()
		{
			@Override
		    public void actionPerformed(ActionEvent e)
		    {
				String[] fls = {"Invoice","Pet ID","Service Address","Start date(yyyy-mm-dd)","End date(yyyy-mm-dd)","Total"};
				fields = new FieldPanel("ADD AGREEMENT",fls,new Callable<Void>(){
					@Override
					public Void call() throws Exception {
						((InsertJDBC) operation).addAgreements(fields.get("Invoice"), fields.get("Pet ID"), fields.get("Service Address"),fields.get("Start date(yyyy-mm-dd)"),fields.get("End date(yyyy-mm-dd)"),fields.get("Total"));
						return null;
					}
				});
				redrawField();
		    }
		});
		
		
		fields = new FieldPanel();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 3;
		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 6;
		add(fields, c);
		fieldIndex = 6;
	}

	/**
	 * Deletion Menu
	 */
	public void showDelete(){
		operation = new DeleteJDBC();
		JButton button;
		setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		c.insets = new Insets(10,10,10,10);
		
		button = new JButton("Delete User");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		add(button, c);
		button.addActionListener(new ActionListener()
		{
			@Override
		    public void actionPerformed(ActionEvent e)
		    {
				String[] fls = {"Email"};
				fields = new FieldPanel("DELETE USER",fls,new Callable<Void>(){
					@Override
					public Void call() throws Exception {
						((DeleteJDBC) operation).deleteUser(fields.get("Email"));
						return null;
					}
				});
				redrawField();
		    }
		});
		
		button = new JButton("Delete Owner");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 1;
		add(button, c);
		button.addActionListener(new ActionListener()
		{
			@Override
		    public void actionPerformed(ActionEvent e)
		    {
				String[] fls = {"Email"};
				fields = new FieldPanel("DELETE OWNER",fls,new Callable<Void>(){
					@Override
					public Void call() throws Exception {
						((DeleteJDBC) operation).deleteOwner(fields.get("Email"));
						return null;
					}
				});
				redrawField();
		    }
		});
		
		button = new JButton("Delete Sitter");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 2;
		add(button, c);
		button.addActionListener(new ActionListener()
		{
			@Override
		    public void actionPerformed(ActionEvent e)
		    {
				String[] fls = {"Email"};
				fields = new FieldPanel("DELETE SITTER",fls,new Callable<Void>(){
					@Override
					public Void call() throws Exception {
						((DeleteJDBC) operation).deleteSitter(fields.get("Email"));
						return null;
					}
				});
				redrawField();
		    }
		});
		
		button = new JButton("Delete Pet");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 3;
		add(button, c);
		button.addActionListener(new ActionListener()
		{
			@Override
		    public void actionPerformed(ActionEvent e)
		    {
				String[] fls = {"Pet ID"};
				fields = new FieldPanel("DELETE PET",fls,new Callable<Void>(){
					@Override
					public Void call() throws Exception {
						((DeleteJDBC) operation).deletePet(fields.get("Pet ID"));
						return null;
					}
				});
				redrawField();
		    }
		});
		
		button = new JButton("Delete Service");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 4;
		add(button, c);
		button.addActionListener(new ActionListener()
		{
			@Override
		    public void actionPerformed(ActionEvent e)
		    {
				String[] fls = {"Service Address"};
				fields = new FieldPanel("DELETE SERVICE",fls,new Callable<Void>(){
					@Override
					public Void call() throws Exception {
						((DeleteJDBC) operation).deleteService(fields.get("Service Address"));
						return null;
					}
				});
				redrawField();
		    }
		});
		
		button = new JButton("Delete Agreement");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 5;
		add(button, c);
		button.addActionListener(new ActionListener()
		{
			@Override
		    public void actionPerformed(ActionEvent e)
		    {
				String[] fls = {"Invoice Number"};
				fields = new FieldPanel("DELETE AGREEMENT",fls,new Callable<Void>(){
					@Override
					public Void call() throws Exception {
						((DeleteJDBC) operation).deleteAgreement(fields.get("Invoice Number"));
						return null;
					}
				});
				redrawField();
		    }
		});
		
		fields = new FieldPanel();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 3;
		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 6;
		add(fields, c);
		fieldIndex = 6;
	}
	
	/**
	 * Update Menu
	 */
	public void showUpdate(){
		operation = new UpdateJDBC();
		JButton button;
		setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		c.insets = new Insets(10,10,10,10);
		
		button = new JButton("Update User Info");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		add(button, c);
		button.addActionListener(new ActionListener()
		{
			@Override
		    public void actionPerformed(ActionEvent e)
		    {
				String[] fls = {"Name","Phone","Where Email ="};
				fields = new FieldPanel("UPDATE USER",fls,new Callable<Void>(){
					@Override
					public Void call() throws Exception {
						((UpdateJDBC) operation).UpdateUser(fields.get("Name"),fields.get("Phone"),fields.get("Where Email ="));
						return null;
					}
				});
				redrawField();
		    }
		});
		
		button = new JButton("Update Owner Info");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 1;
		add(button, c);
		button.addActionListener(new ActionListener()
		{
			@Override
		    public void actionPerformed(ActionEvent e)
		    {
				String[] fls = {"Billing Address","City","State","Zip Code","Where Email ="};
				fields = new FieldPanel("UPDATE OWNER",fls,new Callable<Void>(){
					@Override
					public Void call() throws Exception {
						((UpdateJDBC) operation).UpdateOwner(fields.get("Billing Address"),fields.get("City"),fields.get("State"),fields.get("Zip Code"),fields.get("Where Email ="));
						return null;
					}
				});
				redrawField();
		    }
		});
		
		button = new JButton();
		button.setText("<html>For Sitters having agreements  completed more than<br /> a certain number, Change their Slogan  to 'Hot Sitter'</html>");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 2;
		add(button, c);
		button.addActionListener(new ActionListener()
		{
			@Override
		    public void actionPerformed(ActionEvent e)
		    {
				String[] fls = {"Hot Threshold"};
				fields = new FieldPanel("For Sitters having agreements  completed more than<br /> a certain number, Change their Slogan  to 'Hot Sitter'",fls,new Callable<Void>(){
					@Override
					public Void call() throws Exception {
						((UpdateJDBC) operation).UpdateSitter(fields.get("Hot Threshold"));
						return null;
					}
				});
				redrawField();
		    }
		});
		
		button = new JButton();
		button.setText("<html>Update Services Where the service Price<br /> for cats is higher than the maximum price<br /> for dogs. Set them to that maximum</html>");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 3;
		add(button, c);
		button.addActionListener(new ActionListener()
		{
			@Override
		    public void actionPerformed(ActionEvent e)
		    {
				String[] fls = {};
				fields = new FieldPanel("Update Services Where the service Price<br /> for cats is higher than the maximum price<br /> for dogs. Set them to that maximum",fls,new Callable<Void>(){
					@Override
					
					public Void call() throws Exception {
						((UpdateJDBC) operation).UpdateService();
						return null;
					}
				});
				redrawField();
		    }
		});
		
		fields = new FieldPanel();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 3;
		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 6;
		add(fields, c);
		fieldIndex = 4;
	}
	
	/**
	 * Recreate input field
	 */
	protected void redrawField() {
		// TODO Auto-generated method stub
		GridBagLayout layout = (GridBagLayout)getLayout();
		Component toRemove = this.getComponent(fieldIndex);
		c = layout.getConstraints(toRemove);
		layout.removeLayoutComponent(toRemove);
		remove(toRemove);	
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 3;
		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 6;
		add(fields, c);
		revalidate();
		repaint();
	}
}
