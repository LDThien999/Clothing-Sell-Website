window.onload = function() {
    const token = localStorage.getItem("jwtToken");

    if (token && isTokenValid(token)) {
        fetchData("/clothing-sell/admin/info", "GET")
    }
};

document.getElementById("approveBtn").addEventListener("click", function () {
    const id = this.getAttribute("data-id");
    const url = `/clothing-sell/admin/order/update-order/${id}`;
    fetchData(url, "GET")
});


//function approveOrder(orderId) {
//    const token = localStorage.getItem("jwtToken");
//
//        console.log()
//        if (token && isTokenValid(token)) {
//        const url = `/clothing-sell/admin/order/update-order/${orderId}`;
//            fetchData(url, "GET")
//        }
//};