
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="servlet.Cordinate" %>
<%@ page import="java.util.Vector" %>
<!DOCTYPE html>
<html lang="ru">

<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"
          name="viewport">
    <meta content="ie=edge" http-equiv="X-UA-Compatible">

    <link href="img/face_pensive_icon_227321.png" rel="shortcut icon" type="image/x-icon">
    <link href="styles/style.css" rel="stylesheet">
    <link href="styles/table.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="js/script.js" type="text/javascript"></script>
    <script src="js/graph.js" type="text/javascript"></script>

    <title>Web2</title>
</head>

<body>
<div class="wrapper">
    <header>
        <span>Вариант 3231205</span>
        <span class="student-info">Соболев Иван, P32312</span>
    </header>
    <div class="content" id="content">
        <div class="img-form">
            <div class="areas-img centered">
                <canvas id="plot" width="300" height="300">
                </canvas>
            </div>
            <form class="data-send-form" id="form" oninput="onYInpChange()">
                <div class="form-input">
                    <div class="check-buttons centered" id="x_value_select">
                        <p><strong>X: </strong></p>
                        <p><label class="check-label" for="check-button--3">-3</label>
                            <input class="check-input" id="check-button--3" name="x_value" type="checkbox" value="-3">
                        </p>
                        <p><label class="check-label" for="check-button--2">-2</label>
                            <input class="check-input" id="check-button--2" name="x_value" type="checkbox" value="-2">
                        </p>
                        <p><label class="check-label" for="check-button--1">-1</label>
                            <input class="check-input" id="check-button--1" name="x_value" type="checkbox" value="-1">
                        </p>
                        <p><label class="check-label" for="check-button-0">0</label>
                            <input class="check-input" id="check-button-0" name="x_value" type="checkbox" value="0">
                        </p>
                        <p><label class="check-label" for="check-button-1">1</label>
                            <input class="check-input" id="check-button-1" name="x_value" type="checkbox" value="1">
                        </p>
                        <p><label class="check-label" for="check-button-2">2</label>
                            <input class="check-input" id="check-button-2" name="x_value" type="checkbox" value="2">
                        </p>
                        <p><label class="check-label" for="check-button-3">3</label>
                            <input checked class="check-input" id="check-button-3" name="x_value" type="checkbox"
                                   value="3">
                        </p>
                        <p><label class="check-label" for="check-button-4">4</label>
                            <input class="check-input" id="check-button-4" name="x_value" type="checkbox" value="4">
                        </p>
                        <p><label class="check-label" for="check-button-5">5</label>
                            <input class="check-input" id="check-button-5" name="x_value" type="checkbox" value="5">
                        </p>
                    </div>
                    <div class="form-input-y"><label for="y-value-select"><strong>Y: </strong></label>
                        <input id="y-value-select" maxlength="7" name="y_value" placeholder="-3 ... 5" type="text"
                               value=""/>
                    </div>
                    <div class="form-input-r"><label for="r-value-select"><strong>R: </strong></label>
                        <input id="r-value-select" maxlength="1" name="r_value" placeholder="1 ... 4" type="text"
                               value=""/>
                    </div>
                    <div class="form-buttons">
                        <button class="check-button" id="submitButton" type="submit">Проверить</button>
                        <button class="clear-button" id="clearButton" type="button">Очистить</button>
                    </div>
                    <div id="error-log"></div>
                </div>
            </form>
        </div>
        <div class="table-wrapper" >
            <div class="table">
                <div class="table-header">
                    <div class="header__item"><a class="filter__link" id="x-table">X</a></div>
                    <div class="header__item"><a class="filter__link" id="y-table">Y</a></div>
                    <div class="header__item"><a class="filter__link" id="r-table">R</a></div>
                    <div class="header__item"><a class="filter__link" id="result-table">result</a></div>
                    <div class="header__item"><a class="filter__link" id="time-table">Current Time</a></div>
                    <div class="header__item"><a class="filter__link" id="cr-time-table">Execution Time</a>
                    </div>
                </div>
                <div class="table-content" id="ans" >
                    <% Object attribute = request.getSession().getServletContext().getAttribute("userData");
                        if (!(attribute == null || ((Vector<Cordinate>) attribute).size() == 0)) {
                            Vector<Cordinate> coordinates = (Vector<Cordinate>) attribute;
                            for (Cordinate element : coordinates) {%>
                    <div class="table-row">
                        <div class="table-data"><%= element.getX() %></div>
                        <div class="table-data"><%= element.getY() %></div>
                        <div class="table-data"><%= element.getR() %></div>
                        <div class="table-data"><% out.print(element.getCorrectWords()); %></div>
                        <div class="table-data"><%= element.getRequestTime() %></div>
                        <div class="table-data"><%= element.getExecutionTime() %></div>
                    </div>
                    <%}} %>
                </div>
            </div>
        </div>

    </div>

    <footer class="centered">
        <span class="student-info-footer">&copy;Ivanio1, 2022</span>
        <p class="student-footer-icon">
            <a href="https://github.com/Ivanio1" title="GitHub">
                <img alt="Developer GitHub" src="img/github_PNG19.png">
            </a>
        </p>
    </footer>
</div>
</body>
<script type="text/javascript">
    $(document).ready(drawGraph());
</script>
</html>
