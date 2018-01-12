      var map;
      var directionService;
      var directionDisplay;

      //定位
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

      //初始化map
      function initMap(pyrmont) {
        directionsService = new google.maps.DirectionsService;
        directionsDisplay = new google.maps.DirectionsRenderer;
        map = new google.maps.Map(document.getElementById('map'), {
          zoom: 10,
          center:pyrmont
        });
        directionsDisplay.setMap(map);
        //路線規劃文字資訊
        directionsDisplay.setPanel(document.getElementById('right-panel'));
        
        //預設交通方式為"DRIVING"
        calculateAndDisplayRoute(directionsService, directionsDisplay, "DRIVING");
        
        //交通方式改變，重新路線規劃
        $('#transportation').change(function(){
          var method = $("#transportation").val();
          calculateAndDisplayRoute(directionsService, directionsDisplay, method);
        });
      }

      //路線規劃
      function calculateAndDisplayRoute(directionsService, directionsDisplay, method) {
        var origin = sessionStorage.getItem("origin"); //以javascript session取得出發地
        var destination = sessionStorage.getItem("destination"); //取得目的地
        
        //進行路線規劃
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