package com.example.clothing_sell_website.service.admin.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.clothing_sell_website.dto.respone.MonthlyRevenueResponse;
import com.example.clothing_sell_website.dto.respone.WeeklyRevenueResponse;
import com.example.clothing_sell_website.entity.Bill;
import com.example.clothing_sell_website.repository.BillRepository;
import com.example.clothing_sell_website.service.admin.BillService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BillServiceImpl implements BillService {
    BillRepository billRepository;

    @Override
    public List<Bill> getBills() {
        return billRepository.findAll();
    }

    @Override
    public Bill getBillById(Integer id) {
        return billRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy hóa đơn với id là " + id));
    }

    @Override
    public Bill save(Bill bill) {
        return billRepository.save(bill);
    }

    @Override
    public Float getTotalAmount(Integer orderId) {
        return billRepository.getTotalAmount(orderId);
    }

    @Override
    public List<Map<String, Object>> getYearlyRevenueChartData() {
        return billRepository.getYearlyRevenueChartData();
    }

    @Override
    public List<MonthlyRevenueResponse> getMonthlyRevenueByYear(int year) {
        List<Object[]> monthlyRevenueList = billRepository.getMonthlyRevenueByYear(year);
        return monthlyRevenueList.stream()
                .map(record ->
                        new MonthlyRevenueResponse((int) record[0], (double) record[1] / 1000000, (long) record[2]))
                .toList();
    }

    @Override
    public List<WeeklyRevenueResponse> getWeeklyRevenue(LocalDate startDate, LocalDate endDate) {
        List<Object[]> weeklyRevenueList = billRepository.getWeeklyRevenue(startDate, endDate);
        return weeklyRevenueList.stream()
                .map(record -> new WeeklyRevenueResponse((int) record[0], (double) record[1]))
                .toList();
    }
}
