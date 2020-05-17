// $.ajax({
  // method: "POST",
  // url: "http://localhost:8021/training-app-web-0.0.1-SNAPSHOT/api/appointments",
  // data: JSON.stringify({
	// "timeStart": "2020-01-02T20:00",
	// "firstName" : "Marko",
	// "lastName" : "Lucic",
	// "jmbg":"2101993790026"
// }),
// contentType: "application/json",
// })
  // .done(function( msg ) {
    // console.log(msg)
  // });
  

    $(document).ready(function(){

        $("#submit").on('click', function(e){
			            e.preventDefault();
$.ajax({
  method: "POST",
  url: "http://localhost:8021/training-app-web-0.0.1-SNAPSHOT/api/appointments",
  data: JSON.stringify(getFormData($("#form").serializeArray())),
contentType: "application/json",
})
  .done(function( msg ) {
            $("#success").html(JSON.stringify(msg));
  });
        });
		
		    });
			
			//utility function
function getFormData(data) {
   var unindexed_array = data;
   var indexed_array = {};

   $.map(unindexed_array, function(n, i) {
    indexed_array[n['name']] = n['value'];
   });

   return indexed_array;
}


