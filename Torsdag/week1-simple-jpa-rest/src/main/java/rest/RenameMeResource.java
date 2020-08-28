package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.RenameMe;
import facades.FacadeExample;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import entities.Employee;
import facades.EmployeeFacade;

@Path("employee")
public class RenameMeResource {
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    //NOTE: Change Persistence unit name according to your setup
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu"); 
    FacadeExample facade =  FacadeExample.getFacadeExample(emf);

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"succes\"}";
    }
    
    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllEmployees() {
        return Response.ok().entity(gson.toJson((EmployeeFacade.getAllEmployee()))).build();
    }
    
    @GET
    @Path("animal_list")
    @Produces(MediaType.APPLICATION_JSON)
    
    public String getJson2 (){
        
        return "[\"Dog\", \"Cat\", \"Mouse\", \"Bird\"]";
    }
    

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(RenameMe entity) {
        throw new UnsupportedOperationException();
    }
    
    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmplyeeById(@PathParam("id")int id) {
         return Response.ok().entity(gson.toJson(EmployeeFacade.findEmployee(id))).build();
    }
    
    @Path("/name{name}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmplyeeByString(@PathParam("Name")String name) {
         return Response.ok().entity(gson.toJson(EmployeeFacade.findEmployeeName(name))).build();
    }
}
