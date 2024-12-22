window.onload = function() {
    const token = localStorage.getItem("jwtToken");

    if (token && isTokenValid(token)) {
        fetchData("/clothing-sell/admin/info", "GET")
    }
};

//document.getElementById("approveBtn").addEventListener("click", function () {
//    const button = $(this);
//    const id = this.getAttribute("data-id");
//
//    const staffName = this.getAttribute("staff-name")
//    const url = `/clothing-sell/admin/order/update-order/${id}`;
//    // Disable nút và đổi màu sang xám
//    button.prop('disabled', true);
//    button.removeClass('btn-success').addClass('btn-secondary');
//
//    const row = button.closest('tr');
//    const statusCell = row.find('td:eq(3)'); // Cột thứ 4 (index 3) là cột status
//    statusCell.text('Đã giao');
//
//    const staffNameCell = row.find('td:eq(4)');
//    staffNameCell.text(staffName);
//    fetchData(url, "POST", id)
//});

// Tìm nút có ID động bằng cách lặp qua class hoặc thuộc tính data-id
document.querySelectorAll(".approveBtn").forEach((button) => {
    button.addEventListener("click", function () {
        const id = this.getAttribute("data-id");
        const url = `/clothing-sell/admin/order/update-order/${id}`;
        const staffName = this.getAttribute("staff-name");

        const button = $(this);
        button.prop('disabled', true);
        button.removeClass('btn-success').addClass('btn-secondary');

        // Cập nhật giao diện
        const row = $(this).closest("tr");
        row.find("td:eq(3)").text("Đã giao");
        row.find("td:eq(4)").text(staffName);
        fetchData(url, "POST", id)
    });
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