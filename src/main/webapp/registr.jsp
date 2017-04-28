<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="#" id='send' method='POST'>
    <fieldset>
        <label>Имя</label>
        <input type='text' name='name' placeholder='Имя' required><span></span>
    </fieldset>
    <fieldset>
        <label>Эл. почта</label>
        <input type='email' name='email' placeholder='Эл. почта' required><span></span>
        <br>
        <label>Пароль</label>
        <input type='password' name='pass' placeholder='Пароль' required><span></span>
        <br>
        <label>Повторите</label>
        <input type='password' name='repeat' placeholder='Повторите' required><span></span>
        <br>
    </fieldset>
</form>
<div class="sum">
    <input type="submit" value="Зарегестрироваться" form='send'>
</div>

<style>p {
    font-size: 14px;
}

form {
    font-family: sans-serif;
    border: 1px solid #D4DADB;
    padding: 0 15px;
    border-radius: 3px;
    background-color: white;
}

form img {
    width: 80px;
    vertical-align: middle;
}

fieldset {
    padding: 0;
    margin: 0;
    margin: 15px 0;
    border: none;
}

label {
    width: 80px;
    font-size: 14px;
    display: inline-block;
}

form input {
    border: 1px solid #C1C1C1;
    padding: 3px 5px;
    margin: 7px;
    font-weight: bolder;
    width: 260px;
}

form input:valid + span {
    content: url(http://htmlbook.ru/example/images/ok.png);
}

form input:invalid + span {
    content: url(http://www.clker.com/cliparts/4/3/1/f/1195436930767206781not_ok_mark_h_kon_l_vdal_01.svg);
    height: 15px;
    vertical-align: middle;
    margin-left: -3px;
}

.sum {
    margin-top: 10px;
    padding: 10px;
    background-color: #E3E3E3;
    border-radius: 3px;
}

.sum input {
    padding: 2px 15px;
    margin-left: 95px;
    border-radius: 15px;
    border: 2px solid #008F00;
    background: linear-gradient(#00E000, #008F00);
    color: white;
}
</style>
</body>
</html>
