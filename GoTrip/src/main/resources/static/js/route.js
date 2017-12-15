      var map;
      var directionService;
      var directionDisplay;

      function initialLocation() {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(function(position) {
              var pyrmont = {lat: 0, lng: 0};
              pyrmont.lat = position.coords.latitude;
              pyrmont.lng = position.coords.longitude;
              lat = position.coords.latitude;
              lng = position.coords.longitude;

              //以裝置位置初始化map
              initMap(pyrmont);
          });
        }
      }

      function initMap(pyrmont) {
        directionsService = new google.maps.DirectionsService;
        directionsDisplay = new google.maps.DirectionsRenderer;
        map = new google.maps.Map(document.getElementById('map'), {
          zoom: 10,
          center:pyrmont
        });
        directionsDisplay.setMap(map);
        directionsDisplay.setPanel(document.getElementById('right-panel'));
        
        calculateAndDisplayRoute(directionsService, directionsDisplay, "DRIVING");
        

        $('#transportation').change(function(){
          var method = $("#transportation").val();
          calculateAndDisplayRoute(directionsService, directionsDisplay, method);
        });
      }

      function calculateAndDisplayRoute(directionsService, directionsDisplay, method) {
        var origin = sessionStorage.getItem("origin");
        var destination = sessionStorage.getItem("destination");
        
        directionsService.route({
          origin: {placeId: origin},
          destination: {placeId: destination},
          travelMode: method
          
        }, function(response, status) {
          if (status === 'OK') {
            directionsDisplay.setDirections(response);
          } else {
            window.alert('Directions request failed due to ' + status);
          }
        });
      }