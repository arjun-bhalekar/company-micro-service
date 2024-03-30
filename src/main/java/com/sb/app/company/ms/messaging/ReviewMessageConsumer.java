package com.sb.app.company.ms.messaging;

import com.sb.app.company.ms.dto.ReviewMessage;
import com.sb.app.company.ms.service.CompanyService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ReviewMessageConsumer {

    private final CompanyService companyService;

    public ReviewMessageConsumer(CompanyService companyService) {
        this.companyService = companyService;
    }

    @RabbitListener(queues = "companyRatingQueue")
    public void consumeMessage(ReviewMessage reviewMessage) {
        System.out.println("reviewMessage consumed : (" + reviewMessage.getId() + ": " + reviewMessage.getTitle() + ")");
        companyService.updateCompanyRating(reviewMessage);
    }

}
