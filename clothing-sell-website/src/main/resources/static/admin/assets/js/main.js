$.noConflict();

//sendNotification("info", "Hello user!");

//function sendNotification(type, text) {
//  let notificationBox = document.querySelector(".notification-box");
//  const alerts = {
//    info: {
//      icon: `<svg class="w-6 h-6 mr-2" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor">
//  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 16h-1v-4h-1m1-4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
//</svg>`,
//      color: "bg-info"
//    },
//    error: {
//      icon: `<svg xmlns="http://www.w3.org/2000/svg" class="w-6 h-6 mr-2" fill="none" viewBox="0 0 24 24" stroke="currentColor">
//  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4m0 4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
//</svg>`,
//      color: "bg-danger"
//    },
//    warning: {
//      icon: `<svg xmlns="http://www.w3.org/2000/svg" class="w-6 h-6 mr-2" fill="none" viewBox="0 0 24 24" stroke="currentColor">
//  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 9v2m0 4h.01m-6.938 4h13.856c1.54 0 2.502-1.667 1.732-3L13.732 4c-.77-1.333-2.694-1.333-3.464 0L3.34 16c-.77 1.333.192 3 1.732 3z" />
//</svg>`,
//      color: "bg-warning"
//    },
//    success: {
//      icon: `<svg xmlns="http://www.w3.org/2000/svg" class="w-6 h-6 mr-2" fill="none" viewBox="0 0 24 24" stroke="currentColor">
//  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z" />
//</svg>`,
//      color: "bg-success"
//    }
//  };
//  let component = document.createElement("div");
//  component.className = `relative flex items-center ${alerts[type].color} text-dark text-sm font-bold px-4 py-3 rounded-md opacity-0 transform transition-all duration-500 mb-1`;
//  component.innerHTML = `${alerts[type].icon}<p>${text}</p>`;
//  notificationBox.appendChild(component);
//  setTimeout(() => {
//    component.classList.remove("opacity-0");
//    component.classList.add("opacity-1");
//  }, 1); //1ms For fixing opacity on new element
//  setTimeout(() => {
//    component.classList.remove("opacity-1");
//    component.classList.add("opacity-0");
//    //component.classList.add("-translate-y-80"); //it's a little bit buggy when send multiple alerts
//    component.style.margin = 0;
//    component.style.padding = 0;
//  }, 5000);
//  setTimeout(() => {
//    component.style.setProperty("height", "0", "important");
//  }, 5100);
//  setTimeout(() => {
//    notificationBox.removeChild(component);
//  }, 5700);
//  //If you can do something more elegant than timeouts, please do, but i can't
//}

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