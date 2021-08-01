# FXDeals
# User the script from file DB.sql to create the table
# Use this URL to access the application: http://localhost:8080/FXDeals/Deal
# You can use postman to test the webservice
# Add parameters to the request: 
	## Parameter          Value
	## fromCurrency		  EUR
	## toCurrency		  JOD
	## dealTime		  	  13-4-2021 10:06:05 am
	## dealUniqueId		  123987
	## dealUniqueId		  125

# Response will be json format, below sample response:
	##	{
	##		"message": "Request submitted Successfully",
    ##  	"status": "Success"
	##	}

# I used Apache Tomcat as a Server