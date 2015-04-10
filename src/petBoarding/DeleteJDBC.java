package petBoarding;


/**
 * @author Kenneth
 * Delete SQLs. Keeping foreign key constrains in mind, we delete related tables first;
 */
public class DeleteJDBC extends JDBCOperation {
	
	//#add User
	//REFER TO THIS IF YOU DONT KNOW WHAT TO DO
	public void deleteUser(String email){
		deleteOwner(email);
		deleteSitter(email);
		String sql= "DELETE FROM User Where Email = '"+email+"';";
		executeQuery(sql);
                System.out.println("User "+email+" has successfully been deleted");
	}
	
	//#delete Owner
	public void deleteOwner(String email){
		String sql= "DELETE FROM Agreements Where PetID in (SELECT ID FROM Pets Where OwnerEmail='"+email+"')";
		executeQuery(sql);
		sql= "DELETE FROM Pets Where OwnerEmail='"+email+"'";
		executeQuery(sql);
		sql= "DELETE FROM Owner Where Email = '"+email+"';";
		executeQuery(sql);
                System.out.println("Owner "+email+" has successfully been deleted");
	}
	//delete Pet
	public void deletePet(String petID){
		String sql= "DELETE FROM Agreements Where PetID="+petID+"";
		executeQuery(sql);
		sql= "DELETE FROM Pets Where ID = '"+petID+"';";
		executeQuery(sql);
                System.out.println("Pet "+petID+" has successfully been deleted");
	}
	// delete Sitter
	public void deleteSitter(String email){
		String sql= "DELETE FROM Agreements Where ServiceAddress in (SELECT Address FROM Services Where SitterEmail='"+email+"')";
		executeQuery(sql);
		sql= "DELETE FROM Services Where SitterEmail='"+email+"'";
		executeQuery(sql);
		sql= "DELETE FROM Sitter Where Email = '"+email+"';";
		executeQuery(sql);
                System.out.println("Sitter "+email+" has successfully been deleted");
	}
	
	// delete Service
	public void deleteService(String Address){
		String sql= "DELETE FROM Agreements Where ServiceAddress='"+Address+"'";
		executeQuery(sql);
		sql= "DELETE FROM Services Where Address = '"+Address+"';";
		executeQuery(sql);
                System.out.println("Services at address "+Address+" has successfully been deleted");
	}
	
	//delete agreement
	public void deleteAgreement(String Invoice){
		String sql= "DELETE FROM Agreements Where Invoice = "+Invoice+";";
		executeQuery(sql);
                System.out.println("Agreement with Invoice Number "+Invoice+" has successfully been deleted");
	}
}
