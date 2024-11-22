package tria.controladores;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import tria.entidades.Bateria;
import tria.servicos.BateriaServico;

import java.util.List;
import java.util.Optional;

@Path("baterias")
public class BateriaResource {

    private final BateriaServico bateriaServico = new BateriaServico();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Bateria> getBaterias() {
        return bateriaServico.Listar();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBateriaById(@PathParam("id") int id) {
        Optional<Bateria> bateria = bateriaServico.BuscarPorId(id);
        return bateria.map(value -> Response.ok(value).build())
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND).entity("Bateria não encontradA").build());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBateria(Bateria bateria) {
        bateriaServico.Cadastrar(bateria);
        return Response.status(Response.Status.CREATED).entity(bateria).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBateria(@PathParam("id") int id, Bateria bateria) {
        Optional<Bateria> _bateria = bateriaServico.BuscarPorId(id);
        if (_bateria.isPresent()) {
            bateriaServico.Atualizar(bateria, id);
            return Response.ok(bateria).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteBateria(@PathParam("id") int id) {
        Optional<Bateria> bateria = bateriaServico.BuscarPorId(id);
        if (bateria.isPresent()) {
            bateriaServico.Deletar(id);
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}