package bean;

import dao.EmployeeDao;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "log")
@SessionScoped
public class LoginBean {

    Employee employee = new Employee();
    EmployeeDao empDao = new EmployeeDao();

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void logIn() {
        if ("admin".equals(employee.getPassword()) && "admin".equals(employee.getEmail())) {
            FacesContext context = FacesContext.getCurrentInstance();
            ViewBean viewBean = context.getApplication().evaluateExpressionGet(context, "#{viewBean}", ViewBean.class);
            viewBean.setCurrentPage("index");
        } else {
            Employee loggedInEmployee = empDao.searchUser(employee);
            if (loggedInEmployee != null && loggedInEmployee.getPassword().equals(employee.getPassword()) && loggedInEmployee.getEmail().equals(employee.getEmail())) {
                this.employee = loggedInEmployee;
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("loggedInUser", loggedInEmployee);
                FacesContext context = FacesContext.getCurrentInstance();
                ViewBean viewBean = context.getApplication().evaluateExpressionGet(context, "#{viewBean}", ViewBean.class);
                viewBean.setCurrentPage("home");
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Invalid username or password"));
            }
        }
    }

    public Employee selectOneEmployee() {
        return (Employee) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedInUser");
    }

    public void deleteEmp() {
        if (employee != null) {
            empDao.deleteEmployee(employee.getId());
            employee = new Employee();
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("loggedInUser", employee);
        }
    }

    public void saveProfile() {
        empDao.updateEmployee(employee);
    }

}
