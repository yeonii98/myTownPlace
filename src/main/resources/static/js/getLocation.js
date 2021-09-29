const COORDS = "coords";

function getCurrent(lat,lon) {
    $.ajax({
        url: 'https://dapi.kakao.com/v2/local/geo/coord2address.json?x=' + lon +'&y=' + lat,
        headers: {
            'Authorization' : 'KakaoAK 2d6a1913c9431384837ab17da9346801'
        },
    }).done(res=>{
        res.documents.forEach((i) => {
            $('.getLocation').val(i.address.region_3depth_name);
        })
    }).fail(error=>{
        console.log("오류",error);
    });
}

function handleGeoSucces(position) {
    lat = position.coords.latitude;
    lon = position.coords.longitude;
    getCurrent(lat,lon);
}

function handleGeoError() {
    console.log("Can't access geo location");
}

function askForCoords() {
    navigator.geolocation.getCurrentPosition(handleGeoSucces, handleGeoError);
}

function loadCoords() {
    const loadedCoords = localStorage.getItem(COORDS);
    if (loadedCoords === null) {
        askForCoords();
    } else {
        //
    }
};

