package com.advertisementShop.AdvertisementShop.config;

import com.advertisementShop.AdvertisementShop.models.Advertisement;
import com.advertisementShop.AdvertisementShop.models.Mail;
import com.advertisementShop.AdvertisementShop.repositories.AdvertisementRepository;
import com.advertisementShop.AdvertisementShop.services.AdvertisementService;
import com.advertisementShop.AdvertisementShop.services.MailService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

@Configuration
@EnableScheduling
public class TimeConfig {

    @Autowired
    private AdvertisementService advertisementService;
    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Autowired
    private MailService mailService;

    private static Logger logger = Logger.getGlobal();

    @Scheduled(fixedDelay = 3000,initialDelay = 3000)
    public void scheduletime(){
        ArrayList<Advertisement> advertisement= (ArrayList<Advertisement>) advertisementRepository.getAdvertisementsByStartedTrue();
        for (Advertisement ad:advertisement
        ) {
            Date d1=new Date();
            long diff=(d1.getTime()-ad.getTimeStarted().getTime())/(1000*60);
            if(diff>5) {
                ad.setIsActive(false);
                ad.setStarted(false);
                advertisementService.saveAdvertisement(ad);
//                Mail newMail=new Mail();
//                newMail.setMailFrom("ajaja");
//                newMail.setMailContent("bugagag");
//                newMail.setMailTo("asagyndykov06@gmail.com");
//                newMail.setMailSubject("ahaha");
//                mailService.sendEmail(newMail);
                logger.info("Stake by name: "+ad.getName()+ " is sold to "+ad.getFutureOwner().getFullName());
                logger.info("Dear "+ad.getCreatedBy()+ " your advertisement is sold! Price: "+ad.getTempPrice()+" Congratulations!");
            }
        }
        System.out.println("3 sec");
    }
}
