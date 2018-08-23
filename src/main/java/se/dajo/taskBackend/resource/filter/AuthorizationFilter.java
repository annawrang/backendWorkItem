package se.dajo.taskBackend.resource.filter;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
@Authorization
public final class AuthorizationFilter implements ContainerRequestFilter {

    private final String dummypassword = "abc";

    @Override
    public void filter(ContainerRequestContext requestContext) {
        String authorizationHeader =
                requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        if (!dummypassword.equals(authorizationHeader)){
            requestContext.abortWith(Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity("User cannot access the resource.")
                    .build());
        }
    }
}
