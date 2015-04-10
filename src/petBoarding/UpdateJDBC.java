package petBoarding;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;


/**
 * @author Minghe
 * Update SQLs
 */
public class UpdateJDBC extends JDBCOperation {
	
	//#Update User
	public void UpdateUser(String UserName,String UserPhone,String whereEmail){
		String sql= "UPDATE User SET";
		List<String> setList = new ArrayList<String>();
		
		/*-----------add Strings to setList START----------*/
		if(UserName != null || !UserName.equals("")) 
		{
			setList.add("UserName='"+UserName+"'");
		}
		if(UserPhone != null || !UserPhone.equals(""))
		{
			setList.add("UserPhone='"+UserPhone+"'");
		}
		/*-----------add Strings to setList END----------*/
		
		sql+= serializeSetList(setList);
		sql+= "WHERE Email = '"+whereEmail+"';";
		executeQuery(sql);
	}
	
	//#Update Owner
	public void UpdateOwner(String BillingAddress,String City,String State, String Zipcode, String whereEmail){
		
		String sql= "UPDATE Owner SET";
		List<String> setList = new ArrayList<String>();//Initialize the setList
		/*-----------add Strings to setList START----------*/
		if(BillingAddress != null || !BillingAddress.equals(""))
		{
			setList.add("BillingAddress='"+BillingAddress+"'");
		}
		if(City != null || !City.equals(""))
		{
			setList.add("City='"+City+"'");
		}
		if(State != null || !State.equals(""))
		{
			setList.add("State='"+State+"'");
		}
		if(Zipcode != null || !Zipcode.equals(""))
		{
			setList.add("Zipcode="+Zipcode);
		}
		/*-----------add Strings to setList END---------*/
		sql+= serializeSetList(setList);
		
		sql+= "WHERE Email = '"+whereEmail+"';"; 
		
		executeQuery(sql);
	}
	//#Update Pets
	public void UpdatePet(String PetName,String whereId,String OwnerEmail,String Species, String Breed,String DOB,String Gender){
		String sql= "UPDATE Pets SET";//DONT CHANGE THIS
		List<String> setList = new ArrayList<String>();//Initialize the setList
		/*-----------add Strings to setList START----------*/
		if(PetName != null || !PetName.equals(""))
		{
			setList.add("PetName='"+PetName+"'");
		}
		if(OwnerEmail != null || !OwnerEmail.equals(""))
		{
			setList.add("OwnerEmail='"+OwnerEmail+"'");
		}
		if(Species != null || !Species.equals(""))
		{
			setList.add("Species='"+Species+"'");
		}
		if(Breed != null || !Breed.equals(""))
		{
			setList.add("Breed='"+Breed+"'");
		}
		if(DOB != null || !DOB.equals(""))
		{
			setList.add("DOB='"+DOB+"'");
		}
		if(Gender != null || !Gender.equals(""))
		{
			setList.add("Gender='"+Gender+"'");
		}
		/*-----------add Strings to setList END---------*/
		sql+= serializeSetList(setList);
		
		sql+= "WHERE ID="+whereId+";"; 
		
		executeQuery(sql);
	}
	//For Sitters having agreements  completed more than a certain number, Change their Slogan  to 'Hot Sitter'
	public void UpdateSitter(String whereAgreementNumber){
		if(whereAgreementNumber != null || !whereAgreementNumber.equals(""))
		{
			String sql= "UPDATE sitter s1 INNER JOIN (SELECT count(a.Invoice) as agreementnumber,s2.Email FROM sitter s2,services se,agreements a WHERE s2.Email=se.SitterEmail AND se.Address=a.ServiceAddress GROUP BY s2.Email)n On s1.Email = n.Email  SET s1.Slogan='Hot Sitter!' WHERE n.agreementnumber>"+whereAgreementNumber+";";		
		
			executeQuery(sql);
		}
	}
	
	//#Update Services Where the service Price for cats is higher than the maximum price for dogs. Set them to that maximum
	public void UpdateService(){
		String sql= "UPDATE Services s INNER JOIN ( SELECT max(PriceDog) maxDog FROM Services ) se SET s.PriceCat  = se.maxDog WHERE  s.PriceCat  > se.maxDog;";		
		executeQuery(sql);
	}
	
	//#Update Agreements
	public void UpdateAgreement(String whereInvoice,String PetID,String ServiceAddress,String Startdate,String Enddate,String Total){
		String sql= "UPDATE Agreements SET";
		List<String> setList = new ArrayList<String>();//Initialize the setList
		/*-----------add Strings to setList START----------*/
		if(PetID != null || !PetID.equals(""))
		{
			setList.add("PetID="+PetID);
		}
		if(ServiceAddress != null || !ServiceAddress.equals(""))
		{
			setList.add("ServiceAddress='"+ServiceAddress+"'");
		}
		if(Startdate != null || !Startdate.equals(""))
		{
			setList.add("Startdate='"+Startdate+"'");
		}
		if(Enddate != null || !Enddate.equals(""))
		{
			setList.add("Enddate='"+Enddate+"'");
		}
		if(Total != null || !Total.equals(""))
		{
			setList.add("Total="+Total);
		}
		/*-----------add Strings to setList END---------*/
		sql+= serializeSetList(setList);
		
		sql+= "WHERE Invoice="+whereInvoice+";"; 
		
		executeQuery(sql);
	}
	
	//Convert Set list To string
	public String serializeSetList(List<String> setList){
		String res = " ";
		ListIterator<String> litr = setList.listIterator();
		while(litr.hasNext()){
			res += litr.next();
			if(litr.hasNext()) res+=",";
		}
		res += " ";
		return res;
	}
}
