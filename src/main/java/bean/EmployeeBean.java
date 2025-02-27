package bean;

import dao.EmployeeDao;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "employee")
@ViewScoped
public class EmployeeBean {

    private EmployeeDao empDao = new EmployeeDao();
    private Employee selectedEmp;
    private List<Employee> employees;

    @PostConstruct
    public void init() {
        employees = empDao.readAllEmployees();
        selectedEmp = new Employee();
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public Employee getSelectedEmp() {
        return selectedEmp;
    }

    public void setSelectedEmp(Employee selectedEmp) {
        this.selectedEmp = selectedEmp;
    }

    public ArrayList<Employee> allEmps() {
        ArrayList<Employee> result = (ArrayList<Employee>) empDao.readAllEmployees();
        return result;
    }

    public void newEmp() {
        if (selectedEmp != null){
        empDao.createEmployee(selectedEmp);
        refreshEmployees();
        clearAll();
        }
    }

    public void deleteEmp(int id) {
        empDao.deleteEmployee(id);
        refreshEmployees();
    }

    public void saveUpdatedEmp() {
        empDao.updateEmployee(selectedEmp);
        refreshEmployees();
        clearAll();
    }

    public void updateEmployee(Employee emp) {
        this.selectedEmp = emp;
    }

    public void refreshEmployees() {
        employees = empDao.readAllEmployees();
    }

    private void clearAll() {
        selectedEmp = new Employee();
    }

}
