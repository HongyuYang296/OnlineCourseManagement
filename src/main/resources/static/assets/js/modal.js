
$(document).ready(function (){
        $('.responsive-table #updateBtn').on('click', function (event){
                event.preventDefault();
            const href = $(this).attr('href');
            $.get(href, function (user, status) {
                    $('#idEdit').val(user.id);
                    $('#firstNameEdit').val(user.firstname);
                    $('#lastNameEdit').val(user.lastname);
                    $('#emailEdit').val(user.email);
                    $('#codeEdit').val(user.code);
                });
                $('#editModal').modal('show');
                console.log("works22?")
            }
        )
    }
)

$(document).ready(function (){
        $('.responsive-table #updateBtn2').on('click', function (event){
                event.preventDefault();
                const href = $(this).attr('href');
                $.get(href, function (user, status) {
                    $('#idEdit').val(user.id);
                    $('#firstNameEdit').val(user.firstName);
                    $('#lastNameEdit').val(user.lastName);
                    $('#joinLeftTime').val(user.joinLeftTime);
                    $('#timeSession').val(user.timeSession);
                    $('#emailEdit').val(user.email);
                    $('#codeEdit').val(user.code);
                    $('#codeDate').val(user.date);
                    $('#codeType').val(user.type);
                });
                $('#editModal').modal('show');
                console.log("works edit??")
            }
        )
    }
)


$(document).ready(function (){

        $('.responsive-table #updateBtn3').on('click', function (event){
                event.preventDefault();
                const href = $(this).attr('href');
                $.get(href, function (user, status) {
                    $('#idEdit').val(user.id);
                    $('#nameEdit').val(user.name);
                    $('#codeEdit').val(user.code);
                    $('#locEdit').val(user.loc);
                });
                $('#editModal').modal('show');
                console.log("works33?")
            }
        )
    }
)

$(document).ready(function (){

        $('.responsive-table #deleteBtn').on('click', function (event){
                event.preventDefault();
                var href = $(this).attr('href')
                $('#myModal #delRef').attr('href', href);
                $('#myModal').modal('show');
                console.log("works33?")
            }
        )
    }
)


$(document).ready(function (){
        $('#uploadBtn').on('click', function (event){
                event.preventDefault();
                $('#uploadModal').modal('show');
                console.log("upload?")
            }
        )
    }
)



$(document).ready(function (){

        $('.responsive-table #deleteBtn').on('click', function (event){
                event.preventDefault();
                var href = $(this).attr('href')
                $('#myModal #delRef').attr('href', href);
                $('#myModal').modal('show');
                console.log("works33?")
            }
        )
    }
)
//
// $(document).ready(function (){
//
//         $('.table .btn').on('click', function (event){
//                 event.preventDefault();
//                 $('#exampleModal').modal();
//                 console.log("works2?")
//             }
//         )
//     }
// )
