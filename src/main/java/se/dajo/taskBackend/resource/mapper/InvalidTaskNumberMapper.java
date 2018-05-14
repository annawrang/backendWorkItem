package se.dajo.taskBackend.resource.mapper;

import se.dajo.taskBackend.service.exception.InvalidTaskNumberException;
import se.dajo.taskBackend.service.exception.InvalidUserNumberException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static java.util.Collections.singletonMap;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

@Provider
public final class InvalidTaskNumberMapper implements ExceptionMapper<InvalidTaskNumberException> {
    @Override
    public Response toResponse(InvalidTaskNumberException exception) {
        return Response.status(BAD_REQUEST).entity(singletonMap("Invalid taskNumber input", exception.getMessage())).build();
    }
}
