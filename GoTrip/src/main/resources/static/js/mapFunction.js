 		var map;
      var infowindow;
      var placeName;
      var pyrmont = {lat: 0, lng: 0};
      var myLocation = {lat: 0, lng: 0};

      function start(){
        initalLocation(1);  
      }

      function textSearch(){ 
        var service = new google.maps.places.PlacesService(map);
        var value = document.getElementById("input").value;
        var request = {
              location: pyrmont,
              radius: '500',
              query: value
            };
        service.textSearch(request, callback1);
      }

      function positionSearch(){
        pyrmont = myLocation;
        initMap();  
        var request = {
              location: pyrmont,
              radius: 800,
              type: ['景點']
            };
        var service = new google.maps.places.PlacesService(map);
        service.nearbySearch(request, callback2);
      }

      function initalLocation(value) {
        
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(function(position) {
              pyrmont.lat = position.coords.latitude;
              pyrmont.lng = position.coords.longitude;
              myLocation.lat = position.coords.latitude;
              myLocation.lng = position.coords.longitude;
              if(value === 1)
                initMap();
          });
        }
      }

      function initMap(){
        map = new google.maps.Map(document.getElementById('map'), {
          center: pyrmont,
          zoom: 16
        });

        infowindow = new google.maps.InfoWindow();
      }

      function callback2(results, status){
        if (status === google.maps.places.PlacesServiceStatus.OK) {
          for (var i = 0; i < results.length; i++) {
            createMarker(results[i]);
          }
        }
      }
      
      function callback1(results, status) {
        if (status === google.maps.places.PlacesServiceStatus.OK) {
          pyrmont = results[0].geometry.location;
          initMap();
          for (var i = 0; i < results.length; i++) {
            createMarker(results[i]);
          }
        }
      }

      function createMarker(place){
        var marker = new google.maps.Marker({
          position: place.geometry.location,
          map: map,
          //draggable: true,
          animation: google.maps.Animation.DROP
        });
        createInfoWindow(marker, place);
      }

      function createInfoWindow(marker, place){
        var isOpen;
        if(place.opening_hours)
          isOpen = "營業中";
        else
          isOpen = "目前非營業時間";

        var request = {placeId: place.place_id};
        var singleContent;
        var service = new google.maps.places.PlacesService(map);
        service.getDetails(request, function(placeDetail, status){
          if(status == google.maps.places.PlacesServiceStatus.OK){
            var base = '名稱: ' + place.name + '<br>地址: ' + placeDetail.formatted_address + 
                            '<br>評價: ' + place.rating.toString() + '<br>' + isOpen;
            var create = '<br><span class="glyphicon glyphicon-plus-sign" style="font-size:16px" onclick="createAttraction(\'' + place.place_id + '\')">' + 
                          '</span>'
                          //
            var website = placeDetail.website;
            if(website != null){
              website = '<br>連結: <a href=' + website + '>相關網站</a>';
              singleContent = base + website + create;
            }
            else
              singleContent = base + create;
          }
          else
            singleContent = '名稱: ' + place.name + '<br>評價: ' + place.rating.toString() + '<br>' + isOpen + 
                            '<br><span class="glyphicon glyphicon-plus-sign" style="font-size:16px" onclick="createAttraction(\'' + place.place_id + 
                            '\')"></span>';
        });

        marker.addListener('click', function() {
          infowindow.setContent(singleContent);
          placeName = place.name;
          infowindow.open(map, this);
        });
      }

      function createAttraction(placeId){
        var basket = $('#basket');
        basket.append('<li class="dd-item" id=' + placeId + '><h5 class="title dd-handle" >' + placeName + '<i class=" material-icons ">filter_none</i></h5><span class="glyphicon glyphicon-trash" onclick="removeAttraction(\'' + placeId + '\')" style="color:red"></span> <input type="hidden" value=' + placeId + '> <input type="hidden" value=' + placeName + '> </li>');
      }

      function removeAttraction(placeId){
       $("#" + placeId).remove();
      }
      
      
