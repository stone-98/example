package com.ocat.example.monitor.prometheus;

import io.prometheus.client.Collector;
import io.prometheus.client.Gauge;

import java.util.List;

/**
 * Gauge与MetricFamilySamples是一对一的关系
 */
public class PrometheusExampleApplication01 {
    
    public static void main(String[] args) {
        Gauge requestTotal = Gauge.build().name("request_total") // 指标名称
                .help("request total") // 指标帮助信息
                .labelNames("type", "status") // 指标标签
                .register();
        // 定义度量信息
        long total = 100;
        long success = 99;
        long fail = 1;
        
        // 添加度量数据
        requestTotal.labels("GET", "200").set(total);
        requestTotal.labels("POST", "404").set(success);
        requestTotal.labels("PUT", "500").set(fail);
        
        List<Collector.MetricFamilySamples> metricFamilySamples = requestTotal.collect();
        for (Collector.MetricFamilySamples metricFamilySample : metricFamilySamples) {
            // 名称
            System.out.println("MetricFamilySamples.name:" + metricFamilySample.name);
            // 类型
            System.out.println("MetricFamilySamples.type:" + metricFamilySample.type);
            // 单位
            System.out.println("MetricFamilySamples.unit:" + metricFamilySample.unit);
            // 帮助文本
            System.out.println("MetricFamilySamples.help:" + metricFamilySample.help);
            for (Collector.MetricFamilySamples.Sample sample : metricFamilySample.samples) {
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
}
