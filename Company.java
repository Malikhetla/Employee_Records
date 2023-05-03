import java.io.*;
import java.util.*;
import java.lang.Comparable;


public class Company {
    private Scanner in;
	private EmployeeList employee;
	private String fileName;
	
		
    public Company () {
        in = new Scanner(System.in);
		this.fileName = "employees.csv"; 
		this.employee = new EmployeeList();
    } // constructor






    /*
	    1. CREATING AN EMPLOYEE RECORD
		
	    To create a new employee record 2 componets are used; the list "employees" and the csv "employees.csv"
		The "enterRecords()" method solicits user information and adds this information existing lists "employees"
		The "addEmployee() "method uses data from the "enterRecords()" to add a new employee to "employees.csv
	
	*/
	
	

	
    private void createEmpl (){  
	    int flag = 0;
		int cnt = 0;
		while(flag == 0){
			if(cnt == 0){
				System.out.println("Add employee? <y> for yes, or any character to quit.");
				String cmd = in.next();
				if(cmd.equals("y")){
				   enterRecords();
				   cnt++;
				}
				else{
					flag = 1;
				}
				
			}
			else{
				System.out.println("Add another contact? Type <y> to add another contact, or any character to quit.");
				String cmd = in.next();
				if(cmd.equals("y")){
					enterRecords();
					cnt++;
				}
				else{
					flag = 1;
				}
			}
		}
			
    } // createEmpl
	
	
	
	private void addEmployee (String name, String surname, String birth, int num, int salary, String role, int supervisor){				
		try{
			File employeeLst = new File(this.fileName);
			
			if(!employeeLst.exists()){
				employeeLst.createNewFile();
				
				PrintWriter w = new PrintWriter(new FileWriter(employeeLst));
				w.print(name + "," + surname + "," + birth +  "," + num +"," + salary + "," + role + "," + supervisor);
				w.close();
			}
			else{
				PrintWriter w = new PrintWriter(new FileWriter(employeeLst, true));
				w.print(name + "," + surname + "," + birth +  "," + num +"," + salary + "," + role +"," + supervisor);
				w.close();
			}
		}
		catch (IOException ex){
			ex.printStackTrace();
		}
		
	}// addEmployee
    
	
	
		
	private void enterRecords (){//method ask user for information and does some validation checks. It adds a new employee to EmployeeList 
		in = new Scanner(System.in);
		System.out.println();
	
	
		System.out.print("Enter Employee number: ");
		String number = in.next();
		int value = 0;
		try{
		   value = Integer.parseInt(number);
		   Employee empl = findEmployeeByNumber(value);
		   while(empl != null){
			   System.out.println("Duplicate employee number found.");
			   System.out.println(" ");
			   System.out.println("Please re-enter employee number: ");
			  
			   try{
				   value = Integer.parseInt(number);
				}
			   catch (Exception exc){
				
					System.out.println("Please re-enter correct number: ");
					
					
				}
				number = in.next();
				value = Integer.parseInt(number);
				empl = findEmployeeByNumber(value);							
			}				  					 					   
		}
		catch (Exception exc){
			System.out.println("Please re-enter correct number: ");
			number = in.next();
			value = Integer.parseInt(number);
		}
		int empNum = value;
		
		
		System.out.print("Enter First Name: ");  
		String inputName =  in.next();
		String empName = checkString(inputName.toLowerCase(), 1);
	
	
		System.out.print("Enter Surname: "); 
		String inputSurname =  in.next();
		String empSurname = checkString(inputSurname.toLowerCase(), 2);
		
		
		System.out.print("Enter Birth Date (format ddmmyyyy): "); 
		String inputBirth = in.next();
		String empBirth = checkInt(inputBirth);
		
	 
		
		
		System.out.print("Enter Salary: ");  
		String inputSalary = in.next();
		int empSalary = checkSalary(inputSalary);
	 
		
		System.out.print("Enter Role Description: \n"); 
		System.out.println("M) for Manager, E) for Employee, T) for Trainee");
	   
		String inputRole =  in.next();
		String empRole = "  ";
		int empSupervisor = 0;
		switch (inputRole) {
		
			case "M": case "m":
				empRole = "Manager";
				
				break;
			case "E": case "e":
				empRole="Employee";
				System.out.print("Enter the supervior employee number: ");
				String supervisor = in.next();
				
				int supNumber = 0; 
				try{
				  supNumber = Integer.parseInt(supervisor);
				  empSupervisor = supNumber;
				 
				   
				}
				catch (Exception exc){
					System.out.println("Please re-enter correct number: ");
					supervisor = in.next();
					supNumber = Integer.parseInt(supervisor);
					empSupervisor = supNumber;
				}
				
				break;
			case "T": case "t":
				empRole = "Trainee";
				System.out.print("Enter the supervior employee number: ");
				supervisor = in.next();
				
				supNumber = 0; 
				try{
				  supNumber = Integer.parseInt(supervisor);
				  empSupervisor = supNumber;
				 
				   
				}
				catch (Exception exc){
					System.out.println("Please re-enter correct number: ");
					supervisor = in.next();
					supNumber = Integer.parseInt(supervisor);
					empSupervisor = supNumber;
				}
				break;
			default:
			  System.out.printf("Command not valid.");
			  return;
		}

        
		
		Employee newEmployee = new Employee(empName, empSurname, empBirth, empNum, empSalary, empRole, empSupervisor);
		try{
			employee.add(newEmployee);
			System.out.println("\nEmployee registration completed.");
			System.out.println(newEmployee + "\n");
			
		}
		catch (Exception exc){
			System.out.println("Duplicate employee record found.");
			
		}
		
		addEmployee(empName, empSurname, empBirth, empNum, empSalary, empRole, empSupervisor);
		
	}// readFile
	
	
	
	
	/*
	    2. QUERYING AN EMPLOYEE 
		
	    queryEmployeeRecords() finds employee records based on a given criteria
		The method depends on the linked list methods defined in the EmployeeList class 
		   to find one or more employees who satisfy the given criteria or return an empty list 
		   if the employee is not found. 
		   
		   This method uses the displayEmployeeDetails(), displayEmployeeBySupervisor(), 
		      displayEmployeeBySalary  and  displayEmployeeByNumber () methods 
	
	*/
	
	
	
	
	
	
    public void queryEmployeeRecords(){									
		System.out.println();
		while (true){
			System.out.println("Do you wish to:");
			System.out.println("[1] Query employee by employee number");
            System.out.println("[2] Query employee by name");
			System.out.println("[3] Query employee by surname");
			System.out.println("[4] Query employee by birth date");
		    System.out.println("[5] Query employee by role");
			System.out.println("[6] Query employee by salary");
			System.out.println("[7] Query employee by reporting line");
			System.out.println("[q] Return to previous menu");
			System.out.print("Enter your option: ");
			String cmd = in.next();
			switch (cmd){
				case "1":
				    in = new Scanner(System.in);
		            System.out.println();
					System.out.print("Enter employee number: ");
					String inp = in.next();
					int value = 0; 
					try{
					  value = Integer.parseInt(inp);
					  
					 
					   
					}
					catch (Exception exc){
						System.out.println("Please re-enter correct number: ");
						inp = in.next();
						value = Integer.parseInt(inp);
					}
					
					int n = value;
					
					displayEmployeeByNumber(n);
					break;
				case "2":
				    System.out.println();
					in = new Scanner(System.in);
					System.out.print("Enter the employee name or part thereof: ");
				    String name = in.next();
					int option = 2;
					displayEmployeeDetails(checkString(name.toLowerCase(), 1), option);
                    break;
				case "3":
				    System.out.println();
					in = new Scanner(System.in);
					System.out.print("Enter the employee surname or part thereof: ");
				    String surname = in.next();
                    option = 3;
					displayEmployeeDetails(checkString(surname.toLowerCase(), 2), option);
                    break;
				case "4":
				    System.out.println();
					in = new Scanner(System.in);
					System.out.print("Enter the birth date or part thereof: ");
				    String birth = in.next();
					
					option = 4;
					displayEmployeeDetails(birth, option);
					
                    break;
				case "5":
				    System.out.println();
					in = new Scanner(System.in);
					System.out.print("Enter role (M) for Manager,  E) for Employee,  T) for Trainee: ");
					
					String role = in.next();
	
					switch (role) {
					
						case "M": case "m":
							String empRole = "Manager";
							displayEmployeeDetails(empRole, 5);
							break;
						case "E": case "e":
							role="Employee";
							displayEmployeeDetails(role, 5);
							break;
						case "T": case "t":
							 role = "Trainee";
							 displayEmployeeDetails(role, 5);
						default:
						  System.out.printf("Command %s not valid.");
						  return;
					}
					break;

                
				case "6": 
				    System.out.println();
					in = new Scanner(System.in);
					System.out.print("Enter Employee Salary : ");
					String salary = in.next();
					displayEmployeeBySalary(checkSalary(salary));
                    break;
                
				case "7":
				    System.out.println();
					in = new Scanner(System.in);
					
					System.out.print("Enter the supervior employee number: ");
				    String supervisor = in.next();

					int supNumber = 0; 
					try{
					  supNumber = Integer.parseInt(supervisor);

					}
					catch (Exception exc){
						System.out.println("Please re-enter correct number: ");
						supervisor = in.next();
						supNumber = Integer.parseInt(supervisor);
					}
					int num = supNumber;
					
					displayEmployeeByNumber(num);
				
                    break;
				case "Q": case "q":
					System.out.println();
					return;
                default:
                    System.out.printf("\nCommand %s not valid.  Please re-enter...", cmd + "\n");
					System.out.println();
                    break;
		    }
		}
	}// queryEmployeeRecords
	
	
	private void displayEmployeeByNumber (int empNo) {
		System.out.println("Employee: " + findEmployeeByNumber(empNo));
	} // displayEmployeeByNumber 

    private Employee findEmployeeByNumber (int empNo) {
		return employee.getEmployee(empNo);
    } // findEmployeeByNumber
    
	
	private void displayEmployeeBySalary (int salary) {
		List<Employee> empl = employee.getEmployeeSalary(salary);
		if(empl.isEmpty() == false){
			for(int i = 0; i < empl.size(); i++){
				Employee details = empl.get(i);
				System.out.println(details);
			}
		}
		else{
			System.out.println("The employee does not exist.");
		}
    } // displayEmployeeBySalary 
	
	
	private void displayEmployeeBySupervisor (int supervisor) {
		List<Employee> empl = employee.getEmployeeSupervisor(supervisor);
		if(empl.isEmpty() == false){
			for(int i = 0; i < empl.size(); i++){
				Employee details = empl.get(i);
				System.out.println(details);
			}
		}
		else{
			System.out.println("The employee does not exist.");
		}
    } // displayEmployeeBySupervisor

	private void displayEmployeeDetails (String item, int choice) {
		
		List<Employee> empl = employee.getEmployeeForName(item);
		switch(choice){
			case 2:
				  empl = employee.getEmployeeForName(item); 
				  break;
			case 3:
				  empl = employee.getEmployeeForSurname(item);
				  break;
            case 4:
				  empl = employee. getEmployeeBirthDate(item);
				  break;	
            case 5:
				  empl = employee.getEmployeeRole(item);
				  break;	
            				  
			default:
				  System.out.printf("Command %s not valid.");
				  return;
				
		}	  
		
		if(empl.isEmpty() == false){
			for(int i = 0; i < empl.size(); i++){
				Employee details = empl.get(i);
				System.out.println(details);
			}
		}
		else{
			System.out.println("The employee does not exist.");
		}
    } // displayEmployeeDetails
	
	
	
	
	
	    /*
	    3. UPDATING AN EMPLOYEE RECORD 
		
	    Personal details of the employee are updated using the employees's number 
		
		First the required fields or new values are obtained and changed 
		 in the changeInformation() method 
	   
	   Changes are observed when the program is run again 
	   */
	

	private void editEmployeeRecords(){
		System.out.println(" ");
		System.out.println();
		
		
		in = new Scanner(System.in);
		System.out.println();
		System.out.print("Enter employee number : ");
		String inp = in.next();
		
		int value = 0; 
		try{
		  value = Integer.parseInt(inp);
		  
		 
		   
		}
		catch (Exception exc){
			System.out.println("Please re-enter correct number: ");
			inp = in.next();
			value = Integer.parseInt(inp);
		}
		
		int n = value;
		
		Employee empToChange = findEmployeeByNumber(n);
		while (true){
			System.out.println("Do you wish to:");
			System.out.println("[1] Edit employee name");
			System.out.println("[2] Edit employee surname");
			System.out.println("[3] Edit employee date of birth");
			
			System.out.println("[q] Return to previous menu");
			System.out.print("Enter your option: ");
			String cmd = in.next();
			String oldRecord = empToChange.getName() + "," + empToChange.getSurname() + "," + empToChange.getDateOfBirth() + "," + String.valueOf(empToChange.getEmployeeNumber()) + "," + String.valueOf(empToChange.getSalary()) + "," + empToChange.getEmployeeRoleDescription() + "," + String.valueOf(empToChange.getSupervisor());
			String str  = "  '";
			switch (cmd){
				case "1":
					in = new Scanner(System.in);
					System.out.println();
					System.out.print("Enter new employee name: ");
					inp = in.next();
					String newName = checkString(inp.toLowerCase(), 1);

					 
					str = newName + "," + empToChange.getSurname() + "," + empToChange.getDateOfBirth() + "," + String.valueOf(empToChange.getEmployeeNumber()) + "," + String.valueOf(empToChange.getSalary()) + "," + empToChange.getEmployeeRoleDescription() + "," + String.valueOf(empToChange.getSupervisor());
					  
                    changeInformation (str, oldRecord);
					break;
				case "2":
					System.out.println();
					System.out.print("Enter new employee surname: ");
					inp = in.next();
					newName = checkString(inp.toLowerCase(), 2);
					
					str = empToChange.getName() + "," +  newName+ "," + empToChange.getDateOfBirth() + "," + String.valueOf(empToChange.getEmployeeNumber()) + "," + String.valueOf(empToChange.getSalary()) + "," + empToChange.getEmployeeRoleDescription() + "," + String.valueOf(empToChange.getSupervisor());
					changeInformation (str, oldRecord);
					break;
				case "3":
		
				    System.out.println();
					System.out.print("Enter new employee birth date (format ddmmyyyy): ");
					inp = in.next();
					newName = checkInt(inp);
	
					str = empToChange.getName() + "," +  empToChange.getSurname() + "," + newName + "," + String.valueOf(empToChange.getEmployeeNumber()) + "," + String.valueOf(empToChange.getSalary()) + "," + empToChange.getEmployeeRoleDescription() + "," + String.valueOf(empToChange.getSupervisor());
					changeInformation (str, oldRecord);
					break;
	
				case "Q" : case "q":	 
					System.out.println();
					return;
				default:
					System.out.printf("\nCommand not valid.  Please re-enter...", cmd + "\n");
					System.out.println();
					break;
			}
		}
	}
	
		
	
	
	private void changeInformation (String newRecord, String oldRecord){// reads in the employees.csv file and changes the details which are not currently int he file 
		
			
					try {

					  File inFile = new File(this.fileName );

					  if (!inFile.isFile()) {
						System.out.println("Parameter is not an existing file");
						return;
					  }

					
					  File tempFile = new File("temp.csv");
                    
					  BufferedReader br = new BufferedReader(new FileReader(this.fileName));
					  PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

					  String line = null;

					 
					  while ((line = br.readLine()) != null) {
                            
							if (!line.trim().equals(oldRecord)) {
                              
							  pw.println(line);
							  
							  pw.flush();
							  
							}
							else{
								pw.println(newRecord);
								System.out.println("Employee details have been successfully updated.");
								
							}
						}
					
					  pw.close();
					  br.close();

						if (!inFile.delete()) {
							System.out.println("Could not delete file");
							return;
						}

						
						if (!tempFile.renameTo(inFile)){
							System.out.println("Could not rename file");

						}
					}
					catch (FileNotFoundException ex) {
					  ex.printStackTrace();
					}
					catch (IOException ex) {
					  ex.printStackTrace();
					}
	}
		
	
	
	
	
	    /*
	    4. DISPLAYING EMPLOYEE RECORDS 
		
	    Using name or surname employee records are sorted and displayed 
		This method uses the sortEmployees() birthDateSort() and sort() methods 
	 */
	
	private void printEmpoyeeRecords(){
		
		System.out.println();
		while(true){
			System.out.println("Sort contacts by:");
			System.out.println("N) Name");
			System.out.println("S) Surname");
			System.out.println("B) Date of birth");
			System.out.println("R) Role (salary based)");
			System.out.println("Q) quit");
			System.out.print("Enter your option: ");
			String cmd = in.next();
			switch(cmd){
				case "N": case"n":
					List<String> srtList = sortEmployees (cmd);
					
					printContacts(srtList);
					break;
				case "S": case "s":
					List<String> srtList2 = sortEmployees (cmd);
					
					printContacts(srtList2);
					break;
				case "B": case"b":
					in = new Scanner(System.in);
					System.out.println();
					System.out.print("Enter employee birth date (format ddmmyyyy): ");
					String inp = in.next();
					System.out.print("Do you want employees born: ");
                    System.out.print("[b]  before given date ");
                    System.out.print("[a] after given date ");
					String ch = in.next();
					String birth = checkInt(inp);
					birthDateSort(birth, ch);
					break;
				case "R": case"r":
					 sort();
					 break;
				case "Q": case "q":		
					System.out.println();
					return;
				default:
					System.out.printf("Command %s not valid.  Please re-enter...", cmd);
					System.out.println();
                    break;
			}// switch
		}
		
	
	}// printEmpoyeeRecords
	
	
	private void printContacts (List<String> lst){
		System.out.println();
		System.out.printf("List of employees: ");
		System.out.println(" ");
		System.out.printf("Name Surname   DOB  EmpNo. Salary Role Supervisor : ");
		System.out.println();
	
		for(int i = 0; i < lst.size(); i++){
			
			String contact = lst.get(i);
			System.out.println(contact);
			
		}
		System.out.println();
		System.out.println();
	}//printContacts
	
	
	private List<String> sortEmployees (String inp){//sort employee using given string (Name or surname)
		List<String> lst = new LinkedList<>();
		
		if(inp.equals("n")){
			for(int i = 0; i < employee.getNumEmployees(); i++){
				Employee details = employee.getEmployeeK(i);
				String name = details.getName() + " " + details.getSurname() + ", " + details.getDateOfBirth() + ", " + String.valueOf(details.getEmployeeNumber()) + ", " + String.valueOf(details.getSalary()) + ", " + details.getEmployeeRoleDescription() + ", " +  String.valueOf(details.getSupervisor());
				
				lst.add(name);
			}

		   
			Collections.sort(lst);
			
		}
		else if(inp.equals("s")){
			for(int j = 0; j < employee.getNumEmployees(); j++){
				Employee details = employee.getEmployeeK(j);
			String surname = details.getSurname()  + " " +  details.getName() + ", " + details.getDateOfBirth() + ", " + String.valueOf(details.getEmployeeNumber())+ ", " + String.valueOf(details.getSalary()) + ", " + details.getEmployeeRoleDescription() + ", " +  String.valueOf(details.getSupervisor());
				lst.add(surname);
				
			}
			
			Collections.sort(lst);
			
		}
		return lst;
	}// sortEmployees
	
	private void printEmployees(List<String> lst){
		System.out.println();
		System.out.printf("List of employees: ");
		System.out.println(" ");
		System.out.printf("Name Surname   DOB  EmpNo. Salary Role Supervisor : ");
		System.out.println();
		
		for(int i = 0; i < lst.size(); i++){
		
			String employeee = lst.get(i);
			System.out.println(employee);
			
		}
		System.out.println();
		System.out.println();
	}//priintEmployees
	
	
	private void birthDateSort( String date, String inp){
		
	    
		Integer day = 0;
		Integer month = 0;
		Integer year = 0;
		String newDate = " ";
		String empDay = " ";
		String empMonth = " ";
		String empYear = " ";
		String dateDay = date.substring(0,2);
		String dateMonth = date.substring(2,4);
		String dateYear = date.substring(4);
		Integer eDay = Integer.parseInt(dateDay);
		Integer eMonth = Integer.parseInt(dateMonth);
		Integer eYear = Integer.parseInt(dateYear);
		
		for(int i = 0; i < employee.getNumEmployees(); i++){
			Employee details = employee.getEmployeeK(i);
		    String empDate = details.getDateOfBirth();
		
			String sDay  = empDate.substring(0,2);
			String sMonth  = empDate.substring(2,4);
			String sYear = empDate.substring(4);
			day = Integer.parseInt(sDay);
			month = Integer.parseInt(sMonth);
			year =  Integer.parseInt(sYear);
			switch (inp){
				case "B": case "b":
				      if(eYear >= year){ //anyone born before given date
						   if(eYear > year){
								
								System.out.println(details.getDateOfBirth() + " " + details.getName() + " " + details.getSurname()
								+ " " + details.getEmployeeNumber() + " " + details.getEmployeeRoleDescription() + " " + details.getSalary() + " " + details.getSupervisor());
						   }
						   else if(eYear == year){
							   if(eMonth >= month){
								   if(eMonth > month) {
									   System.out.println(details.getDateOfBirth() + " " + details.getName() + " " + details.getSurname()
										 + " " + details.getEmployeeNumber() + " " + details.getEmployeeRoleDescription() + " " + details.getSalary() + " " + details.getSupervisor());
								   }
								   else if(eMonth == month){
										if(eDay == day || eDay > day){
											System.out.println(details.getDateOfBirth() + " " + details.getName() + " " + details.getSurname()
											   + " " + details.getEmployeeNumber() + " " + details.getEmployeeRoleDescription() + " " + details.getSalary() + " " + details.getSupervisor());
											
										}
										
									   
								   }
							   }
						   }
						}
						break;
				    
				case "A": case "a":	
				    if(eYear <= year){ //anyone born after given date
					   if(eYear < year){
							
							System.out.println(details.getDateOfBirth() + " " + details.getName() + " " + details.getSurname()
							+ " " + details.getEmployeeNumber() + " " + details.getEmployeeRoleDescription() + " " + details.getSalary() + " " + details.getSupervisor());
					   }
					   else if(eYear == year){
						   if(eMonth <= month){
							   if(eMonth < month) {
								   System.out.println(details.getDateOfBirth() + " " + details.getName() + " " + details.getSurname()
									 + " " + details.getEmployeeNumber() + " " + details.getEmployeeRoleDescription() + " " + details.getSalary() + " " + details.getSupervisor());
							   }
							   else if(eMonth == month){
									if(eDay == day || eDay > day){
										System.out.println(details.getDateOfBirth() + " " + details.getName() + " " + details.getSurname()
										   + " " + details.getEmployeeNumber() + " " + details.getEmployeeRoleDescription() + " " + details.getSalary() + " " + details.getSupervisor());
										
									}
									
								   
							   }
						   }
					   }
					}
					break;
			
				default:
					System.out.printf("\nCommand not valid.  \n");
					System.out.println();
					return;
					   
		    }
	    }
	}// birthDateSort
	
	
	
	private void sort () {//sorts employees based on their role and salary. This method uses the compareTo() defined in Employee Class
	
		int count = 0;
		String role = null;
		while(count < 3){
			
			if(count == 0){
				role = "Manager";
			}
			if(count == 1){
				role = "Employee";
			}
			if(count == 2){
				role = "Trainee";
			}
			System.out.println(role + "s" );
			List<Employee> empl = employee.getEmployeeRole(role);
			Collections.sort(empl);
			if(empl.isEmpty() == false){
				for(int j = 0; j < empl.size(); j++){
					Employee details = empl.get(j);
					System.out.println(details);
				}
			}
			System.out.println();
			count++; 
		}   
		
	} // sort
	


					

	    /*
	    6. REMOVING AN EMPLOYEE RECORD
		
	    To remove an employee from the records, the deleteEmployee method uses given data (Name or Surname) 
		    to find employees. 
			
		These changes are only reflected in the EmployeeList when the program is run again. 
	 */
	 
	 
	 
	 
	private void delete (String inp, int num){								
	
		List<String> del = new LinkedList<>();
	    //a list of employee whose names are similar to the given string a added to a "delete list" 
		for(int i = 0; i < employee.getNumEmployees(); i++){
				Employee details = employee.getEmployeeK(i);
				String nameCheck = details.getName() ;
				String name = details.getName() + "," + details.getSurname() + "," + details.getDateOfBirth() + "," + String.valueOf(details.getEmployeeNumber()) + "," + String.valueOf(details.getSalary()) + "," + details.getEmployeeRoleDescription() + "," +  String.valueOf(details.getSupervisor());
				if(nameCheck.contains(inp)){
				    del.add(name);
				}
		}
			//from the delete list the user chooses the desired employee to delete 
		int flag = 1;
		while (flag != 0){
			for(int j = 0; j < del.size(); j++){ 
				int n = j + 1;
				String con = del.get(j);
				String[] hol = con.split(",");
				System.out.println(n + ". " + hol[0] + " " + hol[1] + " : " + hol[2]);
			}
			System.out.println("0. Return to previous menu");
			System.out.println();
			
			System.out.println("Which one do you wish to delete?");
			String cmd = in.next();
			
			if(cmd.equals("0")){
				flag = 0;
			}
			else{
				int idx = -1;
				int index = Integer.parseInt(cmd);
			    String str = del.get(index-1);
				String[] employ  = str.split(",");
				int numb = Integer.parseInt(employ[3]);
				Employee empl = findEmployeeByNumber(numb);
				System.out.print("Employee " + empl.getName() + " " + empl.getSurname() + " " + " records have been deleted!");
				employee.remove(empl);
				idx++;
				String lineToRemove = str;
				try {

				  File inFile = new File(this.fileName );

				  if (!inFile.isFile()) {
					System.out.println("Parameter is not an existing file");
					return;
				  }

                 //the employees.csv records are updated by removing the entry of the deleted employee
				  File tempFile = new File("temp.csv");

				  BufferedReader br = new BufferedReader(new FileReader(this.fileName));
				  PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

				  String line = null;

				
				  while ((line = br.readLine()) != null) {

						if (!line.trim().equals(lineToRemove)) {

						  //pw.println(line);
						  pw.flush();
						}
				    }
				  pw.close();
				  br.close();

				  
					if (!inFile.delete()) {
						System.out.println("Could not delete file");
						return;
					}
                    

					if (!tempFile.renameTo(inFile)){
						System.out.println("Could not rename file");

					}
				}
				catch (FileNotFoundException ex) {
				  ex.printStackTrace();
				}
				catch (IOException ex) {
				  ex.printStackTrace();
				}
			    

			}
		}
		System.out.println();
	}// delete
	   
	
	public void deleteEmployee (){												
		System.out.println();
		
		while (true){
			System.out.println("Do you wish to:");
			System.out.println("[1] Delete by name");
			System.out.println("[2] Delete by surname");
			System.out.println("[q] Return to previous menu");
			System.out.print("Enter your option: ");
			String cmd = in.next();
			switch (cmd){
				case "1":
					System.out.print("Enter a name or part thereof: ");
					String inp = in.next();
					String newName = checkString(inp.toLowerCase(), 1);
					delete(newName, 0);
					break;
				case "2":
					System.out.print("Enter a surname or part thereof: ");
					String inp2 = in.next();
					newName = checkString(inp2.toLowerCase(), 1);
					delete(newName, 1);
					break;
				case "Q": case "q":
					System.out.println();
					return;
				default:
					System.out.printf("Command %s not valid.  Please re-enter...", cmd);
					System.out.println();
                    break;
			}
		}
	}// deleteEmployee
	

	
	
	    /*
	    6. LISTING COMPANY EMPLOYEES
		
	    This method displays all company employees in a hierarchy. It uses linked lists to find employees based on role starting from the highest (Manager) to the lowest (Trainee)
	*/
	private void listCompanyStructure () {
		
			List<Employee> empl = employee.getEmployeeRole("Manager");

			System.out.println(empl.size());
			if(empl.isEmpty() == false){
				for(int j = 0; j < empl.size(); j++){
					Employee detail = empl.get(j);
				
					System.out.println(detail.getEmployeeRoleDescription() + "  " + detail.getEmployeeNumber() + "  " + detail.getName() + "  " +
					detail.getSurname() + "  " + detail.getDateOfBirth() + "  R" +detail.getSalary() );
					
					int bossName = detail.getEmployeeNumber();
					List<Employee> subEmpl = employee.getEmployeeSupervisor(bossName);
					for(int i = 0; i < subEmpl.size(); i++){
					   Employee details = subEmpl.get(i);
					   System.out.println("   " + details.getEmployeeRoleDescription() + "  " + details.getEmployeeNumber() + "  " + details.getName() + "  " +
					details.getSurname() + "  " + details.getDateOfBirth() + "  R" + details.getSalary() );
					  
					   int bossName2 = details.getEmployeeNumber();
					   List<Employee> subSubEmpl = employee.getEmployeeSupervisor(bossName2);
					   
					   for(int k = 0; k < subSubEmpl.size(); k++){
						   Employee info = subSubEmpl.get(k);
						   System.out.println("     " + info.getEmployeeRoleDescription() + "  " + info.getEmployeeNumber() + "  " + info.getName() + "  " +
					               info.getSurname() + "  " + info.getDateOfBirth() + "  R" + info.getSalary() );
						    int bossName3 = info.getEmployeeNumber();
					        List<Employee> subSubEmpl2 = employee.getEmployeeSupervisor(bossName3);
							for(int l = 0; l < subSubEmpl2.size(); l++){
						      Employee info2 = subSubEmpl2.get(l);
						       System.out.println("        " + info2.getEmployeeRoleDescription() + "  " + info2.getEmployeeNumber() + "  " + info2.getName() + "  " +
					               info2.getSurname() + "  " + info2.getDateOfBirth() + "  R" + info2.getSalary() );
							}
					   }
					   System.out.println(" ");
					   
					}
					System.out.println(" ");
				}
			}
			System.out.println();
	}//  listCompanyStructure 




	//SANITY CHECK METHODS 
	private int checkSalary (String value) {//checks for correct salary range 
		int amount = 0;
		
		try{
		   amount = Integer.parseInt(value);
		}
		catch (Exception exc){
			System.out.println("Please re-enter correct salary (R100 - R2000000): ");
			value = in.next();
			amount = Integer.parseInt(value);
		}
		if(amount < 100 || amount > 2000000){
			while(amount < 100 || amount > 2000000){
			    
				System.out.print("Please re-enter correct salary (R100 - R2000000): ");
				value = in.next();
				amount = Integer.parseInt(value);	
		    }
			return amount;
		}
		else{
			return amount;
		}
    }//checkSalary 
	

	
	private String checkString (String str, int option){//checks to ensure strings have only letters 
		
		String item = " ";
		switch(option){
			case 1:
			  item = "name";
			  break;
			case 2:
			  item = "surname";
			  break;
			case 3:
			  item = "supervisor";
			  break;
			default:
			  item = "role";
			  break;
			
		}
		boolean resultname = str.matches("[a-zA-Z']+"); //Name cannot have symbols or numbers. Exceptions are names like O'Malley
		if(!resultname){
			
			while(!resultname){
			    
				System.out.print("Please re-enter correct " + item + " (no symbols/numbers): ");
				str = in.next();
				resultname = str.matches("[a-zA-Z']+");	
		    }
		}
		//Store Name with first letter capitalized
		String firstName = str.substring(0, 1);
		String remName = str.substring(1);
		firstName = firstName.toUpperCase();
		remName= remName.toLowerCase(); 
	    return firstName + remName;
	
	}//checkString 
	
	

	
	private String checkInt (String birthDate){//checks that birthdate is corrrect days months and year 
		
		boolean incorrectBirth = true;
		String newDate = " ";
		while(incorrectBirth) {
			
			
			    Integer day = 0;
				Integer month = 0;
				Integer year = 0;
				
				String sDay = birthDate.substring(0,2);
				String sMonth = birthDate.substring(2,4);
				String sYear = birthDate.substring(4);
			
				try{
				   day = Integer.parseInt(sDay);
				}
				catch (Exception exc){
					System.out.println("Please enter correct birth date (either day, month or year is incorrect ): ");
					birthDate = in.next();
					sDay = birthDate.substring(0,2);
					day = Integer.parseInt(sDay);
				}
				
				try{
					 month = Integer.parseInt(sMonth);
				}
				catch (Exception exc){
					System.out.println("Please enter correct birth date (either day, month or year is incorrect ): ");
					birthDate = in.next();
					sMonth = birthDate.substring(2,4);
					month = Integer.parseInt(sMonth);
				}
				
				try{
					 year = Integer.parseInt(sYear);
				}
				catch (Exception exc){
					System.out.println("Please enter correct birth date (either day, month or year is incorrect ): ");
					birthDate = in.next();
					sYear = birthDate.substring(4);
					year = Integer.parseInt(sYear);
				}
				
				boolean correctYear = checkYear(year);
				boolean correctMonth  = checkMonth(month);
				boolean correctDayAndMonth = checkDayAndMonth(day, month);
				boolean correctDay  = checkDay(day);
				
				if(correctDay && correctDayAndMonth && correctMonth  && correctYear){
					  if(day < 10 && month < 10){
							newDate = "0"+String.valueOf(day)  + "0"+String.valueOf(month)+ String.valueOf(year);
						}
						else if(day < 10){
							newDate = "0"+String.valueOf(day)  + String.valueOf(month)+ String.valueOf(year);

						}
						else if(month < 10){
						   newDate = String.valueOf(day)  + "0"+String.valueOf(month)+ String.valueOf(year);
						}
						else{
							newDate = String.valueOf(day) + String.valueOf(month)+ String.valueOf(year);
							
						}
                         incorrectBirth = false;						
				}
				else{
					System.out.println("Please enter correct birth date (either day, month or year is incorrect ): ");
					birthDate = in.next();
					
				}
				
        }				
		return newDate;	
	}//checkInt 
	
	
	private boolean checkDay( int day){
		 boolean correctDay  = false;
		 
		if(day < 1 || day > 31) {
				
			while(day < 1 || day > 31){//days of the month are not more than 31
				
				System.out.print("Please re-enter correct birth date (01 to 31): ");
				String birthDate = in.next();
				String sDay = birthDate.substring(0,2);
				try{
					day = Integer.parseInt(sDay);
				}
				catch (Exception exc){
					System.out.println("Please enter correct birth date (either day, month or year is incorrect ): ");
					birthDate = in.next();
				}
				
				
			}
		}
		else{
			correctDay  = true;
		}
		return correctDay;
	}//checkDay
	
	private boolean checkDayAndMonth( int day, int month){
		
		List<Integer> evenMonths = Arrays.asList(4, 6, 9, 11);
		boolean correctDayAndMonth = false;
		if(day > 28 && month == 2){// february ends on the 28th 
			while(day > 28 && month == 2){
				
				System.out.print("Please re-enter correct birth date (01 to 28 for February): ");
				String birthDate = in.next();
				String sDay = birthDate.substring(0,2);
				try{
					day = Integer.parseInt(sDay);
				}
				catch (Exception exc){
					System.out.println("Please enter correct birth date (either day, month or year is incorrect ): ");
					birthDate = in.next();
				}
				
			}
		}
		else if(day > 30 && evenMonths.contains(month)){ // month like aprrl, june september etc have only 30 days 
			while(day > 30 && evenMonths.contains(month)){
				
				System.out.print("Please re-enter correct birth date (month selected has 30 days): ");
				String birthDate = in.next();
				String sDay = birthDate.substring(0,2);
				try{
					day = Integer.parseInt(sDay);
				}
				catch (Exception exc){
					System.out.println("Please enter correct birth date (either day, month or year is incorrect ): ");
					birthDate = in.next();
				}
				
			}
		}
		else{
			correctDayAndMonth = true;
		}
		
		return correctDayAndMonth;
	}//checkDayAndMonth
	
	
	
	private boolean checkMonth( int month){
		boolean correctMonth = false;
		if(month < 1 || month > 12) {
		  while(month < 1 || month > 12){//valid months are from january to december 
		
				System.out.print("Please re-enter correct birth month (01 for Jan to 12 for Dec): ");
				String birthDate = in.next();
				String sMonth = birthDate.substring(2,4);
				try{
					 month = Integer.parseInt(sMonth);
				}
				catch (Exception exc){
					System.out.println("Please enter correct birth date (either day, month or year is incorrect ): ");
					birthDate = in.next();
				}
			}
		}
		else{
			   correctMonth = true; 
		}
		return correctMonth;
		
	}// checkMonth
	
	private boolean checkYear (int year){
		
		boolean correctYear = false;
		if(year < 1961){//above retirement  in South Africa
				
				while(year < 1961){
					
					System.out.print("Please re-enter correct birth year (employee is above retirement age): ");
					String birthDate = in.next();
					
					String sYear = birthDate.substring(4);
					try{
						 year = Integer.parseInt(sYear);
					}
					catch (Exception exc){
						System.out.println("Please enter correct birth date (either day, month or year is incorrect ):");
						birthDate = in.next();
					}
					
				}
		}
		else if(year > 2003){ // below legal employment in South Africa
			while(year > 2003){
				
				System.out.print("Please re-enter correct birth year (employee is below employment age): ");
				String birthDate = in.next();
				String sYear = birthDate.substring(4);
				try{
					 year = Integer.parseInt(sYear);
				}
				catch (Exception exc){
					System.out.println("Please enter correct birth date (either day, month or year is incorrect ): ");
					birthDate = in.next();
				}
			}
		}
		else {
			
			correctYear = true;
			
		}
		
		return correctYear;
	}//checkYear 
	
	
	private String readFile (String file){	
		
		File myFile = new File(file);
        String text = "";
		try{
            Scanner input = new Scanner(myFile);
            while(input.hasNextLine()){
                text += input.nextLine() + "\r\n";
            }
            input.close();    
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
            System.out.println("Bad exception!");
		}
		return text;
	}// readFile
	
	
	private void addToList (){	//adds Employee records. Loads data from "employees.csv" to employeeList													
		String txt = readFile(this.fileName);
		String[] num = txt.split("\r\n");
		for(int i = 0; i < num.length; i++){
			String[] items = num[i].split(",");
			
			Employee newEmployee2 = new Employee( items[0] , items[1] , items[2] , Integer.parseInt(items[3] ), Integer.parseInt(items[4] ),items[5], Integer.parseInt(items[6] ));
			try{
			  employee.add(newEmployee2);
			
			}
			catch (Exception exc){
				System.out.println("Duplicate employee record found.");
				
			}
				
		}
	}//addToList
	
	
    //MAIN MENU FOR ACCESSING OPERATIONS
	public void doRecords(){	
	        addToList();
			System.out.println(" ");
			System.out.println("\nWelcome to XYZ employee records!");
			System.out.println(" ");
			System.out.println(" ");
			
			while (true){
				
				System.out.println("Menu:");
				System.out.println("[1] Create new employee record");
				System.out.println("[2] Query employee record(s)");
				System.out.println("[3] Show employee record(s)");
				System.out.println("[4] Update employee personal detail(s)"); 
				System.out.println("[5] Show employee structure");
				System.out.println("[6] Delete employee record(s)");
				System.out.println("[q] Quit");
				System.out.print("Enter your option: ");
				String cmd = in.next();
				switch (cmd){
					case "1":
						createEmpl ();
						break;
					case "2":
						queryEmployeeRecords();
						break;
					case "3":
					    printEmpoyeeRecords();
						break;
					case "4":
						editEmployeeRecords();
						break;
					case "5":
					    listCompanyStructure();
						break;
					case "6":
					    deleteEmployee();
						break;
					case "Q": case "q":
						return;
					default:
						System.out.printf("Command %s not valid.  Please re-enter...", cmd);
						System.out.println();
						break;
				}// switch
			}
		}//Menu

    
    //MAIN METHOD
	public static void main (String[] args){
			Company RUNC = new Company();
			
			
			
			RUNC.doRecords();
    }//main
}
