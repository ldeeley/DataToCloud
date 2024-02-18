package com.example.datatocloud.config;


import com.example.datatocloud.dao.CustomerEntityDAO;

import com.example.datatocloud.dto.CustomerRequestDTO;
import com.example.datatocloud.entity.CustomerEntity;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;

import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.kafka.KafkaItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import java.awt.image.Kernel;

@Configuration
@AllArgsConstructor
public class ApplicationBatchConfig {


    private CustomerEntityDAO customerEntityDAO;
    private JobRepository jobRepository;
    private PlatformTransactionManager platformTransactionManager;

    @Bean
    public FlatFileItemReader<CustomerEntity> reader() {

        FlatFileItemReader<CustomerEntity> itemReader = new FlatFileItemReader<>();
            itemReader.setResource(new FileSystemResource("src/main/resources/itcont_2018_20181231_52010302.txt"));
            itemReader.setName("csvReader");
            itemReader.setLinesToSkip(1);
            itemReader.setLineMapper(lineMapper());
        return itemReader;
    }

    private LineMapper<CustomerEntity> lineMapper() {
        DefaultLineMapper<CustomerEntity> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter("|");
        lineTokenizer.setStrict(false);
//        as per the headings in the CSV file
        lineTokenizer.setNames(
                                "CustomerId",
                                "Grp",
                                "Tipe",
                                "PVal",
                                "CreateDateTime",
                                "Band",
                                "Region",
                                "Name",
                                "MiddleName",
                                "State",
                                "ZIP",
                                "Department",
                                "Sector",
                                "Code",
                                "Area",
                                "XCode",
                                "Mobile",
                                "Land",
                                "Misc",
                                "Category",
                                "Identifier");

        BeanWrapperFieldSetMapper<CustomerEntity> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(CustomerEntity.class);

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;

    }

    @Bean
    public CustomerProcessor processor(){
        System.out.println(" Thread : "+ Thread.currentThread().getName());
        System.out.println(" Process : "+ ProcessHandle.current().pid());
        return new CustomerProcessor();
    }

    @Bean
    public RepositoryItemWriter<CustomerEntity> writer(){
        RepositoryItemWriter<CustomerEntity> writer=new RepositoryItemWriter<>();
        writer.setRepository(customerEntityDAO);
        writer.setMethodName("save");
        return writer;
    }


//    @Bean
//    public KafkaItemWriter<CustomerEntity,CustomerEntity> kafkaItemWriter(){
//
//    }

    @Bean
    public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager){
        return new StepBuilder("step1",jobRepository).
                <CustomerEntity,CustomerEntity>chunk(10,transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    public Step step2(JobRepository jobRepository, PlatformTransactionManager transactionManager){
        return new StepBuilder("step2",jobRepository).
                <CustomerEntity,CustomerEntity>chunk(10,transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .taskExecutor(taskExecutor())
                .build();
    }


    @Bean
    public Step step3(JobRepository jobRepository, PlatformTransactionManager transactionManager){
        return new StepBuilder("step3",jobRepository).
                <CustomerEntity,CustomerEntity>chunk(10,transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    public Step step4(JobRepository jobRepository, PlatformTransactionManager transactionManager){
        return new StepBuilder("step4",jobRepository).
                <CustomerEntity,CustomerEntity>chunk(10,transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .taskExecutor(taskExecutor())
                .build();
    }

//    @Bean
//    public Job runJob(JobRepository jobRepository,PlatformTransactionManager transactionManager){
//        return new JobBuilder("importCustomers",jobRepository)
//                .flow(step1(jobRepository,transactionManager)).end().build();
//    }

//    @Bean
//    public Job sampleJob(JobRepository jobRepository,Step step1){
//        return new JobBuilder("sampleJob",jobRepository)
//                .start(step1)
//                .build();
//    }

//    @Bean
//    public TaskExecutor taskExecutor(){
//        ThreadPoolTaskExecutor taskExecutor=new ThreadPoolTaskExecutor();
//        taskExecutor.setMaxPoolSize(12);
//        taskExecutor.setCorePoolSize(8);
//        taskExecutor.setQueueCapacity(4);
//        taskExecutor.setThreadNamePrefix("MyBatchThread-");
//        return taskExecutor;
//    }
    @Bean
    public Job job(JobRepository jobRepository) {
    return new JobBuilder("job", jobRepository)
            .start(splitFlow())
            .build()        //builds FlowJobBuilder instance
            .build();       //builds Job instance
}

    @Bean
    public Flow splitFlow() {
        return new FlowBuilder<SimpleFlow>("splitFlow")
                .split(taskExecutor())
                .add(flow1(), flow2(),flow3(), flow4())
                .build();
    }

    @Bean
    public Flow flow1() {
        return new FlowBuilder<SimpleFlow>("flow1")
                .start(step1(jobRepository,platformTransactionManager))
                .build();
    }

    @Bean
    public Flow flow2() {
        return new FlowBuilder<SimpleFlow>("flow2")
                .start(step2(jobRepository,platformTransactionManager))
                .build();
    }
    @Bean
    public Flow flow3() {
        return new FlowBuilder<SimpleFlow>("flow3")
                .start(step3(jobRepository,platformTransactionManager))
                .build();
    }

    @Bean
    public Flow flow4() {
        return new FlowBuilder<SimpleFlow>("flow4")
                .start(step4(jobRepository,platformTransactionManager))
                .build();
    }

    @Bean
    public TaskExecutor taskExecutor() {
//        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
//        threadPoolTaskExecutor.setMaxPoolSize(12);
//        threadPoolTaskExecutor.setCorePoolSize(8);
//        threadPoolTaskExecutor.setQueueCapacity(4);
//        threadPoolTaskExecutor.setThreadNamePrefix("MyBatchThread-");
//        return threadPoolTaskExecutor;
        return new SimpleAsyncTaskExecutor("spring_batch");
    }

}
