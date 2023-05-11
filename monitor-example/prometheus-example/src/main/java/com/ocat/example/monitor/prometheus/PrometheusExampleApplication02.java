package com.ocat.example.monitor.prometheus;

import io.prometheus.client.*;
import io.prometheus.client.exporter.HTTPServer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description:
 * @Author: stone-98
 * @createTime: 2023年04月11日 17:05:16
 */
public class PrometheusExampleApplication02
{
    public static void main(String[] args) throws Exception {
        // 注册默认的 JVM 指标
//            DefaultExports.initialize();

        // 创建一个自定义采集器实例
        ExampleCollector collector = new ExampleCollector();

        // 将自定义采集器注册到全局注册表中
        CollectorRegistry.defaultRegistry.register(collector);

        // 启动一个 HTTP 服务器，暴露指标数据
        HTTPServer server = new HTTPServer(8080);

        // 等待程序退出信号
        System.in.read();

        // 关闭 HTTP 服务器
        server.stop();
    }

    public static class ExampleCollector extends Collector {

        private CounterMetricFamily myCounterMetricFamily =  new CounterMetricFamily("the name", "the thlp", Arrays.asList("label_name1", "label_name2"));;

        public ExampleCollector() {
        }

        @Override
        public List<Collector.MetricFamilySamples> collect() {
            // 实现自定义指标的采集逻辑，假设在此处采集到了指标值
            String labelValue1 = "value1";
            String labelValue2 = "value2";
            double metricValue = 1.0;
            myCounterMetricFamily.addMetric(Arrays.asList(labelValue1, labelValue2), metricValue);

            List<io.prometheus.client.Collector.MetricFamilySamples> mfsList = new ArrayList<>();
            mfsList.add(myCounterMetricFamily);
            return mfsList;
        }
    }
}
