// Function detects when the Clear button is clicked
$(function() {
	('#clear').click(clear);
});

// Page setup and hiding the result divs
$(function() {
	$.ajaxSetup({cache: false});
	$('#search-div').hide();
	$('#search-pre').hide();	
});

//Function to clear the input text boxes in the table
function clear() {
	$('#coursesList').reset();
};

// Function that formats the XML data coming from the server
function xmlToString(xmlData) {
    var xmlString;
    if (window.ActiveXObject){
    	// code for Internet Explorer
        xmlString = xmlData.xml;
    } else {
        // code for Firefox, Chrome and others
        xmlString = (new XMLSerializer()).serializeToString(xmlData);
    }
    xmlString = xmlString.replace("<list>","").replace("</list>","");
    return xmlString;
}

// Function that formats the plain text data coming from the server, putting it in a list
function formatText(input) {
	console.log(input);
	input = input.replace(/[^a-zA-Z0-9, ]/g, '');
	inputArr = input.split("Course");
	formatted = "<ul>";
	for (i = 0; i < inputArr.length-1; i++) {
		formatted += ("<li>" + inputArr[i+1] + "</li>");
	}
	formatted += "</ul>";
	return formatted;
}

// Function to get the JSON data from the server according to search query
function json(params) {
	$('#search-div').show();
	$('#search-pre').hide();
	$.get('CoursesController?action=search&' + params, function(responseText) {
		data: 'json',
		$("#search-div").text(JSON.stringify(responseText));
    });
}

//Function to get the XML data from the server according to search query
function xml(params) {
	$('#search-div').hide();
	$('#search-pre').show();
	$.get('CoursesController?action=search&' + params, function(responseText) {
		data: 'xml',
		xmlString = xmlToString(responseText);
		$("#search-pre").text(xmlString);
    });
}

//Function to get the plain text data from the server according to search query
function text(params) {
	$('#search-div').show();
	$('#search-pre').hide();
	$.get('CoursesController?action=search&' + params, function(responseText) {
		data: 'text/plain',
		responseText = formatText(responseText);
		$("#search-div").html(responseText);
    });
}

/* Function that detects when the search form is submitted and handles the
submission so that AJAX can insert the result into the page without reloading it */
$(document).on("submit", "#searchForm", function(event) {
	var $form = $(this);
	var params = $form.serialize();
	var format = document.getElementById("format").value;
	if (format == "json") {
		json(params);
	} else if (format == "xml") {
		xml(params);
	} else if (format == "text") {
		text(params);
	}
    event.preventDefault();
});
