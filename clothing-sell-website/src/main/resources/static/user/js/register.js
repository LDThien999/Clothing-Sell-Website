const step1Form = document.getElementById("step1");
const step2Form = document.getElementById("step2");

document.querySelector("#step2 .btn").addEventListener("click", function(event) {
    event.preventDefault();

    const registerData = {
        name: step1Form.querySelector("input[name='fullname']").value,
        phoneNum: step1Form.querySelector("input[name='telephone']").value,
        email: step1Form.querySelector("input[name='email']").value,
        creditNum: step1Form.querySelector("input[name='cardNum']").value,
        username: step2Form.querySelector("input[name='username']").value,
        password: step2Form.querySelector("input[name='password']").value,
    };

    if (!step2Form.checkValidity()) {
        step2Form.reportValidity(); //tra ve report neu co loi
        return;
    }

    const confirmPassword=step2Form.querySelector("input[name='rewritePassword']").value
    // Check pasword
    if (registerData.password !== confirmPassword) {
        showMessage(false,"Mật khẩu không khớp");
        return;
    }

    fetchData("/clothing-sell/register", "POST", registerData)
        .then(response => response.json())
        .then(data => {
            if (data.success){
                localStorage.setItem("jwtToken", data.token);
                window.location.href = "/clothing-sell/login.html";
            } else {
                showMessage(data.success, data.message || "Đăng ký thất bại!");
                console.log(data)
            }
        });
});

function showMessage(dataStatus, dataMessage) {
    let noticeContainer;
    let noticeMessage;

    if (dataStatus) {
        noticeContainer = document.getElementById("success-notice");
        noticeMessage = document.getElementById("success-message");
        console.log("succ")

    } else {
        noticeContainer = document.getElementById("error-notice");
        noticeMessage = document.getElementById("error-message");
        console.log("err")

    }

    noticeContainer.style.display = "block";
    noticeMessage.textContent = dataMessage;

    setTimeout(function() {
        noticeContainer.style.display = "none";
    }, 2000);  // Ẩn sau 2 giây
}
