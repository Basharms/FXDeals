package org.com.fxdeals.resources;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.com.fxdeals.dto.FXDeal;
import org.com.fxdeals.services.GenericService;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class Deal
 */
@WebServlet("/Deal")
public class Deal extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Deal() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		GenericService genericService = new GenericService();
		FXDeal fxDeal = new FXDeal();
		
		String fromCurrency = request.getParameter("fromCurrency");
		String toCurrency = request.getParameter("toCurrency");
		String dealTime = request.getParameter("dealTime");
		String dealUniqueId = request.getParameter("dealUniqueId");
		String dealAmount = request.getParameter("dealAmount");
		
		fxDeal.setFromCurrency(fromCurrency);
		fxDeal.setToCurrency(toCurrency);
		fxDeal.setDealUniqueId(dealUniqueId);
		fxDeal.setDealAmount(dealAmount);	
		
		System.out.println("Request received for FX Deals for ID :: " + dealUniqueId);
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy HH:mm:ss", Locale.ENGLISH);
		try {
			Date date = formatter.parse(dealTime);
			fxDeal.setDealTime(date);
			
			// Validate all fields well received
			boolean isRequestFieldsValid = isRequestFildsValid(fxDeal, response);
			
			if(isRequestFieldsValid) {
				// There is no missing fields then proceed with adding the request to the DB.
				// In case the Deal Unique Id already submitted then error message will return.
				genericService.addFXDealRequest(fxDeal);

				prepareAndWriteResponse(dealUniqueId + " Success", "Request submitted Successfully", response);
				return;
			}
			
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println(dealUniqueId + " Request already  submitted");
			prepareAndWriteResponse("error", "Request already  submitted", response);
			return;
		} catch (ParseException e) {
			e.printStackTrace();
			prepareAndWriteResponse("error", "Error in deal time format", response);
			return;
		} catch (Exception e) {
			e.printStackTrace();
			prepareAndWriteResponse("error", "Error occured while submitting request", response);
			return;
		}
	}
	
	/**
	 * This method to validate request fields
	 * @param fxDeal
	 * @param response
	 * @return true if all the fields are available
	 */
	public boolean isRequestFildsValid(FXDeal fxDeal, HttpServletResponse response) {
		
		System.out.println(fxDeal.getDealUniqueId() + " Validate request fields");
		if(fxDeal.getFromCurrency() == null || fxDeal.getFromCurrency().trim().length() <= 0) {
			prepareAndWriteResponse("error", "Missing From Currency", response);
			return false;
		}
		
		if(fxDeal.getToCurrency() == null || fxDeal.getToCurrency().trim().length() <= 0) {
			prepareAndWriteResponse("error", "Missing To Currency", response);
			return false;
		}
		
		if(fxDeal.getDealAmount() == null || fxDeal.getDealAmount().trim().length() <= 0) {
			prepareAndWriteResponse("error", "Missing Deal Amount", response);
			return false;
		}
		
		if(fxDeal.getDealTime() == null) {
			prepareAndWriteResponse("error", "Missing Deal Time", response);
			return false;
		}
		
		if(fxDeal.getDealUniqueId() == null || fxDeal.getDealUniqueId().trim().length() <= 0) {
			prepareAndWriteResponse("error", "Missing Deal Unique Id", response);
			return false;
		}
		
		return true;
		
	}
	
	/**
	 * This method used to prepare and send the response as json message
	 * Response contains status and message
	 * @param status
	 * @param message
	 * @param response
	 */
	public void prepareAndWriteResponse(String status, String message, HttpServletResponse response) {
		
		JSONObject jsonObject = new JSONObject();
        
        try {
        	jsonObject.put("status", status);
        	jsonObject.put("message", message);
            
            PrintWriter out = response.getWriter();
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        out.write(jsonObject.toString());
	        out.flush();
            return;
        } catch (JSONException e) {
        	e.printStackTrace();
        } catch (IOException e) {
			e.printStackTrace();
		}
	}

}
