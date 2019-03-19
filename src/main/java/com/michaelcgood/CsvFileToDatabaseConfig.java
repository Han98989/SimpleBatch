package com.michaelcgood;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@EnableBatchProcessing
@Configuration
public class CsvFileToDatabaseConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public DataSource dataSource;

    // begin reader, writer, and processor

    @Autowired
    private Factory factory;


    @Bean
    @StepScope
    public FlatFileItemReader<String> csvAnimeReader() {
        return factory.crete(Tag.TAG1);
    }

    @Bean
    @StepScope
    public FlatFileItemReader<String> csvAnimeReader2() {
        return factory.crete(Tag.TAG2);

    }


    @Bean
    ItemProcessor<String, String> csvAnimeProcessor() {
        return new AnimeProcessor();
    }

    @Bean
    public ItemWriter<String> csvAnimeWriter() {

        return null;
    }

    // end reader, writer, and processor

    // begin job info
    @Bean
    public Step csvFileToDatabaseStep() {
        return stepBuilderFactory.get("csvFileToDatabaseStep")
                .<String, String>chunk(1)
                .reader(csvAnimeReader())
                .processor(csvAnimeProcessor())
                .writer(csvAnimeWriter())
                .build();
    }

    @Bean
    public Step csvFileToDatabaseStep2() {
        return stepBuilderFactory.get("csvFileToDatabaseStep2")
                .<String, String>chunk(1)
                .reader(csvAnimeReader2())
                .processor(csvAnimeProcessor())
                .writer(csvAnimeWriter())
                .build();
    }


    Job csvFileToDatabaseJob() {
        return jobBuilderFactory.get("csvFileToDatabaseJob")
                .incrementer(new RunIdIncrementer())
                .flow(csvFileToDatabaseStep())
                .next(csvFileToDatabaseStep2())
                .end()
                .build();
    }
    // end job info
}
