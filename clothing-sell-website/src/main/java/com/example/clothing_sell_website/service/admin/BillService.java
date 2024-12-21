package com.example.clothing_sell_website.service.admin;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.example.clothing_sell_website.dto.respone.MonthlyRevenueResponse;
import com.example.clothing_sell_website.dto.respone.WeeklyRevenueResponse;
import com.example.clothing_sell_website.entity.Bill;
import org.springframework.data.repository.query.Param;

public interface BillService {
    List<Bill> getBills();

    Bill getBillById(Integer id);

    Bill save(Bill bill);

    Float getTotalAmount(Integer orderId);
    List<Map<String, Object>> getYearlyRevenueChartData();
    List<MonthlyRevenueResponse> getMonthlyRevenueByYear(int year);
    List<WeeklyRevenueResponse> getWeeklyRevenue(LocalDate startDate, LocalDate endDate);

}
