function asyncAjax(url, reqData, method) {
    return new Promise((resolve, reject) => {
        $.ajax({
            url : url,
            data : JSON.stringify(reqData),
            method : method,
            contentType: 'application/json;charset=UTF-8',
            dataType : 'json',
            success : function (data) {
                resolve(data);
            },
            error : function (error) {
                reject(error);
            }
        });
    });
}