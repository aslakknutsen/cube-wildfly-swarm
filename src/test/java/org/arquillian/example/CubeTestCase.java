package org.arquillian.example;

import java.io.File;
import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.UriBuilder;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class CubeTestCase {

   @Deployment(testable = false)
   public static JavaArchive deploy() {
/*
      return ShrinkWrap.create(WildFlySwarmImporter.class)
            .loadPomFromFile(new File("pom.xml"))
            .importBuildOutput().as(JavaArchive.class);
*/
      return ShrinkWrap.createFromZipFile(
            JavaArchive.class,
            new File("target/cube-wildfly-swarm-1.0.0-SNAPSHOT-swarm.jar"));
   }

   @ArquillianResource
   private URI uri;

   @Test
   public void shouldX() throws Exception {
      Thread.sleep(300); // Seems cube is returning to fast? Swarm responding on 8080 before service is deployed?

      Client client = ClientBuilder.newClient();
      WebTarget target = client.target(UriBuilder.fromUri(uri).path("api").path("test"));
      String response = target.request().get(String.class);

      Assert.assertEquals("{\"test2\": true}", response);
   }
}
