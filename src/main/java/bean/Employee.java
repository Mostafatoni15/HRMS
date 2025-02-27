
package bean;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;


@Entity
@Table(name = "person")
public class Employee implements Serializable {
    
    private int id;
    private String empName;
    private String address;
    private String email;
    private String department;
    private String password;
    private int salary;
    
    public Employee() {
    }

    public Employee(int id, String empName, String address, String email, String department, int salary) {
        this.id = id;
        this.empName = empName;
        this.address = address;
        this.email = email;
        this.department = department;
        this.salary = salary;
    }
    
@Id
@TableGenerator(name = "id" , allocationSize = 1)
@GeneratedValue(generator = "id", strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
     
    
}
