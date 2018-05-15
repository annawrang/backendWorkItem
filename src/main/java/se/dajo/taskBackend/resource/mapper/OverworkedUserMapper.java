package se.dajo.taskBackend.resource.mapper;

import se.dajo.taskBackend.service.exception.OverworkedUserException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static java.util.Collections.singletonMap;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

@Provider
public final class OverworkedUserMapper implements ExceptionMapper<OverworkedUserException> {
    @Override
    public Response toResponse(OverworkedUserException exception) {
        return Response.status(BAD_REQUEST).entity(singletonMap("Error", exception.getMessage())).build();
    }
}
