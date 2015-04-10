package petBoarding;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Minghe, Tianjiao, Kenneth
 * Query Operations
 */
public class QueryJDBC extends JDBCOperation {
	
	//#Average prices for service address having size large than 100 yards
	public void averagePrice(){
		String sql= "SELECT AVG(PriceDog) as dog_avg, AVG(PriceCat) as cat_avg, AVG(PriceBird) as bird_avg, AVG(PriceReptile) as reptile_avg FROM Services WHERE SizeofYard >=100;";
		List<MysqlProjection> projections = new ArrayList<MysqlProjection>();
		projections.add(new MysqlProjection("dog_avg","float"));
		projections.add(new MysqlProjection("cat_avg","float"));
		projections.add(new MysqlProjection("bird_avg","float"));
		projections.add(new MysqlProjection("reptile_avg","float"));
		executeQuery(sql,projections);
	}
	
	//#How much has each owner spent on services
	public void ownerSpent(){
		
		String sql= "SELECT u.Email as \"Owner's Email\", u.UserName as \"Owner's name\", sum(a.total) as 'Total Spent on Services' FROM User u, Owner o, Pets p, Agreements a WHERE u.Email = o.Email AND	o.Email = p.OwnerEmail AND	p.ID = a.PetID GROUP BY u.Email, u.UserName;";
		
		List<MysqlProjection> projections = new ArrayList<MysqlProjection>();
		
		projections.add(new MysqlProjection("\"Owner's Email\"","string"));
		projections.add(new MysqlProjection("\"Owner's name\"","string"));
		projections.add(new MysqlProjection("Total Spent on Services","float"));
		
		executeQuery(sql,projections);
	}
	
	//#How much has each owner spent on services
	public void amountOfBusiness(){
		
		String sql = "SELECT City, BusinessAmt as 'Amount of Business' " +
                             "FROM (SELECT City, SUM(Total) as BusinessAmt " +
                                    "FROM (SELECT City,Total " +
                                          "FROM Owner, Pets, Agreements " +
                                          "WHERE Pets.ID = Agreements.PetID AND Pets.OwnerEmail=Owner.Email) a " +
                                    "GROUP BY City) b " +
                             "ORDER BY b.BusinessAmt DESC;"; 
		
		
		List<MysqlProjection> projections = new ArrayList<MysqlProjection>();
		
		
		projections.add(new MysqlProjection("City", "string"));
                projections.add(new MysqlProjection("Amount of Business","float"));
		
		
		executeQuery(sql,projections);
	}
	
	//#What users own Dogs or Cats
	public void userCatOrDog(){
		
		String sql = "SELECT OwnerEmail as 'User' " +
                             "FROM Pets "+
                             "WHERE Species = 'Dog' OR Species = 'Cat'"; 
                
		
		List<MysqlProjection> projections = new ArrayList<MysqlProjection>();
		
		
		projections.add(new MysqlProjection("User", "string"));
		
		
		executeQuery(sql,projections);
	}
	
	//#What addresses have dogs
	public void addressDog(){
		
		String sql = "(SELECT Address " +
                              "FROM Services Where PriceDog > 0) " +
                              "UNION " +
                             "(SELECT CONCAT(BillingAddress,', ', City,', ', State) as 'Address'" +
                              "FROM Owner, Pets " +
                              "WHERE (Owner.Email = Pets.OwnerEmail) AND (Species = 'Dog'));"; //fill out the sql in between the Quotes. Notice there shall not be any double quotes in the sql(see function owenrSpent);
		
		
		List<MysqlProjection> projections = new ArrayList<MysqlProjection>();
		
		
		projections.add(new MysqlProjection("Address", "string"));
		
		
		executeQuery(sql,projections);
	}

	
	//#Pet count per city
	public void petPerCity(){
		
		String sql = "SELECT o.city as 'City', count(p.ID) as 'Number of Pets' FROM Owner o, Pets p WHERE 	o.Email = p.OwnerEmail GROUP BY o.city;"; //fill out the sql in between the Quotes. Notice there shall not be any double quotes in the sql(see function owenrSpent);
		
		
		List<MysqlProjection> projections = new ArrayList<MysqlProjection>();
		
		
		projections.add(new MysqlProjection("City","string"));
		projections.add(new MysqlProjection("Number of Pets","int"));		
		
		executeQuery(sql,projections);
	}
	
	//#Active owners
	public void activeOwners(){
		
		String sql = "SELECT DISTINCT UserName as 'username' FROM User a,Pets b,Agreements c WHERE b.ID=c.PetID AND b.OwnerEmail=a.Email;"; //fill out the sql in between the Quotes. Notice there shall not be any double quotes in the sql(see function owenrSpent);
		
		
		List<MysqlProjection> projections = new ArrayList<MysqlProjection>();
		
		
		projections.add(new MysqlProjection("username","string"));
		
		
		executeQuery(sql,projections);
	}
	
	//#Business Relationship (table with owners username and Sitter username)
	
	public void bussinessRelation(){
		
		String sql = "SELECT u1.UserName as 'Pet Owner',u2.UserName as 'Pet Sitter' FROM Agreements JOIN  pets on pets.ID = Agreements.petID JOIN  User u1 on pets.OwnerEmail = u1.Email JOIN Services on Services.Address = Agreements.ServiceAddress JOIN User u2 on Services.SitterEmail = u2.Email;"; //fill out the sql in between the Quotes. Notice there shall not be any double quotes in the sql(see function owenrSpent);
		
		
		List<MysqlProjection> projections = new ArrayList<MysqlProjection>();
		
		
		projections.add(new MysqlProjection("Pet Owner","string"));
		projections.add(new MysqlProjection("Pet Sitter","string"));	
		
		executeQuery(sql,projections);
	}
	
	
	//#What Sitter addresses provide services for dogs but not cats
	public void dogsNoCats(){
		
		String sql = "SELECT Address as \"Sitter's Address\" FROM Services WHERE PriceDog > 0 AND PriceCat = 0;"; 
		
		List<MysqlProjection> projections = new ArrayList<MysqlProjection>();
		
		projections.add(new MysqlProjection("Sitter's Address","string"));
		
		executeQuery(sql,projections);
	}
	
	//#Users that own birds
	public void userBirds(){
		
		String sql = "SELECT Distinct u.Email as \"User's Email\", u.UserName as \"User's Name\" FROM	User u, Owner o, Pets p WHERE u.Email = o.Email AND	o.Email = p.OwnerEmail AND	p.Species = 'Bird';"; 
		
		List<MysqlProjection> projections = new ArrayList<MysqlProjection>();
		
		projections.add(new MysqlProjection("User's Email","string"));
		projections.add(new MysqlProjection("User's Name","string"));
		
		executeQuery(sql,projections);
	}
	
}
