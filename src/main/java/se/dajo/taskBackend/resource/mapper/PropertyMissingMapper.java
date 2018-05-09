package se.dajo.taskBackend.resource.mapper;

import se.dajo.taskBackend.service.exception.PropertyMissingException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public final class PropertyMissingMapper implements ExceptionMapper<PropertyMissingException> {

    @Override
    public Response toResponse(PropertyMissingException exception) {
        return null;
    }
}
