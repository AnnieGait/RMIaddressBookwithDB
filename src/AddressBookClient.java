import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class AddressBookClient {
	
	private static final String HOST = "localhost";
	private static final int PORT = Registry.REGISTRY_PORT; // 1099
	
	public static void main(String[] args) {
		
		try {
			//Locate rmi registry
			Registry registry = LocateRegistry.getRegistry(HOST, PORT);
			
			String rmiObjectName = "AddressBook";
			AddressBook ref = (AddressBook) registry.lookup(rmiObjectName);
			
			//ref.connectToDB();
			//ref.createTable();
			
			char ans;
			do {
				int choice;
				System.out.println("DATABASE");
				System.out.println("1 - For insertion in DB");
				System.out.println("2 - For selection in DB");
				System.out.println("3 - For updating the DB");
				System.out.println("4 - Deleting from DB");
				System.out.println("5 - For selection in DB");
				
				Scanner scan = new Scanner(System.in);
				choice = scan.nextInt();
				
				if(choice == 1) {
					System.out.println("You selected insertion in DB.");
					
					System.out.println("Enter phone");
					String phone = scan.next();
					
					System.out.println("Enter id");
					String id = scan.next();
					
					System.out.println("Enter name");
					String name = scan.next();
					
					System.out.println("Enter email");
					String mail = scan.next();
					
					ref.insertInDB(phone, id, name, mail);
				} else if(choice == 2) {
					System.out.println("You selected selection in DB.");
					System.out.println(ref.selectFromDB());
				} else if(choice == 3) {
					System.out.println("You selected updating the DB.");
					System.out.println("Enter existing phone you want to update");
					String phone = scan.next();
					
					System.out.println("Enter new id");
					String id = scan.next();
					
					System.out.println("Enter new name");
					String name = scan.next();
					
					System.out.println("Enter new email");
					String email = scan.next();
					
					ref.updateDB(phone, id, name, email);
				} else if(choice == 4) {
					System.out.println("You selected deletion in DB.");
					System.out.println("Enter existing phone you want to delete");
					String phone = scan.next();

					ref.deleteDB(phone);
				}
				else if(choice == 5) {
				System.out.println("You selected selection of an element from DB. ");
				System.out.println("Enter existing phone you want to select");
				String phone = scan.next();
				System.out.println("testing if scne next works" + phone);
				System.out.println(ref.selectContactFromDB(phone));
			}
				
				System.out.println("Would you like to continue? (Y or y:yes) ");
				ans = scan.next().charAt(0);
			} while(ans == 'Y' || ans == 'y');		
			
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
