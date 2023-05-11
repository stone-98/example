package com.ocat.example.monitor.prometheus;

import io.prometheus.client.Collector;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Gauge;
import io.prometheus.client.exporter.HTTPServer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class PrometheusExampleApplication01 {
    public static void main(String[] args) {
//        DefaultExports.initialize();
//        CollectorRegistry registry = new CollectorRegistry();
        // 创建一个 Gauge 类型的度量
        Gauge requestTotal = Gauge.build().name("request_total") // 指标名称
                .help("request total") // 指标帮助信息
                .labelNames("type") // 指标标签
                .register();
        List<Collector.MetricFamilySamples> collect = requestTotal.collect();
        // 定义度量信息
        long total = 100;
        long success = 99;
        long fail = 1;

        // 添加度量数据
        requestTotal.labels("total").set(total);
        requestTotal.labels("success").set(success);
        requestTotal.labels("fail").set(fail);

        // 创建采集器
        Collector collector = new Collector() {
            // 采集指标信息
            public List<MetricFamilySamples> collect() {
                List<io.prometheus.client.Collector.MetricFamilySamples> mfs = new ArrayList<>();
                return mfs;
            }
        };

        // 注册采集器
        CollectorRegistry.defaultRegistry.register(collector);

        // 创建 HTTP 服务器并暴露度量信息
        try {
            new HTTPServer(8080);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
