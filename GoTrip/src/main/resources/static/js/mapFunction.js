    var map;
      var infowindow;
      var placeName;
      var lat = 0;
      var lng = 0;

      function start(){
        //alert("enter start");
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

      function positionSearch(){
        //alert("enter position");
        var pyrmont = map.getCenter();
        var zoom = map.getZoom();
        initMap(pyrmont, zoom);  
        
        
        map.addListener('idle', performSearch);
        //service.nearbySearch(request, callback2);
      }

      function performSearch(){
        //alert("fuck");
        var request = {
          bounds: map.getBounds(),
          keyword: '景點'
        };

        var service = new google.maps.places.PlacesService(map);
        service.radarSearch(request, callback2);
      }

      function initalLocation() {
        //alert("enter initalLocation");
        if (navigator.geolocation) {
          //alert("fuck");
            navigator.geolocation.getCurrentPosition(function(position) {
              var pyrmont = {lat: 0, lng: 0};
              //alert(pyrmont.lat);
              pyrmont.lat = position.coords.latitude;
              pyrmont.lng = position.coords.longitude;
              lat = position.coords.latitude;
              lng = position.coords.longitude;
              initMap(pyrmont, 16);
          });
        }
      }

      function initMap(pyrmont, zoom){
        //alert("enter initMap");
        map = new google.maps.Map(document.getElementById('map'), {
          center: pyrmont,
          zoom: zoom
        });

        infowindow = new google.maps.InfoWindow();
      }

      function callback2(results, status){
        if (status === google.maps.places.PlacesServiceStatus.OK) {
          for (var i = 0; i < results.length; i++) {
            createMarker(results[i]);
            //alert(results[i].name);
          }
        }
      }
      
      function callback1(results, status) {
        //alert("enter");
        if (status === google.maps.places.PlacesServiceStatus.OK) {
          pyrmont = results[0].geometry.location;
          initMap(pyrmont, 16);
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
          //animation: google.maps.Animation.DROP
        });
        createInfoWindow(marker, place);
      }

      function createInfoWindow(marker, place){
        var service = new google.maps.places.PlacesService(map);
        //alert(place.place_id);
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
              infowindow.open(map, marker);//alert("no");
            }
          });
        });




        /*var isOpen;
        if(place.opening_hours)
          isOpen = "營業中";
        else
          isOpen = "目前非營業時間";

        var request = {placeId: place.place_id};
        var singleContent;
        //var service = new google.maps.places.PlacesService(map);
        service.getDetails(request, function(placeDetail, status){
          if(status == google.maps.places.PlacesServiceStatus.OK){
            var base = '名稱: ' + place.name + '<br>地址: ' + placeDetail.formatted_address + 
                            '<br>評價: ' + place.rating.toString() + '<br>' + isOpen;
            var create = '<br><span class="glyphicon glyphicon-plus-sign" style="font-size:16px" onclick="createAttraction(\'' + place.place_id + '\')">' + 
                          '</span>';
                          //
            var website = placeDetail.website;
            if(website !== null){
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
        });*/
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
      
      
