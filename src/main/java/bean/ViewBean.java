package bean;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "viewBean")
@SessionScoped
public class ViewBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String currentPage;

    public ViewBean() {
        this.currentPage = "login"; 
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }
    
    @PostConstruct
    public void init(){
        this.currentPage = "login";
    }
    
    public void navigateTo(String content) {
        switch (content) {
            case "create":
            case "delete":
            case "update":
            case "login":
                setCurrentPage(content);
                break;
            default:
                setCurrentPage("index");
                break;
        }
    }
}
