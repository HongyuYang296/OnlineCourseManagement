document.querySelectorAll("[data-dialog]").forEach(button => {
    button.addEventListener("click", (e) =>{
        document.getElementById(button.dataset.dialog).showModal();
        setTimeout(() => {
            document.getElementById(button.dataset.dialog).close();
        }, (8000))
            const elm = document.getElementById(button.dataset.dialog)
        const clickToClose = function (event) {
            const rect = elm.getBoundingClientRect();
            const isInDialog=(rect.top <= event.clientY && event.clientY <= rect.top + rect.height
                && rect.left <= event.clientX && event.clientX <= rect.left + rect.width);
            if (!isInDialog) {
                elm.close();
            }
        }
        elm.addEventListener('click', clickToClose);
        elm.addEventListener('close', () => {
            elm.removeEventListener("click", clickToClose , false);
        });
    })
})

