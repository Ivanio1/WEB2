const drawGraph = function () {
    const plot_canvas = document.getElementById("plot");
    const plot_context = plot_canvas.getContext("2d");
    let canvWidth = plot_canvas.width;
    plot_context.beginPath();
    plot_context.arc(canvWidth / 2, canvWidth / 2, canvWidth / 6, Math.PI, Math.PI * 3 / 2);
    plot_context.lineTo(canvWidth / 2, canvWidth / 2);
    plot_context.closePath();
    plot_context.rect(canvWidth / 3, canvWidth / 2, canvWidth / 6, canvWidth / 3);
    plot_context.fillStyle = "rgb(0,89,206)";
    plot_context.fill();
    plot_context.beginPath();
    plot_context.moveTo(canvWidth / 2, canvWidth / 2);
    plot_context.lineTo(canvWidth / 2, canvWidth * 5 / 6);
    plot_context.lineTo(canvWidth * 5 / 6, canvWidth / 2);
    plot_context.lineTo(canvWidth / 2, canvWidth / 2);
    plot_context.closePath();
    plot_context.fillStyle = "rgb(0,89,206)";
    plot_context.fill();
    plot_context.beginPath();
//Ox
    plot_context.moveTo(canvWidth / 10, canvWidth / 2);
    plot_context.lineTo(canvWidth * 0.9, canvWidth / 2);
    plot_context.lineTo(260, 140);
    plot_context.moveTo(270, 150);
    plot_context.lineTo(260, 160);
//Oy
    plot_context.moveTo(150, 30);
    plot_context.lineTo(140, 40);
    plot_context.moveTo(150, 30);
    plot_context.lineTo(160, 40);
    plot_context.moveTo(150, 30);
    plot_context.lineTo(150, 270);

    plot_context.moveTo(30, 150);
    plot_context.closePath();
    plot_context.stroke();

};


function drawPoint(e) {
    const r = document.getElementById("r-value-select").value;
    if (r === "" || isNaN(r)) {
        document.querySelector('#error-log').textContent =
            "Выберите значение R!";
    } else {
        const point = getCursorPosition(e);
        const plot_canvas = document.getElementById("plot");
        const plot_context = plot_canvas.getContext("2d");
        plot_context.beginPath();
        plot_context.rect(point.x, point.y, 5, 5);
        point.x = ((point.x - 150) / 100 * r)*1.1;
        point.y = ((-point.y + 150) / 100 * r)*1.1;
        $.ajax({
            type: "POST",
            url: "controller",
            data:
                {
                    x_value: point.x.toFixed(2),
                    y_value: point.y.toFixed(2),
                    r_value: r
                },
            success: data => {
                document.querySelector('#ans').innerHTML = data;
                plot_context.fillStyle = 'red';
                plot_context.fill();

            },
            error: (jqXHR, textStatus, errorThrown) =>
                document.querySelector('#error-log').innerHTML = "Ошибка HTTP: " + jqXHR.status + "(" + errorThrown + ")",
            dataType: "html"
        });
    }
}

function getCursorPosition(e) {
    let x;
    let y;
    const plot_canvas = document.getElementById("plot");
    if (e.pageX !== undefined && e.pageY !== undefined) {
        x = e.pageX;
        y = e.pageY;
    } else {
        x = e.clientX + document.body.scrollLeft +
            document.documentElement.scrollLeft;
        y = e.clientY + document.body.scrollTop +
            document.documentElement.scrollTop;
    }
    return {
        x: x - plot_canvas.getBoundingClientRect().left,
        y: y - plot_canvas.getBoundingClientRect().top
    }
}