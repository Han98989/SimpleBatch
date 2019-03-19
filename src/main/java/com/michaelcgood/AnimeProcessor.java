package com.michaelcgood;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

public class AnimeProcessor implements ItemProcessor<String, String> {
	
    private static final Logger log = LoggerFactory.getLogger(AnimeProcessor.class);
    
    @Override
    public String process(final String AnimeDTO) throws Exception {

        log.info("Converting (" + AnimeDTO + ") ");

        return AnimeDTO;
    }

}
