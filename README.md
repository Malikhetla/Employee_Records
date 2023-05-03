# Employee_Records

Employee_Records is a record keeping program for a small imagined startup business that enables employees to access their details for time planning, role assignment and other tasks. The program is implemented in Java and allows registration of employee data, searching of employee records by names, date of birth and other fields and provides details on the line of supervision for employees. 

- The Employee class represents the employee. This class stores details of the employee which are Name, Surname, Employee Number, Birth date, Role in the Company, Salary and Supervisor in the role. This is the Employee.java file

- The EmployeeList class manages the list of employees. This has a method to add another employee (Employee object) to the list of employee and checks (throws an exception) if an employee with the same number already exists in the list. This class can also remove an employee from the list, retrieve an employee given their position (index) in the list or using their employee number and return the number of employees. The remainder of the methods use a linked list to retrieve details of an employee based on their Name. Surname, Role, Supervisor, Birth date and Salary. This is the EmployeeList.java file

- The Company class runs the program that the user interacts with. This class make use of two items; an EmployeeList named “employee” and a csv file named “employees.csv”. When the program is run, employee records stored in the csv are loaded into the EmployeeList which will then allow the user to do the following operations;
1. Create a new employee record
2. Query employee records
3. Show employee records
4. Update employee records
5. Display employee hierarchy  
6. Delete an employee record. This is the Company.java file

- The employees.csv file stored the list of employees when the program is closed. The contents in this file are updated and modified when the program is running.


Download and install the Java Development Kit from here https://www.oracle.com/java/technologies/downloads/ if it is not already installed.


Using the program:
1. Compile all the files 

From the command prompt (Windows), or Terminal (Linux and MacOS) compile as follows

    $ javac Employee.java

    $ javac EmployeeList.java

    $ javac Company.java


3. Run the program

   $ java Company


