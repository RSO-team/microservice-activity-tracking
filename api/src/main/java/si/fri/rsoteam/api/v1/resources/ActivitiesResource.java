package si.fri.rsoteam.api.v1.resources;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import si.fri.rsoteam.lib.dtos.ActivityDto;
import si.fri.rsoteam.services.beans.ActivityBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.logging.Logger;

@ApplicationScoped
@Path("/activities")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ActivitiesResource {

    private Logger log = Logger.getLogger(ActivitiesResource.class.getName());

    @Inject
    private ActivityBean ActivityBean;

    @Context
    protected UriInfo uriInfo;

    @GET
    @Operation(summary = "Returns list of all activities.", description = "Returns list of all activities..")
    @APIResponses({
            @APIResponse(
                    description = "Successfully returned all activities.",
                    responseCode = "200",
                    content = @Content(schema = @Schema(implementation = ActivityDto.class)),
                    headers = {@Header(name = "X-Total-Count", description = "Number of objects in list")}

            )
    })
    public Response getActivities() {
        return Response.ok(ActivityBean.getAllActivity()).build();
    }

    @GET
    @Path("/{objectId}")
    @Operation(summary = "Gets specific activity by id.", description = "Returns specific activity.")
    @APIResponses({
            @APIResponse(
                    description = "Successfully returned specific activities.",
                    responseCode = "200",
                    content = @Content(schema = @Schema(implementation = ActivityDto.class))
            )
    })
    public Response getActivitiesById(@PathParam("objectId") Integer id) {
        return Response.ok(ActivityBean.getActivity(id)).build();
    }

    @POST
    @Operation(summary = "Create new activity.", description = "Create new activity.")
    @APIResponses({
            @APIResponse(
                    description = "Successfully created new activity.",
                    responseCode = "201",
                    content = @Content(schema = @Schema(implementation = ActivityDto.class))
            )
    })
    public Response createActivities(ActivityDto ActivityDto) {
        return Response.status(201).entity(ActivityBean.createActivity(ActivityDto)).build();
    }

    @PUT
    @Path("{objectId}")
    @Operation(summary = "Update specific activity.", description = "Update specific activity by id.")
    @APIResponses({
            @APIResponse(
                    description = "Successfully updated activity.",
                    responseCode = "201",
                    content = @Content(schema = @Schema(implementation = ActivityDto.class))
            )
    })
    public Response updateActivities(@PathParam("objectId") Integer id, ActivityDto eventDto) {
        return Response.status(201).entity(ActivityBean.updateActivity(eventDto, id)).build();
    }

    @DELETE
    @Path("{objectId}")
    @Operation(summary = "Delete specific activity.", description = "Delete specific activity by id.")
    @APIResponses({
            @APIResponse(
                    description = "Successfully deleted activity.",
                    responseCode = "201",
                    content = @Content(schema = @Schema(implementation = ActivityDto.class))
            )
    })
    public Response deleteEvent(@PathParam("objectId") Integer id) {
        ActivityBean.deleteActivity(id);
        return Response.status(204).build();
    }
}
