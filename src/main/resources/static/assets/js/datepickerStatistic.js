
myFunction();

function saveCookie() {
    var last_value = document.getElementById("searchDate").value;
    console.log("input: " + last_value);
    setCookie("inputDate", last_value , 1);
    let inputDate = getCookie("inputDate");
    console.log("cookie: " + inputDate);

}

function myFunction() {
    let inputDate = getCookie("inputDate");
    if (inputDate === '') {
        const initialDates = document.getElementById("dates").innerText;
        console.log("today: " + initialDates);
        console.log("no input date, date is today = " + initialDates);
        document.getElementById('searchDate').valueAsDate = new Date(initialDates);

    } else {
        console.log("inputDate is: " + inputDate);
        document.getElementById('searchDate').valueAsDate = new Date(inputDate);
        document.getElementById('dates').innerText = inputDate;
        document.getElementById('dates2').innerText = inputDate;
        var newURL_euro = "statistics_euro?searchDate=" + inputDate;
        var newURL_us = "statistics_us?searchDate=" + inputDate;
        var newURL = "statistics?searchDate=" + inputDate;
        document.getElementById("statistics").href = newURL;
        document.getElementById("statistics_euro").href = newURL_euro;
        document.getElementById("statistics_us").href = newURL_us;

    }

}

function setCookie(cname, cvalue, exdays) {
    const d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    let expires = "expires="+ d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

function getCookie(cname) {
    let date = cname + "=";
    let decodedCookie = decodeURIComponent(document.cookie);
    let ca = decodedCookie.split(';');
    for(let i = 0; i <ca.length; i++) {
        let c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(date) == 0) {
            return c.substring(date.length, c.length);
        }
    }
    return "";
}

