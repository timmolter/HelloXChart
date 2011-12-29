<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
	<head>
	    <meta http-equiv="content-type" content="text/html;charset=utf-8" />
        <title>@TITLE@</title>
	    <% String chartId = (String) request.getAttribute("chart_id");%>
	</head>
    <body>
	    <div>
	    	<a href="<%=request.getContextPath()%>/chart?action=whatever">Generate Chart</a>
	    </div>
	    <%if(chartId != null){ %>
	    <div>
	    	<img src="<%=request.getContextPath()%>/chart?chart_id=<%=chartId %>"/> 
	    </div>
 		<%} %>
    </body>
</html>