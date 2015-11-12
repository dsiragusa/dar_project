package etu.upmc.fr.TaskScheduler;

import etu.upmc.fr.entity.Service;
import etu.upmc.fr.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by iShavgula on 11/11/15.
 */
@Component
public class TaskScheduler {

    @Autowired
    private ServiceRepository serviceRepository;

    public TaskScheduler() {

    }

    @Scheduled(fixedDelay=10000)
    public void checkDeadlines() {
        List<Service> allServices = serviceRepository.findAll();
        Collections.sort(allServices);

        Date now = new Date();
        for (Service service : allServices) {
            if (service.getIsActive() == 1 &&
                    (service.getBiddingDeadline().before(now) ||
                            service.getBiddingDeadline().equals(now))) {
                service.setIsActive(0);
            }
        }

        serviceRepository.save(allServices);
    }
}
