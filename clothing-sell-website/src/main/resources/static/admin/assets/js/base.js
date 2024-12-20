function fetchData(url, method, body = null) {
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

function isTokenValid(token) {
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
