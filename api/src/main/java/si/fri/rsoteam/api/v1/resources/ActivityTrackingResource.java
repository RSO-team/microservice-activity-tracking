package si.fri.rsoteam.api.v1.resources;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import si.fri.rsoteam.dtos.ActivityPointDto;
import si.fri.rsoteam.services.beans.ActivityTrackingBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
@Path("/activity-tracking")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ActivityTrackingResource {

    private Logger log = Logger.getLogger(ActivityTrackingResource.class.getName());

    @Inject
    private ActivityTrackingBean bean;

    @Context
    protected UriInfo uriInfo;

    @GET
    @Operation(summary = "Get list of activity points", description = "Returns list of activity points.")
    @APIResponses({
            @APIResponse(
                    description = "Activity point list",
                    responseCode = "200",
                    content = @Content(schema = @Schema(implementation = ActivityPointDto.class, type = SchemaType.ARRAY)),
                    headers = {@Header(name = "X-Total-Count", description = "Number of objects in list")}
            )
    })
    public Response getObjects() {
        List<ActivityPointDto> list = bean.getList();
        return Response.ok(list).build();
    }

    @GET
    @Operation(summary = "Get activity point by given id", description = "Returns activity point")
    @APIResponses({
            @APIResponse(
                    description = "Activity point details",
                    responseCode = "200",
                    content = @Content(schema = @Schema(implementation = ActivityPointDto.class))
            )
    })
    @Path("/{id}")
    public Response getObjectById(@PathParam("id") Integer id) {
        ActivityPointDto object = bean.get(id);
        if (object == null) {
            throw new NotFoundException("Object not found");
        }
        return Response.ok(object).build();
    }

    @POST
    @Operation(summary = "Creates new activity point and returns it", description = "Returns new user details.")
    @APIResponses({
            @APIResponse(
                    description = "User details",
                    responseCode = "201",
                    content = @Content(schema = @Schema(implementation = ActivityPointDto.class))
            )
    })
    public Response createObject(ActivityPointDto dto) {
        return Response.ok(bean.create(dto)).build();
    }

    @POST
    @Path("list")
    @Operation(summary = "Creates new activity points and returns them", description = "Creates a set of activity points.")
    @APIResponses({
            @APIResponse(
                    description = "List of activity points",
                    responseCode = "201",
                    content = @Content(schema = @Schema(implementation = ActivityPointDto.class, type = SchemaType.ARRAY)),
                    headers = {@Header(name = "X-Total-Count", description = "Number of objects in list")}
            )
    })
    public Response createListObjects(List<ActivityPointDto> dtos) {
        List<ActivityPointDto> createdDtos = new ArrayList<>();
        for (ActivityPointDto apd : dtos) {
            createdDtos.add(bean.create(apd));
        }
        return Response.ok(createdDtos).build();
    }

    @PUT
    @Operation(summary = "Updates activity point and returns it", description = "Returns activity point.")
    @APIResponses({
            @APIResponse(
                    description = "Activity point details",
                    responseCode = "201",
                    content = @Content(schema = @Schema(implementation = ActivityPointDto.class))
            )
    })
    @Path("{objectId}")
    public Response updateObjectById(@PathParam("objectId") Integer id,
                                     ActivityPointDto object) {
        return Response.status(201).entity(bean.update(object, id)).build();
    }

    @DELETE
    @Operation(summary = "Deletes specified object", description = "Returns no content.")
    @APIResponses({
            @APIResponse(
                    description = "No content",
                    responseCode = "204"
            )
    })
    @Path("{objectId}")
    public Response deleteObject(@PathParam("objectId") Integer id) {
        bean.delete(id);
        return Response.noContent().build();
    }
}