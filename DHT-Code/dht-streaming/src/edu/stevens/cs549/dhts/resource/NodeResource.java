package edu.stevens.cs549.dhts.resource;

import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.glassfish.jersey.media.sse.SseFeature;

@Path("/dht")
public class NodeResource {

	/*
	 * Web service API.
	 * 
	 * TODO: Fill in the missing operations.
	 */

	Logger log = Logger.getLogger(NodeResource.class.getCanonicalName());

	@Context
	UriInfo uriInfo;

	@Context
	HttpHeaders headers;

	@GET
	@Path("info")
	@Produces("application/json")
	public Response getNodeInfo() {
		return new NodeService(headers, uriInfo).getNodeInfo();
	}

	@GET
	@Path("pred")
	@Produces("application/json")
	public Response getPred() {
		return new NodeService(headers, uriInfo).getPred();
	}

	@PUT
	@Path("notify")
	@Consumes("application/json")
	@Produces("application/json")
	/*
	 * Actually returns a TableRep
	 */
	public Response putNotify(TableRep predDb) {
		/*
		 * See the comment for WebClient::notify (the client side of this logic).
		 */
		return new NodeService(headers, uriInfo).notify(predDb);
	}

	@GET
	@Path("find")
	@Produces("application/json")
	public Response findSuccessor(@QueryParam("id") String index) {
		int id = Integer.parseInt(index);
		return new NodeService(headers, uriInfo).findSuccessor(id);
	}
	
	@GET
	@Path("listen")
	@Produces(SseFeature.SERVER_SENT_EVENTS)
	public Response listenForBindings() {
		return null;
	}

}
