package ru.innopolis.stc21.med.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.innopolis.stc21.med.service.GeoIP;
import ru.innopolis.stc21.med.service.GeoIPService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class GeoIPController {

    public GeoIPController() {
    }

   /* @GetMapping("/geoip")
    public String geoGetIp() {
        return "geoip";
    }*/

    @RequestMapping(value = "/geoip", method = {RequestMethod.GET,RequestMethod.POST})
    public String geoIpLocation(
            HttpServletRequest httpServletRequest
    ) throws Exception {
        String ipAddress = httpServletRequest.getRemoteAddr();

        System.out.println(ipAddress);

        GeoIPService locationService
                = new GeoIPService();
        GeoIP userGeoPosition = locationService.getLocation(ipAddress);

        return "redirect:/hospitallocation";
    }

    @GetMapping("/hospitallocation")
    public String hello() {
        return "hospitallocation";
    }
}
