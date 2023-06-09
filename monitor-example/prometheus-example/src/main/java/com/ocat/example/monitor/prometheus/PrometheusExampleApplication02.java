package com.ocat.monitor.example.prometheus;

import io.prometheus.client.Collector;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.CounterMetricFamily;
import io.prometheus.client.Gauge;
import io.prometheus.client.exporter.HTTPServer;

import java.util.Arrays;
import java.util.List;

/**
 * @Description:Collector的使用
 * @Author: stone-98
 * @createTime: 2023年04月11日 17:05:16
 */
public class PrometheusExampleApplication02 {
    
    private static final Gauge NACOS_MONITOR = Gauge.build().name("nacos_monitor").labelNames("module", "name")
            .help("nacos_monitor").register();
    
    static {
        NACOS_MONITOR.labels("naming", "serviceInfoMapSize").inc();
    }
    
    public static void main(String[] args) throws Exception {
        
        // 创建一个自定义采集器实例
        ExampleCollector01 collector01 = new ExampleCollector01();
        ExampleCollector02 collector02 = new ExampleCollector02();
        
        // 将自定义采集器注册到全局注册表中
        CollectorRegistry.defaultRegistry.register(collector01);
        CollectorRegistry.defaultRegistry.register(collector02);
        
        // 启动一个 HTTP 服务器，暴露指标数据
        HTTPServer server = new HTTPServer(8080);
        
        // 等待程序退出信号
        System.in.read();
        
        // 关闭 HTTP 服务器
        server.stop();
    }
    
    public static class ExampleCollector01 extends Collector {
        
        private CounterMetricFamily myCounterMetricFamily = new CounterMetricFamily("the name 01", "the help 01",
                Arrays.asList("label_name01", "label_value01"));
        
        public ExampleCollector01() {
        }
        
        // 每次请求都会执行一次该方法
        @Override
        public List<MetricFamilySamples> collect() {
            // 实现自定义指标的采集逻辑，假设在此处采集到了指标值
            myCounterMetricFamily.addMetric(Arrays.asList("value1", "value2"), 1.0);
            return Arrays.asList(myCounterMetricFamily);
        }
    }
    
    public static class ExampleCollector02 extends Collector {
        
        private CounterMetricFamily myCounterMetricFamily = new CounterMetricFamily("the name 02", "the help 02",
                Arrays.asList("label_name02", "label_value02"));
        
        public ExampleCollector02() {
        }
        
        // 每次请求都会执行一次该方法
        @Override
        public List<MetricFamilySamples> collect() {
            // 实现自定义指标的采集逻辑，假设在此处采集到了指标值
            myCounterMetricFamily.addMetric(Arrays.asList("value1", "value2"), 1.0);
            return Arrays.asList(myCounterMetricFamily);
        }
    }
}
