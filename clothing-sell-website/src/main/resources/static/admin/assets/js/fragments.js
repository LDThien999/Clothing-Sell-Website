const logoutButton = document.getElementById("logout-btn");
const token = localStorage.getItem("jwtStaffToken");

document.addEventListener("DOMContentLoaded", () => {
    logoutButton.addEventListener("click", function (e) {
        e.preventDefault();

        const confirmLogout = window.confirm("Bạn có chắc chắn muốn đăng xuất?");
        if (confirmLogout) {
            localStorage.removeItem("jwtStaffToken");
            window.location.href = "/clothing-sell/index.html";
        }
    });


    if (!token) {
        alert("Bạn cần đăng nhập để xem thông tin");
        window.location.href = "/clothing-sell/index.html";
    } else {

        getUserInfo()
            .then(data => {
                if (data) {
                } else {
                    alert("Vui lòng đăng nhập lại!");
                    window.location.href = "/clothing-sell/login.html";
                }
            })
            .catch(() => {
                alert("Đã xảy ra vấn đề xác thực! \n Vui lòng đăng nhập lại");
                window.location.href = "/clothing-sell/login.html";
            });
    }

});

function getUserInfo() {
    return fetch("/clothing-sell/user/info", {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + localStorage.getItem("jwtStaffToken")
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.text();
        })
        .catch(error => {
            console.error("Error fetching user info:", error);
            throw error; // Ném lỗi nếu cần xử lý ở nơi gọi hàm
        });
}