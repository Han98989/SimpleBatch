package com.michaelcgood;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Factory {

    @Value("${tag1}")
    private String tag1;

    @Value("${tag2}")
    private String tag2;


    public FlatFileItemReader<String> crete(Tag tag){
        if(tag.equals(Tag.TAG2)){
            return new ItemReaderCOS(tag2);
        }
        if(tag.equals(Tag.TAG1)){
            return new ItemReaderCOS(tag1);
        }
        return null;
    }

}
