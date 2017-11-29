      var map;
      var infowindow;
      var placeName;
      var lat = 0;
      var lng = 0;

      function start(){
        initalLocation();  
      }

      function textSearch(){
        var service = new google.maps.places.PlacesService(map);
        var value = document.getElementById("input").value;
        var pyrmont = {lat: lat, lng: lng};
        var request = {
              location: pyrmont,
              radius: '1000',
              query: value
            };
        service.textSearch(request, callback1);
      }

      function positionSearch(query){
        var pyrmont = map.getCenter();
        var zoom = map.getZoom();
        initMap(pyrmont, zoom);  
        performSearch(query);
        //map.addListener('idle', performSearch(query));
      }

      function performSearch(query){
        if(query == "0")
          clearMarker();
        else{
          if(query == "1") query = "景點";
          else if(query == "2")query = "公園";
          else if(query == "3")query = "夜市";
          else if(query == "4")query = "購物";
          else if(query == "5")query = "古蹟";
          else if(query == "6")query = "美食";
          else query = "景點";
       
          var request = {
            bounds: map.getBounds(),
            keyword: query
          };
          var service = new google.maps.places.PlacesService(map);
          service.radarSearch(request, callback2);
        }
      }

      function initalLocation() {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(function(position) {
              var pyrmont = {lat: 0, lng: 0};
              pyrmont.lat = position.coords.latitude;
              pyrmont.lng = position.coords.longitude;
              lat = position.coords.latitude;
              lng = position.coords.longitude;

              initMap(pyrmont, 16);
          });
        }
      }

      function initMap(pyrmont, zoom){
        map = new google.maps.Map(document.getElementById('map'), {
          center: pyrmont,
          zoom: zoom
        });

        infowindow = new google.maps.InfoWindow();
      }

      function callback1(results, status) {
        if (status === google.maps.places.PlacesServiceStatus.OK) {
          var markers = [];
          pyrmont = results[0].geometry.location;
          initMap(pyrmont, 12);
          for (var i = 0; i < results.length; i++){
            markers.push(createMarker(results[i]));
          }
          var markerCluster = new MarkerClusterer(map, markers, {imagePath: 'https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/m'});
        }
      }

      function callback2(results, status){
        var markers = [];
        if (status === google.maps.places.PlacesServiceStatus.OK) {
          for (var i = 0; i < results.length; i++){
            markers.push(createMarker(results[i]));
          }
          var markerCluster = new MarkerClusterer(map, markers, {imagePath: 'https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/m'});
        }
      }

      function createMarker(place){
        //var labels = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
        var marker = new google.maps.Marker({
          position: place.geometry.location,
          //draggable: true,
          //animation: google.maps.Animation.DROP
        });
        createInfoWindow(marker, place);

        return marker;
      }

      function createInfoWindow(marker, place){
        var service = new google.maps.places.PlacesService(map);
        google.maps.event.addListener(marker, 'click', function(){
          service.getDetails({placeId: place.place_id}, function(detail, status){
            
            if(status !== google.maps.places.PlacesServiceStatus.OK){
              console.error(status);
              alert("error");
              return;
            }
            else{
              placeName = detail.name;
              var isOpen = detail.opening_hours ? "營業中" : "非營業時間";  
              var base = '名稱: ' + detail.name + '<br>地址: ' + detail.formatted_address + '<br>' + isOpen + '<br>';           
              var create = '<span class="glyphicon glyphicon-plus-sign" style="font-size:16px"' + 
                           'onclick="createAttraction(\'' + detail.place_id + '\')">' + '</span>';      
              var website = detail.website;
              var content;

              if(typeof website == "undefined")
                content = base + create;
              else{
                website = '連結: <a href=' + website + ' + target="_blank">相關網站</a><br>';
                content = base + website + create;
              }
              
              infowindow.setContent(content);
              infowindow.open(map, marker);
            }
          });
        });
        
      }

      function createAttraction(placeId){
      
        var basket = $('#basket');
        basket.append('<li class="dd-item" id=' + placeId + '><h5 class="title dd-handle" >' + placeName + 
          '<i class=" material-icons ">filter_none</i></h5><span class="glyphicon glyphicon-trash" ' + 
          'onclick="removeAttraction(\'' + placeId + '\')" style="color:red"></span> <input type="hidden" value=' + 
          placeId + '> <input type="hidden" value=' + placeName + '> </li>');
      }

      function removeAttraction(placeId){
       $("#" + placeId).remove();
      }

      function clearMarker(){
        initMap(map.getCenter(), map.getZoom());
      }

      function specialSearch(){
        //var kind = document.querySelector('input[name="kind"]:checked').value;;
        var kind = $("#kind").val();
        positionSearch(kind);
      }
