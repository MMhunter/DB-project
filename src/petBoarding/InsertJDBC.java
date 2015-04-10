package petBoarding;


/**
 * @author Tianjiao
 * Insertion SQLs.
 */
public class InsertJDBC extends JDBCOperation {
	
	//#add User
	public void addUser(String UserName,String UserPhone,String Email){
		String sql= "Insert Into User(UserName,UserPhone,Email) VALUES ('"+UserName+"','"+UserPhone+"','"+Email+"');";
		executeQuery(sql);
	}
	
	//#add Owner
	public void addOwner(String Email,String BillingAddress,String City,String STATE,String Zipcode){
		String sql= "Insert Into Owner(Email,BillingAddress,City,STATE,Zipcode) VALUES ('"+Email+"','"+BillingAddress+"','"+City+"','"+STATE+"',"+Zipcode+");";
		executeQuery(sql);
	}
	//add Pet
	public void addPets(String PetName,String ID,String OwnerEmail,String Species,String Breed,String DOB,String Gender){
		String sql= "Insert Into Pets(PetName,ID,OwnerEmail,Species,Breed,DOB,Gender) VALUES ('"+PetName+"',"+ID+",'"+OwnerEmail+"','"+Species+"','"+Breed+"','"+DOB+"','"+Gender+"');";
		executeQuery(sql);
	}
	// add Sitter
	public void addSitter(String Email,String ContactHours,String Slogan,String AboutUs){
		String sql= "Insert Into Sitter(Email,ContactHours,Slogan,AboutUs) VALUES ('"+Email+"','"+ContactHours+"','"+Slogan+"','"+AboutUs+"');";
		executeQuery(sql);
	}
	
	// add Service
	public void addServices(String Address,String SitterEmail,String SizeofHouse,String SIzeofYard,String Capacity,String PriceDog,String PriceCat,String PriceBird,String PriceReptile,String SCPhone){
		String sql= "Insert Into Services(Address,SitterEmail,SizeofHouse,SIzeofYard,Capacity,PriceDog,PriceCat,PriceBird,PriceReptile,SCPhone) VALUES ('"+Address+"','"+SitterEmail+"',"+SizeofHouse+","+SIzeofYard+",'"+Capacity+"',"+PriceDog+","+PriceCat+","+PriceBird+","+PriceReptile+",'"+SCPhone+"');";
		executeQuery(sql);
	}
	
	//#Add agreement
	public void addAgreements(String Invoice,String PetID,String ServiceAddress,String Startdate,String Enddate,String Total){
		String sql= "Insert Into Agreements(Invoice,PetID,ServiceAddress,Startdate,Enddate,Total) VALUES ("+Invoice+","+PetID+",'"+ServiceAddress+"','"+Startdate+"','"+Enddate+"',"+Total+");";
		executeQuery(sql);
	}
}
