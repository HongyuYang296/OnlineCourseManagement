
function capture() {
    html2canvas(document.getElementById("screenShot"),{
        allowTaint: true,
        useCORS: true,
        scrollY: -window.scrollY}
    ).then((canvas) => {
        let a = document.createElement("a");
        a.download = "ss.png";
        a.href = canvas.toDataURL("image/png");
        a.click(); // MAY NOT ALWAYS WORK!
    });

}