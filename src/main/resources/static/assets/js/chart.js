// google.charts.load('current', {'packages':['corechart']});
// google.charts.setOnLoadCallback(drawChart);
//
//
//
//
//
// function drawChart() {
//     var data = google.visualization.arrayToDataTable([
//         ['Year', 'Sales'],
//         ['10-01-2023',  1000],
//         ['2005',  1170],
//         ['2006',  660],
//         ['2007',  1030]
//     ]);
//
//     var options = {
//         title: 'Company Performance',
//         curveType: 'function',
//         legend: { position: 'bottom' }
//     };
//
//     var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));
//
//     chart.draw(data, options);
// }
//
google.charts.load('current', {'packages':['corechart']});
//
var data2 = ["year","total"];

var tmp = null;
$.ajax({
    async: false,
    url:"/method2",
    type:"get",
    dataType:"json",
    success:function (data) {
        data.splice(0,0,data2);
        console.log(data);
        console.log(data[1]);
        tmp = data;
    }
});

console.log("outside");
console.log(tmp);


function drawChart() {
    var data3 = google.visualization.arrayToDataTable(
        tmp
    );
    var options = {
        title: 'Company Performance',
        curveType: 'function',
        legend: { position: 'bottom' }
    };
    var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));
    chart.draw(data3, options);
}

//       drawChart(backData);
google.charts.setOnLoadCallback(drawChart);



