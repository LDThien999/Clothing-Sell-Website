$.noConflict();
/*CÁC NÚT ĐÃ GIAO TẤT CẢ CHƯA DUYỆT*/
// Đảm bảo DOM đã load xong
jQuery(document).ready(function($) {
//    const staffName = document.getElementById('staffInfo').getAttribute('data-staff-name');

    // Khởi tạo DataTable một lần duy nhất
    var table = $('#bootstrap-data-table-export-orders').DataTable();

    // Hàm format date
    function formatDate(dateString) {
        if (!dateString) return '';
        const date = new Date(dateString);

        const hours = String(date.getHours()).padStart(2, '0');
        const minutes = String(date.getMinutes()).padStart(2, '0');
        const seconds = String(date.getSeconds()).padStart(2, '0');
        const day = String(date.getDate()).padStart(2, '0');
        const month = String(date.getMonth() + 1).padStart(2, '0');
        const year = date.getFullYear();

        return `${hours}:${minutes}:${seconds} ${day}-${month}-${year}`;
    }

    // Hàm tạo buttons
    function createActionButtons(order) {

        let html = '<div style="display: inline-flex;">';

        if (!order.status) {
            html += `
                <button id="approveBtn-${order.orderId}"
                 data-id="${order.orderId}"
                 class="btn btn-success approveBtn"
                 type="button">
                    Chấp thuận
                </button>`;
        } else {
            html += `
                <button type="button"
                class="btn btn-secondary disabled"
                style="pointer-events: none;">
                    Chấp thuận
                </button>`;
        }

        html += `
            <a href="/clothing-sell/admin/order/order-list/${order.orderId}">
                <button type="button" class="btn btn-primary">
                    <i class="fa fa-eye"></i>
                </button>
            </a>`;

        html += '</div>';
        return html;
    }

    // Xử lý sự kiện khi radio button thay đổi
    $('input[name="options"]').on('change', function() {
        const status = $(this).val();
        console.log("Selected status:", status);

        // Gọi API để lấy dữ liệu
        $.ajax({
            url: '/clothing-sell/admin/api/orders/filter',
            method: 'GET',
            data: { status: status },
            success: function(response) {
                console.log("API response:", response);
                // Xóa dữ liệu cũ
                table.clear();

                // Thêm dữ liệu mới
                response.forEach(function(order) {
                    table.row.add([
                        order.orderId,
                        formatDate(order.date),
                        order.paymentMethod,
                        order.status ? 'Đã giao' : 'Chưa duyệt',
                        order.staff ? order.staff.name : '',
                        order.customer ? order.customer.name : '',
                        createActionButtons(order)
                    ]);
                });

                // Vẽ lại bảng
                table.draw();
            },
            error: function(xhr, status, error) {
                console.error("Error fetching data:", error);
                alert("Có lỗi xảy ra khi tải dữ liệu!");
            }
        });
    });

    // Xử lý sự kiện nút chấp thuận
    $(document).on('click', '.approve-btn', function() {
        const orderId = $(this).data('id');
        if (confirm('Bạn có chắc chắn muốn chấp thuận đơn hàng này?')) {
            console.log('Chấp thuận đơn hàng:', orderId);
            // Thêm code xử lý chấp thuận đơn hàng ở đây
        }
    });

    // Các xử lý menu và UI khác
    $('#menuToggle').on('click', function(event) {
        $('body').toggleClass('open');
    });

    $('.search-trigger').on('click', function(event) {
        event.preventDefault();
        event.stopPropagation();
        $('.search-trigger').parent('.header-left').addClass('open');
    });

    $('.search-close').on('click', function(event) {
        event.preventDefault();
        event.stopPropagation();
        $('.search-trigger').parent('.header-left').removeClass('open');
    });
});

function sendNotification(type, message) {
  const notificationBox = document.querySelector(".notification-box");

  if (!notificationBox) {
    console.error("Notification container not found.");
    return;
  }

  // Cấu hình các kiểu thông báo
  const alerts = {
    info: { class: "alert-info", badgeClass: "badge-info", badge: "Info" },
    error: { class: "alert-danger", badgeClass: "badge-danger", badge: "Error" },
    warning: { class: "alert-warning", badgeClass: "badge-warning", badge: "Warning" },
    success: { class: "alert-success", badgeClass: "badge-success", badge: "Success" },
  };

  if (!alerts[type]) {
    console.warn(`Invalid notification type: ${type}`);
    type = "info"; // Sử dụng giá trị mặc định nếu không hợp lệ
  }

  // Tạo thông báo
  const alert = document.createElement("div");
  alert.className = `sufee-alert alert with-close ${alerts[type].class} alert-dismissible fade show`;
  alert.innerHTML = `
    <span class="badge badge-pill ${alerts[type].badgeClass}">${alerts[type].badge}</span>
    ${message}
    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
      <span aria-hidden="true">&times;</span>
    </button>
  `;


  // Thêm vào container
  notificationBox.appendChild(alert);

  // Tự động ẩn sau 5 giây
  setTimeout(() => {
    if (alert) {
      alert.classList.remove("show");
      alert.classList.add("fade");
      setTimeout(() => notificationBox.removeChild(alert), 500);
    }
  }, 5000);
}




jQuery(document).ready(function($) {

	"use strict";

	[].slice.call( document.querySelectorAll( 'select.cs-select' ) ).forEach( function(el) {
		new SelectFx(el);
	} );

	jQuery('.selectpicker').selectpicker;


	$('#menuToggle').on('click', function(event) {
		$('body').toggleClass('open');
	});

	$('.search-trigger').on('click', function(event) {
		event.preventDefault();
		event.stopPropagation();
		$('.search-trigger').parent('.header-left').addClass('open');
	});

	$('.search-close').on('click', function(event) {
		event.preventDefault();
		event.stopPropagation();
		$('.search-trigger').parent('.header-left').removeClass('open');
	});

	// $('.user-area> a').on('click', function(event) {
	// 	event.preventDefault();
	// 	event.stopPropagation();
	// 	$('.user-menu').parent().removeClass('open');
	// 	$('.user-menu').parent().toggleClass('open');
	// });

	 $(document).on('shown.bs.modal', function() {
            // Đảm bảo không có backdrop nào còn lại khi mở modal mới
            $('.modal-backdrop').remove();
//            $('body').addClass('modal-open');
        });


});