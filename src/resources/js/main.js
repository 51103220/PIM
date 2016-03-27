var maxPaginationLinks = 2;
/**********************************
 * *SELECT ON CHANGE
 **********************************/
function selectHandler() {
	$("select").change(function() {
		var class_name = $(this).attr('class').replace(/empty/g, "");
		$(this).attr('class', class_name);
	});

};
function fixBodyHeight() {
	var header_height = $(".header").outerHeight();
	var body_height = $(window).height() - header_height;
	$("#main").css("height", body_height + "px");
};

/*********************************
 * * SET AND UNSET SELECTED LINKS
 *********************************/
function setLinkSelected(link) {
	var newClass = link.attr("class") + " selected";
	link.attr("class", newClass);
};
function unsetLinkSelected(link) {
	var newClass = link.attr("class").replace(/selected/g, "");

	link.attr("class", newClass);
};
/**********************************
 * * SET AND UNSET ERROR INPUTS
 **********************************/
function setErrorInput(input, isSet, code) {
	if (isSet) {
		var newClass = input.attr("class") + " errorInput";
		input.attr("class", newClass);
		input.parent().find("p.hiddenError").show();
		input.parent().find("p.hiddenError").html(code);
	} else {
		var className = input.attr("class").replace(/errorInput/g, "");
		input.attr("class", className);
		input.parent().find("p.hiddenError").hide();
		input.parent().find("p.hiddenError").html(code);
	}
};
/**********************************
 * * PAGINATION: SET LINK VISIBLE
 * * OR INVISBLE
 **********************************/
function handlePagination(id){
	var links = $("#projectList .pagination .paging");
	id =  parseInt(id);
	var i =1;
	var start,end =0;
	if(id%maxPaginationLinks ==0){
		start = id -maxPaginationLinks+1;
		end = id;
	}
	else{
		start = id;
		end = id+maxPaginationLinks-1;
	}
	links.each(function(){
		var link =$(this);
		if (i>=start && i <=end){
			link.show();
		}else{
			link.hide();
		}
			
		i=i+1;
	});
};
$(document).ready(function() {

	/***************************************************************************
	 * * Language Options * clicks
	 **************************************************************************/
	$(".header #langOption li a").click(function(e) {
		e.preventDefault();
		unsetLinkSelected($(this).parent().parent().find("a.selected"));
		setLinkSelected($(this));
	});
	/***************************************************************************
	 * * Menu item clicks
	 **************************************************************************/
	$("#main #menuList #selectList li a").click(function(e) {
		e.preventDefault();
		unsetLinkSelected($(this).parent().parent().find("a.selected"));
		var class_str = $(this).attr("class");
		if (class_str && class_str.indexOf("notAffected") >= 0) {
			setLinkSelected($(this).parent().next().find("a"));
		} else {
			setLinkSelected($(this));
		}
		// AJAX NAVIGATOR
		var url = $(this).attr("href");
		$.ajax({
			method : "GET",
			url : url
		}).done(function(data) {
			$("#main #contentBody").html(data);
		}).fail(function(jqXHR, textStatus) {
			window.location.href = $(".header #projectName").attr("href") + "errorsunexpected=" + textStatus;
		});
	});

	/***************************************************************************
	 * *Date Picking event
	 **************************************************************************/
	$('.datePicker').datetimepicker();
	fixBodyHeight();

	/***************************************************************************
	 * * General Form Handling
	 **************************************************************************/
	$(".general-content .processBtn").click(function(e) {
		e.preventDefault();
		var form = $(this).parent().parent().find(".general-form").first();
		var $inputs = form.find("input");
		var $selects = form.find("select");
		var values = {};
		$inputs.each(function() {
			setErrorInput($(this), false, "");
			var id = $(this);
			if(id.attr("name") == "id"){
				var val = id.val();
				if(val){
					values[id.attr("name")] = val;
				}
			}
			else{
				values[$(this).attr("name")] = $(this).val();
			}
			if($(this).attr("name") == "members"){
					values[$(this).attr("name")] =  $(this).val().replace(/ /g,'').split(",");
			}
			if($(this).attr("name") == "startDate" || $(this).attr("name") == "endDate" ){
				var date = new Date($(this).val());
				if (date == "Invalid Date"){
					values[$(this).attr("name")] = null;
				}
				else{
					values[$(this).attr("name")] =  date.getFullYear() + "-" + (date.getMonth()+1) +"-" + date.getDate(); 
				}
			}
		});
	
		$selects.each(function() {
			setErrorInput($(this), false, "");
			values[$(this).attr("name")] = $(this).val();
		});
		$.ajax({
			contentType : 'application/json',
			type : form.attr("method"),
			url : form.attr("action"),
			async : false,
			dataType : 'json',
			data : JSON.stringify(values),
			success : function(data) {
				if (data.status == "FAIL") {
					for (var i = 0; i < data.result.length; i++) {
						var field = data.result[i].field;
						var code = data.result[i].code;
						var message = data.result[i].defaultMessage;
						if (code == "NotEmpty") {
							$(".errorPanel").show();
						}

						$inputs.each(function() {
							if ($(this).attr("name") == field) {
								setErrorInput($(this), true, message);
							}
						});
						$selects.each(function() {
							if ($(this).attr("name") == field) {
								setErrorInput($(this), true, message);
							}
						});
					}
				} else {
					window.location.href = $(".header #projectName").attr("href");
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				window.location.href = $(".header #projectName").attr("href") + "errorsunexpected=" + textStatus;
			}
		})
	});
	/***************************************************************************
	 * * Get Project detail Handling
	 **************************************************************************/
	$("#projectList #searchDatas .projectDetail").click(function(e){
		e.preventDefault();
		var url = $(this).attr("href");
		$.ajax({
			method : "GET",
			url : url
		}).done(function(data) {
			$("#main #contentBody").html(data);
		}).fail(function(jqXHR, textStatus) {
			window.location.href = $(".header #projectName").attr("href") + "/errorsunexpected=" + textStatus;
		});
	});
	/***************************************************************************
	 * * Search Form Handling
	 **************************************************************************/
	//Check Box
	$("#projectList #searchDatas [type=checkbox]").click(function(){
		var boxes = $("#projectList #searchDatas input:checked");
		if(boxes.length>0){
			$("#projectList .resultRow").show();
			$("#projectList .resultRow .totalItems").html(boxes.length+" item(s) selected");
		}else{
			$("#projectList .resultRow").hide();
		}
	});
	//Single Delete
	$("#projectList #searchDatas .deleteIcon").click(function(e){
		e.preventDefault();
		var link = $(this);
		$.ajax({
			method : "POST",
			url : link.attr("href")
		}).done(function(data) {
			link.parent().parent().remove();
		}).fail(function(jqXHR, textStatus) {
			window.location.href = $(".header #projectName").attr("href") + "/errorsunexpected=" + textStatus;
		});
	});
	//Multiple Deletes
	$("#projectList .resultRow .deleteMultiple").click(function(e){
		var link = $(this);
		e.preventDefault();
		var ids = [];
		var boxes = $("#projectList #searchDatas input:checked");
	
		boxes.each(function(){
			var box = $(this);
			if(box.val() == "true"){
				ids.push(box.attr("id"));
			}
		});
		$.ajax({
			method : "POST",
			url : link.attr("href"),
			data: {
				ids: ids
			}
		}).done(function(data) {
			boxes.each(function(){
				var box = $(this);
				if(box.val() == "true"){
					box.parent().parent().remove();
				}
			});
			$("#projectList .resultRow").hide();
		}).fail(function(jqXHR, textStatus) {
			window.location.href = $(".header #projectName").attr("href") + "/errorsunexpected=" + textStatus;
		});
	});
	//Search 
	$("#projectList #searchInputs #search_btn").click(function(e){
		e.preventDefault();
		var form = $("#projectList #searchInputs");
		var keywords =  $("#projectList #searchInputs #keywords").val();
		var statusKey = $("#projectList #searchInputs #statusKey").val();
		$.ajax({
			method : "POST",
			url : form.attr("action"),
			data: {
				keywords: keywords,
				statusKey: statusKey
			}
		}).done(function(data) {
			$("#main #contentBody").html(data);
		}).fail(function(jqXHR, textStatus) {
			window.location.href = $(".header #projectName").attr("href") + "/errorsunexpected=" + textStatus;
		});
	});
	//Reset Search
	$("#projectList #searchInputs #reset_btn").click(function(e){
		e.preventDefault();
		$.ajax({
			method : "GET",
			url : "resetCriteria"
		}).done(function(data) {
			$("#main #contentBody").html(data);
		}).fail(function(jqXHR, textStatus) {
			window.location.href = $(".header #projectName").attr("href") + "/errorsunexpected=" + textStatus;
		});
	});
	/***************************************************************************
	 * * PAGINATION
	 **************************************************************************/
	handlePagination("2");
	$("#projectList .pagination .paging").click(function(e){
		e.preventDefault();
		var link = $(this);
		var id = link.attr("id");
		$.ajax({
			method : "GET",
			url : link.attr("href")
		}).done(function(data) {
			$("#main #contentBody").html(data);
			handlePagination(id);
		}).fail(function(jqXHR, textStatus) {
			window.location.href = $(".header #projectName").attr("href") + "/errorsunexpected=" + textStatus;
		});
	});
	$("#projectList .pagination .directives").click(function(e){
		e.preventDefault();
		var directive = $(this);
		var max = parseInt($("#projectList #paginationMax").val());
		var start = parseInt($("#projectList #paginationStart").val());
		var end = parseInt($("#projectList #paginationEnd").val());
		var id =0;
		if (directive.attr("id") == "previous"){
			id =start;
			if(start>maxPaginationLinks)
				id=id-maxPaginationLinks;
		}else{
			id =end+1; 
			if (id > max){
				id = id -1;
			}
		}
		var url = directive.parent().attr("href") + id;
		
		$.ajax({
			method : "GET",
			url : directive.attr("href") + id 
		}).done(function(data) {
			$("#main #contentBody").html(data);
			handlePagination(id);
		}).fail(function(jqXHR, textStatus) {
			window.location.href = $(".header #projectName").attr("href") + "/errorsunexpected=" + textStatus;
		});
	});
	/***************************************************************************
	 * * SELECT HANDLING AND CLOSE PANEL HANDLING
	 **************************************************************************/
	
	selectHandler();
	$(".errorPanel .closePanel").click(function(e) {
		
		e.preventDefault();
		$(this).parent().hide();
	});
	/***************************************************************************
	 * * PREVENT ENTER SUBMIT FORM
	 **************************************************************************/
	 $(window).keydown(function(event){
	    if(event.keyCode == 13) {
	      event.preventDefault();
	      return false;
	    }
	  });
	 $("#projectList #searchDatas").tablesorter({ 
	       
	        headers: { 
	            0: { 
	                sorter: false 
	            }, 
	            6: { 
	                sorter: false 
	            } 
	        } 
	    }); 

});