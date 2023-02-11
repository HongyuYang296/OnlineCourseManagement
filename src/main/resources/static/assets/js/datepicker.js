
//setDate();
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
        // document.getElementById('dates2').innerText = inputDate;
        var newURL_euro = "attendance_euro?searchDate=" + inputDate;
        var newURL_us = "attendance_us?searchDate=" + inputDate;
        var newURL_total = "attendance_total?searchDate=" + inputDate;
        var newURL = "attendance?searchDate=" + inputDate;
        document.getElementById("attendance").href = newURL;
        document.getElementById("attendance_euro").href = newURL_euro;
        document.getElementById("attendance_us").href = newURL_us;
        document.getElementById("attendance_total").href = newURL_total;

    }

}



//
//     // const today = document.getElementById("dates").innerText;
//     // document.getElementById('searchDate').valueAsDate = new Date(today);

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





// function setDate(){
//     const initialDates = document.getElementById("dates").innerText;
//     var last_value = document.getElementById("searchDate").value;
//     console.log(last_value);
//     console.log("test2");
//     console.log(initialDates);
//
//     setCookie("inputDate", last_value , 30);
//     let inputDate = getCookie("inputDate");
//     console.log("input date: " + inputDate);
//
//     if (inputDate === '') {
//         console.log("no input date, date is today = " + initialDates);
//         document.getElementById('searchDate').valueAsDate = new Date(initialDates);
//
//     } else {
//         console.log("inputDate is" + inputDate);
//         document.getElementById('searchDate').valueAsDate = new Date(inputDate);
//     }


// //    document.getElementById("dates").innerText = null;
//
//     function checkCookie() {
//         setCookie("inputDate", initialDates , 30);
//         let inputDate = getCookie("inputDate");
//         console.log("input date" + inputDate);
//
//         if (last_value == null) {
//             console.log("no input date, date is today = " + inputDate);
//             document.getElementById('searchDate').valueAsDate = new Date(inputDate);
//
//         } else {
//             console.log("no input");
//             document.getElementById('searchDate').valueAsDate = new Date(inputDate);
//         }
//     }
//     checkCookie()

    //
    // $("#searchDate").on("change", function () {
    //     if (this.value === last_value) alert('no change');
    //     last_value=this.value;
    // });
    //
    // var input = document.getElementById("searchDate").innerText;
    // console.log(input);
    // if (input == null){
    //     const today = document.getElementById("dates").innerText;
    //     document.getElementById('searchDate').valueAsDate = new Date(today);
    // }
    // else {
    //     var today = document.getElementById("searchDate").innerText;
    //     document.getElementById('searchDate').valueAsDate = new Date(today);
    // }
//}





