/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.EmployeeDTO;
import entities.Employee;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Marks
 */
public class EmployeeFacade {
    
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    private static EntityManager em = emf.createEntityManager();
    private static EmployeeFacade instance;
    
    public static void main(String[] args) {
        
         EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu"); 
         EmployeeFacade facade = EmployeeFacade.getEmployeeFacade(emf);
    Employee b1 = facade.createEmployee("Ja","Nej",1);
    //Employee b2 = facade.createEmployee("Author 2");
    //Find book by ID
    System.out.println("Book1: "+EmployeeFacade.findEmployee(b1.getId()).getName());
    //System.out.println("Book2: "+facade.findBook(b2.getId()).getAuthor());
    //Find all books
    System.out.println("Number of Employees: "+facade.getAllEmployee().size());

        
    }
    
    public EmployeeDTO findEmployee(Long id){
         EntityManager em = emf.createEntityManager();
        try{
            Employee e2 = em.find(Employee.class,id);
             EmployeeDTO e1 = new EmployeeDTO(e2);
            return e1;
        }finally {
            em.close();
        }
    }
    
    public static EmployeeFacade getEmployeeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EmployeeFacade();
        }
        return instance;
    }

    
    public static Employee createEmployee(String name, String address, int bdm){
       Employee employee = new Employee(name, address, bdm);
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(employee);
            em.getTransaction().commit();
            return employee;
        }finally {
            em.close();
        }
    }
    
    public static Employee findEmployee(int id){
         EntityManager em = emf.createEntityManager();
        try{
            Employee employee = em.find(Employee.class,id);
            return employee;
        }finally {
            em.close();
        }
    }
    
    public static Employee findEmployeeName(String name){
         EntityManager em = emf.createEntityManager();
        try{
            Employee employee = em.find(Employee.class,name);
            return employee;
        }finally {
            em.close();
        }
    }
    
    public static List<Employee> getAllEmployee(){
         EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Employee> query = 
                       em.createQuery("Select employee from Employee employee",Employee.class);
            return query.getResultList();
        }finally {
            em.close();
        }
    }
    
    public Employee getHighestSalary (){
             EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Employee> query = 
                       em.createQuery("Select e From Employee e where e.salary = MAX(e.salary) FROM Employee e",Employee.class);
            return query.getSingleResult();
        }finally {
            em.close();
        }
    }
   
    
}
