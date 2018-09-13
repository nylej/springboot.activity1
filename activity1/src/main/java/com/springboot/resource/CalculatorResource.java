package com.springboot.resource;

import java.text.DecimalFormat;

import javax.servlet.http.HttpServletResponse;
import javax .ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@Path("/calculator")
public class CalculatorResource {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response calculate(CalculatorRequest request) {
		if (StringUtils.isEmpty(request.getOperator()) || StringUtils.isEmpty(request.getNumber1()) || StringUtils.isEmpty(request.getNumber2())) {
			return Response.status(Response.Status.BAD_REQUEST).entity("400 BAD REQUEST - null input").type(MediaType.TEXT_HTML).build();
		}
		CalculatorResponse calculatorResponse = new CalculatorResponse();
		DecimalFormat df = new DecimalFormat("#.#####");
		try {
			Integer number1 = Integer.parseInt(request.getNumber1());
			Integer number2 = Integer.parseInt(request.getNumber2());
			if (request.getOperator().equals("+")) {
				calculatorResponse.setAction("addition");
				calculatorResponse.setResult(String.valueOf(number1 + number2));

			} else if (request.getOperator().equals("-")) {
				calculatorResponse.setAction("subtraction");
				calculatorResponse.setResult(number1 - number2 + "");
			} else if (request.getOperator().equals("*")) {
				calculatorResponse.setAction("multiplication");
				calculatorResponse.setResult(df.format(number1 * number2));
			} else if (request.getOperator().equals("/")) {
				if (request.getNumber2().equals("0")) {
					return Response.status(Response.Status.BAD_REQUEST).entity("400 BAD REQUEST - unabel to divide in zero").type(MediaType.TEXT_HTML).build();
				} else {
					calculatorResponse.setAction("division");
					Double numberDiv = Double.parseDouble((request.getNumber1()));
					Double number2Div = Double.parseDouble((request.getNumber2()));
					calculatorResponse.setResult(df.format(numberDiv / number2Div).replaceAll(".00000", ""));
				}

			} else {
				return Response.status(Response.Status.BAD_REQUEST).entity("400 BAD REQUEST - invalid operator ").type(MediaType.TEXT_HTML).build();
			}
				return Response.ok().entity(calculatorResponse).build();
		} catch (NumberFormatException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity("400 BAD REQUEST - Invalid input").type(MediaType.TEXT_HTML).build();
	
		}}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response calculate() {
		return Response.ok("OK").status(HttpServletResponse.SC_OK).build();
	}

}
