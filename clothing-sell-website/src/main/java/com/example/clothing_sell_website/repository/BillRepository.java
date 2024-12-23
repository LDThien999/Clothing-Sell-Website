package com.example.clothing_sell_website.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.clothing_sell_website.entity.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {

    @Query("SELECT SUM(ol.quantity * p.price)" + "FROM OrderList ol "
            + "JOIN Cart c ON ol.cart.cartId = c.cartId "
            + "JOIN Product p ON p.productId = c.product.productId "
            + "WHERE ol.order.orderId = :orderId")
    Float getTotalAmount(@Param("orderId") Integer orderId);

    @Query("SELECT new map(FUNCTION('YEAR', b.date) AS year, SUM(b.totalAmount) as total) "
            + "FROM Bill b GROUP BY FUNCTION('YEAR', b.date) ORDER BY year ASC")
    List<Map<String, Object>> getYearlyRevenueChartData();

    @Query(
            "SELECT MONTH(b.date) AS month, SUM(DISTINCT b.totalAmount) AS revenue, COUNT(DISTINCT ol.cart.customer) AS customerCount "
                    + "FROM Bill b "
                    + "JOIN OrderList ol "
                    + "ON b.order = ol.order "
                    + "WHERE YEAR(b.date) = :year "
                    + "GROUP BY MONTH(b.date) "
                    + "ORDER BY MONTH(b.date)")
    List<Object[]> getMonthlyRevenueByYear(@Param("year") int year);

    @Query(
            value = "SELECT DATEPART(WEEKDAY, b.Date) AS dayOfWeek, SUM(b.Total_Amount) AS revenue " + "FROM Bill b "
                    + "WHERE CAST(b.Date AS DATE) BETWEEN :startDate AND :endDate "
                    + "GROUP BY DATEPART(WEEKDAY, b.Date) ORDER BY dayOfWeek",
            nativeQuery = true)
    List<Object[]> getWeeklyRevenue(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
