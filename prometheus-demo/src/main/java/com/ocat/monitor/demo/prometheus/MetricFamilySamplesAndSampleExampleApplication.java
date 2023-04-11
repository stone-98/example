package com.ocat.monitor.demo.prometheus;

import io.prometheus.client.Collector;
import io.prometheus.client.Counter;
import io.prometheus.client.exporter.HTTPServer;

import java.io.IOException;
import java.util.List;

/**
 * @Description:
 * @Author: stone-98
 * @createTime: 2023年04月11日 18:09:43
 */
public class MetricFamilySamplesAndSampleExampleApplication
{
    private static final Counter httpRequestsTotal = Counter.build()
            .name("http_requests_total")
            .labelNames("method", "status")
            .help("Total HTTP requests.")
            .unit("Counter")
            .register();

    public static void main(String[] args) throws IOException, InterruptedException {
        // 模拟请求
        simulateRequest("GET", "200");
        simulateRequest("POST", "200");
        simulateRequest("GET", "500");
        simulateRequest("GET", "200");

        // 注册 DefaultExports
//        DefaultExports.initialize();

        // 创建 HTTP 服务器
//        HTTPServer server = new HTTPServer(8080);

        // 等待请求
//        Thread.sleep(Long.MAX_VALUE);
        List<Collector.MetricFamilySamples> metricFamilySamples = httpRequestsTotal.collect();
        for (Collector.MetricFamilySamples metricFamilySample : metricFamilySamples) {
            System.out.println("MetricFamilySamples name:" + metricFamilySample.name);
            System.out.println("MetricFamilySamples help:" + metricFamilySample.help);
            System.out.println("MetricFamilySamples unit:" + metricFamilySample.unit);
            for (Collector.MetricFamilySamples.Sample sample : metricFamilySample.samples) {
                System.out.println("Sample name: " + sample.name);
                System.out.println("Sample value: " + sample.value);
                System.out.println("Sample labels: " + sample.labelNames + " " + sample.labelValues);
            }
        }
    }

    private static void simulateRequest(String method, String status) {
        httpRequestsTotal.labels(method, status).inc();
    }
}
