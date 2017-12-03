/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import org.glassfish.jersey.server.ResourceConfig;

import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author lucas
 */
@ApplicationPath("webresources")
public class ApplicationConfig extends ResourceConfig {
    public ApplicationConfig() {
        packages("ws", "jersey");
    }
}
