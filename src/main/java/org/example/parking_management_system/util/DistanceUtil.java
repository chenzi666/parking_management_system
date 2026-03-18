package org.example.parking_management_system.util;

import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GeodeticCurve;
import org.gavaghan.geodesy.GlobalCoordinates;

import java.math.BigDecimal;

/**
 * 距离计算工具类
 * 使用Geodesy库计算地球上两点之间的距离
 */
public class DistanceUtil {
    
    /**
     * 计算两个经纬度坐标点之间的距离（米）
     * 
     * @param lat1 第一个点的纬度
     * @param lon1 第一个点的经度
     * @param lat2 第二个点的纬度
     * @param lon2 第二个点的经度
     * @return 两点之间的距离（米）
     */
    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        // 创建坐标点
        GlobalCoordinates point1 = new GlobalCoordinates(lat1, lon1);
        GlobalCoordinates point2 = new GlobalCoordinates(lat2, lon2);
        
        // 使用WGS84椭球体模型计算距离
        GeodeticCalculator calculator = new GeodeticCalculator();
        Ellipsoid reference = Ellipsoid.WGS84;
        
        // 计算大地曲线
        GeodeticCurve curve = calculator.calculateGeodeticCurve(reference, point1, point2);
        
        // 返回椭球体距离（米）
        return curve.getEllipsoidalDistance();
    }
    
    /**
     * 计算两个BigDecimal类型经纬度坐标点之间的距离（米）
     *
     * @param lat1 第一个点的纬度
     * @param lon1 第一个点的经度
     * @param lat2 第二个点的纬度
     * @param lon2 第二个点的经度
     * @return 两点之间的距离（米）
     */
    public static double calculateDistance(BigDecimal lat1, BigDecimal lon1, BigDecimal lat2, BigDecimal lon2) {
        return calculateDistance(
            lat1.doubleValue(), 
            lon1.doubleValue(), 
            lat2.doubleValue(), 
            lon2.doubleValue()
        );
    }
    
    /**
     * 计算两个经纬度坐标点之间的距离（千米）
     * 
     * @param lat1 第一个点的纬度
     * @param lon1 第一个点的经度
     * @param lat2 第二个点的纬度
     * @param lon2 第二个点的经度
     * @return 两点之间的距离（千米）
     */
    public static double calculateDistanceInKilometers(double lat1, double lon1, double lat2, double lon2) {
        return calculateDistance(lat1, lon1, lat2, lon2) / 1000.0;
    }
    
    /**
     * 计算两个BigDecimal类型经纬度坐标点之间的距离（千米）
     *
     * @param lat1 第一个点的纬度
     * @param lon1 第一个点的经度
     * @param lat2 第二个点的纬度
     * @param lon2 第二个点的经度
     * @return 两点之间的距离（千米）
     */
    public static double calculateDistanceInKilometers(BigDecimal lat1, BigDecimal lon1, BigDecimal lat2, BigDecimal lon2) {
        return calculateDistance(lat1, lon1, lat2, lon2) / 1000.0;
    }
    
    /**
     * 计算两个坐标点之间的距离（米）
     * 
     * @param point1 第一个坐标点
     * @param point2 第二个坐标点
     * @return 两点之间的距离（米）
     */
    public static double calculateDistance(GlobalCoordinates point1, GlobalCoordinates point2) {
        // 使用WGS84椭球体模型计算距离
        GeodeticCalculator calculator = new GeodeticCalculator();
        Ellipsoid reference = Ellipsoid.WGS84;
        
        // 计算大地曲线
        GeodeticCurve curve = calculator.calculateGeodeticCurve(reference, point1, point2);
        
        // 返回椭球体距离（米）
        return curve.getEllipsoidalDistance();
    }
    
    /**
     * 计算两个坐标点之间的距离（千米）
     * 
     * @param point1 第一个坐标点
     * @param point2 第二个坐标点
     * @return 两点之间的距离（千米）
     */
    public static double calculateDistanceInKilometers(GlobalCoordinates point1, GlobalCoordinates point2) {
        return calculateDistance(point1, point2) / 1000.0;
    }
}