$(document).ready(function () {
    $(".edit").on("click", function (e) {
        var id;
        e.preventDefault();
        let currow = $(this).closest("tr");

        $("#titleEdit").val(currow.find("td:eq(0)").text());

        $("#contentEdit").val(currow.find("td:eq(1)").text());
        $("#dateEdit").val(currow.find("td:eq(2)").text());
        $("#authorEdit").val(currow.find("td:eq(3)").text());
        id = currow.find("td:eq(0)").attr("postid");
        $(".title").attr("data-id", id);

        //th:href="@{/posts/edit/{id}(id=${post._id})}"
        console.log(id);
    });

    function parseDate(str) {
        var arr = str.split('-');
        var months = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
        var i = 0;
        for (i; i < months.length; i++) {
            if (months[i] == arr[1]) {
                break;
            }
        }
        ++i;
        if (i < 10) {
            i = '0' + i;
        }
        var formatddate = i + '-' + arr[0] + '-' + arr[2];
        return formatddate;
    }

    //Save edit post
    $(".save").on("click", function (e) {
        let title = $("#titleEdit").val();
        let content = $("#contentEdit").val();
        let date = parseDate($("#dateEdit").val());
        let id = $(".title").attr("data-id");

        $.ajax({
            url: "/post/" + id,
            type: "PUT",
            contentType: "application/json;charset=utf-8",
            data: JSON.stringify({
                title: title, content: content, createdDate: date
            }),
            success: function (result) {
                location.reload();
            },
        });
    });

    $(".delete").on("click", function (e) {
        let id = $(this).attr("postid");
        $.ajax({
            url: "/post/" + id,
            type: "DELETE",
            contentType: "application/json;charset=utf-8",
            success: function (result) {
                alert("Delete successfull");
                location.reload();
            },
        });
    });
});
