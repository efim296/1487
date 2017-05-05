<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TASKS</title>
</head>
<body>
<h1>TASKS</h1>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"
        integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
<script>
    $.ajax({
        url: '/rest/tasks',
        dataType: 'json',
        type: 'POST',
        contentType: 'application/json',
        mimeType: 'application/json',
        data: {
            name: 'test2',
            date_from: Date.now,
            date_to: (Date.now + 90000)
        }
    }).done(function () {
        console.log(arguments);
    });


    $.ajax({
        url: '/rest/tasks/2',
        dataType: 'json',
        type: 'PUT',
        contentType: 'application/json',
        mimeType: 'application/json',
        data: {
            id: 2,
            name: 'update',
            date_from: Date.now,
            date_to: (Date.now + 90000)
        }
    }).done(function () {
        console.log(arguments);
    });

    $.ajax({
        url: '/rest/tasks/1',
        dataType: 'json',
        type: 'DELETE',
        contentType: 'application/json',
        mimeType: 'application/json'
    }).done(function () {
        console.log(arguments);
    });
</script>
</body>
</html>
