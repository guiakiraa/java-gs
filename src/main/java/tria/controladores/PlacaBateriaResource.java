package tria.controladores;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import tria.entidades.PlacaBateria;
import tria.servicos.PlacaBateriaServico;

import java.util.List;
import java.util.Optional;

@Path("placasbaterias")
public class PlacaBateriaResource {

    private final PlacaBateriaServico placaBateriaServico = new PlacaBateriaServico();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PlacaBateria> getBaterias() {
        return placaBateriaServico.Listar();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBateriaById(@PathParam("id") int id) {
        Optional<PlacaBateria> placaBateria = placaBateriaServico.BuscarPorId(id);
        return placaBateria.map(value -> Response.ok(value).build())
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND).entity("Placa Bateria não encontradA").build());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBateria(PlacaBateria placaBateria) {
        placaBateriaServico.Cadastrar(placaBateria);
        return Response.status(Response.Status.CREATED).entity(placaBateria).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBateria(@PathParam("id") int id, PlacaBateria placaBateria) {
        Optional<PlacaBateria> _placaBateria = placaBateriaServico.BuscarPorId(id);
        if (_placaBateria.isPresent()) {
            placaBateriaServico.Atualizar(placaBateria, id);
            return Response.ok(placaBateria).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteBateria(@PathParam("id") int id) {
        Optional<PlacaBateria> placaBateria = placaBateriaServico.BuscarPorId(id);
        if (placaBateria.isPresent()) {
            placaBateriaServico.Deletar(id);
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}