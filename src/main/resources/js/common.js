async function asyncAjax(url, data, method) {
    return new Promise((resolve, reject) => {
        $.ajax({
            url : url,
            data : data,
            method : method,
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