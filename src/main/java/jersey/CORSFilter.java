package jersey;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class CORSFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext request, ContainerResponseContext response) throws IOException {
        response.getHeaders().add("Access-Control-Allow-Origin", "*");
        response.getHeaders().add("Access-Control-Allow-Headers", "Authorization, Origin, X-Requested-With, Content-Type");
        response.getHeaders().add("Access-Control-Expose-Headers", "Location, Content-Disposition");
        response.getHeaders().add("Access-Control-Allow-Methods", "POST, PUT, GET, DELETE, HEAD, OPTIONS");
    }
}
