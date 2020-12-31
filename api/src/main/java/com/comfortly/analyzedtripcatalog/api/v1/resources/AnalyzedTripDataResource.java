package com.comfortly.analyzedtripcatalog.api.v1.resources;

import com.comfortly.analyzedtripcatalog.lib.AnalyzedTripData;
import com.comfortly.analyzedtripcatalog.lib.SummaryAnalyzedTripData;
import com.comfortly.analyzedtripcatalog.services.beans.AnalyzedTripDataBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
@Path("/analyzed-trips")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AnalyzedTripDataResource {

    private Logger log = Logger.getLogger(AnalyzedTripDataResource.class.getName());

    @Inject
    private AnalyzedTripDataBean analyzedTripDataBean;

    @Context
    protected UriInfo uriInfo;

    @GET
    @Path("/summary")
    public Response getAnalyzedTripData(@HeaderParam("UserId") String userId) {

        if (userId == null) {
            return Response.status(Response.Status.BAD_REQUEST.getStatusCode(), "Missing UserId header").build();
        }
        List<SummaryAnalyzedTripData> tripData = analyzedTripDataBean.getAnalyzedTripDataByUser(userId);

        return Response.status(Response.Status.OK).entity(tripData).build();
    }

    @GET
    @Path("/{analyzedTripDataId}")
    public Response getAnalyzedTripData(@HeaderParam("UserId") String userId, @PathParam("analyzedTripDataId") Integer analyzedTripDataId) {

        if (userId == null) {
            return Response.status(Response.Status.BAD_REQUEST.getStatusCode(), "Missing UserId header").build();
        }

        AnalyzedTripData tripData = analyzedTripDataBean.getAnalyzedTripData(analyzedTripDataId);

        if (tripData == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        if (!tripData.getUserId().equals(userId)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        return Response.status(Response.Status.OK).entity(tripData).build();
    }

    @DELETE
    @Path("{analyzedTripDataId}")
    public Response deleteAnalyzedTripData(@PathParam("tripDataId") Integer analyzedTripDataId) {

        boolean deleted = analyzedTripDataBean.deleteAnalyzedTripData(analyzedTripDataId);

        if (deleted) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
