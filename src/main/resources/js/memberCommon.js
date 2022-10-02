
$(document).ready(function () {
/* 회원가입 */
    $('#btnSignupMember').click(function () {
        console.log('hi');
        let data = {
            name : $('#name').val(),
            homeAddress : {
                city : $('#homeCity').val(),
                street : $('#homeStreet').val(),
                zipCode : $('#homeZipcode').val()
            },
            companyAddress : {
                city : $('#compCity').val(),
                street : $('#compStreet').val(),
                zipCode : $('#compZipcode').val()
            }
        }

        let result = asyncAjax("/member", data, 'post');
        if(result) {
            alert('성공');
        } else {
            console.log(result);
        }
    });


});
