/**
 * 
 *  Main Script File that handles auto complete requests and responses
 * @author Shubham
 *
 */

var suggestionIndex=0;

$(document).ready(function(){

	$('#suggestions-wrapper').hide();


	function autocomplete(){

		suggestionIndex=0;
		$('#suggestions-wrapper').find('span').remove();
		$('#suggestions-wrapper').find('br').remove();
		var currSearchText = $("#searchBox").val();
		if(currSearchText.length>0){

			var url = encodeURIComponent(currSearchText)+"/suggestions";
			$.ajax({
				type: 'GET',
				url: url,
				cache: false,
				dataType:'json',
				success: function(data){


					$('#suggestions-wrapper').find('span').remove();
					$('#suggestions-wrapper').find('br').remove();
					$("#suggestions-wrapper").removeClass('suggestions-error');
					var suggestionsList = data.suggestions;
					if(suggestionsList!=null && suggestionsList.length>0){

						$('#suggestions-wrapper').show();
						for(var x=0;x<6;x++){

							var replacement = "<font style='color:black;font-weight:bold;'>"+currSearchText+"</font>";
							var suggestion=suggestionsList[x].replace(currSearchText, replacement);

							var spanId = "suggestion"+(x+1);
							if(x==suggestionsList.length-1 || x==5){
								$("#suggestions-wrapper").append("<span id="+spanId+" onclick='setSearchBoxVal(this)' onmouseover='spanHoverAction(this)' class='suggestion-font'>"+suggestion+"</span>");
							}
							else {
								$("#suggestions-wrapper").append("<span id="+spanId+" onclick='setSearchBoxVal(this)' onmouseover='spanHoverAction(this)' class='suggestion-font'>"+suggestion+"</span><br />");
							}


						}
					}

					else{
						$('#suggestions-wrapper').hide();
						suggestionIndex=0;
					}
				},

				error : function(xhr, textStatus, errorThrown){

					$('#suggestions-wrapper').find('span').remove();
					$('#suggestions-wrapper').find('br').remove();
					$("#suggestions-wrapper").addClass('suggestions-error');
					$('#suggestions-wrapper').show();
					$("#suggestions-wrapper").append("<span id='errorMsg' class='suggestion-font suggestion-error'>"+xhr.statusText+"</span>");

				}

			})
		}

		else {
			$('#suggestions-wrapper').hide();
			suggestionIndex=0;
		}

	}

	function makeSelection(suggestionNum){

		var suggestionsData = $("#suggestions-wrapper").find('span');

		if(suggestionsData.length>0){

			$("#suggestion"+suggestionNum).addClass('suggestion-select');
			$("#searchBox").val($("#suggestion"+suggestionNum).text());



		}

	}

	function removeSelection(suggestionNum){

		var suggestionsData = $("#suggestions-wrapper").find('span');

		if(suggestionsData.length>0){

			$("#suggestion"+suggestionNum).removeClass('suggestion-select');

		}

	}

	$("#searchBox").keyup(function(event){

		if(event.keyCode!="38" && event.keyCode!="40"){
			autocomplete();
		}
	})

	$("#searchBox").click(function(){

		autocomplete();
	})
	
	 
	$("body").click(function(){

		$('#suggestions-wrapper').hide();
	})

	$("#searchBox").keydown(function(event){

		var suggestionsData = $("#suggestions-wrapper").find('span');

		if(event.keyCode=='40'){

			suggestionIndex = suggestionIndex+1;
			if(suggestionIndex>suggestionsData.length){

				suggestionIndex=1;
				removeSelection(suggestionsData.length);
				makeSelection(suggestionIndex);
			}

			else {
				removeSelection(((suggestionIndex-1)==0) ? suggestionsData.length:suggestionIndex-1);
				makeSelection(suggestionIndex);
			}


		}

		else if(event.keyCode=='38'){

			suggestionIndex = suggestionIndex-1;
			if(suggestionIndex==0){

				suggestionIndex=suggestionsData.length;
				removeSelection(1);
				makeSelection(suggestionIndex);
			}

			else {
				removeSelection((suggestionIndex+1)>suggestionsData.length?suggestionData.length:suggestionIndex+1);
				makeSelection(suggestionIndex);
			}

		}

	})
});


function setSearchBoxVal(obj){
	
	  $("#searchBox").val($(obj).text());
	  $("#searchBox").focus();
}

function spanHoverAction(obj){
	
	  $("#suggestions-wrapper").find('span').each(function(){
	  $(this).removeClass("suggestion-select");})
	  suggestionIndex=0;
}
