package com.michaelcgood;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.core.io.ClassPathResource;

public class ItemReaderCOS extends FlatFileItemReader<String> {

    public ItemReaderCOS(String location) {
        this.setResource(new ClassPathResource(location));
        this.setLineMapper((s, i) -> s.trim());
    }
}
