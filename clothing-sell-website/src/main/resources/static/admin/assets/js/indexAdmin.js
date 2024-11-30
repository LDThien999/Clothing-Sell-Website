window.onload = function() {
    const token = localStorage.getItem("jwtToken");

    if (token && isTokenValid(token)) {
        fetchData("/clothing-sell/admin/info", "GET")
    }
};

document.getElementById("approveBtn").addEventListener("click", function () {
    const button = $(this);
    const id = this.getAttribute("data-id");
    const url = `/clothing-sell/admin/order/update-order/${id}`;
    // Disable nút và đổi màu sang xám
    button.prop('disabled', true);
    button.removeClass('btn-success').addClass('btn-secondary');

    const row = button.closest('tr');
    const statusCell = row.find('td:eq(3)'); // Cột thứ 4 (index 3) là cột status
    statusCell.text('Đã giao');
    fetchData(url, "POST", id)
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