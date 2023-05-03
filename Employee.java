import java.util.Comparator;

public class Employee implements Comparable<Employee>{
	private String name;
	private String surname;
	private String dateOfBirth; 
	private int employeeNumber;
	private int salary;
	private String roleDescription;
	private int supervisor; //reportingLine


	public Employee (String name, String surname, String dateOfBirth, int employeeNumber, int salary, String roleDescription, int supervisor){
		this.name = name;
		this.surname = surname;
		this.dateOfBirth = dateOfBirth;
		this.employeeNumber = employeeNumber;
		this.salary = salary;
		this.roleDescription = roleDescription;
		this.supervisor = supervisor;
	}// constructor
	
	
	public String getName (){
		return name;
	}//getName
	
	public String setName (String name){
		return this.name;
	}//getName
	
	
	public String getSurname (){
		return this.surname;
	}// getSurname
	
	public String getDateOfBirth (){
		return this.dateOfBirth;
	}// getDateOfBirth
	
	public int getEmployeeNumber(){
		return this.employeeNumber;
	}// getEmployeeNumber
	
	public int getSalary(){
		return this.salary;
	}// getSalary
	
	
	public String getEmployeeRoleDescription(){
		return this.roleDescription;
	}// getEmployeeRoleDescription
	
	public int getSupervisor(){
		return this.supervisor;
	}//getSupervisor
	
	
	public String toString (){
		return name + " " + surname + ", " + dateOfBirth + ", " + employeeNumber + ", " + salary + ", " + roleDescription + ", " + supervisor;
	}// toString
	
	public int compareTo(Employee compareSalary) {
	
		int compareAmount = (int)compareSalary.getSalary(); 
		
		//ascending order
		//return this.salary - (double)compareAmount;
		
		//descending order
		return compareAmount - (int)this.salary;
		
	}

	
}