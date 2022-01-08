package si.fri.rsoteam.api.v1.resources;

import com.kumuluz.ee.logs.LogManager;
import com.kumuluz.ee.logs.Logger;
import com.kumuluz.ee.logs.cdi.Log;
import com.kumuluz.ee.logs.cdi.LogParams;
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

@ApplicationScoped
@Path("/activities")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ActivitiesResource {

    private Logger log = LogManager.getLogger(ActivitiesResource.class.getName());

    @Inject
    private ActivityBean activityBean;

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
    @Log(LogParams.METRICS)
    public Response getActivities() {
        log.warn("Hey, somebody requested all activities");
        return Response.ok(activityBean.getAllActivity()).build();
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
    @Log(LogParams.METRICS)
    public Response getActivitiesById(@PathParam("objectId") Integer id) {
        return Response.ok(activityBean.getActivity(id)).build();
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
    @Log(LogParams.METRICS)
    public Response createActivities(ActivityDto ActivityDto) {
        return Response.status(201).entity(activityBean.createActivity(ActivityDto)).build();
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
    @Log(LogParams.METRICS)
    public Response updateActivities(@PathParam("objectId") Integer id, ActivityDto eventDto) {
        return Response.status(201).entity(activityBean.updateActivity(eventDto, id)).build();
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
    @Log(LogParams.METRICS)
    public Response deleteEvent(@PathParam("objectId") Integer id) {
        activityBean.deleteActivity(id);
        return Response.status(204).build();
    }

    @DELETE
    @Path("user/{userId}")
    @Operation(summary = "Delete activities for specific user.", description = "Delete activities for specific user.")
    @APIResponses({
            @APIResponse(
                    description = "Successfully deleted activity.",
                    responseCode = "204",
                    content = @Content(schema = @Schema(name = "none"))
            )
    })
    @Log(LogParams.METRICS)
    public Response deleteForUser(@PathParam("userId") Integer userId) {
        activityBean.deleteActivitiesForUser(userId);
        return Response.status(204).build();
    }
}
