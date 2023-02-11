/**
 * ---------------------------------------
 * This demo was created using amCharts 5.
 *
 * For more information visit:
 * https://www.amcharts.com/
 *
 * Documentation is available at:
 * https://www.amcharts.com/docs/v5/
 * ---------------------------------------
 */

$.ajax({
    async: false,
    url: "/usersData",
    datatype: "JSON",
    type: "Get",
    success: function (data) {
        for(var i=0;i<data.length;i++)
        {
            var opt = new Option(data[i].name,data[i].name);
            $("#searchCode").append(opt);
        }
        var opt2 = new Option("All","all");
        $("#searchCode").append(opt2);
    }
});


// $.ajax({
//     async: false,
//     url:"/method2",
//     type:"get",
//     dataType:"json",
//     success:function (data) {
//         for(var i=0;i<data.length;i++)
//         {
//             var opt = new Option(data[i][0]);
//             $("#op1").append(opt);
//         }
//     }
// });


var tmp = null;
$.ajax({
    async: false,
    url:"/method2",
    type:"get",
    dataType:"json",
    success:function (data) {
        // console.log(data);
        // console.log(data[1]);
        tmp = data;
        // var date = new Date(tmp3[0][0]);
        console.log('tmp: ' + (new Date(tmp[0][0])).getDay());
    }
});



var tmp3 = null;
$.ajax({
    async: false,
    url:"/getAllName",
    type:"get",
    dataType:"json",
    success:function (data) {
        // console.log(data);
        // console.log(data[1]);
        tmp3 = data;
    }
});


// console.log("outside method3");
// console.log(typeof tmp);
// console.log("tmp: " + tmp);
// console.log("tmp2: " + tmp2);
// console.log(typeof tmp3);
// console.log("tmp3: " + tmp3);


// Create root element
// https://www.amcharts.com/docs/v5/getting-started/#Root_element
var root = am5.Root.new("chartdiv");


// Set themes
// https://www.amcharts.com/docs/v5/concepts/themes/
root.setThemes([
    am5themes_Animated.new(root)
]);


// Create chart
// https://www.amcharts.com/docs/v5/charts/xy-chart/
var chart = root.container.children.push(am5xy.XYChart.new(root, {
    panX: true,
    panY: true,
    wheelX: "panX",
    wheelY: "zoomX",
    pinchZoomX:true
}));

// Add cursor
// https://www.amcharts.com/docs/v5/charts/xy-chart/cursor/
var cursor = chart.set("cursor", am5xy.XYCursor.new(root, {
    behavior: "none"
}));
cursor.lineY.set("visible", false);


// Generate random data
var date = new Date();
date.setHours(0, 0, 0, 0);
var value = 100;

function generateData() {
    value = Math.round((Math.random() * 10 - 5) + value);
    am5.time.add(date, "day", 1);
    return {
        date: date.getTime(),
        value: value
    };
}

function generateData2(i) {
    return {
        date: tmp3[i][0],
        value: tmp3[i][2]
    };
}


function generateData3(i) {
    return {
        date: tmp[i][0],
        value: tmp[i][1]
    };
}

function generateDatas2(input1, input2) {
    var data = [];

    for (var j = 0; j < tmp.length; ++j) {
        if (input1 === 'all' && input2 === 'all') {
            data.push(generateData3(j));
        } else if (input1 === 'all' && input2 !== 'all') {
            if ((new Date(tmp[j][0])).getDay() === input2) {
                data.push(generateData3(j));
            }
        }
    }
    for (var i = 0; i < tmp3.length; ++i) {
        if (input1 !== 'all' && input2 === 'all') {
            if (tmp3[i][1] === input1){
                data.push(generateData2(i));
            }
        }
        else if (input1 !== 'all' && input2 !== 'all'){
            if (tmp3[i][1] === input1 && (new Date(tmp3[i][0])).getDay() === input2){
                data.push(generateData2(i));
            }
        }
    }

    return data;
}


function generateDatas(count) {
    var data = [];
    for (var i = 0; i < count; ++i) {
        data.push(generateData());
    }
    return data;
}


// Create axes
// https://www.amcharts.com/docs/v5/charts/xy-chart/axes/
var xAxis = chart.xAxes.push(am5xy.DateAxis.new(root, {
    maxDeviation: 0.5,
    baseInterval: {
        timeUnit: "day",
        count: 1
    },
    renderer: am5xy.AxisRendererX.new(root, {
        pan:"zoom"
    }),
    tooltip: am5.Tooltip.new(root, {})
}));

var yAxis = chart.yAxes.push(am5xy.ValueAxis.new(root, {
    maxDeviation:1,
    renderer: am5xy.AxisRendererY.new(root, {
        pan:"zoom"
    })
}));


// Add series
// https://www.amcharts.com/docs/v5/charts/xy-chart/series/
var series = chart.series.push(am5xy.SmoothedXLineSeries.new(root, {
    name: "Series",
    xAxis: xAxis,
    yAxis: yAxis,
    valueYField: "value",
    valueXField: "date",
    tooltip: am5.Tooltip.new(root, {
        labelText: "{valueY}"
    })
}));

series.fills.template.setAll({
    visible: true,
    fillOpacity: 0.2
});

series.bullets.push(function() {
    return am5.Bullet.new(root, {
        locationY: 0,
        sprite: am5.Circle.new(root, {
            radius: 4,
            stroke: root.interfaceColors.get("background"),
            strokeWidth: 2,
            fill: series.get("fill")
        })
    });
});


// Add scrollbar
// https://www.amcharts.com/docs/v5/charts/xy-chart/scrollbars/
chart.set("scrollbarX", am5.Scrollbar.new(root, {
    orientation: "horizontal"
}));



var inputData = document.getElementById("searchCode").value;
var data = generateDatas(10);

function draw_chart_by_Name(){
    var inputData = document.getElementById("searchCode").value;
    if (inputData === 'all') {
        document.getElementById("description1").innerText = 'Total Analyse';

    }
    else {
        document.getElementById("description1").innerText = inputData;

    }
    var data2 = generateDatas2(inputData,inputData2);

    console.log(inputData);
    console.log(inputData2);
    console.log(data2);
    series.data.setAll(data2);
}

var data2 = generateDatas2('all','all');
series.data.setAll(data2);


console.log(data);
console.log(data2);
console.log(tmp.length);
console.log(tmp[1][0]);

//series.data.setAll(data2);


// Make stuff animate on load
// https://www.amcharts.com/docs/v5/concepts/animations/
series.appear(1000);
chart.appear(1000, 100);


var inputData2 = "all";
var initialAll ="<img src=\"https://cdn-icons-png.flaticon.com/512/5129/5129628.png\"\n" +
    "                           onmouseover=\"this.src='https://cdn-icons-png.flaticon.com/512/5129/5129670.png'\"\n" +
    "                           onmouseout=\"this.src='https://cdn-icons-png.flaticon.com/512/5129/5129628.png'\" style=\"width: 25%\" alt=\"all week\">"
var initialMon = "<img src=\"https://cdn-icons-png.flaticon.com/512/8048/8048607.png\"\n" +
    "                           onmouseover=\"this.src='https://cdn-icons-png.flaticon.com/512/8048/8048600.png'\"\n" +
    "                           onmouseout=\"this.src='https://cdn-icons-png.flaticon.com/512/8048/8048607.png'\"  style=\"width: 25%\" alt=\"Mon\">";
var initialTue ="<img src=\"https://cdn-icons-png.flaticon.com/512/8048/8048610.png\"\n" +
    "                           onmouseover=\"this.src='https://cdn-icons-png.flaticon.com/512/8048/8048603.png'\"\n" +
    "                           onmouseout=\"this.src='https://cdn-icons-png.flaticon.com/512/8048/8048610.png'\"  style=\"width: 25%\" alt=\"Tue\">";
var initialWed ="<img src=\"https://cdn-icons-png.flaticon.com/512/8048/8048613.png\"\n" +
    "                           onmouseover=\"this.src='https://cdn-icons-png.flaticon.com/512/8048/8048606.png'\"\n" +
    "                           onmouseout=\"this.src='https://cdn-icons-png.flaticon.com/512/8048/8048613.png'\"  style=\"width: 25%\" alt=\"Wed\" >";
var initialThu ="<img src=\"https://cdn-icons-png.flaticon.com/512/8048/8048616.png\"\n" +
    "                           onmouseover=\"this.src='https://cdn-icons-png.flaticon.com/512/8048/8048609.png'\"\n" +
    "                           onmouseout=\"this.src='https://cdn-icons-png.flaticon.com/512/8048/8048616.png'\"  style=\"width: 25%\" alt=\"Thu\">";
var initialFir ="<img src=\"https://cdn-icons-png.flaticon.com/512/8048/8048619.png\"\n" +
    "                           onmouseover=\"this.src='https://cdn-icons-png.flaticon.com/512/8048/8048612.png'\"\n" +
    "                           onmouseout=\"this.src='https://cdn-icons-png.flaticon.com/512/8048/8048619.png'\"  style=\"width: 25%\" alt=\"Fir\">" ;



function chooseAll() {
    document.getElementById("imgAll").innerHTML="<img id='imgAll' src='https://cdn-icons-png.flaticon.com/512/5129/5129670.png'  style='width: 30%' alt='all week'>"
    document.getElementById("imgMon").innerHTML= initialMon;
    document.getElementById("imgTue").innerHTML= initialTue;
    document.getElementById("imgWed").innerHTML= initialWed;
    document.getElementById("imgThu").innerHTML= initialThu;
    document.getElementById("imgFir").innerHTML= initialFir;
    document.getElementById("weeks").innerText = "All Weekday";
    // document.getElementById("weeks2").innerText = "All Weekday";
    inputData2 = "all";
    console.log('all');
    draw_chart_by_Name();

}

function chooseMon() {
    document.getElementById("imgMon").innerHTML = "<img id='imgMon' src='https://cdn-icons-png.flaticon.com/512/8048/8048600.png'  style='width: 30%' alt='Mon'>"
    document.getElementById("imgAll").innerHTML = initialAll;
    document.getElementById("imgTue").innerHTML = initialTue;
    document.getElementById("imgWed").innerHTML = initialWed;
    document.getElementById("imgThu").innerHTML = initialThu;
    document.getElementById("imgFir").innerHTML = initialFir;
    document.getElementById("weeks").innerText = "Monday"
    // document.getElementById("weeks2").innerText = "Monday"
    inputData2 = 1;
    console.log('mon');
    draw_chart_by_Name();
}

function chooseTue(){
    document.getElementById("imgTue").innerHTML = "<img id='imgTue' src='https://cdn-icons-png.flaticon.com/512/8048/8048603.png'  style='width: 30%' alt='Tue'>"
    document.getElementById("imgAll").innerHTML = initialAll;
    document.getElementById("imgMon").innerHTML = initialMon;
    document.getElementById("imgWed").innerHTML = initialWed;
    document.getElementById("imgThu").innerHTML = initialThu;
    document.getElementById("imgFir").innerHTML = initialFir;
    document.getElementById("weeks").innerText = "Tuesday";
    // document.getElementById("weeks2").innerText = "Tuesday";
    inputData2 = 2;
    console.log('tue');
    draw_chart_by_Name()

}

function chooseWed(){
    document.getElementById("imgWed").innerHTML = "<img id='imgWed' src='https://cdn-icons-png.flaticon.com/512/8048/8048606.png'  style='width: 30%' alt='Wed'>"
    document.getElementById("imgAll").innerHTML = initialAll;
    document.getElementById("imgMon").innerHTML = initialMon;
    document.getElementById("imgTue").innerHTML = initialTue;
    document.getElementById("imgThu").innerHTML = initialThu;
    document.getElementById("imgFir").innerHTML = initialFir;
    document.getElementById("weeks").innerText = "Wednesday";
    // document.getElementById("weeks2").innerText = "Wednesday";
    inputData2 = 3;
    console.log('wed');
    draw_chart_by_Name();

}
function chooseThu(){
    document.getElementById("imgThu").innerHTML = "<img id='imgThu' src='https://cdn-icons-png.flaticon.com/512/8048/8048609.png'  style='width: 30%' alt='Thu'>"
    document.getElementById("imgAll").innerHTML = initialAll;
    document.getElementById("imgMon").innerHTML = initialMon;
    document.getElementById("imgTue").innerHTML = initialTue;
    document.getElementById("imgWed").innerHTML = initialWed;
    document.getElementById("imgFir").innerHTML = initialFir;
    document.getElementById("weeks").innerText = "Thursday";
    // document.getElementById("weeks2").innerText = "Thursday";
    inputData2 = 4;
    console.log('thu');
    draw_chart_by_Name()

}
function chooseFir(){
    document.getElementById("imgFir").innerHTML = "<img id='imgFir' src='https://cdn-icons-png.flaticon.com/512/8048/8048612.png'  style='width: 30%' alt='Fir'>"
    document.getElementById("imgAll").innerHTML = initialAll;
    document.getElementById("imgMon").innerHTML= initialMon;
    document.getElementById("imgTue").innerHTML= initialTue;
    document.getElementById("imgWed").innerHTML= initialWed;
    document.getElementById("imgThu").innerHTML= initialThu;
    document.getElementById("weeks").innerText = "Friday";
    // document.getElementById("weeks2").innerText = "Friday";
    inputData2 = 5;
    console.log('fir');
    draw_chart_by_Name();

}