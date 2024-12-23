$.noConflict();
/*CÁC NÚT ĐÃ GIAO TẤT CẢ CHƯA DUYỆT*/
// Đảm bảo DOM đã load xong


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




//jQuery(document).ready(function($) {
//
//	"use strict";
//
//	[].slice.call( document.querySelectorAll( 'select.cs-select' ) ).forEach( function(el) {
//		new SelectFx(el);
//	} );
//
//	jQuery('.selectpicker').selectpicker;
//
//
//	$('#menuToggle').on('click', function(event) {
//		$('body').toggleClass('open');
//	});
//
//	$('.search-trigger').on('click', function(event) {
//		event.preventDefault();
//		event.stopPropagation();
//		$('.search-trigger').parent('.header-left').addClass('open');
//	});
//
//	$('.search-close').on('click', function(event) {
//		event.preventDefault();
//		event.stopPropagation();
//		$('.search-trigger').parent('.header-left').removeClass('open');
//	});
//
//	// $('.user-area> a').on('click', function(event) {
//	// 	event.preventDefault();
//	// 	event.stopPropagation();
//	// 	$('.user-menu').parent().removeClass('open');
//	// 	$('.user-menu').parent().toggleClass('open');
//	// });
//
//	 $(document).on('shown.bs.modal', function() {
//            // Đảm bảo không có backdrop nào còn lại khi mở modal mới
//            $('.modal-backdrop').remove();
////            $('body').addClass('modal-open');
//        });
//
//
//});

(function($) {
    'use strict';
    $(document).on('shown.bs.modal', function() {
                // Đảm bảo không có backdrop nào còn lại khi mở modal mới
                $('.modal-backdrop').remove();
    //            $('body').addClass('modal-open');
            });

    // Biến global cho DataTable
    var dataTable;
    // Định nghĩa ngôn ngữ tiếng Việt cho DataTable
        var vietnameseLanguage = {
            "sProcessing":   "Đang xử lý...",
            "sLengthMenu":   "Xem _MENU_ mục",
            "sZeroRecords":  "Không tìm thấy dòng nào phù hợp",
            "sInfo":         "Đang xem _START_ đến _END_ trong tổng số _TOTAL_ mục",
            "sInfoEmpty":    "Đang xem 0 đến 0 trong tổng số 0 mục",
            "sInfoFiltered": "(được lọc từ _MAX_ mục)",
            "sInfoPostFix":  "",
            "sSearch":       "Tìm:",
            "sUrl":          "",
            "oPaginate": {
                "sFirst":    "Đầu",
                "sPrevious": "Trước",
                "sNext":     "Tiếp",
                "sLast":     "Cuối"
            }
        };

    // Khởi tạo các components
    function initializeComponents() {
         // Khởi tạo DataTable
                dataTable = $('#bootstrap-data-table-export-orders').DataTable({
                    "order": [[ 3, "asc" ]], // Sắp xếp theo cột trạng thái
                    "language": vietnameseLanguage,
                    "drawCallback": function() {
                        // Đảm bảo "Chưa duyệt" luôn ở trên "Đã giao"
                        var api = this.api();
                        var rows = api.rows({page: 'current'}).nodes();
                        var last = null;

                        api.column(3, {page: 'current'}).data().each(function(group, i) {
                            if (last !== group) {
                                $(rows).eq(i).before(
                                    '<tr class="group"><td colspan="7">' + group + '</td></tr>'
                                );
                                last = group;
                            }
                        });
                    }
                });

        // Khởi tạo Select nếu có
        if (typeof SelectFx !== 'undefined') {
            [].slice.call(document.querySelectorAll('select.cs-select')).forEach(function(el) {
                new SelectFx(el);
            });
        }

        // Khởi tạo Bootstrap Select nếu có
        if ($.fn.selectpicker) {
            $('.selectpicker').selectpicker();
        }
    }

    // Xử lý các sự kiện
    function handleEvents() {
        // Xử lý search
        $('.search-trigger').on('click', function(e) {
            e.preventDefault();
            e.stopPropagation();
            $('.search-trigger').parent('.header-left').addClass('open');
        });

        $('.search-close').on('click', function(e) {
            e.preventDefault();
            e.stopPropagation();
            $('.search-trigger').parent('.header-left').removeClass('open');
        });

        // Xử lý nút approve
        $(document).on('click', '.approveBtn', function() {
            const orderId = $(this).data('id');
            if (confirm('Bạn có chắc chắn muốn chấp thuận đơn hàng này?')) {
                const button = $(this);
                const staffName = $('#staffInfo').attr('data-staff-name');

                button.prop('disabled', true)
                      .removeClass('btn-success')
                      .addClass('btn-secondary');

                const row = button.closest('tr');
                row.find('td:eq(3)').text('Đã giao');
                row.find('td:eq(4)').text(staffName);

                const url = `/clothing-sell/admin/order/update-order/${orderId}`;
                fetchData(url, "POST", orderId);
            }
        });

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
        // 5. Action Buttons Creation
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

        // Xử lý radio buttons
        $('input[name="options"]').on('change', function() {
            const status = $(this).val();
            // Reset search và order trước
            dataTable.search('').columns().search('');
            var filterValue = $(this).val();

                        if (filterValue === 'all') {
                            dataTable.order([3, 'asc']).draw(); // Sắp xếp "Chưa duyệt" lên trên
                        } else if (filterValue === 'delivered') {
                            dataTable.column(3).search('Đã giao').draw();
                        } else if (filterValue === 'pending') {
                            dataTable.column(3).search('Chưa duyệt').draw();
                        }

            if (status === 'all') {
                        // Gọi API để lấy tất cả dữ liệu
                        $.ajax({
                            url: '/clothing-sell/admin/api/orders/filter',
                            method: 'GET',
                            data: { status: status },
                            success: function(response) {
                                dataTable.clear();
                                response.forEach(function(order) {
                                    dataTable.row.add([
                                        order.orderId,
                                        formatDate(order.date),
                                        order.paymentMethod,
                                        order.status ? 'Đã giao' : 'Chưa duyệt',
                                        order.staff ? order.staff.name : '',
                                        order.customer ? order.customer.name : '',
                                        createActionButtons(order)
                                    ]);
                                });
                                // Sắp xếp "Chưa duyệt" lên trên
                                dataTable.order([3, 'asc']).draw();
                            },
                            error: function(error) {
                                console.error('Lỗi khi lọc dữ liệu:', error);
                                alert('Có lỗi xảy ra khi tải dữ liệu!');
                            }
                        });
                    } else {
                        // Xử lý cho "Đã giao" và "Chưa duyệt"
                        $.ajax({
                            url: '/clothing-sell/admin/api/orders/filter',
                            method: 'GET',
                            data: { status: status },
                            success: function(response) {
                                dataTable.clear();
                                response.forEach(function(order) {
                                    dataTable.row.add([
                                        order.orderId,
                                        formatDate(order.date),
                                        order.paymentMethod,
                                        order.status ? 'Đã giao' : 'Chưa duyệt',
                                        order.staff ? order.staff.name : '',
                                        order.customer ? order.customer.name : '',
                                        createActionButtons(order)
                                    ]);
                                });
                                dataTable.draw();
                            },
                            error: function(error) {
                                console.error('Lỗi khi lọc dữ liệu:', error);
                                alert('Có lỗi xảy ra khi tải dữ liệu!');
                            }
                        });
                    }
        });
    }

    // Khởi tạo khi document ready
    $(document).ready(function() {
        initializeComponents();
        handleEvents();
    });

})(jQuery);