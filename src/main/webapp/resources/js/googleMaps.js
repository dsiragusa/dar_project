(function($) {

        window.initMap = function () {
            directionsService = new google.maps.DirectionsService;
            directionsDisplay = new google.maps.DirectionsRenderer;

            var element = document.getElementById('map');

            $(element).css('height', '500px');

            var map = new google.maps.Map(element, {
                zoom: 12,
                scrollwheel: false,
                center: {lat: 48.85661, lng: 2.35222}
            });
            directionsDisplay.setMap(map);

            $('#getPath').click(calculateAndDisplayRoute);
        };


        function calculateAndDisplayRoute() {

            /*
             var waypoint = document.getElementById('waypoint').innerHTML;

             var waypointArray = [];

             if(waypoint != "") {
             var temp = {location: waypoint, stopover: true};
             waypointArray.push(temp);
             }
             */

            var travelMode = {
                transit : google.maps.TravelMode.TRANSIT,
                driving : google.maps.TravelMode.DRIVING,
                bicycling : google.maps.TravelMode.BICYCLING,
                walking : google.maps.TravelMode.WALKING
            };
            var selectedTravelMode = travelMode[$('#travelMode').val()];

            directionsService.route({
                origin: $('#start').val(),
                destination: $('#end').text(),
                //waypoints: waypointArray,
                optimizeWaypoints: true,
                travelMode: selectedTravelMode
            }, function(response, status) {
                if (status === google.maps.DirectionsStatus.OK) {
                    directionsDisplay.setDirections(response);
                } else {
                    window.alert('Le calcul de l\'itinéraire a échoué. Peut être avez vous entrer une mauvaise adresse ?' + status);
                }
            });
        }

        $(document).ready(function() {
            $('#favorites').change(function (event) {
                $('#start').val($(this).val());

                calculateAndDisplayRoute();
            });

            $('#travelMode').change(calculateAndDisplayRoute);

        });

    }
)(jQuery);