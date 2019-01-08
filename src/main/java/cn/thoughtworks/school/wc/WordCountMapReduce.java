package cn.thoughtworks.school.wc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class WordCountMapReduce {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        // 创建配置对象
        Configuration configuration = new Configuration();

        Job job = Job.getInstance(configuration, "wordcount");

        // 设置运行job类
        job.setJarByClass(WordCountMapReduce.class);

        // 设置mapper类
        job.setMapperClass(WordCountMapper.class);

        //
        job.setReducerClass(WordCountReduce.class);

        //
        job.setMapOutputKeyClass(Text.class);

        job.setOutputValueClass(IntWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        // 设置输入的路径
        FileInputFormat.setInputPaths(job, new Path("hdfs://localhost:9000/words"));
        FileOutputFormat.setOutputPath(job, new Path("hdfs://localhost:9000/out06"));

        // 提交job
        boolean b = job.waitForCompletion(true);

        if(!b) {
            System.err.print("failed");
        }
    }
}
