$(document).ready(function(){
    $('.edit').on('click',function(e){
        let id;
        e.preventDefault();
        let currow=$(this).closest('tr');

        $('#titleEdit').val(currow.find('td:eq(0)').text());

        $('#contentEdit').val(currow.find('td:eq(1)').text());
        $('#dateEdit').val(currow.find('td:eq(2)').text());
        $('#authorEdit').val(currow.find('td:eq(3)').text());
        id= currow.find('td:eq(0)').attr('postid');
        $('.title').attr('data-id',id);

        //th:href="@{/posts/edit/{id}(id=${post._id})}"
        console.log(id);
    });
});