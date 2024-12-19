function getUserInfo() {
    return fetch("/clothing-sell/user/info", {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + localStorage.getItem("jwtToken")
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

function fetchDataApi(url, method, body = null) {
    const token = localStorage.getItem("jwtToken");
    const headers = {
        "Content-Type": "application/json"
    };
    if (token) {
        headers["Authorization"] = "Bearer " + token;
    }
    const options = {
        method: method,
        headers: headers,
    };
    if (body) {
        options.body = JSON.stringify(body);
    }

    return  fetch(url, options);
}

function TokenValid(token) {
    try {
        const payload = JSON.parse(atob(token.split(".")[1]));
        const currentTime = Math.floor(Date.now() / 1000);

        // Kiểm tra expiration và loại bỏ trường hợp anonymous
        return payload.exp > currentTime && payload.sub !== "anonymousUser";
    } catch (error) {
        console.error("Token không hợp lệ:", error);
        return false;
    }
}


document.addEventListener("DOMContentLoaded", () => {
    const nonLoginSections = document.querySelectorAll(".non-login");
    const loggedInSections = document.querySelectorAll(".logged-in");
    const logoutButtons = document.querySelectorAll(".logout-btn");

    logoutButtons.forEach(function(logoutButton) {
        logoutButton.addEventListener("click", function(e) {
            //Ngừng sự kiện mặc định
            e.preventDefault();

            const confirmLogout = window.confirm("Bạn có chắc chắn muốn đăng xuất?");
            if (confirmLogout) {
                localStorage.removeItem("jwtToken");
                window.location.href = "/clothing-sell/index.html";
            }
        });
    });

    // Gọi hàm lấy thông tin người dùng
    getUserInfo()
        .then(data => {
            if (data) {
                loggedInSections.forEach(section => section.classList.remove("hidden"));
                nonLoginSections.forEach(section => section.classList.add("hidden"));
            } else {
                nonLoginSections.forEach(section => section.classList.remove("hidden"));
                loggedInSections.forEach(section => section.classList.add("hidden"));
            }
        })
        .catch(() => {
            // Xử lý lỗi, mặc định hiển thị phần 'non-login' và ẩn phần 'logged-in'
            nonLoginSections.forEach(section => section.classList.remove("hidden"));
            loggedInSections.forEach(section => section.classList.add("hidden"));
        });
});
