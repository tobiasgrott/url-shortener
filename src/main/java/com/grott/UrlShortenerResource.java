package com.grott;

import java.net.URI;
import java.net.URISyntaxException;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("alias")
public class UrlShortenerResource {
    @Inject
    UrlShortenerService service;

    @GET
    @Path("{alias}")
    public Response redirect(@PathParam("alias") String alias) throws URISyntaxException {
        String url = service.getUrl(alias);
        if (url == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Url not found").build();
        }
        return Response.temporaryRedirect(new URI(url)).build();
    }

    @DELETE
    @Path("{alias}")
    public Response deleteAlias(@PathParam("alias") String alias){
        service.removeShortUrl(alias);
        return Response.noContent().build();
    }

    @POST
    public Response createAlias(DtoAlias input){
        String key = "";
        if(input.getKey()==null){
            key = service.addShortUrl(input.getUrl());
        }else {
            key = service.addShortUrl(input.getKey(),input.getUrl());
        }
        DtoAlias retval = new DtoAlias();
        retval.setKey(key);
        retval.setUrl(input.getUrl());
        return Response.ok(retval).build();
    }
}