package org.com.fxdeals.resources;

import static org.junit.jupiter.api.Assertions.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Test;

class DealJ extends Mockito {

	@Test
	void testDoPostHttpServletRequestHttpServletResponse() {
		
		HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class); 
        
		fail("Not yet implemented");
	}

}
