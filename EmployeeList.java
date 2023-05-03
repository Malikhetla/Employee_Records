import java.util.*;
import java.lang.Comparable;

public class EmployeeList{
	private LinkedList<Employee> employee;
	
	public EmployeeList (){
		this.employee = new LinkedList<>();
	} //constructor
	
	public void add (Employee empl) throws Exception{
		boolean contain = false;
		for(int i = 0; i < employee.size(); i++){
			Employee details = employee.get(i);
			int n = details.getEmployeeNumber();
			if(n == (empl.getEmployeeNumber())){
				contain = true;
				break;
			}
		}
		
		if(contain == false){
			employee.add(empl);
		}
		else{
			throw new Exception("Duplicate employee found.");
		}
	}// add
	
	public void remove (Employee empl){
		employee.remove(empl);
	}// remove
	
	public Employee getEmployeeK (int k){
		return employee.get(k);
	}// getEmployee
	
	public int getNumEmployees (){
		return employee.size();
	}// getNumEmployees
	
	
	public Employee getEmployee (int employeeNumber){
		Employee empl = null;
		for(int i = 0; i < employee.size(); i++){
			Employee details = employee.get(i);
			int name = details.getEmployeeNumber();
			if(name == (employeeNumber)){
				empl = details;
				break;
			}
		}
		return empl;
	}// getEmployeeByNumber
	

	public List<Employee> getEmployeeSalary (int employeeSalary){
		List<Employee> empl = new LinkedList<>();
		for(int i = 0; i < employee.size(); i++){
			Employee details = employee.get(i);
			int name = details.getSalary();
			if(name == (employeeSalary)){
				empl.add(details);
			}
		}
		return empl;
	}// getEmployeeSalary
	
	
	public List<Employee> getEmployeeSupervisor (int employeeSupervisor){
		List<Employee> empl = new LinkedList<>();
		for(int i = 0; i < employee.size(); i++){
			Employee details = employee.get(i);
			int name = details.getSupervisor();
			if(name == (employeeSupervisor)){
				empl.add(details);
			}
		}
		return empl;
	}	
	public List<Employee> getEmployeeForName (String employeeName){
		List<Employee> empl = new LinkedList<>();
		for(int i = 0; i < employee.size(); i++){
			Employee details = employee.get(i);
			String name = details.getName();
			if(name.contains(employeeName)){
				empl.add(details);
			}
		}
		return empl;
	}// getEmployeeForName
	
	
	public List<Employee> getEmployeeForSurname (String employeeSurname){
		List<Employee> empl = new LinkedList<>();
		for(int i = 0; i < employee.size(); i++){
			Employee details = employee.get(i);
			String name = details.getSurname();
			if(name.contains(employeeSurname)){
				empl.add(details);
			}
		}
		return empl;
	}// getEmployeeForSurname
	
	public List<Employee> getEmployeeBirthDate (String employeeBirthDate){
		List<Employee> empl = new LinkedList<>();
		for(int i = 0; i < employee.size(); i++){
			Employee details = employee.get(i);
			String name = details.getDateOfBirth();
			if(name.contains(employeeBirthDate)){
				
				empl.add(details);
			}
		}
		return empl;
	}// getEmployeeBirthDate
	
	public List<Employee> getEmployeeRole (String employeeRole){
		List<Employee> empl = new LinkedList<>();
		for(int i = 0; i < employee.size(); i++){
			Employee details = employee.get(i);
			String name = details.getEmployeeRoleDescription();
			if(name.contains(employeeRole)){
				empl.add(details);
			}
		}
		return empl;
	}	// getEmployeeRole
	
	
	
	
}