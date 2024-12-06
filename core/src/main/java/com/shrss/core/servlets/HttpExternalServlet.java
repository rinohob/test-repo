package com.shrss.core.servlets;
 
import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.shrss.core.services.unityapi.pojo.location.Locations;

import com.google.gson.Gson;
import com.shrss.core.services.unityapi.APIInvoker;
import com.shrss.core.services.unityapi.CIAMService;
import com.shrss.core.services.unityapi.pojo.CIAMTokenResponse;

@Component(service=Servlet.class,enabled = true,
property={
        "sling.servlet.methods={" + HttpConstants.METHOD_GET +',' + HttpConstants.METHOD_POST +'}',
        "sling.servlet.paths=" + "/bin/shrss/getData"
})
public class HttpExternalServlet extends SlingAllMethodsServlet {
	 private static final long serialVersionUID = 1L;

    private static final Logger log = LoggerFactory.getLogger(HttpExternalServlet.class);

    @Reference
    private CIAMService ciamService;
    
    @Reference
    
    private APIInvoker shrssAPIInvoker;

   
    @Override
	protected final void doGet(final SlingHttpServletRequest request,
            final SlingHttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html; charset=UTF-8");
    	response.setCharacterEncoding("UTF-8");
		log.info("Entering doGet method");
		CIAMTokenResponse ciamTokenResponse = ciamService.getCachedGuestToken();
		
		response.getWriter().write("Cached Token : "+ciamTokenResponse.getAccessToken()+ "<br>");
		response.getWriter().write("Expired In : "+ciamTokenResponse.getExpiresTime()+ "<br>");
		if(null !=shrssAPIInvoker) {
			String locationResponse  = shrssAPIInvoker.invokeUnityLocationAPI();
			Gson gson = new Gson();
			Locations[] locations = gson.fromJson(locationResponse, Locations[].class);
			response.getWriter().write("Array Length :" + locations.length+"<br>");
			log.info("Location Response : "+locationResponse);
			response.getWriter().write("Locations Data : "+locationResponse+ "<br>");	
		}
		
	}

    
}