const checkElement = function (elemAddress) {
    let elem = $(elemAddress);
    if (elem.val().replace(",", ".") !== "-") {
        let value = Number(elem.val().replace(",", "."));
        if (elemAddress[1] === 'r') {
            if (!($.isNumeric(elem.val().replace(",", ".")) || elem.val().replace(",", ".") === "-" || elem
                .val().replace(",", ".") === ""))
                document.getElementById("r-value-select").value = elem.val().slice(0, elem.val().length -
                    1);
            if (value < 1 || value > 4 || isNaN(value) || /[\s]+/.test(elem.val()) || elem.val() ===
                "undefined" || elem.val() === "") {
                $(elem).removeClass().addClass("is-invalid");
                document.querySelector('#error-log').innerHTML ="Радиус - целое число от 1 до 4"
                return false;
            }
        }
        if (elemAddress[1] === 'y') {
            if (!($.isNumeric(elem.val().replace(",", ".")) || elem.val().replace(",", ".") === "-" || elem
                .val().replace(",", ".") === ""))
                document.getElementById("y-value-select").value = elem.val().slice(0, elem.val().length -
                    1);
            if (value < -3 || value > 5 || isNaN(value) || /[\s]+/.test(elem
                .val()) || elem.val() === "") {
                $(elem).removeClass().addClass("is-invalid");
                return false;
            }
        }
        document.querySelector('#error-log').textContent = " ";
        elem.removeClass().addClass("is-valid");
        return true;
    }
};

let onYInpChange = function () {
    if (!checkElement("#r-value-select")) {
        // incorrect
    } else {
        // correct.jpg;
    }
    if (!checkElement("#y-value-select")) {
        // incorrect.jpg
    } else {
        // correct.jpg
    }
};

const submit = function (e) {
    e.preventDefault();
    if (!checkElement("#r-value-select")) {
        checkElement("#y-value-select");
        document.querySelector('#error-log').textContent =
            "Значение R должно быть в диапазоне [1;4]";
        return;
    } else if (!checkElement("#y-value-select")) {
        document.querySelector('#error-log').textContent =
            "Значение Y должно быть в диапазоне [-3;5]";
        return;
    } else if ($('input[name=x_value]:checked', '#form').val() === undefined) {
        document.querySelector('#error-log').textContent =
            "Выберите значение X!";
        return;
    }
    let checkboxes = document.querySelectorAll("input[type='checkbox']")
    checkboxes.forEach((element) => {
        if (element.checked) {
            sendReq(element.value)
        }
    })

};
function sendReq(x){
    $.ajax({
        type: "POST",
        url: "controller",
        data:
            {
                r_value: $("#r-value-select").val(),
                y_value: $("#y-value-select").val(),
                x_value: x
            },
        success: data => document.querySelector('#ans').innerHTML = data,
        error: (jqXHR, textStatus, errorThrown) =>
            document.querySelector('#error-log').innerHTML = "Ошибка HTTP: " + jqXHR.status + "(" + errorThrown + ")",
        dataType: "html"
    });
}

const clear = function(e) {
    if (confirm('Вы точно хотите очистить таблицу??')) {
        e.preventDefault();
        $.ajax({
            type: "POST",
            url: "controller",
            data: {
                type: "clear"
            },
            success: data => {
                document.querySelector('#ans').innerHTML = data;
                const plot_canvas = document.getElementById("plot");
                const context = plot_canvas.getContext('2d');
                context.clearRect(0, 0, plot_canvas.width, plot_canvas.height);
                drawGraph();
            },
            error: (jqXHR, textStatus, errorThrown) =>
                document.querySelector('#error-log').innerHTML = "Ошибка HTTP: " + jqXHR.status +
                    "(" + errorThrown + ")",
            dataType: "html"
        });
    }
};

document.addEventListener('DOMContentLoaded', ()=> {
    document.getElementById("plot").addEventListener('click', drawPoint);
    document.querySelector('#submitButton').addEventListener('click', submit);
    document.querySelector('#clearButton').addEventListener('click', clear);
});
