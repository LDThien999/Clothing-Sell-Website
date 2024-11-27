window.onload = function() {
    const token = localStorage.getItem("jwtToken");

    if (token && isTokenValid(token)) {
        fetchData("/clothing-sell/admin/info", "GET")
    }
};
