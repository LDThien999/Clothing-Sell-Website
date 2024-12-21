function createChart(canvasId, apiUrl, chartConfig) {
    const ctx = document.getElementById(canvasId);
    if (ctx) {
        fetch(apiUrl)
            .then(response => response.json())
            .then(data => {
                // Chuẩn bị dữ liệu từ API
                const labels = data.map(item => item.year || item.label); // Tùy thuộc vào cấu trúc API
                const dataset = data.map(item => item.total || item.value); // Tùy thuộc vào cấu trúc API

                // Dùng cấu hình biểu đồ đã cho (chartConfig)
                const finalConfig = {
                    type: chartConfig.type || 'line',
                    data: {
                        labels: labels,
                        datasets: [{
                            data: dataset,
                            label: chartConfig.label || "Data",
                            backgroundColor: chartConfig.backgroundColor || 'rgba(0,103,255,.15)',
                            borderColor: chartConfig.borderColor || 'rgba(0,103,255,0.5)',
                            borderWidth: chartConfig.borderWidth || 3.5,
                            pointStyle: chartConfig.pointStyle || 'circle',
                            pointRadius: chartConfig.pointRadius || 5,
                            pointBorderColor: chartConfig.pointBorderColor || 'transparent',
                            pointBackgroundColor: chartConfig.pointBackgroundColor || 'rgba(0,103,255,0.5)',
                        }]
                    },
                    options: chartConfig.options || {
                        responsive: true,
                        scales: {
                            xAxes: [{
                                display: true,
                                gridLines: { display: false }
                            }],
                            yAxes: [{
                                display: true,
                                gridLines: { display: false }
                            }]
                        }
                    }
                };

                // Tạo biểu đồ
                new Chart(ctx, finalConfig);
            })
            .catch(error => console.error("Error loading chart data:", error));
    } else {
        console.error(`Canvas with ID '${canvasId}' not found.`);
    }
}

function initMonthlyChart(containerId, fetchDataCallback) {
    const container = document.getElementById(containerId);
    const prevYearBtn = document.getElementById("prevYearBtnMonth"); // container.querySelector(".prevYearBtn");
    const nextYearBtn = document.getElementById("nextYearBtnMonth"); // container.querySelector(".nextYearBtn");
    const currentYearSpan = document.getElementById("currentYear"); // container.querySelector(".currentYear");

    let currentYear = new Date().getFullYear();
    currentYearSpan.textContent = currentYear;

    let chartInstance;

    function loadChart(year) {
        fetchDataCallback(year).then(data => {
            const labels = data.map(item => `${item.month}`);
            const revenueData = data.map(item => item.revenue);
            const customerData = data.map(item => item.customerCount);

            if (chartInstance) {
                chartInstance.destroy();
            }

            chartInstance = new Chart(containerId, {
                type: 'bar',
                data: {
                    labels: labels,
                    datasets: [
                        {
                            label: "Doanh thu (triệu đồng)",
                            data: revenueData,
                            borderColor: "rgba(0, 123, 255, 0.9)",
                            backgroundColor: "rgba(0, 123, 255, 0.5)"
                        },
                        {
                            label: "Số lượng khách hàng (người)",
                            data: customerData,
                            borderColor: "rgba(40, 167, 69, 0.9)",
                            backgroundColor: "rgba(40, 167, 69, 0.5)"
                        }
                    ]
                },
                options: {
                    responsive: true,
                    scales: {
                        yAxes: [{
                            ticks: {
                                beginAtZero: true
                            }
                        }]
                    }
                }
            });
        });
    }

    prevYearBtn.addEventListener("click", function () {
        currentYear--;
        currentYearSpan.textContent = currentYear;
        loadChart(currentYear);
    });

    nextYearBtn.addEventListener("click", function () {
        currentYear++;
        currentYearSpan.textContent = currentYear;
        loadChart(currentYear);
    });

    loadChart(currentYear);
}

// Hàm lấy ngày đầu tuần (Thứ Hai)
    function getStartOfWeek(date) {
        const day = date.getDay(); // 0 = Chủ nhật
        const diff = date.getDate() - day + (day === 0 ? -6 : 1); // Điều chỉnh để bắt đầu từ Thứ Hai
        return new Date(date.setDate(diff));
    }

    // Hàm cập nhật biểu đồ
    function updateWeeklyChart(startDate) {
        // Lấy ngày bắt đầu và ngày kết thúc tuần
        const endDate = new Date(startDate);
        endDate.setDate(startDate.getDate() + 6);

        // Hiển thị tuần hiện tại
        const options = { year: "numeric", month: "long", day: "numeric" };
        document.getElementById("currentWeek").textContent =
            `${startDate.toLocaleDateString("vi-VN", options)} - ${endDate.toLocaleDateString("vi-VN", options)}`;

        // Gọi API để lấy dữ liệu
        fetch(`/clothing-sell/admin/bill/api/weekly-revenue?startDate=${startDate.toISOString().split("T")[0]}&endDate=${endDate.toISOString().split("T")[0]}`)
            .then(response => response.json())
            .then(data => {
                const labels = ["Chủ nhật", "Thứ Hai", "Thứ Ba", "Thứ Tư", "Thứ Năm", "Thứ Sáu", "Thứ Bảy"];
                const revenues = Array(7).fill(0); // Mặc định 0 cho các ngày không có dữ liệu

                // Điền dữ liệu vào mảng revenues
                data.forEach(item => {
                    const dayOfWeek = item.dayOfWeek; // 1 = Chủ nhật, 7 = Thứ Bảy
                    revenues[dayOfWeek - 1] = item.revenue;
                });

                // Tạo hoặc cập nhật biểu đồ
                if (window.weeklyChart) {
                    // Cập nhật biểu đồ
                    window.weeklyChart.data.datasets[0].data = revenues;
                    window.weeklyChart.update();
                } else {
                    // Tạo biểu đồ
                    const ctx = document.getElementById("weekly-revenue-chart");
                    window.weeklyChart = new Chart(ctx, {
                        type: "bar",
                        data: {
                            labels: labels,
                            datasets: [{
                                label: "Doanh thu (VND)",
                                data: revenues,
                                borderColor: "rgba(0, 123, 255, 0.9)",
                                borderWidth: "0",
                                backgroundColor: "rgba(0, 123, 255, 0.5)"
                            }],
                        },
                        options: {
                            responsive: true,
                            scales: {
                                yAxes: [{
                                    ticks: {
                                        beginAtZero: true,
                                    },
                                }],
                            },
                        },
                    });
                }
            });
    }




( function ( $ ) {
    "use strict";

    //Team chart
    var ctx = document.getElementById( "team-chart" );
    ctx.height = 150;
    var myChart = new Chart( ctx, {
        type: 'line',
        data: {
            labels: [ "2010", "2011", "2012", "2013", "2014", "2015", "2016" ],
            type: 'line',
            defaultFontFamily: 'Montserrat',
            datasets: [ {
                data: [ 0, 7, 3, 5, 2, 10, 7 ],
                label: "Expense",
                backgroundColor: 'rgba(0,103,255,.15)',
                borderColor: 'rgba(0,103,255,0.5)',
                borderWidth: 3.5,
                pointStyle: 'circle',
                pointRadius: 5,
                pointBorderColor: 'transparent',
                pointBackgroundColor: 'rgba(0,103,255,0.5)',
                    }, ]
        },
        options: {
            responsive: true,
            tooltips: {
                mode: 'index',
                titleFontSize: 12,
                titleFontColor: '#000',
                bodyFontColor: '#000',
                backgroundColor: '#fff',
                titleFontFamily: 'Montserrat',
                bodyFontFamily: 'Montserrat',
                cornerRadius: 3,
                intersect: false,
            },
            legend: {
                display: false,
                position: 'top',
                labels: {
                    usePointStyle: true,
                    fontFamily: 'Montserrat',
                },


            },
            scales: {
                xAxes: [ {
                    display: true,
                    gridLines: {
                        display: false,
                        drawBorder: false
                    },
                    scaleLabel: {
                        display: false,
                        labelString: 'Month'
                    }
                        } ],
                yAxes: [ {
                    display: true,
                    gridLines: {
                        display: false,
                        drawBorder: false
                    },
                    scaleLabel: {
                        display: true,
                        labelString: 'Value'
                    }
                        } ]
            },
            title: {
                display: false,
            }
        }
    } );


    //Sales chart
    var ctx = document.getElementById( "sales-chart" );
    ctx.height = 150;
    var myChart = new Chart( ctx, {
        type: 'line',
        data: {
            labels: [ "2010", "2011", "2012", "2013", "2014", "2015", "2016" ],
            type: 'line',
            defaultFontFamily: 'Montserrat',
            datasets: [ {
                label: "Foods",
                data: [ 0, 30, 10, 120, 50, 63, 10 ],
                backgroundColor: 'transparent',
                borderColor: 'rgba(220,53,69,0.75)',
                borderWidth: 3,
                pointStyle: 'circle',
                pointRadius: 5,
                pointBorderColor: 'transparent',
                pointBackgroundColor: 'rgba(220,53,69,0.75)',
                    }, {
                label: "Electronics",
                data: [ 0, 50, 40, 80, 40, 79, 120 ],
                backgroundColor: 'transparent',
                borderColor: 'rgba(40,167,69,0.75)',
                borderWidth: 3,
                pointStyle: 'circle',
                pointRadius: 5,
                pointBorderColor: 'transparent',
                pointBackgroundColor: 'rgba(40,167,69,0.75)',
                    } ]
        },
        options: {
            responsive: true,

            tooltips: {
                mode: 'index',
                titleFontSize: 12,
                titleFontColor: '#000',
                bodyFontColor: '#000',
                backgroundColor: '#fff',
                titleFontFamily: 'Montserrat',
                bodyFontFamily: 'Montserrat',
                cornerRadius: 3,
                intersect: false,
            },
            legend: {
                display: false,
                labels: {
                    usePointStyle: true,
                    fontFamily: 'Montserrat',
                },
            },
            scales: {
                xAxes: [ {
                    display: true,
                    gridLines: {
                        display: false,
                        drawBorder: false
                    },
                    scaleLabel: {
                        display: false,
                        labelString: 'Month'
                    }
                        } ],
                yAxes: [ {
                    display: true,
                    gridLines: {
                        display: false,
                        drawBorder: false
                    },
                    scaleLabel: {
                        display: true,
                        labelString: 'Value'
                    }
                        } ]
            },
            title: {
                display: false,
                text: 'Normal Legend'
            }
        }
    } );







    //line chart
    var ctx = document.getElementById( "lineChart" );
    ctx.height = 150;
    var myChart = new Chart( ctx, {
        type: 'line',
        data: {
            labels: [ "January", "February", "March", "April", "May", "June", "July" ],
            datasets: [
                {
                    label: "My First dataset",
                    borderColor: "rgba(0,0,0,.09)",
                    borderWidth: "1",
                    backgroundColor: "rgba(0,0,0,.07)",
                    data: [ 22, 44, 67, 43, 76, 45, 12 ]
                            },
                {
                    label: "My Second dataset",
                    borderColor: "rgba(0, 123, 255, 0.9)",
                    borderWidth: "1",
                    backgroundColor: "rgba(0, 123, 255, 0.5)",
                    pointHighlightStroke: "rgba(26,179,148,1)",
                    data: [ 16, 32, 18, 26, 42, 33, 44 ]
                            }
                        ]
        },
        options: {
            responsive: true,
            tooltips: {
                mode: 'index',
                intersect: false
            },
            hover: {
                mode: 'nearest',
                intersect: true
            }

        }
    } );


    //bar chart
    var ctx = document.getElementById( "barChart" );
    //    ctx.height = 200;
    var myChart = new Chart( ctx, {
        type: 'bar',
        data: {
            labels: [ "January", "February", "March", "April", "May", "June", "July" ],
            datasets: [
                {
                    label: "My First dataset",
                    data: [ 65, 59, 80, 81, 56, 55, 40 ],
                    borderColor: "rgba(0, 123, 255, 0.9)",
                    borderWidth: "0",
                    backgroundColor: "rgba(0, 123, 255, 0.5)"
                            },
                {
                    label: "My Second dataset",
                    data: [ 28, 48, 40, 19, 86, 27, 90 ],
                    borderColor: "rgba(0,0,0,0.09)",
                    borderWidth: "0",
                    backgroundColor: "rgba(0,0,0,0.07)"
                            }
                        ]
        },
        options: {
            scales: {
                yAxes: [ {
                    ticks: {
                        beginAtZero: true
                    }
                                } ]
            }
        }
    } );

    //radar chart
    var ctx = document.getElementById( "radarChart" );
    ctx.height = 160;
    var myChart = new Chart( ctx, {
        type: 'radar',
        data: {
            labels: [ [ "Eating", "Dinner" ], [ "Drinking", "Water" ], "Sleeping", [ "Designing", "Graphics" ], "Coding", "Cycling", "Running" ],
            datasets: [
                {
                    label: "My First dataset",
                    data: [ 65, 59, 66, 45, 56, 55, 40 ],
                    borderColor: "rgba(0, 123, 255, 0.6)",
                    borderWidth: "1",
                    backgroundColor: "rgba(0, 123, 255, 0.4)"
                            },
                {
                    label: "My Second dataset",
                    data: [ 28, 12, 40, 19, 63, 27, 87 ],
                    borderColor: "rgba(0, 123, 255, 0.7",
                    borderWidth: "1",
                    backgroundColor: "rgba(0, 123, 255, 0.5)"
                            }
                        ]
        },
        options: {
            legend: {
                position: 'top'
            },
            scale: {
                ticks: {
                    beginAtZero: true
                }
            }
        }
    } );


    //pie chart
    var ctx = document.getElementById( "pieChart" );
    ctx.height = 300;
    var myChart = new Chart( ctx, {
        type: 'pie',
        data: {
            datasets: [ {
                data: [ 45, 25, 20, 10 ],
                backgroundColor: [
                                    "rgba(0, 123, 255,0.9)",
                                    "rgba(0, 123, 255,0.7)",
                                    "rgba(0, 123, 255,0.5)",
                                    "rgba(0,0,0,0.07)"
                                ],
                hoverBackgroundColor: [
                                    "rgba(0, 123, 255,0.9)",
                                    "rgba(0, 123, 255,0.7)",
                                    "rgba(0, 123, 255,0.5)",
                                    "rgba(0,0,0,0.07)"
                                ]

                            } ],
            labels: [
                            "green",
                            "green",
                            "green"
                        ]
        },
        options: {
            responsive: true
        }
    } );

    //doughut chart
    var ctx = document.getElementById( "doughutChart" );
    ctx.height = 150;
    var myChart = new Chart( ctx, {
        type: 'doughnut',
        data: {
            datasets: [ {
                data: [ 45, 25, 20, 10 ],
                backgroundColor: [
                                    "rgba(0, 123, 255,0.9)",
                                    "rgba(0, 123, 255,0.7)",
                                    "rgba(0, 123, 255,0.5)",
                                    "rgba(0,0,0,0.07)"
                                ],
                hoverBackgroundColor: [
                                    "rgba(0, 123, 255,0.9)",
                                    "rgba(0, 123, 255,0.7)",
                                    "rgba(0, 123, 255,0.5)",
                                    "rgba(0,0,0,0.07)"
                                ]

                            } ],
            labels: [
                            "green",
                            "green",
                            "green",
                            "green"
                        ]
        },
        options: {
            responsive: true
        }
    } );

    //polar chart
    var ctx = document.getElementById( "polarChart" );
    ctx.height = 150;
    var myChart = new Chart( ctx, {
        type: 'polarArea',
        data: {
            datasets: [ {
                data: [ 15, 18, 9, 6, 19 ],
                backgroundColor: [
                                    "rgba(0, 123, 255,0.9)",
                                    "rgba(0, 123, 255,0.8)",
                                    "rgba(0, 123, 255,0.7)",
                                    "rgba(0,0,0,0.2)",
                                    "rgba(0, 123, 255,0.5)"
                                ]

                            } ],
            labels: [
                            "green",
                            "green",
                            "green",
                            "green"
                        ]
        },
        options: {
            responsive: true
        }
    } );

    // single bar chart
    var ctx = document.getElementById( "singelBarChart" );
    ctx.height = 150;
    var myChart = new Chart( ctx, {
        type: 'bar',
        data: {
            labels: [ "Sun", "Mon", "Tu", "Wed", "Th", "Fri", "Sat" ],
            datasets: [
                {
                    label: "My First dataset",
                    data: [ 40, 55, 75, 81, 56, 55, 40 ],
                    borderColor: "rgba(0, 123, 255, 0.9)",
                    borderWidth: "0",
                    backgroundColor: "rgba(0, 123, 255, 0.5)"
                            }
                        ]
        },
        options: {
            scales: {
                yAxes: [ {
                    ticks: {
                        beginAtZero: true
                    }
                                } ]
            }
        }
    } );




} )( jQuery );