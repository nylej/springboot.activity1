package com.springboot.people;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@Path("/people")
public class PeopleResource {

	List<PeopleRequest> peopleList = new ArrayList<>();
	DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response addPerson(PeopleRequest peopleRequest) {
		
		if(StringUtils.isEmpty(peopleRequest.getFirstName()) || StringUtils.isEmpty(peopleRequest.getLastName())||StringUtils.isEmpty(peopleRequest.getBirthDate())) {
			return Response.status(Response.Status.BAD_REQUEST).entity("400 BAD REQUEST").type(MediaType.TEXT_HTML).build();
		}
		try {
			Date bDate = new Date(peopleRequest.getBirthDate());
			//String birthDate = df.format());
			peopleRequest.setBirthDate(df.format(bDate).replaceAll("/", "-"));
			peopleList.add(peopleRequest);
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity("400 BAD REQUEST").type(MediaType.TEXT_HTML).build();		
		}
		
		
		
		return Response.ok().entity(Response.Status.CREATED).entity("201 CREATED").type(MediaType.TEXT_HTML).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response person() {
		return Response.ok().entity(peopleList).build();
	}
}
