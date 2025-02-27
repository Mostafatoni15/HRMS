package dao;

import bean.Employee;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EmployeeDao {

    Transaction ts = null;

    public void createEmployee(Employee emp) {
        Session se = NewHibernateUtil.getSessionFactory().openSession();
        try {
            ts = se.beginTransaction();
            se.save(emp);
            se.flush();
            ts.commit();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Employee Added Successfully ");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (RuntimeException e) {
            if (ts != null) {
                ts.rollback();
            }
            e.printStackTrace();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Failed to Add Employee");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } finally {
            se.close();
        }
    }

    public void deleteEmployee(int id) {
        Session se = NewHibernateUtil.getSessionFactory().openSession();
        try {
            ts = se.beginTransaction();
            Employee emp = (Employee) se.get(Employee.class, id);
            if (emp != null) {
                se.delete(emp);
                se.flush();
                ts.commit();
            }
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Employee Deleted Successfully ");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (RuntimeException e) {
            if (ts != null) {
                ts.rollback();
            }
            e.printStackTrace();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Failed to Delete Employee");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } finally {
            se.close();
        }
    }

    public void updateEmployee(Employee emp) {
        Session se = NewHibernateUtil.getSessionFactory().openSession();
        try {
            ts = se.beginTransaction();
            se.update(emp);
            se.flush();
            ts.commit();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Employee Updated Successfully ");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (RuntimeException e) {
            if (ts != null) {
                ts.rollback();
            }
            e.printStackTrace();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Failed to Update Employee");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } finally {
            se.close();
        }
    }

    public Employee searchUser(Employee emp) {
        Session session = null;
        Employee foundEmployee = null;

        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            String sql = "FROM Employee WHERE email = :email";
            Query query = session.createQuery(sql);
            query.setParameter("email", emp.getEmail());

            List<Employee> results = query.list();

            if (!results.isEmpty()) {
                foundEmployee = (Employee) results.get(0);
            }

        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return foundEmployee;
    }

    public List<Employee> readAllEmployees() {
        Session se = NewHibernateUtil.getSessionFactory().openSession();
        List<Employee> employeesList = null;
        try {
            employeesList = se.createQuery("FROM Employee").list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            se.close();
        }
        return employeesList;
    }

}
