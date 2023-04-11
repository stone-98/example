package com.ocat.monitor.example.prometheus;

import io.prometheus.client.Collector;

import java.util.Arrays;

/**
 * 理解MetricFamilySamples与Sample的含义
 */
public class PrometheusExampleApplication03 {
    
    public static void main(String[] args) {
        // 创建一个名为http_requests_total的MetricFamilySamples，包含了多个Sample
        Collector.MetricFamilySamples httpRequestsTotal = new Collector.MetricFamilySamples(
                // 指标名称
                "http_requests_total",
                // 指标类型为计数器
                Collector.Type.COUNTER,
                // 指标帮助文本
                "Total number of HTTP requests",
                Arrays.asList(
                        new Collector.MetricFamilySamples.Sample("http_requests_total", Arrays.asList("method", "status"), Arrays.asList("GET", "failure"), 100),
                        new Collector.MetricFamilySamples.Sample("http_requests_total", Arrays.asList("method", "status"), Arrays.asList("POST", "success"), 100)
                )
        );
        // 名称
        System.out.println("MetricFamilySamples.name:" + httpRequestsTotal.name);
        // 类型
        System.out.println("MetricFamilySamples.type:" + httpRequestsTotal.type);
        // 单位
        System.out.println("MetricFamilySamples.unit:" + httpRequestsTotal.unit);
        // 帮助文本
        System.out.println("MetricFamilySamples.help:" + httpRequestsTotal.help);
        for (Collector.MetricFamilySamples.Sample sample : httpRequestsTotal.samples) {
            // 此name与MetricFamilySamples.name对应
            System.out.println("Sample.name:" + sample.name);
            // labelName
            System.out.println("Sample.labelNames:" + sample.labelNames);
            // labelValue
            System.out.println("Sample.labelValues:" + sample.labelValues);
            // value：标记的指标值，与 sample 中的值相同。
            // timestamp：标记的时间戳，用于指示该指标值是何时采集的。
            // labels：可选标记的标签。示例标签用于标记采样数据的上下文，通常是采样值的来源、处理或相关性。
            System.out.println("Sample.exemplar:" + sample.exemplar);
            // 该度量的采集时间
            System.out.println("Sample.timestampMs:" + sample.timestampMs);
            
        }
    
    }
    
}
