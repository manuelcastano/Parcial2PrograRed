package services;

import model.dto.Response;
import model.dto.TareaDTO;
import model.provider.TareaProvider;

import javax.ejb.Stateless;
import javax.ws.rs.*;
import java.util.ArrayList;

@Stateless
@Path("tarea")
public class TareaService {

    @POST
    @Path("nuevaTarea")
    @Consumes("application/json")
    @Produces("application/json")
    public Response nuevaTarea(TareaDTO tarea){
        TareaProvider tareaProvider = new TareaProvider();
        tareaProvider.insertTarea(tareaProvider.mapFromDTO(tarea));
        return new Response("Operacion exitosa");
    }

    @GET
    @Path("all")
    @Produces("application/json")
    public ArrayList<TareaDTO> getAllTareas(){
        TareaProvider tareaProvider = new TareaProvider();
        ArrayList<TareaDTO> tareas = tareaProvider.getAllTareas();
        return tareas;
    }

    @DELETE
    @Path("delete/{id}")
    @Produces("application/json")
    public Response deleteTarea(@PathParam("id") String id){
        TareaProvider tareaProvider = new TareaProvider();
        boolean success = tareaProvider.deleteTarea(Integer.parseInt(id));
        if(success){
            return new Response("Operacion exitosa");
        } else{
            return new Response("Operacion fallida");
        }
    }

    @PUT
    @Path("cambiarSeccion")
    @Produces("application/json")
    public Response changeSeccion(@QueryParam("id") String id, @QueryParam("seccion") String seccion){
        TareaProvider tareaProvider = new TareaProvider();
        boolean success = tareaProvider.changeSeccion(Integer.parseInt(id), Integer.parseInt(seccion));
        if(success){
            return new Response("Operacion exitosa");
        } else{
            return new Response("Operacion fallida");
        }
    }
}
